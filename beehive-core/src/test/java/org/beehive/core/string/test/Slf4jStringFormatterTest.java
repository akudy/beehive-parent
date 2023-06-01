/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.test.Slf4jStringFormatterTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-10-14
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string.test;

import org.beehive.core.string.Slf4jStringFormatter;
import org.junit.Test;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.string.test</code></li>
 * <li>Class Name: <code>Slf4jStringFormatterTest</code></li>
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
 * <td align="center"><em>2020/10/14</em></td>
 * <td align="center"><em>akudy</em></td>
 * <td><em>Define</em></td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 *
 * @author <a href="mailto:akudys@163.com">akudy</a>
 * @version 1.0
 * @since 1.0
 */
public class Slf4jStringFormatterTest {

    Slf4jStringFormatter formatter = new Slf4jStringFormatter();

    @Test
    public void test() {
        String message = "this is str template: name = {}, jobs = {}, \'sex\' = \\{{}\\}, description = {my name is xx}, {hello!{}}";
        System.out.println(message + " format of = ");
        System.out.println("\t" + formatter.format(message, "akudy"));
        System.out.println("\t" + formatter.format(message, "akudy", "java"));
        System.out.println("\t" + formatter.format(message, "akudy", "java", 'm', 'o'));
        System.out.println("\t" + formatter.format(message, "akudy", new String[]{"java", "python"}));
    }

}
