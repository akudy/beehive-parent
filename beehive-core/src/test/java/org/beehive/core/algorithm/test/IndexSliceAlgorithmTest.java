/*
 * Copyright (c) 2019-2022 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.algorithm.test.IndexSliceAlgorithmTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2022-03-04
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.algorithm.test;

import org.beehive.core.algorithm.IndexSliceAlgorithm;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Arrays;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.algorithm.test</code></li>
 *   <li>Class Name: <code>IndexSliceAlgorithmTest</code></li>
 *   <li>Java Version Used: Java 8</li>
 *   <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 *   <dd>
 *     <table border="1" cellspacing="0" cellpadding="0" summary=""Upgrade&shy;Modify History>
 *       <tr>
 *         <th>Version</th>
 *         <th>Environment</th>
 *         <th>ModifyTime</th>
 *         <th>Modifier</th>
 *         <th>Description</th>
 *       </tr>
 *       <tr>
 *         <td align="center"><em>1.0</em></td>
 *         <td align="center"><em>Java 8</em></td>
 *         <td align="center"><em>2022-03-04</em></td>
 *         <td align="center"><em>akudy</em></td>
 *         <td><em>Define</em></td>
 *       </tr>
 *     </table>
 *   </dd>
 * </dl>
 *
 * @author <a href="mailto:akudys@163.com">akudy</a>
 * @version 1.0
 * @since 1.0
 */
public class IndexSliceAlgorithmTest {

    @Test
    public void test() {
        String str = "hello world!";
        printIndex(str);
        testCompare(str, -3);
        testCompare(str, -3, -1);
        testCompare(str, 3);
        testCompare(str, 3, 7);
        testCompare(str, -1, -1);
        testCompare(str, 0, 0);
        testCompare(str, 0, -1);
        testCompare(str, -5, 7);
        testCompare(str, 19, 7);
        testCompare(str, 7, 19);
        testCompare(str, -7, -19);
        testCompare(str, -19, -7);
        testCompare(str, -12, -11);
        testCompare(str, 5, -6);
    }

    private void testCompare(String str, int... indexs) {
        String result1 = execJavaScript(str, indexs);
        String result2 = test(str, indexs);
        System.out.print("JavaScript result = " + result1 + " | " + "Java.result = " + result2 + " ==> ");
        if (result1.equals(result2)) {
            System.out.println("true");
        } else {
            System.err.println("false");
        }
    }

    private String execJavaScript(String str, int... indexs) {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            String execScript = "";
            if (indexs.length == 1) {
                execScript = "\"" + str + "\".slice(" + indexs[0] + ")";
            } else if (indexs.length == 2) {
                execScript = "\"" + str + "\".slice(" + indexs[0] + "," + indexs[1] + ")";
            }
            Object result = engine.eval(execScript);
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void printIndex(String str) {
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        StringBuilder c = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            a.append(str.charAt(i) + "\t");
            b.append((i) + "\t");
            c.append(-(str.length() - i) + "\t");
        }
        System.out.println("str.length = " + str.length());
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());
        System.out.println("===============================================");
    }

    private String test(String str, int... indexs) {
        if (indexs.length == 1) {
            int[] numbers = IndexSliceAlgorithm.indexSlice(str.length(), indexs[0]);
            String result = str.substring(numbers[0], numbers[1]);
            String debug = "(" + str + ", " + indexs[0] + ") -> " + str + Arrays.toString(numbers) + " = " + result;
            System.out.println("\t\t===>debug: " + debug);
            return result;
        } else if (indexs.length == 2) {
            int[] numbers = IndexSliceAlgorithm.indexSlice(str.length(), indexs[0], indexs[1]);
            String result = str.substring(numbers[0], numbers[1]);
            String debug = "(" + str + ", " + indexs[0] + ", " + indexs[1] + ") -> " + str + Arrays.toString(numbers) + " = " + result;
            System.out.println("\t\t===>debug: " + debug);
            return result;
        }
        return null;
    }


}
