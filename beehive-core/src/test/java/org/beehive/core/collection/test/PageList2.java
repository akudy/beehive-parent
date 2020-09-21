/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.collection.test.PageList2
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-21
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.collection.test;

import org.beehive.util.ArrayUtils;
import org.beehive.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.collection</code></li>
 * <li>Class Name: <code>PageList2</code></li>
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
 * <td align="center"><em>2020/9/16</em></td>
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
public class PageList2<E> {

    /**
     * 默认的页面数量
     */
    private static int DEFAULT_PAGE_SIZE = 10;

    /**
     * 设置默认的分页大小
     *
     * @param pageSize 页面大小
     */
    public static void setDefaultPageSize(int pageSize) {
        DEFAULT_PAGE_SIZE = pageSize;
    }

    /**
     * 页面编号
     */
    private int pageNo;

    /**
     * 页面数据流大小，-1表一个可以判断没有输入的值
     */
    private int pageSize = -1;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总数据量大小
     */
    private int totalSize;

    /**
     * 总数据集合
     */
    private List<E> allData;

    /**
     * 当前页面数据
     */
    private List<E> pageData;

    /**
     * 计算页面数量
     */
    protected void calcPageCount() {
        int size = this.totalSize;
        this.totalPage = (size % this.pageSize == 0) ? size / this.pageSize : (size / this.pageSize) + 1;
    }

    /**
     * 分页处理，设置分页大小；同时计算总页数
     *
     * @return 当前页面对象
     */
    public final PageList2<E> paging() {
        return this.paging(DEFAULT_PAGE_SIZE);
    }

    /**
     * 分页处理，设置分页大小；同时计算总页数
     *
     * @param pageSize 页面大小
     * @return 当前页面对象
     */
    public final PageList2<E> paging(int pageSize) {
        this.pageSize = pageSize < 1 ? this.totalSize : pageSize;
        this.calcPageCount();
        return this;
    }

    /**
     * 获取指定页面的数据列表
     *
     * @param pageNo 页码，从1开始
     * @return 指定页码的页面数据对象
     */
    public final PageList2<E> fetch(int pageNo) {
        if (this.pageSize == -1) {
            this.paging();
        }
        this.pageNo = pageNo < 1 ? 1 : pageNo;
        int startIndex = 0;
        int endIndex = this.totalSize;
        if (pageNo > 0 && this.pageSize > 0) {
            startIndex = this.pageSize * (pageNo - 1);
            endIndex = this.pageSize * pageNo;
            startIndex = startIndex > this.totalSize ? this.totalSize : startIndex;
            endIndex = endIndex > this.totalSize ? this.totalSize : endIndex;
        }
        this.pageData = this.allData.subList(startIndex, endIndex);
        return this;
    }

    /**
     * 获取首页数据
     *
     * @return 首页数据列表对象
     */
    public final PageList2<E> firstPage() {
        return this.fetch(1);
    }

    /**
     * 获取最后一页数据
     *
     * @return 最后一页数据列表对象
     */
    public final PageList2<E> lastPage() {
        return this.fetch(this.totalPage);
    }

    /**
     * 使用一个数据列表来初始化一个分页列表对象
     *
     * @param list 源数据列表对象
     */
    public PageList2(List<E> list) {
        List<E> newList = null;
        if (CollectionUtils.isEmpty(list)) {
            newList = new ArrayList<>(16);
        } else {
            newList = new ArrayList<>(list.size());
            newList.addAll(list);
        }
        this.allData = newList;
        this.totalSize = this.allData.size();
    }

    /**
     * 使用一个数组列表来初始化一个分页列表对象
     *
     * @param array 数组元素列表
     */
    public PageList2(E... array) {
        List<E> newList = null;
        if (ArrayUtils.isEmpty(array)) {
            newList = new ArrayList<>(16);
        } else {
            newList = Arrays.asList(array);
        }
        this.allData = newList;
        this.totalSize = this.allData.size();
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
     * 获取当前页面数据
     *
     * @return pageData - 当前页面数据
     */
    public List<E> getPageData() {
        return this.pageData;
    }

    /**
     * 获取页面总列表数据；这是输入的列表的一个副本，即使输入列表在外部再次改变，分页列表对象包含的数据不会改变。
     *
     * @return allData - 总列表数据
     */
    protected List<E> getAllData() {
        return this.allData;
    }

}
