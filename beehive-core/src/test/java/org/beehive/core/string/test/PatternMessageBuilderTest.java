/*
 * Copyright (c) 2019-2023 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.test.FormatMessageBuilderTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2023-03-07
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string.test;

import org.beehive.core.string.pattern.PatternMessage;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.string.test</code></li>
 *   <li>Class Name: <code>FormatMessageBuilderTest</code></li>
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
 *         <td align="center"><em>2023-03-07</em></td>
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
public class PatternMessageBuilderTest {

    @Test
    public void messageTest() {


        DecimalFormat df = new DecimalFormat("#.#0E0");
        System.out.println(df.toLocalizedPattern());
        System.out.println(df.toPattern());

        String fstr = "first = {0,number," + df.toPattern() + "}, second = {1,number," + df.toPattern() + "}, '{,  '},  '''',.   '{0'}";
        String str = MessageFormat.format(fstr, 12345.15, -2000.7869, 3L);
        System.out.println(str);

        System.out.println(MessageFormat.format("{0, number, 0.0E0}", 1000002123.123456));

    }


    @Test
    public void numberPatternBuilderTest() {
        PatternMessage numberMessage = PatternMessage.numberBuilder(0)
//                .digit()
                .point(3)
//                .digitOrZero()
                .grouping()
//                .currency(new PatternMessage.NumberPatternBuilder.CurrencyPattern().showTailEnd(true).setSpacing("      "))
                .scientificNotation()
                .build();
        String tMessage = numberMessage.placeholder();
        System.out.println(tMessage);
        System.out.println(MessageFormat.format(tMessage, 12123.123456));

    }

    @Test
    public void datePatternBuilderTest() {
        PatternMessage dateMessage = PatternMessage.dateBuilder(0)
                .era()
                .spacing("  ")
                .year()
                .spacing('年')
                .month(1)
                .spacing('月')
                .dayInMonth(1)
                .spacing('日')
                .build();
        String tMessage = dateMessage.placeholder();
        System.out.println(tMessage);
        System.out.println(MessageFormat.format(tMessage, new Date()));
    }

    @Test
    public void timePatternBuilderTest() {
        PatternMessage dateMessage = PatternMessage.timeBuilder(0)
                .timezone(1)
                .spacing("  ")
                .rfcTimezone(3)
                .spacing("  ")
                .isoTimezone(2)
                .spacing("  ")
                .hour_0_23()
                .spacing(":")
                .minute(3)
                .spacing(":")
                .second()
                .spacing(".")
                .millisecond(4)
                .build();
        String tMessage = dateMessage.placeholder();
        System.out.println(tMessage);
        System.out.println(MessageFormat.format(tMessage, new Date()));
    }

}
