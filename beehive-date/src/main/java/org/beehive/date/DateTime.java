/*
 * Copyright (c) 2019-2019 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-date
 * File Name: org.beehive.date.DateTime
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2019-06-28
 * Comments: Java源文件
 */

package org.beehive.date;

import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期时间对象封装。用于描述日期和时间对象，与{@link java.util.Date java.util.Date}和{@link java.util.Calendar java.util.Calendar}比较，更为灵活。
 * <br>
 * 内部使用{@link java.util.Calendar java.util.Calendar}实现。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.date</code></li>
 * <li>Class Name: <code>DateTime</code></li>
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
 * @see Calendar
 * @see TimeZone
 * @see Locale
 * @since 1.0
 */
public final class DateTime implements Serializable, Comparable<DateTime> {

    /**
     * 月份的最小值
     */
    private static final int MONTH_MIN_VALUE = 1;
    /**
     * 月份的最大值
     */
    private static final int MONTH_MAX_VALUE = 12;
    /**
     * 天的最小值
     */
    private static final int DAY_MIN_VALUE = 1;
    /**
     * 大月对应的天数，1/3/5/7/8/10/12
     */
    private static final int DAYS_OF_BIG_MONTH = 31;
    /**
     * 小月对应的天数，4/6/9/11
     */
    private static final int DAYS_OF_SMALL_MONTH = 30;
    /**
     * 闰年2月对应的天数
     */
    private static final int DAYS_OF_LEAP_YEAR_FEBRUARY = 29;
    /**
     * 平年2月对应的天数
     */
    private static final int DAYS_OF_NON_LEAP_YEAR_FEBRUARY = 28;
    /**
     * 小时数的最小值
     */
    private static final int HOUR_MIN_VALUE = 0;
    /**
     * 小时数的最大值
     */
    private static final int HOUR_MAX_VALUE = 23;
    /**
     * 分钟数的最小值
     */
    private static final int MINUTE_MIN_VALUE = 0;
    /**
     * 分钟数的最大值
     */
    private static final int MINUTE_MAX_VALUE = 59;
    /**
     * 秒数的最小值
     */
    private static final int SECOND_MIN_VALUE = 0;
    /**
     * 秒数的最大值
     */
    private static final int SECOND_MAX_VALUE = 59;
    /**
     * 毫秒数的最小值
     */
    private static final int MILLISECOND_MIN_VALUE = 0;
    /**
     * 毫秒数的最大值
     */
    private static final int MILLISECOND_MAX_VALUE = 999;

