/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.test.ClassUtilsTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-14
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.util.test;

import org.beehive.util.ClassUtils;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.util.test</code></li>
 * <li>Class Name: <code>ClassUtilsTest</code></li>
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
 * <td align="center"><em>2020/9/14</em></td>
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
public class ClassUtilsTest {

    @Test
    public void typeTest() {

        Object[] obj = new Integer[]{};
        System.out.println(String.format("getArrayType(%s) =  %s", Arrays.toString(obj), ClassUtils.getArrayType(obj)));

        System.out.println(String.format("isPrimitiveType(%s) = %s", int.class, ClassUtils.isPrimitiveType(int.class)));
        System.out.println(String.format("isPrimitiveType(%s) = %s", Integer.class, ClassUtils.isPrimitiveType(Integer.class)));
        System.out.println(String.format("isPrimitiveType(%s) = %s", Date.class, ClassUtils.isPrimitiveType(Date.class)));
        System.out.println(String.format("isWrapperType(%s) = %s", Integer.class, ClassUtils.isWrapperType(Integer.class)));
        System.out.println(String.format("isWrapperType(%s) = %s", Integer.class, ClassUtils.isWrapperType(ArrayList.class)));
        System.out.println(String.format("isValueType(%s) = %s", Integer.class, ClassUtils.isValueType(Integer.class)));
        System.out.println(String.format("isValueType(%s) = %s", Date.class, ClassUtils.isValueType(Date.class)));
        System.out.println(String.format("isValueType(%s) = %s", ArrayList.class, ClassUtils.isValueType(ArrayList.class)));
        System.out.println("....register ArrayList to valueType....");
        ClassUtils.registerValueType(ArrayList.class);
        System.out.println(String.format("isValueType(%s) = %s", ArrayList.class, ClassUtils.isValueType(ArrayList.class)));
    }

    @Test
    public void typeOfTest() {
        Class<ArrayList> arrayList = ArrayList.class;
        System.out.println("----- implementOf -----");
        System.out.println(String.format("implementOf(%s, %s) = %s", arrayList, ArrayList.class, ClassUtils.implementOf(arrayList, ArrayList.class)));
        System.out.println(String.format("implementOf(%s, %s) = %s", arrayList, Collection.class, ClassUtils.implementOf(arrayList, Collection.class)));
        System.out.println(String.format("implementOf(%s, %s) = %s", arrayList, List.class, ClassUtils.implementOf(arrayList, List.class)));
        System.out.println(String.format("implementOf(%s, %s) = %s", arrayList, Iterable.class, ClassUtils.implementOf(arrayList, Iterable.class)));
        System.out.println(String.format("implementOf(%s, %s) = %s", arrayList, AbstractList.class, ClassUtils.implementOf(arrayList, AbstractList.class)));
        System.out.println(String.format("implementOf(%s, %s) = %s", arrayList, AbstractCollection.class, ClassUtils.implementOf(arrayList, AbstractCollection.class)));
        System.out.println(String.format("implementOf(%s, %s) = %s", Integer.class, Serializable.class, ClassUtils.implementOf(Integer.class, Serializable.class)));

        System.out.println("----- extendsOf -----");
        System.out.println(String.format("extendsOf(%s, %s) = %s", arrayList, ArrayList.class, ClassUtils.extendsOf(arrayList, ArrayList.class)));
        System.out.println(String.format("extendsOf(%s, %s) = %s", arrayList, Collection.class, ClassUtils.extendsOf(arrayList, Collection.class)));
        System.out.println(String.format("extendsOf(%s, %s) = %s", arrayList, List.class, ClassUtils.extendsOf(arrayList, List.class)));
        System.out.println(String.format("extendsOf(%s, %s) = %s", arrayList, Iterable.class, ClassUtils.extendsOf(arrayList, Iterable.class)));
        System.out.println(String.format("extendsOf(%s, %s) = %s", arrayList, AbstractList.class, ClassUtils.extendsOf(arrayList, AbstractList.class)));
        System.out.println(String.format("extendsOf(%s, %s) = %s", arrayList, AbstractCollection.class, ClassUtils.extendsOf(arrayList, AbstractCollection.class)));
        System.out.println(String.format("implementOf(%s, %s) = %s", BufferedInputStream.class, InputStream.class, ClassUtils.extendsOf(BufferedInputStream.class, InputStream.class)));
    }

