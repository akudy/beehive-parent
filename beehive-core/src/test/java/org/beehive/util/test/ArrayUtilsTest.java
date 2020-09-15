/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.test.ArrayUtilsTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-14
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.util.test;

import org.beehive.core.convert.ConvertException;
import org.beehive.core.convert.Converter;
import org.beehive.util.ArrayUtils;
import org.junit.Test;

import java.util.*;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.util.test</code></li>
 * <li>Class Name: <code>ArrayUtilsTest</code></li>
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
 * <td align="center"><em>2020/8/20</em></td>
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
public class ArrayUtilsTest {

    @Test
    public void nullAndEmptyTest() {
        String[] strArray = {"AB", "AC", "AD"};
        System.out.println(String.format("isNull(E[]) params = (%s), result = %s", Arrays.toString(strArray), ArrayUtils.isNull(strArray)));
        System.out.println(String.format("isNotNull(E[]) params = (%s), result = %s", Arrays.toString(strArray), ArrayUtils.isNotNull(strArray)));
        System.out.println(String.format("isEmpty(E[]) params = (%s), result = %s", Arrays.toString(strArray), ArrayUtils.isEmpty(strArray)));
        System.out.println(String.format("isNotEmpty(E[]) params = (%s), result = %s", Arrays.toString(strArray), ArrayUtils.isNotEmpty(strArray)));

        byte[] byteArray = {};
        System.out.println(String.format("isNull(byte[]) params = (%s), result = %s", Arrays.toString(byteArray), ArrayUtils.isNull(byteArray)));
        System.out.println(String.format("isNotNull(byte[]) params = (%s), result = %s", Arrays.toString(byteArray), ArrayUtils.isNotNull(byteArray)));
        System.out.println(String.format("isEmpty(byte[]) params = (%s), result = %s", Arrays.toString(byteArray), ArrayUtils.isEmpty(byteArray)));
        System.out.println(String.format("isNotEmpty(byte[]) params = (%s), result = %s", Arrays.toString(byteArray), ArrayUtils.isNotEmpty(byteArray)));

        char[] charArray = {'A', 'B'};
        System.out.println(String.format("isNull(char[]) params = (%s), result = %s", Arrays.toString(charArray), ArrayUtils.isNull(charArray)));
        System.out.println(String.format("isNotNull(char[]) params = (%s), result = %s", Arrays.toString(charArray), ArrayUtils.isNotNull(charArray)));
        System.out.println(String.format("isEmpty(char[]) params = (%s), result = %s", Arrays.toString(charArray), ArrayUtils.isEmpty(charArray)));
        System.out.println(String.format("isNotEmpty(char[]) params = (%s), result = %s", Arrays.toString(charArray), ArrayUtils.isNotEmpty(charArray)));

        short[] shortArray = {1, 2};
        System.out.println(String.format("isNull(short[]) params = (%s), result = %s", Arrays.toString(shortArray), ArrayUtils.isNull(shortArray)));
        System.out.println(String.format("isNotNull(short[]) params = (%s), result = %s", Arrays.toString(shortArray), ArrayUtils.isNotNull(shortArray)));
        System.out.println(String.format("isEmpty(short[]) params = (%s), result = %s", Arrays.toString(shortArray), ArrayUtils.isEmpty(shortArray)));
        System.out.println(String.format("isNotEmpty(short[]) params = (%s), result = %s", Arrays.toString(shortArray), ArrayUtils.isNotEmpty(shortArray)));

        int[] intArray = {1, 2, 3, 5};
        System.out.println(String.format("isNull(int[]) params = (%s), result = %s", Arrays.toString(intArray), ArrayUtils.isNull(intArray)));
        System.out.println(String.format("isNotNull(int[]) params = (%s), result = %s", Arrays.toString(intArray), ArrayUtils.isNotNull(intArray)));
        System.out.println(String.format("isEmpty(int[]) params = (%s), result = %s", Arrays.toString(intArray), ArrayUtils.isEmpty(intArray)));
        System.out.println(String.format("isNotEmpty(int[]) params = (%s), result = %s", Arrays.toString(intArray), ArrayUtils.isNotEmpty(intArray)));

        long[] longArray = null;
        System.out.println(String.format("isNull(long[]) params = (%s), result = %s", Arrays.toString(longArray), ArrayUtils.isNull(longArray)));
        System.out.println(String.format("isNotNull(long[]) params = (%s), result = %s", Arrays.toString(longArray), ArrayUtils.isNotNull(longArray)));
        System.out.println(String.format("isEmpty(long[]) params = (%s), result = %s", Arrays.toString(longArray), ArrayUtils.isEmpty(longArray)));
        System.out.println(String.format("isNotEmpty(long[]) params = (%s), result = %s", Arrays.toString(longArray), ArrayUtils.isNotEmpty(longArray)));

        float[] floatArray = {1.1f, 1.2f};
        System.out.println(String.format("isNull(float[]) params = (%s), result = %s", Arrays.toString(floatArray), ArrayUtils.isNull(floatArray)));
        System.out.println(String.format("isNotNull(float[]) params = (%s), result = %s", Arrays.toString(floatArray), ArrayUtils.isNotNull(floatArray)));
        System.out.println(String.format("isEmpty(float[]) params = (%s), result = %s", Arrays.toString(floatArray), ArrayUtils.isEmpty(floatArray)));
        System.out.println(String.format("isNotEmpty(float[]) params = (%s), result = %s", Arrays.toString(floatArray), ArrayUtils.isNotEmpty(floatArray)));

        double[] doubleArray = {1.12, 1.23};
        System.out.println(String.format("isNull(double[]) params = (%s, result = %s", Arrays.toString(doubleArray), ArrayUtils.isNull(doubleArray)));
        System.out.println(String.format("isNotNull(double[]) params = (%s, result = %s", Arrays.toString(doubleArray), ArrayUtils.isNotNull(doubleArray)));
        System.out.println(String.format("isEmpty(double[]) params = (%s, result = %s", Arrays.toString(doubleArray), ArrayUtils.isEmpty(doubleArray)));
        System.out.println(String.format("isNotEmpty(double[]) params = (%s, result = %s", Arrays.toString(doubleArray), ArrayUtils.isNotEmpty(doubleArray)));

        boolean[] booleanArray = {true, false};
        System.out.println(String.format("isNull(boolean[]) params = (%s), result = %s", Arrays.toString(booleanArray), ArrayUtils.isNull(booleanArray)));
        System.out.println(String.format("isNotNull(boolean[]) params = (%s), result = %s", Arrays.toString(booleanArray), ArrayUtils.isNotNull(booleanArray)));
        System.out.println(String.format("isEmpty(boolean[]) params = (%s), result = %s", Arrays.toString(booleanArray), ArrayUtils.isEmpty(booleanArray)));
        System.out.println(String.format("isNotEmpty(boolean[]) params = (%s), result = %s", Arrays.toString(booleanArray), ArrayUtils.isNotEmpty(booleanArray)));

    }

