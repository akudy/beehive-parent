/*
 * Copyright (c) 2019-2023 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.format.FormatMarkerBuilder
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2023-03-06
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string.pattern;

import java.text.DecimalFormatSymbols;
import java.util.EnumSet;
import java.util.Locale;

/**
 * 模式/格式字符串模板构建器。
 * <br>
 * 提供模式/格式字符串模板占位符形式的字符串构建方法，其中每一个实例代表一个格式字符串模板的开始和结束。<br/>
 * 提供各种方法来完成<code> %[argument_index$][flags][width][.precision]conversion </code>内容个构建<br/>
 * 可参考{@link java.util.Formatter}<a href ="https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html">官方文档</a>
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.string.format</code></li>
 *   <li>Class Name: <code>PatternString</code></li>
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
 *       </tr>
 *       <tr>
 *         <td align="center"><em>1.0</em></td>
 *         <td align="center"><em>Java 8</em></td>
 *         <td align="center"><em>2023-03-06</em></td>
 *         <td align="center"><em>akudy</em></td>
 *         <td><em>Define</em></td>
 *       </tr>
 *     </table>
 *   </dd>
 * </dl>
 *
 * @author <a href="mailto:akudys@163.com">akudy</a>
 * @version 1.0
 * @see java.util.Formatter
 * @since 1.0
 */
public final class PatternString implements PatternPlaceholder {

    private final static char START_SYMBOL = '%';
    private final static char ARGUMENT_START_SYMBOL = '$';
    private final static char LEFT_ALIGN_SYMBOL = '-';
    private final static char ALTERNATE_SYMBOL = '#';
    private final static char SIGNED_NUMBER_SYMBOL = '+';
    private final static char LEADING_SPACE_SYMBOL = ' ';
    private final static char LEADING_ZERO_SYMBOL = '0';
    private final static char DEFAULT_GROUPING_SEPARATOR_SYMBOL = DecimalFormatSymbols.getInstance().getGroupingSeparator();
    private final static char NEGATIVE_NUMBER_ENCLOSED_SYMBOL = '(';

    /**
     * 参数索引，表示这个格式字符串作用的参数位置，从1开始计数
     *
     * @see java.util.Formatter
     */
    private Integer argIndex;

    /**
     * 参数输出格式修饰符号
     *
     * @see java.util.Formatter
     */
    private String flags;

    /**
     * 输出的最小字符数量
     *
     * @see java.util.Formatter
     */
    private Integer width;

    /**
     * 浮点数的精度标记，一个正整数
     *
     * @see java.util.Formatter
     */
    private Integer precision;

    /**
     * 格式字符串符号标记
     *
     * @see java.util.Formatter
     */
    private String marker;

    private PatternString(Builder builder) {
        this.argIndex = builder.argIndex;
        this.flags = builder.getFlags();
        this.width = builder.width;
        this.precision = builder.precision;
        this.marker = builder.marker.mark();
    }

    /**
     * 返回一个格式字符串的占位符<br/>
     * 格式字符串示例：%[argument_index$][flags][width][.precision]conversion
     *
     * @return 格式字符串的占位符号
     */
    @Override
    public String placeholder() {
        StringBuilder sb = new StringBuilder();
        sb.append(START_SYMBOL);
        if (this.argIndex != null) {
            sb.append(this.argIndex).append(ARGUMENT_START_SYMBOL);
        }
        if (this.flags != null) {
            sb.append(this.flags);
        }
        if (this.width != null) {
            sb.append(this.width);
        }
        if (this.precision != null) {
            sb.append('.').append(this.precision);
        }
        sb.append(this.marker);
        return sb.toString();
    }

    /**
     * 构建一个格式字符串构建器
     *
     * @param marker 格式符号标记
     * @return 当前格式字符串构建器
     */
    public static Builder builder(Marker marker) {
        return new Builder(marker);
    }

    /**
     * @return 格式字符串的占位符
     * @see #placeholder()
     */
    @Override
    public String toString() {
        return this.placeholder();
    }

    /**
     * 格式字符串构建器
     */
    public static class Builder {

        private Integer argIndex;
        private Character flag_left_align;
        private Character flag_default_alternate;
        private Character flag_signed_number;
        private Character flag_leading_space;
        private Character flag_leading_zero;
        private Character flag_grouping_separator;
        private Character flag_enclose_negative_number;
        private Integer width;
        private Integer precision;
        private Marker marker;

