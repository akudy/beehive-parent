/*
 * Copyright (c) 2019-2023 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.datetime.DateTimeValue
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2023-05-18
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 日期时间值对象定义，用来解构一个日期时间对象或者日历对象。
 * <br>
 * 通过提供年、月、日、时、分、秒来描述一个日期时间值。其中可以将年、月、日划归类日期值来描述，时、分、秒划归类为时间值来描述。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.datetime</code></li>
 *   <li>Class Name: <code>DateTimeValue</code></li>
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
 * @see DateValue
 * @see TimeValue
 * @since 1.0
 */
public class DateTimeValue implements Comparable<DateTimeValue> {

    /**
     * 日期值对象
     */
    private DateValue dateValue;

    /**
     * 时间值对象
     */
    private TimeValue timeValue;

    private DateTimeValue(DateValue dateValue, TimeValue timeValue) {
        this.dateValue = dateValue;
        this.timeValue = timeValue;
    }

    /**
     * 基于日期值对象和时间值对象构建一个日期时间值对象
     *
     * @param dateValue 日期值对象
     * @param timeValue 时间值对象
     * @return 日期时间值对象
     */
    public static DateTimeValue of(DateValue dateValue, TimeValue timeValue) {
        DateTimeValue dateTimeValue = new DateTimeValue(dateValue, timeValue);
        return dateTimeValue;
    }

    /**
     * 基于年、月、日、时、分、秒、毫秒数值构建一个日期时间值对象
     *
     * @param year        年份值
     * @param month       月份值
     * @param day         日期值
     * @param hour        24小时值
     * @param minute      分钟值
     * @param second      秒值
     * @param millisecond 毫秒值
     * @return 日期时间值对象
     */
    public static DateTimeValue of(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        return of(DateValue.of(year, month, day), TimeValue.of(hour, minute, second, millisecond));
    }

    /**
     * 基于年、月、日、时、分、秒数值构建一个日期时间值对象
     *
     * @param year   年份值
     * @param month  月份值
     * @param day    日期值
     * @param hour   24小时值
     * @param minute 分钟值
     * @param second 秒值
     * @return 日期时间值对象
     */
    public static DateTimeValue of(int year, int month, int day, int hour, int minute, int second) {
        return of(DateValue.of(year, month, day), TimeValue.of(hour, minute, second));
    }

    /**
     * 基于年、月、日构建一个开始时间的日期时间值对象
     *
     * @param year  年份值
     * @param month 月份值
     * @param day   日期值
     * @return 日期时间值对象
     */
    public static DateTimeValue ofBeginTime(int year, int month, int day) {
        return of(year, month, day, 0, 0, 0);
    }

    /**
     * 基于年、月、日构建一个结束时间的日期时间值对象
     *
     * @param year  年份值
     * @param month 月份值
     * @param day   日期值
     * @return 日期时间值对象
     */
    public static DateTimeValue ofEndTime(int year, int month, int day) {
        return of(year, month, day, 23, 59, 59, 0);
    }

    /**
     * 基于日历对象构建一个日期时间值对象
     *
     * @param calendar 日历对象
     * @return 日期时间值对象
     */
    public static DateTimeValue ofCalendar(Calendar calendar) {
        return of(DateValue.ofCalendar(calendar), TimeValue.ofCalendar(calendar));
    }

    /**
     * 基于日期对象构建一个日期时间值对象
     *
     * @param date 日期对象
     * @return 日期时间值对象
     */
    public static DateTimeValue ofDate(Date date) {
        return of(DateValue.ofDate(date), TimeValue.ofDate(date));
    }

    /**
     * 基于时间戳构架一个日期时间值对象
     *
     * @param timestamp 时间戳
     * @return 日期时间值对象
     */
    public static DateTimeValue ofTimestamp(long timestamp) {
        return of(DateValue.ofTimestamp(timestamp), TimeValue.ofTimestamp(timestamp));
    }

    /**
     * 基于本地日期时间对象构建一个日期时间值对象
     *
     * @param localDateTime 本地日期时间对象
     * @return 日期时间值对象
     */
    public static DateTimeValue ofLocalDateTime(LocalDateTime localDateTime) {
        return of(DateValue.ofLocalDateTime(localDateTime), TimeValue.ofLocalDateTime(localDateTime));
    }

