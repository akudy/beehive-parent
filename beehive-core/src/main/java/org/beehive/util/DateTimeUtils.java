/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.DateUtils
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-26 23:28:59
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类定义，提供处理{@link java.util.Calendar java.util.Calendar}、{@link java.util.Date java.util.Date}、{@link java.sql.Date java.sql.Date}、{@link java.sql.Timestamp java.sql.Timestamp}、{@link java.sql.Time java.sql.Time}等类的工具。
 * <br/>
 * 该工具类适用Java 8 版本以下的环境中使用；Java 8 请使用 {@link DateTime8Utils DateTime8Utils(Date For Java 8 Utils)}工具类，该类中引入了Java 8 的java.time包进行计算。
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
 * <li>Class Name: <code>DateUtils</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1" cellspacing="0" cellpadding="0" summary=""Upgrade&shy;Modify History>
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
 * <td align="center"><em>2020-9-26</em></td>
 * <td align="center"><em>akudy</em></td>
 * <td align="center"><em>Define</em></td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @see DateTime8Utils
 * @since Java 8
 */
public class DateTimeUtils {

    /**
     * DateTime默认格式化字符串
     */
    private static String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";

    /**
     * Date默认格式字符串
     */
    private static String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";

    /**
     * Time默认格式字符串
     **/
    private static String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * 是否忽略毫秒误差计算，默认忽略，否则不忽略
     */
    private static boolean IGNORE_MILLISECOND = true;

    /**
     * 设置默认日期时间格式字符串
     *
     * @param dateTimeFormat 日期时间格式字符串
     */
    public void setDefaultDateTimeFormat(String dateTimeFormat) {
        DateTimeUtils.DEFAULT_DATE_TIME_FORMAT = dateTimeFormat;
    }

    /**
     * 设置默认日期格式字符串
     *
     * @param dateFormat 日期格式字符串
     */
    public void setDefaultDateFormat(String dateFormat) {
        DateTimeUtils.DEFAULT_DATE_FORMAT = dateFormat;
    }

    /**
     * 设置默认时间格式字符串
     *
     * @param timeFormat 时间格式字符串
     */
    public void setDefaultTimeFormat(String timeFormat) {
        DateTimeUtils.DEFAULT_TIME_FORMAT = timeFormat;
    }

    /**
     * 设置是否忽略毫秒计算，如果忽略则毫秒值为0
     *
     * @param ignoreMillisecond true-忽略毫秒值，使用0表示；false-不忽略，使用原毫秒值表示
     */
    public void setIgnoreMillisecond(boolean ignoreMillisecond) {
        DateTimeUtils.IGNORE_MILLISECOND = ignoreMillisecond;
    }

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
            calendar.set(Calendar.MILLISECOND, IGNORE_MILLISECOND ? 0 : 999);
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

}
