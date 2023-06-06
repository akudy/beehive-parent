/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.algorithm.test;

import org.beehive.core.algorithm.IndexRangeAlgorithm;
import org.junit.Test;

import java.util.Arrays;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class IndexRangeAlgorithmTest {

    @Test
    public void subOfSingleIndex() {
        String str = "ABCDEFGHIJ";
        System.out.println("to right: ");
        System.out.println("\t" + test(str, 2));
        System.out.println("\t" + test(str, 20));
        System.out.println("to left：");
        System.out.println("\t" + test(str, -2));
        System.out.println("\t" + test(str, -20));
    }

    @Test
    public void subOfDoubleIndex() {
        String str = "ABCDEFGHIJ";
        System.out.println(test(str, 1, 0));
        System.out.println(test(str, 0, 1));
        System.out.println(test(str, 2, 2));
        System.out.println(test(str, 2, 4));
        System.out.println(test(str, 4, 2));
        System.out.println(test(str, 20, 2));
        System.out.println(test(str, 2, 20));
        System.out.println(test(str, 20, 20));
        System.out.println();
        System.out.println(test(str, -2, 0));
        System.out.println(test(str, 0, -2));
        System.out.println(test(str, -2, -2));
        System.out.println(test(str, -2, -4));
        System.out.println(test(str, -4, -2));
        System.out.println(test(str, -20, -2));
        System.out.println(test(str, -2, -20));
        System.out.println(test(str, -20, -20));
        System.out.println();
        System.out.println(test(str, -2, -2));
        System.out.println(test(str, -2, 2));
        System.out.println(test(str, 2, -2));
        System.out.println(test(str, 2, -4));
        System.out.println(test(str, -4, 2));
        System.out.println(test(str, -20, -2));
        System.out.println(test(str, 2, -20));
        System.out.println(test(str, -20, -20));
    }

    @Test
    public void subOfMultipleIndex() {
        String str = "ABCDEFGHIJ";
        System.out.println(test(str, 1, 1, 2, 2, 3, 3, 4, 4));
        System.out.println(test(str, 1, 2, 3, 4, 5));
        System.out.println(test(str, 5, 4, 3, 2, 1));
        System.out.println(test(str, 1, 3, 5, 7, 9));
    }

    private String test(String str, int... indexs) {
        if (indexs.length == 1) {
            int[] numbers = IndexRangeAlgorithm.indexRange(str.length(), indexs[0]);
            return "(" + str + ", " + indexs[0] + ") -> " + str + Arrays.toString(numbers) + " = " + str.substring(numbers[0], numbers[1]);
        } else if (indexs.length == 2) {
            int[] numbers = IndexRangeAlgorithm.indexRange(str.length(), indexs[0], indexs[1]);
            return "(" + str + ", " + indexs[0] + ", " + indexs[1] + ") -> " + str + Arrays.toString(numbers) + " = " + str.substring(numbers[0], numbers[1]);
        } else if (indexs.length > 2) {
            int[] numbers = IndexRangeAlgorithm.indexRange(str.length(), indexs);
            StringBuffer sb = new StringBuffer();
            for (int i : numbers) {
                sb.append(str.charAt(i));
            }
            return "(" + str + ", " + Arrays.toString(indexs) + ") -> " + str + Arrays.toString(numbers) + " = " + sb.toString();
        }
        return null;
    }
}
