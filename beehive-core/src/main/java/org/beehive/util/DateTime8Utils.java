/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.Date8Utils
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-26 23:32:27
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.util;

/**
 * 日期时间 Java 8 支持工具类定义（Java 8 Support Utils），提供处理Java中日期、时间等类型的工具类。<br/>
 * 功能等同于{@link DateTimeUtils DateTimeUtils}，只是这是使用了Java 8新增的日期类型。
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
 * <li>Class Name: <code>Date8Utils</code></li>
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
 * @since Java 8
 * @see DateTimeUtils
 */
public class DateTime8Utils {



}
