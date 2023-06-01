/*
 * Copyright (c) 2019-2023 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.datetime.DateValue
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2023-05-18
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 日期值对象定义，用来解构一个日期时间对象或者日历对象。
 * <br>
 * 通过提供年、月、日来描述一个日期值
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.datetime</code></li>
 *   <li>Class Name: <code>DateValue</code></li>
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
 *         <td align="center"><em>2023-05-18</em></td>
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
public class DateValue implements Comparable<DateValue> {

    /**
     * 年份值
     */
    private int year;

    /**
     * 月份值
     */
    private int month;

    /**
     * 日期值
     */
    private int day;

    /**
     * 星期值
     */
    private int week;

    /**
     * 当前星期是一年中的第几周
     */
    private int weekOfYear;

    /**
     * 当前星期是当前月视图中的第几周
     */
    private int weekOfMonth;

    /**
     * 一年中的第几天
     */
    private int dayOfYear;

    /**
     * 当新签是一个月中的第几周，将每月1号作为当月周计算的第一天
     */
    private int dayOfWeekInMonth;

    private static void checkValue(int year, int month, int day) {
        if (year < 1) {
            throw new IllegalArgumentException("value range of year must be greater than 1");
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("value range of month is [1,12]");
        }
        int daysOfMonth = getDaysOfMonth(year, month);
        if (day < 1 || day > daysOfMonth) {
            throw new IllegalArgumentException("value range of day is [1," + daysOfMonth + "]");
        }
    }

    private static int getDaysOfMonth(int year, int month) {
        int daysOfMonth = 31;
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                daysOfMonth = 30;
                break;
            case 2:
                boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
                daysOfMonth = isLeapYear ? 29 : 28;
            default:
                break;
        }
        return daysOfMonth;
    }

    private DateValue(int year, int month, int day) {
        checkValue(year, month, day);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * 基于年、月、日的数字创建一个日期值对象
     *
     * @param year  年份值
     * @param month 月份值
     * @param day   日期值
     * @return 日期值对象
     */
    public static DateValue of(int year, int month, int day) {
        checkValue(year, month, day);
        DateValue dateValue = new DateValue(year, month, day);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        dateValue.week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        dateValue.weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        dateValue.weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        dateValue.dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        dateValue.dayOfWeekInMonth = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        return dateValue;
    }

    /**
     * 基于日历对象创建一个日期值对象
     *
     * @param calendar 日历对象
     * @return 日期值对象
     */
    public static DateValue ofCalendar(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DateValue(year, month, day);
    }

    /**
     * 基于日期对象构建一个日期值对象
     *
     * @param date 日期对象
     * @return 日期值对象
     * @see #ofCalendar(Calendar)
     */
    public static DateValue ofDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return ofCalendar(calendar);
    }

    /**
     * 基于时间戳构建一个日期值对象
     *
     * @param timestamp 时间戳
     * @return 日期值对象
     * @see #ofDate(Date)
     */
    public static DateValue ofTimestamp(long timestamp) {
        return ofDate(new Date(timestamp));
    }

    /**
     * 基于本地日期对象构建一个日期值对象
     *
     * @param localDate 本地日期对象
     * @return 日期值对象
     * @see LocalDate#getYear()
     * @see LocalDate#getMonth()
     * @see LocalDate#getDayOfMonth()
     * @see #of(int, int, int)
     */
    public static DateValue ofLocalDate(LocalDate localDate) {
        return of(localDate.getYear(), localDate.getMonth().getValue(), localDate.getDayOfMonth());
    }

    /**
     * 基于本地日期时间对象构建一个日期值对象
     *
     * @param localDateTime 本地日期时间对象
     * @return 日期值对象
     * @see LocalDateTime#getYear() ()
     * @see LocalDateTime#getMonth() ()
     * @see LocalDateTime#getDayOfMonth() ()
     * @see #of(int, int, int)
     */
    public static DateValue ofLocalDateTime(LocalDateTime localDateTime) {
        return of(localDateTime.getYear(), localDateTime.getMonth().getValue(), localDateTime.getDayOfMonth());
    }

    /**
     * 基于一个SQL日期对象构建一个日期值对象
     *
     * @param sqlDate SQL日期对象
     * @return 日期值对象
     * @see #ofTimestamp(long)
     */
    public static DateValue ofSQLDate(java.sql.Date sqlDate) {
        return ofTimestamp(sqlDate.getTime());
    }

    /**
     * 基于一个SQL时间戳构建一个日期值对象
     *
     * @param timestamp SQL时间戳
     * @return 日期值对象
     * @see #ofTimestamp(long)
     */
    public static DateValue ofSQLTimestamp(java.sql.Timestamp timestamp) {
        return ofTimestamp(timestamp.getTime());
    }

    /**
     * 构建一个当前日期时间的日期值对象
     *
     * @return 日期值对象
     */
    public static DateValue now() {
        return ofDate(new Date());
    }

    /**
     * 设置年份值
     *
     * @param year 年份值
     */
    public void setYear(int year) {
        checkValue(this.year, this.month, this.day);
        this.year = year;
    }

    /**
     * 获取年份值
     *
     * @return 年份值
     */
    public int getYear() {
        return this.year;
    }

    /**
     * 设置月份值
     *
     * @param month 月份值
     */
    public void setMonth(int month) {
        checkValue(this.year, this.month, this.day);
        this.month = month;
    }

    /**
     * 获取月份值
     *
     * @return 月份值
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * 设置日期值
     *
     * @param day 日期值
     */
    public void setDay(int day) {
        checkValue(this.year, this.month, this.day);
        this.day = day;
    }

    /**
     * 获取日期值
     *
     * @return 日期值
     */
    public int getDay() {
        return this.day;
    }

    /**
     * 获取星期值<br/>
     * <li>0 - 星期日</li>
     * <li>1 - 星期一</li>
     * <li>2 - 星期二</li>
     * <li>3 - 星期三</li>
     * <li>4 - 星期四</li>
     * <li>5 - 星期五</li>
     * <li>6 - 星期六</li>
     *
     * @return 星期值
     */
    public int getWeek() {
        return this.week;
    }

    /**
     * 获取当前日期值是一周中的第几天
     *
     * @return 一周中的第几天
     */
    public int getDayOfWeek() {
        return this.week + 1;
    }

    /**
     * 获取当前日期值所在周是当年的第几周
     *
     * @return 当前日期值所在周是当年的第几周
     */
    public int getWeekOfYear() {
        return this.weekOfYear;
    }

    /**
     * 获取当前日期值所在周在当月视图中的第几周
     *
     * @return 当前日期值所在当月视图中的第几周
     */
    public int getWeekOfMonth() {
        return this.weekOfMonth;
    }

    /**
     * 获取当前日期值是当年的第几天
     *
     * @return 当前日期值是当年的第几天
     */
    public int getDayOfYear() {
        return this.dayOfYear;
    }

    /**
     * 获取当前日期值所在周是当月的第几周，将每月1号作为当月周计算的第一天
     *
     * @return 当前日期值所在周是当月的第几周
     */
    public int getDayOfWeekInMonth() {
        return this.dayOfWeekInMonth;
    }

    /**
     * 获取年份值的字符串格式，格式为四位数值，不足两位左边补0
     *
     * @return 年份值的字符串格式
     */
    public String year() {
        return String.format("%04d", this.year);
    }

    /**
     * 获取月份值的字符串格式，格式为两位数值，不足两位左边补0
     *
     * @return 月份值的字符串格式
     */
    public String month() {
        return String.format("%02d", this.month);
    }

    /**
     * 获取日期值的字符串格式，格式为两位数值，不足两位左边补0
     *
     * @return 日期份值的字符串格式
     */
    public String day() {
        return String.format("%02d", this.day);
    }

    /**
     * 日期值字符串表现形式，格式：yyyy-MM-dd<br/>
     * 例如：{@code 2023-5-19}
     *
     * @return 日期值字符串表现形式
     */
    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", this.year, this.month, this.day);
    }

    /**
     * 将当前日期值对象转换为本地日期对象
     *
     * @return 本地日期对象
     */
    public LocalDate asLocalDate() {
        LocalDate localDate = LocalDate.of(this.year, this.month, this.day);
        return localDate;
    }

    /**
     * 判断当前日期值是否在输入日期值之前，如果当前日期值比输入日期值大或者相等，则表示在其后或相等，则返回false；否则返回true
     *
     * @param other 输入的日期值
     * @return 如果当前日期值比输入日期值大或者相等，则表示在其后或相等，则返回false；否则返回true
     */
    public boolean before(DateValue other) {
        return this.compareTo(other) < 0;
    }

    /**
     * 判断当前日期值是否在输入日期值之后，如果当前日期值比输入日期值小或者相等，则表示在其前或相等，则返回false；否则返回true
     *
     * @param other 输入的日期值
     * @return 如果当前日期值比输入日期值小或者相等，则表示在其前或相等，则返回false；否则返回true
     */
    public boolean after(DateValue other) {
        return this.compareTo(other) > 0;
    }

    /**
     * 判断当前日期值是否是当年的第一天，即1月1日
     *
     * @return 如果是1月1日，则返回true；否则返回false
     */
    public boolean isFirstDayOfYear() {
        return this.month == 1 && this.day == 1;
    }

    /**
     * 判断当前日期值是否是当年的最后一天，即12月31日
     *
     * @return 如果是12月31日，则返回true；否则返回false
     */
    public boolean isLastDayOfYear() {
        return this.month == 12 && this.day == 31;
    }

    /**
     * 判断当前日期值是否是当月中的第一天，即1日
     *
     * @return 如果是1日，则返回true；否则返回false
     */
    public boolean isFirstDayOfMonth() {
        return this.day == 1;
    }

    /**
     * 判断当前日期值是否是当月中的最后一天，与闰年、大小月的天数有关
     *
     * @return 如果是，则返回true；否则返回false
     */
    public boolean isLastDayOfMonth() {
        int daysOfMonth = getDaysOfMonth(this.year, this.month);
        return this.day == daysOfMonth;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        DateValue dateValue = (DateValue) other;
        return year == dateValue.year && month == dateValue.month && day == dateValue.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    @Override
    public int compareTo(DateValue other) {
        int diff = this.year - other.year;
        if (diff != 0) {
            return diff > 0 ? 1 : -1;
        }
        diff = this.month - other.month;
        if (diff != 0) {
            return diff > 0 ? 1 : -1;
        }
        diff = this.day - other.day;
        if (diff != 0) {
            return diff > 0 ? 1 : -1;
        }
        return 0;
    }

}
