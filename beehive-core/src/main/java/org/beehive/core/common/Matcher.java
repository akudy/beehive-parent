/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.common;

/**
 * 匹配器功能接口定义。
 * <br>
 * 用于检测源对象（值）和目标对象（值）是否匹配。
 *
 * @param <T> 源对象（值）类型定义
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public interface Matcher<T> {

    /**
     * 匹配器接受检查的对象，如果检查的对象符合匹配器的要求和规则，则返回true，否则返回false。
     * 匹配规则：规则具体由实现类提供。
     *
     * @param obj 受检查的对象
     * @return 如果完全匹配则返回true，否则返回false
     */
    boolean accept(T obj);

}
