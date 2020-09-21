/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.collection.Pageable
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-21
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.collection;

/**
 * 描述一个可分页的对象接口定义。包含：总页数，每页大小；分页遍历等信息。
 * <br>
 * 可分页的对象可以被迭代。集成了{@link Iterable}接口
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.collection</code></li>
 * <li>Class Name: <code>Pageable</code></li>
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
 * @author akudy(akudys @ 163.com)
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
