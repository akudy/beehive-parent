/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.file.AntPathMatcher
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-12-22
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.file;

import org.beehive.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Ant路径表达式匹配器，支持Ant的路径表达式匹配操作。
 * <br>
 * 提供Ant路径表达式的匹配支持。Ant 路径表达式匹配通配符规则如下：<br>
 * <ul>
 *   <li>{@code ?} —— 匹配任意一个字符（至少一个）</li>
 *   <li>{@code *} —— 匹配任意多个字符（0个或多个）</li>
 *   <li>{@code **} —— 匹配任意多个层级目录（0个或多个）</li>
 *   <li>最长匹配原则/优先精确匹配原则；即如果出现 <code>/&#42;&#42;/a.jsp</code>和<code>/demo/test/a.jsp</code>，则优先使用<code>/demo/test/a.jsp</code>进行匹配。</li>
 * </ul>
 * <p>示例如下：</p>
 * <ul>
 *     <li><code>org/beehive/core/test.jsp</code> 可以匹配 <code>org/beehive/core/test.jsp</code></li>
 *     <li><code>org/beehive/core/t?st.jsp</code> 可以匹配 <code>org/beehive/core/test.jsp</code>，也可以匹配 <code>org/beehive/core/tast.jsp</code></li>
 *     <li><code>org/beehive/&#42;/test.jsp</code> 可以匹配 <code>org/beehive/core/test.jsp</code>，也可以匹配 <code>org/beehive/util/test.jsp</code>，也可以匹配 <code>org/beehive/test.jsp</code>，但是不能匹配 <code>org/beehive/core/file/test.jsp</code></li>
 *     <li><code>org/beehive/&#42;&#42;/test.jsp</code> 可以匹配 <code>org/beehive/core/test.jsp</code>，也可以匹配 <code>org/beehive/util/test.jsp</code>，也可以匹配 <code>org/beehive/test.jsp</code>，也可以匹配 <code>org/beehive/core/file/test.jsp</code></li>
 * </ul>
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.file</code></li>
 *   <li>Class Name: <code>AntPathMatcher</code></li>
 *   <li>Java Version Used: Java 8</li>
 *   <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 *   <dd>
 *     <table border="1" cellspacing="0" cellpadding="0" summary=""Upgrade&shy;Modify History>
 *       <tr>
 *         <th>Version</th>
 *         <th>Environment</th>
 *         <th>ModifyTime</th>
 *         <th>Modifier</th>
 *         <th>Description</th>
 *        </tr>
 *        <tr>
 *         <td align="center"><em>1.0</em></td>
 *         <td align="center"><em>Java 8</em></td>
 *         <td align="center"><em>2020/12/22</em></td>
 *         <td align="center"><em>akudy</em></td>
 *         <td><em>Define</em></td>
 *        </tr>
 *     </table>
 *   </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @since 1.0
 */
public class AntPathMatcher implements PathMatcher {

    /**
     * 匹配任意数量的字符
     */
    private static final String ANY_COUNT_CHAR = "*";

    /**
     * 匹配任意单个字符
     */
    private static final String ANY_SINGLE_CHAR = "?";

    /**
     * 匹配任意数量的目录和文件
     */
    private static final String ANY_COUNT_DIR = "**";

    private static final char[] WILDCARD_CHARS = {ANY_COUNT_CHAR.charAt(0), ANY_SINGLE_CHAR.charAt(0)};

    /**
     * 模式串最大缓存大小
     */
    private static final int PATTERN_TOKEN_CACHE_SIZE = 4096;

    /**
     * 模式串 Token 缓存
     */
    private final Map<String, String[]> patternTokenCache = new ConcurrentHashMap<>(PATTERN_TOKEN_CACHE_SIZE);

    /**
     * 路径分割符
     */
    private String pathSeparator;

    /**
     * 大小写敏感，true-表示敏感（不忽略大小写）；false-表示不敏感（忽略大小写）
     */
    private boolean caseSensitive;

