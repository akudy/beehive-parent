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
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.LocaleResources;

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
    public void formatTest() {

        Locale[] locales = Locale.getAvailableLocales();
//        for (Locale locale : locales) {
//            System.out.println(locale + "\t" + locale.getDisplayName() + "\t" + locale.getDisplayLanguage() + "\t" + locale.getDisplayCountry());
//        }
//

        Locale locale = Locale.forLanguageTag("en");

        Calendar calendar = Calendar.getInstance();
        LocaleResources localeResources = LocaleProviderAdapter.getResourceBundleBased().getLocaleResources(locale);
        String p = localeResources.getDateTimePattern(3,3, calendar);
        System.out.println(p);



        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DAY_OF_MONTH, 23);
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 50);
        calendar.set(Calendar.SECOND, 60);
        calendar.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat(p,locale);


        System.out.println(sdf.format(calendar.getTime()));

        Date date1 = DateTimeUtils.newDateTime(2019, 2, 28, 12, 34, 45, 343);
        System.out.println(sdf.format(date1));

        Date date2 = DateTimeUtils.newDateTimeOfArray(2020, 5);
        System.out.println(sdf.format(date2));


        Date date3 = DateTimeUtils.tryToParse("2019年03月03日 23:13:10","yyyy-MM-dd HH:mm:ss","yyyy年MM月dd日 HH:mm:ss","yyyy/MM/dd HH:mm:ss");
        System.out.println(date3);

    }

}
