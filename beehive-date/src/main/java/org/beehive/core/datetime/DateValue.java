/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-date
 * File Name: org.beehive.core.datetime.DateValue
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-04-22
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.datetime;

/**
 * 日期值描述对象，包含年、月、日三个核心字段。
 * <br>
 * 该模型用于描述日期的构成，通过年、月、日三个字段构成。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.date</code></li>
 * <li>Class Name: <code>DateValue</code></li>
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
public class DateValue {

    /**
     * 年份值
     */
     Integer year;

    /**
     * 月份值
     */
     Integer month;

    /**
     * 日期值
     */
     Integer day;

    /**
     * 禁止外部实例化
     */
    private DateValue() {

    }

    /**
     * 设置年份值
     *
     * @param year - 年份值
     * @return 当前日期值对象
     */
    public DateValue year(int year) {
        this.year = year;
        return this;
    }

    /**
     * 设置月份值
     *
     * @param month - 月份值
     * @return 当前日期值对象
     */
    public DateValue month(int month) {
        this.month = month;
        return this;
    }

    /**
     * 设置日期值
     *
     * @param day - 日期值
     * @return 当前日期值对象
     */
    public DateValue day(int day) {
        this.day = day;
        return this;
    }

    /**
     * 创建一个日期值对象实例
     *
     * @return 当前日期值对象
     */
    public static DateValue newInstance() {
        return new DateValue();
    }

}
