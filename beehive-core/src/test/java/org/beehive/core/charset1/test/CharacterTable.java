/*
 * Copyright (c) 2019-2021 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.charset.CharacterTable
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2021-03-02
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.charset1.test;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

/**
 * 字符表对象
 * <br>
 * 定义一个字符表的开始范围、结束范围和标签分类。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.charset</code></li>
 *   <li>Class Name: <code>CharacterTable</code></li>
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

public class CharacterTable {

    /**
     * 开始位置
     */
    private int start;

    /**
     * 结束位置
     */
    private int end;

    /**
     * 标签
     */
    private EnumSet<CharacterTag> tags;

    CharacterTable(int start, int end, EnumSet<CharacterTag> tags) {
        this.start = start;
        this.end = end;
        this.tags = tags;
    }

    /**
     * 获取开始编码
     *
     * @return 开始编码的整数值
     */
    public int getStart() {
        return this.start;
    }

    /**
     * 获取结束编码
     *
     * @return 结束编码的整数值
     */
    public int getEnd() {
        return this.end;
    }

    /**
     * 获取这段编码的所有标签列表
     *
     * @return 返回标签列表
     */
    public List<CharacterTag> getTags() {
        return Arrays.asList(this.tags.toArray(new CharacterTag[]{}));
    }

    /**
     * 获取字符数量
     *
     * @return 该字符表下的字符数量
     */
    public int count() {
        return (this.end - this.start) + 1;
    }

    /**
     * 判断某个字符是否属于当前字符表
     *
     * @param ch 输入的某个字符
     * @return 如果属于当前字符表，则返回true；否则返回false
     */
    public boolean contains(char ch) {
        int chCode = (int) ch;
        if (this.getStart() <= chCode && this.getEnd() >= chCode) {
            return true;
        }
        return false;
    }

    /**
     * 获取所有的字符
     *
     * @return 该字符表下的所有字符
     */
    public char[] chars() {
        int charCount = this.count();
        int i = 0;
        char[] chars = new char[charCount];
        for (int code = this.getStart(); code <= this.getEnd(); code++) {
            chars[i++] = (char) code;
        }
        return chars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CharacterTable that = (CharacterTable) o;
        return start == that.start &&
                end == that.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

}
