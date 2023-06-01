/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.collection.test.ListPageTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-21
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.collection.test;

import org.beehive.core.collection.ListPage;
import org.beehive.core.collection.Page;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.collection.test</code></li>
 * <li>Class Name: <code>ListPageTest</code></li>
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
 * @author <a href="mailto:akudys@163.com">akudy</a>
 * @version 1.0
 * @since 1.0
 */
public class ListPageTest {

    @Test
    public void listPageTest() {
        List<String> list = new ArrayList<>();
        char[] array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        for (int i = 0; i < 26; i++) {
            list.add(new String(new char[]{array[i], array[i], array[i]}));
        }

        System.out.println(list);

        ListPage<String> listPage = new ListPage<>(list, 10);
        System.out.println("pageSize = " + listPage.getPageSize() + "\tpageCount = " + listPage.getPageCount() + "\ttotalCount = " + listPage.getTotalCount());

        for (Page<String> page : listPage) {
            System.out.println(page);
        }

        for (int i = 1; i <= listPage.getPageCount(); i++) {
            Page<String> page = listPage.getPage(i);
            System.out.println(page);
        }

        System.out.println(listPage.getFirstPage());
        System.out.println(listPage.getLastPage());
    }

}
