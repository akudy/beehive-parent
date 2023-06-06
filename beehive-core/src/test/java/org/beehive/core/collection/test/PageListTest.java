/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.collection.test;

import org.beehive.util.CollectionUtils;
import org.junit.Test;

import java.util.List;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 *
 * @author akudy
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
