/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.util;

import org.beehive.core.file.AntPathMatcher;
import org.beehive.core.reflection.ConstructorMatcher;
import org.beehive.core.reflection.FieldMatcher;
import org.beehive.core.reflection.MethodMatcher;

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
 *
 * @author akudy
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
     *  类型    |长度(bit)     |取值范围          |默认值
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
     * {@link ClassLoader}类加载器，可根据一个指定的类的全限定名，找到对应的Class字节码文件，然后加载它转化成一个java.lang.Class类的一个加载器实例。<br/>
     * Java应用程序的类加载器分为四类（优先级依次递减）：
     * <ol>
     *     <li>启动类加载器(Bootstrap ClassLoader)：这个类加载器负责将\lib目录下的类库加载到虚拟机内存中,用来加载java的核心库,此类加载器并不继承于java.lang.ClassLoader,不能被java程序直接调用,代码是使用C++编写的.是虚拟机自身的一部分.</li>
     *     <li>扩展类加载器(Extension ClassLoader)：这个类加载器负责加载\lib\ext目录下的类库,用来加载java的扩展库,开发者可以直接使用这个类加载器.</li>
     *     <li>应用程序类加载器(Application ClassLoader)：这个类加载器负责加载用户类路径(CLASSPATH)下的类库,一般我们编写的java类都是由这个类加载器加载,这个类加载器是CLassLoader中的getSystemClassLoader()方法的返回值,所以也称为系统类加载器.一般情况下这就是系统默认的类加载器.</li>
     *     <li>自定义类加载器(Custom ClassLoader)：自己定义的类加载器,用来满足特殊的需求,需要继承java.lang.ClassLoader类.</li>
     * </ol>
     * Java的类加载器使用了双亲委托模型，当需要加载一个类时，优先交给启动类加载器；如果没有找到被加载类，则交给扩展类加载器；如果没有找到被加载类，则交给应用程序加载器；
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
     * 获取给定类的实际声明泛型类型，通过继承或接口实现链路进行查找，并输出所有的定义了实际类型的泛型类型。（如果存在泛型类型声明时定义多个参数，则如果有一个参数未明确指定类型，则被忽略）
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
     * @since 1.0
     */
    public static Annotation[] getAnnotations(Class<?> clazz) {
        if (clazz == null) {
            return new Annotation[]{};
        }
        return clazz.getAnnotations();
    }

    /**
     * 判断指定的类型，是否含有指定的注解定义。<br/>
     * 如果要实现多个注解的包含检查，可以借助{@link #getAnnotations(Class)}和{@link ArrayUtils#containsAll(Object[], Comparator, Object[])}或者{@link ArrayUtils#containsAny(Object[], Comparator, Object[])}的组合功能来实现。
     *
     * @param clazz           被检查的类定义
     * @param annotationClass 需要检查的注解类型
     * @return 如果该类包含指定的注解定义（含继承来的注解定义），则返回true，否则返回false
     */
    public static boolean hasAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        Annotation[] annotations = getAnnotations(clazz);
        for (Annotation tempAnn : annotations) {
            if (tempAnn.annotationType() == annotationClass) {
                return true;
            }
        }
        return false;
    }


    /*----------------------------- class info end ----------------------------------------*/

    /*----------------------------- class content start ----------------------------------------*/

    /**
     * 获取指定类型和其继承链上的可以匹配的字段定义。这里查找字段范围包括公有、被保护的、默认的、私有的，以及内置的。<br/>
     * 继承链上的所有类型会按照定义的顺序进行依次查找。<br/><br/>
     * <strong>注：该方法主要用于辅助反射功能，请谨慎使用。</strong>
     *
     * @param clazz   指定的底层类型
     * @param matcher 字段匹配器
     * @param findUp  是否向上查找，true-是；false-否
     * @return 如果不向上查找；则直返回当前类型的字段定义列表；否则会向上查找，所有继承链上的类的字段定义列表
     * @see Class#getDeclaredFields()
     * @since 1.0
     */
    private static List<Field> getFieldList(Class<?> clazz, FieldMatcher matcher, boolean findUp) {
        List<Field> fieldList = new ArrayList<>();
        List<Class<?>> clazzList = new ArrayList<>();
        clazzList.add(clazz);
        if (findUp) {
            // 获取所有父类型定义
            getParentClasses(clazz, clazzList);
        }
        // 将所有类型的字段添加到结果列表
        for (Class<?> tempClazz : clazzList) {
            for (Field tempField : tempClazz.getDeclaredFields()) {
                if (matcher.accept(tempField)) {
                    fieldList.add(tempField);
                }
            }
        }
        return fieldList;
    }

    /**
     * 获取指定类型的可以匹配的构造函数的定义<br/><br/>
     * <strong>注：该方法主要用于辅助反射功能，请谨慎使用。</strong>
     *
     * @param clazz   指定的类型
     * @param matcher 构造函数匹配器
     * @return 该类型定义的所有构造函数列表
     * @see Class#getDeclaredConstructors()
     * @since 1.0
     */
    private static List<Constructor<?>> getConstructorList(Class<?> clazz, ConstructorMatcher matcher) {
        List<Constructor<?>> constructorList = new ArrayList<>();
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor tempConstructor : constructors) {
            if (matcher.accept(tempConstructor)) {
                constructorList.add(tempConstructor);
            }
        }
        return constructorList;
    }

    /**
     * 获取指定类型和其继承链上的所有类型的方法定义。这里的方法，包括公有、被保护的、默认的、私有的。<br/>
     * 继承链上的所有类型会按照定义的顺序进行依次查找。<br/><br/>
     * <strong>注：该方法主要用于辅助反射功能，请谨慎使用。</strong>
     *
     * @param clazz   指定的底层类型
     * @param matcher 方法匹配器
     * @param findUp  是否向上查找，true-是；false-否
     * @return 如果不向上查找；则直返回当前类型的方法定义列表；否则会向上查找，所有继承链上的类的方法定义列表
     * @see Class#getDeclaredMethods()
     * @since 1.0
     */
    private static List<Method> getMethodList(Class<?> clazz, MethodMatcher matcher, boolean findUp) {
        List<Method> methodList = new ArrayList<>();
        List<Class<?>> clazzList = new ArrayList<>();
        clazzList.add(clazz);
        if (findUp) {
            // 获取所有父类型定义
            getParentClasses(clazz, clazzList);
        }
        // 将所有类型的方法添加到结果列表
        for (Class<?> tempClazz : clazzList) {
            for (Method tempMethod : tempClazz.getDeclaredMethods()) {
                if (matcher.accept(tempMethod)) {
                    methodList.add(tempMethod);
                }
            }
        }
        return methodList;
    }


    /**
     * 从指定的类型定义中获取与字段匹配器匹配字段定义。（所包含的字段定义）<br/>
     * 如果使用向上查找策略，会从当前指定类型的继承链上依次向上查找，所以可能会存在多个同名同类型的字段定义，但是隶属不同类定义。<br/>
     * 如果使用向上查找策略，会违反属性不被继承的原则，在面向对象的原则中，属性是不会被继承的；如果使用向上查找，则可能会存在误导，所以一般用于反射查找和操作字段。<p/>
     *
     * @param clazz   要进行字段查找的类型
     * @param matcher 字段匹配器，参考{@link FieldMatcher}
     * @return 指定类型中与字段匹配器匹配的字段定义
     * @see FieldMatcher
     * @see #getFieldList(Class, FieldMatcher, boolean)
     * @since 1.0
     */
    public static Field[] getContainFields(Class<?> clazz, FieldMatcher matcher) {
        List<Field> fieldList = getFieldList(clazz, matcher, true);
        return fieldList.toArray(new Field[]{});
    }

    /**
     * 从指定的类型中，查找获取字段匹配器匹配的字段定义，不会从当前指定类型的继承链上依次向上查找。（所声明的字段定义）
     *
     * @param clazz   要进行字段查找的类型
     * @param matcher 字段匹配器，参考{@link FieldMatcher}
     * @return 指定类型中与字段匹配器匹配的字段定义
     * @see #getFieldList(Class, FieldMatcher, boolean)
     * @since 1.0
     */
    public static Field[] getDeclaredFields(Class<?> clazz, FieldMatcher matcher) {
        List<Field> fieldList = getFieldList(clazz, matcher, false);
        return fieldList.toArray(new Field[]{});
    }


    /**
     * 从指定的类型中查找获取指定名称的字段定义（所包含的字段）。<br/><br/>
     * 如果使用向上查找策略，会从当前指定类型的继承链上依次向上查找，所以可能会存在多个同名同名的字段定义，但是隶属不同类定义。<br/>
     * 如果使用向上查找策略，会违反属性不被继承的原则，在面向对象的原则中，属性是不会被继承的；如果使用向上查找，则可能会存在误导，所以一般用于反射查找和操作字段。<p/>
     *
     * @param clazz     要进行字段查找的类型
     * @param fieldName 字段名称
     * @return 指定类型中包含指定名称的字段
     * @see #getContainFields(Class, FieldMatcher)
     * @since 1.0
     */
    public static Field[] getContainFields(Class<?> clazz, String fieldName) {
        FieldMatcher matcher = new FieldMatcher.Builder(fieldName).build();
        List<Field> fieldList = getFieldList(clazz, matcher, true);
        return fieldList.toArray(new Field[]{});
    }

    /**
     * 从指定的类型中，查找获取指定名称的字段定义，不会从当前指定类型的继承链上依次向上查找（所声明的字段）。<br/><br/>
     * 由于不会向上查找，在同一个类中同一个字段名称仅能出现一次，所以只会存在一个字段。
     *
     * @param clazz     要进行字段查找的类型
     * @param fieldName 字段名称
     * @return 指定类型中包含指定类型声明和名称的字段
     * @see #getDeclaredFields(Class, FieldMatcher)
     * @since 1.0
     */
    public static Field getDeclaredField(Class<?> clazz, String fieldName) {
        FieldMatcher matcher = new FieldMatcher.Builder(fieldName).build();
        Field[] fields = getDeclaredFields(clazz, matcher);
        if (fields == null || fields.length == 0) {
            return null;
        } else if (fields.length > 1) {
            throw new RuntimeException("duplicate definitions for the same field[" + fieldName + "].");
        }
        return fields[0];
    }

    /**
     * 判断指定的类是否包含指定匹配器匹配的字段定义<br/>会向上查找
     *
     * @param clazz   要检查的类定义
     * @param matcher 字段匹配器
     * @return 如果包含指定名称的字段，则返回true；否则返回false
     * @since 1.0
     */
    public static boolean containField(Class<?> clazz, FieldMatcher matcher) {
        Field[] matchFields = getContainFields(clazz, matcher);
        return matchFields != null && matchFields.length > 0;
    }

    /**
     * 判断指定的类是否声明了指定匹配器匹配的字段定义<br/>不会向上查找
     *
     * @param clazz   要检查的类定义
     * @param matcher 字段匹配器
     * @return 如果包含指定名称的字段，则返回true；否则返回false
     * @since 1.0
     */
    public static boolean declaredField(Class<?> clazz, FieldMatcher matcher) {
        Field[] matchFields = getDeclaredFields(clazz, matcher);
        return matchFields != null && matchFields.length > 0;
    }

    /**
     * 判断指定的类是否包含指定名称的字段定义
     *
     * @param clazz     要检查的类定义
     * @param fieldName 要检查的字段名称定义
     * @return 如果包含指定名称的字段，则返回true；否则返回false
     * @since 1.0
     */
    public static boolean containField(Class<?> clazz, String fieldName) {
        Field[] matchFields = getContainFields(clazz, fieldName);
        return matchFields != null && matchFields.length > 0;
    }

    /**
     * 判断指定的类是否声明了指定名称的字段定义
     *
     * @param clazz     要检查的类定义
     * @param fieldName 要检查的字段名称定义
     * @return 如果包含指定名称的字段，则返回true；否则返回false
     * @since 1.0
     */
    public static boolean declaredField(Class<?> clazz, String fieldName) {
        return getDeclaredField(clazz, fieldName) != null;
    }

    /**
     * 从指定的类型中获取指定构造函数匹配器匹配的构造函数定义
     *
     * @param clazz   要查找的类定义
     * @param matcher 构造函数匹配器，参考{@link ConstructorMatcher}
     * @return 指定类中与指定构造函数匹配器匹配的构造函数列表
     * @since 1.0
     */
    public static Constructor[] getDeclaredConstructors(Class<?> clazz, ConstructorMatcher matcher) {
        //获取所有的构造函数
        List<Constructor<?>> constructorList = getConstructorList(clazz, matcher);
        return constructorList.toArray(new Constructor[]{});
    }

    /**
     * 获取指定类的指定参数类型列表签名的构造函数，参数类型列表进行依次匹配。<br/><br/>
     * 由于一个类针对同一个参数签名的构造函数只存在一个，所以这里只返回一个构造函数
     *
     * @param clazz          要查找的类定义
     * @param parameterTypes 参数类型列表定义
     * @return 指定类的指定参数类型列表的构造函数
     * @see #getDeclaredConstructors(Class, ConstructorMatcher)
     * @since 1.0
     */
    public static Constructor getDeclaredConstructor(Class<?> clazz, Type... parameterTypes) {
        ConstructorMatcher matcher = new ConstructorMatcher.Builder().addParameterType(parameterTypes).build();
        Constructor[] constructors = getDeclaredConstructors(clazz, matcher);
        if (constructors.length > 0) {
            return constructors[0];
        }
        return null;
    }

    /**
     * 判断指定类是否包含指定参数类型列表的构造函数，参数类型列表进行依次匹配
     *
     * @param clazz          要查找的类定义
     * @param parameterTypes 参数类型列表定义
     * @return 如果存在匹配的构造函数，则返回true，否则返回false
     * @see #getDeclaredConstructor(Class, Type...)
     * @since 1.0
     */
    public static boolean declaredConstructor(Class<?> clazz, Type... parameterTypes) {
        Constructor constructor = getDeclaredConstructor(clazz, parameterTypes);
        return constructor != null;
    }

    /**
     * 从指定的类型定义中获取与方法匹配器匹配的方法定义，并使用向上查找策略，会从当前指定类型的继承链上依次向上查找，所以可能会存在多个方法定义，但是隶属不同类定义。<br/>
     * 如果使用向上查找策略，可能会违反私有方法不被继承的原则，在面向对象的原则中，私有方法是不会被继承的；如果使用向上查找，则可能会存在误导，所以一般用于反射查找和操作字段。<p/>
     *
     * @param clazz   要查找的类定义
     * @param matcher 方法匹配器
     * @return 与方法匹配器匹配的方法列表
     * @since 1.0
     */
    public static Method[] getContainMethods(Class<?> clazz, MethodMatcher matcher) {
        List<Method> methodList = getMethodList(clazz, matcher, true);
        return methodList.toArray(new Method[]{});
    }

    /**
     * 从指定的类型定义中获取与方法匹配器匹配的方法定义，不使用向上查找策略<br/>
     *
     * @param clazz   要查找的类定义
     * @param matcher 方法匹配器
     * @return 与方法匹配器匹配的方法列表
     * @since 1.0
     */
    public static Method[] getDeclaredMethods(Class<?> clazz, MethodMatcher matcher) {
        List<Method> methodList = getMethodList(clazz, matcher, false);
        return methodList.toArray(new Method[]{});
    }

    /**
     * 从指定的类中查找与方法名称匹配的方法定义，使用向上查找策略（查找范围包括继承而来的方法查找、以及父类定义的私有方法）<br/>
     *
     * @param clazz      要查找的类定义
     * @param methodName 方法名称
     * @return 与方法名称匹配的方法列表
     * @see #getContainFields(Class, FieldMatcher)
     */
    public static Method[] getContainMethods(Class<?> clazz, String methodName) {
        MethodMatcher matcher = new MethodMatcher.Builder().nameOf(methodName).build();
        return getContainMethods(clazz, matcher);
    }

    /**
     * 从指定的类型定义中查找与方法名称匹配的方法定义，不使用向上查找策略<br/>
     *
     * @param clazz      要查找的类定义
     * @param methodName 方法名称
     * @return 与方法名称匹配的方法列表
     * @see #getDeclaredFields(Class, FieldMatcher)
     */
    public static Method[] getDeclaredMethods(Class<?> clazz, String methodName) {
        MethodMatcher matcher = new MethodMatcher.Builder().nameOf(methodName).build();
        return getDeclaredMethods(clazz, matcher);
    }

    /**
     * 从指定的类中查找与方法名称和参数类型列表匹配的方法定义，使用向上查找策略（查找范围包括继承而来的方法查找、以及父类定义的私有方法）<br/>
     *
     * @param clazz      要查找的类定义
     * @param methodName 方法名称
     * @return 与方法名称匹配的方法列表
     * @see #getContainFields(Class, FieldMatcher)
     */
    public static Method[] getContainMethods(Class<?> clazz, String methodName, Type... parameterTypes) {
        MethodMatcher matcher = new MethodMatcher.Builder().nameOf(methodName).addParameterType(parameterTypes).build();
        return getContainMethods(clazz, matcher);
    }

    /**
     * 从指定的类型定义中查找与方法名称和参数类型列表匹配的方法定义，不使用向上查找策略<br/>
     *
     * @param clazz      要查找的类定义
     * @param methodName 方法名称
     * @return 与方法名称匹配的方法列表
     * @see #getDeclaredFields(Class, FieldMatcher)
     */
    public static Method[] getDeclaredMethods(Class<?> clazz, String methodName, Type... parameterTypes) {
        MethodMatcher matcher = new MethodMatcher.Builder().nameOf(methodName).addParameterType(parameterTypes).build();
        return getDeclaredMethods(clazz, matcher);
    }

    /**
     * 判断指定的类型中是否包含匹配器匹配的方法，使用向上查找策略（查找范围包括继承而来的方法查找、以及父类定义的私有方法）<br/>
     *
     * @param clazz   要查找的类定义
     * @param matcher 方法匹配器
     * @return 如果包含匹配的方法，则返回true；否则返回false
     * @see #getContainFields(Class, FieldMatcher)
     */
    public static boolean containMethod(Class<?> clazz, MethodMatcher matcher) {
        List<Method> methodList = getMethodList(clazz, matcher, true);
        return methodList != null && methodList.size() > 0;
    }

    /**
     * 判断指定的类型中是否声明了匹配器匹配的方法，不适用向上查找策略<br/>
     *
     * @param clazz   要查找的类定义
     * @param matcher 方法匹配器
     * @return 如果声明了匹配的方法，则返回true，否则返回false
     * @see #getDeclaredMethods(Class, MethodMatcher)
     */
    public static boolean declaredMethod(Class<?> clazz, MethodMatcher matcher) {
        List<Method> methodList = getMethodList(clazz, matcher, false);
        return methodList != null && methodList.size() > 0;
    }

    /**
     * 判断指定的类型中是否包含方法名称匹配的方法，使用向上查找策略（查找范围包括继承而来的方法查找、以及父类定义的私有方法）<br/>
     *
     * @param clazz      要查找的类定义
     * @param methodName 方法名称
     * @return 如果包含匹配的方法，则返回true；否则返回false
     * @see #getContainMethods(Class, String)
     */
    public static boolean containMethod(Class<?> clazz, String methodName) {
        MethodMatcher matcher = new MethodMatcher.Builder().nameOf(methodName).build();
        return containMethod(clazz, matcher);
    }

    /**
     * 判断指定的类型中是否声明了方法名称匹配的方法，使用向上查找策略（查找范围包括继承而来的方法查找、以及父类定义的私有方法）<br/>
     *
     * @param clazz      要查找的类定义
     * @param methodName 方法名称
     * @return 如果声明了匹配的方法，则返回true；否则返回false
     * @see #getDeclaredMethods(Class, String) (Class, String)
     */
    public static boolean declaredMethod(Class<?> clazz, String methodName) {
        MethodMatcher matcher = new MethodMatcher.Builder().nameOf(methodName).build();
        return declaredMethod(clazz, matcher);
    }

    /**
     * 判断指定的类型中是否包含方法名称和参数类型列表匹配的方法，使用向上查找策略（查找范围包括继承而来的方法查找、以及父类定义的私有方法）<br/>
     *
     * @param clazz      要查找的类定义
     * @param methodName 方法名称
     * @return 如果包含匹配的方法，则返回true；否则返回false
     * @see #getContainMethods(Class, String)
     */
    public static boolean containMethod(Class<?> clazz, String methodName, Type... parameterTypes) {
        MethodMatcher matcher = new MethodMatcher.Builder().nameOf(methodName).addParameterType(parameterTypes).build();
        return containMethod(clazz, matcher);
    }

    /**
     * 判断指定的类型中是否声明了方法名称和参数类型列表匹配的方法，使用向上查找策略（查找范围包括继承而来的方法查找、以及父类定义的私有方法）<br/>
     *
     * @param clazz      要查找的类定义
     * @param methodName 方法名称
     * @return 如果声明了匹配的方法，则返回true；否则返回false
     * @see #getDeclaredMethods(Class, String) (Class, String)
     */
    public static boolean declaredMethod(Class<?> clazz, String methodName, Type... parameterTypes) {
        MethodMatcher matcher = new MethodMatcher.Builder().nameOf(methodName).addParameterType(parameterTypes).build();
        return declaredMethod(clazz, matcher);
    }

    /*----------------------------- class content end ----------------------------------------*/

}
