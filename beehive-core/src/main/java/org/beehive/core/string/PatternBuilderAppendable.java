/*
 * Copyright (c) 2019-2023 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.FormatAppendable
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2023-03-08
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string;

import org.beehive.core.string.pattern.PatternPlaceholder;

/**
 * 可追加的格式对象，实现了{@link Appendable}的基础方法定义
 * <br>
 * 这是一个抽象实现，内部使用{@link StringBuilder}进行格式支化串的支持和实现。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.string.format</code></li>
 *   <li>Class Name: <code>FormatAppendable</code></li>
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
 *         <td align="center"><em>2023-03-08</em></td>
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
public abstract class PatternBuilderAppendable<T extends PatternBuilderAppendable<T, F>, F extends PatternPlaceholder> implements Appendable {

    /**
     * 内部字符串构建器
     */
    protected StringBuilder sb;

    /**
     * 自身对象模拟函数
     *
     * @return 当前对象本身
     */
    protected abstract T self();

    @Override
    public T append(CharSequence csq) {
        this.sb.append(csq);
        return this.self();
    }

    /**
     * 将指定的字符序列的固定片段追加到原字符串末尾
     *
     * @param csq   字符串序列
     * @param start 开始位置，从0开始，包含该位置
     * @param end   结束位置，从0开始，包含该位置
     * @return 当前对象
     * @see Appendable#append(CharSequence, int, int)
     */
    @Override
    public T append(CharSequence csq, int start, int end) {
        this.sb.append(csq, start, end);
        return this.self();
    }

    @Override
    public T append(char c) {
        this.sb.append(c);
        return this.self();
    }

    /**
     * 在当前字符串的末尾追加一个格式化字符串
     *
     * @param formatStr 格式字符串
     * @return 当前字符串构建器
     */
    public T append(F formatStr) {
        this.sb.append(formatStr.placeholder());
        return this.self();
    }

    /**
     * 在当前字符串的开头插入一个格式化字符串
     *
     * @param formatStr 格式字符串
     * @return 当前字符串构建器
     * @see StringBuilder#insert(int, String)
     */
    public T beginWith(F formatStr) {
        this.sb.insert(0, formatStr.placeholder());
        return this.self();
    }

    /**
     * 在当前字符串的指定位置插入一个格式化字符串
     *
     * @param formatStr 格式字符串
     * @param offset    指定位置
     * @return 当前字符串构建器
     */
    public T insert(int offset, F formatStr) {
        this.sb.insert(offset, formatStr.placeholder());
        return this.self();
    }

    /**
     * 将指定区间的字符串替换为一个格式字符串
     *
     * @param formatStr 格式字符串
     * @param start     指定区间的开始位置，从0开始，包含该位置
     * @param end       指定截止的开始位置，从0开始，包含该位置
     * @return 当前字符串构建器
     */
    public T replace(int start, int end, F formatStr) {
        this.sb.replace(start, end, formatStr.placeholder());
        return this.self();
    }

    @Override
    public String toString() {
        if (this.sb == null) {
            return null;
        }
        return this.sb.toString();
    }
}
