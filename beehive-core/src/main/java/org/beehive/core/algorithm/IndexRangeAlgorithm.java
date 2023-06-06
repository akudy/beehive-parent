/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.algorithm;

import java.util.Arrays;

/**
 * 索引范围算法。 比如一个集合、数组、字符串等，只要是一个可以被截取的容器，都可以使用该算法来完成截取索引的计算。<br/>
 * 灵活的算法支持以下正向或逆向的索引范围推算，索引从0开始：
 * <ul>
 * <li>1. 正整数表示正向推算；负整数表示逆向推算</li>
 * <li>2. 如果索引超过容器长度，则以容器长度为终止索引</li>
 * <li>3. 索引从0开始计算</li>
 * </ul>
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public final class IndexRangeAlgorithm {

    /**
     * 禁止实例化
     */
    private IndexRangeAlgorithm() {
    }

    /**
     * 根据指定的一个数值来计算的索引范围。当只确定一个数值时，该值表示开始索引位置和截取方向。<br/>
     * 如果为正数：则表示从0开始正向截取指定数量的元素；如果为负数：则表示从指定长度开始逆向截取指定数量的元素
     *
     * @param size  总大小
     * @param index 绝对值表示数量；正数表示正向，负数表示逆向
     * @return 截取的索引数组；第一个元素表示开始位置，第二个元素表示结束位置
     * @since 1.0
     */
    public static int[] indexRange(int size, int index) {
        int start = 0;
        int end = size;
        if (index >= 0) {
            start = 0;
            end = index > size ? size : index;
        } else {
            start = -index > size ? 0 : size - (-index);
            end = size;
        }
        int[] indexArray = new int[2];
        indexArray[0] = start;
        indexArray[1] = end;
        return indexArray;
    }

    /**
     * 根据指定的两个数字来计算索引范围。当有两个数值时，一般第一个值表示开始索引位置，第二个值表示截取的方向和数量。<br/>
     * <ul>
     * <li>如果第一个值是正数，则表示从0开始向右推算的该值所表示的位置开始；如果第一个值是负数，则表从指定从总大小开始向左推算的该值所表示的位置开始</li>
     * <li>如果第二个值是正数，则表示向右截取的个数；如果第二个值是负数，则表示向左截取的个数</li>
     * </ul>
     *
     * @param size  总大小
     * @param index 截取的开始索引位置，正数表示从0开始递增；负数表示从总大小开始递减
     * @param count 截取的方向和数量；正数表示向右截取，负数表示向左截取
     * @return 截取的索引范围数组，第一个元素表示开始位置，第二个元素表示结束位置
     * @since 1.0
     */
    public static int[] indexRange(int size, int index, int count) {
        int start = 0;
        int end = size;
        if (index >= 0) {
            start = index > size ? size : index;
        } else {
            start = size - (-index) < 0 ? 0 : size - (-index);
        }
        if (count >= 0) {
            end = start + count > size ? size : start + count;
        } else {
            end = start - (-count) < 0 ? 0 : start - (-count);
        }
        if (start > end) {
            start = start + end;
            end = start - end;
            start = start - end;
        }
        int[] indexArray = new int[2];
        indexArray[0] = start;
        indexArray[1] = end;
        return indexArray;
    }

    /**
     * 重新整理指定的索引，并从小到大，去重
     *
     * @param size    总大小
     * @param indexes 索引列表
     * @return 截取的索引范围数组；被去重可重新排序后的索引值
     * @since 1.0
     */
    public static int[] indexRange(int size, int[] indexes) {
        int[] indexTemp = Arrays.copyOf(indexes, indexes.length);
        Arrays.sort(indexTemp);
        int count = 0;
        int[] posTemp = new int[indexes.length];
        for (int i = 0, length = indexes.length; i < length; i++) {
            posTemp[i] = -1;
            if (indexes[i] < 0 || indexes[i] >= size || (i > 0 && indexes[i] == indexes[i - 1])) {
                continue;
            }
            count++;
            posTemp[i] = i;
        }
        int[] newIndex = new int[count];
        for (int i = 0, j = 0, length = posTemp.length; i < length; i++) {
            if (posTemp[i] != -1) {
                newIndex[j++] = indexTemp[posTemp[i]];
            }
        }
        return newIndex;
    }

}
