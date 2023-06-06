/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.string;

import org.beehive.core.string.pattern.PatternPlaceholder;

/**
 * 可追加的格式对象，实现了{@link Appendable}的基础方法定义
 * <br>
 * 这是一个抽象实现，内部使用{@link StringBuilder}进行格式支化串的支持和实现。
 *
 * @author akudy
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
    protected abstract T _self();

    @Override
    public T append(CharSequence csq) {
        this.sb.append(csq);
        return this._self();
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
        return this._self();
    }

    @Override
    public T append(char c) {
        this.sb.append(c);
        return this._self();
    }

    /**
     * 在当前字符串的末尾追加一个格式化字符串
     *
     * @param formatStr 格式字符串
     * @return 当前字符串构建器
     */
    public T append(F formatStr) {
        this.sb.append(formatStr.placeholder());
        return this._self();
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
        return this._self();
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
        return this._self();
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
        return this._self();
    }

    @Override
    public String toString() {
        if (this.sb == null) {
            return null;
        }
        return this.sb.toString();
    }
}
