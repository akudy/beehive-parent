/*
 * Copyright (c) 2019-2023 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.format.FormatMessage
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2023-03-07
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string.pattern;

/**
 * 模式/格式文本消息模板构建器。
 * <br>
 * 提供模式/格式文本消息模板占位符形式的字符串构建方法，其中每一个实例代表一个格式消息模板的开始和结束。<br/>
 * 提供各种方法来完成<code> { ArgumentIndex , FormatType , FormatStyle } </code>内容个构建<br/>
 * 可参考{@link java.text.MessageFormat}<a href ="https://docs.oracle.com/javase/8/docs/api/java/text/MessageFormat.html">官方文档</a>
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.string.format</code></li>
 *   <li>Class Name: <code>PatternMessage</code></li>
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
 *         <td align="center"><em>2023-03-07</em></td>
 *         <td align="center"><em>akudy</em></td>
 *         <td><em>Define</em></td>
 *       </tr>
 *     </table>
 *   </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @since 1.0
 */
public final class PatternMessage implements PatternPlaceholder {

    /**
     * 参数索引
     */
    private int argIndex;

    /**
     * 格式类型
     */
    private String formatType;

    /**
     * 格式样式
     */
    private String formatStyle;

    private PatternMessage(Builder builder) {
        this.argIndex = builder.argIndex;
        this.formatType = builder.formatType;
        this.formatStyle = builder.getFormatStyle();
    }

    /**
     * 创建一个字符串型模式消息构建器
     *
     * @param argumentIndex 参数索引值，从0开始
     * @return 字符串类型模式消息字符串构建器
     */
    public static StringPatternBuilder stringBuilder(int argumentIndex) {
        return new StringPatternBuilder(argumentIndex);
    }

    /**
     * 创建一个数字类型模式消息构建器
     *
     * @param argumentIndex 参数索引值，从0开始
     * @return 数字类型模式消息字符串构建器
     */
    public static NumberPatternBuilder numberBuilder(int argumentIndex) {
        return new NumberPatternBuilder(argumentIndex);
    }

    /**
     * 创建一个日期类型模式消息构建器
     *
     * @param argumentIndex 参数索引值，从0开始
     * @return 日期类型模式消息字符串构建器
     */
    public static DatePatternBuilder dateBuilder(int argumentIndex) {
        return new DatePatternBuilder(argumentIndex);
    }

    /**
     * 创建一个时间类型模式消息构建器
     *
     * @param argumentIndex 参数索引值，从0开始
     * @return 时间类型模式消息字符串构建器
     */
    public static TimePatternBuilder timeBuilder(int argumentIndex) {
        return new TimePatternBuilder(argumentIndex);
    }

