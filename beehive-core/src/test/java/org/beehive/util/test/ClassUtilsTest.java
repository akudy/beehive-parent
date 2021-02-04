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
        ArrayList<String> arrayList = new ArrayList<>();
        System.out.println("----- implementOf -----");
        System.out.println(String.format("implementOf(%s, %s) = %s", arrayList, ArrayList.class, ClassUtils.implementOf(arrayList, ArrayList.class)));
        System.out.println(String.format("implementOf(%s, %s) = %s", arrayList, Collection.class, ClassUtils.implementOf(arrayList, Collection.class)));
        System.out.println(String.format("implementOf(%s, %s) = %s", arrayList, List.class, ClassUtils.implementOf(arrayList, List.class)));
        System.out.println(String.format("implementOf(%s, %s) = %s", arrayList, Iterable.class, ClassUtils.implementOf(arrayList, Iterable.class)));
        System.out.println(String.format("implementOf(%s, %s) = %s", arrayList, AbstractList.class, ClassUtils.implementOf(arrayList, AbstractList.class)));
        System.out.println(String.format("implementOf(%s, %s) = %s", arrayList, AbstractCollection.class, ClassUtils.implementOf(arrayList, AbstractCollection.class)));

        System.out.println("----- extendsOf -----");
        System.out.println(String.format("extendsOf(%s, %s) = %s", arrayList, ArrayList.class, ClassUtils.extendsOf(arrayList, ArrayList.class)));
        System.out.println(String.format("extendsOf(%s, %s) = %s", arrayList, Collection.class, ClassUtils.extendsOf(arrayList, Collection.class)));
        System.out.println(String.format("extendsOf(%s, %s) = %s", arrayList, List.class, ClassUtils.extendsOf(arrayList, List.class)));
        System.out.println(String.format("extendsOf(%s, %s) = %s", arrayList, Iterable.class, ClassUtils.extendsOf(arrayList, Iterable.class)));
        System.out.println(String.format("extendsOf(%s, %s) = %s", arrayList, AbstractList.class, ClassUtils.extendsOf(arrayList, AbstractList.class)));
        System.out.println(String.format("extendsOf(%s, %s) = %s", arrayList, AbstractCollection.class, ClassUtils.extendsOf(arrayList, AbstractCollection.class)));

        System.out.println("----- instanceOf -----");
        System.out.println(String.format("instanceOf(%s, %s) = %s", arrayList, ArrayList.class, ClassUtils.instanceOf(arrayList, ArrayList.class)));
        System.out.println(String.format("instanceOf(%s, %s) = %s", arrayList, Collection.class, ClassUtils.instanceOf(arrayList, Collection.class)));
        System.out.println(String.format("instanceOf(%s, %s) = %s", arrayList, List.class, ClassUtils.instanceOf(arrayList, List.class)));
        System.out.println(String.format("instanceOf(%s, %s) = %s", arrayList, Iterable.class, ClassUtils.instanceOf(arrayList, Iterable.class)));
        System.out.println(String.format("instanceOf(%s, %s) = %s", arrayList, AbstractList.class, ClassUtils.instanceOf(arrayList, AbstractList.class)));
        System.out.println(String.format("instanceOf(%s, %s) = %s", arrayList, AbstractCollection.class, ClassUtils.instanceOf(arrayList, AbstractCollection.class)));

    }

    @Test
    public void classLoaderTest() {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        System.out.println(String.format("getDefaultClassLoader() = %s", classLoader));
        // /D:/workspace/IdeaProjects/my/beehive-parent/beehive-core/target/classes/string/factory/common-characters.properties
        System.out.println(String.format("\tclassLoader.getResource(\"%s\") = %s", "string/factory/common-characters.properties", classLoader.getResource("string/factory/common-characters.properties").getPath()));
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

}