    @Test
    public void classLoaderTest() {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        System.out.println(String.format("getDefaultClassLoader() = %s", classLoader));
        // /D:/workspace/IdeaProjects/my/beehive-parent/beehive-core/target/classes/string/factory/common-characters_zh_CN.properties
        System.out.println(String.format("\tclassLoader.getResource(\"%s\") = %s", "string/factory/common-characters_zh_CN.properties", classLoader.getResource("string/factory/common-characters_zh_CN.properties").getPath()));
        // /D:/workspace/IdeaProjects/my/beehive-parent/beehive-core/target/test-classes/
        System.out.println(String.format("\tclassLoader.getResource(\"%s\") = %s", ".", classLoader.getResource(".").getPath()));
        // /D:/workspace/IdeaProjects/my/beehive-parent/beehive-core/target/test-classes/
        System.out.println(String.format("\tclassLoader.getResource(\"%s\") = %s", "", classLoader.getResource("").getPath()));
        System.out.println(String.format("getClassLoader(%s) = %s", String.class, ClassUtils.getClassLoader(String.class)));
        System.out.println(String.format("getClassLoader(%s) = %s", ClassUtils.class, ClassUtils.getClassLoader(ClassUtils.class)));
        System.out.println(String.format("getClassLoader(%s) = %s", ClassUtilsTest.class, ClassUtils.getClassLoader(ClassUtilsTest.class)));
    }

    @Test
    public void classPathTest() {
        System.out.println(String.format("getDefaultClassLoadURL() = %s", ClassUtils.getDefaultClassLoadURL()));

        System.out.println(String.format("getClassLoadURL(%s) = %s", String.class, ClassUtils.getClassLoadURL(String.class)));
        System.out.println(String.format("getClassLoadURL(%s) = %s", ClassUtils.class, ClassUtils.getClassLoadURL(ClassUtils.class)));
        System.out.println(String.format("getClassLoadURL(%s) = %s", ClassUtilsTest.class, ClassUtils.getClassLoadURL(ClassUtilsTest.class)));

        System.out.println(String.format("getCurrentClassURL(%s) = %s", String.class, ClassUtils.getCurrentClassURL(String.class)));
        System.out.println(String.format("getCurrentClassURL(%s) = %s", ClassUtils.class, ClassUtils.getCurrentClassURL(ClassUtils.class)));
        System.out.println(String.format("getCurrentClassURL(%s) = %s", ClassUtilsTest.class, ClassUtils.getCurrentClassURL(ClassUtilsTest.class)));
    }

    @Test
    public void testClassName() {
        System.out.println(String.format("getClassName(%s) = %s", String.class, ClassUtils.getFullName(String.class)));
        System.out.println(String.format("getClassName(%s) = %s", ClassUtils.class, ClassUtils.getFullName(ClassUtils.class)));

        System.out.println(String.format("getAbbreviatedName(%s) = %s", String.class, ClassUtils.getAbbreviatedName(String.class)));
        System.out.println(String.format("getAbbreviatedName(%s) = %s", ClassUtils.class, ClassUtils.getAbbreviatedName(ClassUtils.class)));

        System.out.println(String.format("getSimpleName(%s) = %s", String.class, ClassUtils.getShortName(String.class)));
        System.out.println(String.format("getSimpleName(%s) = %s", ClassUtils.class, ClassUtils.getShortName(ClassUtils.class)));

        System.out.println(String.format("fromPackage(%s, %s) = %s", String.class, "org.beehive", ClassUtils.fromPackage(String.class, "org.beehive")));
        System.out.println(String.format("fromPackage(%s, %s) = %s", ClassUtils.class, "org.beehive", ClassUtils.fromPackage(ClassUtils.class, "org.beehive")));

        System.out.println(String.format("matchPackage(%s, %s) = %s", String.class, "org.beehive.**", ClassUtils.matchPackage(String.class, "org.beehive.**")));
        System.out.println(String.format("matchPackage(%s, %s) = %s", ClassUtils.class, "org.beehive.**", ClassUtils.matchPackage(ClassUtils.class, "org.beehive.**")));
    }

    /***************/
    // 泛型接口
    interface InterfaceA<E> {

    }

    // 普通接口继承泛型接口
    interface InterfaceB extends InterfaceA<Integer> {

    }

    // 普通接口
    interface InterfaceC {

    }

    // 普通接口
    interface InterfaceD extends InterfaceB {

    }

    // 泛型子接口
    interface InterfaceE<T> extends InterfaceA<T>, Comparable<T> {

    }

