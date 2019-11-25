/*
 * Copyright (c) 2019-2019 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-date
 * File Name: org.beehive.date.test.LocalDateTimeTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2019-11-24 22:18:10
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.date.test;

import org.beehive.date.DateTime;
import org.beehive.date.LocalDateTime;
import org.junit.Test;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.date</code></li>
 * <li>Class Name: <code>LocalDateTime</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1" cellspacing="0" cellpadding="0" summary="Upgrade/Modify History">
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
 * <td align="center"><em>2019/6/28</em></td>
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
public class LocalDateTimeTest {

    public static void main(String[] args) {

        for (String str : TimeZone.getAvailableIDs()){
            System.out.println(str);
        }

        DateTime dateTime =new DateTime(TimeZone.getTimeZone("America/New_York"), Locale.ENGLISH);
        System.out.println(dateTime);
    }

    @Test
    public void testBuilder() throws Exception {
        LocalDateTime localDateTime1 = new LocalDateTime();
        System.out.println(localDateTime1);
        java.time.LocalDateTime localDateTime = java.time.LocalDateTime.now();
        System.out.println(localDateTime);
    }

}
