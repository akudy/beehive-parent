/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.common;

import org.beehive.util.ObjectUtils;

/**
 * 类型转换异常定义。
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class ConvertException extends RuntimeException {

    /**
     * 使用异常消息内容实例化一个转换异常对象
     *
     * @param message 异常消息内容
     */
    public ConvertException(String message) {
        super(message);
    }

    /**
     * 使用异常消息内容和根异常实例化一个转换异常对象
     *
     * @param message 异常消息内容
     * @param cause   根异常原因
     */
    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertException(Class<?> originClass, Class<?> targetClass) {
        this("the original type[" + originClass.getName() + "] cannot be converted to the target type[" + targetClass.getName() + "].");
    }

    public ConvertException(Object origin, Class<?> targetClass) {
        this("the original object[" + ObjectUtils.toString(origin) + "] cannot be converted to the target type[" + targetClass.getName() + "].");
    }

}
