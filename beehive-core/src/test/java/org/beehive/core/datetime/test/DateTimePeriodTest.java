/*
 * Copyright (c) 2019-2023 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.datetime.test.DateTimePeriodTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2023-05-22
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.datetime.test;

import org.beehive.core.datetime.DateTimePeriod;
import org.beehive.core.datetime.DateTimeValue;
import org.beehive.util.DateTimeUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.datetime.test</code></li>
 *   <li>Class Name: <code>DateTimePeriodTest</code></li>
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
 *         <td align="center"><em>2023-05-22</em></td>
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
public class DateTimePeriodTest {

    @Test
    public void buildTest() {
        Date begin = DateTimeValue.of(2023, 5, 15, 12, 34, 32).asDate();
        Date end = DateTimeValue.of(2023, 5, 22, 11, 13, 23).asDate();
        DateTimePeriod period1 = new DateTimePeriod(begin, end);
        System.out.println(period1);
        System.out.println("\t\tintervalDay = " + period1.intervalDays() + ", spanDays = " + period1.spanDays());
        DateTimePeriod period2 = new DateTimePeriod(begin, end, true);
        System.out.println(period2);
        System.out.println("\t\tintervalDay = " + period2.intervalDays() + ", spanDays = " + period2.spanDays());
    }

    @Test
    public void compareDateTest() {
        Date begin = DateTimeValue.of(2023, 5, 15, 12, 34, 32).asDate();
        Date end = DateTimeValue.of(2023, 5, 22, 11, 13, 23).asDate();
        DateTimePeriod period = new DateTimePeriod(begin, end);
        Date date1 = DateTimeValue.of(2023, 5, 10, 13, 23, 23).asDate();
        Date date2 = DateTimeValue.of(2023, 5, 20, 13, 23, 23).asDate();
        Date date3 = DateTimeValue.of(2023, 5, 30, 13, 23, 23).asDate();
        Date date4 = DateTimeValue.of(2023, 5, 15, 12, 34, 32).asDate();

        System.out.println("---------------------before-----------------------------------");
        System.out.println(String.format("[%s].before(%s) => %s", period, DateTimeUtils.format(date1), period.before(date1)));
        System.out.println(String.format("[%s].before(%s) => %s", period, DateTimeUtils.format(date2), period.before(date2)));
        System.out.println(String.format("[%s].before(%s) => %s", period, DateTimeUtils.format(date3), period.before(date3)));
        System.out.println(String.format("[%s].before(%s) => %s", period, DateTimeUtils.format(date4), period.before(date4)));

        System.out.println("---------------------after-----------------------------------");
        System.out.println(String.format("[%s].after(%s) => %s", period, DateTimeUtils.format(date1), period.after(date1)));
        System.out.println(String.format("[%s].after(%s) => %s", period, DateTimeUtils.format(date2), period.after(date2)));
        System.out.println(String.format("[%s].after(%s) => %s", period, DateTimeUtils.format(date3), period.after(date3)));
        System.out.println(String.format("[%s].after(%s) => %s", period, DateTimeUtils.format(date4), period.after(date4)));

        System.out.println("---------------------contain-----------------------------------");
        System.out.println(String.format("[%s].contain(%s) => %s", period, DateTimeUtils.format(date1), period.contain(date1)));
        System.out.println(String.format("[%s].contain(%s) => %s", period, DateTimeUtils.format(date2), period.contain(date2)));
        System.out.println(String.format("[%s].contain(%s) => %s", period, DateTimeUtils.format(date3), period.contain(date3)));
        System.out.println(String.format("[%s].contain(%s) => %s", period, DateTimeUtils.format(date4), period.contain(date4)));
    }

    @Test
    public void comparePeriodTest() {
        Date begin1 = DateTimeValue.of(2023, 5, 1, 23, 12, 3).asDate();
        Date end1 = DateTimeValue.of(2023, 5, 28, 21, 12, 3).asDate();
        DateTimePeriod period1 = new DateTimePeriod(begin1, end1);

        Date begin2 = DateTimeValue.of(2023, 4, 15, 15, 30, 0).asDate();
        Date end2 = DateTimeValue.of(2023, 4, 28, 21, 12, 3).asDate();
        DateTimePeriod period2 = new DateTimePeriod(begin2, end2);

        Date begin3 = DateTimeValue.of(2023, 6, 1, 12, 12, 3).asDate();
        Date end3 = DateTimeValue.of(2023, 6, 13, 4, 14, 0).asDate();
        DateTimePeriod period3 = new DateTimePeriod(begin3, end3);

        Date begin4 = DateTimeValue.of(2023, 5, 1, 23, 12, 3).asDate();
        Date end4 = DateTimeValue.of(2023, 5, 28, 21, 12, 3).asDate();
        DateTimePeriod period4 = new DateTimePeriod(begin4, end4);

        Date begin5 = DateTimeValue.of(2023, 4, 30, 23, 12, 3).asDate();
        Date end5 = DateTimeValue.of(2023, 5, 12, 21, 12, 3).asDate();
        DateTimePeriod period5 = new DateTimePeriod(begin5, end5);

        Date begin6 = DateTimeValue.of(2023, 3, 1, 23, 12, 3).asDate();
        Date end6 = DateTimeValue.of(2023, 7, 28, 21, 12, 3).asDate();
        DateTimePeriod period6 = new DateTimePeriod(begin6, end6);

        System.out.println(String.format("[%s].left([%s]) => %s", period1, period2, period1.left(period2)));
        System.out.println(String.format("[%s].left([%s]) => %s", period1, period3, period1.left(period3)));
        System.out.println(String.format("[%s].left([%s]) => %s", period1, period4, period1.left(period4)));
        System.out.println(String.format("[%s].left([%s]) => %s", period1, period5, period1.left(period5)));
        System.out.println(String.format("[%s].left([%s]) => %s", period1, period6, period1.left(period6)));
        System.out.println();
        System.out.println(String.format("[%s].right([%s]) => %s", period1, period2, period1.right(period2)));
        System.out.println(String.format("[%s].right([%s]) => %s", period1, period3, period1.right(period3)));
        System.out.println(String.format("[%s].right([%s]) => %s", period1, period4, period1.right(period4)));
        System.out.println(String.format("[%s].right([%s]) => %s", period1, period5, period1.right(period5)));
        System.out.println(String.format("[%s].right([%s]) => %s", period1, period6, period1.right(period6)));
        System.out.println();
        System.out.println(String.format("[%s].middle([%s]) => %s", period1, period2, period1.middle(period2)));
        System.out.println(String.format("[%s].middle([%s]) => %s", period1, period3, period1.middle(period3)));
        System.out.println(String.format("[%s].middle([%s]) => %s", period1, period4, period1.middle(period4)));
        System.out.println(String.format("[%s].middle([%s]) => %s", period1, period5, period1.middle(period5)));
        System.out.println(String.format("[%s].middle([%s]) => %s", period1, period6, period1.middle(period6)));
        System.out.println();
        System.out.println(String.format("[%s].enclose([%s]) => %s", period1, period2, period1.enclose(period2)));
        System.out.println(String.format("[%s].enclose([%s]) => %s", period1, period3, period1.enclose(period3)));
        System.out.println(String.format("[%s].enclose([%s]) => %s", period1, period4, period1.enclose(period4)));
        System.out.println(String.format("[%s].enclose([%s]) => %s", period1, period5, period1.enclose(period5)));
        System.out.println(String.format("[%s].enclose([%s]) => %s", period1, period6, period1.enclose(period6)));
        System.out.println();
        System.out.println(String.format("[%s].cross([%s]) => %s", period1, period2, period1.cross(period2)));
        System.out.println(String.format("[%s].cross([%s]) => %s", period1, period3, period1.cross(period3)));
        System.out.println(String.format("[%s].cross([%s]) => %s", period1, period4, period1.cross(period4)));
        System.out.println(String.format("[%s].cross([%s]) => %s", period1, period5, period1.cross(period5)));
        System.out.println(String.format("[%s].cross([%s]) => %s", period1, period6, period1.cross(period6)));

        System.out.println();

        List<DateTimePeriod> list = new ArrayList<>();
        list.add(period1);
        list.add(period2);
        list.add(period3);
        list.add(period4);
        list.add(period5);
        list.add(period6);
        Collections.sort(list);
        for (DateTimePeriod period : list) {
            System.out.println(period);
        }
    }


}