    @Test
    public void isArrayAndLengthTest() {
        String str1 = "ABC";
        Object object = new String[]{"ABC", "DDD"};
        String[] strArray = (String[]) object;

        System.out.println(String.format("isArray(Object) params = (%s), result = %s", object, ArrayUtils.isArray(object)));
        System.out.println(String.format("length(Object) params = (%s), result = %s", object, ArrayUtils.length(object)));
        System.out.println(String.format("isArray(Object) params = (%s), result = %s", Arrays.toString(strArray), ArrayUtils.isArray(strArray)));
        System.out.println(String.format("length(Object) params = (%s), result = %s", Arrays.toString(strArray), ArrayUtils.length(strArray)));
        System.out.println(String.format("isArray(Object) params = (%s), result = %s", str1, ArrayUtils.isArray(str1)));
//        System.out.println(String.format("length(Object) params = (%s), result = %s", str1, ArrayUtils.length(str1)));

    }

    @Test
    public void asListTest() {
        List<String> strList = ArrayUtils.asList("A", "B", "C");
        System.out.println(String.format("asList(E...) params = (%s), result = %s", "A, B, C", strList));
        strList.add("1");
        strList.add("2");
        System.out.println("\tadd 1,2 to this list after: " + strList);

        List<String> strListView = ArrayUtils.asListView("A", "B", "C");
        System.out.println(String.format("asListView(E...) params = (%s), result = %s", "A, B, C", strListView));
//        strListView.add("1");
//        strListView.add("2");
//        System.out.println("\tadd 1,2 to this list view after: " + strListView);

        Student[] stuArray = {new Student(1),new Student(2),new Student(1)};
        Set<Student> stuSet = ArrayUtils.asSet(stuArray);
        System.out.println(String.format("asSet(E...) params = (%s), result = %s", Arrays.toString(stuArray), stuSet));
        stuSet.add(new Student(3));
        stuSet.add(new Student(2));
        System.out.println("\tadd 3,2 to this set after: " + stuSet);

        List<Student> stuSetView = ArrayUtils.asListView(stuArray);
        System.out.println(String.format("asSetView(E...) params = (%s), result = %s", Arrays.toString(stuArray), stuSetView));
//        stuSetView.add(new Student(3));
//        stuSetView.add(new Student(2));
//        System.out.println("\tadd 3,2 to this set view after: " + stuSetView);

    }

    class Student implements Comparable<Student> {

        private int id;

        public Student(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return this.id + "";
        }

        @Override
        public int compareTo(Student o) {
            return this.id - o.id;
        }

        @Override
        public boolean equals(Object obj) {
            return compareTo((Student) obj) == 0;
        }

        @Override
        public int hashCode() {
            return this.id >> 1;
        }
    }

    @Test
    public void toArrayTest() {
        Object object = new String[]{"ABC", "DDD"};
        System.out.println(String.format("toArray(Object) params = (%s), result = %s", object, Arrays.toString(ArrayUtils.toArray(object))));

        String[] strArray = ArrayUtils.toArray(object, String.class);
        System.out.println(String.format("toArray(Object, Class<T>) params = (%s, %s), result = %s", object, "String.class", Arrays.toString(strArray)));

        Integer[] intArray = new Integer[]{1, 2, 3};
        Converter<Integer, String> strConverter = new Converter<Integer, String>() {
            @Override
            public String convert(Integer source) throws ConvertException {
                return String.valueOf(source);
            }
        };
        strArray = ArrayUtils.toArray(intArray, String.class, strConverter);
        System.out.println(String.format("toArray(S[], Class<T>, Converter) params = (%s, %s, %s), result = %s", Arrays.toString(intArray), "String.class", strConverter, Arrays.toString(strArray)));
    }