    /**
     * 默认的日期格式化器
     */
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    /**
     * 加载配置参数，修改当前类的初始配置
     */
    static {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("beehive-date");
            String dateFormatStr = resourceBundle.getString("default.date.format.style");
            dateFormatter = new SimpleDateFormat(dateFormatStr);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * 日历对象
     */
    private Calendar calendar;


    /**
     * 使用时区和本地语言环境构建日期时间对象
     *
     * @param timeZone 时区对象 {@link TimeZone java.util.TimeZone}
     * @param locale   本地语言环境 {@link Locale java.util.Locale}
     * @since 1.0
     */
    public DateTime(TimeZone timeZone, Locale locale) {
        this.calendar = Calendar.getInstance(timeZone, locale);
    }

    /**
     * 使用时区构建日期时间对象
     *
     * @param timeZone 时区对象 {@link TimeZone java.util.TimeZone}
     * @since 1.0
     */
    public DateTime(TimeZone timeZone) {
        this.calendar = Calendar.getInstance(timeZone);
    }

    /**
     * 使用本地语言环境构建日期时间对象
     *
     * @param locale 本地语言环境 {@link Locale java.util.Locale}
     * @since 1.0
     */
    public DateTime(Locale locale) {
        this.calendar = Calendar.getInstance(locale);
    }

    /**
     * 使用{@link Date java.util.Date}日期对象构建日期时间对象
     *
     * @param date {@link Date java.util.Date}日期对象
     * @since 1.0
     */
    public DateTime(Date date) {
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(date);
    }

    /**
     * 使用当前毫秒时间值构建日期时间对象
     *
     * @param millisecond 毫秒时间值
     * @since 1.0
     */
    public DateTime(long millisecond) {
        this.calendar = Calendar.getInstance();
        this.calendar.setTimeInMillis(millisecond);
    }

    /**
     * 使用{@link Calendar java.util.Calendar}日历对象构建日期时间对象
     *
     * @param calendar 日历对象
     * @since 1.0
     */
    public DateTime(Calendar calendar) {
        this(calendar.getTimeInMillis());
    }

    /**
     * 使用{@link java.sql.Date java.sql.Date}日期对象构建日期时间对象
     *
     * @param date {@link java.sql.Date java.sql.Date}日期对象
     * @since 1.0
     */
    public DateTime(java.sql.Date date) {
        this(date.getTime());
    }

    /**
     * 使用{@link java.sql.Timestamp java.sql.Timestamp}时间戳对象构建日期时间对象
     *
     * @param timestamp {@link java.sql.Timestamp java.sql.Timestamp}时间戳对象
     * @since 1.0
     */
    public DateTime(java.sql.Timestamp timestamp) {
        this(timestamp.getTime());
    }

    /**
     * 构建当前系统默认的日期时间对象
     *
     * @since 1.0
     */
    public DateTime() {
        this(System.currentTimeMillis());
    }

    /**
     * 构建当前系统默认的日期时间对象
     *
     * @return 当前日期时间对象
     * @since 1.0
     */
    public static DateTime now() {
        return new DateTime();
    }

    /**
     * 设置日历值
     *
     * @param calendar 待设置值的日期对象
     * @param value    日期时间值对象
     * @see Calendar#getInstance()
     * @see Calendar#YEAR
     * @see Calendar#MONTH
     * @see Calendar#DAY_OF_MONTH
     * @see Calendar#HOUR_OF_DAY
     * @see Calendar#MINUTE
     * @see Calendar#SECOND
     * @see Calendar#MILLISECOND
     * @see Value
     * @since 1.0
     */
    private void setCalendarValue(Calendar calendar, Value value) {
        // 获取当前的年月日时分秒毫秒值，如果设置为指定，则使用该值
        Calendar __calendar = Calendar.getInstance();
        int year = value.year != null ? value.year : __calendar.get(Calendar.YEAR);
        int month = value.month != null ? value.month : __calendar.get(Calendar.MONTH);
        int day = value.day != null ? value.day : __calendar.get(Calendar.DAY_OF_MONTH);
        int hour = value.hour != null ? value.hour : __calendar.get(Calendar.HOUR_OF_DAY);
        int minute = value.minute != null ? value.minute : __calendar.get(Calendar.MINUTE);
        int second = value.second != null ? value.second : __calendar.get(Calendar.SECOND);
        int millisecond = value.millisecond != null ? value.millisecond : __calendar.get(Calendar.MILLISECOND);
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        calendar.set(Calendar.YEAR, year);
        if (month < MONTH_MIN_VALUE || month > MONTH_MAX_VALUE) {
            throw new IllegalArgumentException(formatString("value range of month is [{0},{1}]", MONTH_MIN_VALUE, MONTH_MAX_VALUE));
        }
        calendar.set(Calendar.MONTH, month - 1);
        int daysOfMonth = getDaysOfMonth(year, month);
        if (day < DAY_MIN_VALUE || day > daysOfMonth) {
            throw new IllegalArgumentException(formatString("value range of day is [{0},{1}], because year is {2,number,#}, month is {3}", DAY_MIN_VALUE, daysOfMonth, year, month));
        }
        calendar.set(Calendar.DAY_OF_MONTH, day);
        if (hour < HOUR_MIN_VALUE || hour > HOUR_MAX_VALUE) {
            throw new IllegalArgumentException(formatString("value range of hour is [{0},{1}]", HOUR_MIN_VALUE, HOUR_MAX_VALUE));
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        if (minute < MINUTE_MIN_VALUE || minute > MINUTE_MAX_VALUE) {
            throw new IllegalArgumentException(formatString("value range of minute is [{0},{1}]", MINUTE_MIN_VALUE, MINUTE_MAX_VALUE));
        }
        calendar.set(Calendar.MINUTE, minute);
        if (second < SECOND_MIN_VALUE || second > SECOND_MAX_VALUE) {
            throw new IllegalArgumentException(formatString("value range of second is [{0},{1}]", SECOND_MIN_VALUE, SECOND_MAX_VALUE));
        }
        calendar.set(Calendar.SECOND, second);
        if (month < MILLISECOND_MIN_VALUE || month > MILLISECOND_MAX_VALUE) {
            throw new IllegalArgumentException(formatString("value range of millisecond is [{0},{1}]", MILLISECOND_MIN_VALUE, MILLISECOND_MAX_VALUE));
        }
        calendar.set(Calendar.MILLISECOND, millisecond);
    }

    /**
     * 使用年、月（正整数月份）、日、时（24小时制）、分、秒、毫秒数值构建日期时间对象
     *
     * @param year        年份数值
     * @param month       月份数值，取值范围[1,12]
     * @param day         天数值，1/3/5/7/8/10/12月份的取值范围为[1,31]；4,6,9,11月份取值取值范围为[1,30]；2月份取值范围为[1,28]或[1,29]
     * @param hour        小时值，取值范围[0,23]
     * @param minute      分钟值，取值范围[0,59]
     * @param second      秒值，取值范围[0,59]
     * @param millisecond 毫秒值，取值范围[0,999]
     * @see Calendar#getInstance()
     * @see Calendar#YEAR
     * @see Calendar#MONTH
     * @see Calendar#DAY_OF_MONTH
     * @see Calendar#HOUR_OF_DAY
     * @see Calendar#MINUTE
     * @see Calendar#SECOND
     * @see Calendar#MILLISECOND
     * @since 1.0
     */
    public DateTime(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        this.calendar = Calendar.getInstance();
        Value value = new Value().year(year).month(month).day(day).hour(hour).minute(minute).second(second).millisecond(millisecond);
        this.setCalendarValue(this.calendar, value);
    }

    /**
     * 使用构建器构造日期时间对象，如果构造器的值未设置，则使用当前日期时间值
     *
     * @param value 值对象
     */
    private DateTime(Value value) {
        this.calendar = Calendar.getInstance();
        this.setCalendarValue(this.calendar, value);
    }

    /**
     * 使用年、月（正整数月份）、日、时（24小时制）、分、秒构建日期时间对象
     *
     * @param year   年份数值
     * @param month  月份数值，取值范围[1,12]
     * @param day    天数值，1/3/5/7/8/10/12月份的取值范围为[1,31]；4,6,9,11月份取值取值范围为[1,30]；2月份取值范围为[1,28]或[1,29]
     * @param hour   小时值，取值范围[0,23]
     * @param minute 分钟值，取值范围[0,59]
     * @param second 秒值，取值范围[0,59]
     * @see #DateTime(int, int, int, int, int, int, int)
     * @since 1.0
     */
    public DateTime(int year, int month, int day, int hour, int minute, int second) {
        this(year, month, day, hour, minute, second, MILLISECOND_MIN_VALUE);
    }

    /**
     * 转换为{@link Date java.util.Date}日期对象。
     * 使用一个新的日期对象进行体现。
     *
     * @return {@link Date java.util.Date}日期对象
     * @see Calendar#getTime()
     * @since 1.0
     */
    public Date asDate() {
        return this.calendar.getTime();
    }

    /**
     * 转换为{@link Calendar java.util.Calnedar}日历对象。
     *
     * @return {@link Calendar java.util.Calnedar}日历对象
     * @see Calendar#getInstance()
     * @see Calendar#setTimeInMillis(long)
     * @since 1.0
     */
    public Calendar asCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.calendar.getTimeInMillis());
        return calendar;
    }