    @Override
    public String placeholder() {
        StringBuilder sb = new StringBuilder("{");
        sb.append(this.argIndex);
        if (this.formatType != null) {
            sb.append(",").append(this.formatType);
        }
        if (this.formatStyle != null) {
            sb.append(",").append(this.formatStyle);
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return this.placeholder();
    }

    private static void repeatAppend(StringBuilder sb, char ch, int figure) {
        if (figure < 0) {
            throw new IllegalArgumentException("figure must be greater than 0");
        }
        for (int i = 0; i < figure; i++) {
            sb.append(ch);
        }
    }

    private static void repeatAppend(StringBuilder sb, String str, int figure) {
        if (figure < 0) {
            throw new IllegalArgumentException("figure must be greater than 0");
        }
        for (int i = 0; i < figure; i++) {
            sb.append(str);
        }
    }

    /**
     * 抽象的格式样式构建器
     */
    protected abstract static class Builder {
        private int argIndex;
        private String formatType;

        protected Builder(String formatType, int argumentIndex) {
            this.formatType = formatType;
            if (argumentIndex < 0) {
                throw new IllegalArgumentException("argument_index must be greater than -1");
            }
            this.argIndex = argumentIndex;
        }

        /**
         * 获取格式样式字符串
         *
         * @return 格式样式字符串
         */
        protected abstract String getFormatStyle();

        public PatternMessage build() {
            return new PatternMessage(this);
        }

    }

    /**
     * 日期字符串模式消息构建器
     */
    public static class StringPatternBuilder extends Builder {

        /**
         * 创建一个字符串模式消息构建器
         *
         * @param argumentIndex 参数索引，从0开始计数
         */
        public StringPatternBuilder(int argumentIndex) {
            super(null, argumentIndex);
        }

        @Override
        protected String getFormatStyle() {
            return null;
        }
    }

    /**
     * 参考<a href="https://blog.csdn.net/qq_49912622/article/details/122507312">DecimalFormat数字格式转换</a><br/>
     * <table border="1">
     *     <caption>日期时间格式字符解释</caption>
     *     <tr>
     *         <th width="10%">符号</th>
     *         <th width="45%">符号描述</th>
     *         <th width="45%">使用说明</th>
     *     </tr>
     *     <tr>
     *         <td>0</td>
     *         <td>表示当前位置是一个任意数字，如果参数中当前位置数字缺失，则默认补充0</td>
     *         <td rowspan="2">
     *             整数部分：
     *             <ul>
     *                 <li>如果设置n个0，则从个位开始向高位填充，如果有值就使用原值，否则就填充0</li>
     *                 <li>如果设置n个#，没有实际意义，无论设置多少个#都是原值</li>
     *                 <li>0和#配合使用，实现上面描述的合集；0只能在#的右边，不能出现在#的左边</li>
     *             </ul>
     *             小数部分：<br/>
     *             <ul>
     *                 <li>如果设置n个0，则表示保留（按照指定的舍入规则，默认四舍五入）n位小数，即从十分位开始向低位填充，如果有值就使用原值，否则就填充0</li>
     *                 <li>如果设置n个#，则表示保留（按照指定的舍入规则，默认四舍五入）n位小数，即从十分位开始向低位填充，如果有值就使用原值，否则不进行填充</li>
     *                 <li>0和#配合使用，实现上面描述的合集；0只能在#的左边，不能出现在#的右边</li>
     *             </ul>
     *         </td>
     *     </tr>
     *     <tr>
     *          <td>#</td>
     *          <td>表示当前位置是一个任意数字，如果参数中当前位置数字缺失，则不做任何处理</td>
     *      </tr>
     *      <tr>
     *          <td>.</td>
     *          <td>表示小数的分隔符</td>
     *          <td>可以出现在任意位置，小数点左边的整数部分和小数点右边的小数部分可能存在模式规则差异</td>
     *      </tr>
     *      <tr>
     *          <td>-</td>
     *          <td>表示这个数值在输出时是一个负数，自动在前面负号</td>
     *          <td>必须出现在模式的开头</td>
     *      </tr>
     *      <tr>
     *          <td>,</td>
     *          <td>表示数字分组分隔符</td>
     *          <td>最后一个分隔符才生效，分隔符右边的#或0表示每一组的数值个数</td>
     *      </tr>
     *      <tr>
     *          <td>E</td>
     *          <td>表示用于分割科学计数法中的尾数和指数</td>
     *          <td>
     *              <ul>
     *                  <li>需要保证E前面一定存在一个0或者#</li>
     *                  <li>E后面必须是0，不能是#；0的个数对输出显示有影响，不足会填充0</li>
     *                  <li>E前面#和0的总个数决定指数：如果指数值大于总个数，则指数的值是个数的倍数；否则，指数值等于总个数</li>
     *                  <li>整数下，0个总个数决定输出结果的位数，与0的位置无关</li>
     *                  <li>整数部分保留几位数字，就使用几个0描述</li>
     *              </ul>
     *          </td>
     *      </tr>
     *      <tr>
     *          <td>;</td>
     *          <td>表示用于区分正数和负数的子模式</td>
     *          <td><strong><font color="red">未深入考究，展示不支持使用</font></strong></td>
     *      </tr><tr>
     *          <td>%</td>
     *          <td>表示是一个百分数，结果将是一个自动乘以100后的百分数值</td>
     *           <td rowspan="2">除了不能放在开头，可以出现在其他任何位置；二者只能选一个使用，不能共存</td>
     *      </tr>
     *      <tr>
     *          <td>‰(&#92;u2030)</td>
     *          <td>表示是一个千分数，结果将是一个自动乘以1000后的千分数值</td>
     *      </tr>
     *      <tr>
     *          <td>¤(&#92;u00A4)</td>
     *          <td>表示是一个货币格式的数字</td>
     *          <td>
     *              <ul>
     *                  <li>出现一次表示使用本地化货币符号</li>
     *                  <li>连续出现两次表示使用国际化货币符号</li>
     *                  <li>位置：如果出现在最前面，则货币符号会输出在最前面；如果出现在最后面，则货币符号会出现在最后面</li>
     *              </ul>
     *          </td>
     *      </tr>
     *      <tr>
     *          <td>'</td>
     *          <td>转义符号，表示紧接其后的符号不被解析和替换，而是直接输出</td>
     *          <td>用于引用特殊的字符，作为前缀或后缀</td>
     *      </tr>
     * </table>
     */
    public static class NumberPatternBuilder extends Builder {

        private final static char DIGIT_SYMBOL = '#';
        private final static char DIGIT_OR_ZERO_SYMBOL = '0';
        private final static char DECIMAL_POINT_SYMBOL = '.';
        private final static char NEGATIVE_NUMBER_SYMBOL = '-';
        private final static char GROUPING_SYMBOL = ',';
        private final static char SCIENTIFIC_NOTATION_SYMBOL = 'E';
        private final static char PERCENT_SYMBOL = '%';
        private final static char PER_MILLE_SYMBOL = '‰';
        private final static char CURRENCY_SYMBOL = '¤';

        /**
         * 货币模式串
         */
        public static class CurrencyPattern {
            /**
             * 是否展示在末尾
             */
            private boolean tailEnd;
            /**
             * 和数值之间的间隔字符串
             */
            private String spacing;
            /**
             * 是否使用国际化货币符号
             */
            private boolean international;

            /**
             * 设置符号和数值之间的间隔字符串
             *
             * @param spacing 间隔字符串
             * @return 当前货币模式对象
             */
            public CurrencyPattern setSpacing(String spacing) {
                this.spacing = spacing;
                return this;
            }

            /**
             * 设置货币是否国际化
             *
             * @param international 如果设置为true，则表示国际化；否则表示本地化
             * @return 当前货币模式对象
             */
            public CurrencyPattern ofInternational(boolean international) {
                this.international = international;
                return this;
            }

            /**
             * 设置货币符号是否展示在默认
             *
             * @param tailEnd 如果设置为true，则表示展示在末尾；否则展示在开头
             * @return 当前货币模式对象
             */
            public CurrencyPattern showTailEnd(boolean tailEnd) {
                this.tailEnd = tailEnd;
                return this;
            }

            /**
             * 获取模式串
             *
             * @return 模式串
             */
            public String toPattern() {
                StringBuilder sb = new StringBuilder();
                if (!this.tailEnd) {
                    this.appendCurrencySymbol(sb);
                    if (this.spacing != null) {
                        sb.append(this.spacing);
                    }
                } else {
                    if (this.spacing != null) {
                        sb.append(this.spacing);
                    }
                    this.appendCurrencySymbol(sb);
                }
                return sb.toString();
            }

            private void appendCurrencySymbol(StringBuilder sb) {
                sb.append(CURRENCY_SYMBOL);
                if (this.international) {
                    sb.append(CURRENCY_SYMBOL);
                }
            }
        }

        /**
         * 负数模式串
         */
        public static class NegativeNumberPattern {
            /**
             * 和数值之间的间隔字符串
             */
            private String spacing;

            public NegativeNumberPattern() {
                this.spacing = null;
            }

            /**
             * 实例化时指定符号和数值的间隔字符串
             *
             * @param spacing 间隔字符串
             */
            public NegativeNumberPattern(String spacing) {
                this.spacing = spacing;
            }

            /**
             * 获取模式串
             *
             * @return 模式串
             */
            public String toPattern() {
                StringBuilder sb = new StringBuilder();
                sb.append(NEGATIVE_NUMBER_SYMBOL);
                if (this.spacing != null) {
                    sb.append(this.spacing);
                }
                return sb.toString();
            }
        }

        /**
         * 比例模式串
         */
        public static class RatioPattern {
            /**
             * 和数值之间的间隔字符串
             */
            private String spacing;
            /**
             * 比率符号
             */
            private Character ratioSymbol;

            /**
             * 设置符号和数值之间的间隔字符串
             *
             * @param spacing 符号和数值之间的间隔字符串
             * @return 当前比例模式对象
             */
            public RatioPattern setSpacing(String spacing) {
                this.spacing = spacing;
                return this;
            }

            /**
             * 使用百分比模式
             *
             * @return 当前比例模式对象
             */
            public RatioPattern ofPercent() {
                this.ratioSymbol = PERCENT_SYMBOL;
                return this;
            }

            /**
             * 使用千分比模式
             *
             * @return 当前比例模式对象
             */
            public RatioPattern ofPerMille() {
                this.ratioSymbol = PER_MILLE_SYMBOL;
                return this;
            }

            /**
             * 获取模式串
             *
             * @return 模式串
             */
            public String toPattern() {
                StringBuilder sb = new StringBuilder();
                if (this.spacing != null) {
                    sb.append(this.spacing);
                }
                sb.append(this.ratioSymbol);
                return sb.toString();
            }
        }


        private StringBuilder integerPart;
        private boolean isFloatNumber;
        private StringBuilder decimalPart;
        private int precision;
        private boolean doGrouping;
        private int groupCount;

        private NegativeNumberPattern negativeNumberPattern;
        private CurrencyPattern currencyPattern;
        private RatioPattern ratioPattern;

        private boolean isScientificNotationMode;

        /**
         * 创建一个数字模式字符串构建器
         *
         * @param argumentIndex 参数索引，从0开始计数
         */
        public NumberPatternBuilder(int argumentIndex) {
            super("number", argumentIndex);
        }

        @Override
        protected String getFormatStyle() {
            StringBuilder sb = new StringBuilder();
            if (this.currencyPattern != null && !this.currencyPattern.tailEnd) {
                sb.append(this.currencyPattern.toPattern());
            }
            if (this.negativeNumberPattern != null) {
                sb.append(this.negativeNumberPattern.toPattern());
            }
            if (this.integerPart == null) {
                sb.append(DIGIT_SYMBOL);
            } else {
                sb.append(this.integerPart);
            }
            if (this.isFloatNumber) {
                sb.append(DECIMAL_POINT_SYMBOL);
            }
            if (this.decimalPart == null) {
                for (int i = 0; i < this.precision; i++) {
                    sb.append(DIGIT_SYMBOL);
                }
            } else {
                sb.append(this.decimalPart);
            }
            if (this.doGrouping) {
                sb.append(GROUPING_SYMBOL);
                for (int i = 0; i < this.groupCount; i++) {
                    sb.append(DIGIT_SYMBOL);
                }
            }
            if (this.isScientificNotationMode) {
                sb.append(SCIENTIFIC_NOTATION_SYMBOL).append(DIGIT_OR_ZERO_SYMBOL);
            }
            if (this.ratioPattern != null) {
                sb.append(this.ratioPattern.toPattern());
            }
            if (this.currencyPattern != null && this.currencyPattern.tailEnd) {
                sb.append(this.currencyPattern.toPattern());
            }
            return sb.toString();
        }

        /**
         * 指定一个数字位（如果目标没有缺少该位，则不处理）<br/>
         * 如果是整数部分（小数点之前），则不允许#出现在0之后
         *
         * @return 数值模式构建器
         */
        public NumberPatternBuilder digit() {
            return this.digit(1);
        }

        /**
         * 指定多个数字位（如果目标没有缺少该位，则不处理）<br/>
         * 如果是整数部分（小数点之前），则不允许#出现在0之后
         *
         * @param figure 位数
         * @return 数值模式构建器
         */
        public NumberPatternBuilder digit(int figure) {
            if (this.isFloatNumber) {
                // 当是小数格式时
                if (this.decimalPart == null) {
                    this.decimalPart = new StringBuilder();
                }
                PatternMessage.repeatAppend(this.decimalPart, DIGIT_SYMBOL, figure);
            } else {
                // 当是整数格式时
                if (this.integerPart == null) {
                    this.integerPart = new StringBuilder();
                } else if (this.integerPart.lastIndexOf(String.valueOf(DIGIT_OR_ZERO_SYMBOL)) != -1) {
                    // 如果已经包含了 0，则不允许添加 #
                    throw new IllegalArgumentException("integer part: '#' cannot appear to the right of '0'");
                }
                PatternMessage.repeatAppend(this.integerPart, DIGIT_SYMBOL, figure);
            }
            return this;
        }

        /**
         * 指定一个数字位（如果目标没有缺少该位，则填充0）<br/>
         * 如果是小数部分（小数点之后），则不允许0出现在#之后
         *
         * @return 数值模式构建器
         */
        public NumberPatternBuilder digitOrZero() {
            return this.digitOrZero(1);
        }

        /**
         * 指定多个数字位（如果目标没有缺少该位，则填充0）<br/>
         * 如果是小数部分（小数点之后），则不允许0出现在#之后
         *
         * @param figure 位数
         * @return 数值模式构建器
         */
        public NumberPatternBuilder digitOrZero(int figure) {
            if (this.isFloatNumber) {
                // 当是小数格式时
                if (this.decimalPart == null) {
                    this.decimalPart = new StringBuilder();
                } else if (this.decimalPart.lastIndexOf(String.valueOf(DIGIT_SYMBOL)) != -1) {
                    // 如果已经包含了#，则不允许添加0
                    throw new IllegalArgumentException("floating part: '0' cannot appear to the right of '#'");
                }
                PatternMessage.repeatAppend(this.decimalPart, DIGIT_OR_ZERO_SYMBOL, figure);
            } else {
                // 当是整数格式时
                if (this.integerPart == null) {
                    this.integerPart = new StringBuilder();
                }
                PatternMessage.repeatAppend(this.integerPart, DIGIT_OR_ZERO_SYMBOL, figure);
            }
            return this;
        }

        /**
         * 指定一个小数点，并指定精度为0；小数点前面的符号序列将被分割为整数部分，小数点后面的符号序列将被分割为小数部分。<br/>
         *
         * @return 数值模式构建器
         * @see #point(int)
         */
        public NumberPatternBuilder point() {
            return this.point(0);
        }

        /**
         * 指定一个小数点和小数精度位数；指定小数点后，小数点前面的符号序列将被分割为整数部分，小数点后面的符号序列将被分割为小数部分。<br/>
         * 指定小数点时需要指定小数的精度，如果没指定，则无精度
         *
         * @param precision 小数精度位数
         * @return 数值模式构建器
         */
        public NumberPatternBuilder point(int precision) {
            if (precision < 0) {
                throw new IllegalArgumentException("precision must be a positive number or 0");
            }
            this.isFloatNumber = true;
            this.precision = precision;
            return this;
        }

        /**
         * 指定一个按照3位数字进行分组的分组策略
         *
         * @return 数值模式构建器
         * @see #grouping(int)
         */
        public NumberPatternBuilder grouping() {
            return this.grouping(3);
        }

        /**
         * 指定一个按照指定位的个数数字进行分组的分组策略
         *
         * @param eachGroupCount 指定位的个数数字，每一组的数字个数
         * @return 数值模式构建器
         */
        public NumberPatternBuilder grouping(int eachGroupCount) {
            if (this.isFloatNumber && this.isScientificNotationMode) {
                throw new IllegalArgumentException("floating number mode, grouping and scientific notation cannot coexist");
            }
            if (groupCount < 0) {
                throw new IllegalArgumentException("group count must be a positive number or 0");
            }
            this.doGrouping = true;
            this.groupCount = eachGroupCount;
            return this;
        }

        /**
         * 指定使用科学计数法模式
         *
         * @return 数值模式构建器
         */
        public NumberPatternBuilder scientificNotation() {
            if (this.isFloatNumber && this.doGrouping) {
                throw new IllegalArgumentException("floating number mode, scientific notation and grouping cannot coexist");
            }
            this.isScientificNotationMode = true;
            return this;
        }


        /**
         * 指定这是一个负数模式，将会在输出的数字前面自动追加减号
         *
         * @return 数值模式构建器
         */
        public NumberPatternBuilder minusSign() {
            this.negativeNumberPattern = new NegativeNumberPattern();
            return this;
        }

        public NumberPatternBuilder minusSign(NegativeNumberPattern pattern) {
            this.negativeNumberPattern = pattern;
            return this;
        }

        /**
         * 指定这是一个百分比结果，结果会自动乘以100<br/>
         * 多次设置仅以最后一次生效；百分比和千分比只能一个生效
         *
         * @return 数值模式构建器
         */
        public NumberPatternBuilder percent() {
            this.ratioPattern = new RatioPattern().ofPercent();
            return this;
        }

        /**
         * 指定这是一个千分比结果，结果会自动乘以1000<br/>
         * 多次设置仅以最后一次生效；百分比和千分比只能一个生效
         *
         * @return 数值模式构建器
         */
        public NumberPatternBuilder perMille() {
            this.ratioPattern = new RatioPattern().ofPerMille();
            return this;
        }

        /**
         * 设置一个比例模式
         *
         * @param pattern 比例模式对象
         * @return 数值模式构建器
         */
        public NumberPatternBuilder ratio(RatioPattern pattern) {
            this.ratioPattern = pattern;
            return this;
        }

        /**
         * 在开头添加一个本地货币符号
         *
         * @return 数值模式构建器
         */
        public NumberPatternBuilder currency() {
            this.currencyPattern = new CurrencyPattern().showTailEnd(false).ofInternational(false);
            return this;
        }

        /**
         * 在开头添加一个国际货币符号
         *
         * @return 数值模式构建器
         */
        public NumberPatternBuilder intlCurrency() {
            this.currencyPattern = new CurrencyPattern().showTailEnd(false).ofInternational(true);
            return this;
        }

        /**
         * 设置一个货币模式串
         *
         * @param pattern 货币模式对象
         * @return 数值模式构建器
         */
        public NumberPatternBuilder currency(CurrencyPattern pattern) {
            this.currencyPattern = pattern;
            return this;
        }

    }

    /**
     * 日期时间模式消息构建器<br/>
     * <table border="1" >
     * 	<caption>日期时间格式字符解释</caption>
     * 	<tr>
     * 			<th>符号</th>
     * 			<th>说明</th>
     * 			<th>示例</th>
     * 	</tr>
     * 	<tr>
     * 			<td align="center">G</td>
     * 			<td>年代标识符，次数不限</td>
     * 			<td>G - 公元</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">y</td>
     * 		<td>年份标识符。yy被默认格式化为年份的末尾两位。</td>
     * 		<td>y - 2019<br>yy - 19<br>yyy - 2019<br>yyyy - 2019<br>yyyyy - 02019</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">Y</td>
     * 		<td>周年标识符，用法同y。<br>区别在于描述的是日历所在周表述的年份，例如2019年12月29日（周日）的周年份为2020年，因为它是2020年1月1日所在周的开始。</td>
     * 		<td>Y - 2019<br>YY - 19<br>YYY - 2019<br>YYYY - 2019<br>YYYYY - 02019</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">M</td>
     * 		<td>月份标识符。MMM被默认格式化为本地语言对应的月份文本</td>
     * 		<td>M - 3<br>MM - 03<br>MMM - 三月</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">w</td>
     * 		<td>一年中的第几个周标识符，取值范围：1-52。例如2019年6月27日是本年的第26周</td>
     * 		<td>w - 26<br>ww - 03<br>www - 026</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">W</td>
     * 		<td>一月中第几个周标识符，取值范围：1-5。例如2019年6月27日是该月的第5周。</td>
     * 		<td>W - 5<br>WW - 05<br>WWW - 005</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">d</td>
     * 		<td>日/天标识符，取值范围：1-31。</td>
     * 		<td>d - 27<br>dd - 05<br>ddd - 027</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">D</td>
     * 		<td>一年中的第几天标识符，取值范围：1-366。例如2019年6月27日是该年的第178天。</td>
     * 		<td>d - 178<br>dddd - 0178</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">F</td>
     * 		<td>一个月中第几个周几的标识符，取值范围：1-5。描述当前周几在本月中出现的次数，例如2019年6月29日在6月周六在本月中出现的次数为第5次</td>
     * 		<td>F - 5<br>FF - 05</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">E</td>
     * 		<td>周几标识符。描述当前日期对应的星期几。EEEE被默认格式化为本地语言对应的周几文本</td>
     * 		<td>E - 星期六/Sat<br>EEEE -星期六/Saturday</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">u</td>
     * 		<td>一周中的第几天标识符，取值范围：1（周一）-7（周日）。</td>
     * 		<td>u - 6<br>uu - 06</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">a</td>
     * 		<td>上午下午标识符</td>
     * 		<td>a - 上午/AM<br>aa - 上午/AM</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">h</td>
     * 		<td>12小时制的小时数标识符，取值范围：0-11，最好结合"a"使用</td>
     * 		<td>h - 1<br>hh - 01</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">H</td>
     * 		<td>24小时制的小时数标识符，取值范围：0-23。</td>
     * 		<td>H - 5<br>HH - 05<br>HHH - 005</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">k</td>
     * 		<td>12小时进制中的当天第几个小时标识符，取值范围：1-12</td>
     * 		<td>k - 2<br>kk - 02</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">K</td>
     * 		<td>24小时进制中的当天第几个小时标识符，取值范围：1-24。</td>
     * 		<td>K - 13<br>KK - 13<br>KKK - 013</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">m</td>
     * 		<td>小时中的分钟数标识符，取值范围：0-59。</td>
     * 		<td>m - 34<br>mm - 34<br>mmm - 034</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">s</td>
     * 		<td>分钟中的秒数标识符，取值范围：0-59</td>
     * 		<td>s - 29<br>ss - 29<br>sss - 029</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">S</td>
     * 		<td>秒中的毫秒数标识符，取值范围：0-999</td>
     * 		<td>S - 132<br>SSS - 132<br>SSS - 0132</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">z</td>
     * 		<td>通用时区标识符，zzzz表示默认格式为本地语言所在的时区标识文本</td>
     * 		<td>z - GST<br>zzzz - 中国标准时间</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">Z</td>
     * 		<td>RFC 822时区标识符，描述的是GMT偏差的小时数和分钟数</td>
     * 		<td>Z - +0800</td>
     * 	</tr>
     * 	<tr>
     * 		<td align="center">X</td>
     * 		<td>ISO 8601时区标识符，长度必须小于4，描述的是GMT偏差的小时数和分钟数。X表示偏差的小时数；XX-表示偏差的小时数和分钟数；XXX-表示偏差的分钟数和小时数分开</td>
     * 		<td>X - +08<br>XX - 0800<br>XXX - +08:00</td>
     * 	</tr>
     * </table>
     */
    protected abstract static class DateTimePatternBuilder<B extends DateTimePatternBuilder> extends Builder {

        private static final char ERA_SYMBOL = 'G';
        private static final char YEAR_SYMBOL = 'y';
        private static final char WEEK_YEAR_SYMBOL = 'Y';
        private static final char MONTH_IN_YEAR_SYMBOL = 'M';
        private static final char WEEK_IN_YEAR_SYMBOL = 'w';
        private static final char WEEK_IN_MONTH_SYMBOL = 'W';
        private static final char DAY_IN_YEAR_SYMBOL = 'D';
        private static final char DAY_IN_MONTH_SYMBOL = 'd';
        private static final char DAY_OF_WEEK_IN_MONTH_SYMBOL = 'F';
        private static final char DAY_NAME_IN_WEEK_SYMBOL = 'E';
        private static final char DAY_NUMBER_OF_WEEK_SYMBOL = 'u';
        private static final char AM_PM_SYMBOL = 'a';
        private static final char HOUR_IN_DAY_0_23_SYMBOL = 'H';
        private static final char HOUR_IN_DAY_1_24_SYMBOL = 'h';
        private static final char HOUR_IN_DAY_0_11_SYMBOL = 'K';
        private static final char HOUR_IN_DAY_1_12_SYMBOL = 'h';
        private static final char MINUTE_IN_HOUR_SYMBOL = 'm';
        private static final char SECOND_IN_MINUTE_SYMBOL = 's';
        private static final char MILLISECOND_SYMBOL = 'S';
        private static final char TIME_ZONE_GMT_SYMBOL = 'z';
        private static final char TIME_ZONE_RFC_SYMBOL = 'Z';
        private static final char TIME_ZONE_ISO_SYMBOL = 'X';

        /**
         * 日期时间模式字符串
         */
        protected StringBuilder sb;

        /**
         * 实例化一个指定格式的日期时间模式构建器
         *
         * @param formatType    格式模式类型
         * @param argumentIndex 参数索引，从0开始计数
         */
        protected DateTimePatternBuilder(String formatType, int argumentIndex) {
            super(formatType, argumentIndex);
            this.sb = new StringBuilder();
        }

        /**
         * 自身函数模拟器
         *
         * @return 对象自身
         */
        protected abstract B self();

        @Override
        protected String getFormatStyle() {
            if (this.sb != null) {
                return this.sb.toString();
            }
            return null;
        }

        /**
         * 设置一个间隔字符串内容
         *
         * @param spacing 间隔字符串内容
         * @return 当前日期时间模式串构建器
         */
        public B spacing(String spacing) {
            this.sb.append(spacing);
            return this.self();
        }

        /**
         * 设置一个间隔字符内容
         *
         * @param spacing 间隔字符内容
         * @return 当前日期时间模式串构建器
         */
        public B spacing(char spacing) {
            this.sb.append(spacing);
            return this.self();
        }

        /**
         * 添加一个一位的年代纪元描述符号
         *
         * @return 当前日期时间模式串构建器
         * @see #era(int)
         */
        public B era() {
            return this.era(1);
        }

        /**
         * 添加一个指定位数的年代纪元描述符号
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B era(int figure) {
            PatternMessage.repeatAppend(this.sb, ERA_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个四位的年份格式
         *
         * @return 当前日期时间模式串构建器
         * @see #year(int)
         */
        public B year() {
            return this.year(4);
        }

        /**
         * 添加一个指定位数的年份格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B year(int figure) {
            PatternMessage.repeatAppend(this.sb, YEAR_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个两位的月份格式
         *
         * @return 当前日期时间模式串构建器
         * @see #month(int)
         */
        public B month() {
            return this.month(2);
        }

        /**
         * 添加一个指定位数的月份格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B month(int figure) {
            PatternMessage.repeatAppend(this.sb, MONTH_IN_YEAR_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个一位的（某年中的第几周）格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #weekInYear(int)
         */
        public B weekInYear() {
            return this.weekInYear(1);
        }

        /**
         * 添加一个指定位数的（某年中的第几周）格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B weekInYear(int figure) {
            PatternMessage.repeatAppend(this.sb, WEEK_IN_YEAR_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个一位的（某月中的第几周）格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #weekInMonth(int)
         */
        public B weekInMonth() {
            return this.weekInMonth(1);
        }

        /**
         * 添加一个指定位数的（某月中的第几周）格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B weekInMonth(int figure) {
            PatternMessage.repeatAppend(this.sb, WEEK_IN_MONTH_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个一位的（某年中的第几天）格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #dayInYear(int)
         */
        public B dayInYear() {
            return this.dayInYear(1);
        }

        /**
         * 添加一个指定位数的（某年中的第几天）格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B dayInYear(int figure) {
            PatternMessage.repeatAppend(this.sb, DAY_IN_YEAR_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个两位的（某月中的第几天）格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #dayInMonth(int)
         */
        public B dayInMonth() {
            return this.dayInMonth(2);
        }

        /**
         * 添加一个指定位数的（某月中的第几天）格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B dayInMonth(int figure) {
            PatternMessage.repeatAppend(this.sb, DAY_IN_MONTH_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个一位的（某月中的当天所对应周几出现的次数）格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #dayOfWeekInMonth(int)
         */
        public B dayOfWeekInMonth() {
            return this.dayOfWeekInMonth(1);
        }

        /**
         * 添加一个指定位数的（某月中的当天所对应周几出现的次数）格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B dayOfWeekInMonth(int figure) {
            PatternMessage.repeatAppend(this.sb, DAY_OF_WEEK_IN_MONTH_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个全名称的（当天对应周几名称）格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #dayNameInWeek(int)
         */
        public B dayNameInWeek() {
            return this.dayNameInWeek(4);
        }

        /**
         * 添加一个指定位数的（当天对应周几名称）格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B dayNameInWeek(int figure) {
            PatternMessage.repeatAppend(this.sb, DAY_NAME_IN_WEEK_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个一位的（当天对应某周中的第几天，从1开始）格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #dayNumberOfWeek(int)
         */
        public B dayNumberOfWeek() {
            return this.dayNameInWeek(1);
        }

        /**
         * 添加一个指定位数的（当天对应某周中的第几天，从1开始）格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B dayNumberOfWeek(int figure) {
            PatternMessage.repeatAppend(this.sb, DAY_NUMBER_OF_WEEK_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个一位的上午下午标识格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #am_pm(int)
         */
        public B am_pm() {
            return this.am_pm(1);
        }

        /**
         * 添加一个指定位数的上午下午标识格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B am_pm(int figure) {
            PatternMessage.repeatAppend(this.sb, AM_PM_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个两位的24小时制小时值（取值：0-23）格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #hour_0_23(int)
         */
        public B hour_0_23() {
            return this.hour_0_23(2);
        }

        /**
         * 添加一个指定位数的24小时制小时值（取值：0-23）格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B hour_0_23(int figure) {
            PatternMessage.repeatAppend(this.sb, HOUR_IN_DAY_0_23_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个两位的24小时制小时值（取值：1-24）格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #hour_1_24(int)
         */
        public B hour_1_24() {
            return this.hour_1_24(2);
        }

        /**
         * 添加一个指定位数的24小时制小时值（取值：1-24）格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B hour_1_24(int figure) {
            PatternMessage.repeatAppend(this.sb, HOUR_IN_DAY_1_24_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个两位的12小时制小时值（取值：0-11）格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #hour_0_11(int)
         */
        public B hour_0_11() {
            return this.hour_0_11(2);
        }

        /**
         * 添加一个指定位数的12小时制小时值（取值：0-11）格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B hour_0_11(int figure) {
            PatternMessage.repeatAppend(this.sb, HOUR_IN_DAY_0_11_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个两位的12小时制小时值（取值：1-12）格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #hour_1_12(int)
         */
        public B hour_1_12() {
            return this.hour_1_12(2);
        }

        /**
         * 添加一个指定位数的12小时制小时值（取值：1-12）格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B hour_1_12(int figure) {
            PatternMessage.repeatAppend(this.sb, HOUR_IN_DAY_1_12_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个两位的分钟值格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #minute(int)
         */
        public B minute() {
            return this.minute(2);
        }

        /**
         * 添加一个指定位数的分钟值格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B minute(int figure) {
            PatternMessage.repeatAppend(this.sb, MINUTE_IN_HOUR_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个两位的秒值格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #second(int)
         */
        public B second() {
            return this.second(2);
        }

        /**
         * 添加一个指定位数的秒值格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B second(int figure) {
            PatternMessage.repeatAppend(this.sb, SECOND_IN_MINUTE_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个两位的毫秒值格式，如果位数不足则自动填充0
         *
         * @return 当前日期时间模式串构建器
         * @see #millisecond(int)
         */
        public B millisecond() {
            return this.millisecond(3);
        }

        /**
         * 添加一个指定位数的毫秒值格式，如果位数不足则自动填充0
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B millisecond(int figure) {
            PatternMessage.repeatAppend(this.sb, MILLISECOND_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个一位的标准时区标识格式
         *
         * @return 当前日期时间模式串构建器
         * @see #timezone(int)
         */
        public B timezone() {
            return this.timezone(1);
        }

        /**
         * 添加一个指定位数的标准时区标识格式
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B timezone(int figure) {
            PatternMessage.repeatAppend(this.sb, TIME_ZONE_GMT_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个一位的RFC时区标识格式
         *
         * @return 当前日期时间模式串构建器
         * @see #rfcTimezone(int)
         */
        public B rfcTimezone() {
            return this.rfcTimezone(1);
        }

        /**
         * 添加一个指定位数的RFC时区标识格式
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B rfcTimezone(int figure) {
            PatternMessage.repeatAppend(this.sb, TIME_ZONE_RFC_SYMBOL, figure);
            return this.self();
        }

        /**
         * 添加一个一位的RFC时区标识格式
         *
         * @return 当前日期时间模式串构建器
         * @see #isoTimezone(int)
         */
        public B isoTimezone() {
            return this.isoTimezone(1);
        }

        /**
         * 添加一个指定位数的RFC时区标识格式，位数不能大于3
         *
         * @param figure 位数
         * @return 当前日期时间模式串构建器
         */
        public B isoTimezone(int figure) {
            if (figure > 3) {
                throw new IllegalArgumentException("iso timezone symbol 'X' must be less then 4");
            }
            PatternMessage.repeatAppend(this.sb, TIME_ZONE_ISO_SYMBOL, figure);
            return this.self();
        }

    }

    /**
     * 日期模式构建器
     */
    public static class DatePatternBuilder extends DateTimePatternBuilder<DatePatternBuilder> {


        /**
         * 创建一个日期模式字符串构建器
         *
         * @param argumentIndex 参数索引，从0开始计数
         */
        public DatePatternBuilder(int argumentIndex) {
            super("date", argumentIndex);
        }

        @Override
        protected DatePatternBuilder self() {
            return this;
        }

    }

    /**
     * 日期模式消息构建器
     */
    public static class TimePatternBuilder extends DateTimePatternBuilder<TimePatternBuilder> {

        /**
         * 创建一个时间模式字符串构建器
         *
         * @param argumentIndex 参数索引，从0开始计数
         */
        public TimePatternBuilder(int argumentIndex) {
            super("time", argumentIndex);
        }

        @Override
        protected TimePatternBuilder self() {
            return this;
        }
    }

}
