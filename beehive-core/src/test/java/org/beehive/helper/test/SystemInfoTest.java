/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
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
 *
 * @author akudy
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