    public AntPathMatcher() {
        this.pathSeparator = "/";
        this.caseSensitive = true;
    }

    public AntPathMatcher(String pathSeparator, boolean caseSensitive) {
        this.pathSeparator = pathSeparator;
        this.caseSensitive = caseSensitive;
    }

    @Override
    public boolean isPattern(String path) {
        return StringUtils.containsAny(path, WILDCARD_CHARS);
    }

    @Override
    public boolean match(String pattern, String path) {
        // 如果模式字符串不是模式字符串，则进行完全匹配；如果相等则直接返回
        if (!isPattern(pattern) && pattern.equals(path)) {
            return true;
        }
        return doMatch(pattern, path);
    }

    /**
     * 进行模式字符串的分割解析；为了提高效率，内部进行默认字符串的缓存
     *
     * @param pattern 模式字符串
     * @return 模式字符串分割后的字符串数组
     */
    protected String[] tokenizePattern(String pattern) {
        String[] patternTokens = this.patternTokenCache.get(pattern);
        if (patternTokens == null) {
            patternTokens = this.tokenizePath(pattern);
            if (this.patternTokenCache.size() > PATTERN_TOKEN_CACHE_SIZE) {
                this.patternTokenCache.clear();
            }
            this.patternTokenCache.put(pattern, patternTokens);
        }
        return patternTokens;
    }

    /**
     * 对路径字符串进行分割解析，返回分割后的字符串数据组。<br/>
     * 例如:
     * <ul>
     *     <li>将 <code>aa/bb/cc/dd</code> 切分为 <code>["aa", "bb", "cc", "dd"]</code></li>
     *     <li>将 <code>aa/bb/&#42;/dd</code> 切分为 <code>["aa", "bb", "*", "dd"]</code></li>
     *     <li>将 <code>aa/bb/&#42;/&#42;</code> 切分为 <code>["aa", "bb", "*", "*"]</code></li>
     *     <li>将 <code>aa/bb/c&#42;c/dd</code> 切分为 <code>["aa", "bb", "c*c", "dd"]</code></li>
     *     <li>将 <code>aa/&#42;&#42;/cc/dd</code> 切分为 <code>["aa", "**", "cc", "dd"]</code></li>
     *     <li>将 <code>aa/&#42;&#42;/cc/&#42;&#42;</code> 切分为 <code>["aa", "**", "cc", "**"]</code></li>
     * </ul>
     *
     * @param path 路径字符串
     * @return 路径字符串分割后的字符串数组
     */
    private String[] tokenizePath(String path) {
        String[] tokens = StringUtils.split(path, this.pathSeparator);
        List<String> tokenList = new ArrayList<>();
        for (String token : tokens) {
            if (token.length() > 0) {
                tokenList.add(token);
            }
        }
        return tokenList.toArray(new String[]{});
    }

    private boolean matchSegment(String patternToken, String pathToken) {
        // 如果模式字符串是 "**"，则表示任意目录，则直接返回true
        if (ANY_COUNT_DIR.equals(patternToken)) {
            return true;
        }
        // 如果模式字符串元素中包含 "*"，则表示任意个数字符，替换为".*"进行正则匹配
        if (patternToken.contains(ANY_COUNT_CHAR)) {
            patternToken = patternToken.replaceAll("\\*", ".*");
        }
        // 如果模式字符串元素中包含 "?"，则表示任意一个字符，替换为"."进行正则匹配
        if (patternToken.contains(ANY_SINGLE_CHAR)) {
            patternToken = patternToken.replaceAll("\\?", ".");
        }
        if (this.caseSensitive) {
            return pathToken.matches(patternToken) || pathToken.equalsIgnoreCase(patternToken);
        } else {
            return pathToken.matches(patternToken) || pathToken.equals(patternToken);
        }
    }

