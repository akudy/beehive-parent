/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.collection.test.PageList1
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-21
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.collection.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 分页列表对象，提供分页信息（页面数据、总数、当前页码、每页展示数量）。
 * <br>
 * 在构建分页列表对象时，需要输入一个完整的列表数据；然后对该列表进行分页截取。分页列表的总数据量，在初始化是已经确定，不可修改。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.collection</code></li>
 * <li>Class Name: <code>PageList1</code></li>
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
 * @author <a href="mailto:akudys@163.com">akudy</a>
 * @version 1.0
 * @since 1.0
 */
public class PageList1<E> extends ArrayList<E> {

    /**
     * 每页元素数量
     */
    private int pageSize;

    public PageList1(List<E> list) {
        this(10, list);
    }

    public PageList1(E... e) {
        this(10, e);
    }

    public PageList1(int pageSize, List<E> list) {
        this.pageSize = pageSize;
        this.addAll(list);
    }

    public PageList1(int pageSize, E... e) {
        this.pageSize = pageSize;
        this.addAll(Arrays.asList(e));
    }

    public int getPageSize() {
        int pageSize = this.pageSize > 0 ? this.pageSize : this.getTotalCount();
        return pageSize;
    }

    public int getTotalCount() {
        return this.size();
    }

    public int getPageCount() {
        int size = this.getTotalCount();
        int pageCount = (size % this.pageSize == 0) ? size / this.pageSize : (size / this.pageSize) + 1;
        return pageCount;
    }

    public List<E> fetch(int pageNo) {
        if (this.pageSize <= 0) {
            this.pageSize = this.getTotalCount();
        }
        int totalCount = this.getTotalCount();
        pageNo = pageNo < 1 ? 1 : pageNo;
        int startIndex = 0;
        int endIndex = this.getTotalCount();
        if (pageNo > 0 && this.pageSize > 0) {
            startIndex = this.pageSize * (pageNo - 1);
            endIndex = this.pageSize * pageNo;
            startIndex = startIndex > totalCount ? totalCount : startIndex;
            endIndex = endIndex > totalCount ? totalCount : endIndex;
        }
        return this.subList(startIndex, endIndex);
    }

    public List<E> firstPage() {
        return this.fetch(1);
    }

    public List<E> lastPage() {
        return this.fetch(this.getPageCount());
    }
}
