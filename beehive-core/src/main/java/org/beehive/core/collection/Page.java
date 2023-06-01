/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.collection.Page
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-21
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.collection;

import java.util.List;

/**
 * 分页页面对象定义。描述一个分页的页面，内部包含分页的展示元素（页码、页面大小、总条数、总页数、页面数据）等信息。
 * <br>
 * 这个对象只是一个简单的值对象。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.collection</code></li>
 * <li>Class Name: <code>Page</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1" cellspacing="0" cellpadding="0" summary=""Upgrade&shy;Modify History>
 * <tr>
 * <th>Version</th>
 * <th>Environment</th>
 * <th>ModifyTime</th>
 * <th>Modifier</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td align="center"><em>1.0</em></td>
 * <td align="center"><em>Java 8</em></td>
 * <td align="center"><em>2020/9/21</em></td>
 * <td align="center"><em>akudy</em></td>
 * <td><em>Define</em></td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 *
 * @param <E> 页面元素类型
 * @author <a href="mailto:akudys@163.com">akudy</a>
 * @version 1.0
 * @since 1.0
 */
public class Page<E> {

    /**
     * 当前页码，一个正整数值
     */
    private int pageNo;

    /**
     * 每页数量大小，一个正整数值
     */
    private int pageSize;

    /**
     * 待分页的源数据总条数
     */
    private int totalCount;

    /**
     * 总页数，一个正整数值
     */
    private int pageCount;

    /**
     * 页面数据列表
     */
    private List<? extends E> pageData;

    /**
     * 不允许外部直接构建分页对象
     *
     * @param pageNo     页码
     * @param pageSize   页面大小
     * @param totalCount 页面总数
     * @param pageCount  总页数
     * @param list       页面列表
     */
    Page(int pageNo, int pageSize, int totalCount, int pageCount, List<? extends E> list) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.pageCount = pageCount;
        this.pageData = list;
    }

    /**
     * 创建一个外部分页对象，此时将该对象作为一个值对象进行使用。
     *
     * @param pageNo     页码
     * @param pageSize   页面大小
     * @param totalCount 页面总数
     * @param pageCount  总页数
     * @param list       页面列表
     * @param <T>        页面元素类型
     * @return 页面对象
     */
    public static <T> Page<T> newCustomPage(int pageNo, int pageSize, int totalCount, int pageCount, List<T> list) {
        return new Page<>(pageNo, pageSize, totalCount, pageCount, list);
    }

    /**
     * 获取当前页码，一个正整数值
     *
     * @return pageNo - 当前页码，一个正整数值
     */
    public int getPageNo() {
        return this.pageNo;
    }

    /**
     * 获取每页数量大小，一个正整数值
     *
     * @return pageSize - 每页数量大小，一个正整数值
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 获取待分页的源数据总条数
     *
     * @return totalCount - 待分页的源数据总条数
     */
    public int getTotalCount() {
        return this.totalCount;
    }

    /**
     * 获取总页数，一个正整数值
     *
     * @return pageCount - 总页数，一个正整数值
     */
    public int getPageCount() {
        return this.pageCount;
    }

    /**
     * 获取页面数据列表
     *
     * @return pageData - 页面数据列表
     */
    public List<? extends E> getPageData() {
        return this.pageData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("pageNo = ").append(this.getPageNo()).append(", ");
        sb.append("pageSize = ").append(this.getPageSize()).append(", ");
        sb.append("pageCount = ").append(this.getPageCount()).append(", ");
        sb.append("totalCount = ").append(this.getTotalCount()).append(", ");
        sb.append("pageData = ").append(this.getPageData());
        sb.append("}");
        return sb.toString();
    }
}
