/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.charset1.test;

/**
 * 字符标签
 * <br>
 * 定义字符的常用标签和分类
 *
 * @author akudy
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
