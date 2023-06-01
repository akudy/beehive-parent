/*
 * Copyright (c) 2019-2023 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.datetime.DatePeriod
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2023-05-19
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 日期时间周期对象定义，通过一个开始日期时间和结束日期时间来描述一个日期时间周期
 * <br>
 * 在日期时间周期中，为了保证其可被比较，所以开始日期时间必须小于等于截止日期时间
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.datetime</code></li>
 *   <li>Class Name: <code>DatePeriod</code></li>
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
 *         <td align="center"><em>2023-05-19</em></td>
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
public class DateTimePeriod implements Comparable<DateTimePeriod> {

    /**
     * 每天的毫秒数
     */
    private static final long MILLIS_OF_DAY = 1000 * 60 * 60 * 24;

    /**
     * 是否忽略毫秒误差计算，默认忽略，否则不忽略
     */
    private static boolean IGNORE_MILLISECOND = true;

    /**
     * Date默认格式化器
     */
    private static SimpleDateFormat DEFAULT_FORMATTER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * 设置是否忽略毫秒计算，如果忽略则毫秒值为0
     *
     * @param ignoreMillisecond true-忽略毫秒值，使用0表示；false-不忽略，使用原毫秒值表示
     */
    public void setIgnoreMillisecond(boolean ignoreMillisecond) {
        DateTimePeriod.IGNORE_MILLISECOND = ignoreMillisecond;
    }

    /**
     * 设置默认日期格式化器
     *
     * @param dateFormat 日期格式字符串
     */
    public void setDefaultFormatter(String dateFormat) {
        DateTimePeriod.DEFAULT_FORMATTER = new SimpleDateFormat(dateFormat);
    }

    /**
     * 周期的开始日期时间点
     */
    private Date begin;

    /**
     * 周期的截止日期时间点
     */
    private Date end;

    /**
     * 构建一个日期周期对象，并通过指定是否保留时间策略来确定周期的开始和截止时间点
     *
     * @param beginDate     开始日期时间点
     * @param endDate       截止日期时间点
     * @param keepTimeValue 是否保留时间值；如果true，则时间点不会被固定为"00:00:00"和"23:59:59"。而是使用输入的时间点
     */
    public DateTimePeriod(Date beginDate, Date endDate, boolean keepTimeValue) {
        if (beginDate.compareTo(endDate) > 0) {
            throw new IllegalArgumentException("period begin must be less then or equals period end.");
        }
        if (keepTimeValue) {
            this.begin = beginDate;
            this.end = endDate;
        } else {
            this.begin = getBeginOfDate(beginDate);
            this.end = getEndOfDate(endDate);
        }
    }

    /**
     * 构建一个日期周期对象；开始日期的时间点会固定在"00:00:00"，截止日期的时间点会固定在"23:59:59"
     *
     * @param beginDate 开始日期时间点
     * @param endDate   截止日期时间点
     */
    public DateTimePeriod(Date beginDate, Date endDate) {
        this(beginDate, endDate, false);
    }

    /**
     * 获取某个日期在某天的开始日期时间点
     *
     * @param date 指定的日期
     * @return 指定的日期在某天的开始日期时间点
     */
    protected static Date getBeginOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某个日期在某天的截止日期时间点
     *
     * @param date 指定的日期
     * @return 指定的日期在某天的截止日期时间点
     */
    protected static Date getEndOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, IGNORE_MILLISECOND ? 0 : 999);
        return calendar.getTime();
    }

    /**
     * 获取周期的开始日期时间点
     *
     * @return 周期的开始日期时间点
     */
    public Date getBegin() {
        return this.begin;
    }

    /**
     * 获取周期的截止日期时间点
     *
     * @return 周期的截止日期时间点
     */
    public Date getEnd() {
        return this.end;
    }

    /**
     * 获取周期之间的间隔天数（即跨越0点的天数）<br/>
     * 例如: 2023-05-12 ~ 2023-05-13，中间间隔天数为 1 天
     *
     * @return 截止日期和开始时间之间的间隔天数（即跨域0点的天数）
     * @see #spanDays()
     */
    public int intervalDays() {
        Date beginDate = getBeginOfDate(this.begin);
        Date endDate = getBeginOfDate(this.end);
        long beginMillis = beginDate.getTime();
        long endMillis = endDate.getTime();
        long days = Math.abs(endMillis - beginMillis) / MILLIS_OF_DAY;
        return (int) days;
    }

    /**
     * 获取两个日期之间的横跨天数（含首尾日期）<br/>
     * 例如: 2023-05-12 ~ 2023-05-13，中间横跨的天数为 2 天
     *
     * @return 截止日期和开始时间之间的横跨天数（含首尾日期）
     * @see #intervalDays()
     */
    public int spanDays() {
        return this.intervalDays() + 1;
    }

    /**
     * 判断当前周期是否在输入的时间点之前，如果输入的时间点大于当前周期的开始和结束日期时间，则表示在其前，则返回true；否则返回false<br/>
     * 图例: <br/>
     * <pre>
     *      |_________________|         .
     * (this.begin)       (this.end)  (date)
     * </pre>
     *
     * @param date 输入的日期时间点
     * @return 如果输入的时间点大于当前周期的开始和结束日期时间，则表示在其前，则返回true；否则返回false
     */
    public boolean before(Date date) {
        return this.begin.compareTo(date) < 0 && this.end.compareTo(date) < 0;
    }

    /**
     * 判断当前周期是否在输入的时间点之后，如果输入的时间点小于当前周期的开始和结束日期时间，则表示在其后，则返回true；否则返回false<br/>
     * 图例: <br/>
     * <pre>
     *   .            |_________________|
     * (date)     (this.begin)      (this.end)
     * </pre>
     *
     * @param date 输入的日期时间点
     * @return 如果输入的时间点小于当前周期的开始和结束日期时间，则表示在其后，则返回true；否则返回false
     */
    public boolean after(Date date) {
        return this.begin.compareTo(date) > 0 && this.end.compareTo(date) > 0;
    }

    /**
     * 判断当前周期是否包含输入的时间点，如果输入的时间点大于等于当前周期的开始日期时间，小于等于当前周期的截止日期，则表示包含，则返回true；否则返回false<br/>
     * 图例: <br/>
     * <pre>
     *      |_____________________.___________________|
     * (this.begin/date)     (date)           (this.end/date)
     * </pre>
     *
     * @param date 输入的日期时间点
     * @return 如果输入的时间点大于等于当前周期的开始日期时间，小于等于当前周期的截止日期，则表示包含，则返回true；否则返回false
     */
    public boolean contain(Date date) {
        return this.begin.compareTo(date) <= 0 && this.end.compareTo(date) >= 0;
    }

    /**
     * 判断当前周期是否在输入周期的左边；即当前周期的开始和结束日期时间小于输入周期的开始时间和结束时间<br/>
     * 图例: <br/>
     * <pre>
     *        |________________________|
     *  (this.begin)              (this.end)
     *  ........................................................................................
     *  <font color="red">
     *                                                    |________________________|
     *                                              (period.begin)               (period.end)
     *  </font>
     * </pre>
     *
     * @param period 输入的日期时间周期
     * @return 如果当前周期的开始和结束日期时间小于输入周期的开始时间和结束时间，则返回true；否则返回false
     */
    public boolean left(DateTimePeriod period) {
        return (this.begin.compareTo(period.begin) < 0 && this.end.compareTo(period.begin) < 0)
                && (this.begin.compareTo(period.end) < 0 && this.end.compareTo(period.end) < 0);
    }

    /**
     * 判断当前周期是否在输入周期的右边；即当前周期的开始和结束日期时间大于输入周期的开始时间和结束时间<br/>
     * 图例: <br/>
     * <pre>
     *                                              |________________________|
     *                                        (this.begin)              (this.end)
     *  ........................................................................................
     * <font color="red">
     *        |________________________|
     *   (period.begin)            (period.end)
     * </font>
     * </pre>
     *
     * @param period 输入的日期时间周期
     * @return 如果当前周期的开始和结束日期时间小于输入周期的开始时间和结束时间，则返回true；否则返回false
     */
    public boolean right(DateTimePeriod period) {
        return (this.begin.compareTo(period.begin) > 0 && this.end.compareTo(period.begin) > 0)
                && (this.begin.compareTo(period.end) > 0 && this.end.compareTo(period.end) > 0);
    }

    /**
     * 判断当前周期是否在输入周期的中间；即当前周期的开始日期时间小于输入周期的开始日期时间，同时当前周期的结束日期时间大于输入周期的结束日期时间<br/>
     * 图例: <br/>
     * <pre>
     *               |_____________________________________________________________________|
     *          (this.begin)                                                          (this.end)
     *  ......................................................................................................................
     *  <font color="red">
     *                     |____________________________________________|
     *                (period.begin)                                 (period.end)
     *  </font>
     * </pre>
     *
     * @param period 输入的日期时间周期
     * @return 当前周期的开始日期时间小于输入周期的开始日期时间，同时当前周期的结束日期时间大于输入周期的结束日期时间，则返回true；否则返回false
     */
    public boolean middle(DateTimePeriod period) {
        return (this.begin.compareTo(period.begin) < 0 && this.begin.compareTo(period.end) < 0)
                && (this.end.compareTo(period.begin) > 0 && this.end.compareTo(period.end) > 0);
    }

    /**
     * 判断当前周期是否包被输入的周期包围，即输入周期的开始日期时间小于当前周期的开始日期时间，同时输入周期的结束日期时间大于当前周期的结束日期时间<br/>
     * 图例: <br/>
     * <pre>
     *                   |____________________________________________________|
     *              (this.begin)                                          (this.end)
     *  ......................................................................................................................
     *  <font color="red">
     *        |______________________________________________________________________________|
     *    (period.begin)                                                                  (period.end)
     *  </font>
     * @param period 输入的日期时间周期
     * @return 输入周期的开始日期时间小于当前周期的开始日期时间，同时输入周期的结束日期时间大于当前周期的结束日期时间，则返回true；否则返回false
     */
    public boolean enclose(DateTimePeriod period) {
        return (this.begin.compareTo(period.begin) > 0 && this.end.compareTo(period.begin) > 0)
                && (this.begin.compareTo(period.end) < 0 && this.end.compareTo(period.end) < 0);
    }

    /**
     * 判断当前周期是否包含输入周期的片段，即两个周期是否产生时间上的交叉<br/>
     * 图例: <br/>
     * <pre>
     *                   |_____________________________________________________________________|
     *              (this.begin)                                                           (this.end)
     *  ......................................................................................................................
     *  <font color="red">
     *        |_________________________|
     * (period.begin)             (period.end)
     * .......................................................................................................................
     *                                                                             |_________________________|
     *                                                                     (period.begin)              (period.end)
     * .......................................................................................................................
     *                   |_____________________________________________________________________|
     *             (period.begin)                                                       (period.end)
     *  </font>
     * </pre>
     *
     * @param period 输入的日期时间周期
     * @return 当前周期是否包含输入周期的片段时，返回true，否则返回false
     */
    public boolean cross(DateTimePeriod period) {
        return (this.begin.compareTo(period.begin) == 0 && this.end.compareTo(period.end) == 0)
                || (this.begin.compareTo(period.begin) > 0 && this.begin.compareTo(period.end) < 0
                && this.end.compareTo(period.begin) > 0 && this.end.compareTo(period.end) > 0)
                || (this.end.compareTo(period.begin) > 0 && this.end.compareTo(period.end) < 0
                && this.begin.compareTo(period.begin) < 0 && this.begin.compareTo(period.end) < 0);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        DateTimePeriod that = (DateTimePeriod) other;
        return Objects.equals(this.begin, that.begin) && Objects.equals(this.end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.begin, this.end);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.begin != null) {
            sb.append(DEFAULT_FORMATTER.format(begin));
        } else {
            sb.append("Unknown");
        }
        sb.append(" ~ ");
        if (this.end != null) {
            sb.append(DEFAULT_FORMATTER.format(end));
        } else {
            sb.append("Unknown");
        }
        return sb.toString();
    }

    @Override
    public int compareTo(DateTimePeriod other) {
        int beginDiff = other.begin.compareTo(this.begin);
        if (beginDiff < 0) {
            return 1;
        } else if (beginDiff > 0) {
            return -1;
        } else {
            int endDiff = other.end.compareTo(this.end);
            if (endDiff < 0) {
                return -1;
            } else if (endDiff > 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