    /**
     * 转换为时间戳
     *
     * @return 时间戳
     * @see Calendar#getTimeInMillis()
     * @since 1.0
     */
    public long asTimestamp() {
        return this.calendar.getTimeInMillis();
    }

    /**
     * 转换为{@link java.sql.Date java.sql.Date}SQL日期对象
     *
     * @return {@link java.sql.Date java.sql.Date}SQL日期对象
     * @see java.sql.Date#Date(long)
     * @since 1.0
     */
    public java.sql.Date asSQLDate() {
        return new java.sql.Date(this.calendar.getTimeInMillis());
    }

    /**
     * 转换为{@link java.sql.Timestamp java.sql.Timestamp}SQL时间戳对象
     *
     * @return {@link java.sql.Timestamp java.sql.Timestamp}SQL时间戳对象
     * @see java.sql.Timestamp#Timestamp(long)
     * @since 1.0
     */
    public java.sql.Timestamp asSQLTimestamp() {
        return new java.sql.Timestamp(this.calendar.getTimeInMillis());
    }

    /**
     * 转换为{@link LocalDateTime}本地日期时间
     *
     * @return {@link LocalDateTime}本地日期时间对象
     * @since 1.0
     */
    public LocalDateTime asLocalDateTime() {
        //TODO
        return null;
    }

    /**
     * 转换为{@link LocalDate}本地日期
     *
     * @return {@link LocalDate}本地日期对象
     * @since 1.0
     */
    public LocalDate asLocalDate() {
        //TODO
        return null;
    }

    /**
     * 转换为{@link LocalTime}本地时间
     *
     * @return {@link LocalTime}本地时间对象
     * @since 1.0
     */
    public LocalTime asLocalTime() {
        //TODO
        return null;
    }

    /**
     * 获取时间毫秒值
     *
     * @return 时间毫秒值
     * @since 1.0
     */
    public long millisecond() {
        return this.calendar.getTimeInMillis();
    }

    /**
     * 转换为一个8个元素的数组，元素值依次为年、月、日、周几、时、分、秒、毫秒
     *
     * @return 8个元素的数组，元素值依次为年、月、日、周几、时、分、秒、毫秒
     * @see #getYear()
     * @see #getMonth()
     * @see #getDay()
     * @see #getHour()
     * @see #getMinute()
     * @see #getSecond()
     * @see #getMillisecond()
     * @see #getWeek()
     * @since 1.0
     * @deprecated 替代方法{@link #values()}
     */
    @Deprecated
    public int[] toArray() {
        int[] array = new int[8];
        array[0] = this.getYear();
        array[1] = this.getMonth();
        array[2] = this.getDay();
        array[3] = this.getWeek();
        array[4] = this.getHour();
        array[5] = this.getMinute();
        array[6] = this.getSecond();
        array[7] = this.getMillisecond();
        return array;
    }

