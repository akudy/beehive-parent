/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.algorithm;

/**
 * 索引切片算法。 比如一个集合、数组、字符串等，只要是一个可以被截取的容器，都可以使用该算法来完成截取索引的计算。<br/>
 * 灵活的算法支持以下正向或逆向的索引范围推算，索引从0开始：
 * <ul>
 * <li>1. 正整数表示正向推算；负整数表示逆向推算</li>
 * <li>2. 如果索引超过容器长度，则以容器长度为终止索引</li>
 * <li>3. 索引从0开始计算</li>
 * </ul>
 * 示例如下（以字符串"hello world!"为例）：<br/>
 * <table border="1" cellspacing="0" cellpadding="0">
 *     <tr>
 *         <td>h</td>
 *         <td>e</td>
 *         <td>l</td>
 *         <td>l</td>
 *         <td>o</td>
 *         <td> </td>
 *         <td>w</td>
 *         <td>o</td>
 *         <td>r</td>
 *         <td>l</td>
 *         <td>d</td>
 *         <td>!</td>
 *     </tr>
 *     <tr>
 *         <td>0</td>
 *         <td>1</td>
 *         <td>2</td>
 *         <td>3</td>
 *         <td>4</td>
 *         <td>5</td>
 *         <td>6</td>
 *         <td>7</td>
 *         <td>8</td>
 *         <td>9</td>
 *         <td>10</td>
 *         <td>11</td>
 *     </tr>
 *     <tr>
 *         <td>-12</td>
 *         <td>-11</td>
 *         <td>-10</td>
 *         <td>-9</td>
 *         <td>-8</td>
 *         <td>-7</td>
 *         <td>-6</td>
 *         <td>-5</td>
 *         <td>-4</td>
 *         <td>-3</td>
 *         <td>-2</td>
 *         <td>-1</td>
 *     </tr>
 * </table>
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public final class IndexSliceAlgorithm {

    /**
     * 禁止实例化
     */
    private IndexSliceAlgorithm() {

    }

    /**
     * 根据指定的一个数值进行索引切片。当只有一个数值时，该值表示开始所以位置和切片方向。<br/>
     * 如果为正数，则表示从0开始正向截取到末尾的元素；如果为负数，则表示从-1开始逆向截取到末尾的元素
     *
     * @param length 总长度
     * @param start  索引开始位置；正数表示从0开始向右推算，负数表示从-1开始向左推算
     * @return 截取的索引数组；第一个元素表示开始位置，第二个元素表示结束位置
     * @since 1.0
     */
    public static int[] indexSlice(int length, int start) {
        return indexSlice(length, start, length);
    }


    /**
     * 根据指定的两个数值来计算索引范围。当有两个数值时，第一个数值表示开始索引位置，第二个数值表示结束的所有位置。<br/>
     * 如果数值为正数，则表示从0开始向右推算索引位置；如果数值为负数，则表示从0开始向左推算索引位置。
     *
     * @param length 总长度
     * @param start  索引的开始位置
     * @param end    索引的结束位置
     * @return 截取的索引数组；第一个元素表示开始位置，第二个元素表示结束位置
     * @since 1.0
     */
    public static int[] indexSlice(int length, int start, int end) {
        int from = 0;
        int to = 0;
        from = start < 0 ? length + start : start;
        from = from >= length ? length : from;
        to = end < 0 ? length + end : end;
        to = to >= length ? length : to;
        from = from < 0 ? 0 : from;
        to = to < 0 ? 0 : to;
        if (from > to) {
            from = to = 0;
        }
        return new int[]{from, to};
    }


}
