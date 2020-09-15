/*
 * Copyright (c) 2019-2019 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-date
 * File Name: org.beehive.date.test.DateTimeTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2019-07-08
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.date.test;

import org.beehive.date.DateTime;
import org.junit.Test;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.date.test</code></li>
 * <li>Class Name: <code>DateTimeTest</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1" cellspacing="0" cellpadding="0">
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
 * <td align="center"><em>2019/7/8</em></td>
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
public class DateTimeTest {

    public static void main(String[] args) throws Exception {

        DateTime dateTime = new DateTime();
        System.out.println(dateTime.toString("M月d日"));
        System.out.println(dateTime);

        SimpleDateFormat formatter = new SimpleDateFormat("X yyyy年MM月dd日 HH:mm:ss.SSS");
        Field field = DateTime.class.getDeclaredField("dateFormatter");
        field.setAccessible(true);
        field.set(DateTime.class, formatter);

        dateTime = new DateTime();
        System.out.println(dateTime);


        dateTime = new DateTime(2019, 2, 24, 3, 3, 3, 999);
        System.out.println(dateTime);

        System.out.println(MessageFormat.format("{0},{1},{2},{4},'{}", "A", "B", "C", "D", 23));

        System.out.println(TimeZone.getTimeZone("GMT-1"));

        Instant instant = Instant.now();
        instant.plusSeconds(300);
        System.out.println(instant);

    }

    @Test
    public void testBuilder() throws Exception {
        DateTime dateTime1 = new DateTime();
        System.out.println(dateTime1);
        DateTime dateTime2 = dateTime1.now();
        System.out.println(dateTime2);
        DateTime dateTime3 = new DateTime(2019, 1, 3, 12, 34, 56);
        System.out.println(dateTime3);

        DateTime dateTime4 = new DateTime.Value().day(21).build();
        System.out.println(dateTime4);

        dateTime4.add(new DateTime.Value().hour(1));
        System.out.println(dateTime4);

        dateTime4.minus(new DateTime.Value().hour(2));
        System.out.println(dateTime4);
    }

    @Test
    public void testDiff() throws Exception {
        DateTime dateTime1 = new DateTime(2019,11,1,23,56,13);
        DateTime dateTime2 = new DateTime(2019, 10, 2, 23, 56, 13, 873);
        System.out.println(dateTime1+"\n"+dateTime2);
        DateTime.DiffValue diffValue = dateTime1.diff(dateTime2);
        System.out.println(diffValue.days() + "天" + diffValue.hours() + "时" + diffValue.minutes() + "分" + diffValue.seconds() + "秒" + diffValue.milliseconds() + "毫秒");
    }

    @Test
    public void testCompare() throws Exception {
        DateTime dateTime1 = new DateTime();
        DateTime dateTime2 = new DateTime(2019, 11, 22, 0, 0, 0, 0);
        System.out.println(dateTime1.compareTo(dateTime2));
        System.out.println(dateTime1.equals(dateTime2));
        dateTime1.beginOf();
        System.out.println(dateTime1.compareTo(dateTime2));
        System.out.println(dateTime1.equals(dateTime2));
    }

}
