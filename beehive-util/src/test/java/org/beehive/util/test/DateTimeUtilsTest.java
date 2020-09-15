/*
 * Copyright (c) 2019-2019 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-util
 * File Name: org.beehive.util.test.DateTimeUtilsTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2019-07-08
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.util.test;

import org.beehive.util.DateTimeUtils;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p/>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.util</code></li>
 * <li>Class Name: <code>DateTimeUtilsTest</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1">
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
 * <td align="center"><em>2019/6/26</em></td>
 * <td align="center"><em>akudy</em></td>
 * <td align="center"><em>Define</em></td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @since Java 8
 */
public class DateTimeUtilsTest {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    @Test
    public void createTest() {
        System.out.println("------- now date time ------->");

        System.out.println(String.format("nowDateTime() = %s", sdf.format(DateTimeUtils.newDateTime())));
        System.out.println(String.format("newBeginDate() = %s", sdf.format(DateTimeUtils.newBeginDate())));
        System.out.println(String.format("newEndDate() = %s", sdf.format(DateTimeUtils.newEndDate())));
        System.out.println(String.format("now() = %s", sdf.format(DateTimeUtils.now())));
        System.out.println(String.format("nowDateTime() = %s", sdf.format(DateTimeUtils.nowDateTime())));
        System.out.println(String.format("beginOfMonth() = %s", sdf.format(DateTimeUtils.beginOfMonth())));
        System.out.println(String.format("endOfMonth() = %s", sdf.format(DateTimeUtils.endOfMonth())));

        System.out.println("------- create by date ------->");
        Date nowDate = new Date();
        long timestamp = System.currentTimeMillis();
        System.out.println(String.format("newDateTime(java.util.Date[%s]) = %s", sdf.format(nowDate), sdf.format(DateTimeUtils.newDateTime(nowDate))));
        System.out.println(String.format("newBeginDate(java.util.Date[%s]) = %s", sdf.format(nowDate), sdf.format(DateTimeUtils.newBeginDate(nowDate))));
        System.out.println(String.format("newEndDate(java.util.Date[%s]) = %s", sdf.format(nowDate), sdf.format(DateTimeUtils.newEndDate(nowDate))));
        System.out.println(String.format("beginOfDate(java.util.Date[%s]) = %s", sdf.format(nowDate), sdf.format(DateTimeUtils.beginOfDate(nowDate))));
        System.out.println(String.format("endOfDate(java.util.Date[%s]) = %s", sdf.format(nowDate), sdf.format(DateTimeUtils.endOfDate(nowDate))));
        System.out.println(String.format("beginOfMonth(java.util.Date[%s]) = %s", sdf.format(nowDate), sdf.format(DateTimeUtils.beginOfMonth(nowDate))));
        System.out.println(String.format("endOfMonth(java.util.Date[%s]) = %s", sdf.format(nowDate), sdf.format(DateTimeUtils.endOfMonth(nowDate))));

        System.out.println("------- create by timestamp ------->");
        System.out.println(String.format("newDateTime(%s) = %s", timestamp, sdf.format(DateTimeUtils.newDateTime(timestamp))));
        System.out.println(String.format("newBeginTime(%s) = %s", timestamp, sdf.format(DateTimeUtils.newBeginDate(timestamp))));
        System.out.println(String.format("newEndTime(%s) = %s", timestamp, sdf.format(DateTimeUtils.newEndDate(timestamp))));
        System.out.println(String.format("beginOfDate(%s) = %s", timestamp, sdf.format(DateTimeUtils.beginOfDate(timestamp))));
        System.out.println(String.format("endOfDate(%s) = %s", timestamp, sdf.format(DateTimeUtils.endOfDate(timestamp))));

        System.out.println("------- create by number ------->");
        int[] array = DateTimeUtils.toArray(nowDate);
        int year = array[0], month = array[1], day = array[2], hour = array[4], minute = array[5], second = array[6], millisecond = array[7];
        System.out.println(String.format("newDateTime(%s, %s, %s, %s, %s, %s) = %s", year, month, day, hour, minute, second, sdf.format(DateTimeUtils.newDateTime(year, month, day, hour, minute, second))));
        System.out.println(String.format("newDateTime(%s, %s, %s, %s, %s, %s, %s) = %s", year, millisecond, day, hour, minute, second, millisecond, sdf.format(DateTimeUtils.newDateTime(year, month, day, hour, minute, second, millisecond))));
        System.out.println(String.format("newBeginDate(%s, %s, %s) = %s", year, month, day, sdf.format(DateTimeUtils.newBeginDate(year, month, day))));
        System.out.println(String.format("newEndDate(%s, %s, %s) = %s", year, month, day, sdf.format(DateTimeUtils.newEndDate(year, month, day))));
        System.out.println(String.format("newDateTimeOfNowDate(%s, %s, %s) = %s", year, month, day, sdf.format(DateTimeUtils.newDateTimeOfNowDate(hour, minute, second))));
        System.out.println(String.format("newDateTimeOfNowTime(%s, %s, %s) = %s", hour, minute, second, sdf.format(DateTimeUtils.newDateTimeOfNowTime(year, month, day))));

        System.out.println("------- create by timeZone ------->");
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println(String.format("newDateTime(%s) = %s", timeZone.getID(), sdf.format(DateTimeUtils.newDateTime(timeZone))));
        System.out.println(String.format("now(%s) = %s", timeZone.getID(), sdf.format(DateTimeUtils.now(timeZone))));

        System.out.println("------- create by array ------->");
        System.out.println(String.format("newDateTimeOfArray(%s) = %s", year, sdf.format(DateTimeUtils.newDateTimeOfArray(year))));
        System.out.println(String.format("newDateTimeOfArray(%s, %s) = %s", year, month, sdf.format(DateTimeUtils.newDateTimeOfArray(year, month))));
        System.out.println(String.format("newDateTimeOfArray(%s, %s, %s) = %s", year, month, day, sdf.format(DateTimeUtils.newDateTimeOfArray(year, month, day))));
        System.out.println(String.format("newDateTimeOfArray(%s, %s, %s, %s) = %s", year, month, day, hour, sdf.format(DateTimeUtils.newDateTimeOfArray(year, month, day, hour))));
        System.out.println(String.format("newDateTimeOfArray(%s, %s, %s, %s, %s) = %s", year, month, day, hour, minute, sdf.format(DateTimeUtils.newDateTimeOfArray(year, month, day, hour, minute))));
        System.out.println(String.format("newDateTimeOfArray(%s, %s, %s, %s, %s, %s) = %s", year, month, day, hour, minute, second, sdf.format(DateTimeUtils.newDateTimeOfArray(year, month, day, hour, minute, second))));
        System.out.println(String.format("newDateTimeOfArray(%s, %s, %s, %s, %s, %s, %s) = %s", year, month, day, hour, minute, second, millisecond, sdf.format(DateTimeUtils.newDateTimeOfArray(year, month, day, hour, minute, second, millisecond))));

        System.out.println("------- other date time ------->");
        System.out.println(String.format("yesterday() = %s", sdf.format(DateTimeUtils.yesterday())));
        System.out.println(String.format("beginOfYesterday() = %s", sdf.format(DateTimeUtils.beginOfYesterday())));
        System.out.println(String.format("endOfYesterday() = %s", sdf.format(DateTimeUtils.endOfYesterday())));
        System.out.println(String.format("today() = %s", sdf.format(DateTimeUtils.today())));
        System.out.println(String.format("beginOfToday() = %s", sdf.format(DateTimeUtils.beginOfToday())));
        System.out.println(String.format("endOfToday() = %s", sdf.format(DateTimeUtils.endOfToday())));
        System.out.println(String.format("tomorrow() = %s", sdf.format(DateTimeUtils.tomorrow())));
        System.out.println(String.format("beginOfTomorrow() = %s", sdf.format(DateTimeUtils.beginOfTomorrow())));
        System.out.println(String.format("endOfTomorrow() = %s", sdf.format(DateTimeUtils.endOfTomorrow())));

    }

