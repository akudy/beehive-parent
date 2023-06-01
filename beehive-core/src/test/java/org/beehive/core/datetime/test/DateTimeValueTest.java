/*
 * Copyright (c) 2019-2023 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.datetime.test.DateTimeValueTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2023-05-19
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.datetime.test;

import org.beehive.core.datetime.DateTimeValue;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.datetime.test</code></li>
 *   <li>Class Name: <code>DateTimeValueTest</code></li>
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
 *         <td align="center"><em>2023-05-19</em></td>
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
public class DateTimeValueTest {

    @Test
    public void buildTest() {
        DateTimeValue dateTimeValue = DateTimeValue.now();
        System.out.println(dateTimeValue);
        dateTimeValue = DateTimeValue.of(2023, 5, 19, 15, 16, 42);
        System.out.println(dateTimeValue);
        dateTimeValue = DateTimeValue.ofCalendar(Calendar.getInstance());
        System.out.println(dateTimeValue);
        dateTimeValue = DateTimeValue.ofDate(new Date());
        System.out.println(dateTimeValue);
        dateTimeValue = DateTimeValue.ofTimestamp(123456789);
        System.out.println(dateTimeValue);
        dateTimeValue = DateTimeValue.ofLocalDateTime(LocalDateTime.now());
        System.out.println(dateTimeValue);
        dateTimeValue = DateTimeValue.ofLocalDateTime(LocalDateTime.now());
        System.out.println(dateTimeValue);
        dateTimeValue = DateTimeValue.ofSQLDate(new java.sql.Date(1));
        System.out.println(dateTimeValue);
        dateTimeValue = DateTimeValue.ofSQLTimestamp(new java.sql.Timestamp(1));
        System.out.println(dateTimeValue);
    }

    @Test
    public void getValueTest() {
        DateTimeValue dateTimeValue = DateTimeValue.of(2023, 5, 19, 15, 16, 42, 567);
        System.out.println(dateTimeValue);
        System.out.println("getYear() => " + dateTimeValue.getYear() + ", year => " + dateTimeValue.year());
        System.out.println("getMonth() => " + dateTimeValue.getMonth() + ", month => " + dateTimeValue.month());
        System.out.println("getDay() => " + dateTimeValue.getDay() + ", day => " + dateTimeValue.day());
        System.out.println("getHour() => " + dateTimeValue.getHour() + ", hour => " + dateTimeValue.hour());
        System.out.println("getMinute() => " + dateTimeValue.getMinute() + ", minute => " + dateTimeValue.minute());
        System.out.println("getSecond() => " + dateTimeValue.getSecond() + ", second => " + dateTimeValue.second());
        System.out.println("getMillisecond() => " + dateTimeValue.getMillisecond() + ", millisecond => " + dateTimeValue.millisecond());
    }

    @Test
    public void compareTest() {
        DateTimeValue dateTimeValue1 = DateTimeValue.of(2023, 12, 5, 12, 34, 21);
        DateTimeValue dateTimeValue2 = DateTimeValue.of(2023, 11, 23, 9, 43, 12);
        System.out.println(String.format("%s after %s => %s", dateTimeValue1, dateTimeValue2, dateTimeValue1.after(dateTimeValue2)));
        System.out.println(String.format("%s before %s => %s", dateTimeValue1, dateTimeValue2, dateTimeValue1.before(dateTimeValue2)));
        System.out.println(String.format("%s equals %s => %s", dateTimeValue1, dateTimeValue2, dateTimeValue1.equals(dateTimeValue2)));

        System.out.println("------------------------");

        List<DateTimeValue> list = new ArrayList<>();
        list.add(DateTimeValue.of(2023, 12, 5, 12, 34, 21));
        list.add(DateTimeValue.of(2021, 10, 28, 00, 0, 0));
        list.add(DateTimeValue.of(2022, 9, 12, 00, 0, 0));
        list.add(DateTimeValue.of(2021, 2, 12, 00, 0, 0));
        list.add(DateTimeValue.of(2023, 12, 5, 12, 34, 22));
        list.add(DateTimeValue.of(2023, 12, 5, 12, 34, 20));
        Collections.sort(list);
        for (DateTimeValue dateTimeValue : list) {
            System.out.println(dateTimeValue);
        }
    }

}
