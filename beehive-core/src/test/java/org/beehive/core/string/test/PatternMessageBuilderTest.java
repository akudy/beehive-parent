/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
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
 *
 * @author akudy
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
