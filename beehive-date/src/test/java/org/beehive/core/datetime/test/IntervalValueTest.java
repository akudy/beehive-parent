/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-date
 * File Name: org.beehive.core.datetime.test.IntervalValueTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-04-22
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.datetime.test;

import org.beehive.core.datetime.IntervalValue;
import org.junit.Test;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.date.test</code></li>
 * <li>Class Name: <code>IntervalValueTest</code></li>
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
public class IntervalValueTest {

    @Test
    public void testPositive() {
        IntervalValue value = new IntervalValue(1231231260L);
        System.out.println(value.days() + "-" + value.hours() + "-" + value.minutes() + "-" + value.seconds() + "-" + value.milliseconds());
    }

    public void testNegative() {
        IntervalValue value = new IntervalValue(-360L);
        System.out.println(value.days() + "-" + value.hours() + "-" + value.minutes() + "-" + value.seconds() + "-" + value.milliseconds());
    }

}
