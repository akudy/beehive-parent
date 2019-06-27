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

/**
 * DateTime工具类定义。用于处理{@link java.util.Calendar java.util.Calendar}、{@link java.util.Date java.util.Date}、{@link java.sql.Date java.sql.Date}、{@link java.sql.Timestamp java.sql.Timestamp}、{@link java.sql.Time java.sql.Time}等类的工具。
 * <br>
 * 内部包含格式化、转换等多种处理方法。格式字符（单个格式字符描述的都是实际值，如果需要进行位对其的格式化则可以使用多个占位符，不足位会自动补0）参考：
 * <table border="1" cellspacing="0" cellpadding="0">
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
 * <td>月份标识符，取值范围：1-12。MMM被默认格式化为本地语言对应的月份文本</td>
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
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">E</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">u</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">a</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">h</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">H</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">k</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">K</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">m</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">s</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">S</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">z</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">Z</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
 * </tr>
 * <tr>
 * <td align="center">X</td>
 * <td>年代标识符，次数不限</td>
 * <td>公元/公元前</td>
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
 * <table border="1" cellspacing="0" cellpadding="0">
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
public class DateTimeUtils {

    /**
     * 私有化构造器，禁止外部实例化
     */
    private DateTimeUtils() {
    }

    /**
     * DateTime默认格式化字符串
     */
    public static String DEFAULT_FORMAT = "yyyy/MM/dd HH:mm:ss";

    /**
     * Date默认格式字符串
     */
    public static String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";

    /**
     * Time默认格式字符串
     */
    public static String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * 设置默认的格式化字符串
     *
     * @param defaultFormat 格式字符串
     */
    public static void setDefaultFormat(String defaultFormat) {
        DateTimeUtils.DEFAULT_FORMAT = defaultFormat;
    }

    public static void setDefaultDateFormat(String defaultDateFormat) {
        DateTimeUtils.DEFAULT_DATE_FORMAT = defaultDateFormat;
    }

    public static void setDefaultTimeFormat(String defaultTimeFormat) {
        DateTimeUtils.DEFAULT_TIME_FORMAT = defaultTimeFormat;
    }


}
