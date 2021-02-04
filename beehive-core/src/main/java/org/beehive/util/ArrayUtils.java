/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.ArrayUtils
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-08-19
 * Comments: Java源文件
 */

package org.beehive.util;

import org.beehive.core.convert.Converter;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 数组工具类，提供数组的检查、转换、排序、查找等功能。
 * <br>
 * 该工具类用于辅助{@link Arrays}工具类的实现数组其他功能的扩展。
 * 该工具类中所有涉及到需要比较的对象元素，必须是{@link Comparable}的一个实例，在实现{@link Comparable}接口时，需要遵循该接口的实现约定，同时保证与{@link Object#equals(Object)}的方法具有等同性。
 * 当你重写{@link Object#equals(Object)}时需要保证{@link Object#hashCode()}的可靠性。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.util</code></li>
 * <li>Class Name: <code>ArrayUtils</code></li>
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
 * <td align="center"><em>2020/8/18</em></td>
 * <td align="center"><em>akudy</em></td>
 * <td><em>Define</em></td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @see Array
 * @see Arrays
 * @since 1.0
 */
public class ArrayUtils {

    /*----------------------------- null & notNull & empty && notEmpty start ----------------------------------------*/

    /**
     * 判断数组是空，即等于null。
     *
     * @param array 数组对象
     * @param <E>   数组元素类型
     * @return 如果等于null则返回true，否则返回false。
     * @since 1.0
     */
    public static <E> boolean isNull(E[] array) {
        return null == array;
    }

    /**
     * 判断byte数组是空，即等于null。
     *
     * @param array byte数组对象
     * @return 如果等于null则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isNull(byte[] array) {
        return null == array;
    }

    /**
     * 判断short数组是空，即等于null。
     *
     * @param array short数组对象
     * @return 如果等于null则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isNull(short[] array) {
        return null == array;
    }


    /**
     * 判断int数组是空，即等于null。
     *
     * @param array int数组对象
     * @return 如果等于null则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isNull(int[] array) {
        return null == array;
    }

    /**
     * 判断long数组是空，即等于null。
     *
     * @param array long数组对象
     * @return 如果等于null则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isNull(long[] array) {
        return null == array;
    }

    /**
     * 判断float数组是空，即等于null。
     *
     * @param array float数组对象
     * @return 如果等于null则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isNull(float[] array) {
        return null == array;
    }

    /**
     * 判断double数组是空，即等于null。
     *
     * @param array double数组对象
     * @return 如果等于null则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isNull(double[] array) {
        return null == array;
    }

    /**
     * 判断char数组是空，即等于null。
     *
     * @param array char数组对象
     * @return 如果等于null则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isNull(char[] array) {
        return null == array;
    }

    /**
     * 判断boolean数组是空，即等于null。
     *
     * @param array boolean数组对象
     * @return 如果等于null则返回true，否则返回false
     * @since 1.0
     */
    public static boolean isNull(boolean[] array) {
        return null == array;
    }

    /**
     * 判断数组不是空，即不等于null。
     *
     * @param array 数组对象
     * @param <E>   数组元素类型
     * @return 如果不等于null则返回false，否则返回true。
     * @since 1.0
     */
    public static <E> boolean isNotNull(E[] array) {
        return null != array;
    }

    /**
     * 判断byte数组不是空，即不等于null。
     *
     * @param array byte数组对象
     * @return 如果不等于null则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isNotNull(byte[] array) {
        return null != array;
    }

    /**
     * 判断short数组不是空，即不等于null。
     *
     * @param array short数组对象
     * @return 如果不等于null则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isNotNull(short[] array) {
        return null != array;
    }

    /**
     * 判断int数组不是空，即不等于null。
     *
     * @param array int数组对象
     * @return 如果不等于null则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isNotNull(int[] array) {
        return null != array;
    }

    /**
     * 判断long数组不是空，即不等于null。
     *
     * @param array long数组对象
     * @return 如果不等于null则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isNotNull(long[] array) {
        return null != array;
    }

    /**
     * 判断float数组不是空，即不等于null。
     *
     * @param array float数组对象
     * @return 如果不等于null则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isNotNull(float[] array) {
        return null != array;
    }

    /**
     * 判断double数组不是空，即不等于null。
     *
     * @param array double数组对象
     * @return 如果不等于null则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isNotNull(double[] array) {
        return null != array;
    }

    /**
     * 判断char数组不是空，即不等于null。
     *
     * @param array char数组对象
     * @return 如果不等于null则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isNotNull(char[] array) {
        return null != array;
    }

    /**
     * 判断boolean数组不是空，即不等于null。
     *
     * @param array boolean数组对象
     * @return 如果不等于null则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isNotNull(boolean[] array) {
        return null != array;
    }

    /**
     * 判断数组无元素，即等于null，或者无元素。
     *
     * @param array 数组对象
     * @param <E>   元素类型
     * @return 如果不等于null或者无元素则返回false，否则返回true。
     * @since 1.0
     */
    public static <E> boolean isEmpty(E[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断byte数组无元素，即等于null，或者无元素。
     *
     * @param array byte数组对象
     * @return 如果不等于null或者无元素则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isEmpty(byte[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断short数组无元素，即等于null，或者无元素。
     *
     * @param array short数组对象
     * @return 如果不等于null或者无元素则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isEmpty(short[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断int数组无元素，即等于null，或者无元素。
     *
     * @param array int数组对象
     * @return 如果不等于null或者无元素则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isEmpty(int[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断long数组无元素，即等于null，或者无元素。
     *
     * @param array long数组对象
     * @return 如果不等于null或者无元素则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isEmpty(long[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断float数组无元素，即等于null，或者无元素。
     *
     * @param array float数组对象
     * @return 如果不等于null或者无元素则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isEmpty(float[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断double数组无元素，即等于null，或者无元素。
     *
     * @param array double数组对象
     * @return 如果不等于null或者无元素则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isEmpty(double[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断char数组无元素，即等于null，或者无元素。
     *
     * @param array char数组对象
     * @return 如果不等于null或者无元素则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isEmpty(char[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断boolean数组无元素，即等于null，或者无元素。
     *
     * @param array boolean数组对象
     * @return 如果不等于null或者无元素则返回false，否则返回true。
     * @since 1.0
     */
    public static boolean isEmpty(boolean[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断数组有元素，即不等于null，或者有元素。
     *
     * @param array 数组对象
     * @param <E>   元素类型
     * @return 如果不等于null或有元素则返回true，否则返回false。
     * @since 1.0
     */
    public static <E> boolean isNotEmpty(E[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断数byte组有元素，即不等于null，或者有元素。
     *
     * @param array byte数组对象
     * @return 如果不等于null或有元素则返回true，否则返回false。
     * @since 1.0
     */
    public static boolean isNotEmpty(byte[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断数short组有元素，即不等于null，或者有元素。
     *
     * @param array short数组对象
     * @return 如果不等于null或有元素则返回true，否则返回false。
     * @since 1.0
     */
    public static boolean isNotEmpty(short[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断数int组有元素，即不等于null，或者有元素。
     *
     * @param array int数组对象
     * @return 如果不等于null或有元素则返回true，否则返回false。
     * @since 1.0
     */
    public static boolean isNotEmpty(int[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断数long组有元素，即不等于null，或者有元素。
     *
     * @param array long数组对象
     * @return 如果不等于null或有元素则返回true，否则返回false。
     * @since 1.0
     */
    public static boolean isNotEmpty(long[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断数float组有元素，即不等于null，或者有元素。
     *
     * @param array float数组对象
     * @return 如果不等于null或有元素则返回true，否则返回false。
     * @since 1.0
     */
    public static boolean isNotEmpty(float[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断数double组有元素，即不等于null，或者有元素。
     *
     * @param array double数组对象
     * @return 如果不等于null或有元素则返回true，否则返回false。
     * @since 1.0
     */
    public static boolean isNotEmpty(double[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断数char组有元素，即不等于null，或者有元素。
     *
     * @param array char数组对象
     * @return 如果不等于null或有元素则返回true，否则返回false。
     * @since 1.0
     */
    public static boolean isNotEmpty(char[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断数boolean组有元素，即不等于null，或者有元素。
     *
     * @param array boolean数组对象
     * @return 如果不等于null或有元素则返回true，否则返回false。
     * @since 1.0
     */
    public static boolean isNotEmpty(boolean[] array) {
        return null != array && array.length > 0;
    }

    /*----------------------------- null & notNull & empty && notEmpty end ----------------------------------------*/

    /*----------------------------- cast start ----------------------------------------*/

    /**
     * 判断一个对象是否是数组对象
     *
     * @param object 待检测对象
     * @return 如果是数组，则返回true，否则返回false，如果是null则也返回false
     * @see Class#isArray()
     * @since 1.0
     */
    public static boolean isArray(Object object) {
        if (object == null) {
            return false;
        }
        return object.getClass().isArray();
    }

    /**
     * 获取一个数据对象的长租
     *
     * @param object 数组对象
     * @return 如果是数组则返回数组长租，如果不是数组对象则抛出异常
     * @throws IllegalArgumentException 如果输入的对象不是数组时抛出该异常
     * @see Array#getLength(Object)
     * @since 1.0
     */
    public static int length(Object object) {
        if (!isArray(object)) {
            throw new IllegalArgumentException(String.format("object [%s] is not array.", object));
        }
        return Array.getLength(object);
    }

    /**
     * 将数组转换为List对象<br/>
     * 与{@link Arrays#asList(Object[])}的区别在于，{@link Arrays#asList(Object[])}转换后是一个List视图（不允许修改），而该方法转换的是一个ArrayList对象（允许修改）。
     *
     * @param e   元素列表
     * @param <E> 元素类型
     * @return ArrayList对象。如果输入的元素为空，则返回null
     * @since 1.0
     */
    @SafeVarargs
    public static <E> List<E> asList(E... e) {
        if (ArrayUtils.isEmpty(e)) {
            return null;
        }
        List<E> arrayList = new ArrayList<>(e.length);
        Collections.addAll(arrayList, e);
        return arrayList;
    }

    /**
     * 将数组转换为List视图对象
     *
     * @param e   元素列表
     * @param <E> 元素类型
     * @return List视图对象。如果输入的元素为空，则返回null
     * @see Arrays#asList(Object[])
     * @since 1.0
     */
    @SafeVarargs
    public static <E> List<E> asListView(E... e) {
        if (ArrayUtils.isEmpty(e)) {
            return null;
        }
        return Arrays.asList(e);
    }

    /**
     * 将数组转换为Set对象
     *
     * @param e   元素列表
     * @param <E> 元素类型
     * @return ArrayList对象。如果输入的元素为空，则返回null
     * @since 1.0
     */
    @SafeVarargs
    public static <E extends Comparable<? super E>> Set<E> asSet(E... e) {
        if (ArrayUtils.isEmpty(e)) {
            return null;
        }
        Set<E> arraySet = new HashSet<>(e.length);
        Collections.addAll(arraySet, e);
        return arraySet;
    }

    /**
     * 将数组转换为Set视图对象
     *
     * @param e   元素列表
     * @param <E> 元素类型
     * @return Set视图对象。如果输入的元素为空，则返回null
     * @see ArrayUtils#asSet(Comparable[])
     * @since 1.0
     */
    @SafeVarargs
    public static <E extends Comparable<? super E>> Set<E> asSetView(E... e) {
        if (ArrayUtils.isEmpty(e)) {
            return null;
        }
        return Collections.unmodifiableSet(asSet(e));
    }

    /**
     * 将一个未知类型的对象转换为指定类型的数组对象
     *
     * @param object 原对象
     * @return 该类型的新的数组对象
     * @throws ClassCastException 如果输入的对象不是数组则抛出该异常
     * @see Array#getLength(Object)
     * @see Array#get(Object, int)
     * @since 1.0
     */
    public static Object[] toArray(Object object) {
        if (!isArray(object)) {
            throw new ClassCastException("object [" + object + "] is not array.");
        }
        int length = Array.getLength(object);
        Object[] array = new Object[length];
        for (int i = 0; i < length; i++) {
            array[i] = Array.get(object, i);
        }
        return array;
    }

    /**
     * 将一个已知类型的对象转换为指定类型的数组对象
     *
     * @param object 原对象
     * @param clazz  指定的数组元素类型
     * @param <E>    指定的数据对象类型
     * @return 该类型的新的数组对象
     * @throws ClassCastException 如果输入的对象不是数组则抛出该异常
     * @see Array#getLength(Object)
     * @see Array#newInstance(Class, int)
     * @see Array#get(Object, int)
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public static <E> E[] toArray(Object object, Class<E> clazz) {
        if (!isArray(object)) {
            throw new ClassCastException("object [" + object + "] is not array.");
        }
        int length = Array.getLength(object);
        E[] array = (E[]) Array.newInstance(clazz, length);
        for (int i = 0; i < length; i++) {
            Object element = Array.get(object, i);
            if (element.getClass() != clazz) {
                throw new ClassCastException("element [" + element + "] cast to [" + clazz + "] is error.");
            }
            array[i] = (E) element;
        }
        return array;
    }

    /**
     * 将一个已知类型的数组转换为另一个指定类型的数组
     *
     * @param sourceArray 源数组对象
     * @param targetClass 目标数组类型
     * @param converter   转换器
     * @param <S>         源数组类型
     * @param <T>         目标数组类型
     * @return 目标数组类型
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public static <S, T> T[] toArray(S[] sourceArray, Class<T> targetClass, Converter<S, T> converter) {
        if (ArrayUtils.isNull(sourceArray)) {
            throw new NullPointerException("source array is null");
        }
        int length = sourceArray.length;
        T[] array = (T[]) Array.newInstance(targetClass, length);
        for (int i = 0; i < length; i++) {
            array[i] = converter.convert(sourceArray[i]);
        }
        return array;
    }

    /*----------------------------- cast end ----------------------------------------*/

    /*----------------------------- contains start ----------------------------------------*/

    /**
     * 判断元素是否在数组中，如果存在则返回true，否则返回false。<br/>
     *
     * @param array 数组
     * @param e     待查找元素
     * @param <E>   元素类型
     * @return 如果存在则返回true，否则返回false
     * @see #contains(Object[], Comparator, Object)
     * @see Object#equals(Object)
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> boolean contains(E[] array, E e) {
        if (isEmpty(array)) {
            return false;
        }
        return indexOf(array, e) >= 0;
    }

    /**
     * 判断元素是否在数组中，如果存在则返回true，否则返回false。<br/>
     *
     * @param array      数组
     * @param e          待查找元素
     * @param comparator 比较器
     * @param <E>        元素类型
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(Object[], Comparator, Object)
     * @since 1.0
     */
    public static <E> boolean contains(E[] array, Comparator<E> comparator, E e) {
        if (isEmpty(array)) {
            return false;
        }
        return indexOf(array, comparator, e) >= 0;
    }

    /**
     * 在byte数组中查找某一个元素是否存在
     *
     * @param array byte数组对象
     * @param e     需要查找的元素
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(byte[], byte)
     * @since 1.0
     */
    public static boolean contains(byte[] array, byte e) {
        if (isEmpty(array)) {
            return false;
        }
        return indexOf(array, e) >= 0;
    }

    /**
     * 在char数组中查找某一个元素是否存在
     *
     * @param array char数组对象
     * @param e     需要查找的元素
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(char[], char)
     * @since 1.0
     */
    public static boolean contains(char[] array, char e) {
        if (isEmpty(array)) {
            return false;
        }
        return indexOf(array, e) >= 0;
    }

    /**
     * 在int数组中查找某一个元素是否存在
     *
     * @param array int数组对象
     * @param e     需要查找的元素
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(short[], short)
     * @since 1.0
     */
    public static boolean contains(int[] array, int e) {
        if (isEmpty(array)) {
            return false;
        }
        return indexOf(array, e) >= 0;
    }


    /**
     * 在short数组中查找某一个元素是否存在
     *
     * @param array short数组对象
     * @param e     需要查找的元素
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(int[], int)
     * @since 1.0
     */
    public static boolean contains(short[] array, short e) {
        if (isEmpty(array)) {
            return false;
        }
        return indexOf(array, e) >= 0;
    }

    /**
     * 在long数组中查找某一个元素是否存在
     *
     * @param array long数组对象
     * @param e     需要查找的元素
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(long[], long)
     * @since 1.0
     */
    public static boolean contains(long[] array, long e) {
        if (isEmpty(array)) {
            return false;
        }
        return indexOf(array, e) >= 0;
    }

    /**
     * 在float数组中查找某一个元素是否存在
     *
     * @param array byte数组对象
     * @param e     需要查找的元素
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(float[], float)
     * @since 1.0
     */
    public static boolean contains(float[] array, float e) {
        if (isEmpty(array)) {
            return false;
        }
        return indexOf(array, e) >= 0;
    }

    /**
     * 在double数组中查找某一个元素是否存在
     *
     * @param array double数组对象
     * @param e     需要查找的元素
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(double[], double)
     * @since 1.0
     */
    public static boolean contains(double[] array, double e) {
        if (isEmpty(array)) {
            return false;
        }
        return indexOf(array, e) >= 0;
    }

    /**
     * 判断多个元素是否在数组中，如果都存在则返回true，否则返回false。<br/>
     *
     * @param array 原数组
     * @param e     待查找的元素
     * @param <E>   数组元素类型
     * @return 如果都存在则返回true，否则返回false。
     * @see #indexOf(Comparable[], Comparable)
     * @since 1.0
     */
    @SafeVarargs
    public static <E extends Comparable<? super E>> boolean containsAll(E[] array, E... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (E element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断多个元素是否在数组中，如果都存在则返回true，否则返回false。<br/>
     *
     * @param array      数组
     * @param e          待查找元素列表
     * @param comparator 比较器
     * @param <E>        元素类型
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(Object[], Comparator, Object)
     * @since 1.0
     */
    @SafeVarargs
    public static <E> boolean containsAll(E[] array, Comparator<E> comparator, E... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (E element : e) {
            int firstIndex = indexOf(array, comparator, element);
            if (firstIndex < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 在byte数组中查找多个元素是否都存在
     *
     * @param array byte数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(byte[], byte)
     * @since 1.0
     */
    public static boolean containsAll(byte[] array, byte... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (byte element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 在char数组中查找多个元素是否都存在
     *
     * @param array char数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(char[], char)
     * @since 1.0
     */
    public static boolean containsAll(char[] array, char... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (char element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 在short数组中查找多个元素是否都存在
     *
     * @param array short数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(short[], short)
     * @since 1.0
     */
    public static boolean containsAll(short[] array, short... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (short element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 在int数组中查找多个元素是否都存在
     *
     * @param array int数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(int[], int)
     * @since 1.0
     */
    public static boolean containsAll(int[] array, int... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (int element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 在long数组中查找多个元素是否都存在
     *
     * @param array long数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(long[], long)
     * @since 1.0
     */
    public static boolean containsAll(long[] array, long... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (long element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 在float数组中查找多个元素是否都存在
     *
     * @param array float数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(float[], float)
     * @since 1.0
     */
    public static boolean containsAll(float[] array, float... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (float element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 在double数组中查找多个元素是否都存在
     *
     * @param array double数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(double[], double)
     * @since 1.0
     */
    public static boolean containsAll(double[] array, double... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (double element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断多个元素是否在数组中，如果有任意一个存在则返回true，否则返回false。<br/>
     *
     * @param array 原数组
     * @param e     待查找的元素
     * @param <E>   数组元素类型
     * @return 如果都存在则返回true，否则返回false。
     * @see #indexOf(Comparable[], Comparable)
     * @since 1.0
     */
    @SafeVarargs
    public static <E extends Comparable<? super E>> boolean containsAny(E[] array, E... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (E element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断多个元素是否在数组中，如果有任意一个存在则返回true，否则返回false。<br/>
     *
     * @param array      数组
     * @param e          待查找元素列表
     * @param comparator 比较器
     * @param <E>        元素类型
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(Object[], Comparator, Object)
     * @since 1.0
     */
    @SafeVarargs
    public static <E> boolean containsAny(E[] array, Comparator<E> comparator, E... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (E element : e) {
            int firstIndex = indexOf(array, comparator, element);
            if (firstIndex >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在byte数组中查找多个元素是否有一个存在
     *
     * @param array byte数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(byte[], byte)
     * @since 1.0
     */
    public static boolean containsAny(byte[] array, byte... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (byte element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在char数组中查找多个元素是否有一个存在
     *
     * @param array char数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(char[], char)
     * @since 1.0
     */
    public static boolean containsAny(char[] array, char... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (char element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在short数组中查找多个元素是否有一个存在
     *
     * @param array short数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #lastIndexOf(short[], short)
     * @since 1.0
     */
    public static boolean containsAny(short[] array, short... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (short element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在int数组中查找多个元素是否有一个存在
     *
     * @param array int数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(int[], int)
     * @since 1.0
     */
    public static boolean containsAny(int[] array, int... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (int element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在long数组中查找多个元素是否有一个存在
     *
     * @param array long数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(long[], long)
     * @since 1.0
     */
    public static boolean containsAny(long[] array, long... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (long element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在float数组中查找多个元素是否有一个存在
     *
     * @param array float数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(float[], float)
     * @since 1.0
     */
    public static boolean containsAny(float[] array, float... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (float element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在double数组中查找多个元素是否有一个存在
     *
     * @param array double数组对象
     * @param e     需要查找的元素列表
     * @return 如果存在则返回true，否则返回false
     * @see #indexOf(double[], double)
     * @since 1.0
     */
    public static boolean containsAny(double[] array, double... e) {
        if (isEmpty(array) || isEmpty(e)) {
            return false;
        }
        for (double element : e) {
            int firstIndex = indexOf(array, element);
            if (firstIndex >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 统计元素是否在数组中出现的次数。<br/>
     *
     * @param array 数组
     * @param e     待查找元素
     * @param <E>   元素类型
     * @return 该元素出现的次数，如果没有出现过则返回0
     * @see #count(Object[], Comparator, Object)
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> int count(E[] array, E e) {
        int count = 0;
        if (isEmpty(array)) {
            return count;
        }
        if (e == null) {
            for (E element : array) {
                if (element == null) {
                    count++;
                }
            }
        } else {
            for (E element : array) {
                if (e.compareTo(element) == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 判断元素是否在数组中查找元素出现的次数。<br/>
     *
     * @param array      数组
     * @param e          待查找元素
     * @param comparator 比较器
     * @param <E>        元素类型
     * @return 该元素出现的次数，如果没有出现过则返回0
     * @see Comparator#compare(Object, Object)
     * @since 1.0
     */
    public static <E> int count(E[] array, Comparator<E> comparator, E e) {
        int count = 0;
        if (isEmpty(array)) {
            return count;
        }
        for (E element : array) {
            if (comparator.compare(e, element) == 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * 在byte数组中查找某一个元素出现的次数
     *
     * @param array byte数组对象
     * @param e     需要查找的元素
     * @return 该元素出现的次数，如果没有出现过则返回0
     * @since 1.0
     */
    public static int count(byte[] array, byte e) {
        int count = 0;
        if (isEmpty(array)) {
            return count;
        }
        for (byte element : array) {
            if (e == element) {
                count++;
            }
        }
        return count;
    }

    /**
     * 在char数组中查找某一个元素出现的次数
     *
     * @param array char数组对象
     * @param e     需要查找的元素
     * @return 该元素出现的次数，如果没有出现过则返回0
     * @since 1.0
     */
    public static int count(char[] array, char e) {
        int count = 0;
        if (isEmpty(array)) {
            return count;
        }
        for (char element : array) {
            if (e == element) {
                count++;
            }
        }
        return count;
    }

    /**
     * 在short数组中查找某一个元素出现的次数
     *
     * @param array short数组对象
     * @param e     需要查找的元素
     * @return 该元素出现的次数，如果没有出现过则返回0
     * @since 1.0
     */
    public static int count(short[] array, short e) {
        int count = 0;
        if (isEmpty(array)) {
            return count;
        }
        for (short element : array) {
            if (e == element) {
                count++;
            }
        }
        return count;
    }

    /**
     * 在int数组中查找某一个元素出现的次数
     *
     * @param array int数组对象
     * @param e     需要查找的元素
     * @return 该元素出现的次数，如果没有出现过则返回0
     * @since 1.0
     */
    public static int count(int[] array, int e) {
        int count = 0;
        if (isEmpty(array)) {
            return count;
        }
        for (int element : array) {
            if (e == element) {
                count++;
            }
        }
        return count;
    }

    /**
     * 在long数组中查找某一个元素出现的次数
     *
     * @param array long数组对象
     * @param e     需要查找的元素
     * @return 该元素出现的次数，如果没有出现过则返回0
     * @since 1.0
     */
    public static int count(long[] array, long e) {
        int count = 0;
        if (isEmpty(array)) {
            return count;
        }
        for (long element : array) {
            if (e == element) {
                count++;
            }
        }
        return count;
    }

    /**
     * 在float数组中查找某一个元素出现的次数
     *
     * @param array byte数组对象
     * @param e     需要查找的元素
     * @return 该元素出现的次数，如果没有出现过则返回0
     * @since 1.0
     */
    public static int count(float[] array, float e) {
        int count = 0;
        if (isEmpty(array)) {
            return count;
        }
        for (float element : array) {
            if (e == element) {
                count++;
            }
        }
        return count;
    }

    /**
     * 在double数组中查找某一个元素出现的次数
     *
     * @param array double数组对象
     * @param e     需要查找的元素
     * @return 该元素出现的次数，如果没有出现过则返回0
     * @since 1.0
     */
    public static int count(double[] array, double e) {
        int count = 0;
        if (isEmpty(array)) {
            return count;
        }
        for (double element : array) {
            if (e == element) {
                count++;
            }
        }
        return count;
    }

    /*----------------------------- contains end ----------------------------------------*/

    /*----------------------------- find start ----------------------------------------*/

    /**
     * 查找元素首次出现的索引位置，从0开始。<br/>
     *
     * @param array 数组
     * @param e     待查找元素
     * @param <E>   元素类型
     * @return 如果存在返回其首次出现的位置，否则返回-1
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> int indexOf(E[] array, E e) {
        if (isEmpty(array)) {
            return -1;
        }
        if (e == null) {
            for (int i = 0, length = array.length; i < length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0, length = array.length; i < length; i++) {
                if (e.compareTo(array[i]) == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 查找元素首次出现的索引位置，从0开始。使用比较器进行对比。<br/>
     *
     * @param array      数组
     * @param e          待查找元素
     * @param <E>        元素类型
     * @param comparator 比较器
     * @return 如果存在返回其首次出现的位置，否则返回-1
     * @see Comparator#compare(Object, Object)
     * @since 1.0
     */
    public static <E> int indexOf(E[] array, Comparator<E> comparator, E e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (comparator.compare(e, array[i]) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找byte数组中某个元素首次出现的位置，从0开始。
     *
     * @param array byte数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其首次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int indexOf(byte[] array, byte e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找char数组中某个元素首次出现的位置，从0开始。
     *
     * @param array char数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其首次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int indexOf(char[] array, char e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找short数组中某个元素首次出现的位置，从0开始。
     *
     * @param array short数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其首次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int indexOf(short[] array, short e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找int数组中某个元素首次出现的位置，从0开始。
     *
     * @param array int数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其首次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int indexOf(int[] array, int e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找long数组中某个元素首次出现的位置，从0开始。
     *
     * @param array long数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其首次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int indexOf(long[] array, long e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找float数组中某个元素首次出现的位置，从0开始。
     *
     * @param array float数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其首次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int indexOf(float[] array, float e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找double数组中某个元素首次出现的位置，从0开始。
     *
     * @param array double数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其首次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int indexOf(double[] array, double e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找元素最后一次出现的索引位置，从0开始。<br/>
     *
     * @param array 数组
     * @param e     待查找元素
     * @param <E>   元素类型
     * @return 如果存在返回其最后一次出现的位置，否则返回-1
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> int lastIndexOf(E[] array, E e) {
        if (isEmpty(array)) {
            return -1;
        }
        if (e == null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = array.length - 1; i >= 0; i--) {
                if (e.compareTo(array[i]) == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 查找元素首次出现的索引位置，从0开始。使用比较器进行对比。<br/>
     *
     * @param array      数组
     * @param e          待查找元素
     * @param <E>        元素类型
     * @param comparator 比较器
     * @return 如果存在返回其最后一次出现的位置，否则返回-1
     * @see Comparator#compare(Object, Object)
     * @since 1.0
     */
    public static <E> int lastIndexOf(E[] array, Comparator<E> comparator, E e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            if (comparator.compare(e, array[i]) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找byte数组中某个元素最后一次出现的位置，从0开始。
     *
     * @param array byte数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其最后一次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int lastIndexOf(byte[] array, byte e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找char数组中某个元素最后一次出现的位置，从0开始。
     *
     * @param array char数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其最后一次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int lastIndexOf(char[] array, char e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找short数组中某个元素最后一次出现的位置，从0开始。
     *
     * @param array short数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其最后一次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int lastIndexOf(short[] array, short e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找int数组中某个元素最后一次出现的位置，从0开始。
     *
     * @param array int数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其最后一次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int lastIndexOf(int[] array, int e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找long数组中某个元素最后一次出现的位置，从0开始。
     *
     * @param array long数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其最后一次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int lastIndexOf(long[] array, long e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找float数组中某个元素最后一次出现的位置，从0开始。
     *
     * @param array float数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其最后一次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int lastIndexOf(float[] array, float e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找double数组中某个元素最后一次出现的位置，从0开始。
     *
     * @param array double数组对象
     * @param e     查找的元素
     * @return 如果元素存在则返回其最后一次出现的位置；否则返回-1
     * @since 1.0
     */
    public static int lastIndexOf(double[] array, double e) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            if (e == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /*----------------------------- find end ----------------------------------------*/

    /*----------------------------- compare start ----------------------------------------*/

    /**
     * 比较两个数组是否相等<br/>
     *
     * @param array 数组1
     * @param other 数组2
     * @param <E>   数组元素类型
     * @return 如果两个数组的元素一致，并且每一个元素都相等，则返回true；否则返回false
     * @see Object#equals(Object)
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> boolean equals(E[] array, E[] other) {
        if (array.length != other.length) {
            return false;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (array[i].compareTo(other[i]) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个数组是否相等<br/>
     * 内部需要使用比较器进行比较。
     *
     * @param comparator 比较器
     * @param array      数组1
     * @param other      数组2
     * @param <E>        数组元素类型
     * @return 如果两个数组的元素一致，并且每一个元素都相等，则返回true；否则返回false
     * @see Comparator#compare(Object, Object)
     * @since 1.0
     */
    public static <E> boolean equals(E[] array, Comparator<E> comparator, E[] other) {
        if (array.length != other.length) {
            return false;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (comparator.compare(array[i], (other[i])) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个byte数组是否相等<br/>
     *
     * @param array 数组1
     * @param other 数组2
     * @return 如果两个数组的元素一致，并且每一个元素都相等，则返回true；否则返回false
     * @see Object#equals(Object)
     * @since 1.0
     */
    public static boolean equals(byte[] array, byte[] other) {
        if (array.length != other.length) {
            return false;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (array[i] != other[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个char数组是否相等<br/>
     *
     * @param array 数组1
     * @param other 数组2
     * @return 如果两个数组的元素一致，并且每一个元素都相等，则返回true；否则返回false
     * @see Object#equals(Object)
     * @since 1.0
     */
    public static boolean equals(char[] array, char[] other) {
        if (array.length != other.length) {
            return false;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (array[i] != other[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个short数组是否相等<br/>
     *
     * @param array 数组1
     * @param other 数组2
     * @return 如果两个数组的元素一致，并且每一个元素都相等，则返回true；否则返回false
     * @see Object#equals(Object)
     * @since 1.0
     */
    public static boolean equals(short[] array, short[] other) {
        if (array.length != other.length) {
            return false;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (array[i] != other[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个int数组是否相等<br/>
     *
     * @param array 数组1
     * @param other 数组2
     * @return 如果两个数组的元素一致，并且每一个元素都相等，则返回true；否则返回false
     * @see Object#equals(Object)
     * @since 1.0
     */
    public static boolean equals(int[] array, int[] other) {
        if (array.length != other.length) {
            return false;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (array[i] != other[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个long数组是否相等<br/>
     *
     * @param array 数组1
     * @param other 数组2
     * @return 如果两个数组的元素一致，并且每一个元素都相等，则返回true；否则返回false
     * @see Object#equals(Object)
     * @since 1.0
     */
    public static boolean equals(long[] array, long[] other) {
        if (array.length != other.length) {
            return false;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (array[i] != other[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个float数组是否相等<br/>
     *
     * @param array 数组1
     * @param other 数组2
     * @return 如果两个数组的元素一致，并且每一个元素都相等，则返回true；否则返回false
     * @see Object#equals(Object)
     * @since 1.0
     */
    public static boolean equals(float[] array, float[] other) {
        if (array.length != other.length) {
            return false;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (array[i] != other[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个double数组是否相等<br/>
     *
     * @param array 数组1
     * @param other 数组2
     * @return 如果两个数组的元素一致，并且每一个元素都相等，则返回true；否则返回false
     * @see Object#equals(Object)
     * @since 1.0
     */
    public static boolean equals(double[] array, double[] other) {
        if (array.length != other.length) {
            return false;
        }
        for (int i = 0, length = array.length; i < length; i++) {
            if (array[i] != other[i]) {
                return false;
            }
        }
        return true;
    }

    /*----------------------------- compare end ----------------------------------------*/


    /*----------------------------- reverse start ----------------------------------------*/

    /**
     * 对原数组进行反转操作
     *
     * @param array 原数组
     * @param <E>   元素类型
     * @since 1.0
     */
    public static <E> void reverse(E[] array) {
        if (isEmpty(array)) {
            return;
        }
        int modVal = 2;
        int length = array.length;
        for (int i = 0, middleIndex = length / modVal; i < middleIndex; i++) {
            E temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
    }

    /**
     * 对byte数组进行反转操作
     *
     * @param array 原byte数组
     * @since 1.0
     */
    public static void reverse(byte[] array) {
        if (isEmpty(array)) {
            return;
        }
        int modVal = 2;
        int length = array.length;
        for (int i = 0, middleIndex = length / modVal; i < middleIndex; i++) {
            byte temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
    }

    /**
     * 对char数组进行反转操作
     *
     * @param array 原char数组
     * @since 1.0
     */
    public static void reverse(char[] array) {
        if (isEmpty(array)) {
            return;
        }
        int modVal = 2;
        int length = array.length;
        for (int i = 0, middleIndex = length / modVal; i < middleIndex; i++) {
            char temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
    }

    /**
     * 对short数组进行反转操作
     *
     * @param array 原short数组
     * @since 1.0
     */
    public static void reverse(short[] array) {
        if (isEmpty(array)) {
            return;
        }
        int modVal = 2;
        int length = array.length;
        for (int i = 0, middleIndex = length / modVal; i < middleIndex; i++) {
            short temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
    }

    /**
     * 对int数组进行反转操作
     *
     * @param array 原int数组
     * @since 1.0
     */
    public static void reverse(int[] array) {
        if (isEmpty(array)) {
            return;
        }
        int modVal = 2;
        int length = array.length;
        for (int i = 0, middleIndex = length / modVal; i < middleIndex; i++) {
            int temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
    }

    /**
     * 对long数组进行反转操作
     *
     * @param array 原long数组
     * @since 1.0
     */
    public static void reverse(long[] array) {
        if (isEmpty(array)) {
            return;
        }
        int modVal = 2;
        int length = array.length;
        for (int i = 0, middleIndex = length / modVal; i < middleIndex; i++) {
            long temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
    }

    /**
     * 对float数组进行反转操作
     *
     * @param array 原float数组
     * @since 1.0
     */
    public static void reverse(float[] array) {
        if (isEmpty(array)) {
            return;
        }
        int modVal = 2;
        int length = array.length;
        for (int i = 0, middleIndex = length / modVal; i < middleIndex; i++) {
            float temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
    }

    /**
     * 对double数组进行反转操作
     *
     * @param array 原double数组
     * @since 1.0
     */
    public static void reverse(double[] array) {
        if (isEmpty(array)) {
            return;
        }
        int modVal = 2;
        int length = array.length;
        for (int i = 0, middleIndex = length / modVal; i < middleIndex; i++) {
            double temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
    }

    /*----------------------------- reverse end ----------------------------------------*/


    /*----------------------------- extract start ----------------------------------------*/

    /**
     * 截取数组，并产生一个新的数组
     *
     * @param array 原数组
     * @param start 开始位置，从0开始
     * @param end   结束位置
     * @param <E>   元素类型
     * @return 子数组对象
     * @see Arrays#copyOfRange(Object[], int, int)
     * @since 1.0
     */
    public static <E> E[] subArray(E[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end + 1);
    }

    /**
     * 截取byte数组，并产生一个新的byte数组
     *
     * @param array 原数组
     * @param start 开始位置，从0开始
     * @param end   结束位置
     * @return 子数组对象
     * @see Arrays#copyOfRange(byte[], int, int)
     * @since 1.0
     */
    public static byte[] subArray(byte[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end + 1);
    }

    /**
     * 截取char数组，并产生一个新的char数组
     *
     * @param array 原数组
     * @param start 开始位置，从0开始
     * @param end   结束位置
     * @return 子数组对象
     * @see Arrays#copyOfRange(char[], int, int)
     * @since 1.0
     */
    public static char[] subArray(char[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end+1);
    }

    /**
     * 截取int数组，并产生一个新的int数组
     *
     * @param array 原数组
     * @param start 开始位置，从0开始
     * @param end   结束位置
     * @return 子数组对象
     * @see Arrays#copyOfRange(int[], int, int)
     */
    public static int[] subArray(int[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end+1);
    }

    /**
     * 截取short数组，并产生一个新的short数组
     *
     * @param array 原数组
     * @param start 开始位置，从0开始
     * @param end   结束位置
     * @return 子数组对象
     * @see Arrays#copyOfRange(short[], int, int)
     * @since 1.0
     */
    public static short[] subArray(short[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end+1);
    }

    /**
     * 截取long数组，并产生一个新的long数组
     *
     * @param array 原数组
     * @param start 开始位置，从0开始
     * @param end   结束位置
     * @return 子数组对象
     * @see Arrays#copyOfRange(long[], int, int)
     * @since 1.0
     */
    public static long[] subArray(long[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end+1);
    }

    /**
     * 截取float数组，并产生一个新的float数组
     *
     * @param array 原数组
     * @param start 开始位置，从0开始
     * @param end   结束位置
     * @return 子数组对象
     * @see Arrays#copyOfRange(float[], int, int)
     * @since 1.0
     */
    public static float[] subArray(float[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end+1);
    }

    /**
     * 截取double数组，并产生一个新的double数组
     *
     * @param array 原数组
     * @param start 开始位置，从0开始
     * @param end   结束位置
     * @return 子数组对象
     * @see Arrays#copyOfRange(double[], int, int)
     * @since 1.0
     */
    public static double[] subArray(double[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end+1);
    }

    /*----------------------------- extract end ----------------------------------------*/

    /*----------------------------- array calc start ----------------------------------------*/

    /**
     * 将两个数组连接起来
     *
     * @param arrayA 数组A
     * @param arrayB 数组B
     * @param <E>    数组元素类型
     * @return 连接后的新数组
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public static <E> E[] join(E[] arrayA, E[] arrayB) {
        int length = arrayA.length + arrayB.length;
        E[] newArray = (E[]) Array.newInstance(arrayB.getClass().getComponentType(), length);
        int j = 0;
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (j < arrayA.length) {
                newArray[i] = arrayA[j++];
            } else {
                newArray[i] = arrayB[k++];
            }
        }
        return newArray;
    }

    /**
     * 将两个byte数组连接起来
     *
     * @param arrayA byte数组A
     * @param arrayB byte数组B
     * @return 连接后的新byte数组
     * @since 1.0
     */
    public static byte[] join(byte[] arrayA, byte[] arrayB) {
        int length = arrayA.length + arrayB.length;
        byte[] newArray = new byte[length];
        int j = 0;
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (j < arrayA.length) {
                newArray[i] = arrayA[j++];
            } else {
                newArray[i] = arrayB[k++];
            }
        }
        return newArray;
    }

    /**
     * 将两个char数组连接起来
     *
     * @param arrayA char数组A
     * @param arrayB char数组B
     * @return 连接后的新char数组
     * @since 1.0
     */
    public static char[] join(char[] arrayA, char[] arrayB) {
        int length = arrayA.length + arrayB.length;
        char[] newArray = new char[length];
        int j = 0;
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (j < arrayA.length) {
                newArray[i] = arrayA[j++];
            } else {
                newArray[i] = arrayB[k++];
            }
        }
        return newArray;
    }


    /**
     * 将两个short数组连接起来
     *
     * @param arrayA short数组A
     * @param arrayB short数组B
     * @return 连接后的新short数组
     * @since 1.0
     */
    public static short[] join(short[] arrayA, short[] arrayB) {
        int length = arrayA.length + arrayB.length;
        short[] newArray = new short[length];
        int j = 0;
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (j < arrayA.length) {
                newArray[i] = arrayA[j++];
            } else {
                newArray[i] = arrayB[k++];
            }
        }
        return newArray;
    }

    /**
     * 将两个int数组连接起来
     *
     * @param arrayA int数组A
     * @param arrayB int数组B
     * @return 连接后的新int数组
     * @since 1.0
     */
    public static int[] join(int[] arrayA, int[] arrayB) {
        int length = arrayA.length + arrayB.length;
        int[] newArray = new int[length];
        int j = 0;
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (j < arrayA.length) {
                newArray[i] = arrayA[j++];
            } else {
                newArray[i] = arrayB[k++];
            }
        }
        return newArray;
    }

    /**
     * 将两个long数组连接起来
     *
     * @param arrayA long数组A
     * @param arrayB long数组B
     * @return 连接后的新long数组
     * @since 1.0
     */
    public static long[] join(long[] arrayA, long[] arrayB) {
        int length = arrayA.length + arrayB.length;
        long[] newArray = new long[length];
        int j = 0;
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (j < arrayA.length) {
                newArray[i] = arrayA[j++];
            } else {
                newArray[i] = arrayB[k++];
            }
        }
        return newArray;
    }

    /**
     * 将两个float数组连接起来
     *
     * @param arrayA float数组A
     * @param arrayB float数组B
     * @return 连接后的新float数组
     * @since 1.0
     */
    public static float[] join(float[] arrayA, float[] arrayB) {
        int length = arrayA.length + arrayB.length;
        float[] newArray = new float[length];
        int j = 0;
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (j < arrayA.length) {
                newArray[i] = arrayA[j++];
            } else {
                newArray[i] = arrayB[k++];
            }
        }
        return newArray;
    }

    /**
     * 将两个double数组连接起来
     *
     * @param arrayA double数组A
     * @param arrayB double数组B
     * @return 连接后的新double数组
     * @since 1.0
     */
    public static double[] join(double[] arrayA, double[] arrayB) {
        int length = arrayA.length + arrayB.length;
        double[] newArray = new double[length];
        int j = 0;
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (j < arrayA.length) {
                newArray[i] = arrayA[j++];
            } else {
                newArray[i] = arrayB[k++];
            }
        }
        return newArray;
    }

    /**
     * 将两个数组进行合并，得出两个数组的合集，并去重。等同于并集（A∪B）
     *
     * @see #merge(Object[], Object[], Comparator)
     * @see #merge(Comparable[], Comparable[])
     */
    @SuppressWarnings("unchecked")
    private static <E> E[] merge0(E[] arrayA, E[] arrayB, Comparator<E> comparator) {
        int leftLength = arrayA.length;
        int rightLength = arrayB.length;
        // B数组中重复元素标记
        boolean[] dupleFlag = new boolean[rightLength];
        // B数组中重复元素数量
        int dupleCount = 0;
        for (int i = 0; i < rightLength; i++) {
            int firstIndex = comparator != null ? indexOf(arrayA, comparator, arrayB[i]) : indexOf((Comparable[]) arrayA, (Comparable) arrayB[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 数组A长度 - 重复元素个数 + 数组B长度
        int length = leftLength + rightLength - dupleCount;
        //使用数组的真实类型实例化一个新数组
        E[] newArray = (E[]) Array.newInstance(arrayA.getClass().getComponentType(), length);
        int i = 0;
        int j = 0;
        while (i < length) {
            if (i < leftLength) {
                newArray[i] = arrayA[i++];
            } else {
                if (dupleFlag[j]) {
                    j++;
                    continue;
                }
                newArray[i++] = arrayB[j++];
            }
        }
        return newArray;
    }

    /**
     * 将两个数组进行合并，得出两个数组的合集，并去重（通过比较器进行对比）。等同于并集（A∪B）。
     *
     * @param arrayA     数组A
     * @param arrayB     数组B
     * @param comparator 比较器
     * @param <E>        元素类型
     * @return 并集之后的新数组
     * @see Comparator#compare(Object, Object)
     * @since 1.0
     */
    public static <E> E[] merge(E[] arrayA, E[] arrayB, Comparator<E> comparator) {
        return merge0(arrayA, arrayB, comparator);
    }

    /**
     * 将两个数组进行合并，得出两个数组的合集，并去重（通过equals方法进行对比）。等同于并集（A∪B）。
     *
     * @param arrayA 数组A
     * @param arrayB 数组B
     * @param <E>    元素类型
     * @return 并集之后的新数组
     * @see Object#equals(Object)
     */
    public static <E extends Comparable<? super E>> E[] merge(E[] arrayA, E[] arrayB) {
        return merge0(arrayA, arrayB, null);
    }

    /**
     * 将两个byte数组进行合并，得出两个数组的合集，并去重。等同于并集（A∪B）。
     *
     * @param arrayA byte数组A
     * @param arrayB byte数组B
     * @return 并集之后的新byte数组
     * @since 1.0
     */
    public static byte[] merge(byte[] arrayA, byte[] arrayB) {
        int leftLength = arrayA.length;
        int rightLength = arrayB.length;
        // B数组中重复元素标记
        boolean[] dupleFlag = new boolean[rightLength];
        // B数组中重复元素数量
        int dupleCount = 0;
        for (int i = 0; i < rightLength; i++) {
            int firstIndex = indexOf(arrayA, arrayB[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 数组A长度 - 重复元素个数 + 数组B长度
        int length = leftLength + rightLength - dupleCount;
        //使用数组的真实类型实例化一个新数组
        byte[] newArray = new byte[length];
        int i = 0;
        int j = 0;
        while (i < length) {
            if (i < leftLength) {
                newArray[i] = arrayA[i++];
            } else {
                if (dupleFlag[j]) {
                    j++;
                    continue;
                }
                newArray[i++] = arrayB[j++];
            }
        }
        return newArray;
    }

    /**
     * 将两个char数组进行合并，得出两个数组的合集，并去重。等同于并集（A∪B）。
     *
     * @param arrayA char数组A
     * @param arrayB char数组B
     * @return 并集之后的新char数组
     * @since 1.0
     */
    public static char[] merge(char[] arrayA, char[] arrayB) {
        int leftLength = arrayA.length;
        int rightLength = arrayB.length;
        // B数组中重复元素标记
        boolean[] dupleFlag = new boolean[rightLength];
        // B数组中重复元素数量
        int dupleCount = 0;
        for (int i = 0; i < rightLength; i++) {
            int firstIndex = indexOf(arrayA, arrayB[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 数组A长度 - 重复元素个数 + 数组B长度
        int length = leftLength + rightLength - dupleCount;
        //使用数组的真实类型实例化一个新数组
        char[] newArray = new char[length];
        int i = 0;
        int j = 0;
        while (i < length) {
            if (i < leftLength) {
                newArray[i] = arrayA[i++];
            } else {
                if (dupleFlag[j]) {
                    j++;
                    continue;
                }
                newArray[i++] = arrayB[j++];
            }
        }
        return newArray;
    }

    /**
     * 将两个short数组进行合并，得出两个数组的合集，并去重。等同于并集（A∪B）。
     *
     * @param arrayA short数组A
     * @param arrayB short数组B
     * @return 并集之后的新short数组
     * @since 1.0
     */
    public static short[] merge(short[] arrayA, short[] arrayB) {
        int leftLength = arrayA.length;
        int rightLength = arrayB.length;
        // B数组中重复元素标记
        boolean[] dupleFlag = new boolean[rightLength];
        // B数组中重复元素数量
        int dupleCount = 0;
        for (int i = 0; i < rightLength; i++) {
            int firstIndex = indexOf(arrayA, arrayB[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 数组A长度 - 重复元素个数 + 数组B长度
        int length = leftLength + rightLength - dupleCount;
        //使用数组的真实类型实例化一个新数组
        short[] newArray = new short[length];
        int i = 0;
        int j = 0;
        while (i < length) {
            if (i < leftLength) {
                newArray[i] = arrayA[i++];
            } else {
                if (dupleFlag[j]) {
                    j++;
                    continue;
                }
                newArray[i++] = arrayB[j++];
            }
        }
        return newArray;
    }

    /**
     * 将两个int数组进行合并，得出两个数组的合集，并去重。等同于并集（A∪B）。
     *
     * @param arrayA int数组A
     * @param arrayB int数组B
     * @return 并集之后的新int数组
     * @since 1.0
     */
    public static int[] merge(int[] arrayA, int[] arrayB) {
        int leftLength = arrayA.length;
        int rightLength = arrayB.length;
        // B数组中重复元素标记
        boolean[] dupleFlag = new boolean[rightLength];
        // B数组中重复元素数量
        int dupleCount = 0;
        for (int i = 0; i < rightLength; i++) {
            int firstIndex = indexOf(arrayA, arrayB[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 数组A长度 - 重复元素个数 + 数组B长度
        int length = leftLength + rightLength - dupleCount;
        //使用数组的真实类型实例化一个新数组
        int[] newArray = new int[length];
        int i = 0;
        int j = 0;
        while (i < length) {
            if (i < leftLength) {
                newArray[i] = arrayA[i++];
            } else {
                if (dupleFlag[j]) {
                    j++;
                    continue;
                }
                newArray[i++] = arrayB[j++];
            }
        }
        return newArray;
    }

    /**
     * 将两个long数组进行合并，得出两个数组的合集，并去重。等同于并集（A∪B）。
     *
     * @param arrayA long数组A
     * @param arrayB long数组B
     * @return 并集之后的新long数组
     * @since 1.0
     */
    public static long[] merge(long[] arrayA, long[] arrayB) {
        int leftLength = arrayA.length;
        int rightLength = arrayB.length;
        // B数组中重复元素标记
        boolean[] dupleFlag = new boolean[rightLength];
        // B数组中重复元素数量
        int dupleCount = 0;
        for (int i = 0; i < rightLength; i++) {
            int firstIndex = indexOf(arrayA, arrayB[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 数组A长度 - 重复元素个数 + 数组B长度
        int length = leftLength + rightLength - dupleCount;
        //使用数组的真实类型实例化一个新数组
        long[] newArray = new long[length];
        int i = 0;
        int j = 0;
        while (i < length) {
            if (i < leftLength) {
                newArray[i] = arrayA[i++];
            } else {
                if (dupleFlag[j]) {
                    j++;
                    continue;
                }
                newArray[i++] = arrayB[j++];
            }
        }
        return newArray;
    }

    /**
     * 将两个float数组进行合并，得出两个数组的合集，并去重。等同于并集（A∪B）。
     *
     * @param arrayA float数组A
     * @param arrayB float数组B
     * @return 并集之后的新float数组
     * @since 1.0
     */
    public static float[] merge(float[] arrayA, float[] arrayB) {
        int leftLength = arrayA.length;
        int rightLength = arrayB.length;
        // B数组中重复元素标记
        boolean[] dupleFlag = new boolean[rightLength];
        // B数组中重复元素数量
        int dupleCount = 0;
        for (int i = 0; i < rightLength; i++) {
            int firstIndex = indexOf(arrayA, arrayB[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 数组A长度 - 重复元素个数 + 数组B长度
        int length = leftLength + rightLength - dupleCount;
        //使用数组的真实类型实例化一个新数组
        float[] newArray = new float[length];
        int i = 0;
        int j = 0;
        while (i < length) {
            if (i < leftLength) {
                newArray[i] = arrayA[i++];
            } else {
                if (dupleFlag[j]) {
                    j++;
                    continue;
                }
                newArray[i++] = arrayB[j++];
            }
        }
        return newArray;
    }

    /**
     * 将两个double数组进行合并，得出两个数组的合集，并去重。等同于并集（A∪B）。
     *
     * @param arrayA double数组A
     * @param arrayB double数组B
     * @return 并集之后的新double数组
     * @since 1.0
     */
    public static double[] merge(double[] arrayA, double[] arrayB) {
        int leftLength = arrayA.length;
        int rightLength = arrayB.length;
        // B数组中重复元素标记
        boolean[] dupleFlag = new boolean[rightLength];
        // B数组中重复元素数量
        int dupleCount = 0;
        for (int i = 0; i < rightLength; i++) {
            int firstIndex = indexOf(arrayA, arrayB[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 数组A长度 - 重复元素个数 + 数组B长度
        int length = leftLength + rightLength - dupleCount;
        //使用数组的真实类型实例化一个新数组
        double[] newArray = new double[length];
        int i = 0;
        int j = 0;
        while (i < length) {
            if (i < leftLength) {
                newArray[i] = arrayA[i++];
            } else {
                if (dupleFlag[j]) {
                    j++;
                    continue;
                }
                newArray[i++] = arrayB[j++];
            }
        }
        return newArray;
    }

    /**
     * 取两个数组共同的元素，得出两个数组的交集。等同于交集（A∩B）
     *
     * @see #cross(Object[], Object[], Comparator)
     * @see #cross(Comparable[], Comparable[])
     */
    @SuppressWarnings("unchecked")
    private static <E> E[] cross0(E[] arrayA, E[] arrayB, Comparator<E> comparator) {
        int leftLength = arrayA.length;
        // A数组中相同元素标记
        boolean[] dupleFlag = new boolean[leftLength];
        // A数组中相同元素数量
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = comparator != null ? indexOf(arrayB, comparator, arrayA[i]) : indexOf((Comparable[]) arrayB, (Comparable) arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 相同元素个数
        int length = dupleCount;
        //使用数组的真实类型实例化一个新数组
        E[] newArray = (E[]) Array.newInstance(arrayA.getClass().getComponentType(), length);
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 取两个数组共同（使用比较器进行比较）的元素，得出两个数组的交集。等同于交集（A∩B）
     *
     * @param arrayA     数组A
     * @param arrayB     数组B
     * @param comparator 比较器
     * @param <E>        元素类型
     * @return 两个数组共同元素组成的新数组
     * @see Comparator#compare(Object, Object)
     * @since 1.0
     */
    public static <E> E[] cross(E[] arrayA, E[] arrayB, Comparator<E> comparator) {
        return cross0(arrayA, arrayB, comparator);
    }

    /**
     * 取两个数组共同（使用equals进行比较）的元素，得出两个数组的交集。等同于交集（A∩B）
     *
     * @param arrayA 数组A
     * @param arrayB 数组B
     * @param <E>    元素类型
     * @return 两个数组共同元素组成的新数组
     * @see Object#equals(Object)
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> E[] cross(E[] arrayA, E[] arrayB) {
        return cross0(arrayA, arrayB, null);
    }

    /**
     * 取两个byte数组共同的元素，得出两个数组的交集。等同于交集（A∩B）
     *
     * @param arrayA byte数组A
     * @param arrayB byte数组B
     * @return 两个数组共同元素组成的新byte数组
     * @since 1.0
     */
    public static byte[] cross(byte[] arrayA, byte[] arrayB) {
        int leftLength = arrayA.length;
        // A数组中相同元素标记
        boolean[] dupleFlag = new boolean[leftLength];
        // A数组中相同元素数量
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 相同元素个数
        int length = dupleCount;
        //使用数组的真实类型实例化一个新数组
        byte[] newArray = new byte[length];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 取两个char数组共同的元素，得出两个数组的交集。等同于交集（A∩B）
     *
     * @param arrayA char数组A
     * @param arrayB char数组B
     * @return 两个数组共同元素组成的新char数组
     * @since 1.0
     */
    public static char[] cross(char[] arrayA, char[] arrayB) {
        int leftLength = arrayA.length;
        // A数组中相同元素标记
        boolean[] dupleFlag = new boolean[leftLength];
        // A数组中相同元素数量
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 相同元素个数
        int length = dupleCount;
        //使用数组的真实类型实例化一个新数组
        char[] newArray = new char[length];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 取两个short数组共同的元素，得出两个数组的交集。等同于交集（A∩B）
     *
     * @param arrayA short数组A
     * @param arrayB short数组B
     * @return 两个数组共同元素组成的新short数组
     * @since 1.0
     */
    public static short[] cross(short[] arrayA, short[] arrayB) {
        int leftLength = arrayA.length;
        // A数组中相同元素标记
        boolean[] dupleFlag = new boolean[leftLength];
        // A数组中相同元素数量
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 相同元素个数
        int length = dupleCount;
        //使用数组的真实类型实例化一个新数组
        short[] newArray = new short[length];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 取两个int数组共同的元素，得出两个数组的交集。等同于交集（A∩B）
     *
     * @param arrayA int数组A
     * @param arrayB int数组B
     * @return 两个数组共同元素组成的新int数组
     * @since 1.0
     */
    public static int[] cross(int[] arrayA, int[] arrayB) {
        int leftLength = arrayA.length;
        // A数组中相同元素标记
        boolean[] dupleFlag = new boolean[leftLength];
        // A数组中相同元素数量
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 相同元素个数
        int length = dupleCount;
        //使用数组的真实类型实例化一个新数组
        int[] newArray = new int[length];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 取两个long数组共同的元素，得出两个数组的交集。等同于交集（A∩B）
     *
     * @param arrayA long数组A
     * @param arrayB long数组B
     * @return 两个数组共同元素组成的新long数组
     * @since 1.0
     */
    public static long[] cross(long[] arrayA, long[] arrayB) {
        int leftLength = arrayA.length;
        // A数组中相同元素标记
        boolean[] dupleFlag = new boolean[leftLength];
        // A数组中相同元素数量
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 相同元素个数
        int length = dupleCount;
        //使用数组的真实类型实例化一个新数组
        long[] newArray = new long[length];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 取两个float数组共同的元素，得出两个数组的交集。等同于交集（A∩B）
     *
     * @param arrayA float数组A
     * @param arrayB float数组B
     * @return 两个数组共同元素组成的新float数组
     * @since 1.0
     */
    public static float[] cross(float[] arrayA, float[] arrayB) {
        int leftLength = arrayA.length;
        // A数组中相同元素标记
        boolean[] dupleFlag = new boolean[leftLength];
        // A数组中相同元素数量
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 相同元素个数
        int length = dupleCount;
        //使用数组的真实类型实例化一个新数组
        float[] newArray = new float[length];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 取两个double数组共同的元素，得出两个数组的交集。等同于交集（A∩B）
     *
     * @param arrayA double数组A
     * @param arrayB double数组B
     * @return 两个数组共同元素组成的新double数组
     * @since 1.0
     */
    public static double[] cross(double[] arrayA, double[] arrayB) {
        int leftLength = arrayA.length;
        // A数组中相同元素标记
        boolean[] dupleFlag = new boolean[leftLength];
        // A数组中相同元素数量
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
                dupleCount++;
            }
        }
        // 新数组的长度 = 相同元素个数
        int length = dupleCount;
        //使用数组的真实类型实例化一个新数组
        double[] newArray = new double[length];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 在一个数组中移除包含在另一数组中的元素，并得出两个数组的差集。等同于差集（A－B）
     *
     * @return 数组A排除在数组B中的元素后的新数组
     * @see #differ(Object[], Object[], Comparator)
     * @see #differ(Comparable[], Comparable[])
     */
    @SuppressWarnings("unchecked")
    private static <E> E[] differ0(E[] arrayA, E[] arrayB, Comparator<E> comparator) {
        int leftLength = arrayA.length;
        boolean[] dupleFlag = new boolean[leftLength];
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = comparator != null ? indexOf(arrayB, comparator, arrayA[i]) : indexOf((Comparable[]) arrayB, (Comparable) arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
            } else {
                dupleCount++;
            }
        }
        E[] newArray = (E[]) Array.newInstance(arrayA.getClass().getComponentType(), dupleCount);
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (!dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 在一个数组中移除包含在另一数组中的元素（使用比较器进行比较），并得出两个数组的差集。等同于差集（A－B）
     *
     * @param arrayA     数组A
     * @param arrayB     数组B
     * @param comparator 比较器
     * @param <E>        数组元素类型
     * @return 数组A排除在数组B中的元素后的新数组
     * @since 1.0
     */
    public static <E> E[] differ(E[] arrayA, E[] arrayB, Comparator<E> comparator) {
        return differ0(arrayA, arrayB, comparator);
    }

    /**
     * 在一个数组中移除包含在另一数组中的元素（使用equals进行比较），并得出两个数组的差集。等同于差集（A－B）
     *
     * @param arrayA 数组A
     * @param arrayB 数组B
     * @param <E>    数组元素类型
     * @return 数组A排除在数组B中的元素后的新数组
     */
    public static <E extends Comparable<? super E>> E[] differ(E[] arrayA, E[] arrayB) {
        return differ0(arrayA, arrayB, null);
    }

    /**
     * 在一个byte数组中移除包含在另一byte数组中的元素，并得出两个数组的差集。等同于差集（A－B）
     *
     * @param arrayA byte数组A
     * @param arrayB byte数组B
     * @return 数组A排除在数组B中的元素后的新byte数组
     * @since 1.0
     */
    public static byte[] differ(byte[] arrayA, byte[] arrayB) {
        int leftLength = arrayA.length;
        boolean[] dupleFlag = new boolean[leftLength];
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
            } else {
                dupleCount++;
            }
        }
        byte[] newArray = new byte[dupleCount];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (!dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 在一个char数组中移除包含在另一char数组中的元素，并得出两个数组的差集。等同于差集（A－B）
     *
     * @param arrayA char数组A
     * @param arrayB char数组B
     * @return 数组A排除在数组B中的元素后的新char数组
     * @since 1.0
     */
    public static char[] differ(char[] arrayA, char[] arrayB) {
        int leftLength = arrayA.length;
        boolean[] dupleFlag = new boolean[leftLength];
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
            } else {
                dupleCount++;
            }
        }
        char[] newArray = new char[dupleCount];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (!dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 在一个short数组中移除包含在另一short数组中的元素，并得出两个数组的差集。等同于差集（A－B）
     *
     * @param arrayA short数组A
     * @param arrayB short数组B
     * @return 数组A排除在数组B中的元素后的新short数组
     * @since 1.0
     */
    public static short[] differ(short[] arrayA, short[] arrayB) {
        int leftLength = arrayA.length;
        boolean[] dupleFlag = new boolean[leftLength];
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
            } else {
                dupleCount++;
            }
        }
        short[] newArray = new short[dupleCount];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (!dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 在一个int数组中移除包含在另一int数组中的元素，并得出两个数组的差集。等同于差集（A－B）
     *
     * @param arrayA int数组A
     * @param arrayB int数组B
     * @return 数组A排除在数组B中的元素后的新int数组
     * @since 1.0
     */
    public static int[] differ(int[] arrayA, int[] arrayB) {
        int leftLength = arrayA.length;
        boolean[] dupleFlag = new boolean[leftLength];
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
            } else {
                dupleCount++;
            }
        }
        int[] newArray = new int[dupleCount];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (!dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 在一个long数组中移除包含在另一long数组中的元素，并得出两个数组的差集。等同于差集（A－B）
     *
     * @param arrayA long数组A
     * @param arrayB long数组B
     * @return 数组A排除在数组B中的元素后的新long数组
     * @since 1.0
     */
    public static long[] differ(long[] arrayA, long[] arrayB) {
        int leftLength = arrayA.length;
        boolean[] dupleFlag = new boolean[leftLength];
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
            } else {
                dupleCount++;
            }
        }
        long[] newArray = new long[dupleCount];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (!dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 在一个float数组中移除包含在另一float数组中的元素，并得出两个数组的差集。等同于差集（A－B）
     *
     * @param arrayA float数组A
     * @param arrayB float数组B
     * @return 数组A排除在数组B中的元素后的新float数组
     * @since 1.0
     */
    public static float[] differ(float[] arrayA, float[] arrayB) {
        int leftLength = arrayA.length;
        boolean[] dupleFlag = new boolean[leftLength];
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
            } else {
                dupleCount++;
            }
        }
        float[] newArray = new float[dupleCount];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (!dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 在一个double数组中移除包含在另一double数组中的元素，并得出两个数组的差集。等同于差集（A－B）
     *
     * @param arrayA double数组A
     * @param arrayB double数组B
     * @return 数组A排除在数组B中的元素后的新double数组
     * @since 1.0
     */
    public static double[] differ(double[] arrayA, double[] arrayB) {
        int leftLength = arrayA.length;
        boolean[] dupleFlag = new boolean[leftLength];
        int dupleCount = 0;
        for (int i = 0; i < leftLength; i++) {
            int firstIndex = indexOf(arrayB, arrayA[i]);
            if (firstIndex >= 0) {
                dupleFlag[i] = true;
            } else {
                dupleCount++;
            }
        }
        double[] newArray = new double[dupleCount];
        if (dupleCount == 0) {
            return newArray;
        } else {
            int i = 0;
            for (int k = 0; k < leftLength; k++) {
                if (!dupleFlag[k]) {
                    newArray[i++] = arrayA[k];
                }
            }
        }
        return newArray;
    }

    /**
     * 排除两个数组共同（使用比较器进行比较）的元素，并得出两个数组的对称差集。等同于对称差集（(A－B)∪(B－A)）
     *
     * @param arrayA     数组A
     * @param arrayB     数组B
     * @param comparator 比较器
     * @param <E>        元素类型
     * @return 两个数组共同元素以外的元素组成的新数组
     * @see #differ(Object[], Object[], Comparator)
     * @since 1.0
     */
    public static <E> E[] unCross(E[] arrayA, E[] arrayB, Comparator<E> comparator) {
        E[] array1 = differ(arrayA, arrayB, comparator);
        E[] array2 = differ(arrayB, arrayA, comparator);
        return join(array1, array2);
    }

    /**
     * 排除两个数组共同（使用equals进行比较）的元素，并得出两个数组的对称差集。等同于对称差集（(A－B)∪(B－A)）
     *
     * @param arrayA 数组A
     * @param arrayB 数组B
     * @param <E>    元素类型
     * @return 两个数组共同元素以外的元素组成的新数组
     * @see #differ(Comparable[], Comparable[])
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> E[] unCross(E[] arrayA, E[] arrayB) {
        E[] array1 = differ(arrayA, arrayB);
        E[] array2 = differ(arrayB, arrayA);
        return join(array1, array2);
    }

    /**
     * 排除两个byte数组共同（使用equals进行比较）的元素，并得出两个数组的对称差集。等同于对称差集（(A－B)∪(B－A)）
     *
     * @param arrayA byte数组A
     * @param arrayB byte数组B
     * @return 两个数组共同元素以外的元素组成的新byte数组
     * @see #differ(byte[], byte[])
     * @since 1.0
     */
    public static byte[] unCross(byte[] arrayA, byte[] arrayB) {
        byte[] array1 = differ(arrayA, arrayB);
        byte[] array2 = differ(arrayB, arrayA);
        return join(array1, array2);
    }

    /**
     * 排除两个char数组共同的元素，并得出两个数组的对称差集。等同于对称差集（(A－B)∪(B－A)）
     *
     * @param arrayA char数组A
     * @param arrayB char数组B
     * @return 两个数组共同元素以外的元素组成的新char数组
     * @see #differ(char[], char[])
     * @since 1.0
     */
    public static char[] unCross(char[] arrayA, char[] arrayB) {
        char[] array1 = differ(arrayA, arrayB);
        char[] array2 = differ(arrayB, arrayA);
        return join(array1, array2);
    }

    /**
     * 排除两个short数组共同的元素，并得出两个数组的对称差集。等同于对称差集（(A－B)∪(B－A)）
     *
     * @param arrayA short数组A
     * @param arrayB short数组B
     * @return 两个数组共同元素以外的元素组成的新short数组
     * @see #differ(short[], short[])
     * @since 1.0
     */
    public static short[] unCross(short[] arrayA, short[] arrayB) {
        short[] array1 = differ(arrayA, arrayB);
        short[] array2 = differ(arrayB, arrayA);
        return join(array1, array2);
    }

    /**
     * 排除两个short数组共同的元素，并得出两个数组的对称差集。等同于对称差集（(A－B)∪(B－A)）
     *
     * @param arrayA int数组A
     * @param arrayB int数组B
     * @return 两个数组共同元素以外的元素组成的新int数组
     * @see #differ(int[], int[])
     * @since 1.0
     */
    public static int[] unCross(int[] arrayA, int[] arrayB) {
        int[] array1 = differ(arrayA, arrayB);
        int[] array2 = differ(arrayB, arrayA);
        return join(array1, array2);
    }

    /**
     * 排除两个long数组共同的元素，并得出两个数组的对称差集。等同于对称差集（(A－B)∪(B－A)）
     *
     * @param arrayA long数组A
     * @param arrayB long数组B
     * @return 两个数组共同元素以外的元素组成的新long数组
     * @see #differ(long[], long[])
     * @since 1.0
     */
    public static long[] unCross(long[] arrayA, long[] arrayB) {
        long[] array1 = differ(arrayA, arrayB);
        long[] array2 = differ(arrayB, arrayA);
        return join(array1, array2);
    }

    /**
     * 排除两个float数组共同的元素，并得出两个数组的对称差集。等同于对称差集（(A－B)∪(B－A)）
     *
     * @param arrayA float数组A
     * @param arrayB float数组B
     * @return 两个数组共同元素以外的元素组成的新float数组
     * @see #differ(float[], float[])
     * @since 1.0
     */
    public static float[] unCross(float[] arrayA, float[] arrayB) {
        float[] array1 = differ(arrayA, arrayB);
        float[] array2 = differ(arrayB, arrayA);
        return join(array1, array2);
    }

    /**
     * 排除两个double数组共同的元素，并得出两个数组的对称差集。等同于对称差集（(A－B)∪(B－A)）
     *
     * @param arrayA double数组A
     * @param arrayB double数组B
     * @return 两个数组共同元素以外的元素组成的新double数组
     * @see #differ(double[], double[])
     * @since 1.0
     */
    public static double[] unCross(double[] arrayA, double[] arrayB) {
        double[] array1 = differ(arrayA, arrayB);
        double[] array2 = differ(arrayB, arrayA);
        return join(array1, array2);
    }

    /*----------------------------- unite calc end ----------------------------------------*/


}
