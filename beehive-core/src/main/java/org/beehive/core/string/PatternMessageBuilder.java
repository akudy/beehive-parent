/*
 * Copyright (c) 2019-2022 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.FormatMessageBuilder
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2022-07-07
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string;

import org.beehive.core.string.pattern.PatternMessage;

/**
 * 格式文本消息构建器，用户创建一个带有格式化符号的字符串模板。
 * <br>
 * 这个构建器是对格式文本消息的构建，格式字符串来源于{@link java.text.MessageFormat}的定义。<br/>
 * 这里是将格式文本消息的构建进行了便捷的创建封装；往往我们比较难以记住复杂的格式占位符和使用规则，通过构造器我们可以快速的构建器格式文本消息的内容
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.string</code></li>
 *   <li>Class Name: <code>FormatMessageBuilder</code></li>
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
 * @author <a href="mailto:akudys@163.com">akudy</a>
 * @version 1.0
 * @since 1.0
 */
public class PatternMessageBuilder extends PatternBuilderAppendable<PatternMessageBuilder, PatternMessage> {

    /**
     * 实例化一个格式字符串构建器
     */
    public PatternMessageBuilder() {
        this.sb = new StringBuilder();
    }

    /**
     * 通过开始字符实例化一个格式字符串构建器，开始字符将作为该字符串的起始内容
     *
     * @param beginWithStr 开始字符串
     */
    public PatternMessageBuilder(String beginWithStr) {
        this.sb = new StringBuilder(beginWithStr);
    }

    @Override
    protected PatternMessageBuilder _self() {
        return this;
    }

    /**
     * 在开头插入一个左括号("{")
     *
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder beginWithLeftBrace() {
        this.sb.insert(0, "'{");
        return this;
    }

    /**
     * 在开头插入一个右括号("}")
     *
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder beginWithRightBrace() {
        this.sb.insert(0, "'}");
        return this;
    }

    /**
     * 在开头插入一对括号("{}")
     *
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder beginWithBrace() {
        this.sb.insert(0, "'{'}");
        return this;
    }

    /**
     * 在开头插入一个单引号("'")
     *
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder beginWithSingleQuote() {
        this.sb.insert(0, "''");
        return this;
    }

    /**
     * 在开头插入一对单引号("''")
     *
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder beginWithSingleQuotes() {
        this.sb.insert(0, "''''");
        return this;
    }

    /**
     * 在末尾追加一个左括号("{")
     *
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder appendLeftBrace() {
        this.sb.append("'{");
        return this;
    }

    /**
     * 在末尾追加一个右括号("}")
     *
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder appendRightBrace() {
        this.sb.append("'}");
        return this;
    }

    /**
     * 在末尾追加一对括号("{}")
     *
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder appendBrace() {
        this.sb.append("'{'}");
        return this;
    }

    /**
     * 在末尾追加一个单引号("'")
     *
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder appendSingleQuote() {
        this.sb.append("''");
        return this;
    }

    /**
     * 在末尾追加一对单引号("''")
     *
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder appendSingleQuotes() {
        this.sb.append("''''");
        return this;
    }

    /**
     * 在指定位置插入一个左括号("{")
     *
     * @param offset 指定位置
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder insertLeftBrace(int offset) {
        this.sb.insert(offset, "'{");
        return this;
    }

    /**
     * 在指定位置插入一个右括号("}")
     *
     * @param offset 指定位置
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder insertRightBrace(int offset) {
        this.sb.insert(offset, "'}");
        return this;
    }

    /**
     * 在指定位置插入一对括号("{}")
     *
     * @param offset 指定位置
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder insertBrace(int offset) {
        this.sb.insert(offset, "'{'}");
        return this;
    }

    /**
     * 在指定位置插入一个单引号("'")
     *
     * @param offset 指定位置
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder insertSingleQuote(int offset) {
        this.sb.insert(offset, "''");
        return this;
    }

    /**
     * 在指定位置插入一对单引号("''")
     *
     * @param offset 指定位置
     * @return 当前格式消息构造器
     */
    public PatternMessageBuilder insertSingleQuotes(int offset) {
        this.sb.insert(offset, "''''");
        return this;
    }

}