        Builder(Marker marker) {
            this.marker = marker;
        }

        /**
         * 设置一个参数索引信息
         *
         * @param argumentIndex 参数索引值，由调用者指定，从1开始
         * @return 当前格式字符串构建器
         */
        public Builder argumentIndex(Integer argumentIndex) {
            if (argumentIndex < 1) {
                throw new IllegalArgumentException("argument_index must be greater than 0");
            }
            this.argIndex = argumentIndex;
            return this;
        }

        /**
         * 设置信息左对齐，末尾通过空格进行填充
         *
         * @param minOutputCharCount 最小输出的字符串数量，大于等于0
         * @return 当前格式字符串构建器
         */
        public Builder leftAlign(int minOutputCharCount) {
            if (minOutputCharCount < 0) {
                throw new IllegalArgumentException("min output char count must be greater than 0");
            }
            if (this.flag_leading_zero != null) {
                throw new IllegalArgumentException("marker '-' and marker '0' cannot coexist.");
            }
            this.flag_left_align = LEFT_ALIGN_SYMBOL;
            this.width = minOutputCharCount;
            return this;
        }

        /**
         * 设置信息右对齐，如果不设置，默认也是右对齐
         *
         * @return 当前格式字符串构建器
         */
        public Builder rightAlign() {
            this.flag_left_align = null;
            return this;
        }

        /**
         * 设置值的默认展示格式<br/>
         * <ul>
         *     <li>支持八进制格式符号'o'，默认格式为：开头补充0</li>
         *     <li>支持十六进制格式符号'x'和'X'，默认格式为：开头补充0x或者0X</li>
         *     <li>支持浮点数格式符号'f', 'e', 'E', 'g', 'G', 'a', 'A'，默认格式为：始终展示小数点</li>
         * </ul>
         *
         * @return 当前格式字符串构建器
         */
        public Builder defaultFormat() {
            if (!PatternString.Marker.HAS_FORMAT_MARKERS.contains(this.marker)) {
                throw new IllegalArgumentException("[" + this.marker + "] not support. please modify marker to " + PatternString.Marker.HAS_FORMAT_MARKERS + ".");
            }
            this.flag_default_alternate = ALTERNATE_SYMBOL;
            return this;
        }

        /**
         * 设置数值的展示为有符号的数值<br/>
         * <ul>
         *     <li>支持十进制格式符号'd'</li>
         *     <li>支持八进制格式符号'o'</li>
         *     <li>支持十六进制格式符号'x'和'X'</li>
         *    <li>支持浮点数进制格式符号'f', 'e', 'E', 'g', 'G', 'a', 'A'</li>
         * </ul>
         *
         * @return 当前格式字符串构建器
         */
        public Builder signedNumber() {
            if (!PatternString.Marker.NUMERIC_TYPE_MARKERS.contains(this.marker)) {
                throw new IllegalArgumentException("[" + this.marker + "] not support. please modify marker to " + PatternString.Marker.NUMERIC_TYPE_MARKERS + ".");
            }
            if (this.flag_leading_space != null) {
                throw new IllegalArgumentException("marker '+' and marker ' ' cannot coexist.");
            }
            this.flag_signed_number = SIGNED_NUMBER_SYMBOL;
            return this;
        }

        /**
         * 设置数值的展示时，补充指定数量的前置空格<br/>
         * <ul>
         *     <li>支持十进制格式符号'd'</li>
         *     <li>支持八进制格式符号'o'</li>
         *     <li>支持十六进制格式符号'x'和'X'</li>
         *     <li>支持浮点数进制格式符号'f', 'e', 'E', 'g', 'G', 'a', 'A'</li>
         * </ul>
         *
         * @return 当前格式字符串构建器
         */
        public Builder leadingSpace() {
            if (!PatternString.Marker.NUMERIC_TYPE_MARKERS.contains(this.marker)) {
                throw new IllegalArgumentException("[" + this.marker + "] not support. please modify marker to " + PatternString.Marker.NUMERIC_TYPE_MARKERS + ".");
            }
            if (this.flag_signed_number != null) {
                throw new IllegalArgumentException("marker ' ' and marker '+' cannot coexist.");
            }
            this.flag_leading_space = LEADING_SPACE_SYMBOL;
            return this;
        }

