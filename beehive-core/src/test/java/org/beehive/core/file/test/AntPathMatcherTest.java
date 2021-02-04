/*
 * Copyright (c) 2019-2021 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.file.test.AntPathMatcherTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2021-01-21
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.file.test;

import org.beehive.core.file.AntPathMatcher;
import org.junit.Test;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.file.test</code></li>
 *   <li>Class Name: <code>AntPathMatcherTest</code></li>
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
 *         <td align="center"><em>2021/1/21</em></td>
 *         <td align="center"><em>akudy</em></td>
 *         <td><em>Define</em></td>
 *       </tr>
 *     </table>
 *   </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @since 1.0
 */
public class AntPathMatcherTest {

    @Test
    public void matchTest() {
        AntPathMatcher matcher = new AntPathMatcher();
        String path = "/a/b/c/d1/e1/f1/g1/h1/i1/j/k.txt";
        String pattern = "**";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "*";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "?";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/*";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/?";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/*/*";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/*";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/*.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/?.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/*/*.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/*/?.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/a/b/c/d1/e1/f1/g1/h1/i1/j/k.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/**/**/**/**/**/**/**/**/*/**";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/**/**/**/**/";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/*/*.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/*/**/*.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/*/?.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/*/**/?.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/a/b/c/**/e/**/f/g/**/?.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/**";
        path = "/abc/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/**";
        path = "/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/**/*";
        path = "/abc/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/**/*";
        path = "/abc/a/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/ab*/*";
        path = "/abc/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/ab*/*";
        path = "/abc/a/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/abc/*";
        path = "/abc/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/abc/*";
        path = "/abc/a/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));


    }

    @Test
    public void test(){
        AntPathMatcher matcher = new AntPathMatcher();
        String path = "/a/b/c/d1/e1/f1/g1/h1/i1/j/k.txt";
        String pattern = "/a/b/c/**/e/**/f/g/**/?.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
    }

}
