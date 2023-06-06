/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.datetime.test;

import org.beehive.core.datetime.DateValue;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class DateValueTest {

    @Test
    public void buildTest() {
        DateValue dateValue = DateValue.now();
        System.out.println(dateValue);
        dateValue = DateValue.of(2023, 5, 19);
        System.out.println(dateValue);
        dateValue = DateValue.ofCalendar(Calendar.getInstance());
        System.out.println(dateValue);
        dateValue = DateValue.ofDate(new Date());
        System.out.println(dateValue);
        dateValue = DateValue.ofTimestamp(123456789);
        System.out.println(dateValue);
        dateValue = DateValue.ofLocalDate(LocalDate.now());
        System.out.println(dateValue);
        dateValue = DateValue.ofLocalDateTime(LocalDateTime.now());
        System.out.println(dateValue);
        dateValue = DateValue.ofSQLDate(new java.sql.Date(1));
        System.out.println(dateValue);
        dateValue = DateValue.ofSQLTimestamp(new java.sql.Timestamp(1));
        System.out.println(dateValue);
    }

    @Test
    public void getValueTest() {
        DateValue dateValue = DateValue.of(2023, 4, 30);
        System.out.println(dateValue);
        System.out.println("getYear() => " + dateValue.getYear() + ", year => " + dateValue.year());
        System.out.println("getMonth() => " + dateValue.getMonth() + ", month => " + dateValue.month());
        System.out.println("getDay() => " + dateValue.getDay() + ", day => " + dateValue.day());
        System.out.println("getWeek() => " + dateValue.getWeek());
        System.out.println("getDayOfWeek() => " + dateValue.getDayOfWeek());
        System.out.println("getWeekOfMonth() => " + dateValue.getWeekOfMonth());
        System.out.println("getWeekOfYear() => " + dateValue.getWeekOfYear());
        System.out.println("getDayOfWeekInMonth() => " + dateValue.getDayOfWeekInMonth());
        System.out.println("getDayOfYear() => " + dateValue.getDayOfYear());
        System.out.println("---------------");
        System.out.println("isFirstDayOfYear() => " + dateValue.isFirstDayOfYear());
        System.out.println("isLastDayOfYear() => " + dateValue.isLastDayOfYear());
        System.out.println("isFirstDayOfMonth() => " + dateValue.isFirstDayOfMonth());
        System.out.println("isLastDayOfMonth() => " + dateValue.isLastDayOfMonth());
    }

    @Test
    public void compareTest() {
        DateValue dateValue1 = DateValue.of(2023, 12, 5);
        DateValue dateValue2 = DateValue.of(2023, 11, 23);
        System.out.println(String.format("%s after %s => %s", dateValue1, dateValue2, dateValue1.after(dateValue2)));
        System.out.println(String.format("%s before %s => %s", dateValue1, dateValue2, dateValue1.before(dateValue2)));
        System.out.println(String.format("%s equals %s => %s", dateValue1, dateValue2, dateValue1.equals(dateValue2)));

        System.out.println("------------------------");

        List<DateValue> list = new ArrayList<>();
        list.add(DateValue.of(2021, 4, 3));
        list.add(DateValue.of(2021, 10, 28));
        list.add(DateValue.of(2022, 9, 12));
        list.add(DateValue.of(2021, 2, 12));
        list.add(DateValue.of(2023, 4, 3));
        list.add(DateValue.of(2023, 8, 12));
        Collections.sort(list);
        for (DateValue dateValue : list) {
            System.out.println(dateValue);
        }
    }

}