    /**
     * Ant 路径表达式匹配规则：<br/>
     * <ul>
     *     <li>对两个字符串根据特定的分隔符进行字符串分割切片</li>
     *     <li>以首次出现的 "**" 为分割点，优先比对左半部分的模式字符串元素，如果左边不匹配，则返回false</li>
     *     <li>以最后一次出现的 "**" 为分割点，再对比右半部分的模式字符串元素，如果右边不匹配，则返回false</li>
     *     <li>最后从左向右依次对比剩余部门的模式字符串（即首次出现和最后一次出现 "**" 之间的部分），将剩余的路径字符串元素与剩余的每一个模式字符串元素进行对比</li>
     *     <li>如果所有的对比完成后，模式字符串元素还存在剩余，则判断是否全部是 "**"</li>
     *     <li>比对非 "**" 的元素时，如果出现 "?"， 则会替换为 "."，然后进行正则匹配</li>
     * </ul>
     *
     * @param pattern 模式字符串
     * @param path    路径字符串
     * @return 如果匹配则返回true；否则返回false
     */
    protected boolean doMatch(String pattern, String path) {
        // 1. 如果模式和地址的开头符号不一致，则返回false
        if (path.startsWith(this.pathSeparator) != pattern.startsWith(this.pathSeparator)) {
            return false;
        }

        // 默认字符串 按照分隔符号 分隔后的字符串数组
        String[] patternTokens = this.tokenizePattern(pattern);
        // 路径字符串 按照分隔符号 分隔后的字符串数组
        String[] pathTokens = this.tokenizePattern(path);

        // 2. 对两个分割后的字符串数据，进行元素对比
        // 模式字符串开始、结束比对的位置
        int patternStartIdx = 0, patternEndIdx = patternTokens.length - 1;
        // 路径开始、结束比对的位置
        int pathStartIdx = 0, pathEndIdx = pathTokens.length - 1;

        // 2.1. 从左向右依次进行比较，在遇到 "**" 符号之前，依次比对同位置的元素是否匹配
        while (patternStartIdx <= patternEndIdx && pathStartIdx <= pathEndIdx) {
            String patternToken = patternTokens[patternStartIdx];
            // 如果遇到 "**" 则跳出，否则逐个字符串进行比对
            if (ANY_COUNT_DIR.equals(patternToken)) {
                break;
            }

            // 如果非 "**" 模式字符串元素和路径同位置的路径元素不匹配，则返回false，否则继续比对下一个元素
            if (!matchSegment(patternToken, pathTokens[pathStartIdx])) {
                return false;
            }

            patternStartIdx++;
            pathStartIdx++;
        }

        // 2.1.1. 如果从左向右比对完成后，路径字符串没有剩余可比元素
        if (pathStartIdx > pathEndIdx) {
            // 如果模式字符串没有剩余可比的元素，则比对末尾符号是否相等【当两个数组无剩余可对比的元素】
            // 例如：pattern = a/b/c/, path = a/b/c/
            if (patternStartIdx > patternEndIdx) {
                return pattern.endsWith(this.pathSeparator) == path.endsWith(this.pathSeparator);
            }
            // 如果模式字符串只剩最后一个可比较的元素时，并且最后一个元素为"*"时，则比对末尾符号是否相等【模式字符串默认为"*"时】
            // 例如：pattern = a/b/*, path = a/b/c/
            if (patternStartIdx == patternEndIdx) {
                return ANY_COUNT_CHAR.equals(patternTokens[patternStartIdx]) && path.endsWith(this.pathSeparator);
            }
            // 如果模式字符串还存在剩余可比的元素，则判断剩余的元素是否都是"**"，如果不是，则返回false
            // 例如：pattern = a/b/**, path = a/b/c/
            for (int i = patternStartIdx; i <= patternEndIdx; i++) {
                if (!ANY_COUNT_DIR.equals(patternTokens[i])) {
                    return false;
                }
            }
            return true;
        } else if (patternStartIdx > patternEndIdx) {
            // 2.1.2. 如果从左向右比对完成后，模式字符串没有剩余的可比元素；则返回false；理论上不可能存在这个场景
            return false;
        }
        //else if (ANY_COUNT_DIR.equals(patternTokens[patternStartIdx])) {
        // 2.1.3. 如果从左向右比对完成后，模式字符串的最后一个元素为 "**"，则直接返回true
        // 例如：pattern = a/b/**, path = a/b/c/
        //    return true;
        //}

        // 2.2. 优先从左向右比对完成后，再从右向左依次进行比较，在遇到 "**" 符号之前或者前一次比对最后位置时退出，依次比对同位置的元素是否匹配
        while (patternStartIdx <= patternEndIdx && pathStartIdx <= pathEndIdx) {
            String patternToken = patternTokens[patternEndIdx];
            // 如果遇到 "**" 则跳出，否则逐个字符串进行比对
            if (ANY_COUNT_DIR.equals(patternToken)) {
                break;
            }

            // 如果非 "**" 模式字符串元素和路径同位置的路径元素不匹配，则返回false，否则继续比对上一个元素
            if (!matchSegment(patternToken, pathTokens[pathStartIdx])) {
                return false;
            }

            patternEndIdx--;
            pathEndIdx--;
        }

        if (pathStartIdx > pathEndIdx) {
            // 2.2.1. 如果从左向右比对后，再从右向左比对后，路径字符串没有剩余可比元素
            // 值判断多出来的模式字符串元素是否都是 "**"
            for (int i = patternStartIdx; i <= patternEndIdx; i++) {
                if (!ANY_COUNT_DIR.equals(patternTokens[i])) {
                    return false;
                }
            }
            return true;
        } else {
            // 2.2.2. 如果从左向右比对后，再从右向左比对后，路径字符串还存在可比元素
            // 如果模式字符串和前一次和后一次的截止位置不一样，则需要判断剩余的模式字符串是否是 "**"
            while (patternStartIdx != patternEndIdx) {
                // 剩余的模式字符串中下一次出现 "**" 的位置
                int nextIdxOfAnyCountDirSymbol = -1;
                for (int i = patternStartIdx + 1; i <= patternEndIdx; i++) {
                    if (ANY_COUNT_DIR.equals(patternTokens[i])) {
                        nextIdxOfAnyCountDirSymbol = i;
                        break;
                    }
                }

                if (nextIdxOfAnyCountDirSymbol == patternStartIdx + 1) {
                    // 如果下一次出现 "**" 的位置，刚好是待比较模式字符串中的第一个元素，则忽略该元素
                    patternStartIdx++;
                    continue;
                }
                // 否则，从这段模式字符串（待匹配的第二个元素和"**"出现的文字）的开始位置和结束位置进行逐个路径字符串（剩余待对比的）的元素匹配
                int patternLength = (nextIdxOfAnyCountDirSymbol - patternStartIdx - 1);
                int pathLength = (pathEndIdx - pathStartIdx + 1);
                int foundIdx = -1;

                for (int i = 0; i <= pathLength - patternLength; i++) {
                    for (int j = 0; j < patternLength; j++) {
                        String patternToken = patternTokens[patternStartIdx + j + 1];
                        String pathToken = pathTokens[pathStartIdx + i + j];
                        if (!matchSegment(patternToken, pathToken)) {
                            break;
                        }
                    }
                    foundIdx = pathStartIdx + i;
                    break;
                }

                if (foundIdx == -1) {
                    return false;
                }

                patternStartIdx = nextIdxOfAnyCountDirSymbol;
                pathStartIdx = foundIdx + patternLength;

            }
        }

        // 3. 路径字符串比对完成后，如果还存在待对比的模式字符串，则判断是否都是 "**"
        for (int i = patternStartIdx; i <= patternEndIdx; i++) {
            if (!ANY_COUNT_DIR.equals(patternTokens[i])) {
                return false;
            }
        }

        return true;
    }

}
