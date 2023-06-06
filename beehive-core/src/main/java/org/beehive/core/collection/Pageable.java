/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.collection;

/**
 * 描述一个可分页的对象接口定义。包含：总页数，每页大小；分页遍历等信息。
 * <br>
 * 可分页的对象可以被迭代。集成了{@link Iterable}接口
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public interface Pageable {

    /**
     * 默认页面大小
     */
    int DEFAULT_PAGE_SIZE = 10;

    /**
     * 获取总页数，一个正整数值
     *
     * @return 总页数
     */
    default int getPageCount() {
        int pageSize = this.getPageSize() < 0 ? DEFAULT_PAGE_SIZE : this.getPageSize();
        int count = this.getTotalCount() % pageSize == 0 ? this.getTotalCount() / pageSize : this.getTotalCount() / pageSize + 1;
        return count;
    }

    /**
     * 获取每页数量大小，一个正整数值
     *
     * @return 每页数量大小
     */
    int getPageSize();

    /**
     * 获取待分页的数据总量，一个正整数值
     *
     * @return 需要分页的数据总量
     */
    int getTotalCount();

}