    /**
     * 获取年份值
     *
     * @return year - 年份值
     * @since 1.0
     */
    public int getYear() {
        return this.calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月份值，取值范围[1,12]
     *
     * @return month - 月份值，取值范围[1,12]
     * @since 1.0
     */
    public int getMonth() {
        return this.calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期值，取值范围[1,31]
     *
     * @return day - 日期值，取值范围[1,31]
     * @since 1.0
     */
    public int getDay() {
        return this.calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取星期值，取值范围[0,6]，以周日为一周的开始
     *
     * @return week - 星期值，取值范围[0,6]，以周日为一周的开始
     * @since 1.0
     */
    public int getWeek() {
        return this.calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取24小时进制的小时值，取值范围[0,23]
     *
     * @return hour - 24小时进制的小时值，取值范围[0,23]
     * @since 1.0
     */
    public int getHour() {
        return this.calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分钟值，取值范围[0,59]
     *
     * @return minute - 分钟值，取值范围[0,59]
     * @since 1.0
     */
    public int getMinute() {
        return this.calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取秒值，取值范围[0,59]
     *
     * @return second - 秒值，取值范围[0,59]
     * @since 1.0
     */
    public int getSecond() {
        return this.calendar.get(Calendar.SECOND);
    }

    /**
     * 获取毫秒值，取值范围[0,999]
     *
     * @return millisecond - 毫秒值，取值范围[0,999]
     * @since 1.0
     */
    public int getMillisecond() {
        return this.calendar.get(Calendar.MILLISECOND);
    }

    /**
     * 设置日期值
     *
     * @param year  年份数值
     * @param month 月份数值，取值范围[1,12]
     * @param day   天数值，1/3/5/7/8/10/12月份的取值范围为[1,31]；4,6,9,11月份取值取值范围为[1,30]；2月份取值范围为[1,28]或[1,29]
     * @since 1.0
     */
    public void setDate(int year, int month, int day) {
        Value value = new Value().year(year).month(month).day(day).hour(this.getHour()).minute(this.getMinute()).second(this.getSecond()).millisecond(this.getMillisecond());
        this.setCalendarValue(this.calendar, value);
    }

    /**
     * 设置时间值
     *
     * @param hour   小时值，取值范围[0,23]
     * @param minute 分钟值，取值范围[0,59]
     * @param second 秒值，取值范围[0,59]
     * @since 1.0
     */
    public void setTime(int hour, int minute, int second) {
        Value value = new Value().year(this.getYear()).month(this.getMonth()).day(this.getDay()).hour(hour).minute(minute).second(second).millisecond(MILLISECOND_MIN_VALUE);
        this.setCalendarValue(this.calendar, value);
    }

    /**
     * 设置时间值
     *
     * @param hour        小时值，取值范围[0,23]
     * @param minute      分钟值，取值范围[0,59]
     * @param second      秒值，取值范围[0,59]
     * @param millisecond 毫秒值，取值范围[0,999]
     * @since 1.0
     */
    public void setTime(int hour, int minute, int second, int millisecond) {
        Value value = new Value().year(this.getYear()).month(this.getMonth()).day(this.getDay()).hour(hour).minute(minute).second(second).millisecond(millisecond);
        this.setCalendarValue(this.calendar, value);
    }

    /**
     * 转换为当天的开始时间
     *
     * @see #setTime(int, int, int, int)
     * @since 1.0
     */
    public void beginOf() {
        this.setTime(HOUR_MIN_VALUE, MINUTE_MIN_VALUE, SECOND_MIN_VALUE, MILLISECOND_MIN_VALUE);
    }

    /**
     * 转换为当天的结束时间。<br/>
     * 需要指定是否忽略毫秒值，因为有些有些时候在转换为数据库的日期时间时可能会由于毫秒丢失进度，例如MySQL的DATETIME类型。
     *
     * @param ignoreMillisecond 是否忽略毫秒值，如果忽略则设置为0
     * @see #setTime(int, int, int, int)
     * @since 1.0
     */
    public void endOf(boolean ignoreMillisecond) {
        this.setTime(HOUR_MIN_VALUE, MINUTE_MIN_VALUE, SECOND_MIN_VALUE, ignoreMillisecond ? MILLISECOND_MIN_VALUE : MILLISECOND_MAX_VALUE);
    }

    /**
     * 转换为当天的结束时间(忽略毫秒值)。
     *
     * @see #endOf(boolean)
     * @since 1.0
     */
    public void endOf() {
        this.endOf(true);
    }


    /**
     * 加上指定的年数
     *
     * @param years 年数
     * @since 1.0
     */
    public void addYear(int years) {
        this.calendar.add(Calendar.YEAR, years);
    }

    /**
     * 减去指定的年数
     *
     * @param years 年数
     * @see Calendar#add(int, int)
     * @see Calendar#YEAR
     * @since 1.0
     */
    public void minuteYear(int years) {
        this.calendar.add(Calendar.YEAR, -years);
    }

    /**
     * 加上指定的月数
     *
     * @param months 月数
     * @see Calendar#add(int, int)
     * @see Calendar#MONTH
     * @since 1.0
     */
    public void addMonth(int months) {
        this.calendar.add(Calendar.MONTH, months);
    }

    /**
     * 减去指定的年数
     *
     * @param months 年数
     * @see Calendar#add(int, int)
     * @see Calendar#MONTH
     * @since 1.0
     */
    public void minuteMonth(int months) {
        this.calendar.add(Calendar.MONTH, -months);
    }

    /**
     * 加上指定的天数
     *
     * @param days 天数
     * @see Calendar#add(int, int)
     * @see Calendar#DAY_OF_MONTH
     * @since 1.0
     */
    public void addDay(int days) {
        this.calendar.add(Calendar.DAY_OF_MONTH, days);
    }

    /**
     * 减去指定的天数
     *
     * @param months 天数
     * @see Calendar#add(int, int)
     * @see Calendar#DAY_OF_MONTH
     * @since 1.0
     */
    public void minuteDay(int months) {
        this.calendar.add(Calendar.DAY_OF_MONTH, -months);
    }

    /**
     * 加上指定的小时数
     *
     * @param hours 小时数
     * @see Calendar#add(int, int)
     * @see Calendar#HOUR_OF_DAY
     * @since 1.0
     */
    public void addHour(int hours) {
        this.calendar.add(Calendar.HOUR_OF_DAY, hours);
    }

    /**
     * 减去指定的小时数
     *
     * @param hours 小时数
     * @see Calendar#add(int, int)
     * @see Calendar#HOUR_OF_DAY
     * @since 1.0
     */
    public void minuteHour(int hours) {
        this.calendar.add(Calendar.HOUR_OF_DAY, -hours);
    }

    /**
     * 加上指定的分钟数
     *
     * @param minute 分钟数
     * @see Calendar#add(int, int)
     * @see Calendar#MINUTE
     * @since 1.0
     */
    public void addMinute(int minute) {
        this.calendar.add(Calendar.MINUTE, minute);
    }

    /**
     * 减去指定的分钟数
     *
     * @param minute 分钟数
     * @see Calendar#add(int, int)
     * @see Calendar#MINUTE
     * @since 1.0
     */
    public void minuteMinute(int minute) {
        this.calendar.add(Calendar.MINUTE, -minute);
    }

    /**
     * 加上指定的秒数
     *
     * @param seconds 秒数
     * @see Calendar#add(int, int)
     * @see Calendar#SECOND
     * @since 1.0
     */
    public void addSecond(int seconds) {
        this.calendar.add(Calendar.SECOND, seconds);
    }

    /**
     * 减去指定的秒数
     *
     * @param seconds 秒数
     * @see Calendar#add(int, int)
     * @see Calendar#SECOND
     * @since 1.0
     */
    public void minuteSecond(int seconds) {
        this.calendar.add(Calendar.SECOND, -seconds);
    }

    /**
     * 加上指定的毫秒数
     *
     * @param milliseconds 毫秒数
     * @see Calendar#add(int, int)
     * @see Calendar#MILLISECOND
     * @since 1.0
     */
    public void addMillisecond(int milliseconds) {
        this.calendar.add(Calendar.MILLISECOND, milliseconds);
    }

    /**
     * 减去指定的毫秒数
     *
     * @param milliseconds 毫秒数
     * @see Calendar#add(int, int)
     * @see Calendar#MILLISECOND
     * @since 1.0
     */
    public void minuteMillisecond(int milliseconds) {
        this.calendar.add(Calendar.MILLISECOND, -milliseconds);
    }

    /**
     * 计算日期
     *
     * @param calendar 待计算的日期对象
     * @param calcType 计算类型，大于0表示加，小于等于0表示减
     * @param value    日期时间值对象
     * @see Calendar#YEAR
     * @see Calendar#MONTH
     * @see Calendar#DAY_OF_MONTH
     * @see Calendar#HOUR_OF_DAY
     * @see Calendar#MINUTE
     * @see Calendar#SECOND
     * @see Calendar#MILLISECOND
     * @see Value
     * @since 1.0
     */
    private void calc(Calendar calendar, int calcType, Value value) {
        int year = value.year != null ? value.year : 0;
        int month = value.month != null ? value.month : 0;
        int day = value.day != null ? value.day : 0;
        int hour = value.hour != null ? value.hour : 0;
        int minute = value.minute != null ? value.minute : 0;
        int second = value.second != null ? value.second : 0;
        int millisecond = value.millisecond != null ? value.millisecond : 0;
        boolean isAdd = calcType > 0;
        calendar.add(Calendar.YEAR, isAdd ? year : -year);
        calendar.add(Calendar.MONTH, isAdd ? month : 0);
        calendar.add(Calendar.DAY_OF_MONTH, isAdd ? day : 0);
        calendar.add(Calendar.HOUR_OF_DAY, isAdd ? hour : 0);
        calendar.add(Calendar.MINUTE, isAdd ? minute : 0);
        calendar.add(Calendar.SECOND, isAdd ? second : 0);
        calendar.add(Calendar.MILLISECOND, isAdd ? millisecond : 0);
    }

    /**
     * 添加一个值对象描述的值列表
     *
     * @param value 值对象
     * @see Value
     * @since 1.0
     */
    public void add(Value value) {
        this.calc(this.calendar, 1, value);
    }

    /**
     * 减去一个值对象描述的值列表
     *
     * @param value 值对象
     * @see Value
     * @since 1.0
     */
    public void minus(Value value) {
        this.calc(this.calendar, -1, value);
    }

    /**
     * 计算两个日期时间的差值，不区分前后大小
     *
     * @param dateTime 另一个日期时间对象
     * @return 两个日期时间的差值
     * @see DiffValue
     * @see #asTimestamp()
     * @since 1.0
     */
    public DiffValue diff(DateTime dateTime) {
        long beginTimestamp = this.asTimestamp();
        long endTimestamp = dateTime.asTimestamp();
        return new DiffValue(beginTimestamp, endTimestamp);
    }

    /**
     * 计算和指定日期的差值
     *
     * @param date 日期对象
     * @return 两个日期时间的差值
     * @see DiffValue
     * @see #asTimestamp()
     * @since 1.0
     */
    public DiffValue diff(Date date) {
        long beginTimestamp = this.asTimestamp();
        long endTimestamp = date.getTime();
        return new DiffValue(beginTimestamp, endTimestamp);
    }

    /**
     * 计算和指定日历的差值
     *
     * @param calendar 日历对象
     * @return 两个日期时间的差值
     * @see DiffValue
     * @see #asTimestamp()
     * @see Calendar#getTimeInMillis()
     * @since 1.0
     */
    public DiffValue diff(Calendar calendar) {
        long beginTimestamp = this.asTimestamp();
        long endTimestamp = calendar.getTimeInMillis();
        return new DiffValue(beginTimestamp, endTimestamp);
    }

    /**
     * 比较两个日期时间的大小
     *
     * @param other 另一个日期时间对象
     * @return 如果当前日期时间大于指定的日期时间则返回大于正整数；如果当前日期时间小于指定的日期时间则返回负整数；否则返回0
     * @see Calendar#compareTo(Calendar)
     * @since 1.0
     */
    @Override
    public int compareTo(DateTime other) {
        return this.calendar.compareTo(other.calendar);
    }

    /**
     * 比较和指定日期的大小
     *
     * @param date 日期对象
     * @return 如果当前日期时间大于指定的日期则返回大于正整数；如果当前日期时间小于指定的日期则返回负整数；否则返回0
     * @see #compareTo(DateTime)
     * @since 1.0
     */
    public int compare(Date date) {
        return this.compareTo(new DateTime(date));
    }

    /**
     * 比较和指定日期对象的大小
     *
     * @param calendar 日历对象
     * @return 如果当前日期时间大于指定的日历则返回大于正整数；如果当前日期时间小于指定的日历则返回负整数；否则返回0
     * @see #compareTo(DateTime)
     * @since 1.0
     */
    public int compare(Calendar calendar) {
        return this.compareTo(new DateTime(calendar));
    }

    /**
     * 当前日期时间是否在指定的日期时间之前
     *
     * @param dateTime 日期时间对象
     * @return 如果当前日期时间在指定的日期时间之前则返回true，否则返回false
     * @see #compareTo(DateTime)
     * @since 1.0
     */
    public boolean before(DateTime dateTime) {
        return this.compareTo(dateTime) > 0;
    }

    /**
     * 当前日期时间是否在指定的日期之前
     *
     * @param date 日期对象
     * @return 如果当前日期时间在指定的日期之前则返回true，否则返回false
     * @see #compare(Date)
     * @since 1.0
     */
    public boolean before(Date date) {
        return this.compare(date) > 0;
    }

    /**
     * 当前日期时间是否在指定的日历之前
     *
     * @param calendar 日历对象
     * @return 如果当前日期时间在指定的日历之前则返回true，否则返回false
     * @see #compare(Calendar)
     * @since 1.0
     */
    public boolean before(Calendar calendar) {
        return this.compare(calendar) > 0;
    }

    /**
     * 当前日期时间是否在指定的日期时间之后
     *
     * @param dateTime 日期时间对象
     * @return 如果当前日期时间在指定的日期时间之后则返回true，否则返回false
     * @see #compare(Calendar)
     * @since 1.0
     */
    public boolean after(DateTime dateTime) {
        return this.compareTo(dateTime) < 0;
    }

    /**
     * 当前日期时间是否在指定的日期之后
     *
     * @param date 日期对象
     * @return 如果当前日期时间在指定的日期之后则返回true，否则返回false
     * @see #compare(Calendar)
     * @since 1.0
     */
    public boolean after(Date date) {
        return this.compare(date) < 0;
    }

    /**
     * 当前日期时间是否在指定的日历之后
     *
     * @param calendar 日历对象
     * @return 如果当前日期时间在指定的日历之后则返回true，否则返回false
     * @see #compare(Calendar)
     * @since 1.0
     */
    public boolean after(Calendar calendar) {
        return this.compare(calendar) < 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DateTime) {
            return this.compareTo((DateTime) obj) == 0;
        }
        if (obj instanceof Date) {
            return this.compare((Date) obj) == 0;
        }
        if (obj instanceof Calendar) {
            return this.compare((Calendar) obj) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.calendar.hashCode();
    }

    /**
     * 转换为指定格式的日期时间字符串。<br>
     * 格式字符（单个格式字符描述的都是实际值，如果需要进行位对其的格式化则可以使用多个占位符，不足位会自动补0，所有的格式化内容都与本地语言有关）参考：
     * <table border="1" cellspacing="0" cellpadding="0">
     * <caption>日期时间格式字符解释</caption>
     * <tr>
     * <th>符号</th>
     * <th>说明</th>
     * <th>示例</th>
     * </tr>
     * <tr>
     * <td align="center">G</td>
     * <td>年代标识符，次数不限</td>
     * <td>G - 公元</td>
     * </tr>
     * <tr>
     * <td align="center">y</td>
     * <td>年份标识符。yy被默认格式化为年份的末尾两位。</td>
     * <td>y - 2019<br>yy - 19<br>yyy - 2019<br>yyyy - 2019<br>yyyyy - 02019</td>
     * </tr>
     * <tr>
     * <td align="center">Y</td>
     * <td>周年标识符，用法同y。<br>区别在于描述的是日历所在周表述的年份，例如2019年12月29日（周日）的周年份为2020年，因为它是2020年1月1日所在周的开始。</td>
     * <td>Y - 2019<br>YY - 19<br>YYY - 2019<br>YYYY - 2019<br>YYYYY - 02019</td>
     * </tr>
     * <tr>
     * <td align="center">M</td>
     * <td>月份标识符。MMM被默认格式化为本地语言对应的月份文本</td>
     * <td>M - 3<br>MM - 03<br>MMM - 三月</td>
     * </tr>
     * <tr>
     * <td align="center">w</td>
     * <td>一年中的第几个周标识符，取值范围：1-52。例如2019年6月27日是本年的第26周</td>
     * <td>w - 26<br>ww - 03<br>www - 026</td>
     * </tr>
     * <tr>
     * <td align="center">W</td>
     * <td>一月中第几个周标识符，取值范围：1-5。例如2019年6月27日是该月的第5周。</td>
     * <td>W - 5<br>WW - 05<br>WWW - 005</td>
     * </tr>
     * <tr>
     * <td align="center">d</td>
     * <td>日/天标识符，取值范围：1-31。</td>
     * <td>d - 27<br>dd - 05<br>ddd - 027</td>
     * </tr>
     * <tr>
     * <td align="center">D</td>
     * <td>一年中的第几天标识符，取值范围：1-366。例如2019年6月27日是该年的第178天。</td>
     * <td>d - 178<br>dddd - 0178</td>
     * </tr>
     * <tr>
     * <td align="center">F</td>
     * <td>一个月中第几个周几的标识符，取值范围：1-5。描述当前周几在本月中出现的次数，例如2019年6月29日在6月周六在本月中出现的次数为第5次</td>
     * <td>F - 5<br>FF - 05</td>
     * </tr>
     * <tr>
     * <td align="center">E</td>
     * <td>周几标识符。描述当前日期对应的星期几。EEEE被默认格式化为本地语言对应的周几文本</td>
     * <td>E - 星期六/Sat<br>EEEE -星期六/Saturday</td>
     * </tr>
     * <tr>
     * <td align="center">u</td>
     * <td>一周中的第几天标识符，取值范围：1（周一）-7（周日）。</td>
     * <td>u - 6<br>uu - 06</td>
     * </tr>
     * <tr>
     * <td align="center">a</td>
     * <td>上午下午标识符</td>
     * <td>a - 上午/AM<br>aa - 上午/AM</td>
     * </tr>
     * <tr>
     * <td align="center">h</td>
     * <td>12小时制的小时数标识符，取值范围：0-11，最好结合"a"使用</td>
     * <td>h - 1<br>hh - 01</td>
     * </tr>
     * <tr>
     * <td align="center">H</td>
     * <td>24小时制的小时数标识符，取值范围：0-23。</td>
     * <td>H - 5<br>HH - 05<br>HHH - 005</td>
     * </tr>
     * <tr>
     * <td align="center">k</td>
     * <td>12小时进制中的当天第几个小时标识符，取值范围：1-12</td>
     * <td>k - 2<br>kk - 02</td>
     * </tr>
     * <tr>
     * <td align="center">K</td>
     * <td>24小时进制中的当天第几个小时标识符，取值范围：1-24。</td>
     * <td>K - 13<br>KK - 13<br>KKK - 013</td>
     * </tr>
     * <tr>
     * <td align="center">m</td>
     * <td>小时中的分钟数标识符，取值范围：0-59。</td>
     * <td>m - 34<br>mm - 34<br>mmm - 034</td>
     * </tr>
     * <tr>
     * <td align="center">s</td>
     * <td>分钟中的秒数标识符，取值范围：0-59</td>
     * <td>s - 29<br>ss - 29<br>sss - 029</td>
     * </tr>
     * <tr>
     * <td align="center">S</td>
     * <td>秒中的毫秒数标识符，取值范围：0-999</td>
     * <td>S - 132<br>SSS - 132<br>SSS - 0132</td>
     * </tr>
     * <tr>
     * <td align="center">z</td>
     * <td>通用时区标识符，zzzz表示默认格式为本地语言所在的时区标识文本</td>
     * <td>z - GST<br>zzzz - 中国标准时间</td>
     * </tr>
     * <tr>
     * <td align="center">Z</td>
     * <td>RFC 822时区标识符，描述的是GMT偏差的小时数和分钟数</td>
     * <td>Z - +0800</td>
     * </tr>
     * <tr>
     * <td align="center">X</td>
     * <td>ISO 8601时区标识符，长度必须小于4，描述的是GMT偏差的小时数和分钟数。X表示偏差的小时数；XX-表示偏差的小时数和分钟数；XXX-表示偏差的分钟数和小时数分开</td>
     * <td>X - +08<br>XX - 0800<br>XXX - +08:00</td>
     * </tr>
     * </table>
     *
     * @param pattern 日期时间字符串
     * @return 格式化后的字符串
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#format(Date)
     * @since 1.0
     */
    public String toString(String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(this.calendar.getTime());
    }

    @Override
    public String toString() {
        return dateFormatter.format(this.calendar.getTime());
    }

    /**
     * 判断年份是否为闰年
     *
     * @param year 年份
     * @return 如果是闰年则返回true，否则返回false
     * @since 1.0
     */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /**
     * 获取指定年份的指定月份的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 当前月份对应的总天数
     * @since 1.0
     */
    private static int getDaysOfMonth(int year, int month) {
        int days = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = DAYS_OF_BIG_MONTH;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = DAYS_OF_SMALL_MONTH;
                break;
            case 2:
                days = isLeapYear(year) ? DAYS_OF_LEAP_YEAR_FEBRUARY : DAYS_OF_NON_LEAP_YEAR_FEBRUARY;
                break;
            default:
                break;
        }
        return days;
    }

    /**
     * 格式化带有参数的字符串模板，并返回替换后的字符串序列
     *
     * @param template 格式字符串
     * @param args     参数列表
     * @return 字符串序列
     * @see MessageFormat#format(Object)
     * @since 1.0
     */
    private static String formatString(String template, Object... args) {
        return MessageFormat.format(template, args);
    }

    /**
     * 日期时间值对象
     */
    public static final class Value {

        /**
         * 年份值
         */
        private Integer year;

        /**
         * 月份值，取值范围[1,12]
         */
        private Integer month;

        /**
         * 日期值，取值范围[1,31]
         */
        private Integer day;

        /**
         * 24小时进制的小时值，取值范围[0,23]
         */
        private Integer hour;

        /**
         * 分钟值，取值范围[0,59]
         */
        private Integer minute;

        /**
         * 秒值，取值范围[0,59]
         */
        private Integer second;

        /**
         * 毫秒值，取值范围[0,999]
         */
        private Integer millisecond;

        /**
         * 设置年份值
         *
         * @param year - 年份值
         * @return 当前日期时间构建器对象
         */
        public Value year(int year) {
            this.year = year;
            return this;
        }

        /**
         * 设置月份值，取值范围[1,12]
         *
         * @param month - 月份值，取值范围[1,12]
         * @return 当前日期时间构建器对象
         */
        public Value month(int month) {
            this.month = month;
            return this;
        }

        /**
         * 设置日期值，取值范围[1,31]
         *
         * @param day - 日期值，取值范围[1,31]
         * @return 当前日期时间构建器对象
         */
        public Value day(int day) {
            this.day = day;
            return this;
        }

        /**
         * 设置24小时进制的小时值，取值范围[0,23]
         *
         * @param hour - 24小时进制的小时值，取值范围[0,23]
         * @return 当前日期时间构建器对象
         */
        public Value hour(int hour) {
            this.hour = hour;
            return this;
        }

        /**
         * 设置分钟值，取值范围[0,59]
         *
         * @param minute - 分钟值，取值范围[0,59]
         * @return 当前日期时间构建器对象
         */
        public Value minute(int minute) {
            this.minute = minute;
            return this;
        }

        /**
         * 设置秒值，取值范围[0,59]
         *
         * @param second - 秒值，取值范围[0,59]
         * @return 当前日期时间构造器对象
         */
        public Value second(int second) {
            this.second = second;
            return this;
        }

        /**
         * 设置毫秒值，取值范围[0,999]
         *
         * @param millisecond - 毫秒值，取值范围[0,999]
         * @return 当前日期时间构造器对象
         */
        public Value millisecond(int millisecond) {
            this.millisecond = millisecond;
            return this;
        }

        /**
         * 构建日期对象对象
         *
         * @return 日期时间对象
         */
        public DateTime build() {
            return new DateTime(this);
        }
    }

    /**
     * 日期时间差异值对象
     */
    public static final class DiffValue {

        /**
         * 每天的毫秒数
         */
        private static final long MILLIS_OF_DAY = 1000 * 60 * 60 * 24;
        /**
         * 每小时的毫秒数
         */
        private static final long MILLIS_OF_HOUR = 1000 * 60 * 60;
        /**
         * 每分钟的毫秒数
         */
        private static final long MILLIS_OF_MINUTE = 1000 * 60;
        /**
         * 每秒的毫秒数
         */
        private static final long MILLIS_OF_SECOND = 1000;

        private DiffValue() {

        }

        /**
         * 根据两个时间戳来计算差值对象
         *
         * @param beginTimestamp 开始时间戳
         * @param endTimestamp   结束时间戳
         */
        private DiffValue(long beginTimestamp, long endTimestamp) {
            long diff = endTimestamp - beginTimestamp;
            diff = diff > 0 ? diff : -diff;
            long days = diff / MILLIS_OF_DAY;
            long hours = diff % MILLIS_OF_DAY / MILLIS_OF_HOUR;
            long minutes = diff % MILLIS_OF_HOUR / MILLIS_OF_MINUTE;
            long seconds = diff % MILLIS_OF_MINUTE / MILLIS_OF_SECOND;
            long milliseconds = diff % MILLIS_OF_SECOND;
            this.days = (int) days;
            this.hours = (int) hours;
            this.minutes = (int) minutes;
            this.seconds = (int) seconds;
            this.milliseconds = (int) milliseconds;
        }

        /**
         * 天数
         */
        private int days;

        /**
         * 小时数
         */
        private int hours;

        /**
         * 分钟数
         */
        private int minutes;

        /**
         * 秒数
         */
        private int seconds;

        /**
         * 毫秒数
         */
        private int milliseconds;

        /**
         * 获取天数
         *
         * @return days - 天数
         */
        public int days() {
            return this.days;
        }

        /**
         * 获取小时数
         *
         * @return hours - 小时数
         */
        public int hours() {
            return this.hours;
        }

        /**
         * 获取分钟数
         *
         * @return minutes - 分钟数
         */
        public int minutes() {
            return this.minutes;
        }

        /**
         * 获取秒数
         *
         * @return seconds - 秒数
         */
        public int seconds() {
            return this.seconds;
        }

        /**
         * 获取毫秒数
         *
         * @return milliseconds - 毫秒数
         */
        public int milliseconds() {
            return this.milliseconds;
        }

    }

}
