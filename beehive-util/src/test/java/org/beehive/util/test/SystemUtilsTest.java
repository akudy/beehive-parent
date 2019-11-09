/*
 * Copyright (c) 2019-2019 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-util
 * File Name: org.beehive.util.test.SystemUtilsTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2019-11-01
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.util.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.Locale;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.util.test</code></li>
 * <li>Class Name: <code>SystemUtilsTest</code></li>
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
 * <td align="center"><em>2019/11/1</em></td>
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
public class SystemUtilsTest {

    @Test
    public void localTest() {
        Locale enLocale = new Locale("en_IN");
        Locale[] locales = Locale.getAvailableLocales();
        System.out.println(Arrays.toString(Locale.getISOCountries()));
        System.out.println(Arrays.toString(Locale.getISOLanguages()));
        for (Locale locale : locales) {
            System.out.print(locale + "\t" + locale.getDisplayName());
            System.out.print("\t" + locale.getCountry() + "\t" + locale.getDisplayCountry() + "\t" + locale.getDisplayCountry(enLocale));
            System.out.print("\t" + locale.getLanguage() + "\t" + locale.getDisplayLanguage() + "\t" + locale.getDisplayLanguage(enLocale));
            System.out.println();
            //System.out.println(locale.getCountry() + "\t" + locale.getDisplayCountry() + "\t" + locale.getLanguage() + "\t" + locale.getDisplayLanguage() + "\t" + locale.toString()+"\t"+locale.getDisplayScript()+"\t"+locale.getDisplayVariant()+"\t"+locale.getExtensionKeys());
        }
    }


}