    /**
     * 基于本地日期对象和本地时间对象构建一个日期时间值对象
     *
     * @param localDate 本地日期对象
     * @param localTime 本地时间对象
     * @return 日期时间值对象
     */
    public static DateTimeValue ofLocalDateTime(LocalDate localDate, LocalTime localTime) {
        return of(DateValue.ofLocalDate(localDate), TimeValue.ofLocalTime(localTime));
    }

    /**
     * 基于SQL日期对象构建一个日期时间值对象
     *
     * @param sqlDate SQL日期对象
     * @return 日期时间值对象
     */
    public static DateTimeValue ofSQLDate(java.sql.Date sqlDate) {
        return of(DateValue.ofSQLDate(sqlDate), TimeValue.ofSQLDate(sqlDate));
    }

    /**
     * 基于SQL时间戳对象构建一个日期时间值对象
     *
     * @param timestamp SQL时间戳对象
     * @return 日期时间值对象
     */
    public static DateTimeValue ofSQLTimestamp(java.sql.Timestamp timestamp) {
        return of(DateValue.ofSQLTimestamp(timestamp), TimeValue.ofSQLTimestamp(timestamp));
    }

    /**
     * 基于当前日期时间构建一个当前日期时间值对象
     *
     * @return 日期时间值对象
     */
    public static DateTimeValue now() {
        return ofDate(new Date());
    }

    /**
     * 设置年份值
     *
     * @param year 年份值
     */
    public void setYear(int year) {
        this.dateValue.setYear(year);
    }

    /**
     * 获取年份值
     *
     * @return 年份值
     */
    public int getYear() {
        return this.dateValue.getYear();
    }

    /**
     * 设置月份值
     *
     * @param month 月份值
     */
    public void setMonth(int month) {
        this.dateValue.setMonth(month);
    }

    /**
     * 获取月份值
     *
     * @return 月份值
     */
    public int getMonth() {
        return this.dateValue.getMonth();
    }

    /**
     * 设置日期值
     *
     * @param day 日期值
     */
    public void setDay(int day) {
        this.dateValue.setDay(day);
    }

    /**
     * 获取日期值
     *
     * @return 日期值
     */
    public int getDay() {
        return this.dateValue.getDay();
    }

    /**
     * 设置小时值，取值范围[0,24]
     *
     * @param hour 小时值
     */
    public void setHour(int hour) {
        this.timeValue.setHour(hour);
    }

    /**
     * 获取小时值
     *
     * @return 小时值
     */
    public int getHour() {
        return this.timeValue.getHour();
    }

    /**
     * 设置分钟值，取值范围[0,59]
     *
     * @param minute 分钟值
     */
    public void setMinute(int minute) {
        this.timeValue.setMinute(minute);
    }

    /**
     * 获取分钟值
     *
     * @return 分钟值
     */
    public int getMinute() {
        return this.timeValue.getMinute();
    }

    /**
     * 设置秒值，取值范围[0,59]
     *
     * @param second 秒值
     */
    public void setSecond(int second) {
        this.timeValue.setSecond(second);
    }

    /**
     * 获取秒值
     *
     * @return 秒值
     */
    public int getSecond() {
        return this.timeValue.getSecond();
    }

    /**
     * 设置毫秒值，取值范围[0,999]
     *
     * @param millisecond 毫秒值
     */
    public void setMillisecond(int millisecond) {
        this.timeValue.setMillisecond(millisecond);
    }

    /**
     * 获取毫秒值
     *
     * @return 毫秒值
     */
    public int getMillisecond() {
        return this.timeValue.getMillisecond();
    }

    /**
     * 获取年份值的字符串格式，格式为四位数值，不足两位左边补0
     *
     * @return 年份值的字符串格式
     */
    public String year() {
        return String.format("%04d", this.getYear());
    }

    /**
     * 获取月份值的字符串格式，格式为两位数值，不足两位左边补0
     *
     * @return 月份值的字符串格式
     */
    public String month() {
        return String.format("%02d", this.getMonth());
    }

    /**
     * 获取日期值的字符串格式，格式为两位数值，不足两位左边补0
     *
     * @return 日期份值的字符串格式
     */
    public String day() {
        return String.format("%02d", this.getDay());
    }