        /**
         * 设置数值的展示时，补充指定数量的前置0，内置默认内容宽度为1<br/>
         * <ul>
         *     <li>支持十进制格式符号'd'</li>
         *     <li>支持八进制格式符号'o'</li>
         *     <li>支持十六进制格式符号'x'和'X'</li>
         *     <li>支持浮点数进制格式符号'f', 'e', 'E', 'g', 'G', 'a', 'A'</li>
         * </ul>
         *
         * @return 当前格式字符串构建器
         */
        public Builder leadingZero() {
            if (!PatternString.Marker.NUMERIC_TYPE_MARKERS.contains(this.marker)) {
                throw new IllegalArgumentException("[" + this.marker + "] not support. please modify marker to " + PatternString.Marker.NUMERIC_TYPE_MARKERS + ".");
            }
            if (this.flag_left_align != null) {
                throw new IllegalArgumentException("marker '0' and marker '-' cannot coexist.");
            }
            this.flag_leading_zero = LEADING_ZERO_SYMBOL;
            if (this.width == null) {
                this.width = 1;
            }
            return this;
        }


        /**
         * 设置一个本地语言环境默认的数值分组展示符号<br/>
         * <ul>
         *     <li>支持十进制格式符号'd'</li>
         *     <li>支持浮点数进制格式符号'f', 'e', 'E', 'g', 'G'</li>
         * </ul>
         *
         * @return 当前格式字符串构建器
         */
        public Builder groupingSeparator() {
            if (!PatternString.Marker.GROUPING_SUPPORT_MARKERS.contains(this.marker)) {
                throw new IllegalArgumentException("[" + this.marker + "] not support. please modify marker to " + PatternString.Marker.GROUPING_SUPPORT_MARKERS + ".");
            }
            this.flag_grouping_separator = DEFAULT_GROUPING_SEPARATOR_SYMBOL;
            return this;
        }

        /**
         * 设置指定语言环境默认的数值分组展示符号<br/>
         * <ul>
         *     <li>支持十进制格式符号'd'</li>
         *     <li>支持浮点数进制格式符号'f', 'e', 'E', 'g', 'G'</li>
         * </ul>
         *
         * @return 当前格式字符串构建器
         */
        public Builder groupingSeparator(Locale locale) {
            if (!PatternString.Marker.GROUPING_SUPPORT_MARKERS.contains(this.marker)) {
                throw new IllegalArgumentException("[" + this.marker + "] not support. please modify marker to " + PatternString.Marker.GROUPING_SUPPORT_MARKERS + ".");
            }
            char separator = DecimalFormatSymbols.getInstance(locale).getGroupingSeparator();
            this.flag_grouping_separator = separator;
            return this;
        }


        /**
         * 设置负数通过括号进行包裹<br/>
         * <ul>
         *     <li>支持十进制格式符号'd'</li>
         *     <li>支持八进制格式符号'o'</li>
         *     <li>支持十六进制格式符号'x'和'X'</li>
         *     <li>支持浮点数进制格式符号'f', 'e', 'E', 'g', 'G'</li>
         * </ul>
         *
         * @return 当前格式字符串构建器
         */
        public Builder negativeNumberEnclosed() {
            if (!PatternString.Marker.ENCLOSE_NEGATIVE_NUMBER_MARKERS.contains(this.marker)) {
                throw new IllegalArgumentException("[" + this.marker + "] not support. please modify marker to " + PatternString.Marker.ENCLOSE_NEGATIVE_NUMBER_MARKERS + ".");
            }
            this.flag_enclose_negative_number = NEGATIVE_NUMBER_ENCLOSED_SYMBOL;
            return this;
        }

        /**
         * 获取一个Flags标记集
         */
        private String getFlags() {
            StringBuilder flags = new StringBuilder();
            if (this.flag_left_align != null) {
                flags.append(this.flag_left_align);
            }
            if (this.flag_default_alternate != null) {
                flags.append(this.flag_default_alternate);
            }
            if (this.flag_signed_number != null) {
                flags.append(this.flag_signed_number);
            }
            if (this.flag_leading_space != null) {
                flags.append(this.flag_leading_space);
            }
            if (this.flag_leading_zero != null) {
                flags.append(this.flag_leading_zero);
            }
            if (this.flag_grouping_separator != null) {
                flags.append(this.flag_grouping_separator);
            }
            if (this.flag_enclose_negative_number != null) {
                flags.append(this.flag_enclose_negative_number);
            }
            return flags.toString();
        }

