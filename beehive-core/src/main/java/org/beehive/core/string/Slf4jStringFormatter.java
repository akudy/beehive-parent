/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.string;

import java.util.HashSet;

/**
 * 提供类似slf4j的信息格式化实现方式。
 * <br>
 * 参考{@link org.slf4j.helpers.MessageFormatter}类的核心方法进行实现。
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class Slf4jStringFormatter extends StringFormatter {

    /**
     * 约定占位符号的开始符号
     */
    private static final char DELIMITER_START = '{';

    /**
     * 约定占位符号的结束符号
     */
    private static final char DELIMITER_END = '}';

    /**
     * 约定占位符号字符串
     */
    private static final String DELIMITER_STR = "{}";

    /**
     * 针对占位符号输出的转义符号
     */
    private static final char ESCAPE_CHAR = '\\';

    @Override
    public String format(String strTemplate, Object... parameters) {
        return this.format0(strTemplate, parameters);
    }

    /**
     * 将格式字符串，按照参数的顺序，进行重新拼接组装
     *
     * @param strTemplate 格式字符串
     * @param parameters  参数列表
     * @return 拼装后的字符串
     */
    private String format0(String strTemplate, Object... parameters) {
        if (strTemplate == null) {
            return null;
        }
        if (parameters == null) {
            return strTemplate;
        }

        // 下一个开始查找字符的位置
        int nextLookupStartIndex = 0;
        //占位符的位置
        int delimiterIndex;
        StringBuilder strBuilder = new StringBuilder(strTemplate.length() + 50);
        for (int paramIndex = 0, length = parameters.length; paramIndex < length; paramIndex++) {
            // 从当前字符串指定的位置开始向后查找占位字符串的首次出现位置
            delimiterIndex = strTemplate.indexOf(DELIMITER_STR, nextLookupStartIndex);
            // 如果没有占位符出现
            if (delimiterIndex == -1) {
                if (nextLookupStartIndex == 0) {
                    // 如果占位符出现次数为0，则视为一个非格式字符串
                    return strTemplate;
                } else {
                    strBuilder.append(strTemplate, nextLookupStartIndex, strTemplate.length());
                    return strTemplate;
                }
            } else {
                if (isSingleEscaped(strTemplate, delimiterIndex)) {
                    // 如果存在单个转义字符，则对转义字符串进行特殊处理
                    if (!isDoubleEscaped(strTemplate, delimiterIndex)) {
                        paramIndex--;
                        // 向前推移一个字符
                        strBuilder.append(strTemplate, nextLookupStartIndex, delimiterIndex - 1);
                        strBuilder.append(DELIMITER_START);
                        nextLookupStartIndex = delimiterIndex + 1;
                    } else {
                        strBuilder.append(strTemplate, nextLookupStartIndex, delimiterIndex - 1);
                        this.deeplyAppendParameter(strBuilder, parameters[paramIndex], new HashSet<Object[]>());
                        nextLookupStartIndex = delimiterIndex + 2;
                    }
                } else {
                    strBuilder.append(strTemplate, nextLookupStartIndex, delimiterIndex);
                    this.deeplyAppendParameter(strBuilder, parameters[paramIndex], new HashSet<>());
                    nextLookupStartIndex = delimiterIndex + 2;
                }
            }
        }
        strBuilder.append(strTemplate, nextLookupStartIndex, strTemplate.length());
        return strBuilder.toString();
    }

    /**
     * 判断字符串模板中制定位置的是否存在单转义字符串
     *
     * @param strTemplate 字符串模板
     * @param index       指定开始位置
     * @return 如果位置不等于0，并且向前推移1个字符为占位字符，则返回true，否则返回false
     */
    private static boolean isSingleEscaped(String strTemplate, int index) {
        if (index > 0 && strTemplate.charAt(index - 1) == ESCAPE_CHAR) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串模板中制定位置的是否存在双转义字符串
     *
     * @param strTemplate 字符串模板
     * @param index       指定开始位置
     * @return 如果位置大于2并且向前推移2个字符为占位字符，则返回true，否则返回false
     */
    private static boolean isDoubleEscaped(String strTemplate, int index) {
        if (index >= 2 && strTemplate.charAt(index - 2) == ESCAPE_CHAR) {
            return true;
        } else {
            return false;
        }
    }


}
