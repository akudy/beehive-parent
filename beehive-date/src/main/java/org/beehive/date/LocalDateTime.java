/*
 * Copyright (c) 2019-2019 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-date
 * File Name: org.beehive.date.LocalDateTime
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2019-06-28
 * Comments: Java源文件
 */

package org.beehive.date;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 本地日期时间类，扩展至{@link DateTime DateTime}。
 * <br/>
 * 该类并没有提供额外的日期时间功能，仅仅扩展了{@link DateTime DateTime}
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.date</code></li>
 * <li>Class Name: <code>LocalDateTime</code></li>
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
 * @since 1.0
 */
public final class LocalDateTime {

    /**
     * 默认的日期格式化器
     */
    private static String dateFormatStyle = "yyyy-MM-dd HH:mm:ss";

    /**
     * 加载配置参数，修改当前类的初始配置
     */
    static {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("beehive-date");
            dateFormatStyle = resourceBundle.getString("default.local.datetime.format.style");
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * 日期时间对象
     */
    private DateTime dateTime;

    /**
     * 时区对象
     */
    private TimeZone timeZone = TimeZone.getDefault();

    /**
     * 本地语言环境
     */
    private Locale locale = Locale.getDefault();

    /**
     * 日期时间对象
     */
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormatStyle);

    public LocalDateTime(long timestamp) {

    }

    public LocalDateTime(Date date) {

    }

    public LocalDateTime(Calendar calendar){

    }

    public LocalDateTime(DateTime dateTime) {

    }

    /**
     * 构建默认的本地日期时间对象
     */
    public LocalDateTime() {
        this.dateTime = new DateTime(this.timeZone, this.locale);
        this.dateFormatter = new SimpleDateFormat(dateFormatStyle, this.locale);
        this.dateFormatter.setTimeZone(this.timeZone);
    }


    /**
     * 本地当前日期时间对象
     *
     * @return 当前的本地日期时间对象
     */
    public static LocalDateTime now() {
        return new LocalDateTime();
    }


    @Override
    public String toString() {
        return dateFormatter.format(this.dateTime.asDate());
    }
}