        /**
         * 设置输出展示的最小内容宽度<br/>
         * 如果参数字符小于该宽度，则左边填充空格，直到满足最小宽度<br/>
         *
         * @param minOutputCharCount 最小的输出字符数量
         * @return 当前格式字符串构建器
         */
        public Builder minWidth(int minOutputCharCount) {
            this.width = minOutputCharCount;
            return this;
        }

        /**
         * 设置输出展示的最大内容宽度<br/>
         * 如果参数字符大于该宽度，则裁剪右边的字符，直到满足最大宽度<br/>
         * <ul>
         *     <li>不支持浮点数格式符号f', 'e', 'E', 'g', 'G', 'a', 'A'</li>
         *     <li>不支持字符格式符号'c'和'C'</li>
         *     <li>不支持整数格式符号'd', 'o', 'x', 'X'</li>
         *     <li>不支持日期't'和'T'开头的符号</li>
         *     <li>不支持特殊符号'%'和'\n'</li>
         * </ul>
         *
         * @param maxOutputCharCount 最大的输出字符数量
         * @return 当前格式字符串构建器
         */
        public Builder maxWidth(int maxOutputCharCount) {
            if (PatternString.Marker.MAX_WIDTH_UN_SUPPORT_MAKERS.contains(this.marker) || PatternString.Marker.DATE_TIME_TYPE_MARKERS.contains(this.marker)) {
                throw new IllegalArgumentException("[" + this.marker + "] not support. please modify marker to outside of " + PatternString.Marker.MAX_WIDTH_UN_SUPPORT_MAKERS +
                        " and " + PatternString.Marker.DATE_TIME_TYPE_MARKERS + ".");
            }
            this.precision = maxOutputCharCount;
            return this;
        }

        /**
         * 设置浮点数的精度<br/>
         * <ul>
         *     <li>支持浮点数格式符号f', 'e', 'E', 'g', 'G', 'a', 'A'</li>
         * </ul>
         *
         * @param precision 精度，小数位数
         * @return 当前格式字符串构建器
         */
        public Builder decimalNumberPrecision(int precision) {
            if (!PatternString.Marker.FLOATING_NUMERIC_TYPE_MARKERS.contains(this.marker)) {
                throw new IllegalArgumentException("[" + this.marker + "] not support. please modify marker to " + PatternString.Marker.FLOATING_NUMERIC_TYPE_MARKERS + ".");
            }
            this.precision = precision;
            return this;
        }


        /**
         * 构建一个格式字符串对象
         *
         * @return 格式字符串对象
         */
        public PatternString build() {
            return new PatternString(this);
        }

    }

    /**
     * 格式化符号标记接口定义
     * <br>
     * 描述格式化符号类型定义时需要的提供的信息，包括符号标记定义、符号描述以及符号使用示例。
     */
    public enum Marker {

        /*时间特定格式符号*/
        /**
         * 24时制小时值(2位), 例如：24
         */
        TIME_HOUR_24_HOUR_CLOCK("tH"),
        /**
         * 12时制小时值(2位)，例如：12
         */
        TIME_HOUR_12_HOUR_CLOCK("tI"),
        /**
         * 24时制小时值(1位)；例如：5
         */
        TIME_HOUR_24_HOUR_CLOCK_SHORT("tk"),
        /**
         * 12时制小时值(1位)；例如：5
         */
        TIME_HOUR_12_HOUR_CLOCK_SHORT("tl"),
        /**
         * 分钟数值(2位)；例如：54
         */
        TIME_MINUTE("tM"),
        /**
         * 秒数值(2位)；例如：58
         */
        TIME_SECOND("tS"),
        /**
         * 微秒数值(3位)；例如：845
         */
        TIME_MILLISECOND("tL"),
        /**
         * 纳秒数值(9位)；例如：541258489
         */
        TIME_NANOSECOND("tN"),
        /**
         * am/pm值(小写)；例如：am
         */
        TIME_AM_PM_LOWERCASE("tp"),
        /**
         * AM/PM值(大写)；例如：PM
         */
        TIME_AM_PM_UPPERCASE("Tp"),
        /**
         * GMT时区偏移值数字表示形式；例如：+0800
         */
        TIME_GMT_OFFSET("tz"),
        /**
         * 时区缩写的字符串；例如：CST
         */
        TIME_ZONE_ABBR("tZ"),
        /**
         * 距离1970-1-1 00:00:00开始的秒数值；例如：1675071673
         */
        SECONDS("ts"),
        /**
         * 距离从1970-1-1 00:00:00开始的毫秒数值；例如：1675071673949
         */
        MILLISECOND("tQ"),

