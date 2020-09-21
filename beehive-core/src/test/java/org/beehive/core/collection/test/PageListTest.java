/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.collection.test.PageListTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-15
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.collection.test;

import org.beehive.util.CollectionUtils;
import org.junit.Test;

import java.util.List;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.collection.test</code></li>
 * <li>Class Name: <code>PageListTest</code></li>
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
 * <td align="center"><em>2020/9/15</em></td>
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
public class PageListTest {

    @Test
    public void test() {
        List<String> list = CollectionUtils.newArrayList("A", "B", "C", "D", "E");
        PageList1<String> pageList = new PageList1<>(2,list);
        System.out.println(pageList.getPageSize()+"\t"+pageList.getPageCount()+"\t"+pageList.getTotalCount());
        System.out.println(pageList.fetch(2));
//        pageList.paging(2).fetch(2);
//
//        System.out.println(pageList.getPageCount() + "\t" + pageList.getPageSize() + "\t" + pageList.getTotalPage() + "\t" + pageList.getTotalSize());
//        System.out.println(pageList.getPageData());
//
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                while (i < 10) {
//                    pageList.paging(2).fetch(2);
//                    String mss = "pageNum=" + pageList.getPageNo() + "\tpageSize=" + pageList.getPageSize() + "\tpageCount=" + pageList.getTotalPage() + "\ttotalCount=" + pageList.getTotalSize() + ":" + pageList.getPageData();
//                    System.out.println("线程1 - " + mss);
//                    i++;
//                }
//            }
//        });
//
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                while (i < 10) {
//                    pageList.paging(1).fetch(2);
//                    String mss = "pageNum=" + pageList.getPageNo() + "\tpageSize=" + pageList.getPageSize() + "\tpageCount=" + pageList.getTotalPage() + "\ttotalCount=" + pageList.getTotalSize() + ":" + pageList.getPageData();
//                    System.out.println("线程2 - " + mss);
//                    i++;
//                }
//            }
//        });
//
//        thread1.start();
//        thread2.start();
    }

}