    @Test
    public void containsTest() {
        String[] strArray = new String[]{"A", "B", "C"};
        System.out.println(String.format("contains(E[], E) params = (%s, %s), result = %s", Arrays.toString(strArray), "B", ArrayUtils.contains(strArray, "B")));
        System.out.println(String.format("containsAll(E[], E...) params = (%s, %s), result = %s", Arrays.toString(strArray), Arrays.toString(new String[]{"B", "D"}), ArrayUtils.containsAll(strArray, "B", "D")));
        System.out.println(String.format("containsAny(E[], E...) params = (%s, %s), result = %s", Arrays.toString(strArray), Arrays.toString(new String[]{"B", "D"}), ArrayUtils.containsAny(strArray, "B", "D")));

        Comparator<String> strComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        };
        System.out.println(String.format("contains(E[], Comparator, E) params = (%s, %s, %s), result = %s", Arrays.toString(strArray), strComparator, "b", ArrayUtils.contains(strArray, strComparator, "b")));
        System.out.println(String.format("containsAll(E[], Comparator, E...) params = (%s, %s, %s), result = %s", Arrays.toString(strArray), strComparator, Arrays.toString(new String[]{"b", "d"}), ArrayUtils.containsAll(strArray, strComparator, "b", "d")));
        System.out.println(String.format("containsAny(E[], Comparator, E...) params = (%s, %s, %s), result = %s", Arrays.toString(strArray), strComparator, Arrays.toString(new String[]{"b", "d"}), ArrayUtils.containsAny(strArray, strComparator, "b", "d")));

        byte[] byteArray = new byte[]{30, 25, 21};
        System.out.println(String.format("contains(byte[], byte) params = (%s, %s), result = %s", Arrays.toString(byteArray), 21, ArrayUtils.contains(byteArray, (byte) 21)));
        System.out.println(String.format("containsAll(byte[], byte) params = (%s, %s), result = %s", Arrays.toString(byteArray), Arrays.toString(new byte[]{30, 21}), ArrayUtils.containsAll(byteArray, (byte) 30, (byte) 21)));
        System.out.println(String.format("containsAny(byte[], byte) params = (%s, %s), result = %s", Arrays.toString(byteArray), Arrays.toString(new byte[]{30, 21}), ArrayUtils.containsAny(byteArray, (byte) 30, (byte) 21)));

        char[] charArray = new char[]{'A', 'B', 'C'};
        System.out.println(String.format("contains(char[], char) params = (%s, %s), result = %s", Arrays.toString(charArray), "B", ArrayUtils.contains(charArray, 'B')));
        System.out.println(String.format("containsAll(char[], char) params = (%s, %s), result = %s", Arrays.toString(charArray), Arrays.toString(new char[]{'B', 'A'}), ArrayUtils.containsAll(charArray, 'B', 'A')));
        System.out.println(String.format("containsAny(char[], char) params = (%s, %s), result = %s", Arrays.toString(charArray), Arrays.toString(new char[]{'B', 'A'}), ArrayUtils.containsAny(charArray, 'B', 'A')));

        short[] shortArray = new short[]{1, 2, 3};
        System.out.println(String.format("contains(short[], short) params = (%s, %s), result = %s", Arrays.toString(shortArray), 3, ArrayUtils.contains(shortArray, (short) 3)));
        System.out.println(String.format("containsAll(short[], short) params = (%s, %s), result = %s", Arrays.toString(shortArray), Arrays.toString(new short[]{3, 2}), ArrayUtils.containsAll(shortArray, (short) 3, (short) 2)));
        System.out.println(String.format("containsAny(short[], short) params = (%s, %s), result = %s", Arrays.toString(shortArray), Arrays.toString(new short[]{3, 2}), ArrayUtils.containsAny(shortArray, (short) 3, (short) 2)));

        int[] intArray = new int[]{333, 222, 444};
        System.out.println(String.format("contains(int[], int) params = (%s, %s), result = %s", Arrays.toString(intArray), 222, ArrayUtils.contains(intArray, 222)));
        System.out.println(String.format("containsAll(int[], int) params = (%s, %s), result = %s", Arrays.toString(intArray), Arrays.toString(new int[]{222, 333}), ArrayUtils.containsAll(intArray, 222, 333)));
        System.out.println(String.format("containsAny(int[], int) params = (%s, %s), result = %s", Arrays.toString(intArray), Arrays.toString(new int[]{222, 333}), ArrayUtils.containsAny(intArray, 222, 333)));

