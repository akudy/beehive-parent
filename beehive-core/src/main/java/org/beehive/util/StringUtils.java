/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.StringUtils
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-08-19
 * Comments: Java源文件
 */

package org.beehive.util;

import org.beehive.core.algorithm.IndexRangeAlgorithm;
import org.beehive.core.algorithm.IndexSliceAlgorithm;
import org.beehive.core.converter.ConvertException;
import org.beehive.core.string.RandomStringFactory;
import org.beehive.core.string.Slf4jStringFormatter;
import org.beehive.core.string.StringDictComparator;
import org.beehive.core.string.StringFormatter;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.*;

/**
 * 字符串工具类，定义参见的字符串处理等操作。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.util</code></li>
 * <li>Class Name: <code>StringUtils</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1" cellspacing="0" cellpadding="0" summary=""Upgrade&shy;Modify History>
 * <tr>
 * <th>Version</th>
 * <th>Environment</th>
 * <th>ModifyTime</th>
 * <th>Modifier</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td align="center"><em>1.0</em></td>
 * <td align="center"><em>Java 8</em></td>
 * <td align="center"><em>2020/8/18</em></td>
 * <td align="center"><em>akudy</em></td>
 * <td><em>Define</em></td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @since 1.0
 */
public class StringUtils {

    private static final String DEFAULT_CHARSET = System.getProperty("file.encoding");

    /****************************** format start **********************************/

    /**
     * 自定义字符串格式化器
     */
    private static final StringFormatter slf4jStringFormatter = new Slf4jStringFormatter();

    /**
     * 模板样式枚举定义
     */
    public enum TemplateStyle {

        /**
         * Java 字符串模板样式。示例：<br/>
         * <code>
         * String.format("userId = %d, userName = %s", 1, "张三");
         * </code>
         *
         * @see String#format(String, Object...)
         * @see Formatter
         */
        JAVA_STRING,

        /**
         * Java 消息模板样式。示例：<br/>
         * <code>
         * MessageFormat.format("userId = {0}, userName = {1}", 1, "张三");
         * </code>
         *
         * @see MessageFormat#format(String, Object...)
         */
        JAVA_MESSAGE,

        /**
         * slf4j 消息模板样式。示例：<br/>
         * <code>
         * MessageFormatter.arrayFormat("userId = {}, userName = {}", new Object[]{1, "张三"}).getMessage();
         * </code>
         *
         * @see Slf4jStringFormatter#format(String, Object...)
         */
        SLF4J_LOG;

    }

    /**
     * 默认模板样式类型
     */
    private static TemplateStyle defaultTemplateStyle = TemplateStyle.SLF4J_LOG;

    /**
     * 设置字符串模板类型
     *
     * @param templateStyle 字符串模板类型
     * @since 1.0
     */
    public void setDefaultTemplateStyle(TemplateStyle templateStyle) {
        StringUtils.defaultTemplateStyle = templateStyle;
    }

    /**
     * 字符串格式输出方法。首先定义字符串模板，然后通过指定内容填充
     *
     * @param templateStyle 模板样式
     * @param templateStr   模板字符串
     * @param args          填充内容列表
     * @return 模板填充后的字符串
     * @since 1.0
     */
    public static String format(TemplateStyle templateStyle, String templateStr, Object... args) {
        String message = templateStr;
        if (TemplateStyle.SLF4J_LOG == templateStyle) {
            message = slf4jStringFormatter.format(templateStr, args);
        } else if (TemplateStyle.JAVA_STRING == templateStyle) {
            message = String.format(templateStr, args);
        } else if (TemplateStyle.JAVA_MESSAGE == templateStyle) {
            message = MessageFormat.format(templateStr, args);
        }
        return message;
    }

    /**
     * 字符串格式输出方法，使用默认模板进行字符串格式化。
     *
     * @param templateStr 模板字符串
     * @param args        填充内容列表
     * @return 模板填充后的字符串
     * @since 1.0
     */
    public static String format(String templateStr, Object... args) {
        return format(defaultTemplateStyle, templateStr, args);
    }

