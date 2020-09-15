/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-date
 * File Name: org.beehive.core.datetime.TimeValue
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-04-22
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.datetime;

/**
 * 时间值描述对象，包含时、分、秒、毫秒四个核心字段。
 * <br>
 * 该模型用于描述日期的构成，通过时、分、秒、毫秒四个字段构成。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.date</code></li>
 * <li>Class Name: <code>TimeValue</code></li>
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
public class TimeValue {

    /**
     * 24小时进制的小时值
     */
     Integer hour;

    /**
     * 分钟值
     */
     Integer minute;

    /**
     * 秒值
     */
     Integer second;

    /**
     * 毫秒值
     */
     Integer millisecond;

    /**
     * 禁止外部实例化
     */
    private TimeValue() {
    }

    /**
     * 设置24小时进制的小时值
     *
     * @param hour - 24小时进制的小时值
     * @return 当前时间值对象
     */
    public TimeValue hour(int hour) {
        this.hour = hour;
        return this;
    }

    /**
     * 设置分钟值
     *
     * @param minute - 分钟值
     * @return 当前时间值对象
     */
    public TimeValue minute(int minute) {
        this.minute = minute;
        return this;
    }

    /**
     * 设置秒值
     *
     * @param second - 秒值
     * @return 当前时间值对象
     */
    public TimeValue second(int second) {
        this.second = second;
        return this;
    }

    /**
     * 设置毫秒值
     *
     * @param millisecond - 毫秒值
     * @return 当前时间值对象
     */
    public TimeValue millisecond(int millisecond) {
        this.millisecond = millisecond;
        return this;
    }

    /**
     * 创建一个时间值对象实例
     *
     * @return 当前时间值对象
     */
    public static TimeValue newInstance(){
        return new TimeValue();
    }

}