        long[] longArray = new long[]{777777L, 555555L, 666666L};
        System.out.println(String.format("contains(long[], long) params = (%s, %s), result = %s", Arrays.toString(longArray), 555555, ArrayUtils.contains(longArray, 555555L)));
        System.out.println(String.format("containsAll(long[], long) params = (%s, %s), result = %s", Arrays.toString(longArray), Arrays.toString(new long[]{555555, 999999}), ArrayUtils.containsAll(longArray, 555555L, 999999L)));
        System.out.println(String.format("containsAny(long[], long) params = (%s, %s), result = %s", Arrays.toString(longArray), Arrays.toString(new long[]{555555, 999999}), ArrayUtils.containsAny(longArray, 555555L, 999999L)));

        float[] floatArray = new float[]{};
        System.out.println(String.format("contains(float[], float) params = (%s, %s), result = %s", Arrays.toString(floatArray), 2.2, ArrayUtils.contains(floatArray, 2.2F)));
        System.out.println(String.format("containsAll(float[], float) params = (%s, %s), result = %s", Arrays.toString(floatArray), Arrays.toString(new float[]{1.1f, 2.2f}), ArrayUtils.containsAll(floatArray, 1.1F, 2.2F)));
        System.out.println(String.format("containsAny(float[], float) params = (%s, %s), result = %s", Arrays.toString(floatArray), Arrays.toString(new float[]{1.1f, 2.2f}), ArrayUtils.containsAny(floatArray, 1.1F, 2.2F)));

