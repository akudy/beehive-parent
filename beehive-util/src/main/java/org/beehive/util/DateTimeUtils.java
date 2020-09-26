/*
 * Copyright (c) 2019-2019 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-util
 * File Name: org.beehive.util.DateTimeUtils
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2019-06-26
 * Comments: Java源文件
 */

package org.beehive.util;

import sun.util.locale.provider.LocaleProviderAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * DateTime工具类定义，提供处理{@link java.util.Calendar java.util.Calendar}、{@link java.util.Date java.util.Date}、{@link java.sql.Date java.sql.Date}、{@link java.sql.Timestamp java.sql.Timestamp}、{@link java.sql.Time java.sql.Time}等类的工具。
 * <br/>
 * 该工具类适用Java 8 版本以下的环境中使用；Java 8 请使用 {@link DateTime8Utils DateTime8Utils(DateTime For Java 8 Utils)}工具类，该类中引入了Java 8 的LocalDateTime进行计算。
 * <br>
 * <a name="dateFormat"></a>
 * <br>
 * 内部包含格式化、转换等多种处理方法。格式字符（单个格式字符描述的都是实际值，如果需要进行位对其的格式化则可以使用多个占位符，不足位会自动补0，所有的格式化内容都与本地语言有关）参考：
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
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.util</code></li>
 * <li>Class Name: <code>DateTimeUtils</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1" cellspacing="0" cellpadding="0" summary="Upgrade/Modify History">
 * <tr style="background-color: #CECECE">
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
public final class DateTimeUtils {

    /**
     * 私有化构造器，禁止外部实例化
     */
    private DateTimeUtils() {
    }

    /**
     * DateTime默认格式化字符串
     */
    private static final String DEFAULT_FORMAT = "yyyy/MM/dd HH:mm:ss";

    /**
     * Date默认格式字符串
     */
    private static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";

    /**
     * Time默认格式字符串
     **/
    private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

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

    /**
     * 时间模式定义，用于定义时间是某一天的开始(00:00:00)、结束(23:59:59)还是当前不变。
     *
     * @since 1.0
     */
    public enum TimeMode {
        BEGIN_OF_DAY, END_OF_DAY, NOW_OF_DAY;
    }