    /**
     * 使用Java字符串模板进行输出。示例：<br/>
     * <code>
     * userId = %d, userName = %s
     * </code>
     *
     * @param templateStr 字符串模板
     * @param args        填充内容列表
     * @return 模板填充后的字符串
     * @see Formatter
     * @see String#format(String, Object...)
     * @since 1.0
     */
    public static String strFormat(String templateStr, Object... args) {
        return format(TemplateStyle.JAVA_STRING, templateStr, args);
    }

    /**
     * 使用Java消息模板进行输出。示例：<br/>
     * <code>
     * userId = {0}, userName = {1}
     * </code>
     *
     * @param templateStr 字符串模板
     * @param args        填充内容列表
     * @return 填充后的字符串
     * @see MessageFormat#format(String, Object...)
     * @since 1.0
     */
    public static String msgFormat(String templateStr, Object... args) {
        return format(TemplateStyle.JAVA_MESSAGE, templateStr, args);
    }

    /**
     * 使用slf4j日志模板进行输出。示例：<br/>
     * <code>
     * userId = {}, userName = {}
     * </code>
     *
     * @param templateStr 字符串模板
     * @param args        填充内容列表
     * @return 填充后的字符串
     * @see Slf4jStringFormatter#format(String, Object...)
     * @since 1.0
     */
    public static String logFormat(String templateStr, Object... args) {
        return format(TemplateStyle.SLF4J_LOG, templateStr, args);
    }

    /**
     * 向字符串左边追加一个或多个字符
     *
     * @param str         原字符串
     * @param appendChar  追加的字符
     * @param repeatCount 追加的字符重复次数
     * @return 按规则追加字符后的字符串
     */
    public static String appendToLeft(String str, char appendChar, int repeatCount) {
        return repeatStr(String.valueOf(appendChar), repeatCount) + str;
    }

    /**
     * 向字符串右边追加一个或多个字符
     *
     * @param str         原字符串
     * @param appendChar  追加的字符
     * @param repeatCount 追加的字符重复次数
     * @return 按规则追加字符后的字符串
     */
    public static String appendToRight(String str, char appendChar, int repeatCount) {
        return str + repeatStr(String.valueOf(appendChar), repeatCount);
    }

    private static String repeatStr(String str, int repeatCount) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repeatCount; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 向字符串左边追加一个或多个字符串
     *
     * @param str         原字符串
     * @param appendStr   追加的字符串
     * @param repeatCount 追加的字符串重复次数
     * @return 按规则追加字符串后的字符串
     */
    public static String appendToLeft(String str, String appendStr, int repeatCount) {
        return repeatStr(appendStr, repeatCount) + str;
    }

    /**
     * 向字符串右边追加一个或多个字符串
     *
     * @param str         原字符串
     * @param appendStr   追加的字符串
     * @param repeatCount 追加的字符串重复次数
     * @return 按规则追加字符串后的字符串
     */
    public static String appendToRight(String str, String appendStr, int repeatCount) {
        return str + repeatStr(appendStr, repeatCount);
    }

    /**
     * 向字符串左边追加一个字符串
     *
     * @param str       原字符串
     * @param appendStr 追加的字符串
     * @return 按规则追加字符串后的字符串
     */
    public static String appendToLeft(String str, String appendStr) {
        return appendStr + str;
    }

    /**
     * 向字符串右边追加一个字符串
     *
     * @param str       原字符串
     * @param appendStr 追加的字符串
     * @return 按规则追加字符串后的字符串
     */
    public static String appendToRight(String str, String appendStr) {
        return str + appendStr;
    }

    /****************************** format end **********************************/