        /*日期特定格式符号*/
        /**
         * 月份名称；例如：January
         */
        DATE_MONTH_NAME("tB"),
        /**
         * 月份缩写名称；例如：Jan
         */
        DATE_MONTH_NAME_SHORT("tb"),
        /**
         * 星期名称；例如：Sunday
         */
        DATE_WEEK_NAME("tA"),
        /**
         * 星期缩写名称；例如：Sun
         */
        DATE_WEEK_NAME_SHORT("ta"),
        /**
         * 年份前两位数值；例如：20
         */
        DATE_YEAR_FIRST_2_DIGITS("tC"),
        /**
         * 年份(4位)；例如：2022
         */
        DATE_YEAR("tY"),
        /**
         * 年份后两位数值；例如：22
         */
        DATE_YEAR_LAST_2_DIGITS("ty"),
        /**
         * 年份中的第几天；例如：325
         */
        DATE_DAY_OF_YEAR("tj"),
        /**
         * 年份中的月份值(2位)；例如：12
         */
        DATE_MONTH_OF_YEAR("tm"),
        /**
         * 月份中的日期值(2位)；例如：05
         */
        DATE_DAY_OF_MONTH("td"),
        /**
         * 月份中的日期值(1位)；例如：5
         */
        DATE_DAY_OF_MONTH_SHORT("te"),

        /*公共日期和时间格式符号*/
        /**
         * 完整的日期时间格式；例如：星期六 十月 27 14:21:20 CST 2007
         */
        DATE_TIME_COMPLETED("tc"),
        /**
         * 完整ISO-8601日期格式；例如：2022-10-01
         */
        DATE_STANDARD("tF"),
        /**
         * 默认日期格式(美式)；例如：12/25/2012
         */
        DATE_DEFAULT("tD"),
        /**
         * 12时制时间格式；例如：02:25:51 下午
         */
        TIME_12_HOUR_CLOCK("tr"),
        /**
         * 24时制时间格式；例如：14:25:51
         */
        TIME_24_HOUR_CLOCK("tT"),
        /**
         * 24时制时间格式，仅含时分；例如：14:25
         */
        TIME_24_HOUR_CLOCK_SHORT("tR"),

        /*类型转换相关格式符号*/
        /**
         * 小写布尔值；例如：false
         */
        BOOL_VALUE_LOWERCASE("b"),
        /**
         * 大写布尔值；例如：TRUE
         */
        BOOL_VALUE_UPPERCASE("B"),
        /**
         * 小写十六进制字符串值；例如：3e8
         */
        HEX_VALUE_LOWERCASE("h"),
        /**
         * 大写十六进制字符串值；例如：3E8
         */
        HEX_VALUE_UPPERCASE("H"),
        /**
         * 小写字符串；例如：aaa
         */
        STRING_VALUE_LOWERCASE("s"),
        /**
         * 大写字符串；例如：AAA
         */
        STRING_VALUE_UPPERCASE("S"),
        /**
         * 小写Unicode字符；例如：a
         */
        UNICODE_CHARACTER_LOWERCASE("c"),
        /**
         * 大写Unicode字符；例如：A
         */
        UNICODE_CHARACTER_UPPERCASE("C"),
        /**
         * 十进制整数数值；例如：20
         */
        DECIMAL_INTEGER_NUMBER("d"),
        /**
         * 八进制整数数值；例如：310
         */
        OCTAL_INTEGER_NUMBER("o"),
        /**
         * 小写十六进制整数数值；例如：c8
         */
        HEX_INTEGER_NUMBER_LOWERCASE("x"),
        /**
         * 大写十六进制整数数值；例如：C8
         */
        HEX_INTEGER_NUMBER_UPPERCASE("X"),
        /**
         * 小写科学计数法浮点数数值；例如：2.000000e+02
         */
        SCIENTIFIC_NOTATION_FLOATING_NUMBER_LOWERCASE("e"),
        /**
         * 大写科学计数法浮点数数值；例如：2.000000E+02
         */
        SCIENTIFIC_NOTATION_FLOATING_NUMBER_UPPERCASE("E"),
        /**
         * 十进制浮点数数值；例如：200.000000
         */
        DECIMAL_FLOATING_NUMBER("f"),
        /**
         * 小写科学计数法浮点数四舍五入后数值；例如：2.00000e+08
         */
        SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_LOWERCASE("g"),
        /**
         * 大写科学计数法浮点数四舍五入后数值；例如：2.00000E+08
         */
        SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_UPPERCASE("G"),
        /**
         * 小写指数格式浮点数数值；例如：0x1.7d784p27
         */
        SIGNIFICAND_EXPONENT_HEX_FLOATING_NUMBER_LOWERCASE("a"),
        /**
         * 大写指数格式浮点数数值；例如：0X1.7D784P27
         */
        SIGNIFICAND_EXPONENT_HEX_FLOATING_NUMBER_UPPERCASE("A"),

