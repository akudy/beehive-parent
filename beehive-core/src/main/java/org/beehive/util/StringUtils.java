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
import org.beehive.core.convert.ConvertException;
import org.beehive.core.string.RandomStringFactory;
import org.beehive.core.string.Slf4jStringFormatter;
import org.beehive.core.string.StringDictComparator;
import org.beehive.core.string.StringFormatter;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Formatter;

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
     * 使用slf4j日志模板进行输出。示例：<br/>
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
     * 使用Java字符串模板进行输出。示例：<br/>
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
     * 使用Java消息模板进行输出。示例：<br/>
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
        return str != null && str.trim().length() == 0;
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
     * 同： <code>source.equals(target[0]) && source.equals(target[1]) && ...</code>
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
     * 同： <code>source.equals(target[0]) || source.equals(target[1]) || ...</code>
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
     * 按照字典顺序进行字符串大小比较
     *
     * @param str1 被比较字符串文本
     * @param str1 比较字符串文本
     * @return 如果被比较字符串大于比较字符串则返回大于0；如果被比较字符串小于比较字符串则返回小于0；否则返回0
     * @see StringDictComparator
     * @since 1.0
     */
    public static int compareWithDict(String str1, String str2) {
        return compare(str1, str2, new StringDictComparator());
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
     * 针对一个字符串进行截取，并返回一个新的子字符串
     *
     * @param str   原字符串
     * @param index 截取的开始位置和元素个数（正数表示正向计算并截取，负数表示逆向计算并截取)
     * @return 子字符串
     * @see IndexRangeAlgorithm
     * @since 1.0
     */
    public static String subStr(String str, int index) {
        if (str == null) {
            return null;
        }
        int[] range = IndexRangeAlgorithm.indexRange(str.length(), index);
        return str.substring(range[0], range[1]);
    }

    /**
     * 针对一个字符串进行截取，并返回一个新的子字符串
     *
     * @param str   原字符串
     * @param index 截取的开始位置和元素个数（正数表示正向计算，负数表示逆向计算)
     * @param count 截取的方向和元素个数（整数表示正向截取，负数表示逆向截取）
     * @return 子字符串
     * @see IndexRangeAlgorithm
     * @since 1.0
     */
    public static String subStr(String str, int index, int count) {
        if (str == null) {
            return null;
        }
        int[] range = IndexRangeAlgorithm.indexRange(str.length(), index, count);
        return str.substring(range[0], range[1]);
    }

    /**
     * 针对一个字符串进行截取，并返回一个新的子字符串；将所有索引进行重新排序并去重，然后提取指定位置的元素组成新列表
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
     * @return 随机的数组字符串
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
    public static String randomLetter(int count) {
        return RandomStringFactory.newAlphabetFactory().random(count);
    }

    /**
     * 产生一个随机字母和数字组成的字符串
     *
     * @param count 字符个数
     * @return 随机的字母和数字字符串
     * @since 1.0
     */
    public static String randomDigitAndLetter(int count) {
        return RandomStringFactory.newAlphanumericFactory().random(count);
    }

    /****************************** create end **********************************/

    /****************************** convert start **********************************/
    /**
     * 将字符串转换为大写格式，将指定未知的字符串转换为大写字符串
     *
     * @param str        字符串文本
     * @param startIndex 开始位置，索引从0开始
     * @param endIndex   结束位置，索引从0开始
     * @return 转换后的字符串
     * @since 1.0
     */
    public static String upperCase(String str, int startIndex, int endIndex) {
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
     * 将字符串转换为小写格式，将指定未知的字符串转换为小写字符串
     *
     * @param str        字符串文本
     * @param startIndex 开始位置，索引从0开始
     * @param endIndex   结束位置，索引从0始
     * @return 转换后的字符串
     * @since 1.0
     */
    public static String lowerCase(String str, int startIndex, int endIndex) {
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
     * 将字符串使用指定的字符集进行编码，并返回编码后的字符串
     *
     * @param str               字符文本
     * @param encodeCharsetName 编码字符集名称
     * @return 编码后的字符串
     * @since 1.0
     */
    public static String encode(String str, String encodeCharsetName) {
        return transcode(str, encodeCharsetName, DEFAULT_CHARSET);
    }

    /**
     * 将字符串使用指定的字符集进行解码，并返回解码后的字符串
     *
     * @param str               字符文本
     * @param decodeCharsetName 编码字符集名称
     * @return 编码后的字符串
     * @since 1.0
     */
    public static String decode(String str, String decodeCharsetName) {
        return transcode(str, DEFAULT_CHARSET, decodeCharsetName);
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
}
