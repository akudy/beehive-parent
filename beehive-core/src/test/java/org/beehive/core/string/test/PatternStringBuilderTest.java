/*
 * Copyright (c) 2019-2023 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.test.FormatStringBuilderTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2023-01-30
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string.test;

import org.beehive.core.string.PatternStringBuilder;
import org.beehive.core.string.pattern.PatternString;
import org.junit.Test;

import java.util.Date;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.string.test</code></li>
 *   <li>Class Name: <code>FormatStringBuilderTest</code></li>
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
 *         <td align="center"><em>2023-01-30</em></td>
 *         <td align="center"><em>akudy</em></td>
 *         <td><em>Define</em></td>
 *       </tr>
 *     </table>
 *   </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @since 1.0
 */
public class PatternStringBuilderTest {


    @Test
    public void stringTest() {
        String fstr1 = "% 8d";
        String fstr2 = "% 8d";

        Object value = 20;
        String str1 = String.format(fstr1, value);
        String str2 = String.format(fstr2, value);

        System.out.println(str1);
        System.out.println(str2);

        Date now = new Date();
        Date date2 = new Date(2022, 1, 3);
        String fstr3 = "%tY-%<tm-%<td  %2$tH:%<tM:%<tS";
        System.out.println(String.format(fstr3, now, date2));

    }

    @Test
    public void formatStringTest() {
        PatternString fstr = PatternString.builder(PatternString.Marker.DECIMAL_FLOATING_NUMBER)
                .minWidth(20)
                .defaultFormat()
                .argumentIndex(1)
                .leadingSpace()
                .build();
        System.out.println(fstr.placeholder());
        Object value = 12f;
        String str1 = String.format(fstr.toString(), value);
        System.out.println(str1);
    }

    @Test
    public void test() {
        PatternStringBuilder builder = new PatternStringBuilder();
        builder.append("这是一个全日期时间 = ");
        builder.append(PatternString.builder(PatternString.Marker.DATE_YEAR).argumentIndex(2).build()).append("-");
        builder.append(PatternString.builder(PatternString.Marker.DATE_MONTH_OF_YEAR).build()).append("-");
        builder.append(PatternString.builder(PatternString.Marker.DATE_DAY_OF_MONTH).build()).append(" ");
        builder.append(PatternString.builder(PatternString.Marker.TIME_HOUR_24_HOUR_CLOCK).argumentIndex(2).build()).append(":");
        builder.append(PatternString.builder(PatternString.Marker.TIME_MINUTE).build()).append(":");
        builder.append(PatternString.builder(PatternString.Marker.TIME_SECOND).build());
        builder.append(", 后面是一个浮点数 = ");
        builder.append(PatternString.builder(PatternString.Marker.DECIMAL_FLOATING_NUMBER).defaultFormat().leftAlign(8).argumentIndex(5).build());
        builder.append(", 后面是一个整数 = ");
        builder.append(PatternString.builder(PatternString.Marker.DECIMAL_INTEGER_NUMBER).leadingZero().minWidth(8).argumentIndex(6).build());
        String str = builder.toString();
        System.out.println(str);
        Date now = new Date();
        String str2 = String.format(str, now, now, now, now, -20f, 20);
        System.out.println(str2);

        PatternStringBuilder fsb = new PatternStringBuilder("aaaa");
        fsb.append(PatternString.builder(PatternString.Marker.BOOL_VALUE_LOWERCASE).build().placeholder(), 0,2);
        System.out.println(fsb);
    }

}