    /****************************** null & blank start **********************************/
    /**
     * 判断字符串对象是否为空
     *
     * @param str 输入字符串
     * @return 如果为空，则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isNull(String str) {
        return str == null;
    }

    /**
     * 判断字符串对象是否不为空
     *
     * @param str 输入字符串
     * @return 如果不为空，则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isNotNull(String str) {
        return str != null;
    }

    /**
     * 判断字符串为空白字符串
     *
     * @param str 输入字符串
     * @return 如果为空白字符串则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串为非空白字符串
     *
     * @param str 输入字符串
     * @return 如果为非空白字符串返回true，否则返回false
     * @since 1.0
     */
    public static boolean isNotBlank(String str) {
        return str != null && str.trim().length() > 0;
    }

    /****************************** null & blank end **********************************/

    /****************************** equals & compare start **********************************/

    /**
     * 比较源字符串与目标字符串，当全部相等时返回true，否则返回false。<br/>
     * 同： <code>source.equals(target[0]) && source.equals(target[1]) && ...</code>
     *
     * @param source 源字符串
     * @param target 目标字符串
     * @return 全部相等时返回true，否则（有一个不相等）返回false
     */
    public static boolean equalsAll(String source, String... target) {
        if (source == null) {
            return false;
        }
        if (target == null || target.length == 0) {
            return false;
        }
        for (String other : target) {
            if (!source.equals(other)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较源字符串与目标字符串，当存在任一个相等时返回true，否则返回false。<br/>
     * 同： <code>source.equals(target[0]) || source.equals(target[1]) || ...</code>
     *
     * @param source 源字符串
     * @param target 目标字符串
     * @return 存在任一个相等时返回true，否则（全部不相等）返回false
     * @since 1.0
     */
    public static boolean equalsAny(String source, String... target) {
        if (source == null) {
            return false;
        }
        if (target == null || target.length == 0) {
            return false;
        }
        for (String other : target) {
            if (source.equals(other)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比较源字符串与目标字符串（不区分大小写），当全部相等时返回true，否则返回false。<br/>
     * 同： <code>source.equalsIgnoreCase(target[0]) && source.equalsIgnoreCase(target[1]) && ...</code>
     *
     * @param source 源字符串
     * @param target 目标字符串
     * @return 全部相等时返回true，否则（有一个不相等）返回false
     */
    public static boolean equalsAllIgnoreCase(String source, String... target) {
        if (source == null) {
            return false;
        }
        if (target == null || target.length == 0) {
            return false;
        }
        for (String other : target) {
            if (!source.equalsIgnoreCase(other)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较源字符串与目标字符串（不区分大小写），当存在任一个相等时返回true，否则返回false。<br/>
     * 同： <code>source.equalsIgnoreCase(target[0]) || source.equalsIgnoreCase(target[1]) || ...</code>
     *
     * @param source 源字符串
     * @param target 目标字符串
     * @return 存在任一个相等时返回true，否则（全部不相等）返回false
     * @since 1.0
     */
    public static boolean equalsAnyIgnoreCase(String source, String... target) {
        if (source == null) {
            return false;
        }
        if (target == null || target.length == 0) {
            return false;
        }
        for (String other : target) {
            if (source.equalsIgnoreCase(other)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断源字符串中是否包含任意指定的字符，如果包含任意一个字符则返回true，否则返回false。<br/>
     * 同：<code>source.contains(chars[0]) || source.contains(chars[1]) || ...</code>
     *
     * @param source 源字符串
     * @param chars  指定的字符
     * @return 如果存在任意一个指定字符，则返回true；否则返回false
     * @since 1.0
     */
    public static boolean containsAny(String source, char... chars) {
        if (source == null) {
            return false;
        }
        if (chars == null || chars.length == 0) {
            return false;
        }
        for (char ch : chars) {
            if (source.indexOf(ch) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断源字符串中是否包含任意指定的字符（忽略大小写），如果包含任意一个字符则返回true，否则返回false。<br/>
     * 同：<code>source.toLowerCase().contains(Character.toUpperCase(chars[0])) || source.toLowerCase().contains(Character.toUpperCase(chars[1])) || ...</code>
     *
     * @param source 源字符串
     * @param chars  指定的字符
     * @return 如果存在任意一个指定字符，则返回true；否则返回false
     * @since 1.0
     */
    public static boolean containsAnyIgnoreCase(String source, char... chars) {
        if (source == null) {
            return false;
        }
        if (chars == null || chars.length == 0) {
            return false;
        }
        String newSource = source.toLowerCase();
        char[] newChars = new char[chars.length];
        for (int i = 0; i < newChars.length; i++) {
            newChars[i] = Character.toLowerCase(chars[i]);
        }
        return containsAny(newSource, newChars);
    }

    /**
     * 判断源字符串中是否包含任意指定的子字符串，如果包含任意一个子字符串则返回true，否则返回false。<br/>
     * 同：<code>source.contains(target[0]) || source.contains(target[1]) || ...</code>
     *
     * @param source 源字符串
     * @param target 指定的子字符串
     * @return 如果存在任意一个指定子字符串，则返回true；否则返回false
     * @since 1.0
     */
    public static boolean containsAny(String source, String... target) {
        if (source == null) {
            return false;
        }
        if (target == null || target.length == 0) {
            return false;
        }
        for (String str : target) {
            if (source.contains(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断源字符串中是否包含任意指定的子字符串（忽略大小写），如果包含任意一个子字符串则返回true，否则返回false。<br/>
     * 同：<code>source.contains(target[0]) || source.contains(target[1]) || ...</code>
     *
     * @param source 源字符串
     * @param target 指定的子字符串
     * @return 如果存在任意一个指定子字符串，则返回true；否则返回false
     * @since 1.0
     */
    public static boolean containsAnyIgnoreCase(String source, String... target) {
        if (source == null) {
            return false;
        }
        if (target == null || target.length == 0) {
            return false;
        }
        String newSource = source.toLowerCase();
        String[] newTarget = new String[target.length];
        for (int i = 0; i < newTarget.length; i++) {
            newTarget[i] = target[i].toLowerCase();
        }
        return containsAny(newSource, newTarget);
    }

    private static final Comparator<String> dictComparator = new StringDictComparator();

    /**
     * 按照字典顺序进行字符串大小比较
     *
     * @param str1 被比较字符串文本
     * @param str1 比较字符串文本
     * @return 如果被比较字符串大于比较字符串则返回大于0；如果被比较字符串小于比较字符串则返回小于0；否则返回0
     * @see StringDictComparator
     * @since 1.0
     */
    public static int compareWithDict(String str1, String str2) {
        return compare(str1, str2, dictComparator);
    }

    /**
     * 比较两个字符串的大小
     *
     * @param str1       被比较字符串文本
     * @param str1       比较字符串文本
     * @param comparator 比较器
     * @return 如果被比较字符串大于比较字符串则返回大于0；如果被比较字符串小于比较字符串则返回小于0；否则返回0
     * @since 1.0
     */
    public static int compare(String str1, String str2, Comparator<String> comparator) {
        return comparator.compare(str1, str2);
    }

    /****************************** equals & compare end **********************************/

    /****************************** sub & pick start **********************************/

    /**
     * 针对一个字符串进行截取，并返回一个新的子字符串。<br/>
     * eg. 字符串"hello word!"的分析如下：
     * <code>
     *     <table border="1">
     *         <tr style="text-align:center">
     *             <td>h</td><td>e</td><td>l</td><td>l</td><td>o</td><td>&nbsp;</td><td>w</td><td>o</td><td>r</td><td>l</td><td>d</td><td>!</td>
     *         </tr>
     *         <tr style="text-align:center">
     *             <td>0</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td>
     *         </tr>
     *         <tr style="text-align:center">
     *             <td>-12</td><td>-11</td><td>-10</td><td>-9</td><td>-8</td><td>-7</td><td>-6</td><td>-5</td><td>-4</td><td>-3</td><td>-2</td><td>-1</td>
     *         </tr>
     *     </table>
     *     <ul>
     *         <li>subStr("hello world!", -3) => ld!</li>
     *         <li>subStr("hello world!", 3) => hel</li>
     *     </ul>
     * </code>
     *
     * @param str  原字符串
     * @param from 截取的开始位置和元素个数（正数表示正向计算并截取，负数表示逆向计算并截取)
     * @return 子字符串
     * @see IndexRangeAlgorithm
     * @since 1.0
     */
    public static String subStr(String str, int from) {
        if (str == null) {
            return null;
        }
        int[] range = IndexRangeAlgorithm.indexRange(str.length(), from);
        return str.substring(range[0], range[1]);
    }

    /**
     * 针对一个字符串进行截取，并返回一个新的子字符串。<br/>
     * eg. 字符串"hello word!"的分析如下：
     * <code>
     *     <table border="1">
     *         <tr style="text-align:center">
     *             <td>h</td><td>e</td><td>l</td><td>l</td><td>o</td><td>&nbsp;</td><td>w</td><td>o</td><td>r</td><td>l</td><td>d</td><td>!</td>
     *         </tr>
     *         <tr style="text-align:center">
     *             <td>0</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td>
     *         </tr>
     *         <tr style="text-align:center">
     *             <td>-12</td><td>-11</td><td>-10</td><td>-9</td><td>-8</td><td>-7</td><td>-6</td><td>-5</td><td>-4</td><td>-3</td><td>-2</td><td>-1</td>
     *         </tr>
     *     </table>
     *     <ul>
     *         <li>subStr("hello world!", -3, -1) => r</li>
     *         <li>subStr("hello world!", -3, 1) => l</li>
     *         <li>subStr("hello world!", 3, 7) => lo worl</li>
     *     </ul>
     * </code>
     *
     * @param str    原字符串
     * @param from   截取的开始位置（正数表示正向计算，负数表示逆向计算)
     * @param length 元素个数（正数表示正向计算，负数表示逆向计算，绝对值表示字符的个数）
     * @return 子字符串
     * @see IndexRangeAlgorithm
     * @since 1.0
     */
    public static String subStr(String str, int from, int length) {
        if (str == null) {
            return null;
        }
        int[] range = IndexRangeAlgorithm.indexRange(str.length(), from, length);
        return str.substring(range[0], range[1]);
    }

    /**
     * 针对一个字符串进行切片截取，并返回一个新的子字符串。<br/>
     * eg. 字符串"hello word!"的分析如下：
     * <code>
     *     <table border="1">
     *         <tr style="text-align:center">
     *             <td>h</td><td>e</td><td>l</td><td>l</td><td>o</td><td>&nbsp;</td><td>w</td><td>o</td><td>r</td><td>l</td><td>d</td><td>!</td>
     *         </tr>
     *         <tr style="text-align:center">
     *             <td>0</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td>
     *         </tr>
     *         <tr style="text-align:center">
     *             <td>-12</td><td>-11</td><td>-10</td><td>-9</td><td>-8</td><td>-7</td><td>-6</td><td>-5</td><td>-4</td><td>-3</td><td>-2</td><td>-1</td>
     *         </tr>
     *     </table>
     *     <ul>
     *         <li>slice("hello world!", -3) => ld!</li>
     *         <li>slice("hello world!", -3, 1) => </li>
     *     </ul>
     * </code>
     *
     * @param str   原字符串
     * @param start 截取的开始位置（正数表示正向计算，负数表示逆向计算）
     * @return 子字符串
     * @see IndexSliceAlgorithm
     * @since 1.0
     */
    public static String slice(String str, int start) {
        if (str == null) {
            return null;
        }
        int[] range = IndexSliceAlgorithm.indexSlice(str.length(), start);
        return str.substring(range[0], range[1]);
    }

    /**
     * 针对一个字符串切片截取，并返回一个新的子字符串。<br/>
     * eg. 字符串"hello word!"的分析如下：
     * <code>
     *     <table border="1">
     *         <tr style="text-align:center">
     *             <td>h</td><td>e</td><td>l</td><td>l</td><td>o</td><td>&nbsp;</td><td>w</td><td>o</td><td>r</td><td>l</td><td>d</td><td>!</td>
     *         </tr>
     *         <tr style="text-align:center">
     *             <td>0</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td>
     *         </tr>
     *         <tr style="text-align:center">
     *             <td>-12</td><td>-11</td><td>-10</td><td>-9</td><td>-8</td><td>-7</td><td>-6</td><td>-5</td><td>-4</td><td>-3</td><td>-2</td><td>-1</td>
     *         </tr>
     *     </table>
     *     <ul>
     *         <li>slice("hello world!", -3, -1) => ld</li>
     *         <li>slice("hello world!", 3) => lo world!</li>
     *         <li>slice("hello world!", 3, 7) => lo w</li>
     *     </ul>
     * </code>
     *
     * @param str   原字符串
     * @param start 截取的开始位置（正数表示正向计算，负数表示逆向计算）；如果开始的位置超过字符串长度，则以字符串长度为基准
     * @param end   截取的结束位置（正数表示正向计算，负数表示逆向计算）；如果结束的位置超过字符串长度，则以字符串长度为基准
     * @return 子字符串
     * @see IndexSliceAlgorithm
     * @since 1.0
     */
    public static String slice(String str, int start, int end) {
        if (str == null) {
            return null;
        }
        int[] range = IndexSliceAlgorithm.indexSlice(str.length(), start, end);
        return str.substring(range[0], range[1]);
    }

    /**
     * 针对一个字符串进行截取，并返回一个新的子字符串；将给定的索引进行重新排序并去重，然后提取指定位置的元素组成新列表
     *
     * @param str   原字符串
     * @param index 索引位置列表，超出范围或负值被剔除
     * @return 子字符串
     * @see IndexRangeAlgorithm
     * @since 1.0
     */
    public static String pickStr(String str, int... index) {
        if (str == null) {
            return null;
        }
        if (ArrayUtils.isEmpty(index)) {
            return str;
        }
        StringBuilder strBuf = new StringBuilder();
        int[] range = IndexRangeAlgorithm.indexRange(str.length(), index);
        for (int i : range) {
            strBuf.append(str.charAt(i));
        }
        return strBuf.toString();
    }

    /**
     * 针对一个字符串进行字符提取，并组成一个新的子字符串
     *
     * @param str   原字符串
     * @param index 提取的索引位置，超过范围或负整数会被忽略
     * @return 子集合列表
     * @since 1.0
     */
    public static String ofStr(String str, int... index) {
        if (str == null) {
            return null;
        }
        StringBuilder strBuf = new StringBuilder();
        if (ArrayUtils.isEmpty(index)) {
            return str;
        }
        for (int i : index) {
            if (i >= 0 && i < str.length()) {
                strBuf.append(str.charAt(i));
            }
        }
        return strBuf.toString();
    }

    /**
     * 将一个字符串按照指定分隔符进行分割，并返回分割后的字符串数组。
     *
     * @param str       字符串
     * @param delimiter 分割字符
     * @return 按照分割符分割后的字符串数组
     * @see #split(String, String)
     * @since 1.0
     */
    public static String[] split(String str, char... delimiter) {
        return split(str, new String(delimiter));
    }

    /**
     * 将一个字符串按照指定的分割字符进行分割，并非返回分割后的字符串数组。
     *
     * @param str        字符串
     * @param delimiters 分割字符组成的字符串
     * @return 按照分割符分割后的字符串数组
     * @see StringTokenizer
     * @since 1.0
     */
    public static String[] split(String str, String delimiters) {
        if (str == null) {
            return new String[0];
        }
        StringTokenizer tokenizer = new StringTokenizer(str, delimiters);
        List<String> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            tokens.add(token);
        }
        return tokens.toArray(new String[]{});
    }

    /****************************** sub & pick end **********************************/

    /****************************** create start **********************************/

    /**
     * 在一个字符串序列中随机产生一个指定字符个数的新字符串
     *
     * @param strSequence 字符串序列
     * @param count       字符个数
     * @return 新的随机字符串
     * @since 1.0
     */
    public static String random(String strSequence, int count) {
        RandomStringFactory factory = RandomStringFactory.newFactory(strSequence);
        return factory.random(count);
    }

    /**
     * 产生一个随机数字字符串
     *
     * @param count 数字个数
     * @return 随机的数值字符串
     * @since 1.0
     */
    public static String randomDigit(int count) {
        return RandomStringFactory.newDigitFactory().random(count);
    }

    /**
     * 产生一个随机字母字符串
     *
     * @param count 字母个数
     * @return 随机的字母字符串
     * @since 1.0
     */
    public static String randomEnLetter(int count) {
        return RandomStringFactory.newAlphabetFactory().random(count);
    }

    /**
     * 产生一个随机字母和数字组成的字符串
     *
     * @param count 字符个数
     * @return 随机的字母和数字字符串
     * @since 1.0
     */
    public static String randomDigitAndEnLetter(int count) {
        return RandomStringFactory.newAlphanumericFactory().random(count);
    }

    /**
     * 产生一个本地语言环境下的随机字符串
     *
     * @param count 字符个数
     * @return 本地语言字符构成的字符串
     * @since 1.0
     */
    public static String randomLocalChar(int count) {
        return RandomStringFactory.newLocalCharacterFactory().random(count);
    }

    /****************************** create end **********************************/

    /****************************** convert start **********************************/
    /**
     * 将字符串转换为大写格式，将指定位置的字符串转换为大写字符串
     *
     * @param str        字符串文本
     * @param startIndex 开始位置，索引从0开始
     * @param endIndex   结束位置，索引从0开始
     * @return 转换后的字符串
     * @since 1.0
     */
    public static String upperCase(String str, int startIndex, int endIndex) {
        if (str == null) {
            return null;
        }
        if (startIndex < 0 || endIndex < 0) {
            return str;
        }
        StringBuffer sb = new StringBuffer(str);
        for (int i = startIndex; i <= endIndex; i++) {
            sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * 将字符串转换为大写格式
     *
     * @param str 字符串文本
     * @return 转换后的字符串
     * @since 1.0
     */
    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    /**
     * 将字符串的首个字符转换为大写格式
     *
     * @param str 字符串文本
     * @return 转换后的字符串
     * @since 1.0
     */
    public static String upperCaseFirst(String str) {
        return upperCase(str, 0, 0);
    }

    /**
     * 将字符串转换为小写格式，将指定位置的字符串转换为小写字符串
     *
     * @param str        字符串文本
     * @param startIndex 开始位置，索引从0开始
     * @param endIndex   结束位置，索引从0始
     * @return 转换后的字符串
     * @since 1.0
     */
    public static String lowerCase(String str, int startIndex, int endIndex) {
        if (str == null) {
            return null;
        }
        if (startIndex < 0 || endIndex < 0) {
            return str;
        }
        StringBuffer sb = new StringBuffer(str);
        for (int i = startIndex; i <= endIndex; i++) {
            sb.setCharAt(i, Character.toLowerCase(sb.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * 将字符串转换为小写格式
     *
     * @param str 字符串文本
     * @return 转换后的字符串
     * @since 1.0
     */
    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    /**
     * 将字符串的首个字符转换为小写格式
     *
     * @param str 字符串文本
     * @return 转换后的字符串
     * @since 1.0
     */
    public static String lowerCaseFirst(String str) {
        return lowerCase(str, 0, 0);
    }

    /**
     * 将默认编码格式的字符串按照指定的编码格式进行编码，并返回编码后的字符串
     *
     * @param str         字符文本
     * @param charsetName 原字符串的编码字符集名称
     * @return 编码后的字符串
     * @since 1.0
     */
    public static String encode(String str, String charsetName) {
        return transcode(str, DEFAULT_CHARSET, charsetName);
    }

    /**
     * 将指定编码格式的字符串按照默认编码格式进行解码，并返回解码后的字符串
     *
     * @param str         字符文本
     * @param charsetName 原字符串的编码字符集名称
     * @return 编码后的字符串
     * @since 1.0
     */
    public static String decode(String str, String charsetName) {
        return transcode(str, charsetName, DEFAULT_CHARSET);
    }

    /**
     * 将字符串文本从指定的编码字符集转换为解码字符集，并返回解码后的字符串
     *
     * @param str               字符文本
     * @param encodeCharsetName 编码字符集名称
     * @param decodeCharsetName 解码字符集名称
     * @return 转码后的字符串
     * @since 1.0
     */
    public static String transcode(String str, String encodeCharsetName, String decodeCharsetName) throws ConvertException {
        try {
            if (str == null) {
                return null;
            }
            return new String(str.getBytes(encodeCharsetName), decodeCharsetName);
        } catch (UnsupportedEncodingException e) {
            throw new ConvertException("transcode is error.", e);
        }
    }

    /****************************** convert end **********************************/

    /****************************** type start **********************************/

    /**
     * 判断给定的字符串是否是数值类型。即串中包含0-9的字符，同时仅包含一个"."符号。
     *
     * @param str 被检查的字符串
     * @return 如果字符串为空，则返回false；如果字符串包含除过0-9和"."以外的符号，则返回false，如果字符串存在两个以上"."符号则返回false。否则返回true。
     */
    public static boolean isNumber(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        int dotCount = 0;
        for (int i = 0, length = str.trim().length(); i < length; i++) {
            int code = (int) str.charAt(i);
            // Unicode中的48~57表示0~9
            if (!(code >= 48 && code <= 57)) {
                // Unicode中的46表示"."
                if (code == 46) {
                    dotCount++;
                } else {
                    return false;
                }
            }
            if (dotCount > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断给定的字符串是否是正数类型。即串中仅包含0-9的字符
     *
     * @param str 被检查的字符串
     * @return 如果字符串为空，则返回false；如果字符串包含除过0-9以外的符号，则返回false
     */
    public static boolean isIntNumber(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        for (int i = 0, length = str.trim().length(); i < length; i++) {
            int code = (int) str.charAt(i);
            // Unicode中的48~57表示0~9
            if (!(code >= 48 && code <= 57)) {
                // Unicode中的46表示"."
                if (code == 46) {
                    return false;
                }
                return false;
            }
        }
        return true;
    }

    /****************************** type end **********************************/

    /****************************** find start **********************************/

    /**
     * 在字符串中查找指定字符出现的全部索引位置，索引从0开始
     *
     * @param str 字符串
     * @param ch  指定查找的字符
     * @return 字符出现位置的索引数组
     * @since 1.0
     */
    public static int[] indexOf(String str, char ch) {
        List<Integer> list = new ArrayList<>();
        int nextStartIndex = 0;
        int end = str.length();
        while (nextStartIndex <= end) {
            int index = str.indexOf(ch, nextStartIndex);
            if (index > 0) {
                list.add(index);
                nextStartIndex = index + 1;
            } else {
                break;
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * 在字符串中查找指定字符串出现的全部索引位置，索引从0开始
     *
     * @param str    字符串
     * @param subStr 指定查找的字符串
     * @return 子字符串出现位置的索引数组
     * @since 1.0
     */
    public static int[] indexOf(String str, String subStr) {
        List<Integer> list = new ArrayList<>();
        int nextStartIndex = 0;
        int stepLength = subStr.length();
        int end = str.length();
        while (nextStartIndex <= end) {
            int index = str.indexOf(subStr, nextStartIndex);
            if (index > 0) {
                list.add(index);
                nextStartIndex = index + stepLength;
            } else {
                break;
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    /****************************** find end **********************************/
}
