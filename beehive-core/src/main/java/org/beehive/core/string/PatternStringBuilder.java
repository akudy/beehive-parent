/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.string;

import org.beehive.core.string.pattern.PatternString;

/**
 * 格式字符串构建器，用于创建一个带有格式化符号的字符串模板。
 * <br>
 * 这个构建器是对格式字符串的构建，格式字符串来源于{@link java.util.Formatter}的定义。<br/>
 * 这里是将格式字符串的构建进行了便捷的创建封装；往往我们比较难以记住复杂的格式占位符和使用规则，通过构造器我们可以快速的构建器格式字符串的内容
 *
 * @author akudy
 * @version 1.0
 * @see java.util.Formatter
 * @see String#format(String, Object...)
 * @since 1.0
 */
public class PatternStringBuilder extends PatternBuilderAppendable<PatternStringBuilder, PatternString> {

    /**
     * 实例化一个格式字符串构建器
     */
    public PatternStringBuilder() {
        this.sb = new StringBuilder();
    }

    /**
     * 通过开始字符实例化一个格式字符串构建器，开始字符将作为该字符串的起始内容
     *
     * @param beginWithStr 开始字符串
     */
    public PatternStringBuilder(String beginWithStr) {
        this.sb = new StringBuilder(beginWithStr);
    }

    @Override
    protected PatternStringBuilder _self() {
        return this;
    }

}