        double[] doubleArray = new double[]{1.11, 2.22, 3.33};
        System.out.println(String.format("contains(double[], double) params = (%s, %s), result = %s", Arrays.toString(doubleArray), 2.22, ArrayUtils.contains(doubleArray, 2.22)));
        System.out.println(String.format("containsAll(double[], double) params = (%s, %s), result = %s", Arrays.toString(doubleArray), Arrays.toString(new double[]{1.11, 2.22}), ArrayUtils.containsAll(doubleArray, 1.11, 2.22)));
        System.out.println(String.format("containsAny(double[], double) params = (%s, %s), result = %s", Arrays.toString(doubleArray), Arrays.toString(new double[]{1.11, 2.22}), ArrayUtils.containsAny(doubleArray, 1.11, 2.22)));
    }

    @Test
    public void indexOfTest() {
        String[] strArray = new String[]{"A", "B", "C", "D", "E", "a", "A", "D", "C", "A"};
        System.out.println(String.format("indexOf(E[], E) params = (%s, %s), result = %s", Arrays.toString(strArray), "A", ArrayUtils.indexOf(strArray, "A")));
        System.out.println(String.format("lastIndexOf(E[], E) params = (%s, %s), result = %s", Arrays.toString(strArray), "A", ArrayUtils.lastIndexOf(strArray, "A")));

        Comparator<String> strComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        };
        System.out.println(String.format("indexOf(E[], Comparator, E) params = (%s, %s, %s), result = %s", Arrays.toString(strArray), strComparator, "a", ArrayUtils.indexOf(strArray, strComparator, "a")));
        System.out.println(String.format("lastIndexOf(E[], Comparator, E) params = (%s, %s, %s), result = %s", Arrays.toString(strArray), strComparator, "a", ArrayUtils.lastIndexOf(strArray, strComparator, "a")));

        byte[] byteArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("indexOf(byte[], byte) params = (%s, %s), result = %s", Arrays.toString(byteArray), 2, ArrayUtils.indexOf(byteArray, (byte) 2)));
        System.out.println(String.format("lastIndexOf(byte[], byte) params = (%s, %s), result = %s", Arrays.toString(byteArray), 2, ArrayUtils.lastIndexOf(byteArray, (byte) 2)));
        char[] charArray = {'2', '1', '2', '2', '3', '4', '2', '2', '2', '3', '4', '2', '5'};
        System.out.println(String.format("indexOf(char[], char) params = (%s, %s), result = %s", Arrays.toString(charArray), '2', ArrayUtils.indexOf(charArray, '2')));
        System.out.println(String.format("lastIndexOf(char[], char) params = (%s, %s), result = %s", Arrays.toString(charArray), '2', ArrayUtils.lastIndexOf(charArray, '2')));
        short[] shortArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("indexOf(short[], short) params = (%s, %s), result = %s", Arrays.toString(shortArray), 2, ArrayUtils.indexOf(shortArray, (short) 2)));
        System.out.println(String.format("lastIndexOf(short[], short) params = (%s, %s), result = %s", Arrays.toString(shortArray), 2, ArrayUtils.lastIndexOf(shortArray, (short) 2)));
        int[] intArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("indexOf(int[], int) params = (%s, %s), result = %s", Arrays.toString(intArray), 2, ArrayUtils.indexOf(intArray, 2)));
        System.out.println(String.format("lastIndexOf(int[], int) params = (%s, %s), result = %s", Arrays.toString(intArray), 2, ArrayUtils.lastIndexOf(intArray, 2)));
        long[] longArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("indexOf(long[], long) params = (%s, %s), result = %s", Arrays.toString(longArray), 2L, ArrayUtils.indexOf(longArray, 2L)));
        System.out.println(String.format("lastIndexOf(long[], long) params = (%s, %s), result = %s", Arrays.toString(longArray), 2L, ArrayUtils.lastIndexOf(longArray, 2L)));
        float[] floatArray = {2.2F, 1.1F, 2.2F, 2.2F, 3.0F, 4.2F, 2.1F, 2.2F, 2.3F, 3.0F, 4F, 2F, 5F};
        System.out.println(String.format("indexOf(int[], int) params = (%s, %s), result = %s", Arrays.toString(floatArray), 2.2F, ArrayUtils.indexOf(floatArray, 2.2F)));
        System.out.println(String.format("lastIndexOf(int[], int) params = (%s, %s), result = %s", Arrays.toString(floatArray), 2.2F, ArrayUtils.lastIndexOf(floatArray, 2.2F)));
        double[] doubleArray = {2.2, 1.2, 2.2, 2.3, 3.2, 4.2, 2.3, 2.2, 2.2, 3.2, 4.2, 2.2, 5.2};
        System.out.println(String.format("indexOf(long[], long) params = (%s, %s), result = %s", Arrays.toString(doubleArray), 2.2, ArrayUtils.indexOf(doubleArray, 2.2)));
        System.out.println(String.format("lastIndexOf(long[], long) params = (%s, %s), result = %s", Arrays.toString(doubleArray), 2.2, ArrayUtils.lastIndexOf(doubleArray, 2.2)));
    }

    @Test
    public void countTest() {
        String[] strArray = new String[]{"A", "B", "C", "D", "E", "a", "A", "D", "C", "A"};
        System.out.println(String.format("count(E[], E) params = (%s, %s), result = %s", Arrays.toString(strArray), "A", ArrayUtils.count(strArray, "A")));

        Comparator<String> strComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        };
        System.out.println(String.format("count(E[], Comparator, E) params = (%s, %s, %s), result = %s", Arrays.toString(strArray), strComparator, "a", ArrayUtils.count(strArray, strComparator, "a")));

        byte[] byteArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("count(byte[], byte) params = (%s, %s), result = %s", Arrays.toString(byteArray), 2, ArrayUtils.count(byteArray, (byte) 2)));
        char[] charArray = {'2', '1', '2', '2', '3', '4', '2', '2', '2', '3', '4', '2', '5'};
        System.out.println(String.format("count(char[], char) params = (%s, %s), result = %s", Arrays.toString(charArray), '2', ArrayUtils.count(charArray, '2')));
        short[] shortArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("count(short[], short) params = (%s, %s), result = %s", Arrays.toString(shortArray), 2, ArrayUtils.count(shortArray, (short) 2)));
        int[] intArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("count(int[], int) params = (%s, %s), result = %s", Arrays.toString(intArray), 2, ArrayUtils.count(intArray, 2)));
        long[] longArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("count(long[], long) params = (%s, %s), result = %s", Arrays.toString(longArray), 2L, ArrayUtils.count(longArray, 2L)));
        float[] floatArray = {2.2F, 1.1F, 2.2F, 2.2F, 3.0F, 4.2F, 2.1F, 2.2F, 2.3F, 3.0F, 4F, 2F, 5F};
        System.out.println(String.format("count(int[], int) params = (%s, %s), result = %s", Arrays.toString(floatArray), 2.2F, ArrayUtils.count(floatArray, 2.2F)));
        double[] doubleArray = {2.2, 1.2, 2.2, 2.3, 3.2, 4.2, 2.3, 2.2, 2.2, 3.2, 4.2, 2.2, 5.2};
        System.out.println(String.format("count(long[], long) params = (%s, %s), result = %s", Arrays.toString(doubleArray), 2.2, ArrayUtils.count(doubleArray, 2.2)));
    }

    @Test
    public void equalsTest() {
        String[] strArray = new String[]{"A", "B", "C", "D", "E", "a", "A", "D", "C", "A"};
        String[] strOtherArray = new String[]{"A", "B", "C", "D", "E", "a", "A", "D", "C", "A"};
        System.out.println(String.format("equals(E[], E[]) params = (%s, %s), result = %s", Arrays.toString(strArray), Arrays.toString(strOtherArray), ArrayUtils.equals(strArray, strOtherArray)));

        Comparator<String> strComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        };
        System.out.println(String.format("equals(E[], Comparator, E[]) params = (%s, %s, %s), result = %s", Arrays.toString(strArray), strComparator, Arrays.toString(strOtherArray), ArrayUtils.equals(strArray, strComparator, strOtherArray)));

        byte[] byteArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        byte[] byteOtherArray = {2, 1, 22, 5};
        System.out.println(String.format("equals(byte[], byte[]) params = (%s, %s), result = %s", Arrays.toString(byteArray), Arrays.toString(strOtherArray), ArrayUtils.equals(byteArray, byteOtherArray)));
        char[] charArray = {'2', '1', '2', '2', '3', '4', '2', '2', '2', '3', '4', '2', '5'};
        char[] charOtherArray = {'2', '1', '2', '2', '3', '4', '2', '2', '2', '3', '4', '2', '5'};
        System.out.println(String.format("equals(char[], char[]) params = (%s, %s), result = %s", Arrays.toString(charArray), Arrays.toString(charOtherArray), ArrayUtils.equals(charArray, charOtherArray)));
        short[] shortArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        short[] shortOtherArray = {2, 1, 2, 2, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("equals(short[], short[]) params = (%s, %s), result = %s", Arrays.toString(shortArray), Arrays.toString(strOtherArray), ArrayUtils.equals(shortArray, shortOtherArray)));
        int[] intArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        int[] intOtherArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("equals(int[], int[]) params = (%s, %s), result = %s", Arrays.toString(intArray), Arrays.toString(intOtherArray), ArrayUtils.equals(intArray, intOtherArray)));
        long[] longArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        long[] longOtherArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("equals(long[], long[]) params = (%s, %s), result = %s", Arrays.toString(longArray), Arrays.toString(longOtherArray), ArrayUtils.equals(longArray, longOtherArray)));
        float[] floatArray = {2.2F, 1.1F, 2.2F, 3.0F, 4.2F, 2.1F, 2.2F, 2.3F, 3.0F, 4F, 2F, 5F};
        float[] floatOtherArray = {2.2F, 1.1F, 2.2F, 2.2F, 3.0F, 4.2F, 2.1F, 2.2F, 2.3F, 3.0F, 4F, 2F, 5F};
        System.out.println(String.format("equals(float[], float[]) params = (%s, %s), result = %s", Arrays.toString(floatArray), Arrays.toString(floatOtherArray), ArrayUtils.equals(floatArray, floatOtherArray)));
        double[] doubleArray = {2.2, 1.2, 2.2, 2.3, 3.2, 4.2, 2.3, 2.2, 2.2, 3.2, 4.2, 2.2, 5.2};
        double[] doubleOtherArray = {2.2, 1.2, 2.2, 2.3, 3.2, 4.2, 2.3, 2.2, 2.2, 3.2, 4.2, 2.2, 5.2};
        System.out.println(String.format("equals(double[], double[]) params = (%s, %s), result = %s", Arrays.toString(doubleArray), Arrays.toString(doubleOtherArray), ArrayUtils.equals(doubleArray, doubleOtherArray)));
    }

    @Test
    public void reverseTest() {
        String[] strArray = new String[]{"A", "B", "C", "D", "E", "a", "A", "D", "C", "A"};
        String[] strAfterArray = strArray.clone();
        ArrayUtils.reverse(strAfterArray);
        System.out.println(String.format("reverse(E[]) params = (%s), result = %s", Arrays.toString(strArray), Arrays.toString(strAfterArray)));

        byte[] byteArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        byte[] byteAfterArray = byteArray.clone();
        ArrayUtils.reverse(byteAfterArray);
        System.out.println(String.format("reverse(byte[]) params = (%s), result = %s", Arrays.toString(byteArray), Arrays.toString(byteAfterArray)));

        char[] charArray = {'2', '1', '2', '2', '3', '4', '2', '2', '2', '3', '4', '2', '5'};
        char[] charAfterArray = charArray.clone();
        ArrayUtils.reverse(charAfterArray);
        System.out.println(String.format("reverse(byte[]) params = (%s), result = %s", Arrays.toString(charArray), Arrays.toString(charAfterArray)));

        short[] shortArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        short[] shortAfterArray = shortArray.clone();
        ArrayUtils.reverse(shortAfterArray);
        System.out.println(String.format("reverse(short[]) params = (%s), result = %s", Arrays.toString(shortArray), Arrays.toString(shortAfterArray)));

        int[] intArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        int[] intAfterArray = intArray.clone();
        ArrayUtils.reverse(intAfterArray);
        System.out.println(String.format("reverse(int[]) params = (%s), result = %s", Arrays.toString(intArray), Arrays.toString(intAfterArray)));

        long[] longArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        long[] longAfterArray = longArray.clone();
        ArrayUtils.reverse(longAfterArray);
        System.out.println(String.format("reverse(long[]) params = (%s), result = %s", Arrays.toString(longArray), Arrays.toString(longAfterArray)));

        float[] floatArray = {2.2F, 1.1F, 2.2F, 2.2F, 3.0F, 4.2F, 2.1F, 2.2F, 2.3F, 3.0F, 4F, 2F, 5F};
        float[] floatAfterArray = floatArray.clone();
        ArrayUtils.reverse(floatAfterArray);
        System.out.println(String.format("reverse(int[]) params = (%s), result = %s", Arrays.toString(floatArray), Arrays.toString(floatAfterArray)));

        double[] doubleArray = {2.2, 1.2, 2.2, 2.3, 3.2, 4.2, 2.3, 2.2, 2.2, 3.2, 4.2, 2.2, 5.2};
        double[] doubleAfterArray = doubleArray.clone();
        ArrayUtils.reverse(doubleAfterArray);
        System.out.println(String.format("reverse(long[]) params = (%s), result = %s", Arrays.toString(doubleArray), Arrays.toString(doubleAfterArray)));
    }

    @Test
    public void subArrayTest() {
        String[] strArray = new String[]{"A", "B", "C", "D", "E", "a", "A", "D", "C", "A"};
        System.out.println(String.format("subArray(E[], int, int) params = (%s, %s, %s), result = %s", Arrays.toString(strArray), 2, 6, Arrays.toString(ArrayUtils.subArray(strArray, 2, 6))));
        byte[] byteArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("subArray(byte[], int, int) params = (%s, %s, %s), result = %s", Arrays.toString(byteArray), 2, 6, Arrays.toString(ArrayUtils.subArray(byteArray, 2, 6))));
        char[] charArray = {'2', '1', '2', '2', '3', '4', '2', '2', '2', '3', '4', '2', '5'};
        System.out.println(String.format("subArray(char[], int, int) params = (%s, %s, %s), result = %s", Arrays.toString(charArray), 2, 6, Arrays.toString(ArrayUtils.subArray(charArray, 2, 6))));
        short[] shortArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("subArray(short[], int, int) params = (%s, %s, %s), result = %s", Arrays.toString(shortArray), 2, 6, Arrays.toString(ArrayUtils.subArray(shortArray, 2, 6))));
        int[] intArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("subArray(int[], int, int) params = (%s, %s, %s), result = %s", Arrays.toString(intArray), 2, 6, Arrays.toString(ArrayUtils.subArray(intArray, 2, 6))));
        long[] longArray = {2, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 2, 5};
        System.out.println(String.format("subArray(long[], int, int) params = (%s, %s, %s), result = %s", Arrays.toString(longArray), 2, 6, Arrays.toString(ArrayUtils.subArray(longArray, 2, 6))));
        float[] floatArray = {2.2F, 1.1F, 2.2F, 2.2F, 3.0F, 4.2F, 2.1F, 2.2F, 2.3F, 3.0F, 4F, 2F, 5F};
        System.out.println(String.format("subArray(int[], int, int) params = (%s, %s, %s), result = %s", Arrays.toString(floatArray), 2, 6, Arrays.toString(ArrayUtils.subArray(floatArray, 2, 6))));
        double[] doubleArray = {2.2, 1.2, 2.2, 2.3, 3.2, 4.2, 2.3, 2.2, 2.2, 3.2, 4.2, 2.2, 5.2};
        System.out.println(String.format("subArray(long[], int, int) params = (%s, %s, %s), result = %s", Arrays.toString(doubleArray), 2, 6, Arrays.toString(ArrayUtils.subArray(doubleArray, 2, 6))));
    }

    @Test
    public void calcArrayTest() {
        String[] array1 = {"A", "B", "C", "D"};
        String[] array2 = {"E", "F", "B", "C", "A"};
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        };
        System.out.println(String.format("A = %s, B = %s", Arrays.toString(array1), Arrays.toString(array2)));
        System.out.println(String.format("\tA＋B = %s", Arrays.toString(ArrayUtils.join(array1, array2))));
        System.out.println(String.format("\tA∪B = %s", Arrays.toString(ArrayUtils.merge(array1, array2))));
        System.out.println(String.format("\tA∪B = %s", Arrays.toString(ArrayUtils.merge(array1, array2, comparator))));
        System.out.println(String.format("\tA－B = %s", Arrays.toString(ArrayUtils.differ(array1, array2))));
        System.out.println(String.format("\tA－B = %s", Arrays.toString(ArrayUtils.differ(array1, array2, comparator))));
        System.out.println(String.format("\tA∩B = %s", Arrays.toString(ArrayUtils.cross(array1, array2))));
        System.out.println(String.format("\tA∩B = %s", Arrays.toString(ArrayUtils.cross(array1, array2, comparator))));
        System.out.println(String.format("\t(A－B)∪(B－A) = %s", Arrays.toString(ArrayUtils.unCross(array1, array2))));
        System.out.println(String.format("\t(A－B)∪(B－A) = %s", Arrays.toString(ArrayUtils.unCross(array1, array2, comparator))));
        System.out.println();

        byte[] byteArray1 = {1, 2, 3, 4, 5};
        byte[] byteArray2 = {6, 2, 1};
        System.out.println(String.format("A = %s, B = %s", Arrays.toString(byteArray1), Arrays.toString(byteArray2)));
        System.out.println(String.format("\tA＋B = %s", Arrays.toString(ArrayUtils.join(byteArray1, byteArray2))));
        System.out.println(String.format("\tA∪B = %s", Arrays.toString(ArrayUtils.merge(byteArray1, byteArray2))));
        System.out.println(String.format("\tA－B = %s", Arrays.toString(ArrayUtils.differ(byteArray1, byteArray2))));
        System.out.println(String.format("\tA∩B = %s", Arrays.toString(ArrayUtils.cross(byteArray1, byteArray2))));
        System.out.println(String.format("\t(A－B)∪(B－A) = %s", Arrays.toString(ArrayUtils.unCross(byteArray1, byteArray2))));
        System.out.println();

        char[] charArray1 = {'A', 'B', 'C', 'D', 'E'};
        char[] charArray2 = {'F', 'A', 'D'};
        System.out.println(String.format("A = %s, B = %s", Arrays.toString(charArray1), Arrays.toString(charArray2)));
        System.out.println(String.format("\tA＋B = %s", Arrays.toString(ArrayUtils.join(charArray1, charArray2))));
        System.out.println(String.format("\tA∪B = %s", Arrays.toString(ArrayUtils.merge(charArray1, charArray2))));
        System.out.println(String.format("\tA－B = %s", Arrays.toString(ArrayUtils.differ(charArray1, charArray2))));
        System.out.println(String.format("\tA∩B = %s", Arrays.toString(ArrayUtils.cross(charArray1, charArray2))));
        System.out.println(String.format("\t(A－B)∪(B－A) = %s", Arrays.toString(ArrayUtils.unCross(charArray1, charArray2))));
        System.out.println();

        short[] shortArray1 = {1, 2, 3, 4, 5};
        short[] shortArray2 = {6, 2, 1};
        System.out.println(String.format("A = %s, B = %s", Arrays.toString(shortArray1), Arrays.toString(shortArray2)));
        System.out.println(String.format("\tA＋B = %s", Arrays.toString(ArrayUtils.join(shortArray1, shortArray2))));
        System.out.println(String.format("\tA∪B = %s", Arrays.toString(ArrayUtils.merge(shortArray1, shortArray2))));
        System.out.println(String.format("\tA－B = %s", Arrays.toString(ArrayUtils.differ(shortArray1, shortArray2))));
        System.out.println(String.format("\tA∩B = %s", Arrays.toString(ArrayUtils.cross(shortArray1, shortArray2))));
        System.out.println(String.format("\t(A－B)∪(B－A) = %s", Arrays.toString(ArrayUtils.unCross(shortArray1, shortArray2))));
        System.out.println();

        int[] intArray1 = {1, 2, 3, 4, 5};
        int[] intArray2 = {6, 2, 1};
        System.out.println(String.format("A = %s, B = %s", Arrays.toString(intArray1), Arrays.toString(intArray2)));
        System.out.println(String.format("\tA＋B = %s", Arrays.toString(ArrayUtils.join(intArray1, intArray2))));
        System.out.println(String.format("\tA∪B = %s", Arrays.toString(ArrayUtils.merge(intArray1, intArray2))));
        System.out.println(String.format("\tA－B = %s", Arrays.toString(ArrayUtils.differ(intArray1, intArray2))));
        System.out.println(String.format("\tA∩B = %s", Arrays.toString(ArrayUtils.cross(intArray1, intArray2))));
        System.out.println(String.format("\t(A－B)∪(B－A) = %s", Arrays.toString(ArrayUtils.unCross(intArray1, intArray2))));
        System.out.println();

        long[] longArray1 = {1, 2, 3, 4, 5};
        long[] longArray2 = {6, 2, 1};
        System.out.println(String.format("A = %s, B = %s", Arrays.toString(longArray1), Arrays.toString(longArray2)));
        System.out.println(String.format("\tA＋B = %s", Arrays.toString(ArrayUtils.join(longArray1, longArray2))));
        System.out.println(String.format("\tA∪B = %s", Arrays.toString(ArrayUtils.merge(longArray1, longArray2))));
        System.out.println(String.format("\tA－B = %s", Arrays.toString(ArrayUtils.differ(longArray1, longArray2))));
        System.out.println(String.format("\tA∩B = %s", Arrays.toString(ArrayUtils.cross(longArray1, longArray2))));
        System.out.println(String.format("\t(A－B)∪(B－A) = %s", Arrays.toString(ArrayUtils.unCross(longArray1, longArray2))));
        System.out.println();

        float[] floatArray1 = {1.1F, 2.2F, 3.3F, 4.4F, 5.5F};
        float[] floatArray2 = {6.1F, 2.1F, 1.1F};
        System.out.println(String.format("A = %s, B = %s", Arrays.toString(floatArray1), Arrays.toString(floatArray2)));
        System.out.println(String.format("\tA＋B = %s", Arrays.toString(ArrayUtils.join(floatArray1, floatArray2))));
        System.out.println(String.format("\tA∪B = %s", Arrays.toString(ArrayUtils.merge(floatArray1, floatArray2))));
        System.out.println(String.format("\tA－B = %s", Arrays.toString(ArrayUtils.differ(floatArray1, floatArray2))));
        System.out.println(String.format("\tA∩B = %s", Arrays.toString(ArrayUtils.cross(floatArray1, floatArray2))));
        System.out.println(String.format("\t(A－B)∪(B－A) = %s", Arrays.toString(ArrayUtils.unCross(floatArray1, floatArray2))));
        System.out.println();

        double[] doubleArray1 = {1.11F, 2.22F, 3.33F, 4.44F, 5.55F};
        double[] doubleArray2 = {6.11F, 2.11F, 1.11F};
        System.out.println(String.format("A = %s, B = %s", Arrays.toString(doubleArray1), Arrays.toString(doubleArray2)));
        System.out.println(String.format("\tA＋B = %s", Arrays.toString(ArrayUtils.join(doubleArray1, doubleArray2))));
        System.out.println(String.format("\tA∪B = %s", Arrays.toString(ArrayUtils.merge(doubleArray1, doubleArray2))));
        System.out.println(String.format("\tA－B = %s", Arrays.toString(ArrayUtils.differ(doubleArray1, doubleArray2))));
        System.out.println(String.format("\tA∩B = %s", Arrays.toString(ArrayUtils.cross(doubleArray1, doubleArray2))));
        System.out.println(String.format("\t(A－B)∪(B－A) = %s", Arrays.toString(ArrayUtils.unCross(doubleArray1, doubleArray2))));
        System.out.println();
    }


}
