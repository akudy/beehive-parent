/*
 * Copyright (c) 2019-2022 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.FormatStringBuilder
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2022-07-07
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string;

import org.beehive.core.string.pattern.PatternString;

/**
 * 格式字符串构建器，用于创建一个带有格式化符号的字符串模板。
 * <br>
 * 这个构建器是对格式字符串的构建，格式字符串来源于{@link java.util.Formatter}的定义。<br/>
 * 这里是将格式字符串的构建进行了便捷的创建封装；往往我们比较难以记住复杂的格式占位符和使用规则，通过构造器我们可以快速的构建器格式字符串的内容
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.string</code></li>
 *   <li>Class Name: <code>FormatStringBuilder</code></li>
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
 *         <td align="center"><em>2022-07-07</em></td>
 *         <td align="center"><em>akudy</em></td>
 *         <td><em>Define</em></td>
 *       </tr>
 *     </table>
 *   </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
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
    protected PatternStringBuilder self() {
        return this;
    }

}
