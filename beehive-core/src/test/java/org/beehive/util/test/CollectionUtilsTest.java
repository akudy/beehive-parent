/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.util.test;

import org.beehive.core.collection.ListPage;
import org.beehive.util.CollectionUtils;
import org.junit.Test;

import java.util.*;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class CollectionUtilsTest {

    @Test
    public void nullAndEmptyTest() {
        List<String> list = Arrays.asList("1", "2", "3");
        Set<String> set = new HashSet<>();

        System.out.println(String.format("isNull(%s) =  %s", list, CollectionUtils.isNull(list)));
        System.out.println(String.format("isNull(%s) =  %s", set, CollectionUtils.isNull(set)));
        System.out.println(String.format("isNotNull(%s) =  %s", list, CollectionUtils.isNotNull(list)));
        System.out.println(String.format("isNotNull(%s) =  %s", set, CollectionUtils.isNotNull(set)));
        System.out.println(String.format("isEmpty(%s) =  %s", list, CollectionUtils.isEmpty(list)));
        System.out.println(String.format("isEmpty(%s) =  %s", set, CollectionUtils.isEmpty(set)));
        System.out.println(String.format("isNotEmpty(%s) =  %s", list, CollectionUtils.isNotEmpty(list)));
        System.out.println(String.format("isNotEmpty(%s) =  %s", set, CollectionUtils.isNotEmpty(set)));
    }

    @Test
    public void isCollectionAndSizeTest() {
        Object list = Arrays.asList("1", "2", "3");
        Object set = new HashSet<>();

        System.out.println(String.format("isCollection(%s) = %s", list, CollectionUtils.isCollection(list)));
        System.out.println(String.format("isCollection(%s) = %s", set, CollectionUtils.isCollection(set)));
        System.out.println(String.format("isCollection(%s) = %s", "a", CollectionUtils.isCollection("a")));

        System.out.println(String.format("size(%s) = %s", list, CollectionUtils.size(list)));
        System.out.println(String.format("size(%s) = %s", set, CollectionUtils.size(set)));
    }

    @Test
    public void newCollectionTest() {
        Integer[] number = {1, 3, 5};
        System.out.println(String.format("newArrayList(%s) = %s", Arrays.toString(number), CollectionUtils.newArrayList(number)));
        System.out.println(String.format("newHashSet(%s) = %s", Arrays.toString(number), CollectionUtils.newHashSet(number)));
    }

    @Test
    public void addRemoveTest() {
        Set<Integer> numbers = CollectionUtils.newHashSet(1, 2, 3, 5);
        Integer[] array = {1, 3, 4};
        System.out.print(String.format("add(%s, %s) = ", numbers, Arrays.toString(array)));
        CollectionUtils.add(numbers, array);
        System.out.println(numbers);

        System.out.print(String.format("remove(%s, %s) = ", numbers, Arrays.toString(array)));
        CollectionUtils.remove(numbers, array);
        System.out.println(numbers);
    }

    @Test
    public void containsTest() {
        List<Integer> numbers = CollectionUtils.newArrayList(1, 3, 5, 2, 4, 6, 1, 2, 1, 2, 3, 1);
        Integer[] keys = {1, 5, 7};
        System.out.println(String.format("count(%s, %s) = %s", numbers, 1, CollectionUtils.count(numbers, 1)));
        System.out.println(String.format("containsAll(%s, %s) = %s", numbers, Arrays.toString(keys), CollectionUtils.containsAll(numbers, keys)));
        System.out.println(String.format("containsAny(%s, %s) = %s", numbers, Arrays.toString(keys), CollectionUtils.containsAny(numbers, keys)));
    }

    @Test
    public void uniteCalcTest() {
        List<String> listA = CollectionUtils.newArrayList("A", "B", "C", "D");
        List<String> listB = CollectionUtils.newArrayList("C", "F", "D", "G");
        System.out.println(String.format("A = %s, B = %s", listA, listB));
        System.out.println(String.format("\tA＋B = %s", CollectionUtils.join(listA, listB)));
        System.out.println(String.format("\tA∪B = %s", CollectionUtils.merge(listA, listB)));
        System.out.println(String.format("\tA－B = %s", CollectionUtils.differ(listA, listB)));
        System.out.println(String.format("\tA∩B = %s", CollectionUtils.cross(listA, listB)));
        System.out.println(String.format("\t(A－B)∪(B－A) = %s", CollectionUtils.unCross(listA, listB)));
    }

    @Test
    public void subListTest() {
        List<String> list = CollectionUtils.newArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        System.out.println(String.format("subList(%s, %s) = %s", list, 3, CollectionUtils.subList(list, 3)));
        System.out.println(String.format("subList(%s, %s) = %s", list, -3, CollectionUtils.subList(list, -3)));
        System.out.println(String.format("subList(%s, %s) = %s", list, 0, CollectionUtils.subList(list, 0)));

        System.out.println(String.format("subList(%s, %s) = %s", list, Arrays.toString(new int[]{3, 7}), CollectionUtils.subList(list, 3, 7)));
        System.out.println(String.format("subList(%s, %s) = %s", list, Arrays.toString(new int[]{-3, -7}), CollectionUtils.subList(list, -3, -7)));
        System.out.println(String.format("subList(%s, %s) = %s", list, Arrays.toString(new int[]{-3, 3}), CollectionUtils.subList(list, -3, 3)));
        System.out.println(String.format("subList(%s, %s) = %s", list, Arrays.toString(new int[]{2, 2}), CollectionUtils.subList(list, 2, 2)));
        System.out.println(String.format("subList(%s, %s) = %s", list, Arrays.toString(new int[]{-2, -2}), CollectionUtils.subList(list, -2, -2)));

        System.out.println(String.format("subList(%s, %s) = %s", list, Arrays.toString(new int[]{-1, -3, 1, 2, 0, 3}), CollectionUtils.pickList(list, -1, -3, 1, 2, 0, 3)));
        System.out.println(String.format("subList(%s, %s) = %s", list, Arrays.toString(new int[]{-1, 1, 1, 2, 1, 3}), CollectionUtils.pickList(list, -1, 1, 1, 2, 1, 3)));

        System.out.println(String.format("extractList(%s, %s) = %s", list, Arrays.toString(new int[]{0, 4, 1, 2, 1, 3}), CollectionUtils.ofList(list, 0, 4, 1, 2, 1, 3)));
        System.out.println(String.format("extractList(%s, %s) = %s", list, Arrays.toString(new int[]{-1, 1, 1, 2, 1, 3}), CollectionUtils.ofList(list, -1, 1, 1, 2, 1, 3)));
    }

    @Test
    public void pageTest() {
        List<String> list = new ArrayList<>();
        char[] array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        for (int i = 0; i < 26; i++) {
            list.add(new String(new char[]{array[i], array[i], array[i]}));
        }
        System.out.println(list);

        ListPage<String> listPage = CollectionUtils.toListPage(list, 5);
        System.out.println("pageSize = " + listPage.getPageSize() + "\tpageCount = " + listPage.getPageCount() + "\ttotalCount = " + listPage.getTotalCount());
        System.out.println(listPage.getFirstPage());
        System.out.println(listPage.getLastPage());
        System.out.println(listPage.getPage(2));

    }

}
