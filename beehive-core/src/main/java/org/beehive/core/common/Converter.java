/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.common;

/**
 * 类型转换接口，用于将一个输入的对象转换为目标类型对象。
 *
 * @param <S> 源对象类型
 * @param <T> 目标对象类型
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public interface Converter<S, T> {

    /**
     * 将源对象转换为目标对象
     *
     * @param source 源对象实例
     * @return 目标对象实例
     * @throws ConvertException
     * @since 1.0
     */
    T convert(S source) throws ConvertException;

    /**
     * 将源对象转换为目标对象，如果转换失败，则返回指定的默认值
     *
     * @param source       源对象实例
     * @param defaultValue 如果转换失败，则返回默认值
     * @return 目标对象实例
     * @since 1.0
     */
    default T convert(S source, T defaultValue) {
        try {
            return this.convert(source);
        } catch (ConvertException e) {
            return defaultValue;
        }
    }

}
