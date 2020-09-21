/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.ClassUtils
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-08-19
 * Comments:
 */

package org.beehive.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 类分析工具类，提供类的解析等功能。
 * <br>
 * 该类是主要依赖反射机制来工作。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.util</code></li>
 * <li>Class Name: <code>ClassUtils</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1" cellspacing="0" cellpadding="0" summary=""Upgrade&shy;Modify History>
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
 * <td align="center"><em>2020/8/19</em></td>
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
public class ClassUtils {

    /*----------------------------- class type start ----------------------------------------*/

    /**
     * 这是一个值类型列表
     */
    private static Set<Class<?>> valueTypeSet = new HashSet<>();

    static {
        // 字符串视为值类型
        CollectionUtils.add(valueTypeSet, String.class, StringBuffer.class, StringBuilder.class);
        // 数值视为值类型
        CollectionUtils.add(valueTypeSet, BigDecimal.class, BigInteger.class);
        // 日历、日期、时间视为值类型
        CollectionUtils.add(valueTypeSet, Date.class, LocalDate.class, LocalDateTime.class, LocalTime.class, Calendar.class, java.sql.Date.class, java.sql.Timestamp.class);
    }

    /**
     * 注册一个值类型；该方法用于辅助值类型的判断。
     *
     * @param clazz 视为值的类型
     * @see #isValueType(Class)
     * @since 1.0
     */
    public void registerValueType(Class<?> clazz) {
        ClassUtils.valueTypeSet.add(clazz);
    }

    /**
     * 判断输入的类型是否为基础数据类型；基础数据类型为8个：<br/>
     * <pre>
     *  类型    |长度(bit)    |取值范围      |默认值
     *  --------|-------------|--------------|-----------
     *  byte    |8            |-2^7~2^7-1    |0
     *  char    |16           |\u0000~\uffff |\u0000
     *  short   |16           |-2^15~2^15-1  |0
     *  int     |32           |-2^31~2^31-1  |0
     *  long    |64           |-2^63~2^63-1  |0
     *  float   |32           |              |0.0F
     *  double  |64           |              |0.0D
     *  boolean |1            |true/false    |false
     * </pre>
     *
     * @param clazz 待判定的输入类型
     * @return 如果是基础数据类型则返回true，否则返回false
     * @see Class#isPrimitive()
     * @since 1.0
     */
    public static boolean isPrimitiveType(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            return true;
        }
        return false;
    }

    /**
     * 判断输入的类型是否为基础数据类型的包装类型；基础数据对象对应的包装类型：
     * <pre>
     *   基础数据类型  |  包装类型
     *   --------------|----------------------
     *   byte          |  java.lang.Byte
     *   char          |  java.lang.Character
     *   short         |  java.lang.Short
     *   int           |  java.lang.Integer
     *   long          |  java.lang.Long
     *   float         |  java.lang.Float
     *   double        |  java.lang.Double
     *   boolean       |  java.lang.Boolean
     * </pre>
     *
     * @param clazz 待判定的输入类型
     * @return 如果输入的类型是包装类型，则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isWrapperType(Class<?> clazz) {
        if (clazz == Byte.class) {
            return true;
        } else if (clazz == Character.class) {
            return true;
        } else if (clazz == Short.class || clazz == Integer.class || clazz == Long.class) {
            return true;
        } else if (clazz == Float.class || clazz == Double.class) {
            return true;
        } else if (clazz == Boolean.class) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断输入的类型是否是一个值类型（即描述一个值的类型）；这个值类型由内部维护，可以通过{@link #registerValueType(Class)}来进行注册。<br/>
     * 这里判断的是该类型的直接描述类型，不追溯其对应的超类型。
     *
     * @param clazz 待判断的输入类型
     * @return 如果是基础类型或基础类型包装类型或者与已注册的值类型匹配，则返回true；否则返回false
     * @see #isPrimitiveType(Class)
     * @see #isWrapperType(Class)
     * @see #registerValueType(Class)
     * @since 1.0
     */
    public static boolean isValueType(Class<?> clazz) {
        if (isPrimitiveType(clazz)) {
            return true;
        }
        if (isWrapperType(clazz)) {
            return true;
        }
        if (valueTypeSet.contains(clazz)) {
            return true;
        }
        return false;
    }

    /**
     * 获取泛型数组的实际类型
     *
     * @param array 数组对象
     * @param <E>   数组元素类型
     * @return 数组的实际类型
     * @since 1.0
     */
    public static <E> Class<?> getArrayType(E[] array) {
        if (array == null) {
            return null;
        }
        return array.getClass().getComponentType();
    }

    /*----------------------------- class type end ----------------------------------------*/


    /*----------------------------- class info start ----------------------------------------*/


    /*----------------------------- class info end ----------------------------------------*/

}