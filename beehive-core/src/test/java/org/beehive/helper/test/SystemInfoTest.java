/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.helper.test.SystemInfoTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-14
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.helper.test;

import org.beehive.helper.SystemInfo;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.helper.test</code></li>
 * <li>Class Name: <code>SystemInfoTest</code></li>
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
 * <td align="center"><em>2019/11/5</em></td>
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
public class SystemInfoTest {

    @Test
    public void systemOtherTest() throws Exception {
        System.out.println(System.inheritedChannel());
        System.out.println(System.getSecurityManager());
        Map<String, String> infoMap = System.getenv();
        if (infoMap != null) {
            infoMap.forEach((key, value) -> {
                System.out.println(key + " = " + value);
            });
        }
    }

    @Test
    public void getAllProperties() {
        System.getProperties().list(System.out);
    }

    @Test
    public void systemInfoTest() throws Exception {

        System.out.println(SystemInfo.class.getSimpleName() + "========================>");
        Field[] systemFields = SystemInfo.class.getDeclaredFields();
        for (Field field : systemFields) {
            System.out.println(SystemInfo.class.getSimpleName() + "." + field.getName() + "=" + field.get(null));
        }
        Method[] methods = SystemInfo.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(SystemInfo.class.getSimpleName() + "." + method.getName() + "()=" + method.invoke(SystemInfo.class));
        }

        System.out.println("<==============================");

        Class[] innerClasses = SystemInfo.class.getDeclaredClasses();
        for (Class clazz : innerClasses) {
            System.out.println(SystemInfo.class.getSimpleName() + "." + clazz.getSimpleName() + "========================>");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getModifiers() == 25) {
                    System.out.println(SystemInfo.class.getSimpleName() + "." + clazz.getSimpleName() + "." + field.getName() + "=" + field.get(null));
                }
            }
            System.out.println("<==============================");
        }


    }

}