    /**
     * 获取24小时制的小时值的字符串格式，格式为两位数值，不足两位左边补0
     *
     * @return 24小时制的小时值的字符串格式
     */
    public String hour() {
        return String.format("%02d", this.getHour());
    }

    /**
     * 获取分钟值的字符串格式，格式为两位数值，不足两位左边补0
     *
     * @return 分钟值的字符串格式
     */
    public String minute() {
        return String.format("%02d", this.getMinute());
    }

    /**
     * 获取秒值的字符串格式，格式为两位数值，不足两位左边补0
     *
     * @return 秒值的字符串格式
     */
    public String second() {
        return String.format("%02d", this.getSecond());
    }

    /**
     * 获取毫秒值的字符串格式，格式为三位数值，不足两位左边补0
     *
     * @return 毫秒值的字符串格式
     */
    public String millisecond() {
        return String.format("%03d", this.getMillisecond());
    }

    /**
     * 日期时间值字符串表现形式，格式：yyyy-MM-dd HH:mm:ss.SSS<br/>
     * 例如：{@code 2023-5-19 13:23:12.987}
     *
     * @return 日期时间值字符串表现形式
     */
    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d %02d:%02d:%02d.%03d", this.getYear(), this.getMonth(), this.getDay(),
                this.getHour(), this.getMinute(), this.getSecond(), this.getMillisecond());
    }

    /**
     * 将当前日期时间值对象转换为本地日期时间对象
     *
     * @return 本地日期时间对象
     */
    public LocalDateTime asLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(this.getYear(), this.getMonth(), this.getDay(),
                this.getHour(), this.getMinute(), this.getSecond(), this.getMillisecond());
        return localDateTime;
    }

    /**
     * 将当前日期时间值转换为日历对象
     *
     * @return 日历对象
     */
    public Calendar asCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.getYear());
        calendar.set(Calendar.MONTH, this.getMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, this.getDay());
        calendar.set(Calendar.HOUR_OF_DAY, this.getHour());
        calendar.set(Calendar.MINUTE, this.getMinute());
        calendar.set(Calendar.SECOND, this.getSecond());
        calendar.set(Calendar.MILLISECOND, this.getMillisecond());
        return calendar;
    }

    /**
     * 将当前日期时间值转换为日期对象
     *
     * @return 日期对象
     */
    public Date asDate() {
        Calendar calendar = this.asCalendar();
        return calendar.getTime();
    }

    /**
     * 将当前日期时间值转换为SQL日期对象
     *
     * @return SQL日期对象
     */
    public java.sql.Date asSQLDate() {
        Calendar calendar = this.asCalendar();
        java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
        return sqlDate;
    }

    /**
     * 将当前日期时间值转换为SQL时间戳对象
     *
     * @return SQL时间戳对象
     */
    public java.sql.Timestamp asSQLTimestamp() {
        Calendar calendar = this.asCalendar();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());
        return timestamp;
    }

    /**
     * 判断当前日期时间值是否在输入日期时间值之前，如果当前日期时间值比输入日期时间值大或者相等，则表示在其后或相等，则返回false；否则返回true
     *
     * @param other 输入的日期值
     * @return 如果当前日期时间值比输入日期时间值大或者相等，则表示在其后或相等，则返回false；否则返回true
     */
    public boolean before(DateTimeValue other) {
        return this.compareTo(other) < 0;
    }

    /**
     * 判断当前日期时间值是否在输入日期时间值之后，如果当前日期时间值比输入日期时间值小或者相等，则表示在其前或相等，则返回false；否则返回true
     *
     * @param other 输入的日期值
     * @return 如果当前日期时间值比输入日期时间值小或者相等，则表示在其前或相等，则返回false；否则返回true
     */
    public boolean after(DateTimeValue other) {
        return this.compareTo(other) > 0;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        DateTimeValue dateTimeValue = (DateTimeValue) other;
        return Objects.equals(this.dateValue, dateTimeValue.dateValue) && Objects.equals(this.timeValue, dateTimeValue.timeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.dateValue, this.timeValue);
    }

    @Override
    public int compareTo(DateTimeValue other) {
        int diff = this.dateValue.compareTo(other.dateValue);
        if (diff == 0) {
            diff = this.timeValue.compareTo(other.timeValue);
        }
        return diff;
    }

}
