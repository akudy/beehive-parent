/*
 * Copyright (c) 2019-2021 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.charset.CharacterTag
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2021-03-02
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.charset1.test;

/**
 * 字符标签
 * <br>
 * 定义字符的常用标签和分类
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.charset</code></li>
 *   <li>Class Name: <code>CharacterTag</code></li>
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
 *         <td align="center"><em>2021/3/2</em></td>
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
public enum CharacterTag {

    /**
     * 控制符号类
     */
    CONTROL,
    /**
     * 标点符号类
     */
    PUNCTUATION,
    /**
     * 数字符号类
     */
    DIGIT,
    /**
     * 字母符号类
     */
    LETTER,
    /**
     * 文字符号
     */
    LITERAL,
    /**
     * 数学符号类
     */
    MATH,
    /**
     * 数学分数符号类
     */
    MATH_FRACTION,
    /**
     * 特殊符号类
     */
    SPECIAL,
    /**
     * 元音符号类
     */
    VOWEL,
    /**
     * 语音字母
     */
    PHONETIC,
    /**
     * 修饰符
     */
    MODIFIER,
    /**
     * 常用符号类
     */
    COMMON


}
