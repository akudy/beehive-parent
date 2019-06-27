/*
 * Copyright (c) 2019-2019 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-util
 * File Name: org.beehive.util.DateTimeUtilsTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2019-06-26
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p/>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.util</code></li>
 * <li>Class Name: <code>DateTimeUtilsTest</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1">
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
 * <td align="center"><em>2019/6/26</em></td>
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
 */
public class DateTimeUtilsTest {

    @Test
    public void formatTest(){
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.set(2019,5,27);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("D");
        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("DD");
        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("DDD");
        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("DDDD");
        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));
    }

}
