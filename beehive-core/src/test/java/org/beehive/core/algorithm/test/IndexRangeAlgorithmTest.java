/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.algorithm.test.IndexRangeAlgorithmTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-15
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.algorithm.test;

import org.beehive.core.algorithm.IndexRangeAlgorithm;
import org.junit.Test;

import java.util.Arrays;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.algorithm</code></li>
 * <li>Class Name: <code>IndexRangeAlgorithmTest</code></li>
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
 * <td align="center"><em>2020/9/14</em></td>
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
public class IndexRangeAlgorithmTest {

    @Test
    public void test() {
        int[] indexs = {1, 1, 2, 3, 5, 7};
        String str = "ABCDEFG";
        if (indexs.length == 1) {
            int[] numbers = IndexRangeAlgorithm.indexRange(str.length(), indexs[0]);
            System.out.println(str + Arrays.toString(numbers) + " = " + str.substring(numbers[0], numbers[1]));
        } else if (indexs.length == 2) {
            int[] numbers = IndexRangeAlgorithm.indexRange(str.length(), indexs[0], indexs[1]);
            System.out.println(str + Arrays.toString(numbers) + " = " + str.substring(numbers[0], numbers[1]));
        } else if (indexs.length > 2) {
            int[] numbers = IndexRangeAlgorithm.indexRange(str.length(), indexs);
            System.out.println(Arrays.toString(numbers));
            StringBuffer sb = new StringBuffer();
            for (int i : numbers) {
                sb.append(str.charAt(i));
            }
            System.out.println(str + Arrays.toString(numbers) + " = " + sb.toString());
        }

    }
}