        /*特殊符号*/
        /**
         * 百分号；例如：%
         */
        PERCENT("%"),
        /**
         * 换行符；例如：\n
         */
        LINE_SEPARATOR(System.lineSeparator());

        /**
         * 日期时间类型符号
         */
        static EnumSet<Marker> DATE_TIME_TYPE_MARKERS = EnumSet.of(TIME_HOUR_24_HOUR_CLOCK, TIME_HOUR_12_HOUR_CLOCK, TIME_HOUR_24_HOUR_CLOCK_SHORT, TIME_HOUR_12_HOUR_CLOCK_SHORT, TIME_MINUTE, TIME_SECOND,
                TIME_MILLISECOND, TIME_NANOSECOND, TIME_AM_PM_LOWERCASE, TIME_AM_PM_UPPERCASE, TIME_GMT_OFFSET, TIME_ZONE_ABBR, SECONDS, MILLISECOND, DATE_MONTH_NAME, DATE_MONTH_NAME_SHORT,
                DATE_WEEK_NAME, DATE_WEEK_NAME_SHORT, DATE_YEAR_FIRST_2_DIGITS, DATE_YEAR, DATE_YEAR_LAST_2_DIGITS, DATE_DAY_OF_YEAR, DATE_MONTH_OF_YEAR, DATE_DAY_OF_MONTH, DATE_DAY_OF_MONTH_SHORT,
                DATE_TIME_COMPLETED, DATE_STANDARD, DATE_DEFAULT, TIME_12_HOUR_CLOCK, TIME_24_HOUR_CLOCK, TIME_24_HOUR_CLOCK_SHORT);

        /**
         * 支付类型符号
         */
        static EnumSet<Marker> CHARACTER_TYPE_MARKERS = EnumSet.of(UNICODE_CHARACTER_LOWERCASE, UNICODE_CHARACTER_UPPERCASE);

        /**
         * 整数类型符号
         */
        static EnumSet<Marker> INTEGRAL_NUMERIC_TYPE_MARKERS = EnumSet.of(DECIMAL_INTEGER_NUMBER, OCTAL_INTEGER_NUMBER, HEX_INTEGER_NUMBER_LOWERCASE, HEX_INTEGER_NUMBER_UPPERCASE);

        /**
         * 浮点数类型符号
         */
        static EnumSet<Marker> FLOATING_NUMERIC_TYPE_MARKERS = EnumSet.of(SCIENTIFIC_NOTATION_FLOATING_NUMBER_LOWERCASE, SCIENTIFIC_NOTATION_FLOATING_NUMBER_UPPERCASE, DECIMAL_FLOATING_NUMBER,
                SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_LOWERCASE, SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_UPPERCASE, SIGNIFICAND_EXPONENT_HEX_FLOATING_NUMBER_LOWERCASE,
                SIGNIFICAND_EXPONENT_HEX_FLOATING_NUMBER_UPPERCASE);

