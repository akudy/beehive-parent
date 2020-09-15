/*
 * Copyright (c) 2019-2019 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-date
 * File Name: org.beehive.core.datetime.test.TimeZoneTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2019-11-26
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.datetime.test;

import org.junit.Test;

import java.util.TimeZone;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.datetime.test</code></li>
 * <li>Class Name: <code>TimeZoneTest</code></li>
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
 * <td align="center"><em>2019/11/26</em></td>
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
public class TimeZoneTest {

    @Test
    public void testPrintAllTimeZone() {

        System.out.println(System.getProperty("java.time.zone.DefaultZoneRulesProvider"));

        String[] timeZoneNameList = TimeZone.getAvailableIDs();
        long time = System.currentTimeMillis();
        for (String timeZoneName : timeZoneNameList) {
//            System.out.println(timeZoneName);
//            System.out.println(TimeZone.getTimeZone(timeZoneName));
            TimeZone timeZone = TimeZone.getTimeZone(timeZoneName);
            System.out.println(timeZone.getID() + "\t" + timeZone.getDisplayName() + "\t" + timeZone.getRawOffset() + "\t" + timeZone.getDSTSavings() + "\t" + timeZone.getOffset(time));
        }

//        System.out.println("-------");
//        List<String> timeZoneNameSet =  new ArrayList(java.time.ZoneId.getAvailableZoneIds());
//        Collections.sort(timeZoneNameSet);
//        for (String timeZoneName : timeZoneNameList){
//            System.out.println(timeZoneName);
//            //System.out.println(TimeZone.getTimeZone(timeZoneName));
//        }

    }

}
