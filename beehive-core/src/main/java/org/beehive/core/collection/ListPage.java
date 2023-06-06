/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.collection;

import java.util.List;

/**
 * 一个列表分页对象实现
 *
 * @param <E> 被分页的列表元素类型
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class ListPage<E> extends AbstractListPage<E> {

    /**
     * 使用源数据列表构建一个列表分页对象
     *
     * @param sourceData 源数据列表
     */
    public ListPage(List<E> sourceData) {
        super(sourceData);
    }

    /**
     * 使用源数据列表和每页展示数量构建一个列表分页对象
     *
     * @param sourceData 源数据列表
     * @param pageSize   每页展示大小
     */
    public ListPage(List<E> sourceData, int pageSize) {
        super(sourceData, pageSize);
    }

}