    /**
     * 获取指定时间模式的日历对象
     *
     * @param date     日期对象
     * @param timeMode 时间模式
     * @return 指定时间模式的日历对象
     * @since 1.0
     */
    private static Calendar getCalendar(Date date, TimeMode timeMode) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (TimeMode.BEGIN_OF_DAY == timeMode) {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        } else if (TimeMode.END_OF_DAY == timeMode) {
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            // 目前转换为SqlDate会有问题，会导致增加1s，所以这里将毫秒值设置为0
            calendar.set(Calendar.MILLISECOND, 0);
        }
        return calendar;
    }

    /************************************* basic start *************************************/

    /**
     * 判断年份是否是闰年
     *
     * @param year 年份
     * @return 如果是闰年则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /**
     * 获取指定年份，指定月份的天数
     *
     * @param year  年份
     * @param month 月份，取值范围[1,12]
     * @return 指定月份的天数
     * @see #isLeapYear(int)
     * @since 1.0
     */
    public static int getDaysOfMonth(int year, int month) {
        int days = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = isLeapYear(year) ? 29 : 28;
                break;
        }
        return days;
    }

    /**
     * 获取指定年份的总天数
     *
     * @param year 年份
     * @return 该年份的总天数
     * @see #isLeapYear(int)
     */
    public static int getDaysOfYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }


    /**
     * 获取指定日历的分段值，分别分为年、月（月份会加1）、日、周几（周自动减1）、时、分、秒、毫秒<br>
     * 月份的取值：[1,12]<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     *
     * @param calendar 日历对象
     * @return 日期分段值的数组，共8位，分别代表年、月（月份会加1）、日、周几（周自动减1）、时、分、秒、毫秒<br>
     * 月份的取值：[1-12]<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     */
    public static int[] toArray(Calendar calendar) {
        int[] values = new int[8];
        values[0] = calendar.get(Calendar.YEAR);
        values[1] = calendar.get(Calendar.MONTH) + 1;
        values[2] = calendar.get(Calendar.DAY_OF_MONTH);
        values[3] = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        values[4] = calendar.get(Calendar.HOUR_OF_DAY);
        values[5] = calendar.get(Calendar.MINUTE);
        values[6] = calendar.get(Calendar.SECOND);
        values[7] = calendar.get(Calendar.MILLISECOND);
        return values;
    }

    /************************************* basic end *************************************/


    /************************************* instance start *************************************/

    /**
     * 获取当前日期时间对象
     *
     * @return 当前日期时间对象
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static Date newDateTime() {
        return newDateTime(System.currentTimeMillis());
    }

    /**
     * 实例化一个当前日期的开始日期时间对象，时间固定为00:00:00
     *
     * @return 时间为00:00:00的当前日期时间对象
     * @see #newBeginDate(Date)
     * @since 1.0
     */
    public static Date newBeginDate() {
        return newBeginDate(now());
    }

    /**
     * 实例化一个当前日期的结束日期时间对象，时间固定为23:59:59
     *
     * @return 时间为23:59:59的当前日期时间对象
     * @see #newEndDate(Date)
     * @since 1.0
     */
    public static Date newEndDate() {
        return newEndDate(now());
    }

    /**
     * 根据指定的日期时间实例化一个日期时间对象副本，类似clone方法。
     *
     * @param date 原日期时间对象
     * @return 新日期时间对象
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static Date newDateTime(Date date) {
        return newDateTime(date.getTime());
    }

    /**
     * 根据指定的日期时间对象实例化一个以当前给定日期为基准的开始日期时间对象，时间固定为00:00:00
     *
     * @param date 原日期时间对象
     * @return 以原日期时间对象为基准的开始日期时间对象
     * @see Calendar#getTime()
     * @since 1.0
     */
    public static Date newBeginDate(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.BEGIN_OF_DAY);
        return calendar.getTime();
    }

    /**
     * 根据指定的日期时间对象实例化一个以当前给定日期为基准的结束日期时间对象，时间固定为23:59:59
     *
     * @param date 原日期时间对象
     * @return 以原日期时间对象为基准的结束日期时间对象
     * @see Calendar#getTime()
     * @since 1.0
     */
    public static Date newEndDate(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.END_OF_DAY);
        return calendar.getTime();
    }

    /**
     * 根据指定的时间戳实例化一个日期时间对象。
     *
     * @param timestamp 时间戳
     * @return 日期时间对象
     * @see Date#Date(long)
     * @since 1.0
     */
    public static Date newDateTime(long timestamp) {
        return new Date(timestamp);
    }

    /**
     * 根据指定的时间戳实例化一个以给定时间戳日期为基准的开始日期时间对象，时间固定为00:00:00
     *
     * @param timestamp 时间戳
     * @return 以时间戳日期对象为基准的开始日期时间对象
     * @see Calendar#getTime()
     * @since 1.0
     */
    public static Date newBeginDate(long timestamp) {
        Calendar calendar = getCalendar(newDateTime(timestamp), TimeMode.BEGIN_OF_DAY);
        return calendar.getTime();
    }

    /**
     * 根据指定的时间戳实例化一个以给定时间戳日期为基准的结束日期时间对象，时间固定为23:59:59
     *
     * @param timestamp 时间戳
     * @return 以时间戳日期对象为基准的结束日期时间对象
     * @see Calendar#getTime()
     * @since 1.0
     */
    public static Date newEndDate(long timestamp) {
        Calendar calendar = getCalendar(newDateTime(timestamp), TimeMode.END_OF_DAY);
        return calendar.getTime();
    }

    /**
     * 使用指定的数值实例化一个日期时间对象，内部使用{@link java.util.Calendar java.util.Calendar}类约定的字段进行设置。<br>
     * 该类预定的字段如下：
     * <table border="1" cellspacing="0" cellpadding="0">
     * <caption>{@link java.util.Calendar java.util.Calendar}字段解释</caption>
     * <tr>
     * <th>Calendar字段</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td><code>{@link java.util.Calendar#YEAR YEAR}</code></td>
     * <td>日历中的年份</td>
     * </tr>
     * <tr>
     * <td><code>{@link java.util.Calendar#MONTH MONTH}</code></td>
     * <td>日历中的月份，标准范围[0,11]，0表示1月，依次递增。设置时可以为任意整数，如果大于12则将超出标准范围累计递增或递减到年份上</td>
     * </tr>
     * <tr>
     * <td><code>{@link java.util.Calendar#DAY_OF_MONTH DAY_OF_MONTH}</code></td>
     * <td>日历中的日期，标准范围[1,32)，依次递增。每个月有固定的天数，设置时可以为任意整数，如果超过固定值则累计递增或递减到月份上</td>
     * </tr>
     * <tr>
     * <td><code>{@link java.util.Calendar#HOUR HOUR}</code></td>
     * <td>日历中的小时数，标准范围[-12,11]，-12表示0点，11表示24点，依次递增。设置时可以为任意整数，如果超出标准范围则累计递增或递减到日期上<br>与{@link java.util.Calendar#HOUR_OF_DAY HOUR_OF_DAY}互斥，以最后设置的为准</td>
     * </tr>
     * <tr>
     * <td><code>{@link java.util.Calendar#HOUR_OF_DAY HOUR_OF_DAY}</code></td>
     * <td>日历中的小时数，标准范围[0,23]，依次递增。设置时可以为任意整数，如果超出标准范围则累计递增或递减到日期上<br>与{@link java.util.Calendar#HOUR HOUR}互斥，以最后设置的为准</td>
     * </tr>
     * <tr>
     * <td><code>{@link java.util.Calendar#MINUTE MINUTE}</code></td>
     * <td>日历中的分钟数，标准范围[0,59]，依次递增。设置时可以为任意整数，如果超出标准范围则累计递增或递减到小时上</td>
     * </tr>
     * <tr>
     * <td><code>{@link java.util.Calendar#SECOND SECOND}</code></td>
     * <td>日历中的秒数，标准范围[0,59]，依次递增。设置时可以为任意整数，如果超出标准范围则累计递增或递减到分钟上</td>
     * </tr>
     * <tr>
     * <td><code>{@link java.util.Calendar#MILLISECOND MILLISECOND}</code></td>
     * <td>日历中的毫秒数，标准范围[0,999]，依次递增。设置时可以为任意整数，如果超出标准范围则累计递增或递减到秒上</td>
     * </tr>
     * </table>
     *
     * @param year        年份
     * @param month       月份，取值范围[1,12]，内部自动-1
     * @param day         日期，取值范围[1,28]/[1,29]/[1,30]/[1,31]
     * @param hour        时，取回范围[0,23]
     * @param minute      分，取值范围[0,59]
     * @param second      秒，取值范围[0,59]
     * @param millisecond 毫秒，取值范围[0,999]
     * @return 日期时间对象
     * @throws IllegalArgumentException 如果给定的参数超过其取值范围会抛出异常
     * @see #getDaysOfMonth(int, int)
     * @see Calendar#getInstance()
     * @see Calendar#getTime()
     * @since 1.0
     */
    public static Date newDateTime(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("value range of month is [1,12]");
        }
        calendar.set(Calendar.MONTH, month - 1);
        int daysOfMonth = getDaysOfMonth(year, month);
        if (day < 1 || day > daysOfMonth) {
            throw new IllegalArgumentException("value range of day is [1," + daysOfMonth + "]");
        }
        calendar.set(Calendar.DAY_OF_MONTH, day);
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("value range of hour is [1,23]");
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("value range of minute is [1,59]");
        }
        calendar.set(Calendar.MINUTE, minute);
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("value range of second is [1,59]");
        }
        calendar.set(Calendar.SECOND, second);
        if (month < 0 || month > 999) {
            throw new IllegalArgumentException("value range of millisecond is [1,999]");
        }
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

    /**
     * 使用指定的数值实例化一个日期时间对象，参考{@link #newDateTime(int, int, int, int, int, int, int)}。
     *
     * @param year   年份
     * @param month  月份，取值范围[1,12]，内部自动-1
     * @param day    日期，取值范围[1,28]/[1,29]/[1,30]/[1,31]
     * @param hour   时，取回范围[0,23]
     * @param minute 分，取值范围[0,59]
     * @param second 秒，取值范围[0,59]
     * @return 日期时间对象
     * @see #newDateTime(int, int, int, int, int, int, int)
     * @since 1.0
     */
    public static Date newDateTime(int year, int month, int day, int hour, int minute, int second) {
        return newDateTime(year, month, day, hour, minute, second, 0);
    }

    /**
     * 使用给定的元素值实例化日期时间对象，参考{@link #newDateTime(int, int, int, int, int, int, int)}。<br>
     * 该方法是{@link #newDateTime(int, int, int, int, int, int, int)}的改良版本，入参是一个元素数组，可以在数组中依次按顺序指定年、月、日、时、分、秒、毫秒值，
     * 然后方案会按照年、月、日、时、分、秒、毫秒设置指定的数组元素。
     * <ul>
     * <li>如果没有指定任何元素，则直接返回当前日历</li>
     * <li>如果指定的元素不足七位（年、月、日、时、分、秒、毫秒），则未指定的元素会自动使用当前实例化的日期时间元素补充</li>
     * <li>如果指定的元素超过七位（年、月、日、时、分、秒、毫秒），则第七位后的元素被忽略</li>
     * </ul>
     *
     * @param elements 日期时间元素值列表，顺序为年、月、日、时、分、秒、毫秒
     * @return 日期时间对象
     * @see #newDateTime(int, int, int, int, int, int, int)
     * @since 1.0
     */
    public static Date newDateTimeOfArray(int... elements) {
        Calendar calendar = Calendar.getInstance();
        if (elements == null || elements.length == 0) {
            return calendar.getTime();
        }
        //共8位：年、月（月份会加1）、日、周几（周自动减1）、时、分、秒、毫秒
        int[] items = toArray(calendar);
        for (int i = 0, size = elements.length > 7 ? 7 : elements.length; i < size; i++) {
            if (i >= 3) {
                items[i + 1] = elements[i];
            } else {
                items[i] = elements[i];
            }
        }
        return newDateTime(items[0], items[1], items[2], items[4], items[5], items[6], items[7]);
    }

    /**
     * 使用指定的年、月、日实例化一个以当前时间为基础的日期时间对象
     *
     * @param year  年份
     * @param month 月份，取值范围[1,12]，内部自动-1
     * @param day   日期，取值范围[1,28]/[1,29]/[1,30]/[1,31]
     * @return 以当前时间为基础的日期时间对象
     * @since 1.0
     */
    public static Date newDateTimeOfNowTime(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("value range of month is [1,12]");
        }
        calendar.set(Calendar.MONTH, month - 1);
        int daysOfMonth = getDaysOfMonth(year, month);
        if (day < 1 || day > daysOfMonth) {
            throw new IllegalArgumentException("value range of day is [1," + daysOfMonth + "]");
        }
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 实例化一个给定年、月、日的开始日期时间对象，时间固定为00:00:00
     *
     * @param year  年份
     * @param month 月份，取值范围[1,12]，内部自动-1
     * @param day   日期，取值范围[1,28]/[1,29]/[1,30]/[1,31]
     * @return 开始日期时间对象
     * @see #newBeginDate(Date)
     * @see #newDateTimeOfNowTime(int, int, int)
     */
    public static Date newBeginDate(int year, int month, int day) {
        return newBeginDate(newDateTimeOfNowTime(year, month, day));
    }

    /**
     * 实例化一个给定年、月、日的结束日期时间对象，时间固定为23:59:59
     *
     * @param year  年份
     * @param month 月份，取值范围[1,12]，内部自动-1
     * @param day   日期，取值范围[1,28]/[1,29]/[1,30]/[1,31]
     * @return 结束日期时间对象
     * @see #newEndDate(Date)
     * @see #newDateTimeOfNowTime(int, int, int)
     * @since 1.0
     */
    public static Date newEndDate(int year, int month, int day) {
        return newEndDate(newDateTimeOfNowTime(year, month, day));
    }

    /**
     * 使用指定的时、分、秒实例化一个以当前日期为基础的日期时间对象
     *
     * @param hour   时，取回范围[0,23]
     * @param minute 分，取值范围[0,59]
     * @param second 秒，取值范围[0,59]
     * @return 以当前日期为基础的日期时间对象
     * @since 1.0
     */
    public static Date newDateTimeOfNowDate(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("value range of hour is [1,23]");
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("value range of minute is [1,59]");
        }
        calendar.set(Calendar.MINUTE, minute);
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("value range of second is [1,59]");
        }
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 根据指定的时区实例化一个当前日期时间对象<br>
     * TimeZone的命名可以使用GTM+X或者时区代码
     *
     * @param timeZone 时区对象
     * @return 给定时区的当前日期时间对象
     * @see Calendar#getInstance(TimeZone)
     * @see Calendar#getTime()
     * @since 1.0
     */
    public static Date newDateTime(TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        return calendar.getTime();
    }

    /************************************* instance end *************************************/


    /************************************* current date start *************************************/

    /**
     * 获取系统瞬时的日期时间对象
     *
     * @return 日期时间对象
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static Date now() {
        return newDateTime(System.currentTimeMillis());
    }

    /**
     * 获取指定时区的瞬时日期时间对象<br>
     * TimeZone的命名可以使用GTM+X或者时区代码
     *
     * @param timeZone 时区对象
     * @return 该时区的当前日期时间对象
     * @see Calendar#getInstance(TimeZone)
     * @see Calendar#getTime()
     * @since 1.0
     */
    public static Date now(TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        return calendar.getTime();
    }

    /**
     * 获取系统瞬时的日期时间对象
     *
     * @return 日期时间对象
     * @see #now()
     * @since 1.0
     */
    public static Date nowDateTime() {
        return now();
    }

    /**
     * 获取当前瞬时的日期时间分段值，分别分为年、月（月份会加1）、日、周几（周自动减1）、时、分、秒、毫秒<br>
     * 月份的取值：[1,12]<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     *
     * @return 当前瞬时的日期时间分段值的数组，共8位，分别代表年、月（月份会加1）、日、周几（周自动减1）、时、分、秒、毫秒<br>
     * 月份的取值：[1-12]<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     * @see #toArray(Date)
     * @since 1.0
     */
    public static int[] toArray() {
        return toArray(now());
    }

    /**
     * 获取当前的年份值
     *
     * @return 年份值
     * @see #now()
     * @see #getYear(Date)
     * @since 1.0
     */
    public static int nowYear() {
        return getYear(now());
    }

    /**
     * 获取当前的月份值
     *
     * @return 月份值
     * @see #now()
     * @see #getMonth(Date)
     * @since 1.0
     */
    public static int nowMonth() {
        return getMonth(now());
    }

    /**
     * 获取当前的日期值
     *
     * @return 日期值
     * @see #now()
     * @see #getDay(Date)
     * @since 1.0
     */
    public static int nowDay() {
        return getDay(now());
    }

    /**
     * 获取当前的日期属于本年中的第几天
     *
     * @return 当前的日期属于本年中的第几天
     * @see #now()
     * @see #getDayOfYear(Date)
     * @since 1.0
     */
    public static int nowDayOfYear() {
        return getDayOfYear(now());
    }

    /**
     * 获取当前的日期属于本月中的第几天
     *
     * @return 当前的日期属于本月中的第几天
     * @see #now()
     * @see #getDayOfMonth(Date)
     * @since 1.0
     */
    public static int nowDayOfMonth() {
        return getDayOfMonth(now());
    }

    /**
     * 获取当前日期对应周几<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     *
     * @return 日期对应的周几<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     * @see #now()
     * @see #getWeek(Date)
     * @since 1.0
     */
    public static int nowWeek() {
        return getWeek(now());
    }

    /**
     * 获取当前日期属于本年中的第几周
     *
     * @return 当前日期在本年中的第几周
     * @see #getWeekOfYear(Date)
     * @see #now()
     * @since 1.0
     */
    public static int nowWeekOfYear() {
        return getWeekOfYear(now());
    }

    /**
     * 获取当前日期属于本月中的第几周
     *
     * @return 当前日期在本月中的第几周
     * @see #getWeekOfMonth(Date)
     * @see #now()
     * @since 1.0
     */
    public static int nowWeekOfMonth() {
        return getWeekOfMonth(now());
    }

    /**
     * 获取当前时间对应的小时值（24小时制）
     *
     * @return 当前时间对应的小时值（24小时制）
     * @see #getHour(Date)
     * @see #now()
     * @since 1.0
     */
    public static int nowHour() {
        return getHour(now());
    }

    /**
     * 获取当前时间对应的分钟值
     *
     * @return 当前时间对应的分钟值
     * @see #getMinute(Date)
     * @see #now()
     * @since 1.0
     */
    public static int nowMinute() {
        return getMinute(now());
    }

    /**
     * 获取当前时间对应的秒值
     *
     * @return 当前时间对应的秒值
     * @see #getSecond(Date)
     * @see #now()
     * @since 1.0
     */
    public static int nowSecond() {
        return getSecond(now());
    }

    /**
     * 获取当前时间对应的毫秒值
     *
     * @return 当前时间对应的毫秒值
     * @see #getMillisecond(Date)
     * @see #now()
     * @since 1.0
     */
    public static int nowMillisecond() {
        return getMillisecond(now());
    }

    /**
     * 当前年份是否是闰年
     *
     * @return 如果是闰年则返回true，否则返回false
     * @see #isLeapYear(Date)
     * @since 1.0
     */
    public static boolean nowYearIsLeap() {
        return isLeapYear(now());
    }

    /**
     * 获取当前月份对应的总天数
     *
     * @return 当前月份对应的总天数
     * @see #now()
     * @see #getDaysOfMonth(Date)
     * @since 1.0
     */
    public static int nowMonthDays() {
        return getDaysOfMonth(now());
    }

    /**
     * 获取当前年份对应的总天数
     *
     * @return 当前年份的总天数
     * @see #getYear(Date)
     * @see #now()
     * @since 1.0
     */
    public static int nowYearDays() {
        return getYear(now());
    }

    /**
     * 获取当前时间戳
     *
     * @return 时间戳
     * @see System#currentTimeMillis()
     * @since 1.0
     */
    public static long nowTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 给当前日期时间添加一个指定的天数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param days 添加的天数
     * @return 添加指定天数后的新日期时间对象
     * @see #addDays(Date, int)
     * @since 1.0
     */
    public static Date addDays(int days) {
        return addDays(now(), days, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给当前的日期时间减去一个指定的天数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param days 减去的天数
     * @return 减去指定天数后的新日期时间对象
     * @see #addDays(Date, int)
     * @since 1.0
     */
    public static Date minusDays(int days) {
        return addDays(now(), -days, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给当前日期时间添加一个指定的月数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param months 添加的月数
     * @return 添加指定月数后的新日期时间对象
     * @see #addMonths(Date, int)
     * @since 1.0
     */
    public static Date addMonths(int months) {
        return addMonths(now(), months, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给当前的日期时间减去一个指定的月数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param months 减去的月数
     * @return 减去指定月数后的新日期时间对象
     * @see #addMonths(Date, int)
     * @since 1.0
     */
    public static Date minusMonths(int months) {
        return addMonths(now(), -months, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给当前日期时间添加一个指定的小时数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param hours 添加的小时数
     * @return 添加指定小时数后的新日期时间对象
     * @see #addHours(Date, int)
     * @since 1.0
     */
    public static Date addHours(int hours) {
        return addHours(now(), hours, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给当前的日期时间减去一个指定的小时数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param hours 减去的小时数
     * @return 减去指定小时数后的新日期时间对象
     * @see #addHours(Date, int)
     * @since 1.0
     */
    public static Date minusHours(int hours) {
        return addHours(now(), -hours, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给当前日期时间添加一个指定的分钟数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param minutes 添加的分钟数
     * @return 添加指定分钟数后的新日期时间对象
     * @see #addMinutes(Date, int)
     * @since 1.0
     */
    public static Date addMinutes(int minutes) {
        return addMinutes(now(), minutes, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给当前的日期时间减去一个指定的分钟数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param minutes 减去的分钟数
     * @return 减去指定分钟数后的新日期时间对象
     * @see #addMinutes(Date, int)
     * @since 1.0
     */
    public static Date minusMinutes(int minutes) {
        return addMinutes(now(), -minutes, TimeMode.NOW_OF_DAY);
    }

    /************************************* current date end *************************************/


    /************************************* date analysis start *************************************/

    /**
     * 获取指定日期时间的分段值，分别分为年、月（月份会加1）、日、周几（周自动减1）、时、分、秒、毫秒<br>
     * 月份的取值：[1,12]<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     *
     * @param date 日期时间对象
     * @return 日期时间分段值的数组，共8位，分别代表年、月（月份会加1）、日、周几（周自动减1）、时、分、秒、毫秒<br>
     * 月份的取值：[1-12]<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     * @since 1.0
     */
    public static int[] toArray(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return toArray(calendar);
    }

    /**
     * 获取指定时间戳对应日期时间的分段值，分别分为年、月（月份会加1）、日、周几（周自动减1）、时、分、秒、毫秒<br>
     * 月份的取值：[1,12]<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     *
     * @param timestamp 时间戳
     * @return 日期分段值的数组，共8位，分别代表年、月（月份会加1）、日、周几（周自动减1）、时、分、秒、毫秒<br>
     * 月份的取值：[1-12]<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     * @see #toArray(Date)
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static int[] toArray(long timestamp) {
        return toArray(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间的年份值
     *
     * @param date 日期时间对象
     * @return 该日期对应的年份值
     * @see Calendar#get(int)
     * @see Calendar#YEAR
     * @since 1.0
     */
    public static int getYear(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取指定时间戳对应的年份值
     *
     * @param timestamp 时间戳
     * @return 时间戳对应的年份值
     * @see #getYear(Date)
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static int getYear(long timestamp) {
        return getYear(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间对应的月份值，已进行+1处理
     *
     * @param date 日期时间对象
     * @return 该日期时间对应的月份值，已进行+1处理
     * @see Calendar#get(int)
     * @see Calendar#MONTH
     * @since 1.0
     */
    public static int getMonth(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定时间戳对应的月份值，已进行+1处理
     *
     * @param timestamp 时间戳
     * @return 时间戳对应的月份值，已进行+1处理
     * @see #getMonth(Date)
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static int getMonth(long timestamp) {
        return getYear(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间对应的日期值
     *
     * @param date 日期时间对象
     * @return 该日期时间对应的日期值
     * @see Calendar#get(int)
     * @see Calendar#DAY_OF_MONTH
     * @since 1.0
     */
    public static int getDay(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定时间戳对应的日期值
     *
     * @param timestamp 时间戳
     * @return 时间戳对应的日期值
     * @see #getDay(Date)
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static int getDay(long timestamp) {
        return getDay(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间对应的日期属于该年份的第几天
     *
     * @param date 日期时间对象
     * @return 该日期是该日期对应年份中的第几天
     * @see Calendar#get(int)
     * @see Calendar#DAY_OF_YEAR
     * @since 1.0
     */
    public static int getDayOfYear(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取指定时间戳对应的日期属于该年份的第几天
     *
     * @param timestamp 时间戳
     * @return 时间戳对应的日期是该日期对应年份中的第几天
     * @see #getDayOfYear(Date)
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static int getDayOfYear(long timestamp) {
        return getDayOfYear(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间对应的日期属于该月份的第几天
     *
     * @param date 日期时间对象
     * @return 该日期是该日期对应月份中的第几天
     * @see Calendar#get(int)
     * @see Calendar#DAY_OF_MONTH
     * @since 1.0
     */
    public static int getDayOfMonth(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定时间戳对应的日期属于该月份的第几天
     *
     * @param timestamp 时间戳
     * @return 时间戳对应的日期是该日期对应月份中的第几天
     * @see #getDayOfMonth(Date)
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static int getDayOfMonth(long timestamp) {
        return getDayOfMonth(newDateTime(timestamp));
    }

    /**
     * 获取指定日期对应周几<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     *
     * @param date 日期
     * @return 日期对应的周几<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     * @see Calendar#get(int)
     * @see Calendar#DAY_OF_WEEK
     * @since 1.0
     */
    public static int getWeek(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取指定时间戳对应周几<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     *
     * @param timestamp 时间戳
     * @return 日期对应的周几<br>
     * 周几的取值：0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     * @see #getWeek(Date)
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static int getWeek(long timestamp) {
        return getWeek(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间是其对应年份中的第几周
     *
     * @param date 日期时间对象
     * @return 该日期时间在对应的年份中的第几周
     * @see Calendar#get(int)
     * @see Calendar#WEEK_OF_YEAR
     * @since 1.0
     */
    public static int getWeekOfYear(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取指定时间戳是其对应年份中的第几周
     *
     * @param timestamp 时间戳
     * @return 该时间戳在对应的年份中的第几周
     * @see #newDateTime(long)
     * @see #getWeekOfYear(Date)
     * @since 1.0
     */
    public static int getWeekOfYear(long timestamp) {
        return getWeekOfYear(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间是其对应月份中的第几周
     *
     * @param date 日期时间对象
     * @return 该日期时间在对应的月份中的第几周
     * @see Calendar#get(int)
     * @see Calendar#WEEK_OF_MONTH
     * @since 1.0
     */
    public static int getWeekOfMonth(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取指定时间戳是其对应月份中的第几周
     *
     * @param timestamp 时间戳
     * @return 该时间戳在对应的月份中的第几周
     * @see #newDateTime(long)
     * @see #getWeekOfMonth(Date)
     * @since 1.0
     */
    public static int getWeekOfMonth(long timestamp) {
        return getWeekOfMonth(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间的小时值（24小时制）
     *
     * @param date 日期时间对象
     * @return 该日期时间对应的小时值（24小时制）
     * @see Calendar#get(int)
     * @see Calendar#HOUR_OF_DAY
     * @since 1.0
     */
    public static int getHour(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定时间戳对应的小时值（24小时制）
     *
     * @param timestamp 时间戳
     * @return 该时间戳对应的小时值（24小时制）
     * @see #newDateTime(long)
     * @see #getHour(Date)
     * @since 1.0
     */
    public static int getHour(long timestamp) {
        return getHour(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间的分钟值
     *
     * @param date 日期时间对象
     * @return 该日期时间对应的分钟值
     * @see Calendar#get(int)
     * @see Calendar#MINUTE
     * @since 1.0
     */
    public static int getMinute(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取指定时间戳对应的分钟值
     *
     * @param timestamp 时间戳
     * @return 该时间戳对应的分钟值
     * @see #newDateTime(long)
     * @see #getMinute(Date)
     * @since 1.0
     */
    public static int getMinute(long timestamp) {
        return getMinute(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间的秒值
     *
     * @param date 日期时间对象
     * @return 该日期时间对应的秒值
     * @see Calendar#get(int)
     * @see Calendar#SECOND
     * @since 1.0
     */
    public static int getSecond(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取指定时间戳对应的秒值
     *
     * @param timestamp 时间戳
     * @return 该时间戳对应的秒值
     * @see #newDateTime(long)
     * @see #getSecond(Date)
     * @since 1.0
     */
    public static int getSecond(long timestamp) {
        return getSecond(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间的毫秒值
     *
     * @param date 日期时间对象
     * @return 该日期时间对应的毫秒值
     * @see Calendar#get(int)
     * @see Calendar#MILLISECOND
     * @since 1.0
     */
    public static int getMillisecond(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.NOW_OF_DAY);
        return calendar.get(Calendar.MILLISECOND);
    }

    /**
     * 获取指定时间戳对应的毫秒值
     *
     * @param timestamp 时间戳
     * @return 该时间戳对应的毫秒值
     * @see #newDateTime(long)
     * @see #getMillisecond(Date)
     * @since 1.0
     */
    public static int getMillisecond(long timestamp) {
        return getMillisecond(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间所属月份的总天数
     *
     * @param date 日期时间
     * @return 日期时间所属月的总天数
     * @see #toArray(Date)
     * @see #getDaysOfMonth(int, int)
     * @since 1.0
     */
    public static int getDaysOfMonth(Date date) {
        int[] items = toArray(date);
        return getDaysOfMonth(items[0], items[1]);
    }

    /**
     * 获取指定时间戳所属月份的总天数
     *
     * @param timestamp 时间戳
     * @return 时间戳所属月的总天数
     * @see #toArray(long)
     * @see #getDaysOfMonth(int, int)
     * @since 1.0
     */
    public static int getDaysOfMonth(long timestamp) {
        int[] items = toArray(timestamp);
        return getDaysOfMonth(items[0], items[1]);
    }

    /**
     * 获取指定日期时间对应年份的总天数
     *
     * @param date 日期时间对象
     * @return 该日期时间对应年份的总天数
     * @see #getYear(Date)
     * @see #getDaysOfYear(int)
     * @since 1.0
     */
    public static int getDaysOfYear(Date date) {
        return getDaysOfYear(getYear(date));
    }

    /**
     * 获取指定时间戳对应年份的总天数
     *
     * @param timestamp 时间戳
     * @return 该时间戳对应年份的总天数
     * @see #getYear(long)
     * @see #getDaysOfYear(int)
     * @since 1.0
     */
    public static int getDaysOfYear(long timestamp) {
        return getDaysOfYear(getYear(timestamp));
    }

    /**
     * 获取指定日期的开始日期时间对象，时间被固定为00:00:00
     *
     * @param date 原日期时间对象
     * @return 对应的开始日期时间对象
     * @see Calendar#getTime()
     * @since 1.0
     */
    public static Date beginOfDate(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.BEGIN_OF_DAY);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的结束日期时间对象，时间被固定为23:59:59
     *
     * @param date 原日期时间对象
     * @return 对应的结束日期时间对象
     * @see Calendar#getTime()
     * @since 1.0
     */
    public static Date endOfDate(Date date) {
        Calendar calendar = getCalendar(date, TimeMode.END_OF_DAY);
        return calendar.getTime();
    }

    /**
     * 获取指定时间戳对应的开始日期时间对象，时间被固定为00:00:00
     *
     * @param timestamp 原时间戳
     * @return 对应的开始日期时间对象
     * @see #newDateTime(long)
     * @see #beginOfDate(Date)
     * @since 1.0
     */
    public static Date beginOfDate(long timestamp) {
        return beginOfDate(newDateTime(timestamp));
    }

    /**
     * 获取指定时间戳对应的结束日期时间对象，时间被固定为23:59:59
     *
     * @param timestamp 原时间戳
     * @return 对应的结束日期时间对象
     * @see #newDateTime(long)
     * @see #beginOfDate(Date)
     * @since 1.0
     */
    public static Date endOfDate(long timestamp) {
        return endOfDate(newDateTime(timestamp));
    }

    /**
     * 获取指定日期时间所在月份的开始日期（第一天），时间被固定为00:00:00
     *
     * @param date 日期时间对象
     * @return 该日期时间对象所在月份的开始日期
     * @see #toArray(Date)
     * @see #newBeginDate(int, int, int)
     * @since 1.0
     */
    public static Date beginOfMonth(Date date) {
        int[] items = toArray(date);
        return newBeginDate(items[0], items[1], 1);
    }

    /**
     * 获取指定时间戳间所在月份的开始日期（第一天），时间被固定为00:00:00
     *
     * @param timestamp 时间戳
     * @return 该时间戳所在月份的开始日期
     * @see #toArray(long)
     * @see #newBeginDate(int, int, int)
     * @since 1.0
     */
    public static Date beginOfMonth(long timestamp) {
        int[] items = toArray(timestamp);
        return newBeginDate(items[0], items[1], 1);
    }

    /**
     * 获取指定日期时间所在月份的结束日期（最后一天），时间被固定为23:59:59
     *
     * @param date 日期时间对象
     * @return 该日期时间对象所在月份的结束日期
     * @see #toArray(Date)
     * @see #getDaysOfMonth(int, int)
     * @see #newEndDate(int, int, int)
     * @since 1.0
     */
    public static Date endOfMonth(Date date) {
        int[] items = toArray(date);
        int daysOfMonth = getDaysOfMonth(items[0], items[1]);
        return newEndDate(items[0], items[1], daysOfMonth);
    }

    /**
     * 获取指定时间戳所在月份的结束日期（最后一天），时间被固定为23:59:59
     *
     * @param timestamp 时间戳
     * @return 该时间戳所在月份的结束日期
     * @see #toArray(long)
     * @see #getDaysOfMonth(int, int)
     * @see #newEndDate(int, int, int)
     * @since 1.0
     */
    public static Date endOfMonth(long timestamp) {
        int[] items = toArray(timestamp);
        int daysOfMonth = getDaysOfMonth(items[0], items[1]);
        return newEndDate(items[0], items[1], daysOfMonth);
    }

    /************************************* date analysis end *************************************/


    /************************************* date format/parse start *************************************/

    /**
     * 获取某个日期的时间戳
     *
     * @param date 日期
     * @return 该日期对应的时间戳
     * @see Date#getTime()
     * @since 1.0
     */
    public static long timestamp(Date date) {
        return date.getTime();
    }

    /**
     * 将日期时间格式为指定时区和语言环境下指定模式的字符串
     *
     * @param date     日期时间
     * @param pattern  <a href="#dateFormat">格式化字符串</a>
     * @param timeZone 时区对象
     * @param locale   语言环境
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String, Locale)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see SimpleDateFormat#format(Date)
     * @since 1.0
     */
    public static String format(Date date, String pattern, TimeZone timeZone, Locale locale) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
        formatter.setTimeZone(timeZone);
        return formatter.format(date);
    }

    /**
     * 将日期时间格式为指定时区下指定模式的字符串
     *
     * @param date     日期时间
     * @param pattern  <a href="#dateFormat">格式化字符串</a>
     * @param timeZone 时区对象
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String, Locale)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see SimpleDateFormat#format(Date)
     * @since 1.0
     */
    public static String format(Date date, String pattern, TimeZone timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setTimeZone(timeZone);
        return formatter.format(date);
    }

    /**
     * 将日期时间格式为指定语言环境下指定模式的字符串
     *
     * @param date    日期时间
     * @param pattern <a href="#dateFormat">格式化字符串</a>
     * @param locale  语言环境
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String, Locale)
     * @see SimpleDateFormat#format(Date)
     * @since 1.0
     */
    public static String format(Date date, String pattern, Locale locale) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
        return formatter.format(date);
    }

    /**
     * 将日期时间格式为本地默认语言环境下指定模式的字符串
     *
     * @param date    日期时间
     * @param pattern <a href="#dateFormat">格式化字符串</a>
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#format(Date)
     * @since 1.0
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * 将日期时间格式为指定时区下指定模式的字符串
     *
     * @param date     日期时间
     * @param timeZone 时区对象
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see SimpleDateFormat#format(Date)
     * @since 1.0
     */
    public static String format(Date date, TimeZone timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_FORMAT);
        formatter.setTimeZone(timeZone);
        return formatter.format(date);
    }

    /**
     * 将指定的日期时间格式化为指定语言环境对应的默认格式化字符串
     *
     * @param date   日期时间
     * @param locale 本地语言环境<br>
     *               可以通过<p><code>
     *               //language为语言代码，可以使用Locale.getAvailableLocales()查看<br>
     *               Locale.forLanguageTag("language");
     *               </code></p>例如zh(中文)；en(英文)等。
     * @return 被该语言环境下默认格式化字符串格式化之后的日期时间字符串
     * @see SimpleDateFormat#SimpleDateFormat()
     * @see LocaleProviderAdapter#getResourceBundleBased()
     * @see LocaleProviderAdapter#getLocaleResources(Locale)
     * @see sun.util.locale.provider.LocaleResources#getDateTimePattern(int, int, Calendar)
     * @since 1.0
     */
    public static String format(Date date, Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
        String pattern = LocaleProviderAdapter.getResourceBundleBased().getLocaleResources(locale).getDateTimePattern(3, 3, calendar);
        return format(date, pattern);
    }

    /**
     * 将日期格式为指定模式{@value DateTimeUtils#DEFAULT_FORMAT}的字符串
     *
     * @param date 日期
     * @return 格式化后的日期字符串
     * @see #format(Date, String)
     * @since 1.0
     */
    public static String format(Date date) {
        return format(date, DEFAULT_FORMAT);
    }

    /**
     * 将时间戳对应的日期时间格式为指定时区和语言环境下指定模式的字符串
     *
     * @param timestamp 时间戳
     * @param pattern   <a href="#dateFormat">格式化字符串</a>
     * @param timeZone  时区对象
     * @param locale    语言环境
     * @return 格式化后的日期字符串
     * @see #newDateTime(long)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see #format(long, String, TimeZone, Locale)
     * @since 1.0
     */
    public static String format(long timestamp, String pattern, TimeZone timeZone, Locale locale) {
        return format(newDateTime(timestamp), pattern, timeZone, locale);
    }

    /**
     * 将时间戳对应的日期时间格式为指定时区下指定模式的字符串
     *
     * @param timestamp 时间戳
     * @param pattern   <a href="#dateFormat">格式化字符串</a>
     * @param timeZone  时区对象
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see #newDateTime(long)
     * @see #format(long, String, TimeZone)
     * @since 1.0
     */
    public static String format(long timestamp, String pattern, TimeZone timeZone) {
        return format(newDateTime(timestamp), pattern, timeZone);
    }

    /**
     * 将时间戳对应的日期时间格式为指定语言环境下指定模式的字符串
     *
     * @param timestamp 时间戳
     * @param pattern   <a href="#dateFormat">格式化字符串</a>
     * @param locale    语言环境
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see #newDateTime(long)
     * @see #format(long, String, Locale)
     * @since 1.0
     */
    public static String format(long timestamp, String pattern, Locale locale) {
        return format(newDateTime(timestamp), pattern, locale);
    }

    /**
     * 将时间戳对应的日期时间格式为本地默认语言环境下指定模式的字符串
     *
     * @param timestamp 时间戳
     * @param pattern   <a href="#dateFormat">格式化字符串</a>
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see #newDateTime(long)
     * @see #format(long, String)
     * @since 1.0
     */
    public static String format(long timestamp, String pattern) {
        return format(newDateTime(timestamp), pattern);
    }

    /**
     * 将时间戳格式为指定时区下指定模式的字符串
     *
     * @param timestamp 时间戳
     * @param timeZone  时区对象
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see SimpleDateFormat#format(Date)
     * @since 1.0
     */
    public static String format(long timestamp, TimeZone timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_FORMAT);
        formatter.setTimeZone(timeZone);
        return formatter.format(newDateTime(timestamp));
    }

    /**
     * 将指定时间戳对应的日期时间格式化为指定语言环境对应的默认格式化字符串
     *
     * @param timestamp 时间戳
     * @param locale    本地语言环境<br>
     *                  可以通过<p><code>
     *                  //language为语言代码，可以使用Locale.getAvailableLocales()查看<br>
     *                  Locale.forLanguageTag("language");
     *                  </code></p>例如zh(中文)；en(英文)等。
     * @return 被该语言环境下默认格式化字符串格式化之后的日期时间字符串
     * @see #format(Date, Locale)
     * @since 1.0
     */
    public static String format(long timestamp, Locale locale) {
        return format(newDateTime(timestamp), locale);
    }

    /**
     * 将时间戳格式为指定模式{@value DateTimeUtils#DEFAULT_FORMAT}的字符串
     *
     * @param timestamp 时间戳
     * @return 格式化后的日期字符串
     * @see #format(long, String)
     * @since 1.0
     */
    public static String format(long timestamp) {
        return format(timestamp, DEFAULT_FORMAT);
    }

    /**
     * 将日历对应的日期时间格式为指定时区和语言环境下指定模式的字符串
     *
     * @param calendar 日历
     * @param pattern  <a href="#dateFormat">格式化字符串</a>
     * @param timeZone 时区对象
     * @param locale   语言环境
     * @return 格式化后的日期字符串
     * @see #newDateTime(long)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see #format(Date, String, TimeZone, Locale)
     * @see Calendar#cachedLocaleData
     * @since 1.0
     */
    public static String format(Calendar calendar, String pattern, TimeZone timeZone, Locale locale) {
        return format(calendar.getTime(), pattern, timeZone, locale);
    }

    /**
     * 将日历对应的日期时间格式为指定时区下指定模式的字符串
     *
     * @param calendar 日历
     * @param pattern  <a href="#dateFormat">格式化字符串</a>
     * @param timeZone 时区对象
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see #newDateTime(long)
     * @see #format(Date, String, TimeZone)
     * @since 1.0
     */
    public static String format(Calendar calendar, String pattern, TimeZone timeZone) {
        return format(calendar.getTime(), pattern, timeZone);
    }

    /**
     * 将日历对应的日期时间格式为指定语言环境下指定模式的字符串
     *
     * @param calendar 日历
     * @param pattern  <a href="#dateFormat">格式化字符串</a>
     * @param locale   语言环境
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see #newDateTime(long)
     * @see #format(Date, String, Locale)
     * @since 1.0
     */
    public static String format(Calendar calendar, String pattern, Locale locale) {
        return format(calendar.getTime(), pattern, locale);
    }

    /**
     * 将日历对应的日期时间格式为本地默认语言环境下指定模式的字符串
     *
     * @param calendar 日历
     * @param pattern  <a href="#dateFormat">格式化字符串</a>
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see #newDateTime(long)
     * @see #format(Date, String)
     * @since 1.0
     */
    public static String format(Calendar calendar, String pattern) {
        return format(calendar.getTime(), pattern);
    }

    /**
     * 将指定日历对应的日期时间格式化为指定语言环境对应的默认格式化字符串
     *
     * @param calendar 日历
     * @param timeZone 时区对象
     * @return 被该语言环境下默认格式化字符串格式化之后的日期时间字符串
     * @see #format(Date, TimeZone)
     * @since 1.0
     */
    public static String format(Calendar calendar, TimeZone timeZone) {
        return format(calendar.getTime(), timeZone);
    }

    /**
     * 将指定日历对应的日期时间格式化为指定语言环境对应的默认格式化字符串
     *
     * @param calendar 日历
     * @param locale   本地语言环境<br>
     *                 可以通过<p><code>
     *                 //language为语言代码，可以使用Locale.getAvailableLocales()查看<br>
     *                 Locale.forLanguageTag("language");
     *                 </code></p>例如zh(中文)；en(英文)等。
     * @return 被该语言环境下默认格式化字符串格式化之后的日期时间字符串
     * @see #format(Date, Locale)
     * @since 1.0
     */
    public static String format(Calendar calendar, Locale locale) {
        return format(calendar.getTime(), locale);
    }

    /**
     * 将日历格式为指定模式{@value DateTimeUtils#DEFAULT_FORMAT}的字符串
     *
     * @param calendar 日历
     * @return 格式化后的日期字符串
     * @see #format(Date, String)
     * @since 1.0
     */
    public static String format(Calendar calendar) {
        return format(calendar.getTime(), DEFAULT_FORMAT);
    }

    /**
     * 将当前日期时间格式为指定时区和语言环境下指定模式的字符串
     *
     * @param pattern  <a href="#dateFormat">格式化字符串</a>
     * @param timeZone 时区对象
     * @param locale   语言环境
     * @return 格式化后的日期字符串
     * @see #format(Date, String, Locale)
     * @see #now()
     * @since 1.0
     */
    public static String formatOfNow(String pattern, TimeZone timeZone, Locale locale) {
        return format(now(), pattern, timeZone, locale);
    }

    /**
     * 将当前日期时间格式为指定时区下指定模式的字符串
     *
     * @param pattern  <a href="#dateFormat">格式化字符串</a>
     * @param timeZone 时区对象
     * @return 格式化后的日期字符串
     * @see #format(Date, String, Locale)
     * @see #now()
     * @since 1.0
     */
    public static String formatOfNow(String pattern, TimeZone timeZone) {
        return format(now(), pattern, timeZone);
    }

    /**
     * 将当前日期时间格式为指定语言环境下指定模式的字符串
     *
     * @param pattern <a href="#dateFormat">格式化字符串</a>
     * @param locale  语言环境
     * @return 格式化后的日期字符串
     * @see #format(Date, String, Locale)
     * @see #now()
     * @since 1.0
     */
    public static String formatOfNow(String pattern, Locale locale) {
        return format(now(), pattern, locale);
    }

    /**
     * 将当前日期时间格式为指定时区下指定模式的字符串
     *
     * @param timeZone 时区对象
     * @return 格式化后的日期字符串
     * @see #format(Date, TimeZone)
     * @see #now()
     * @since 1.0
     */
    public static String formatOfNow(TimeZone timeZone) {
        return format(now(), timeZone);
    }

    /**
     * 将当前日期时间格式化为指定语言环境对应的默认格式化字符串
     *
     * @param locale 本地语言环境<br>
     *               可以通过<p><code>
     *               //language为语言代码，可以使用Locale.getAvailableLocales()查看<br>
     *               Locale.forLanguageTag("language");
     *               </code></p>例如zh(中文)；en(英文)等。
     * @return 被该语言环境下默认格式化字符串格式化之后的日期时间字符串
     * @see #now()
     * @see #format(Date, Locale)
     * @since 1.0
     */
    public static String formatOfNow(Locale locale) {
        return format(now(), locale);
    }

    /**
     * 将当前日期时间格式为本地默认语言环境下指定模式的字符串
     *
     * @param pattern <a href="#dateFormat">格式化字符串</a>
     * @return 格式化后的日期字符串
     * @see #format(Date, String)
     * @since 1.0
     */
    public static String formatOfNow(String pattern) {
        return format(now(), pattern);
    }

    /**
     * 将当前时间格式为指定模式{@value DateTimeUtils#DEFAULT_FORMAT}的字符串
     *
     * @return 格式化后的日期字符串
     * @see #format(Date, String)
     * @since 1.0
     */
    public static String formatOfNow() {
        return format(now(), DEFAULT_FORMAT);
    }

    /**
     * 将当前日期时间格式为指定模式{@value DateTimeUtils#DEFAULT_DATE_FORMAT}的字符串
     *
     * @param date     日期时间
     * @param timeZone 时区对象
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see SimpleDateFormat#format(Date)
     * @since 1.0
     */
    public static String formatDate(Date date, TimeZone timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        formatter.setTimeZone(timeZone);
        return formatter.format(date);
    }

    /**
     * 将日期格式为指定模式{@value DateTimeUtils#DEFAULT_DATE_FORMAT}的字符串
     *
     * @param date 日期
     * @return 格式化后的日期字符串
     * @see #format(Date, String)
     * @since 1.0
     */
    public static String formatDate(Date date) {
        return format(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 将时间戳转换为指定模式{@value DateTimeUtils#DEFAULT_DATE_FORMAT}的字符串
     *
     * @param timestamp 时间戳
     * @param timeZone  时区对象
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see SimpleDateFormat#format(Date)
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static String formatDate(long timestamp, TimeZone timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        formatter.setTimeZone(timeZone);
        return formatter.format(newDateTime(timestamp));
    }

    /**
     * 将指定的日期时间格式化为指定语言环境对应的默认格式化字符串
     *
     * @param date   日期
     * @param locale 本地语言环境<br>
     *               可以通过<p><code>
     *               //language为语言代码，可以使用Locale.getAvailableLocales()查看<br>
     *               Locale.forLanguageTag("language");
     *               </code></p>例如zh(中文)；en(英文)等。
     * @return 被该语言环境下默认格式化字符串格式化之后的日期时间字符串
     * @see SimpleDateFormat#SimpleDateFormat()
     * @see LocaleProviderAdapter#getResourceBundleBased()
     * @see LocaleProviderAdapter#getLocaleResources(Locale)
     * @see sun.util.locale.provider.LocaleResources#getDateTimePattern(int, int, Calendar)
     * @since 1.0
     */
    public static String formatDate(Date date, Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
        String pattern = LocaleProviderAdapter.getResourceBundleBased().getLocaleResources(locale).getDateTimePattern(3, 3, calendar);
        return format(date, pattern);
    }

    /**
     * 将指定的时间戳格式化为指定语言环境对应的默认格式化字符串
     *
     * @param timestamp 时间戳
     * @param locale    本地语言环境<br>
     *                  可以通过<p><code>
     *                  //language为语言代码，可以使用Locale.getAvailableLocales()查看<br>
     *                  Locale.forLanguageTag("language");
     *                  </code></p>例如zh(中文)；en(英文)等。
     * @return 被该语言环境下默认格式化字符串格式化之后的日期时间字符串
     * @see SimpleDateFormat#SimpleDateFormat()
     * @see LocaleProviderAdapter#getResourceBundleBased()
     * @see LocaleProviderAdapter#getLocaleResources(Locale)
     * @see sun.util.locale.provider.LocaleResources#getDateTimePattern(int, int, Calendar)
     * @since 1.0
     */
    public static String formatDate(long timestamp, Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
        String pattern = LocaleProviderAdapter.getResourceBundleBased().getLocaleResources(locale).getDateTimePattern(3, 3, calendar);
        return format(newDateTime(timestamp), pattern);
    }

    /**
     * 将时间戳格式为指定模式{@value DateTimeUtils#DEFAULT_DATE_FORMAT}的字符串
     *
     * @param timestamp 时间戳
     * @return 格式化后的日期字符串
     * @see #format(long, String)
     * @since 1.0
     */
    public static String formatDate(long timestamp) {
        return format(timestamp, DEFAULT_DATE_FORMAT);
    }

    /**
     * 将当前日期时间格式为指定语言环境下的字符串
     *
     * @param locale 本地语言环境<br>
     *               可以通过<p><code>
     *               //language为语言代码，可以使用Locale.getAvailableLocales()查看<br>
     *               Locale.forLanguageTag("language");
     *               </code></p>例如zh(中文)；en(英文)等。
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see SimpleDateFormat#format(Date)
     * @see #now()
     * @since 1.0
     */
    public static String formatDateOfNow(Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
        String pattern = LocaleProviderAdapter.getResourceBundleBased().getLocaleResources(locale).getDateTimePattern(3, 3, calendar);
        return format(now(), pattern);
    }

    /**
     * 将当前日期时间格式为指定时区的字符串
     *
     * @param timeZone 时区对象
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see SimpleDateFormat#format(Date)
     * @see #now()
     * @since 1.0
     */
    public static String formatDateOfNow(TimeZone timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        formatter.setTimeZone(timeZone);
        return formatter.format(now());
    }

    /**
     * 将当前时间格式为指定模式{@value DateTimeUtils#DEFAULT_DATE_FORMAT}的字符串
     *
     * @return 格式化后的日期字符串
     * @see #format(Date, String)
     * @since 1.0
     */
    public static String formatDateOfNow() {
        return format(now(), DEFAULT_DATE_FORMAT);
    }

    /**
     * 将制定的日期时间格式为指定模式{@value DateTimeUtils#DEFAULT_TIME_FORMAT}的字符串
     *
     * @param date     日期时间
     * @param timeZone 时区对象
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see SimpleDateFormat#format(Date)
     * @since 1.0
     */
    public static String formatTime(Date date, TimeZone timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        formatter.setTimeZone(timeZone);
        return formatter.format(date);
    }

    /**
     * 将日期格式为指定模式{@value DateTimeUtils#DEFAULT_TIME_FORMAT}的字符串
     *
     * @param date 日期
     * @return 格式化后的时间字符串
     * @see #format(Date, String)
     * @since 1.0
     */
    public static String formatTime(Date date) {
        return format(date, DEFAULT_TIME_FORMAT);
    }

    /**
     * 将时间戳格式为指定模式{@value DateTimeUtils#DEFAULT_TIME_FORMAT}的字符串
     *
     * @param timestamp 时间戳
     * @param timeZone  时区对象
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see SimpleDateFormat#format(Date)
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static String formatTime(long timestamp, TimeZone timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        formatter.setTimeZone(timeZone);
        return formatter.format(newDateTime(timestamp));
    }

    /**
     * 将指定的时间戳格式化为指定语言环境对应的默认格式化字符串
     *
     * @param date   日期
     * @param locale 本地语言环境<br>
     *               可以通过<p><code>
     *               //language为语言代码，可以使用Locale.getAvailableLocales()查看<br>
     *               Locale.forLanguageTag("language");
     *               </code></p>例如zh(中文)；en(英文)等。
     * @return 被该语言环境下默认格式化字符串格式化之后的日期时间字符串
     * @see SimpleDateFormat#SimpleDateFormat()
     * @see LocaleProviderAdapter#getResourceBundleBased()
     * @see LocaleProviderAdapter#getLocaleResources(Locale)
     * @see sun.util.locale.provider.LocaleResources#getDateTimePattern(int, int, Calendar)
     * @since 1.0
     */
    public static String formatTime(Date date, Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
        String pattern = LocaleProviderAdapter.getResourceBundleBased().getLocaleResources(locale).getDateTimePattern(3, 3, calendar);
        return format(date, pattern);
    }

    /**
     * 将指定的时间戳格式化为指定语言环境对应的默认格式化字符串
     *
     * @param timestamp 时间戳
     * @param locale    本地语言环境<br>
     *                  可以通过<p><code>
     *                  //language为语言代码，可以使用Locale.getAvailableLocales()查看<br>
     *                  Locale.forLanguageTag("language");
     *                  </code></p>例如zh(中文)；en(英文)等。
     * @return 被该语言环境下默认格式化字符串格式化之后的日期时间字符串
     * @see SimpleDateFormat#SimpleDateFormat()
     * @see LocaleProviderAdapter#getResourceBundleBased()
     * @see LocaleProviderAdapter#getLocaleResources(Locale)
     * @see sun.util.locale.provider.LocaleResources#getDateTimePattern(int, int, Calendar)
     * @since 1.0
     */
    public static String formatTime(long timestamp, Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
        String pattern = LocaleProviderAdapter.getResourceBundleBased().getLocaleResources(locale).getDateTimePattern(3, 3, calendar);
        return format(newDateTime(timestamp), pattern);
    }

    /**
     * 将时间戳格式为指定模式{@value DateTimeUtils#DEFAULT_TIME_FORMAT}的字符串
     *
     * @param timestamp 时间戳
     * @return 格式化后的时间字符串
     * @see #format(long, String)
     * @since 1.0
     */
    public static String formatTime(long timestamp) {
        return format(timestamp, DEFAULT_TIME_FORMAT);
    }

    /**
     * 将当前时间格式为指定模式语言环境下的字符串
     *
     * @param locale 本地语言环境<br>
     *               可以通过<p><code>
     *               //language为语言代码，可以使用Locale.getAvailableLocales()查看<br>
     *               Locale.forLanguageTag("language");
     *               </code></p>例如zh(中文)；en(英文)等。
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see SimpleDateFormat#format(Date)
     * @see #now()
     * @since 1.0
     */
    public static String formatTimeOfNow(Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
        String pattern = LocaleProviderAdapter.getResourceBundleBased().getLocaleResources(locale).getDateTimePattern(3, 3, calendar);
        return format(now(), pattern);
    }

    /**
     * 将当前时间格式为指定模式时区的字符串
     *
     * @param timeZone 时区对象
     * @return 格式化后的日期字符串
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#setTimeZone(TimeZone)
     * @see SimpleDateFormat#format(Date)
     * @see #now()
     * @since 1.0
     */
    public static String formatTimeOfNow(TimeZone timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        formatter.setTimeZone(timeZone);
        return formatter.format(now());
    }

    /**
     * 将当前时间格式为指定模式{@value DateTimeUtils#DEFAULT_TIME_FORMAT}的字符串
     *
     * @return 格式化后的时间字符串
     * @see #format(Date, String)
     * @since 1.0
     */
    public static String formatTimeOfNow() {
        return format(now(), DEFAULT_TIME_FORMAT);
    }

    /**
     * 将一个指定格式的日期时间字符串转换为日期时间对象
     *
     * @param dateStr 日期时间字符串
     * @param pattern 日期时间字符串的格式
     * @return 日期时间对象
     * @throws RuntimeException 如果转换失败则抛出该异常
     * @see SimpleDateFormat#parse(String)
     * @since 1.0
     */
    public static Date parse(String dateStr, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将一个指定格式{@value DateTimeUtils#DEFAULT_FORMAT}的日期时间字符串转换为日期时间对象
     *
     * @param dateStr 日期时间字符串
     * @return 日期时间对象
     * @see #parse(String, String)
     * @since 1.0
     */
    public static Date parse(String dateStr) {
        return parse(dateStr, DEFAULT_FORMAT);
    }

    /**
     * 将一个时间戳转换为日期时间对象
     *
     * @param timestamp 时间戳
     * @return 日期时间对象
     * @see #newDateTime(long)
     * @since 1.0
     */
    public static Date parse(long timestamp) {
        return newDateTime(timestamp);
    }

    /**
     * 将一个sql包下的日期时间对象转换为util包下的日期时间对象
     *
     * @param sqlDate sql包下的日期时间对象
     * @return util包下的日期时间对象
     * @see #newDateTime(long)
     * @see java.sql.Date#getTime()
     * @since 1.0
     */
    public static Date parse(java.sql.Date sqlDate) {
        return newDateTime(sqlDate.getTime());
    }

    /**
     * 将sql包下的时间戳转换为util包下的日期时间对象
     *
     * @param sqlTimestamp sql包下的时间戳
     * @return utils包下的日期时间对象
     * @see #newDateTime(long)
     * @see java.sql.Timestamp#getTime()
     * @since 1.0
     */
    public static Date parse(java.sql.Timestamp sqlTimestamp) {
        return newDateTime(sqlTimestamp.getTime());
    }

    /**
     * 将指定格式的日期时间字符串转化为另一个格式的日期时间字符串
     *
     * @param dateStr       日期时间字符串
     * @param sourcePattern 源日期时间字符串格式
     * @param targetPattern 目标日期时间字符串格式
     * @return 目标格式的日期时间字符串
     * @see #parse(String, String)
     * @see #format(Date, String)
     * @since 1.0
     */
    public static String format(String dateStr, String sourcePattern, String targetPattern) {
        Date date = parse(dateStr, sourcePattern);
        return format(date, targetPattern);
    }

    /**
     * 将默认格式{@value DateTimeUtils#DEFAULT_FORMAT}日期时间字符串转化为另一个格式的日期时间字符串
     *
     * @param dateStr       日期时间字符串
     * @param targetPattern 目标日期时间字符串格式
     * @return 目标格式的日期时间字符串
     * @see #format(String, String, String)
     * @since 1.0
     */
    public static String format(String dateStr, String targetPattern) {
        return format(dateStr, DEFAULT_FORMAT, targetPattern);
    }

    /**
     * 根据输入的日期时间字符串和日期时间字符串格式集合，进行尝试匹配解析为日期时间对象
     *
     * @param dateStr  日期时间字符串
     * @param patterns 日期时间格式化字符串列表
     * @return 日期时间对象
     * @see #parse(String, String)
     * @see SimpleDateFormat#SimpleDateFormat(String)
     * @see SimpleDateFormat#parse(String)
     */
    public static Date tryToParse(String dateStr, final String... patterns) {
        if (patterns == null) {
            throw new IllegalArgumentException("patterns is null. don't allow null.");
        }
        if (patterns.length == 1) {
            return parse(dateStr, patterns[0]);
        } else {
            SimpleDateFormat formatter = null;
            for (String pattern : patterns) {
                formatter = new SimpleDateFormat(pattern);
                try {
                    return formatter.parse(dateStr);
                } catch (ParseException e) {
                    continue;
                }
            }
        }
        throw new RuntimeException("parse string[" + dateStr + "] to java.util.Date is error.");
    }

    /************************************* date format/parse end *************************************/


    /************************************* date calculate start *************************************/

    /**
     * 给指定的日期时间添加一个指定的天数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param days     添加的天数
     * @param timeMode 时间模式
     * @return 添加指定天数后的新日期时间对象
     * @see Calendar#add(int, int)
     * @see Calendar#DAY_OF_MONTH
     * @since 1.0
     */
    public static Date addDays(Date date, int days, TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (days != 0) {
            calendar.add(Calendar.DAY_OF_MONTH, days);
        }
        return calendar.getTime();
    }

    /**
     * 给指定的日期时间添加一个指定的天数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date 日期时间对象
     * @param days 添加的天数
     * @return 添加指定天数后的新日期日期时间对象
     * @see #addDays(Date, int, TimeMode)
     * @since 1.0
     */
    public static Date addDays(Date date, int days) {
        return addDays(date, days, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期时间减去一个指定的天数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param days     减去的天数
     * @param timeMode 时间模式
     * @return 减去指定天数后的新日期时间对象
     * @see #addDays(Date, int, TimeMode)
     * @since 1.0
     */
    public static Date minusDays(Date date, int days, TimeMode timeMode) {
        return addDays(date, -days, timeMode);
    }

    /**
     * 给指定的日期时间减去一个指定的天数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date 日期时间对象
     * @param days 减去的天数
     * @return 减去指定天数后的新日期时间对象
     * @see #addDays(Date, int)
     * @since 1.0
     */
    public static Date minusDays(Date date, int days) {
        return addDays(date, -days);
    }

    /**
     * 给指定的日期时间添加一个指定的月数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param months   添加的月数
     * @param timeMode 时间模式
     * @return 添加指定月数后的新日期时间对象
     * @see Calendar#add(int, int)
     * @see Calendar#MONTH
     * @since 1.0
     */
    public static Date addMonths(Date date, int months, TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (months != 0) {
            calendar.add(Calendar.MONTH, months);
        }
        return calendar.getTime();
    }

    /**
     * 给指定的日期时间添加一个指定的月数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date   日期时间对象
     * @param months 添加的月数
     * @return 添加指定月数后的新日期时间对象
     * @see #addMonths(Date, int, TimeMode)
     * @since 1.0
     */
    public static Date addMonths(Date date, int months) {
        return addMonths(date, months, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期时间减去一个指定的月数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param months   减去的月数
     * @param timeMode 时间模式
     * @return 减去指定月数后的新日期时间对象
     * @see #addMonths(Date, int, TimeMode)
     * @since 1.0
     */
    public static Date minusMonths(Date date, int months, TimeMode timeMode) {
        return addMonths(date, -months, timeMode);
    }

    /**
     * 给指定的日期时间减去一个指定的月数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date   日期时间对象
     * @param months 减去的月数
     * @return 减去指定月数后的新日期时间对象
     * @see #addMonths(Date, int)
     * @since 1.0
     */
    public static Date minusMonths(Date date, int months) {
        return addMonths(date, -months, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期时间添加一个指定的年数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param years    添加的年数
     * @param timeMode 时间模式
     * @return 添加指定年数后的新日期时间对象
     * @see Calendar#add(int, int)
     * @see Calendar#YEAR
     * @since 1.0
     */
    public static Date addYears(Date date, int years, TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (years != 0) {
            calendar.add(Calendar.YEAR, years);
        }
        return calendar.getTime();
    }

    /**
     * 给指定的日期时间添加一个指定的年数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date  日期时间对象
     * @param years 添加的年数
     * @return 添加指定年数后的新日期时间对象
     * @see #addYears(Date, int, TimeMode)
     * @since 1.0
     */
    public static Date addYears(Date date, int years) {
        return addYears(date, years, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期时间减去一个指定的年数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param years    减去的年数
     * @param timeMode 时间模式
     * @return 减去指定年数后的新日期时间对象
     * @see #addYears(Date, int, TimeMode)
     * @since 1.0
     */
    public static Date minusYears(Date date, int years, TimeMode timeMode) {
        return addYears(date, -years, timeMode);
    }

    /**
     * 给指定的日期时间减去一个指定的年数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date  日期时间对象
     * @param years 减去的年数
     * @return 减去指定年数后的新日期时间对象
     * @see #addYears(Date, int)
     * @since 1.0
     */
    public static Date minusYears(Date date, int years) {
        return addYears(date, -years, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期时间添加一个指定的小时数得出一个新的日期时间（24小时制）
     *
     * @param date     日期时间对象
     * @param hours    添加的小时数
     * @param timeMode 时间模式
     * @return 添加指定小时数后的新日期时间对象（24小时制）
     * @see Calendar#add(int, int)
     * @see Calendar#HOUR_OF_DAY
     * @since 1.0
     */
    public static Date addHours(Date date, int hours, TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (hours != 0) {
            calendar.add(Calendar.HOUR_OF_DAY, hours);
        }
        return calendar.getTime();
    }

    /**
     * 给指定的日期时间添加一个指定的小时数得出一个新的日期时间（24小时制），时间被固定为00:00:00
     *
     * @param date  日期时间对象
     * @param hours 添加的小时数
     * @return 添加指定小时数后的新日期时间对象（24小时制）
     * @see #addHours(Date, int, TimeMode)
     * @since 1.0
     */
    public static Date addHours(Date date, int hours) {
        return addHours(date, hours, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期时间减去一个指定的小时数得出一个新的日期时间（24小时制）
     *
     * @param date     日期时间对象
     * @param hours    减去的小时数
     * @param timeMode 时间模式
     * @return 减去指定小时数后的新日期时间对象（24小时制）
     * @see #addHours(Date, int, TimeMode)
     * @since 1.0
     */
    public static Date minusHours(Date date, int hours, TimeMode timeMode) {
        return addHours(date, -hours, timeMode);
    }

    /**
     * 给指定的日期时间减去一个指定的小时数得出一个新的日期时间（24小时制），时间被固定为00:00:00
     *
     * @param date  日期时间对象
     * @param hours 减去的小时数
     * @return 减去指定小时数后的新日期时间对象（24小时制）
     * @see #addHours(Date, int)
     * @since 1.0
     */
    public static Date minusHours(Date date, int hours) {
        return addHours(date, -hours, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期时间添加一个指定的分钟数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param minutes  添加的分钟数
     * @param timeMode 时间模式
     * @return 添加指定分钟数后的新日期时间对象
     * @see Calendar#add(int, int)
     * @see Calendar#MINUTE
     * @since 1.0
     */
    public static Date addMinutes(Date date, int minutes, TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (minutes != 0) {
            calendar.add(Calendar.MINUTE, minutes);
        }
        return calendar.getTime();
    }

    /**
     * 给指定的日期时间添加一个指定的分钟数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date    日期时间对象
     * @param minutes 添加的分钟数
     * @return 添加指定分钟数后的新日期时间对象
     * @see #addMinutes(Date, int, TimeMode)
     * @since 1.0
     */
    public static Date addMinutes(Date date, int minutes) {
        return addMinutes(date, minutes, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期减去一个指定的分钟数得出一个新的日期
     *
     * @param date     日期时间对象
     * @param minutes  减去的分钟数
     * @param timeMode 时间模式
     * @return 减去指定分钟数后的新日期时间对象
     * @see #addMinutes(Date, int, TimeMode)
     * @since 1.0
     */
    public static Date minusMinutes(Date date, int minutes, TimeMode timeMode) {
        return addMinutes(date, -minutes, timeMode);
    }

    /**
     * 给指定的日期时间减去一个指定的分钟数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date    日期时间对象
     * @param minutes 减去的分钟数
     * @return 减去指定分钟数后的新日期时间对象
     * @see #addMinutes(Date, int)
     * @since 1.0
     */
    public static Date minusMinutes(Date date, int minutes) {
        return addMinutes(date, -minutes, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期时间添加一个指定的秒数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param seconds  添加的秒数
     * @param timeMode 时间模式
     * @return 添加指定秒数后的新日期时间对象
     * @see Calendar#add(int, int)
     * @see Calendar#SECOND
     * @since 1.0
     */
    public static Date addSeconds(Date date, int seconds, TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (seconds != 0) {
            calendar.add(Calendar.SECOND, seconds);
        }
        return calendar.getTime();
    }

    /**
     * 给指定的日期时间添加一个指定的秒数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date    日期时间对象
     * @param seconds 添加的秒数
     * @return 添加指定秒数后的新日期时间对象
     * @see #addSeconds(Date, int, TimeMode)
     * @since 1.0
     */
    public static Date addSeconds(Date date, int seconds) {
        return addSeconds(date, seconds, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期时间减去一个指定的秒数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param seconds  减去的秒数
     * @param timeMode 时间模式
     * @return 减去指定秒数后的新日期时间对象
     * @see #addSeconds(Date, int, TimeMode)
     * @since 1.0
     */
    public static Date minusSeconds(Date date, int seconds, TimeMode timeMode) {
        return addSeconds(date, -seconds, timeMode);
    }

    /**
     * 给指定的日期时间减去一个指定的秒数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date    日期时间对象
     * @param seconds 减去的秒数
     * @return 减去指定秒数后的新日期时间对象
     * @see #addSeconds(Date, int)
     * @since 1.0
     */
    public static Date minusSeconds(Date date, int seconds) {
        return addSeconds(date, -seconds, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期时间添加月数和天数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param months   添加的月数
     * @param days     添加的天数
     * @param timeMode 时间模式
     * @return 添加指定月数和天数后的新日期时间对象
     * @see Calendar#add(int, int)
     * @see Calendar#MONTH
     * @see Calendar#DAY_OF_MONTH
     * @since 1.0
     */
    public static Date addMD(Date date, int months, int days, TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (months != 0) {
            calendar.add(Calendar.MONTH, months);
        }
        if (days != 0) {
            calendar.add(Calendar.DAY_OF_MONTH, days);
        }
        return calendar.getTime();
    }

    /**
     * 给指定的日期时间添加月数和天数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date   日期时间对象
     * @param months 添加的月数
     * @param days   添加的天数
     * @return 添加指定月数和天数后的新日期时间对象
     * @see #addMD(Date, int, int, TimeMode)
     * @since 1.0
     */
    public static Date addMD(Date date, int months, int days) {
        return addMD(date, months, days, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期时间减去月数和天数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param months   减去的月数
     * @param days     减去的天数
     * @param timeMode 时间模式
     * @return 减去指定月数和天数后的新日期时间对象
     * @see #addMD(Date, int, int, TimeMode)
     * @since 1.0
     */
    public static Date minusMD(Date date, int months, int days, TimeMode timeMode) {
        return addMD(date, -months, -days, timeMode);
    }

    /**
     * 给指定的日期时间减去月数和天数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date   日期时间对象
     * @param months 减去的月数
     * @param days   减去的天数
     * @return 减去指定月数和天数后的新日期时间对象
     * @see #addMD(Date, int, int)
     * @since 1.0
     */
    public static Date minusMD(Date date, int months, int days) {
        return addMD(date, -months, -days);
    }

    /**
     * 给指定的日期时间添加小时数和分钟数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param hours    添加的小时数
     * @param minutes  添加的分钟数
     * @param timeMode 时间模式
     * @return 添加指定小时数和分钟数后的新日期时间对象
     * @see Calendar#add(int, int)
     * @see Calendar#HOUR_OF_DAY
     * @see Calendar#MINUTE
     * @since 1.0
     */
    private static Date addHM(Date date, int hours, int minutes, TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        if (hours != 0) {
            calendar.add(Calendar.HOUR_OF_DAY, hours);
        }
        if (minutes != 0) {
            calendar.add(Calendar.MINUTE, minutes);
        }
        return calendar.getTime();
    }

    /**
     * 给指定的日期时间添加小时数和分钟数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date    日期时间对象
     * @param hours   添加的小时数
     * @param minutes 添加的分钟数
     * @return 添加指定小时数和分钟数后的新日期时间对象
     * @see #addHM(Date, int, int, TimeMode)
     * @since 1.0
     */
    public static Date addHM(Date date, int hours, int minutes) {
        return addHM(date, hours, minutes, TimeMode.NOW_OF_DAY);
    }

    /**
     * 给指定的日期时间减去小时数和分钟数得出一个新的日期时间
     *
     * @param date     日期时间对象
     * @param hours    减去的小时数
     * @param minutes  减去的分钟数
     * @param timeMode 时间模式
     * @return 减去指定小时数和分钟数后的新日期时间对象
     * @see #addHM(Date, int, int, TimeMode)
     * @since 1.0
     */
    public static Date minusHM(Date date, int hours, int minutes, TimeMode timeMode) {
        return addHM(date, -hours, -minutes, timeMode);
    }

    /**
     * 给指定的日期时间减去小时数和分钟数得出一个新的日期时间，时间被固定为00:00:00
     *
     * @param date    日期时间对象
     * @param hours   减去的小时数
     * @param minutes 减去的分钟数
     * @return 减去指定小时数和分钟数后的新日期时间对象
     * @see #addHM(Date, int, int)
     * @since 1.0
     */
    public static Date minusHM(Date date, int hours, int minutes) {
        return addHM(date, -hours, -minutes);
    }

    /**
     * 计算两个日期时间的相差数据数组，数组共5位，分别表示天、时、分、秒、毫秒
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @param timeMode  时间模式
     * @return 数组值共5位，分别表示天、时、分、秒、毫秒
     * @see Calendar#getTimeInMillis()
     * @since 1.0
     */
    public static int[] diffArray(Date beginDate, Date endDate, TimeMode timeMode) {
        int[] array = new int[5];
        Calendar beginCalendar = getCalendar(beginDate, timeMode);
        Calendar endCalendar = getCalendar(endDate, timeMode);
        long beginMillis = beginCalendar.getTimeInMillis();
        long endMillis = endCalendar.getTimeInMillis();
        long diff = endMillis - beginMillis;
        long days = diff / MILLIS_OF_DAY;
        long hours = diff % MILLIS_OF_DAY / MILLIS_OF_HOUR;
        long minutes = diff % MILLIS_OF_HOUR / MILLIS_OF_MINUTE;
        long seconds = diff % MILLIS_OF_MINUTE / MILLIS_OF_SECOND;
        long milliseconds = diff % MILLIS_OF_SECOND;
        array[0] = (int) days;
        array[1] = (int) hours;
        array[2] = (int) minutes;
        array[3] = (int) seconds;
        array[4] = (int) milliseconds;
        return array;
    }

    /**
     * 计算两个日期时间的相差数据数组，数组共5位，分别表示天、时、分、秒、毫秒
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return 数组值共5位，分别表示天、时、分、秒、毫秒
     * @see Calendar#getTimeInMillis()
     * @since 1.0
     */
    public static int[] diffArray(Date beginDate, Date endDate) {
        return diffArray(beginDate, endDate, TimeMode.NOW_OF_DAY);
    }

    /**
     * 计算当前日期时间和指定日期时间的差值数组，数组共5位，分别表示天、时、分、秒、毫秒
     *
     * @param date     日期时间
     * @param timeMode 时间模式
     * @return 差值数组，数组共5位，分别表示天、时、分、秒、毫秒
     * @see #diffArray(Date, Date, TimeMode)
     * @see #now()
     * @see #diffArray(Date, Date, TimeMode)
     * @since 1.0
     */
    public static int[] diffArray(Date date, TimeMode timeMode) {
        return diffArray(now(), date, timeMode);
    }

    /**
     * 计算两个日期时间相差的天数，如果不足1天不计入
     *
     * @param beginDate 开始日期时间
     * @param endDate   结束日期时间
     * @param timeMode  时间模式
     * @return 相差的天数，如果不足1天不计入
     * @see Calendar#getTimeInMillis()
     * @since 1.0
     */
    public static int diffDays(Date beginDate, Date endDate, TimeMode timeMode) {
        Calendar beginCalendar = getCalendar(beginDate, timeMode);
        Calendar endCalendar = getCalendar(endDate, timeMode);
        long beginMillis = beginCalendar.getTimeInMillis();
        long endMillis = endCalendar.getTimeInMillis();
        long days = Math.abs(endMillis - beginMillis) / MILLIS_OF_DAY;
        //if ((endMillis - beginMillis) % MILLIS_OF_DAY != 0) {
        //    days++;
        //}
        return (int) days;
    }

    /**
     * 计算目标日期时间和当前日期时间相差的天数，如果不足1天不计入
     *
     * @param date     目标日期时间
     * @param timeMode 时间模式
     * @return 相差的天数，如果不足1天不计入
     * @see #diffDays(Date, Date, TimeMode)
     * @since 1.0
     */
    public static int diffDays(Date date, TimeMode timeMode) {
        return diffDays(now(), date, timeMode);
    }

    /**
     * 计算两个日期时间相差的天数，忽略时分秒的影响
     *
     * @param beginDate 开始日期时间
     * @param endDate   结束日期时间
     * @return 不计时分秒的差异天数
     * @see #diffDays(Date, Date, TimeMode)
     * @see #intervalDays(Date, Date)
     * @since 1.0
     */
    public static int diffDays(Date beginDate, Date endDate) {
        return diffDays(beginDate, endDate, TimeMode.BEGIN_OF_DAY);
    }

    /**
     * 计算目标日期时间和当前日期时间相差的天数，忽略时分秒的影响
     *
     * @param date 目标日期时间
     * @return 不计时分秒的差异天数
     * @see #diffDays(Date, TimeMode)
     * @see #intervalDays(Date, Date)
     * @since 1.0
     */
    public static int diffDays(Date date) {
        return diffDays(date, TimeMode.BEGIN_OF_DAY);
    }

    /**
     * 计算两个日期时间之间间隔的天数（即晚数），忽略时分秒的影响
     *
     * @param beginDate 开始日期时间
     * @param endDate   结束日期时间
     * @return 不计时分秒的情况下，两个日期之间间隔的天数（即晚数）
     * @see #diffDays(Date, Date)
     * @since 1.0
     */
    public static int intervalDays(Date beginDate, Date endDate) {
        return diffDays(beginDate, endDate);
    }

    /**
     * 计算当前日期时间和指定日期时间之间间隔的天数（即晚数），忽略时分秒的影响
     *
     * @param date 输入的日期时间
     * @return 不计入时分秒的情况下，两个日期之间间隔的天数（即晚数）
     * @see #diffDays(Date)
     * @since 1.0
     */
    public static int intervalDays(Date date) {
        return diffDays(date);
    }

    /**
     * 计算两个日期时间之间跨越的天数（包含首尾日期），忽略时分秒的影响
     *
     * @param beginDate 开始日期时间
     * @param endDate   结束日期时间
     * @return 不计时分秒的情况下，两个日期时间之间跨越的天数（包含首尾日期）
     * @see #diffDays(Date, Date, TimeMode)
     * @since 1.0
     */
    public static int spanDays(Date beginDate, Date endDate) {
        return diffDays(beginDate, endDate, TimeMode.BEGIN_OF_DAY) + 1;
    }

    /**
     * 计算当前日期时间和指定日期时间之间跨越的天数（包含首尾日期），忽略时分秒的影响
     *
     * @param date 输入的日期时间
     * @return 不计入时分秒的情况下，两个日期时间之间跨越的天数（包含首尾日期）
     * @see #diffDays(Date, TimeMode)
     * @since 1.0
     */
    public static int spanDays(Date date) {
        return diffDays(now(), date, TimeMode.BEGIN_OF_DAY) + 1;
    }

    /**
     * 比较两个日期时间的大小，如果前者大于后者则返回1，如果前者小于后者返回-1，否则返回0
     *
     * @param date      第一个日期时间
     * @param otherDate 第二个日期时间
     * @param timeMode  时间模式
     * @return 如果第一个日期时间大于第二个日期时间则返回1；如果第一个日期时间小于第二个日期时间则返回-1；否则返回0
     * @see Calendar#getTimeInMillis()
     * @since 1.0
     */
    public static int compare(Date date, Date otherDate, TimeMode timeMode) {
        Calendar calendar = getCalendar(date, timeMode);
        Calendar otherCalendar = getCalendar(otherDate, timeMode);
        long calendarMillis = calendar.getTimeInMillis();
        long otherCalendarMillis = otherCalendar.getTimeInMillis();
        if (calendarMillis == otherCalendarMillis) {
            return 0;
        } else if (calendarMillis > otherCalendarMillis) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 比较两个日期时间的大小，如果前者大于后者则返回1，如果前者小于后者返回-1，否则返回0
     *
     * @param date      第一个日期时间
     * @param otherDate 第二个日期时间
     * @return 在不计入时间的情况下，如果第一个日期时间大于第二个日期时间则返回1；如果第一个日期时间小于第二个日期时间则返回-1；否则返回0
     * @see #compare(Date, Date, TimeMode)
     * @since 1.0
     */
    public static int compare(Date date, Date otherDate) {
        return compare(date, otherDate, TimeMode.NOW_OF_DAY);
    }

    /**
     * 比较输入的日期时间与当前日期时间的大小，如果输入日期时间大于当前日期时间则返回1，如果输入日期时间小于当前日期时间则返回-1，否则返回0
     *
     * @param date     输入日期时间
     * @param timeMode 时间模式
     * @return 如果输入日期时间大于当前日期时间则返回1，如果输入日期时间小于当前日期时间则返回-1，否则返回0
     * @see #compare(Date, Date, TimeMode)
     * @since 1.0
     */
    public static int compareWithNow(Date date, TimeMode timeMode) {
        return compare(now(), date, timeMode);
    }

    /**
     * 比较当前输入日期时间与当前日期时间的大小，如果输入日期时间大于当前日期时间则返回1，如果输入日期时间小于当前日期时间则返回-1，否则返回0
     *
     * @param date 输入日期时间
     * @return 在不计入时间的情况下，如果输入日期时间大于当前日期时间则返回1，如果输入日期时间小于当前日期时间则返回-1，否则返回0
     * @see #compareWithNow(Date, TimeMode)
     * @since 1.0
     */
    public static int compareWithNow(Date date) {
        return compareWithNow(date, TimeMode.NOW_OF_DAY);
    }

    /**
     * 判断多个日期时间与标准日期时间是否是同一天（忽略时间）
     *
     * @param date      标准日期时间
     * @param otherDate 其他日期时间
     * @return 在忽略时间的情况下，如果都是同一天则返回true，否则返回false
     * @see #compare(Date, Date)
     * @since 1.0
     */
    public static boolean sameDay(Date date, Date... otherDate) {
        if (otherDate == null || otherDate.length == 0) {
            return false;
        }
        for (Date e : otherDate) {
            if (compare(date, e, TimeMode.BEGIN_OF_DAY) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个日期时间是否在另一个日期时间之前。如果前者在后者之前则返回true，否则返回false。
     *
     * @param date      第一个日期时间
     * @param otherDate 第二个日期时间
     * @param timeMode  时间模式
     * @return 如果前者在后者之前则返回true，否则返回false
     * @see #compare(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean before(Date date, Date otherDate, TimeMode timeMode) {
        int result = compare(date, otherDate, timeMode);
        return result < 0;
    }

    /**
     * 判断一个日期时间是否在另一个日期时间之前。如果前者在后者之前则返回true，否则返回false。
     *
     * @param date      第一个日期时间
     * @param otherDate 第二个日期时间
     * @return 在不计入时间的情况下，如果前者在后者之前则返回true，否则返回false
     * @see #before(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean before(Date date, Date otherDate) {
        return before(date, otherDate, TimeMode.NOW_OF_DAY);
    }

    /**
     * 判断输入日期时间是否在当前日期时间之前。如果输入日期时间在当前日期时间之前则返回true，否则返回false。
     *
     * @param date     输入日期时间
     * @param timeMode 时间模式
     * @return 如果输入日期时间在当前日期时间之前则返回true，否则返回false
     * @see #before(Date, Date, TimeMode)
     * @see #now()
     * @since 1.0
     */
    public static boolean beforeOfNow(Date date, TimeMode timeMode) {
        return before(date, now(), timeMode);
    }

    /**
     * 判断输入日期时间是否在当前日期时间之前。如果输入日期时间在当前日期时间之前则返回true，否则返回false。
     *
     * @param date 输入日期时间
     * @return 在不计入时间的情况下，如果输入日期时间在当前日期时间之前则返回true，否则返回false
     * @see #before(Date, Date)
     * @see #now()
     * @since 1.0
     */
    public static boolean beforeOfNow(Date date) {
        return before(date, now());
    }

    /**
     * 判断一个日期时间是否在另一个日期时间之后。如果前者在后者之后则返回true，否则返回false。
     *
     * @param date      第一个日期时间
     * @param otherDate 第二个日期时间
     * @param timeMode  时间模式
     * @return 如果前者在后者之后则返回true，否则返回false
     * @see #compare(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean after(Date date, Date otherDate, TimeMode timeMode) {
        int result = compare(date, otherDate, timeMode);
        return result > 0;
    }

    /**
     * 判断一个日期时间是否在另一个日期时间之后。如果前者在后者之后则返回true，否则返回false。
     *
     * @param date      第一个日期时间
     * @param otherDate 第二个日期时间
     * @return 在不计入时间的情况下，如果前者在后者之后则返回true，否则返回false
     * @see #after(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean after(Date date, Date otherDate) {
        return after(date, otherDate, TimeMode.NOW_OF_DAY);
    }

    /**
     * 判断输入日期时间是否在当前日期时间之后。如果输入日期时间在当前日期时间之后则返回true，否则返回false。
     *
     * @param date     输入日期时间
     * @param timeMode 时间模式
     * @return 如果输入日期时间在当前日期时间之后则返回true，否则返回false
     * @see #after(Date, Date, TimeMode)
     * @see #now()
     * @since 1.0
     */
    public static boolean afterOfNow(Date date, TimeMode timeMode) {
        return after(date, now(), timeMode);
    }

    /**
     * 判断输入日期时间是否在当前日期时间之后。如果输入日期时间在当前日期时间之后则返回true，否则返回false。
     *
     * @param date 输入日期
     * @return 在不计入时间的情况下，如果输入日期时间在当前日期时间之后则返回true，否则返回false
     * @see #after(Date, Date)
     * @see #now()
     * @since 1.0
     */
    public static boolean afterOfNow(Date date) {
        return after(date, now());
    }

    /**
     * 判断两个日期时间是否相等。如果相等则返回true，否则返回false。
     *
     * @param date      第一个日期时间
     * @param otherDate 第二个日期时间
     * @param timeMode  时间模式
     * @return 如果相等则返回true，否则返回false
     * @see #compare(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean equals(Date date, Date otherDate, TimeMode timeMode) {
        return compare(date, otherDate, timeMode) == 0;
    }

    /**
     * 判断两个日期时间是否相等。如果相等则返回true，否则返回false。
     *
     * @param date      第一个日期时间
     * @param otherDate 第二个日期时间
     * @return 在不计入时间的情况下，如果相等则返回true，否则返回false
     * @see #compare(Date, Date)
     * @since 1.0
     */
    public static boolean equals(Date date, Date otherDate) {
        return compare(date, otherDate) == 0;
    }

    /**
     * 判断输入的日期时间和当前日期时间是否相等。如果相等则返回true，否则返回false。
     *
     * @param date     输入的日期时间
     * @param timeMode 时间模式
     * @return 如果相等则返回true，否则返回false
     * @see #now()
     * @see #compare(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean equalsOfNow(Date date, TimeMode timeMode) {
        return compare(date, now(), timeMode) == 0;
    }

    /**
     * 判断输入的日期时间和当前日期时间是否相等。如果相等则返回true，否则返回false。
     *
     * @param date 输入的日期时间
     * @return 在不计入时间的情况下，如果相等则返回true，否则返回false
     * @see #now()
     * @see #compare(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean equalsOfNow(Date date) {
        return compare(date, now()) == 0;
    }

    /**
     * 判断一个日期时间是否在另一个日期时间的后一天之前。如果是则返回true，否则返回false。
     *
     * @param date      第一个日期时间
     * @param otherDate 第二个日期时间
     * @param timeMode  时间模式
     * @return 如果前者在后者后一天之前则返回true，否则返回false
     * @see #compare(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean beforeOrEquals(Date date, Date otherDate, TimeMode timeMode) {
        return compare(date, otherDate, timeMode) <= 0;
    }

    /**
     * 判断一个日期时间是否在另一个日期时间的后一天之前。如果是则返回true，否则返回false。     *
     *
     * @param date      第一个日期时间
     * @param otherDate 第二个日期时间
     * @return 在不计入时间的情况下，如果前者在后者后一天之前则返回true，否则返回false
     * @see #compare(Date, Date)
     * @since 1.0
     */
    public static boolean beforeOrEquals(Date date, Date otherDate) {
        return compare(date, otherDate) <= 0 ? true : false;
    }

    /**
     * 判断一个日期时间是否在另一个日期时间的前一天之后。如果是则返回true，否则返回false。
     *
     * @param date      第一个日期时间
     * @param otherDate 第二个日期时间
     * @param timeMode  时间模式
     * @return 如果前者在后者前一天之后则返回true，否则返回false
     * @see #compare(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean afterOrEquals(Date date, Date otherDate, TimeMode timeMode) {
        return compare(date, otherDate, timeMode) >= 0;
    }

    /**
     * 判断一个日期时间是否在另一个日期时间的前一天之后。如果相等则返回true，否则返回false。
     *
     * @param date      第一个日期时间
     * @param otherDate 第二个日期时间
     * @return 在不计入时间的情况下，如果前者在后者前一天之后则返回true，否则返回false
     * @see #compare(Date, Date)
     * @since 1.0
     */
    public static boolean afterOrEquals(Date date, Date otherDate) {
        return compare(date, otherDate) >= 0;
    }

    /**
     * 判断输入的日期时间是否在当前日期时间的后一天之前。如果是则返回true，否则返回false。
     *
     * @param date     输入的日期时间
     * @param timeMode 时间模式
     * @return 如果前者在后者后一天之前则返回true，否则返回false
     * @see #beforeOrEquals(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean beforeOrEqualsOfNow(Date date, TimeMode timeMode) {
        return beforeOrEquals(date, now(), timeMode);
    }

    /**
     * 判断输入的日期时间是否在当前日期时间的后一天之前。如果是则返回true，否则返回false。
     *
     * @param date 输入的日期时间
     * @return 在不计入时间的情况下，如果前者在后者后一天之前则返回true，否则返回false
     * @see #beforeOrEquals(Date, Date)
     * @since 1.0
     */
    public static boolean beforeOrEqualsOfNow(Date date) {
        return beforeOrEquals(date, now());
    }

    /**
     * 判断输入的日期时间是否在当前日期时间的前一天之后。如果是则返回true，否则返回false。
     *
     * @param date     输入的日期时间
     * @param timeMode 时间模式
     * @return 如果前者在后者前一天之后则返回true，否则返回false
     * @see #afterOrEquals(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean afterOrEqualsOfNow(Date date, TimeMode timeMode) {
        return afterOrEquals(date, now(), timeMode);
    }

    /**
     * 判断输入的日期时间是否在当前日期时间的前一天之后。如果相等则返回true，否则返回false。
     *
     * @param date 输入的日期时间
     * @return 在不计入时间的情况下，如果前者在后者前一天之后则返回true，否则返回false
     * @see #afterOrEquals(Date, Date)
     * @since 1.0
     */
    public static boolean afterOrEqualsOfNow(Date date) {
        return afterOrEquals(date, now());
    }

    /************************************* date calculate end *************************************/


    /************************************* date other start *************************************/

    /**
     * 判断给定的日期对应的年份是否为闰年
     *
     * @param date 日期时间对象
     * @return 如果是闰年则返回true，否则返回false
     * @see #getYear(Date)
     * @see #isLeapYear(int)
     * @since 1.0
     */
    public static boolean isLeapYear(Date date) {
        return isLeapYear(getYear(date));
    }

    /**
     * 判断给定的时间戳对应的年份是否为闰年
     *
     * @param timestamp 时间戳
     * @return 如果是闰年则返回true，否则返回false
     * @see #getYear(long)
     * @see #isLeapYear(int)
     * @since 1.0
     */
    public static boolean isLeapYear(long timestamp) {
        return isLeapYear(getYear(timestamp));
    }

    /**
     * 获取今天的日期时间
     *
     * @return 今天的日期时间对象
     * @see #now()
     * @since 1.0
     */
    public static Date today() {
        return now();
    }

    /**
     * 判断输入的日期时间是否与今天日期相同（忽略时间）
     *
     * @param date 输入的日期时间
     * @return 在忽略时间的情况下，如果相同则返回true，否则返回false
     * @see #compareWithNow(Date)
     * @since 1.0
     */
    public static boolean isToday(Date date) {
        return compareWithNow(date, TimeMode.BEGIN_OF_DAY) == 0;
    }

    /**
     * 获取今天的开始日期，时间被固定为00:00:00
     *
     * @return 今天的开始日期
     * @see #beginOfDate(Date)
     * @since 1.0
     */
    public static Date beginOfToday() {
        return beginOfDate(now());
    }

    /**
     * 获取今天的结束日期，时间被固定为23:59:59
     *
     * @return 今天的结束日期
     * @see #endOfDate(Date)
     * @since 1.0
     */
    public static Date endOfToday() {
        return endOfDate(now());
    }

    /**
     * 获取昨天的日期时间
     *
     * @return 昨天的日期时间对象
     * @see #now()
     * @see #minusDays(Date, int)
     * @since 1.0
     */
    public static Date yesterday() {
        return minusDays(now(), 1);
    }

    /**
     * 判断输入的日期时间是否与昨天日期相同（忽略时间）
     *
     * @param date 输入的日期时间
     * @return 在忽略时间的情况下，如果相同则返回true，否则返回false
     * @see #yesterday()
     * @see #compare(Date, Date)
     * @since 1.0
     */
    public static boolean isYesterday(Date date) {
        return compare(date, yesterday(), TimeMode.BEGIN_OF_DAY) == 0;
    }

    /**
     * 昨天的开始日期时间，时间被固定为00:00:00
     *
     * @return 昨天的开始日期时间
     * @see #endOfDate(Date)
     * @see #yesterday()
     * @since 1.0
     */
    public static Date beginOfYesterday() {
        return beginOfDate(yesterday());
    }

    /**
     * 昨天的结束日期时间，时间被固定为23:59:59
     *
     * @return 昨天的结束日期时间，时间被固定为23:59:59
     * @see #endOfDate(Date)
     * @see #yesterday()
     * @since 1.0
     */
    public static Date endOfYesterday() {
        return endOfDate(yesterday());
    }

    /**
     * 获取明天的日期时间
     *
     * @return 明天的日期时间对象
     * @see #now()
     * @see #addDays(Date, int)
     * @since 1.0
     */
    public static Date tomorrow() {
        return addDays(now(), 1);
    }

    /**
     * 判断输入的日期时间是否与明天日期相同（忽略时间）
     *
     * @param date 输入的日期时间
     * @return 在忽略时间的情况下，如果相同则返回true，否则返回false
     * @see #compare(Date, Date)
     * @see #tomorrow()
     * @since 1.0
     */
    public static boolean isTomorrow(Date date) {
        return compare(date, tomorrow(), TimeMode.BEGIN_OF_DAY) == 0;
    }

    /**
     * 明天的开始日期时间，时间被固定为00:00:00
     *
     * @return 明天的开始日期时间，时间被固定为00:00:00
     * @see #beginOfDate(Date)
     * @see #tomorrow()
     * @since 1.0
     */
    public static Date beginOfTomorrow() {
        return beginOfDate(tomorrow());
    }

    /**
     * 明天的结束日期时间，时间被固定为23:59:59
     *
     * @return 明天的结束日期时间，时间被固定为23:59:59
     * @see #endOfDate(Date)
     * @see #tomorrow()
     * @since 1.0
     */
    public static Date endOfTomorrow() {
        return endOfDate(tomorrow());
    }

    /**
     * 获取当前月份的开始日期，时间被固定为00:00:00
     *
     * @return 当前月份的开始日期
     * @see #now()
     * @see #beginOfMonth(Date)
     * @since 1.0
     */
    public static Date beginOfMonth() {
        return beginOfMonth(now());
    }

    /**
     * 获取当前月份的结束日期，时间被固定为23:59:59
     *
     * @return 当前月份的结束日期
     * @see #now()
     * @see #endOfMonth(Date)
     * @since 1.0
     */
    public static Date endOfMonth() {
        return endOfMonth(now());
    }

    /**
     * 判断输入日期时间是否在明天日期时间之前。如果输入日期时间在明天日期时间之前则返回true，否则返回false。
     *
     * @param date     输入日期时间
     * @param timeMode 时间模式
     * @return 如果输入日期时间在明天日期时间之前则返回true，否则返回false
     * @see #before(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean beforeOfTomorrow(Date date, TimeMode timeMode) {
        return before(date, tomorrow(), timeMode);
    }

    /**
     * 判断输入日期时间是否在明天日期时间之前（忽略时间）。如果输入日期时间在明天日期时间之前则返回true，否则返回false。
     *
     * @param date 输入日期时间
     * @return 在不计入时间的情况下，如果输入日期时间在明天日期时间之前则返回true，否则返回false
     * @see #before(Date, Date)
     * @since 1.0
     */
    public static boolean beforeOfTomorrow(Date date) {
        return before(date, tomorrow());
    }

    /**
     * 判断输入日期时间是否在明天日期时间之后。如果输入日期时间在明天日期时间之后则返回true，否则返回false。
     *
     * @param date     输入日期时间
     * @param timeMode 时间模式
     * @return 如果输入日期时间在明天日期时间之后则返回true，否则返回false
     * @see #after(Date, Date, TimeMode)
     * @since 1.0
     */
    public static boolean afterOfYesterday(Date date, TimeMode timeMode) {
        return after(date, yesterday(), timeMode);
    }

    /**
     * 判断输入日期时间是否在明天日期时间之后（忽略时间）。如果输入日期时间在明天日期时间之后则返回true，否则返回false。
     *
     * @param date 输入日期时间
     * @return 在不计入时间的情况下，如果输入日期时间在明天日期时间之后则返回true，否则返回false
     * @see #after(Date, Date)
     * @since 1.0
     */
    public static boolean afterOfYesterday(Date date) {
        return after(date, yesterday());
    }

    /**
     * 获取指定日期范围内指定周几标记【0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）】的日期列表，时间被固定为00:00:00
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @param weekFlag  周几标记，0（周日）/1（周一）/2（周二）/3（周三）/4（周四）/5（周五）/6（周六）
     * @return 日期列表
     * @see #getDateList(Date, Date)
     * @see #getWeek(Date)
     * @since 1.0
     */
    public static List<Date> getDateList(Date beginDate, Date endDate, Integer... weekFlag) {
        List<Date> dateList = getDateList(beginDate, endDate);
        if (weekFlag == null || weekFlag.length == 0) {
            return dateList;
        }
        List<Integer> weekFlagList = Arrays.asList(weekFlag);
        Iterator<Date> iterator = dateList.iterator();
        while (iterator.hasNext()) {
            int week = getWeek(iterator.next());
            if (!weekFlagList.contains(week)) {
                iterator.remove();
            }
        }
        return dateList;
    }

    /**
     * 获取指定日期范围内的日期列表，时间被固定为00:00:00
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 日期列表
     * @see #beginOfDate(Date)
     * @see #after(Date, Date)
     * @since 1.0
     */
    public static List<Date> getDateList(Date beginDate, Date endDate) {
        List<Date> dateList = new ArrayList<Date>();
        Date nextDate = beginOfDate(beginDate);
        do {
            dateList.add(nextDate);
            nextDate = beginOfDate(addDays(nextDate, 1));
        } while (!after(nextDate, endDate));
        return dateList;
    }

    /**
     * 将util包下的日期时间对象转换为sql包下的日期时间对象
     *
     * @param date util包下的日期时间对象
     * @return sql包下的日期时间对象
     * @see java.sql.Date#Date(long)
     * @since 1.0
     */
    public static java.sql.Date parseSQLDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 将时间戳转换为sql包下的日期时间对象
     *
     * @param timestamp 时间戳
     * @return sql包下的日期时间对象
     * @see java.sql.Date#Date(long)
     * @since 1.0
     */
    public static java.sql.Date parseSQLDate(long timestamp) {
        return new java.sql.Date(timestamp);
    }

    /**
     * 将util包下的日期时间对象转换为sql包下的时间戳对象
     *
     * @param date util包下的日期时间对象
     * @return sql包下的时间戳对象
     * @see java.sql.Timestamp#Timestamp(long)
     * @since 1.0
     */
    public static java.sql.Timestamp parseSQLTimestamp(Date date) {
        return new java.sql.Timestamp(date.getTime());
    }

    /**
     * 将时间戳转换为sql包下的时间戳对象
     *
     * @param timestamp 时间戳
     * @return sql包下的时间戳对象
     * @see java.sql.Timestamp#Timestamp(long)
     * @since 1.0
     */
    public static java.sql.Timestamp parseSQLTimestamp(long timestamp) {
        return new java.sql.Timestamp(timestamp);
    }

    /**
     * 获取当前sql日期时间对象
     *
     * @return 当前sql包下的日期时间对象
     * @see #parseSQLDate(long)
     * @since 1.0
     */
    public static java.sql.Date newSQLDateTime() {
        return parseSQLDate(System.currentTimeMillis());
    }

    /**
     * 获取当前sql时间戳对象
     *
     * @return 当前sql包下的时间戳对象
     * @see #parseSQLTimestamp(long)
     * @since 1.0
     */
    public static java.sql.Timestamp newSQLTimestamp() {
        return parseSQLTimestamp(System.currentTimeMillis());
    }

    /************************************* date other end *************************************/

}
