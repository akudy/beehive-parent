/*
 * Copyright (c) 2019-2023 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.datetime.TimeValue
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2023-05-18
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.datetime;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 时间值对象定义(提供的是24小时制的时间描述定义)，用来解构一个日期时间对象或者日历对象。
 * <br>
 * 通过提供时、分、秒来描述一个时间值</br/>
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.datetime</code></li>
 *   <li>Class Name: <code>TimeValue</code></li>
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
public class TimeValue implements Comparable<TimeValue> {

    /**
     * 24小时进制的小时值
     */
    private int hour;

    /**
     * 分钟值
     */
    private int minute;

    /**
     * 秒值
     */
    private int second;

    /**
     * 毫秒值
     */
    private int millisecond;

    private static void checkValue(int hour, int minute, int second, int millisecond) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("value range of hour is [1,23]");
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("value range of minute is [1,59]");
        }
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("value range of second is [1,59]");
        }
        if (millisecond < 0 || millisecond > 999) {
            throw new IllegalArgumentException("value range of millisecond is [1,999]");
        }
    }

    private TimeValue(int hour, int minute, int second, int millisecond) {
        checkValue(hour, minute, second, millisecond);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
    }

    /**
     * 基于时、分、秒、毫秒值构建一个时间值对象
     *
     * @param hour        小时值
     * @param minute      分钟值
     * @param second      秒值
     * @param millisecond 毫秒值
     * @return 时间值对象
     */
    public static TimeValue of(int hour, int minute, int second, int millisecond) {
        return new TimeValue(hour, minute, second, millisecond);
    }

    /**
     * 基于时、分、秒值构建一个毫秒为0的时间值对象
     *
     * @param hour   小时值
     * @param minute 分钟值
     * @param second 秒值
     * @return 时间值对象
     */
    public static TimeValue of(int hour, int minute, int second) {
        return new TimeValue(hour, minute, second, 0);
    }

    /**
     * 基于日历对象构建一个时间值对象
     *
     * @param calendar 日历对象
     * @return 时间值对象
     * @see Calendar#get(int)
     */
    public static TimeValue ofCalendar(Calendar calendar) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);
        return new TimeValue(hour, minute, second, millisecond);
    }

    /**
     * 基于日期对象构建一个时间值对象
     *
     * @param date 日期对象
     * @return 时间值对象
     * @see #ofCalendar(Calendar)
     */
    public static TimeValue ofDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return ofCalendar(calendar);
    }

    /**
     * 基于时间戳构建一个时间值对象
     *
     * @param timestamp 时间戳
     * @return 时间值对象
     * @see #ofDate(Date)
     */
    public static TimeValue ofTimestamp(long timestamp) {
        return ofDate(new Date(timestamp));
    }

    /**
     * 基于本地时间对象构建一个时间值对象
     *
     * @param localTime 本地时间对象
     * @return 时间值对象
     * @see LocalTime#getHour()
     * @see LocalTime#getMinute()
     * @see LocalTime#getSecond()
     * @see LocalTime#getNano()
     * @see #of(int, int, int, int)
     */
    public static TimeValue ofLocalTime(LocalTime localTime) {
        return of(localTime.getHour(), localTime.getMinute(), localTime.getSecond(), localTime.getNano() / 1000000);
    }

    /**
     * 基于本地日期时间对象构建一个时间值对象
     *
     * @param localDateTime 本地日期时间对象
     * @return 时间值对象
     * @see LocalDateTime#getHour()
     * @see LocalDateTime#getMinute()
     * @see LocalDateTime#getSecond()
     * @see LocalDateTime#getNano()
     * @see #of(int, int, int, int)
     */
    public static TimeValue ofLocalDateTime(LocalDateTime localDateTime) {
        return of(localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), localDateTime.getNano() / 1000000);
    }

    /**
     * 基于一个SQL日期对象构建一个时间值对象
     *
     * @param sqlDate SQL日期对象
     * @return 时间值对象
     * @see #ofTimestamp(long)
     */
    public static TimeValue ofSQLDate(java.sql.Date sqlDate) {
        return ofTimestamp(sqlDate.getTime());
    }

    /**
     * 基于一个SQL时间戳构建一个时间值对象
     *
     * @param timestamp SQL时间戳
     * @return 时间值对象
     * @see #ofTimestamp(long)
     */
    public static TimeValue ofSQLTimestamp(java.sql.Timestamp timestamp) {
        return ofTimestamp(timestamp.getTime());
    }

    /**
     * 构建一个当前日期时间的时间值对象
     *
     * @return 时间值对象
     */
    public static TimeValue now() {
        return ofDate(new Date());
    }

    /**
     * 设置小时值，取值范围[0,24]
     *
     * @param hour 小时值
     */
    public void setHour(int hour) {
        checkValue(hour, this.minute, this.second, this.millisecond);
        this.hour = hour;
    }

    /**
     * 获取小时值
     *
     * @return 小时值
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * 设置分钟值，取值范围[0,59]
     *
     * @param minute 分钟值
     */
    public void setMinute(int minute) {
        checkValue(this.hour, minute, this.second, this.millisecond);
        this.minute = minute;
    }

    /**
     * 获取分钟值
     *
     * @return 分钟值
     */
    public int getMinute() {
        return this.minute;
    }

    /**
     * 设置秒值，取值范围[0,59]
     *
     * @param second 秒值
     */
    public void setSecond(int second) {
        checkValue(this.hour, this.minute, second, this.millisecond);
        this.second = second;
    }

    /**
     * 获取秒值
     *
     * @return 秒值
     */
    public int getSecond() {
        return this.second;
    }

    /**
     * 设置毫秒值，取值范围[0,999]
     *
     * @param millisecond 毫秒值
     */
    public void setMillisecond(int millisecond) {
        checkValue(this.hour, this.minute, this.second, millisecond);
        this.millisecond = millisecond;
    }

    /**
     * 获取毫秒值
     *
     * @return 毫秒值
     */
    public int getMillisecond() {
        return this.millisecond;
    }

    /**
     * 获取24小时制的小时值的字符串格式，格式为两位数值，不足两位左边补0
     *
     * @return 24小时制的小时值的字符串格式
     */
    public String hour() {
        return String.format("%02d", this.hour);
    }

    /**
     * 获取分钟值的字符串格式，格式为两位数值，不足两位左边补0
     *
     * @return 分钟值的字符串格式
     */
    public String minute() {
        return String.format("%02d", this.minute);
    }

    /**
     * 获取秒值的字符串格式，格式为两位数值，不足两位左边补0
     *
     * @return 秒值的字符串格式
     */
    public String second() {
        return String.format("%02d", this.second);
    }

    /**
     * 获取毫秒值的字符串格式，格式为三位数值，不足两位左边补0
     *
     * @return 毫秒值的字符串格式
     */
    public String millisecond() {
        return String.format("%03d", this.millisecond);
    }

    /**
     * 时间值字符串表现形式，格式：HH:mm:ss.SSS<br/>
     * 例如：{@code 22:12:32.876}
     *
     * @return 时间值字符串表现形式
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d.%03d", this.hour, this.minute, this.second, this.millisecond);
    }

    /**
     * 将时间值对象转换为本地时间对象
     *
     * @return 本地时间对象
     * @see LocalTime#of(int, int, int, int)
     */
    public LocalTime asLocalTime() {
        LocalTime localTime = LocalTime.of(this.hour, this.minute, this.second, this.millisecond);
        return localTime;
    }

    /**
     * 判断当前时间值是否在输入时间值之前，如果当前时间值比输入时间值大或者相等，则表示在其后或相等，则返回false；否则返回true
     *
     * @param other 输入的时间值
     * @return 如果当前时间值比输入时间值大或者相等，则表示在其后或相等，则返回false；否则返回true
     */
    public boolean before(TimeValue other) {
        return this.compareTo(other) < 0;
    }

    /**
     * 判断当前时间值是否在输入时间值之后，如果当前时间值比输入时间值小或者相等，则表示在其前或相等，则返回false；否则返回true
     *
     * @param other 输入的时间值
     * @return 如果当前时间值比输入时间值小或者相等，则表示在其前或相等，则返回false；否则返回true
     */
    public boolean after(TimeValue other) {
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
        TimeValue timeValue = (TimeValue) other;
        return hour == timeValue.hour && minute == timeValue.minute && second == timeValue.second && millisecond == timeValue.millisecond;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minute, second, millisecond);
    }

    @Override
    public int compareTo(TimeValue other) {
        int diff = this.hour - other.hour;
        if (diff != 0) {
            return diff > 0 ? 1 : -1;
        }
        diff = this.minute - other.minute;
        if (diff != 0) {
            return diff > 0 ? 1 : -1;
        }
        diff = this.second - other.second;
        if (diff != 0) {
            return diff > 0 ? 1 : -1;
        }
        diff = this.millisecond - other.millisecond;
        if (diff != 0) {
            return diff > 0 ? 1 : -1;
        }
        return 0;
    }

}
