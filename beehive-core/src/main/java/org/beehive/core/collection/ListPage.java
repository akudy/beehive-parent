/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.collection.ListPage
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-18
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.collection;

import java.util.List;

/**
 * 一个列表分页对象实现
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.collection</code></li>
 * <li>Class Name: <code>ListPage</code></li>
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
 * <td align="center"><em>2020/9/18</em></td>
 * <td align="center"><em>akudy</em></td>
 * <td><em>Define</em></td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 *
 * @param <E> 被分页的列表元素类型
 * @author <a href="mailto:akudys@163.com">akudy</a>
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