    //普通子接口
    interface InterfaceF extends InterfaceE<String> {

    }

    //普通子接口
    interface InterfaceG extends InterfaceA {

    }

    //普通子接口
    interface InterfaceH extends InterfaceB {

    }

    // 泛型类
    class ClassA<E> {

    }

    // 普通类继承泛型类
    class ClassB extends ClassA<Integer> {

    }

    // 普通类
    class ClassC {

    }

    // 普通类
    class ClassD extends ClassB {

    }

    // 泛型子类
    class ClassE<T> extends ClassA<T> {

    }

    //普通子类
    class ClassF extends ClassE<String> {

    }

    //普通子类
    class ClassG extends ClassA {

    }

    //普通子类
    class ClassH extends ClassB implements InterfaceA<String>, Comparable<String> {

        @Override
        public int compareTo(String o) {
            return 0;
        }
    }

    class ClassJ<K> extends HashMap<K, ClassB> implements InterfaceA<String> {

    }

    class ClassO extends ClassJ<Integer> {

    }

    /***************/

    @Test
    public void testGenericType() {
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", InterfaceA.class, ClassUtils.isDeclaredGenericType(InterfaceA.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", InterfaceB.class, ClassUtils.isDeclaredGenericType(InterfaceB.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", InterfaceC.class, ClassUtils.isDeclaredGenericType(InterfaceC.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", InterfaceD.class, ClassUtils.isDeclaredGenericType(InterfaceD.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", InterfaceE.class, ClassUtils.isDeclaredGenericType(InterfaceE.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", InterfaceF.class, ClassUtils.isDeclaredGenericType(InterfaceF.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", InterfaceG.class, ClassUtils.isDeclaredGenericType(InterfaceG.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", InterfaceH.class, ClassUtils.isDeclaredGenericType(InterfaceH.class)));

        System.out.println(String.format("isDeclaredGenericType(%s) => %s", ClassA.class, ClassUtils.isDeclaredGenericType(ClassA.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", ClassB.class, ClassUtils.isDeclaredGenericType(ClassB.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", ClassC.class, ClassUtils.isDeclaredGenericType(ClassC.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", ClassD.class, ClassUtils.isDeclaredGenericType(ClassD.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", ClassE.class, ClassUtils.isDeclaredGenericType(ClassE.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", ClassF.class, ClassUtils.isDeclaredGenericType(ClassF.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", ClassH.class, ClassUtils.isDeclaredGenericType(ClassH.class)));
        System.out.println(String.format("isDeclaredGenericType(%s) => %s", ClassG.class, ClassUtils.isDeclaredGenericType(ClassG.class)));

        System.out.println();

        System.out.println(String.format("isGenericType(%s) => %s", InterfaceA.class, ClassUtils.isGenericType(InterfaceA.class)));
        System.out.println(String.format("isGenericType(%s) => %s", InterfaceB.class, ClassUtils.isGenericType(InterfaceB.class)));
        System.out.println(String.format("isGenericType(%s) => %s", InterfaceC.class, ClassUtils.isGenericType(InterfaceC.class)));
        System.out.println(String.format("isGenericType(%s) => %s", InterfaceD.class, ClassUtils.isGenericType(InterfaceD.class)));
        System.out.println(String.format("isGenericType(%s) => %s", InterfaceE.class, ClassUtils.isGenericType(InterfaceE.class)));
        System.out.println(String.format("isGenericType(%s) => %s", InterfaceF.class, ClassUtils.isGenericType(InterfaceF.class)));
        System.out.println(String.format("isGenericType(%s) => %s", InterfaceG.class, ClassUtils.isGenericType(InterfaceG.class)));
        System.out.println(String.format("isGenericType(%s) => %s", InterfaceH.class, ClassUtils.isGenericType(InterfaceH.class)));

        System.out.println(String.format("isGenericType(%s) => %s", ClassA.class, ClassUtils.isGenericType(ClassA.class)));
        System.out.println(String.format("isGenericType(%s) => %s", ClassB.class, ClassUtils.isGenericType(ClassB.class)));
        System.out.println(String.format("isGenericType(%s) => %s", ClassC.class, ClassUtils.isGenericType(ClassC.class)));
        System.out.println(String.format("isGenericType(%s) => %s", ClassD.class, ClassUtils.isGenericType(ClassD.class)));
        System.out.println(String.format("isGenericType(%s) => %s", ClassE.class, ClassUtils.isGenericType(ClassE.class)));
        System.out.println(String.format("isGenericType(%s) => %s", ClassF.class, ClassUtils.isGenericType(ClassF.class)));
        System.out.println(String.format("isGenericType(%s) => %s", ClassH.class, ClassUtils.isGenericType(ClassH.class)));
        System.out.println(String.format("isGenericType(%s) => %s", ClassG.class, ClassUtils.isGenericType(ClassG.class)));

        System.out.println();

        System.out.println(String.format("getDeclaredGenericType(%s) => %s", InterfaceA.class, Arrays.toString(ClassUtils.getDeclaredGenericType(InterfaceA.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", InterfaceB.class, Arrays.toString(ClassUtils.getDeclaredGenericType(InterfaceB.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", InterfaceC.class, Arrays.toString(ClassUtils.getDeclaredGenericType(InterfaceC.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", InterfaceD.class, Arrays.toString(ClassUtils.getDeclaredGenericType(InterfaceD.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", InterfaceE.class, Arrays.toString(ClassUtils.getDeclaredGenericType(InterfaceE.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", InterfaceF.class, Arrays.toString(ClassUtils.getDeclaredGenericType(InterfaceF.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", InterfaceG.class, Arrays.toString(ClassUtils.getDeclaredGenericType(InterfaceG.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", InterfaceH.class, Arrays.toString(ClassUtils.getDeclaredGenericType(InterfaceH.class))));

        System.out.println(String.format("getDeclaredGenericType(%s) => %s", ClassA.class, Arrays.toString(ClassUtils.getDeclaredGenericType(ClassA.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", ClassB.class, Arrays.toString(ClassUtils.getDeclaredGenericType(ClassB.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", ClassC.class, Arrays.toString(ClassUtils.getDeclaredGenericType(ClassC.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", ClassD.class, Arrays.toString(ClassUtils.getDeclaredGenericType(ClassD.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", ClassE.class, Arrays.toString(ClassUtils.getDeclaredGenericType(ClassE.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", ClassF.class, Arrays.toString(ClassUtils.getDeclaredGenericType(ClassF.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", ClassH.class, Arrays.toString(ClassUtils.getDeclaredGenericType(ClassH.class))));
        System.out.println(String.format("getDeclaredGenericType(%s) => %s", ClassG.class, Arrays.toString(ClassUtils.getDeclaredGenericType(ClassG.class))));

        System.out.println();

        System.out.println(String.format("getActualGenericType(%s) => %s", InterfaceA.class, Arrays.toString(ClassUtils.getActualGenericType(InterfaceA.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", InterfaceB.class, Arrays.toString(ClassUtils.getActualGenericType(InterfaceB.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", InterfaceC.class, Arrays.toString(ClassUtils.getActualGenericType(InterfaceC.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", InterfaceD.class, Arrays.toString(ClassUtils.getActualGenericType(InterfaceD.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", InterfaceE.class, Arrays.toString(ClassUtils.getActualGenericType(InterfaceE.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", InterfaceF.class, Arrays.toString(ClassUtils.getActualGenericType(InterfaceF.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", InterfaceG.class, Arrays.toString(ClassUtils.getActualGenericType(InterfaceG.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", InterfaceH.class, Arrays.toString(ClassUtils.getActualGenericType(InterfaceH.class))));

        System.out.println(String.format("getActualGenericType(%s) => %s", ClassA.class, Arrays.toString(ClassUtils.getActualGenericType(ClassA.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", ClassB.class, Arrays.toString(ClassUtils.getActualGenericType(ClassB.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", ClassC.class, Arrays.toString(ClassUtils.getActualGenericType(ClassC.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", ClassD.class, Arrays.toString(ClassUtils.getActualGenericType(ClassD.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", ClassE.class, Arrays.toString(ClassUtils.getActualGenericType(ClassE.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", ClassF.class, Arrays.toString(ClassUtils.getActualGenericType(ClassF.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", ClassH.class, Arrays.toString(ClassUtils.getActualGenericType(ClassH.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", ClassG.class, Arrays.toString(ClassUtils.getActualGenericType(ClassG.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", ClassJ.class, Arrays.toString(ClassUtils.getActualGenericType(ClassJ.class))));
        System.out.println(String.format("getActualGenericType(%s) => %s", ClassO.class, Arrays.toString(ClassUtils.getActualGenericType(ClassO.class))));

    }

}
