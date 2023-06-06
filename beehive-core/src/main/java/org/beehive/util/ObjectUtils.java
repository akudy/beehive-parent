/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.util;

/**
 * 对象操作相关工具类定义。
 * <br>
 * 提供对象的常用操作工具方法。
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class ObjectUtils {

    /**
     * 判断给定的实例对象是否是指定类型的实例
     *
     * @param object 对象实例
     * @param clazz  类型
     * @return 如果是指定类型的实例则返回true，否则返回false
     * @since 1.0
     */
    public static boolean instanceOf(Object object, Class<?> clazz) {
        return clazz.isInstance(object);
    }
    

    public static <T> String toString(T instance) {
        return instance.toString();
    }

}
