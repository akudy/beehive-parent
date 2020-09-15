/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-date
 * File Name: org.beehive.core.datetime.DateTimeValue
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-04-22
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.datetime;

/**
 * 日期时间值描述对象，由{@link DateValue 日期值对象}和{@link TimeValue 时间值对象}组合而成。
 * <br>
 * 该模型用于描述日期时间的构成，通过年、月、日、时、分、秒、毫秒等字段构成。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.date</code></li>
 * <li>Class Name: <code>DateTimeValue</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1" cellspacing="0" cellpadding="0" summary=""Upgrade/Modify History>
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
 * <td align="center"><em>2019/11/26</em></td>
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
public class DateTimeValue {

    /**
     * 日期值对象
     */
    DateValue dateValue;

    /**
     * 时间值对象
     */
    TimeValue timeValue;

    /**
     * 设置年份值
     *
     * @param year - 年份值
     * @return 当前日期时间值对象
     */
    public DateTimeValue year(int year) {
        this.dateValue.year(year);
        return this;
    }

    /**
     * 设置月份值
     *
     * @param month - 月份值
     * @return 当前日期时间值对象
     */
    public DateTimeValue month(int month) {
        this.dateValue.month(month);
        return this;
    }

    /**
     * 设置日期值
     *
     * @param day - 日期值
     * @return 当前日期时间值对象
     */
    public DateTimeValue day(int day) {
        this.dateValue.day(day);
        return this;
    }

    /**
     * 设置24小时进制的小时值
     *
     * @param hour - 24小时进制的小时值
     * @return 当前日期时间值对象
     */
    public DateTimeValue hour(int hour) {
        this.timeValue.hour(hour);
        return this;
    }

    /**
     * 设置分钟值
     *
     * @param minute - 分钟值
     * @return 当前日期时间值对象
     */
    public DateTimeValue minute(int minute) {
        this.timeValue.minute(minute);
        return this;
    }

    /**
     * 设置秒值
     *
     * @param second - 秒值
     * @return 当前日期时间值对象
     */
    public DateTimeValue second(int second) {
        this.timeValue.second(second);
        return this;
    }

    /**
     * 设置毫秒值
     *
     * @param millisecond - 毫秒值
     * @return 当前日期时间值对象
     */
    public DateTimeValue millisecond(int millisecond) {
        this.timeValue.millisecond(millisecond);
        return this;
    }

    /**
     * 创建一个日期时间值对象实例
     *
     * @return 当前日期时间值对象
     */
    public static DateTimeValue newInstance() {
        DateTimeValue dateTimeValue = new DateTimeValue();
        dateTimeValue.dateValue = DateValue.newInstance();
        dateTimeValue.timeValue = TimeValue.newInstance();
        return dateTimeValue;
    }

}
