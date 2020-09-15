/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.collection.PageList
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-14
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页列表对象，扩展自{@link ArrayList}。针对{@link ArrayList}扩展了分页的元素信息。
 * <br>
 * 分页元素信息包含：页面数据、总数、当前页码、每页展示数量。当前的实现类与{@link ArrayList}耦合性较高。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.collection</code></li>
 * <li>Class Name: <code>PageList</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1" cellspacing="0" cellpadding="0" summary=""Upgrade/Modify History>
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
 * <td align="center"><em>2020/9/11</em></td>
 * <td align="center"><em>akudy</em></td>
 * <td><em>Define</em></td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @since 1.0
 */
public class PageList<E> {

    /**
     * 页面编号
     */
    private int pageNo;

    /**
     * 页面数据流大小
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总数据量大小
     */
    private int totalSize;

    /**
     * 页面数据量
     */
    private List<E> pageData;

    /**
     * 对列表进行分页
     *
     * @param pageNo 第几页
     * @param list
     * @return
     */
    public final PageList<E> paging(int pageNo, List<E> list) {
        this.doPage(list, pageNo, this.pageSize);
        return this;
    }

    protected void doPage(List<E> list, int pageNo, int pageSize) {
        if (list == null) {
            list = new ArrayList<>();
        }
        int size = list.size();
        this.pageNo = pageNo < 0 ? 0 : pageNo;
        this.pageSize = pageSize < 0 ? size : pageSize;
        this.totalPage = (size % pageSize == 0) ? size / pageSize : size / pageSize + 1;
        this.totalSize = size;
        int startIndex = 0;
        int endIndex = size;
        if (pageNo > 0 && pageSize > 0) {
            startIndex = pageSize * (pageNo - 1);
            endIndex = pageSize * pageNo;
            startIndex = startIndex > size ? size : startIndex;
            endIndex = endIndex > size ? size : endIndex;
        }
        this.pageData = list.subList(startIndex, endIndex);
    }

    /**
     * 实例化一个默认页面列表对象
     */
    public PageList() {
        this.pageSize = 10;
    }

    /**
     * 使用指定的页面大小和页码实例化一个页面列表对象
     *
     * @param pageSize 页面大小
     */
    public PageList(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取页面数据流大小
     *
     * @return pageSize - 页面数据流大小
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 获取页面编号
     *
     * @return pageNo - 页面编号
     */
    public int getPageNo() {
        return this.pageNo;
    }

    /**
     * 获取总页数
     *
     * @return totalPage - 总页数
     */
    public int getTotalPage() {
        return this.totalPage;
    }

    /**
     * 获取总数据量大小
     *
     * @return totalSize - 总数据量大小
     */
    public int getTotalSize() {
        return this.totalSize;
    }

    /**
     * 获取页面数据量
     *
     * @return pageData - 页面数据量
     */
    public List<E> getPageData() {
        return this.pageData;
    }

}
