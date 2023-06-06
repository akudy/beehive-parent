/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
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
 *
 * @author akudy
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
