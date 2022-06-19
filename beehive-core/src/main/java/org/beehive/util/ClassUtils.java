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

import org.beehive.core.file.AntPathMatcher;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

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
     *  类型    |长度(bit)    |取值范围             |默认值
     *  --------|-------------|-----------------|-----------
     *  byte    |8            |-2^7~2^7-1       |0
     *  char    |16           |\'u0000~\'uffff  |\'u0000
     *  short   |16           |-2^15~2^15-1     |0
     *  int     |32           |-2^31~2^31-1     |0
     *  long    |64           |-2^63~2^63-1     |0
     *  float   |32           |                 |0.0F
     *  double  |64           |                 |0.0D
     *  boolean |1            |true/false       |false
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
     *   基础数据类型   |  包装类型
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
     * 判断给定的类型是否实现了指定的接口类型
     *
     * @param clazz          需要检查判断的类型定义
     * @param interfaceClass 接口类型，如果非接口类型则返回false，否则逐层向上查找
     * @return 如果给定的匹配类型非接口，则返回false；如果其直接或间接实现该接口则返回true
     * @since 1.0
     */
    public static boolean implementOf(Class<?> clazz, Class<?> interfaceClass) {
        if (clazz == null) {
            return false;
        }
        if (interfaceClass == null) {
            return false;
        }
        if (!interfaceClass.isInterface()) {
            return false;
        }
        // 优先基于接口向上查找；获取类型的直接上层所有接口，如果没有接口则获取其父类的接口
        Class<?>[] upperClassArray = clazz.getInterfaces();
        if (upperClassArray == null || upperClassArray.length == 0) {
            if (clazz.getSuperclass() != null) {
                upperClassArray = clazz.getSuperclass().getInterfaces();
            }
        }
        // 进行接口名称的匹配
        if (upperClassArray != null && findInterfaceClazz(interfaceClass, upperClassArray)) {
            return true;
        } else {
            // 其次基于父类向上查找；查找其直接父类的接口
            clazz = clazz.getSuperclass();
            return implementOf(clazz, interfaceClass);
        }
    }

    /**
     * 递归查找接口类
     *
     * @param clazz           待查找接口类型
     * @param upperClazzArray 上层接口数组
     * @return 如果待查找接口类型和上层接口类型一致则返回true，否则返回false
     * @since 1.0
     */
    private static boolean findInterfaceClazz(Class<?> clazz, Class<?>[] upperClazzArray) {
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
     * 判断给定的类型是否继承了指定的某个类型
     *
     * @param clazz      需要检查判断的类型定义
     * @param superClass 父类型定义
     * @return 如果直接或间接继承该类则返回true；否则返回false
     * @since 1.0
     */
    public static boolean extendsOf(Class<?> clazz, Class<?> superClass) {
        if (superClass == null) {
            return false;
        }
        Class<?> currentClazz = clazz.getSuperclass();
        while (currentClazz != null && currentClazz != Object.class) {
            if (superClass == currentClazz) {
                return true;
            }
            currentClazz = currentClazz.getSuperclass();
        }
        return false;
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
     *     <li>扩展类加载器(Extension ClassLoader)：这个类加载器负责加载\lib\ext目录下的类库,用来加载java的扩展库,开发者可以直接使用这个类加载器.</li>
     *     <li>应用程序类加载器(Application ClassLoader)：这个类加载器负责加载用户类路径(CLASSPATH)下的类库,一般我们编写的java类都是由这个类加载器加载,这个类加载器是CLassLoader中的getSystemClassLoader()方法的返回值,所以也称为系统类加载器.一般情况下这就是系统默认的类加载器.</li>
     *     <li>自定义类加载器(Custom ClassLoader)：自己定义的类加载器,用来满足特殊的需求,需要继承java.lang.ClassLoader类.</li>
     * </ol>
     * Java的类加载器使用了双亲委托模型，当需要加载一个类时，优先交个启动类加载器；如果没有找到被加载类，则交给扩展类加载器；如果没有找到被加载类，则交给应用程序加载器；
     * 如果还是没有找到被加载类，则返回给给委托的发起者，并由它到制定的文件系统或网路URL中加载该类，如果没有，则抛出ClassNotFoundException异常。这种模型可以避免类的重复加载。
     *
     * @return 默认的类加载器对象
     * @see ClassLoader
     * @see #getClassLoader(Class)
     * @since 1.0
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

    /**
     * 获取指定类的类加载器
     *
     * @param clazz 类型定义
     * @return 该类所属的类加载器
     * @see ClassLoader
     * @see #getDefaultClassLoader()
     * @since 1.0
     */
    public static ClassLoader getClassLoader(Class<?> clazz) {
        ClassLoader classLoader = clazz.getClassLoader();
        if (classLoader == null) {
            // 系统类加载器（应用程序级别顶层类加载器）
            classLoader = ClassLoader.getSystemClassLoader();
        }
        return classLoader;
    }

    /**
     * 获取默认的类加载路径目录地址（完整URL地址）。
     *
     * @return 默认的类加载路径目录地址
     * @see #getDefaultClassLoader()
     * @see ClassLoader#getResource(String)
     * @see URL
     * @since 1.0
     */
    public static URL getDefaultClassLoadURL() {
        if (getDefaultClassLoader() == null) {
            return null;
        }
        return getDefaultClassLoader().getResource("");
    }

    /**
     * 获取指定类的类加载路径目录地址（完整URL地址）。
     *
     * @param clazz 类型定义
     * @return 指定类所在的类加载路径目录地址
     * @see #getClassLoader(Class)
     * @see ClassLoader#getResource(String)
     * @see URL
     * @since 1.0
     */
    public static URL getClassLoadURL(Class<?> clazz) {
        if (getClassLoader(clazz) == null) {
            return null;
        }
        return getClassLoader(clazz).getResource("");
    }

    /**
     * 获取指定类路径目录地址（完整URL地址）。<br/>
     * 如果类型是一个JDK中的类型，则返回null。
     *
     * @param clazz 类型定义
     * @return 指定类所在的类目录地址
     * @since 1.0
     */
    public static URL getCurrentClassURL(Class<?> clazz) {
        return clazz.getResource("");
    }

    /*----------------------------- class loader end ----------------------------------------*/

    /*----------------------------- class info start ----------------------------------------*/

    /**
     * 获取类型定义的全名称（包含包名）。例如：org.beehive.util.EnumUtils
     *
     * @param clazz 类型定义
     * @return 类型定义的全名称（包含包名）
     * @since 1.0
     */
    public static String getFullName(Class<?> clazz) {
        return clazz.getCanonicalName();
    }

    /**
     * 获取类型定义的缩略名称（包含缩略的包名称）。例如：org.beehive.util.EnumUtils被重新修饰为o.b.u.EnumUtils
     *
     * @param clazz 类型定义
     * @return 类型定义的缩略名称（包含包的缩略名）
     * @since 1.0
     */
    public static String getAbbreviatedName(Class<?> clazz) {
        String fullName = getFullName(clazz);
        String[] packageArrays = fullName.split("\\.");
        StringBuilder builder = new StringBuilder();
        for (int i = 0, size = packageArrays.length; i < size; i++) {
            if (i < size - 1) {
                builder.append(packageArrays[i].charAt(0)).append(".");
            } else {
                builder.append(packageArrays[i]);
            }
        }
        return builder.toString();
    }

    /**
     * 获取类型定义的短名称（不包含包名称）。例如：org.beehive.util.EnumUtils将返回EnumUtils
     *
     * @param clazz 类型定义
     * @return 类型定义的短名称（不包含包名称）
     * @since 1.0
     */
    public static String getShortName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

    /**
     * 判断指定的类型定义是否来自该package。<br/>
     * eg:<br/>
     * (java.lang.String.class, "java.lang") ==> true<br/>
     * (org.beehive.util.ClassUtils.class, "org.beehive") ==> true<br/>
     * (org.beehive.util.ClassUtils.class, "org") ==> true<br/>
     *
     * @param clazz       类型定义
     * @param packageName 比对的包名称字符串
     * @return 如果类型的在指定的包下（即具有相同的开始包名称），则返回true，否则返回false
     * @since 1.0
     */
    public static boolean fromPackage(Class<?> clazz, String packageName) {
        String fullName = getFullName(clazz);
        return fullName.startsWith(packageName);
    }

    /**
     * Ant 路径匹配器
     */
    private static AntPathMatcher antPathMatcher;

    /**
     * 判断指定的类型定义是否来自Ant模式package下。<br/>
     * eg:<br/>
     * (java.lang.String.class, "java.*") ==> true<br/>
     * (org.beehive.util.ClassUtils.class, "org.**") ==> true<br/>
     * (org.beehive.util.ClassUtils.class, "org") ==> false<br/>
     *
     * @param clazz          类型定义
     * @param packagePattern 比对的包Ant模式字符串
     * @return 如果类型与指定的Ant模式包名称匹配，则返回true，否则返回false
     * @since 1.0
     */
    public static boolean matchPackage(Class<?> clazz, String packagePattern) {
        if (antPathMatcher == null) {
            antPathMatcher = new AntPathMatcher(".", true);
        }
        String clazzPackage = clazz.getPackage() != null ? clazz.getPackage().getName() : "";
        return antPathMatcher.match(packagePattern, clazzPackage);
    }

    /**
     * 获取某一个类型的继承或实现链上的所有类型定义（自动去重）
     *
     * @param clazz     需要检查的类型定义
     * @param clazzList 该类型独有的继承或接口实现链上的所有类型定义（自动去重）
     * @since 1.0
     */
    private static void getParentClasses(Class<?> clazz, List<Class<?>> clazzList) {
        if (clazz == null) {
            return;
        }
        List<Class<?>> tempClassList = new ArrayList<>();
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            tempClassList.add(superClass);
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces != null && interfaces.length > 0) {
            tempClassList.addAll(Arrays.asList(interfaces));
        }
        for (Class<?> tempClass : tempClassList) {
            if (tempClass == Object.class || tempClass == null) {
                break;
            }
            if (!clazzList.contains(tempClass)) {
                clazzList.add(tempClass);
            }
            getParentClasses(tempClass, clazzList);
        }
    }

    /**
     * 判断给定的类是否声明为泛型类型，类表面定义直接描述为泛型定义，不考虑其父类或父接口定义。
     *
     * @param clazz 需要检查的类型定义
     * @return 如果直接声明的为泛型类型，则返回true；否则返回false
     * @see #isGenericType(Class)
     * @since 1.0
     */
    public static boolean isDeclaredGenericType(Class<?> clazz) {
        if (clazz == null) {
            return false;
        }
        TypeVariable[] typeVariableArray = clazz.getTypeParameters();
        if (typeVariableArray != null && typeVariableArray.length > 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断给定的类是否是泛型类型，考虑其父类或父接口是否是泛型定义。<br/>
     * 由于子类在继承或其他情况下会抹除泛型定义，所以这种判断推测不一定正确。<br/>
     * <strong>请慎用</strong>
     *
     * @param clazz 需要检查的类型定义
     * @return 如果直接声明的为房型类型，则返回true；否则依次向上判断其超类或接口返回定义是否为泛型定义，如果是则返回true；否则返回false
     * @see #isDeclaredGenericType(Class)
     * @since 1.0
     */
    public static boolean isGenericType(Class<?> clazz) {
        if (isDeclaredGenericType(clazz)) {
            return true;
        }
        List<Class<?>> superClassList = new ArrayList<>();
        getParentClasses(clazz, superClassList);
        for (Class<?> superClass : superClassList) {
            if (isDeclaredGenericType(superClass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取某一个类型的继承或实现链上的所有泛型类型定义（自动去重）
     *
     * @param clazz    需要检查的类型定义
     * @param typeList 该类型独有的继承或接口实现链上的所有泛型类型定义（自动去重）
     * @param findUp   是否获取链，true表示查找链式所有的类型，false表示不查找
     * @since 1.0
     */
    private static void getGenericParentClasses(Class<?> clazz, List<ParameterizedType> typeList, boolean findUp) {
        if (clazz == null) {
            return;
        }
        List<Type> tempTypeList = new ArrayList<>();
        Type superClass = clazz.getGenericSuperclass();
        if (superClass != null && superClass != Object.class) {
            tempTypeList.add(superClass);
        }
        Type[] interfaces = clazz.getGenericInterfaces();
        if (interfaces.length > 0) {
            tempTypeList.addAll(Arrays.asList(interfaces));
        }
        for (Type tempType : tempTypeList) {
            if (tempType == Object.class || tempType == null) {
                break;
            }
            // 如果不是泛型，则忽略
            if (tempType instanceof ParameterizedType) {
                if (!typeList.contains(tempType)) {
                    typeList.add((ParameterizedType) tempType);
                }
            }
            // 如果是Class类型则忽略
            if (findUp && tempType.getClass() != Class.class) {
                getGenericParentClasses(tempType.getClass(), typeList, true);
            }
        }
    }

    /**
     * 过滤泛型父类，将没有定义真实类型的泛型定义过滤掉；如果存在泛型类型声明时定义多个参数，则如果有一个参数未明确指定类型，也将被过滤移除
     *
     * @param typeList 待过滤的泛型类型列表
     * @since 1.0
     */
    private static void filterGenericParentClasses(List<ParameterizedType> typeList) {
        if (typeList == null) {
            return;
        }
        Iterator<ParameterizedType> iterator = typeList.iterator();
        while (iterator.hasNext()) {
            ParameterizedType parameterizedType = iterator.next();
            Type[] actualTypeArray = parameterizedType.getActualTypeArguments();
            for (int i = 0; i < actualTypeArray.length; i++) {
                // 如果真实类型不是类型定义，则忽略
                if (!(actualTypeArray[i] instanceof Class)) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * 获取给定类的实际声明泛型类型，获取类型在声明定义时的具体泛型类型。
     *
     * @param clazz 需要检查的类型定义
     * @return 返回给定类具体的泛型类型列表，按声明的顺序进行排序
     * @see #isGenericType(Class)
     * @see #getActualGenericType(Class)
     * @since 1.0
     */
    public static ParameterizedType[] getDeclaredGenericType(Class<?> clazz) {
        List<ParameterizedType> parameterizedSupperType = new ArrayList<>();
        getGenericParentClasses(clazz, parameterizedSupperType, false);
        if (parameterizedSupperType.size() > 0) {
            return parameterizedSupperType.toArray(new ParameterizedType[]{});
        }
        return new ParameterizedType[]{};
    }

    /**
     * 获取给定类的实际声明泛型类型，通过继承或接口实现链路进行查找，并输出所有的定义了实际类型的泛型类型。（如果存在泛型类型声明时定义多个参数，则如果有一个参数未明确指定类型）
     *
     * @param clazz 需要检查的类型定义
     * @return 返回给定类具体的泛型类型列表，按声明的顺序进行排序
     * @see #getDeclaredGenericType(Class)
     * @since 1.0
     */
    public static ParameterizedType[] getActualGenericType(Class<?> clazz) {
        List<ParameterizedType> parameterizedSupperType = new ArrayList<>();
        getGenericParentClasses(clazz, parameterizedSupperType, true);
        filterGenericParentClasses(parameterizedSupperType);
        if (parameterizedSupperType.size() > 0) {
            return parameterizedSupperType.toArray(new ParameterizedType[]{});
        }
        return new ParameterizedType[]{};
    }

    /**
     * 获取指定类的所有注解定义。<br/>
     * 包含父类（非接口）定义的可被继承，并且 {@link Retention} 被标记为 Runtime 类型的注解 和 当前类上声明的注解。
     *
     * @param clazz 被检查的类定义
     * @return 注解列表
     * @see Class#getAnnotations()
     */
    public static Annotation[] getAnnotation(Class<?> clazz) {
        if (clazz == null) {
            return new Annotation[]{};
        }
        return clazz.getAnnotations();
    }

    /*----------------------------- class info end ----------------------------------------*/

    /*----------------------------- class content start ----------------------------------------*/

    private static List<Field> getFields(Class<?> clazz, boolean findUp) {
        List<Field> fieldList = new ArrayList<>();
        List<Class<?>> clazzList = new ArrayList<>();
        clazzList.add(clazz);
        if (findUp) {
            getParentClasses(clazz, clazzList);
        }
        for (Class<?> tempClazz : clazzList) {
            fieldList.addAll(Arrays.asList(tempClazz.getDeclaredFields()));
        }
        return fieldList;
    }

    private static List<Constructor<?>> getConstructors(Class<?> clazz) {
        return Arrays.asList(clazz.getDeclaredConstructors());
    }

    private static List<Method> getMethods(Class<?> clazz, boolean findUp) {
        List<Method> methodList = new ArrayList<>();
        List<Class<?>> clazzList = new ArrayList<>();
        clazzList.add(clazz);
        if (findUp) {
            getParentClasses(clazz, clazzList);
        }
        for (Class<?> tempClazz : clazzList) {
            methodList.addAll(Arrays.asList(tempClazz.getDeclaredMethods()));
        }
        return methodList;
    }


    public static boolean hasField(Class<?> clazz, String fieldName, boolean findUp) {
        return false;
    }

    public static boolean hasField(Class<?> clazz, String fieldName) {
        return hasField(clazz, fieldName, false);
    }

    public static boolean hasField(Class<?> clazz, Type declaredType, String fieldName, boolean findUp) {
        return false;
    }

    public static boolean hasField(Class<?> clazz, Type declaredType, String fieldName) {
        return false;
    }

    public static Field[] findFiled(Class<?> clazz, String fieldName, boolean findUp) {
        return null;
    }

    public static Field[] findFiled(Class<?> clazz, String fieldName) {
        return null;
    }

    public static Field[] findFiled(Class<?> clazz, Type declaredType, String fieldName, boolean findUp) {
        return null;
    }

    public static Field[] findFiled(Class<?> clazz, Type declaredType, String fieldName) {
        return null;
    }

    public static boolean hasConstructor(Class<?> clazz, Type... parameterTypes) {
        return false;
    }

    public static Constructor[] findConstructor(Class<?> clazz, Type... parameterTypes) {
        return null;
    }

    public static boolean hasMethodName(Class<?> clazz, String methodName, boolean findUp) {
        return false;
    }

    public static boolean hasMethodName(Class<?> clazz, String methodName) {
        return false;
    }

    public static Method[] findMethod(Class<?> clazz, Type returnType, String methodName, boolean findUp, Type... parameterTypes) {
        return null;
    }

    public static Method[] findMethod(Class<?> clazz, Type returnType, String methodName, Type... parameterTypes) {
        return null;
    }

    public static void main(String[] args) throws Exception {

    }

    public static List<Field> getFieldList(Class<?> clazz, boolean findUp) {
        return getFields(clazz, findUp);
    }

    public static List<Constructor<?>> getConstructorList(Class<?> clazz) {
        return getConstructors(clazz);
    }

    public static List<Method> getMethodList(Class<?> clazz, boolean findUp) {
        return getMethods(clazz, findUp);
    }

    /*----------------------------- class content end ----------------------------------------*/

}
