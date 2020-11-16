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
    public static void registerValueType(Class<?> clazz) {
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
        return clazz.isPrimitive();
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

    /**
     * 判断给定的实例对象是否实现了某一个接口类型
     *
     * @param object 实例对象
     * @param clazz  接口类型，如果非接口类型则返回false，否则逐层向上查找
     * @return 如果给定的匹配类型非接口，则返回false；如果其直接或间接实现该接口则返回true
     */
    public static boolean implementOf(Object object, Class<?> clazz) {
        if (clazz == null) {
            return false;
        }
        if (!clazz.isInterface()) {
            return false;
        }
        Class<?>[] upperClassArray = object.getClass().getInterfaces();
        if (upperClassArray == null || upperClassArray.length == 0) {
            upperClassArray = object.getClass().getSuperclass().getInterfaces();
        }
        return findInterfaceClazz(clazz, upperClassArray);
    }

    /**
     * 递归查找接口类
     *
     * @param clazz           待查找接口类型
     * @param upperClazzArray 上层接口数组
     * @return 如果待查找接口类型和上层接口类型一致则返回true，否则返回false
     */
    private static boolean findInterfaceClazz(Class<?> clazz, Class<?>[] upperClazzArray) {
        if (upperClazzArray == null || upperClazzArray.length == 0) {
            return false;
        }
        for (Class<?> upperClazz : upperClazzArray) {
            if (upperClazz.isInterface()) {
                if (clazz == upperClazz) {
                    return true;
                } else {
                    return findInterfaceClazz(clazz, upperClazz.getInterfaces());
                }
            } else {
                if (upperClazz != Object.class) {
                    clazz = clazz.getSuperclass();
                    return findInterfaceClazz(clazz, upperClazz.getInterfaces());
                }
                return false;
            }
        }
        return false;
    }

    /**
     * 判断给定的实例对象是否继承了某个类型
     *
     * @param object 实例对象
     * @param clazz  类类型
     * @return 如果直接或间接继承该类则返回true；否则返回false
     */
    public static boolean extendsOf(Object object, Class<?> clazz) {
        if (clazz == null) {
            return false;
        }
        Class<?> currentClazz = object.getClass().getSuperclass();
        while (currentClazz != null && currentClazz != Object.class) {
            if (clazz == currentClazz) {
                return true;
            }
            currentClazz = currentClazz.getSuperclass();
        }
        return false;
    }


    /**
     * 判断给定的实例对象是否是指定类型的实例
     *
     * @param object 对象实例
     * @param clazz  类型
     * @return 如果是指定类型的实例则返回true，否则返回false
     */
    public static boolean instanceOf(Object object, Class<?> clazz) {
        return clazz.isInstance(object);
    }

    /*----------------------------- class type end ----------------------------------------*/

    /*----------------------------- class loader start ----------------------------------------*/

    /**
     * 获取默认的{@link ClassLoader}类加载器对象。
     * <br/>
     * {@link ClassLoader}类加载器，可根据一个指定的类的全限定名,找到对应的Class字节码文件,然后加载它转化成一个java.lang.Class类的一个实例。<br/>
     * Java应用程序的类加载器分为四类（优先级依次递减）：
     * <ol>
     *     <li>启动类加载器(Bootstrap ClassLoader)：这个类加载器负责将\lib目录下的类库加载到虚拟机内存中,用来加载java的核心库,此类加载器并不继承于java.lang.ClassLoader,不能被java程序直接调用,代码是使用C++编写的.是虚拟机自身的一部分.</li>
     *     <li>扩展类加载器(Extendsion ClassLoader)：这个类加载器负责加载\lib\ext目录下的类库,用来加载java的扩展库,开发者可以直接使用这个类加载器.</li>
     *     <li>应用程序类加载器(Application ClassLoader)：这个类加载器负责加载用户类路径(CLASSPATH)下的类库,一般我们编写的java类都是由这个类加载器加载,这个类加载器是CLassLoader中的getSystemClassLoader()方法的返回值,所以也称为系统类加载器.一般情况下这就是系统默认的类加载器.</li>
     *     <li>自定义类加载器(Custom ClassLoader)：自己定义的类加载器,用来满足特殊的需求,需要继承java.lang.ClassLoader类.</li>
     * </ol>
     * Java的类加载器使用了双亲委托模型，当需要加载一个类时，优先交个启动类加载器；如果没有找到被加载类，则交给扩展类加载器；如果没有找到被加载类，则交给应用程序加载器；
     * 如果还是没有找到被加载类，则返回给给委托的发起者，并由它到制定的文件系统或网路URL中加载该类，如果没有，则抛出ClassNotFoundException异常。这种模型可以避免类的重复加载。
     *
     * @return
     * @see ClassLoader
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        // 当前线程的类加载器（应用程序级别）
        classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            // 当前类所在的类加载器（应用程序级别）
            classLoader = ClassUtils.class.getClassLoader();
        }
        if (classLoader == null) {
            // 系统类加载器（应用程序级别顶层类加载器）
            classLoader = ClassLoader.getSystemClassLoader();
        }
        return classLoader;
    }

    public static ClassLoader getClassLoader(Class<?> clazz) {
        return clazz.getClassLoader();
    }

    /*----------------------------- class loader end ----------------------------------------*/


    /*----------------------------- class info start ----------------------------------------*/


    /*----------------------------- class info end ----------------------------------------*/

}
