/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.test.ClassUtilsSample
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-14
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.util.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.util.test</code></li>
 * <li>Class Name: <code>ClassUtilsSample</code></li>
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
 * <td align="center"><em>2020/8/28</em></td>
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
public class ClassUtilsSample {

    // 泛型接口
    interface InterfaceA<E> {

    }

    // 普通接口继承泛型接口
    interface InterfaceB extends InterfaceA<Integer> {

    }

    // 普通接口
    interface InterfaceC {

    }

    // 泛型类
    class ClassA<E> {

    }

    // 普通类继承泛型类
    class ClassB extends ClassA<Integer> {

    }

    //普通类
    class ClassC {

    }

    // 普通类实现泛型接口
    class ClassD implements InterfaceA<Integer> {

    }

    class StringArrayList extends ArrayList<String> {

    }

    /**
     * 判断一个类型是否是泛型类型
     *
     * @param clazz 输入的类型
     * @return 如果是泛型类型，则返回true，否则返回false
     */
    public static boolean isGenericType(Class<?> clazz) {
        TypeVariable[] typeVariableArray = clazz.getTypeParameters();
        if (typeVariableArray != null && typeVariableArray.length > 0) {
            return true;
        }
        /*Class<?> clazzTemp = clazz;
        while (Object.class != clazzTemp && clazzTemp != null) {
            Type type = clazz.getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                return true;
            }
            clazzTemp = clazz.getSuperclass();
        }*/
        return false;
    }

    /**
     * 获取一个泛型类的真实泛型类型
     *
     * @param clazz 泛型类
     * @return 泛型类型列表
     */
    public static Class<?>[] getGenericType(Class<?> clazz) {
        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] actualTypeArray = parameterizedType.getActualTypeArguments();
            int length = actualTypeArray.length;
            Class<?>[] classArray = new Class<?>[length];
            for (int i = 0; i < length; i++) {
                if (actualTypeArray[i] instanceof Class) {
                    classArray[i] = (Class<?>) actualTypeArray[i];
                }
            }
            return classArray;
        }
        return null;
    }

    public void genericTypeTest() {
        System.out.println(String.format("isGenericType(%s) = %s", InterfaceA.class, isGenericType(InterfaceA.class)));
        System.out.println(String.format("isGenericType(%s) = %s", InterfaceB.class, isGenericType(InterfaceB.class)));
        System.out.println(String.format("isGenericType(%s) = %s", InterfaceC.class, isGenericType(InterfaceC.class)));
        System.out.println(String.format("isGenericType(%s) = %s", ClassA.class, isGenericType(ClassA.class)));
        System.out.println(String.format("isGenericType(%s) = %s", ClassB.class, isGenericType(ClassB.class)));
        System.out.println(String.format("isGenericType(%s) = %s", ClassC.class, isGenericType(ClassC.class)));
        System.out.println(String.format("isGenericType(%s) = %s", ClassD.class, isGenericType(ClassD.class)));
        System.out.println(String.format("isGenericType(%s) = %s", List.class, isGenericType(List.class)));
        System.out.println(String.format("isGenericType(%s) = %s", Map.class, isGenericType(Map.class)));
        System.out.println(String.format("isGenericType(%s) = %s", Object.class, isGenericType(Object.class)));
        System.out.println(String.format("isGenericType(%s) = %s", Class.class, isGenericType(Class.class)));

        System.out.println();
        List<Integer> list = new ArrayList<>();
        StringArrayList list2 = new StringArrayList();

        System.out.println(isGenericType(list2.getClass()));
//        System.out.println(list.getClass().getGenericSuperclass());
//        System.out.println(list2.getClass().getGenericSuperclass());
        Type type = list.getClass().getGenericSuperclass();
        System.out.println(((ParameterizedType) type).getRawType());
        System.out.println(String.format("getGenericType(%s) =  %s", list, Arrays.toString(getGenericType(list.getClass()))));
    }

    public static void main(String[] args) {
        ClassUtilsSample sample = new ClassUtilsSample();
        sample.genericTypeTest();
    }


}