        /**
         * 数字类型符号
         */
        static EnumSet<Marker> NUMERIC_TYPE_MARKERS = EnumSet.of(DECIMAL_INTEGER_NUMBER, OCTAL_INTEGER_NUMBER, HEX_INTEGER_NUMBER_LOWERCASE, HEX_INTEGER_NUMBER_UPPERCASE,
                SCIENTIFIC_NOTATION_FLOATING_NUMBER_LOWERCASE, SCIENTIFIC_NOTATION_FLOATING_NUMBER_UPPERCASE, DECIMAL_FLOATING_NUMBER,
                SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_LOWERCASE, SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_UPPERCASE, SIGNIFICAND_EXPONENT_HEX_FLOATING_NUMBER_LOWERCASE,
                SIGNIFICAND_EXPONENT_HEX_FLOATING_NUMBER_UPPERCASE);

        /**
         * 特殊符号
         */
        static EnumSet<Marker> SPECIAL_MARKERS = EnumSet.of(PERCENT, LINE_SEPARATOR);

        /**
         * 有格式的符号
         */
        static EnumSet<Marker> HAS_FORMAT_MARKERS = EnumSet.of(OCTAL_INTEGER_NUMBER, HEX_INTEGER_NUMBER_LOWERCASE, HEX_INTEGER_NUMBER_UPPERCASE, SCIENTIFIC_NOTATION_FLOATING_NUMBER_LOWERCASE,
                SCIENTIFIC_NOTATION_FLOATING_NUMBER_UPPERCASE, DECIMAL_FLOATING_NUMBER, SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_LOWERCASE, SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_UPPERCASE,
                SIGNIFICAND_EXPONENT_HEX_FLOATING_NUMBER_LOWERCASE, SIGNIFICAND_EXPONENT_HEX_FLOATING_NUMBER_UPPERCASE);

        /**
         * 可分租的符号
         */
        static EnumSet<Marker> GROUPING_SUPPORT_MARKERS = EnumSet.of(DECIMAL_INTEGER_NUMBER, DECIMAL_FLOATING_NUMBER, SCIENTIFIC_NOTATION_FLOATING_NUMBER_LOWERCASE, SCIENTIFIC_NOTATION_FLOATING_NUMBER_UPPERCASE,
                SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_LOWERCASE, SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_UPPERCASE);

        /**
         * 可被包括的负数符号
         */
        static EnumSet<Marker> ENCLOSE_NEGATIVE_NUMBER_MARKERS = EnumSet.of(DECIMAL_INTEGER_NUMBER, OCTAL_INTEGER_NUMBER, HEX_INTEGER_NUMBER_LOWERCASE, HEX_INTEGER_NUMBER_UPPERCASE,
                DECIMAL_FLOATING_NUMBER, SCIENTIFIC_NOTATION_FLOATING_NUMBER_LOWERCASE, SCIENTIFIC_NOTATION_FLOATING_NUMBER_UPPERCASE,
                SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_LOWERCASE, SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_UPPERCASE);

        /**
         * 最大宽度不支持的符号
         */
        static EnumSet<Marker> MAX_WIDTH_UN_SUPPORT_MAKERS = EnumSet.of(DECIMAL_FLOATING_NUMBER, SCIENTIFIC_NOTATION_FLOATING_NUMBER_LOWERCASE, SCIENTIFIC_NOTATION_FLOATING_NUMBER_UPPERCASE,
                SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_LOWERCASE, SCIENTIFIC_NOTATION_FLOATING_NUMBER_OF_ROUNDING_UPPERCASE, SIGNIFICAND_EXPONENT_HEX_FLOATING_NUMBER_LOWERCASE,
                SIGNIFICAND_EXPONENT_HEX_FLOATING_NUMBER_UPPERCASE, UNICODE_CHARACTER_LOWERCASE, UNICODE_CHARACTER_UPPERCASE, DECIMAL_INTEGER_NUMBER, OCTAL_INTEGER_NUMBER, HEX_INTEGER_NUMBER_LOWERCASE,
                HEX_INTEGER_NUMBER_UPPERCASE, PERCENT, LINE_SEPARATOR);

        private String mark;

        Marker(String mark) {
            this.mark = mark;
        }

        /**
         * 符号标记
         *
         * @return 符号标记
         */
        protected String mark() {
            return this.mark;
        }

        @Override
        public String toString() {
            return this.mark;
        }

    }

}
