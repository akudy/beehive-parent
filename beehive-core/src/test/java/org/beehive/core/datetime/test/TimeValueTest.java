/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.datetime.test;

import org.beehive.core.datetime.TimeValue;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
public class TimeValueTest {

    @Test
    public void buildTest() {
        TimeValue timeValue = TimeValue.now();
        System.out.println(timeValue);
        timeValue = TimeValue.of(20, 13, 23, 21);
        System.out.println(timeValue);
        timeValue = TimeValue.of(13, 54, 12);
        System.out.println(timeValue);
        timeValue = TimeValue.ofCalendar(Calendar.getInstance());
        System.out.println(timeValue);
        timeValue = TimeValue.ofDate(new Date());
        System.out.println(timeValue);
        timeValue = TimeValue.ofTimestamp(1);
        System.out.println(timeValue);
        timeValue = TimeValue.ofLocalTime(LocalTime.now());
        System.out.println(timeValue);
        timeValue = TimeValue.ofLocalDateTime(LocalDateTime.now());
        System.out.println(timeValue);
        timeValue = TimeValue.ofSQLDate(new java.sql.Date(1));
        System.out.println(timeValue);
        timeValue = TimeValue.ofSQLTimestamp(new java.sql.Timestamp(1));
        System.out.println(timeValue);
    }

    @Test
    public void getValueTest() {
        TimeValue timeValue = TimeValue.of(8, 8, 8);
        System.out.println(timeValue.getHour() + ":" + timeValue.getMinute() + ":" + timeValue.getSecond() + ":" + timeValue.getMillisecond());
        System.out.println(timeValue.hour() + ":" + timeValue.minute() + ":" + timeValue.second() + ":" + timeValue.millisecond());
    }

    @Test
    public void compareTest() {
        TimeValue timeValue1 = TimeValue.of(23, 12, 32);
        TimeValue timeValue2 = TimeValue.of(22, 0, 23);
        System.out.println(String.format("%s after %s => %s", timeValue1, timeValue2, timeValue1.after(timeValue2)));
        System.out.println(String.format("%s before %s => %s", timeValue1, timeValue2, timeValue1.before(timeValue2)));
        System.out.println(String.format("%s equals %s => %s", timeValue1, timeValue2, timeValue1.equals(timeValue2)));

        System.out.println("------------------------");

        List<TimeValue> list = new ArrayList<>();
        list.add(TimeValue.of(1, 4, 3));
        list.add(TimeValue.of(23, 21, 32));
        list.add(TimeValue.of(10, 32, 12));
        list.add(TimeValue.of(10, 32, 11));
        list.add(TimeValue.of(1, 4, 4));
        list.add(TimeValue.of(12, 32, 12));
        Collections.sort(list);
        for (TimeValue timeValue : list) {
            System.out.println(timeValue);
        }
    }

}