    @Test
    public void rangeTest() {
        Date startDate = new Date();
        Date endDate = DateTimeUtils.addDays(startDate, 10);
        List<Date> dateList = DateTimeUtils.getDateList(startDate, endDate);
        System.out.println(String.format("getDateList(java.util.[%s], java.util.[%s]) = [%s]", sdf.format(startDate), sdf.format(endDate), listDateToString(dateList)));
        List<Date> dateList2 = DateTimeUtils.getDateList(startDate, endDate, 0, 6);
        System.out.println(String.format("getDateList(java.util.[%s], java.util.[%s], 6, 7) = [%s]", sdf.format(startDate), sdf.format(endDate), listDateToString(dateList2)));
    }

    private String listDateToString(final List<Date> dateList) {
        StringBuilder sb = new StringBuilder();
        dateList.forEach(new Consumer<Date>() {
            private int length = dateList.size();

            @Override
            public void accept(Date date) {
                sb.append(sdf.format(date));
                length--;
                if (length > 0) {
                    sb.append(", ");
                }
            }
        });
        return sb.toString();
    }

    @Test
    public void valueTest() {
        Date nowDate = new Date();
        long nowTimestamp = nowDate.getTime();
        Calendar nowCalendar = Calendar.getInstance();
        System.out.println("------- to timestamp ------->");
        System.out.println(String.format("nowTimestamp() = %s", DateTimeUtils.nowTimestamp()));
        System.out.println(String.format("timestamp(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.timestamp(nowDate)));

        System.out.println("------- to array ------->");
        System.out.println(String.format("toArray() = %s", Arrays.toString(DateTimeUtils.toArray())));
        System.out.println(String.format("toArray(java.util.Date[%s]) = %s", sdf.format(nowDate), Arrays.toString(DateTimeUtils.toArray(nowDate))));
        System.out.println(String.format("toArray(%s) = %s", nowTimestamp, Arrays.toString(DateTimeUtils.toArray(nowTimestamp))));
        System.out.println(String.format("toArray(java.util.Calendar[%s]) = %s", sdf.format(nowCalendar.getTime()), Arrays.toString(DateTimeUtils.toArray(nowCalendar))));

        System.out.println("------- year value ------->");
        System.out.println(String.format("nowYear() = %s", DateTimeUtils.nowYear()));
        System.out.println(String.format("getYear(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.getYear(nowDate)));
        System.out.println(String.format("getYear(%s) = %s", nowTimestamp, DateTimeUtils.getYear(nowTimestamp)));

        System.out.println("------- month value ------->");
        System.out.println(String.format("nowMonth() = %s", DateTimeUtils.nowMonth()));
        System.out.println(String.format("getMonth(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.getMonth(nowDate)));
        System.out.println(String.format("getMonth(%s) = %s", nowTimestamp, DateTimeUtils.getMonth(nowTimestamp)));

        System.out.println("------- week value ------->");
        System.out.println(String.format("nowWeek() = %s", DateTimeUtils.nowWeek()));
        System.out.println(String.format("getWeek(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.getWeek(nowDate)));
        System.out.println(String.format("getWeek(%s) = %s", nowTimestamp, DateTimeUtils.getWeek(nowTimestamp)));

        System.out.println("------- day value ------->");
        System.out.println(String.format("nowDay() = %s", DateTimeUtils.nowDay()));
        System.out.println(String.format("getDay(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.getDay(nowDate)));
        System.out.println(String.format("getDay(%s) = %s", nowTimestamp, DateTimeUtils.getDay(nowTimestamp)));

        System.out.println("------- hour value ------->");
        System.out.println(String.format("nowHour() = %s", DateTimeUtils.nowHour()));
        System.out.println(String.format("getHour(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.getHour(nowDate)));
        System.out.println(String.format("getHour(%s) = %s", nowTimestamp, DateTimeUtils.getHour(nowTimestamp)));

        System.out.println("------- minute value ------->");
        System.out.println(String.format("nowMinute() = %s", DateTimeUtils.nowMinute()));
        System.out.println(String.format("getMinute(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.getMinute(nowDate)));
        System.out.println(String.format("getMinute(%s) = %s", nowTimestamp, DateTimeUtils.getMinute(nowTimestamp)));

        System.out.println("------- second value ------->");
        System.out.println(String.format("nowSecond() = %s", DateTimeUtils.nowSecond()));
        System.out.println(String.format("getSecond(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.getSecond(nowDate)));
        System.out.println(String.format("getSecond(%s) = %s", nowTimestamp, DateTimeUtils.getSecond(nowTimestamp)));

        System.out.println("------- millisecond value ------->");
        System.out.println(String.format("nowMillisecond() = %s", DateTimeUtils.nowMillisecond()));
        System.out.println(String.format("getMillisecond(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.getMillisecond(nowDate)));
        System.out.println(String.format("getMillisecond(%s) = %s", nowTimestamp, DateTimeUtils.getMillisecond(nowTimestamp)));

        System.out.println("------- day of year value ------->");
        System.out.println(String.format("nowDayOfYear() = %s", DateTimeUtils.nowDayOfYear()));
        System.out.println(String.format("getDayOfYear(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.getDayOfYear(nowDate)));
        System.out.println(String.format("getDayOfYear(%s) = %s", nowTimestamp, DateTimeUtils.getDayOfYear(nowTimestamp)));

        System.out.println("------- day of month value ------->");
        System.out.println(String.format("nowDayOfMonth() = %s", DateTimeUtils.nowDayOfMonth()));
        System.out.println(String.format("getDayOfMonth(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.getDayOfMonth(nowDate)));
        System.out.println(String.format("getDayOfMonth(%s) = %s", nowTimestamp, DateTimeUtils.getDayOfMonth(nowTimestamp)));

        System.out.println("------- week of year value ------->");
        System.out.println(String.format("nowWeekOfYear() = %s", DateTimeUtils.nowWeekOfYear()));
        System.out.println(String.format("getWeekOfYear(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.getWeekOfYear(nowDate)));
        System.out.println(String.format("getWeekOfYear(%s) = %s", nowTimestamp, DateTimeUtils.getWeekOfYear(nowTimestamp)));

        System.out.println("------- week of month value ------->");
        System.out.println(String.format("nowWeekOfMonth() = %s", DateTimeUtils.nowWeekOfMonth()));
        System.out.println(String.format("getWeekOfMonth(java.util.Date[%s]) = %s", sdf.format(nowDate), DateTimeUtils.getWeekOfMonth(nowDate)));
        System.out.println(String.format("getWeekOfMonth(%s) = %s", nowTimestamp, DateTimeUtils.getWeekOfMonth(nowTimestamp)));

        System.out.println("------- is leap value ------->");
        System.out.println(String.format("nowYearIsLeap() =  %s", DateTimeUtils.nowYearIsLeap()));
        System.out.println(String.format("isLeapYear(2020) =  %s", DateTimeUtils.isLeapYear(2020)));
        System.out.println(String.format("isLeapYear(java.util.Date[%s]) =  %s", sdf.format(nowDate), DateTimeUtils.isLeapYear(nowDate)));
        System.out.println(String.format("isLeapYear(%s) =  %s", nowTimestamp, DateTimeUtils.isLeapYear(nowTimestamp)));

        System.out.println("------- year days value ------->");
        System.out.println(String.format("nowYearDays() =  %s", DateTimeUtils.nowYearDays()));
        System.out.println(String.format("getDaysOfYear(2020) =  %s", DateTimeUtils.getDaysOfYear(2020)));
        System.out.println(String.format("getDaysOfYear(java.util.Date[%s]) =  %s", sdf.format(nowDate), DateTimeUtils.getDaysOfYear(nowDate)));
        System.out.println(String.format("getDaysOfYear(%s) =  %s", nowTimestamp, DateTimeUtils.getDaysOfYear(nowTimestamp)));

        System.out.println("------- month days value ------->");
        System.out.println(String.format("nowMonthDays() =  %s", DateTimeUtils.nowMonthDays()));
        System.out.println(String.format("getDaysOfMonth(2020, 2) =  %s", DateTimeUtils.getDaysOfMonth(2020, 2)));
        System.out.println(String.format("getDaysOfMonth(java.util.Date[%s]) =  %s", sdf.format(nowDate), DateTimeUtils.getDaysOfMonth(nowDate)));
        System.out.println(String.format("getDaysOfMonth(%s) =  %s", nowTimestamp, DateTimeUtils.getDaysOfMonth(nowTimestamp)));

    }

    @Test
    public void calcTest() {
        Date date = new Date();
        System.out.println("------- add/minus years ------->");
        System.out.println(String.format("addYear(%s, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addYears(date, 2))));
        System.out.println(String.format("addYear(%s, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addYears(date, -2))));
        System.out.println(String.format("addYear(%s, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addYears(date, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addYear(%s, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addYears(date, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addYear(%s, 2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addYears(date, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("addYear(%s, -2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addYears(date, -2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusYears(%s, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusYears(date, 2))));
        System.out.println(String.format("minusYears(%s, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusYears(date, -2))));
        System.out.println(String.format("minusYears(%s, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusYears(date, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusYears(%s, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusYears(date, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusYears(%s, 2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusYears(date, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusYears(%s, -2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusYears(date, -2, DateTimeUtils.TimeMode.END_OF_DAY))));

        System.out.println("------- add/minus months ------->");
        System.out.println(String.format("addMonths(2) = %s", sdf.format(DateTimeUtils.addMonths(2))));
        System.out.println(String.format("addMonths(-2) = %s", sdf.format(DateTimeUtils.addMonths(-2))));
        System.out.println(String.format("addMonths(%s, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addMonths(date, 2))));
        System.out.println(String.format("addMonths(%s, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addMonths(date, -2))));
        System.out.println(String.format("addMonths(%s, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addMonths(date, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addMonths(%s, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addMonths(date, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addMonths(%s, 2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addMonths(date, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("addMonths(%s, -2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addMonths(date, -2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusMonths(2) = %s", sdf.format(DateTimeUtils.minusMonths(2))));
        System.out.println(String.format("minusMonths(-2) = %s", sdf.format(DateTimeUtils.minusMonths(-2))));
        System.out.println(String.format("minusMonths(%s, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusMonths(date, 2))));
        System.out.println(String.format("minusMonths(%s, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusMonths(date, -2))));
        System.out.println(String.format("minusMonths(%s, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusMonths(date, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusMonths(%s, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusMonths(date, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusMonths(%s, 2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusMonths(date, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusMonths(%s, -2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusMonths(date, -2, DateTimeUtils.TimeMode.END_OF_DAY))));

        System.out.println("------- add/minus days ------->");
        System.out.println(String.format("addDays(2) = %s", sdf.format(DateTimeUtils.addDays(2))));
        System.out.println(String.format("addDays(-2) = %s", sdf.format(DateTimeUtils.addDays(-2))));
        System.out.println(String.format("addDays(%s, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addDays(date, 2))));
        System.out.println(String.format("addDays(%s, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addDays(date, -2))));
        System.out.println(String.format("addDays(%s, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addDays(date, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addDays(%s, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addDays(date, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addDays(%s, 2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addDays(date, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("addDays(%s, -2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addDays(date, -2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusDays(2) = %s", sdf.format(DateTimeUtils.minusDays(2))));
        System.out.println(String.format("minusDays(-2) = %s", sdf.format(DateTimeUtils.minusDays(-2))));
        System.out.println(String.format("minusDays(%s, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusDays(date, 2))));
        System.out.println(String.format("minusDays(%s, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusDays(date, -2))));
        System.out.println(String.format("minusDays(%s, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusDays(date, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusDays(%s, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusDays(date, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusDays(%s, 2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusDays(date, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusDays(%s, -2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusDays(date, -2, DateTimeUtils.TimeMode.END_OF_DAY))));

        System.out.println("------- add/minus hours ------->");
        System.out.println(String.format("addHours(2) = %s", sdf.format(DateTimeUtils.addHours(2))));
        System.out.println(String.format("addHours(-2) = %s", sdf.format(DateTimeUtils.addHours(-2))));
        System.out.println(String.format("addHours(%s, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addHours(date, 2))));
        System.out.println(String.format("addHours(%s, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addHours(date, -2))));
        System.out.println(String.format("addHours(%s, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addHours(date, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addHours(%s, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addHours(date, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addHours(%s, 2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addHours(date, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("addHours(%s, -2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addHours(date, -2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusHours(2) = %s", sdf.format(DateTimeUtils.minusHours(2))));
        System.out.println(String.format("minusHours(-2) = %s", sdf.format(DateTimeUtils.minusHours(-2))));
        System.out.println(String.format("minusHours(%s, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusHours(date, 2))));
        System.out.println(String.format("minusHours(%s, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusHours(date, -2))));
        System.out.println(String.format("minusHours(%s, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusHours(date, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusHours(%s, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusHours(date, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusHours(%s, 2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusHours(date, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusHours(%s, -2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusHours(date, -2, DateTimeUtils.TimeMode.END_OF_DAY))));

        System.out.println("------- add/minus minutes ------->");
        System.out.println(String.format("addMinutes(2) = %s", sdf.format(DateTimeUtils.addMinutes(2))));
        System.out.println(String.format("addMinutes(-2) = %s", sdf.format(DateTimeUtils.addMinutes(-2))));
        System.out.println(String.format("addMinutes(%s, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addMinutes(date, 2))));
        System.out.println(String.format("addMinutes(%s, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addMinutes(date, -2))));
        System.out.println(String.format("addMinutes(%s, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addMinutes(date, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addMinutes(%s, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addMinutes(date, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addMinutes(%s, 2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addMinutes(date, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("addMinutes(%s, -2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addMinutes(date, -2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusMinutes(2) = %s", sdf.format(DateTimeUtils.minusMinutes(2))));
        System.out.println(String.format("minusMinutes(-2) = %s", sdf.format(DateTimeUtils.minusMinutes(-2))));
        System.out.println(String.format("minusMinutes(%s, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusMinutes(date, 2))));
        System.out.println(String.format("minusMinutes(%s, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusMinutes(date, -2))));
        System.out.println(String.format("minusMinutes(%s, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusMinutes(date, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusMinutes(%s, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusMinutes(date, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusMinutes(%s, 2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusMinutes(date, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusMinutes(%s, -2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusMinutes(date, -2, DateTimeUtils.TimeMode.END_OF_DAY))));

        System.out.println("------- add/minus seconds ------->");
        System.out.println(String.format("addSeconds(%s, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addSeconds(date, 2))));
        System.out.println(String.format("addSeconds(%s, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addSeconds(date, -2))));
        System.out.println(String.format("addSeconds(%s, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addSeconds(date, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addSeconds(%s, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addSeconds(date, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addSeconds(%s, 2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addSeconds(date, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("addSeconds(%s, -2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.addSeconds(date, -2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusSeconds(%s, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusSeconds(date, 2))));
        System.out.println(String.format("minusSeconds(%s, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusSeconds(date, -2))));
        System.out.println(String.format("minusSeconds(%s, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusSeconds(date, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusSeconds(%s, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusSeconds(date, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusSeconds(%s, 2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusSeconds(date, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusSeconds(%s, -2, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusSeconds(date, -2, DateTimeUtils.TimeMode.END_OF_DAY))));

        System.out.println("------- add/minus months & days ------->");
        System.out.println(String.format("addMD(%s, 1, 2) =  %s", sdf.format(date), sdf.format(DateTimeUtils.addMD(date, 1, 2))));
        System.out.println(String.format("addMD(%s, -1, -2) =  %s", sdf.format(date), sdf.format(DateTimeUtils.addMD(date, -1, -2))));
        System.out.println(String.format("addMD(%s, 1, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) =  %s", sdf.format(date), sdf.format(DateTimeUtils.addMD(date, 1, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addMD(%s, -1, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) =  %s", sdf.format(date), sdf.format(DateTimeUtils.addMD(date, -1, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("addMD(%s, 1, 2, DateTimeUtils.TimeMode.END_OF_DAY) =  %s", sdf.format(date), sdf.format(DateTimeUtils.addMD(date, 1, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("addMD(%s, -1, -2, DateTimeUtils.TimeMode.END_OF_DAY) =  %s", sdf.format(date), sdf.format(DateTimeUtils.addMD(date, -1, -2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusMD(%s, 1, 2) =  %s", sdf.format(date), sdf.format(DateTimeUtils.minusMD(date, 1, 2))));
        System.out.println(String.format("minusMD(%s, -1, -2) =  %s", sdf.format(date), sdf.format(DateTimeUtils.minusMD(date, -1, -2))));
        System.out.println(String.format("minusMD(%s, 1, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) =  %s", sdf.format(date), sdf.format(DateTimeUtils.minusMD(date, 1, 2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusMD(%s, -1, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY) =  %s", sdf.format(date), sdf.format(DateTimeUtils.minusMD(date, -1, -2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("minusMD(%s, 1, 2, DateTimeUtils.TimeMode.END_OF_DAY) =  %s", sdf.format(date), sdf.format(DateTimeUtils.minusMD(date, 1, 2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("minusMD(%s, -1, -2, DateTimeUtils.TimeMode.END_OF_DAY) =  %s", sdf.format(date), sdf.format(DateTimeUtils.minusMD(date, -1, -2, DateTimeUtils.TimeMode.END_OF_DAY))));

        System.out.println("------- add/minus hours & minutes ------->");
        System.out.println(String.format("addHM(%s, 1, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addHM(date, 1, 2))));
        System.out.println(String.format("addHM(%s, -1, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.addHM(date, -1, -2))));
        System.out.println(String.format("minusHM(%s, 1, 2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusHM(date, 1, 2))));
        System.out.println(String.format("minusHM(%s, -1, -2) = %s", sdf.format(date), sdf.format(DateTimeUtils.minusHM(date, -1, -2))));

    }

    @Test
    public void compareTest() {
        Date date = new Date();
        Date date2 = DateTimeUtils.addDays(date, 23);
        date2 = DateTimeUtils.addHours(date2, 3);
        date2 = DateTimeUtils.addMinutes(date2, 10);
        date2 = DateTimeUtils.addSeconds(date2, 43);

        System.out.println("------- diff value ------->");
        System.out.println(String.format("diffArray(%s, DateTimeUtils.TimeMode.NOW_OF_DAY) = %s", sdf.format(date2), Arrays.toString(DateTimeUtils.diffArray(date2, DateTimeUtils.TimeMode.NOW_OF_DAY))));
        System.out.println(String.format("diffArray(%s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date2), Arrays.toString(DateTimeUtils.diffArray(date2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("diffArray(%s, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date2), Arrays.toString(DateTimeUtils.diffArray(date2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("diffArray(%s, %s) = %s", sdf.format(date), sdf.format(date2), Arrays.toString(DateTimeUtils.diffArray(date, date2))));
        System.out.println(String.format("diffArray(%s, %s) = %s", sdf.format(date2), sdf.format(date), Arrays.toString(DateTimeUtils.diffArray(date2, date))));
        System.out.println(String.format("diffArray(%s, %s, DateTimeUtils.TimeMode.NOW_OF_DAY) = %s", sdf.format(date), sdf.format(date2), Arrays.toString(DateTimeUtils.diffArray(date, date2, DateTimeUtils.TimeMode.NOW_OF_DAY))));
        System.out.println(String.format("diffArray(%s, %s, DateTimeUtils.TimeMode.NOW_OF_DAY) = %s", sdf.format(date2), sdf.format(date), Arrays.toString(DateTimeUtils.diffArray(date2, date, DateTimeUtils.TimeMode.NOW_OF_DAY))));
        System.out.println(String.format("diffArray(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(date2), Arrays.toString(DateTimeUtils.diffArray(date, date2, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("diffArray(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date2), sdf.format(date), Arrays.toString(DateTimeUtils.diffArray(date2, date, DateTimeUtils.TimeMode.BEGIN_OF_DAY))));
        System.out.println(String.format("diffArray(%s, %s, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(date2), Arrays.toString(DateTimeUtils.diffArray(date, date2, DateTimeUtils.TimeMode.END_OF_DAY))));
        System.out.println(String.format("diffArray(%s, %s, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date2), sdf.format(date), Arrays.toString(DateTimeUtils.diffArray(date2, date, DateTimeUtils.TimeMode.END_OF_DAY))));

        System.out.println(String.format("diffDays(%s) = %s", sdf.format(date2), DateTimeUtils.diffDays(date2)));
        System.out.println(String.format("diffDays(%s, DateTimeUtils.TimeMode.NOW_OF_DAY) = %s", sdf.format(date2), DateTimeUtils.diffDays(date2, DateTimeUtils.TimeMode.NOW_OF_DAY)));
        System.out.println(String.format("diffDays(%s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date2), DateTimeUtils.diffDays(date2, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("diffDays(%s, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date2), DateTimeUtils.diffDays(date2, DateTimeUtils.TimeMode.END_OF_DAY)));
        System.out.println(String.format("diffDays(%s, %s) = %s", sdf.format(date), sdf.format(date2), DateTimeUtils.diffDays(date, date2)));
        System.out.println(String.format("diffDays(%s, %s) = %s", sdf.format(date2), sdf.format(date), DateTimeUtils.diffDays(date2, date)));
        System.out.println(String.format("diffDays(%s, %s, DateTimeUtils.TimeMode.NOW_OF_DAY) = %s", sdf.format(date), sdf.format(date2), DateTimeUtils.diffDays(date, date2, DateTimeUtils.TimeMode.NOW_OF_DAY)));
        System.out.println(String.format("diffDays(%s, %s, DateTimeUtils.TimeMode.NOW_OF_DAY) = %s", sdf.format(date2), sdf.format(date), DateTimeUtils.diffDays(date2, date, DateTimeUtils.TimeMode.NOW_OF_DAY)));
        System.out.println(String.format("diffDays(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(date2), DateTimeUtils.diffDays(date, date2, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("diffDays(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date2), sdf.format(date), DateTimeUtils.diffDays(date2, date, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("diffDays(%s, %s, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date), sdf.format(date2), DateTimeUtils.diffDays(date, date2, DateTimeUtils.TimeMode.END_OF_DAY)));
        System.out.println(String.format("diffDays(%s, %s, DateTimeUtils.TimeMode.END_OF_DAY) = %s", sdf.format(date2), sdf.format(date), DateTimeUtils.diffDays(date2, date, DateTimeUtils.TimeMode.END_OF_DAY)));

        System.out.println(String.format("intervalDays(%s) = %s", sdf.format(date2), DateTimeUtils.intervalDays(date2)));
        System.out.println(String.format("intervalDays(%s, %s) = %s", sdf.format(date), sdf.format(date2), DateTimeUtils.intervalDays(date, date2)));
        System.out.println(String.format("intervalDays(%s, %s) = %s", sdf.format(date2), sdf.format(date), DateTimeUtils.intervalDays(date2, date)));

        System.out.println(String.format("spanDays(%s) = %s", sdf.format(date2), DateTimeUtils.spanDays(date2)));
        System.out.println(String.format("spanDays(%s, %s) = %s", sdf.format(date), sdf.format(date2), DateTimeUtils.spanDays(date, date2)));
        System.out.println(String.format("spanDays(%s, %s) = %s", sdf.format(date2), sdf.format(date), DateTimeUtils.spanDays(date2, date)));


        System.out.println("------- date compare ------->");
        Date date3 = new Date();
        date3 = DateTimeUtils.addMinutes(date3, 20);
        System.out.println(String.format("compareWithNow(%s) = %s", sdf.format(date3), DateTimeUtils.compareWithNow(date3)));
        System.out.println(String.format("compareWithNow(%s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), DateTimeUtils.compareWithNow(date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("compare(%s, %s) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.compare(date, date3)));
        System.out.println(String.format("compare(%s, %s) = %s", sdf.format(date3), sdf.format(date), DateTimeUtils.compare(date3, date)));
        System.out.println(String.format("compare(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.compare(date, date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("compare(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), sdf.format(date), DateTimeUtils.compare(date3, date, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));

        System.out.println(String.format("beforeOfNow(%s) = %s", sdf.format(date3), DateTimeUtils.beforeOfNow(date3)));
        System.out.println(String.format("beforeOfNow(%s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), DateTimeUtils.beforeOfNow(date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("before(%s, %s) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.before(date, date3)));
        System.out.println(String.format("before(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.before(date, date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("before(%s, %s) = %s", sdf.format(date3), sdf.format(date), DateTimeUtils.before(date3, date)));
        System.out.println(String.format("before(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), sdf.format(date), DateTimeUtils.before(date3, date, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));

        System.out.println(String.format("afterOfNow(%s) = %s", sdf.format(date3), DateTimeUtils.afterOfNow(date3)));
        System.out.println(String.format("afterOfNow(%s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), DateTimeUtils.afterOfNow(date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("after(%s, %s) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.after(date, date3)));
        System.out.println(String.format("after(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.after(date, date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("after(%s, %s) = %s", sdf.format(date3), sdf.format(date), DateTimeUtils.after(date3, date)));
        System.out.println(String.format("after(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), sdf.format(date), DateTimeUtils.after(date3, date, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));

        System.out.println(String.format("equalsOfNow(%s) = %s", sdf.format(date3), DateTimeUtils.equalsOfNow(date3)));
        System.out.println(String.format("equalsOfNow(%s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), DateTimeUtils.equalsOfNow(date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("equals(%s, %s) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.equals(date, date3)));
        System.out.println(String.format("equals(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.equals(date, date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("equals(%s, %s) = %s", sdf.format(date3), sdf.format(date), DateTimeUtils.equals(date3, date)));
        System.out.println(String.format("equals(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), sdf.format(date), DateTimeUtils.equals(date3, date, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));

        System.out.println(String.format("beforeOrEqualsOfNow(%s) = %s", sdf.format(date3), DateTimeUtils.beforeOrEqualsOfNow(date3)));
        System.out.println(String.format("beforeOrEqualsOfNow(%s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), DateTimeUtils.beforeOrEqualsOfNow(date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("beforeOrEquals(%s, %s) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.beforeOrEquals(date, date3)));
        System.out.println(String.format("beforeOrEquals(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.beforeOrEquals(date, date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("beforeOrEquals(%s, %s) = %s", sdf.format(date3), sdf.format(date), DateTimeUtils.beforeOrEquals(date3, date)));
        System.out.println(String.format("beforeOrEquals(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), sdf.format(date), DateTimeUtils.beforeOrEquals(date3, date, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));

        System.out.println(String.format("afterOrEqualsOfNow(%s) = %s", sdf.format(date3), DateTimeUtils.afterOrEqualsOfNow(date3)));
        System.out.println(String.format("afterOrEqualsOfNow(%s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), DateTimeUtils.afterOrEqualsOfNow(date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("afterOrEquals(%s, %s) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.afterOrEquals(date, date3)));
        System.out.println(String.format("afterOrEquals(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.afterOrEquals(date, date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("afterOrEquals(%s, %s) = %s", sdf.format(date3), sdf.format(date), DateTimeUtils.afterOrEquals(date3, date)));
        System.out.println(String.format("afterOrEquals(%s, %s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), sdf.format(date), DateTimeUtils.afterOrEquals(date3, date, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));

        System.out.println(String.format("sameDay(%s, %s) = %s", sdf.format(date), sdf.format(date3), DateTimeUtils.sameDay(date, date3)));
        System.out.println(String.format("sameDay(%s, %s, %s) = %s", sdf.format(date), sdf.format(date2), sdf.format(date3), DateTimeUtils.sameDay(date, date2, date3)));

        System.out.println(String.format("isYesterday(%s) = %s", sdf.format(date3), DateTimeUtils.isYesterday(date3)));
        System.out.println(String.format("isToday(%s) = %s", sdf.format(date3), DateTimeUtils.isToday(date3)));
        System.out.println(String.format("isTomorrow(%s) = %s", sdf.format(date3), DateTimeUtils.isTomorrow(date3)));

        System.out.println(String.format("beforeOfTomorrow(%s) = %s", sdf.format(date3), DateTimeUtils.beforeOfTomorrow(date3)));
        System.out.println(String.format("beforeOfTomorrow(%s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), DateTimeUtils.beforeOfTomorrow(date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));
        System.out.println(String.format("beforeOfTomorrow(%s) = %s", sdf.format(date3), DateTimeUtils.afterOfYesterday(date3)));
        System.out.println(String.format("beforeOfTomorrow(%s, DateTimeUtils.TimeMode.BEGIN_OF_DAY) = %s", sdf.format(date3), DateTimeUtils.afterOfYesterday(date3, DateTimeUtils.TimeMode.BEGIN_OF_DAY)));

    }

    @Test
    public void formatTest() {
        Calendar calendar = Calendar.getInstance();
        Date nowDate = new Date();
        long nowTimestamp = nowDate.getTime();
        String sourceStyle = "yyyy-MM-dd HH:mm:ss:SSS";
        String style = "yyyy年MM月dd日 HH:mm:ss.SSS";

        System.out.println("------- datetime format ------->");

        System.out.println(String.format("format(%s) = %s", sdf.format(nowDate), DateTimeUtils.format(nowDate)));
        System.out.println(String.format("format(%s) = %s", nowTimestamp, DateTimeUtils.format(nowTimestamp)));
        System.out.println(String.format("format(%s) = %s", sdf.format(calendar.getTime()), DateTimeUtils.format(calendar)));

        System.out.println(String.format("format(%s, Locale.ENGLISH) = %s", sdf.format(nowDate), DateTimeUtils.format(nowDate, Locale.ENGLISH)));
        System.out.println(String.format("format(%s, Locale.ENGLISH) = %s", nowTimestamp, DateTimeUtils.format(nowTimestamp, Locale.ENGLISH)));
        System.out.println(String.format("format(%s, Locale.ENGLISH) = %s", sdf.format(calendar.getTime()), DateTimeUtils.format(calendar, Locale.ENGLISH)));

        System.out.println(String.format("format(%s, %s) = %s", sdf.format(nowDate), TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.format(nowDate, TimeZone.getTimeZone("GMT+2"))));
        System.out.println(String.format("format(%s, %s) = %s", nowTimestamp, TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.format(nowTimestamp, TimeZone.getTimeZone("GMT+2"))));
        System.out.println(String.format("format(%s, %s) = %s", sdf.format(calendar.getTime()), TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.format(calendar, TimeZone.getTimeZone("GMT+2"))));

        System.out.println(String.format("format(%s, %s) = %s", sdf.format(nowDate), style, DateTimeUtils.format(nowDate, style)));
        System.out.println(String.format("format(%s, %s) = %s", nowTimestamp, style, DateTimeUtils.format(nowTimestamp, style)));
        System.out.println(String.format("format(%s, %s) = %s", sdf.format(calendar.getTime()), style, DateTimeUtils.format(calendar, style)));

        System.out.println(String.format("format(%s, %s, Locale.ENGLISH) = %s", sdf.format(nowDate), style, DateTimeUtils.format(nowDate, style, Locale.ENGLISH)));
        System.out.println(String.format("format(%s, %s, Locale.ENGLISH) = %s", nowTimestamp, style, DateTimeUtils.format(nowTimestamp, style, Locale.ENGLISH)));
        System.out.println(String.format("format(%s, %s, Locale.ENGLISH) = %s", sdf.format(calendar.getTime()), style, DateTimeUtils.format(calendar, style, Locale.ENGLISH)));

        System.out.println(String.format("format(%s, %s, %s) = %s", sdf.format(nowDate), style, TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.format(nowDate, style, TimeZone.getTimeZone("GMT+2"))));
        System.out.println(String.format("format(%s, %s, %s) = %s", nowTimestamp, style, TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.format(nowTimestamp, style, TimeZone.getTimeZone("GMT+2"))));
        System.out.println(String.format("format(%s, %s, %s) = %s", sdf.format(calendar.getTime()), style, TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.format(calendar, style, TimeZone.getTimeZone("GMT+2"))));

        System.out.println(String.format("format(%s, %s, %s, Locale.ENGLISH) = %s", sdf.format(nowDate), style, TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.format(nowDate, style, TimeZone.getTimeZone("GMT+2"), Locale.ENGLISH)));
        System.out.println(String.format("format(%s, %s, %s, Locale.ENGLISH) = %s", nowTimestamp, style, TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.format(nowTimestamp, style, TimeZone.getTimeZone("GMT+2"), Locale.ENGLISH)));
        System.out.println(String.format("format(%s, %s, %s, Locale.ENGLISH) = %s", sdf.format(calendar.getTime()), style, TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.format(calendar, style, TimeZone.getTimeZone("GMT+2"), Locale.ENGLISH)));

        System.out.println(String.format("format(%s, %s) = %s", DateTimeUtils.format(nowDate), style, DateTimeUtils.format(DateTimeUtils.format(nowDate), style)));
        System.out.println(String.format("format(%s, %s, %s) = %s", sdf.format(nowDate), sourceStyle, style, DateTimeUtils.format(sdf.format(nowDate), sourceStyle, style)));

        System.out.println("------- date format ------->");

        System.out.println(String.format("formatDate(%s) = %s", sdf.format(nowDate), DateTimeUtils.formatDate(nowDate)));
        System.out.println(String.format("formatDate(%s) = %s", nowTimestamp, DateTimeUtils.formatDate(nowTimestamp)));

        System.out.println(String.format("formatDate(%s, Locale.ENGLISH) = %s", sdf.format(nowDate), DateTimeUtils.formatDate(nowDate, Locale.ENGLISH)));
        System.out.println(String.format("formatDate(%s, Locale.ENGLISH) = %s", nowTimestamp, DateTimeUtils.formatDate(nowTimestamp, Locale.ENGLISH)));

        System.out.println(String.format("formatDate(%s, %s) = %s", sdf.format(nowDate), TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.formatDate(nowDate, TimeZone.getTimeZone("GMT+2"))));
        System.out.println(String.format("formatDate(%s, %s) = %s", nowTimestamp, TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.formatDate(nowTimestamp, TimeZone.getTimeZone("GMT+2"))));

        System.out.println(String.format("formatDateOfNow() = %s", DateTimeUtils.formatDateOfNow()));
        System.out.println(String.format("formatDateOfNow(%s) = %s", TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.formatDateOfNow(TimeZone.getTimeZone("GMT+2"))));
        System.out.println(String.format("formatDateOfNow(Locale.ENGLISH) = %s", DateTimeUtils.formatDateOfNow(Locale.ENGLISH)));

        System.out.println("------- time format ------->");

        System.out.println(String.format("formatTime(%s) = %s", sdf.format(nowDate), DateTimeUtils.formatTime(nowDate)));
        System.out.println(String.format("formatTime(%s) = %s", nowTimestamp, DateTimeUtils.formatTime(nowTimestamp)));

        System.out.println(String.format("formatTime(%s, Locale.ENGLISH) = %s", sdf.format(nowDate), DateTimeUtils.formatTime(nowDate, Locale.ENGLISH)));
        System.out.println(String.format("formatTime(%s, Locale.ENGLISH) = %s", nowTimestamp, DateTimeUtils.formatTime(nowTimestamp, Locale.ENGLISH)));

        System.out.println(String.format("formatTime(%s, %s) = %s", sdf.format(nowDate), TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.formatTime(nowDate, TimeZone.getTimeZone("GMT+2"))));
        System.out.println(String.format("formatTime(%s, %s) = %s", nowTimestamp, TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.formatTime(nowTimestamp, TimeZone.getTimeZone("GMT+2"))));

        System.out.println(String.format("formatTimeOfNow() = %s", DateTimeUtils.formatTimeOfNow()));
        System.out.println(String.format("formatTimeOfNow(%s) = %s", TimeZone.getTimeZone("GMT+2").getID(), DateTimeUtils.formatTimeOfNow(TimeZone.getTimeZone("GMT+2"))));
        System.out.println(String.format("formatTimeOfNow(Locale.ENGLISH) = %s", DateTimeUtils.formatTimeOfNow(Locale.ENGLISH)));

    }

    @Test
    public void parseTest() {
        Date nowDate = new Date();
        long nowTimestamp = nowDate.getTime();
        String style = "yyyy年MM月dd日 HH:mm:ss.SSS";
        String nowDateStr = DateTimeUtils.format(nowDate);
        java.sql.Date sqlDate = new java.sql.Date(nowDate.getTime());
        Timestamp timestamp = new Timestamp(sqlDate.getTime());

        System.out.println("------- date parse ------->");

        System.out.println(String.format("parse(%s) = %s", nowTimestamp, DateTimeUtils.parse(nowTimestamp)));
        System.out.println(String.format("parse(%s) = %s", DateTimeUtils.formatDate(sqlDate), DateTimeUtils.parse(sqlDate)));
        System.out.println(String.format("parse(%s) = %s", timestamp, DateTimeUtils.parse(timestamp)));
        System.out.println(String.format("parse(%s) = %s", nowDateStr, DateTimeUtils.parse(nowDateStr)));
        System.out.println(String.format("parse(%s, %s) = %s", DateTimeUtils.format(nowDate, style), style, DateTimeUtils.parse(DateTimeUtils.format(nowDate, style), style)));
        System.out.println(String.format("parseSQLDate(%s) = %s", nowDate, DateTimeUtils.parseSQLDate(nowDate)));
        System.out.println(String.format("parseSQLDate(%s) = %s", nowTimestamp, DateTimeUtils.parseSQLDate(nowTimestamp)));
        System.out.println(String.format("parseSQLTimestamp(%s) = %s", nowDate, DateTimeUtils.parseSQLTimestamp(nowDate)));
        System.out.println(String.format("parseSQLTimestamp(%s) = %s", nowTimestamp, DateTimeUtils.parseSQLTimestamp(nowTimestamp)));
        System.out.println(String.format("tryToParse(%s, %s, yyyy/MM/dd HH:mm:ss) = %s", nowDateStr, style, DateTimeUtils.tryToParse(nowDateStr, style, "yyyy/MM/dd HH:mm:ss")));
    }


    @Test
    public void createOfOtherTest() {

        System.out.println("------- create of other date ------->");

        System.out.println(String.format("newSQLTimestamp() = %s", DateTimeUtils.newSQLTimestamp()));
        System.out.println(String.format("newSQLDateTime() = %s", DateTimeUtils.newSQLDateTime()));

    }

}
