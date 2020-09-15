/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-date
 * File Name: org.beehive.core.datetime.IntervalValue
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-04-22
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.datetime;

/**
 * 时间间隔值对象，表述距离格林威治时间1970年01月01日00时00分00秒的时间差信息；通过日、时、分、秒、毫秒进行描述。
 * <br>
 * 该模型用于描述间隔值的构成，由过日、时、分、秒、毫秒等字段构成。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.date</code></li>
 * <li>Class Name: <code>IntervalValue</code></li>
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
 * <td align="center"><em>2020/4/21</em></td>
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
public class IntervalValue {

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

    private IntervalValue() {

    }

    /**
     * 根据两个时间戳来计算差值对象
     *
     * @param timestamp 时间戳
     */
    public IntervalValue(long timestamp) {
        long diff = timestamp - 0L;
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
