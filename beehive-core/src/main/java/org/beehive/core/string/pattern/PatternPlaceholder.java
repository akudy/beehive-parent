/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.string.pattern;

/**
 * 模式/格式占位符接口定义
 * <br>
 * 提供模式/格式占位符字符串输出方法定义
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public interface PatternPlaceholder {

    /**
     * 获取格式占位符的占位符字符串
     *
     * @return 占位符字符串
     */
    String placeholder();

}
