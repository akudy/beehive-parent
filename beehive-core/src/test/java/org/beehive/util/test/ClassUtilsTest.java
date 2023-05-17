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

import org.beehive.core.reflection.FieldMatcher;
import org.beehive.util.ClassUtils;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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

    @Test
    public void annotationTest() {
        System.out.println(String.format("hasAnnotation(%s, %s) => %s", InterfaceA.class, ClassAnnotation.class, ClassUtils.hasAnnotation(InterfaceA.class, ClassAnnotation.class)));
        System.out.println(String.format("hasAnnotation(%s, %s) => %s", InterfaceA.class, InterfaceAnnotation.class, ClassUtils.hasAnnotation(InterfaceA.class, InterfaceAnnotation.class)));
        System.out.println(String.format("hasAnnotation(%s, %s) => %s", InterfaceB.class, InterfaceAnnotation.class, ClassUtils.hasAnnotation(InterfaceB.class, InterfaceAnnotation.class)));
        System.out.println(String.format("hasAnnotation(%s, %s) => %s", ClassA.class, ClassAnnotation.class, ClassUtils.hasAnnotation(ClassA.class, ClassAnnotation.class)));
        System.out.println(String.format("hasAnnotation(%s, %s) => %s", ClassA.class, ClassAnnotation.class, ClassUtils.hasAnnotation(ClassA.class, ClassAnnotation.class)));
        System.out.println(String.format("hasAnnotation(%s, %s) => %s", ClassB.class, ClassAnnotation.class, ClassUtils.hasAnnotation(ClassB.class, ClassAnnotation.class)));
        System.out.println();
        System.out.println(String.format("getAnnotations(%s) => %s", InterfaceA.class, Arrays.toString(ClassUtils.getAnnotations(InterfaceA.class))));
        System.out.println(String.format("getAnnotations(%s) => %s", InterfaceB.class, Arrays.toString(ClassUtils.getAnnotations(InterfaceB.class))));
        System.out.println(String.format("getAnnotations(%s) => %s", InterfaceC.class, Arrays.toString(ClassUtils.getAnnotations(InterfaceC.class))));
        System.out.println(String.format("getAnnotations(%s) => %s", InterfaceD.class, Arrays.toString(ClassUtils.getAnnotations(InterfaceD.class))));
        System.out.println(String.format("getAnnotations(%s) => %s", ClassA.class, Arrays.toString(ClassUtils.getAnnotations(ClassA.class))));
        System.out.println(String.format("getAnnotations(%s) => %s", ClassB.class, Arrays.toString(ClassUtils.getAnnotations(ClassB.class))));
        System.out.println(String.format("getAnnotations(%s) => %s", ClassC.class, Arrays.toString(ClassUtils.getAnnotations(ClassC.class))));
        System.out.println(String.format("getAnnotations(%s) => %s", ClassD.class, Arrays.toString(ClassUtils.getAnnotations(ClassD.class))));
    }

    public static Class[] randomClassOfArray(int length) {
        Class[] allClassArray = new Class[]{InterfaceA.class, InterfaceB.class, InterfaceC.class, InterfaceD.class, InterfaceE.class, InterfaceF.class, InterfaceG.class, InterfaceH.class,
                ClassA.class, ClassB.class, ClassC.class, ClassD.class, ClassE.class, ClassF.class, ClassG.class, ClassH.class, ClassJ.class, ClassO.class};
        Class[] classArray = new Class[length];
        int allLength = allClassArray.length;
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * allLength) + 1;
            Class clazz = allClassArray[index];
            for (int j = 0; j < i; j++) {
                if (classArray[j] == clazz) {
                    i--;
                    break;
                }
            }
            classArray[i] = clazz;
        }
        return classArray;
    }

    public static String[] randomStringOfArray(String[] sourceArray, int length) {
        String[] targetArray = new String[length];
        int allLength = sourceArray.length;
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * allLength) + 1;
            String clazz = sourceArray[index];
            for (int j = 0; j < i; j++) {
                if (targetArray[j] == clazz) {
                    i--;
                    break;
                }
            }
            targetArray[i] = clazz;
        }
        return targetArray;
    }

    @Test
    public void fieldTest() {
        String[] allFieldNameArray = new String[]{"InterfaceA_FieldA", "InterfaceB_FieldA", "InterfaceC_FieldA", "InterfaceD_FieldA", "InterfaceE_FieldA", "InterfaceF_FieldA", "InterfaceG_FieldA", "InterfaceH_FieldA",
                "Private_ClassA_FieldA", "Private_ClassA_Static_FieldA", "Private_ClassA_Final_FieldA", "Private_ClassA_Static_Final_FieldA", "ClassA_FieldB", "ClassA_Static_FieldB", "ClassA_Final_FieldB", "ClassA_Static_Final_FieldB",
                "Protected_ClassA_FieldC", "Protected_ClassA_Static_FieldC", "Protected_ClassA_Final_FieldC", "Protected_ClassA_Static_Final_FieldC", "Public_ClassA_FieldD", "Public_ClassA_Static_FieldD", "Public_ClassA_Final_FieldD", "Public_ClassA_Static_Final_FieldD",
                "Private_ClassB_FieldA", "Private_ClassB_Static_FieldA", "Private_ClassB_Final_FieldA", "Private_ClassB_Static_Final_FieldA", "ClassB_FieldB", "ClassB_Static_FieldB", "ClassB_Final_FieldB", "ClassB_Static_Final_FieldB",
                "Protected_ClassB_FieldC", "Protected_ClassB_Static_FieldC", "Protected_ClassB_Final_FieldC", "Protected_ClassB_Static_Final_FieldC", "Public_ClassB_FieldD", "Public_ClassB_Static_FieldD", "Public_ClassB_Final_FieldD", "Public_ClassB_Static_Final_FieldD",
                "Private_ClassC_FieldA", "Private_ClassC_Static_FieldA", "Private_ClassC_Final_FieldA", "Private_ClassC_Static_Final_FieldA", "ClassC_FieldB", "ClassC_Static_FieldB", "ClassC_Final_FieldB", "ClassC_Static_Final_FieldB",
                "Protected_ClassC_FieldC", "Protected_ClassC_Static_FieldC", "Protected_ClassC_Final_FieldC", "Protected_ClassC_Static_Final_FieldC", "Public_ClassC_FieldD", "Public_ClassC_Static_FieldD", "Public_ClassC_Final_FieldD", "Public_ClassC_Static_Final_FieldD",
                "Private_ClassD_FieldA", "Private_ClassD_Static_FieldA", "Private_ClassD_Final_FieldA", "Private_ClassD_Static_Final_FieldA", "ClassD_FieldB", "ClassD_Static_FieldB", "ClassD_Final_FieldB", "ClassD_Static_Final_FieldB",
                "Protected_ClassD_FieldC", "Protected_ClassD_Static_FieldC", "Protected_ClassD_Final_FieldC", "Protected_ClassD_Static_Final_FieldC", "Public_ClassD_FieldD", "Public_ClassD_Static_FieldD", "Public_ClassD_Final_FieldD", "Public_ClassD_Static_Final_FieldD",
                "Private_ClassE_FieldA", "Private_ClassE_Static_FieldA", "Private_ClassE_Final_FieldA", "Private_ClassE_Static_Final_FieldA", "ClassE_FieldB", "ClassE_Static_FieldB", "ClassE_Final_FieldB", "ClassE_Static_Final_FieldB",
                "Protected_ClassE_FieldC", "Protected_ClassE_Static_FieldC", "Protected_ClassE_Final_FieldC", "Protected_ClassE_Static_Final_FieldC", "Public_ClassE_FieldD", "Public_ClassE_Static_FieldD", "Public_ClassE_Final_FieldD", "Public_ClassE_Static_Final_FieldD",
                "Private_ClassF_FieldA", "Private_ClassF_Static_FieldA", "Private_ClassF_Final_FieldA", "Private_ClassF_Static_Final_FieldA", "ClassF_FieldB", "ClassF_Static_FieldB", "ClassF_Final_FieldB", "ClassF_Static_Final_FieldB",
                "Protected_ClassF_FieldC", "Protected_ClassF_Static_FieldC", "Protected_ClassF_Final_FieldC", "Protected_ClassF_Static_Final_FieldC", "Public_ClassF_FieldD", "Public_ClassF_Static_FieldD", "Public_ClassF_Final_FieldD", "Public_ClassF_Static_Final_FieldD",
                "Private_ClassG_FieldA", "Private_ClassG_Static_FieldA", "Private_ClassG_Final_FieldA", "Private_ClassG_Static_Final_FieldA", "ClassG_FieldB", "ClassG_Static_FieldB", "ClassG_Final_FieldB", "ClassG_Static_Final_FieldB",
                "Protected_ClassG_FieldC", "Protected_ClassG_Static_FieldC", "Protected_ClassG_Final_FieldC", "Protected_ClassG_Static_Final_FieldC", "Public_ClassG_FieldD", "Public_ClassG_Static_FieldD", "Public_ClassG_Final_FieldD", "Public_ClassG_Static_Final_FieldD",
                "Private_ClassH_FieldA", "Private_ClassH_Static_FieldA", "Private_ClassH_Final_FieldA", "Private_ClassH_Static_Final_FieldA", "ClassH_FieldB", "ClassH_Static_FieldB", "ClassH_Final_FieldB", "ClassH_Static_Final_FieldB",
                "Protected_ClassH_FieldC", "Protected_ClassH_Static_FieldC", "Protected_ClassH_Final_FieldC", "Protected_ClassH_Static_Final_FieldC", "Public_ClassH_FieldD", "Public_ClassH_Static_FieldD", "Public_ClassH_Final_FieldD", "Public_ClassH_Static_Final_FieldD",
                "Private_ClassJ_FieldA", "Private_ClassJ_Static_FieldA", "Private_ClassJ_Final_FieldA", "Private_ClassJ_Static_Final_FieldA", "ClassJ_FieldB", "ClassJ_Static_FieldB", "ClassJ_Final_FieldB", "ClassJ_Static_Final_FieldB",
                "Protected_ClassJ_FieldC", "Protected_ClassJ_Static_FieldC", "Protected_ClassJ_Final_FieldC", "Protected_ClassJ_Static_Final_FieldC", "Public_ClassJ_FieldD", "Public_ClassJ_Static_FieldD", "Public_ClassJ_Final_FieldD", "Public_ClassJ_Static_Final_FieldD",
                "Private_ClassO_FieldA", "Private_ClassO_Static_FieldA", "Private_ClassO_Final_FieldA", "Private_ClassO_Static_Final_FieldA", "ClassO_FieldB", "ClassO_Static_FieldB", "ClassO_Final_FieldB", "ClassO_Static_Final_FieldB",
                "Protected_ClassO_FieldC", "Protected_ClassO_Static_FieldC", "Protected_ClassO_Final_FieldC", "Protected_ClassO_Static_Final_FieldC", "Public_ClassO_FieldD", "Public_ClassO_Static_FieldD", "Public_ClassO_Final_FieldD", "Public_ClassO_Static_Final_FieldD",
                "Private_ClassA_E_Field", "Private_ClassB_T_Field", "Private_ClassE_T_Field", "Private_ClassO_K_Field", "Private_ClassF_T_Field"};
        Class[] classArray = randomClassOfArray(5);
        String[] fieldNameArray = randomStringOfArray(allFieldNameArray, 2);
        System.out.println("------ find field -------");
        Field[] fields = null;
        Field field = null;
        FieldMatcher fieldMatcher = null;
        for (Class<?> clazz : classArray) {
            for (String fieldName : fieldNameArray) {
                fields = ClassUtils.getContainFields(clazz, fieldName);
                field = ClassUtils.getDeclaredField(clazz, fieldName);
                System.out.println(String.format("getContainFields(%s, %s) => %s", clazz, fieldName, Arrays.toString(fields)));
                System.out.println(String.format("getDeclaredField(%s, %s) => %s", clazz, fieldName, field));
                fieldMatcher = new FieldMatcher.Builder(fieldName)
                        .typeOf(String.class)
                        .modifierOf(Modifier.PUBLIC, Modifier.FINAL)
                        .build();
                fields = ClassUtils.getContainFields(clazz, fieldMatcher);
                System.out.println(String.format("getContainFields(%s, %s) => %s", clazz, FieldMatcher.class, Arrays.toString(fields)));
                fields = ClassUtils.getDeclaredFields(clazz, fieldMatcher);
                System.out.println(String.format("getDeclaredFields(%s, %s) => %s", clazz, FieldMatcher.class, Arrays.toString(fields)));
            }
        }

        System.out.println("------ has field -------");
        for (Class<?> clazz : classArray) {
            for (String fieldName : fieldNameArray) {
                System.out.println(String.format("containField(%s, %s) => %s", clazz, fieldName, ClassUtils.containField(clazz, fieldName)));
                System.out.println(String.format("declaredField(%s, %s) => %s", clazz, fieldName, ClassUtils.declaredField(clazz, fieldName)));
                fieldMatcher = new FieldMatcher.Builder(fieldName)
                        .typeOf(String.class)
                        .modifierOf(Modifier.PUBLIC, Modifier.FINAL)
                        .build();
                System.out.println(String.format("containField(%s, %s) => %s", clazz, FieldMatcher.class, ClassUtils.containField(clazz, fieldMatcher)));
                System.out.println(String.format("declaredField(%s, %s) => %s", clazz, FieldMatcher.class, ClassUtils.declaredField(clazz, fieldMatcher)));
            }
        }
    }

    @Test
    public void constructorTest() {

    }

    @Test
    public void methodTest() {

    }

    /*********************************************************************/
    // 泛型接口
    @InterfaceAnnotation
    interface InterfaceA<E> {
        String InterfaceA_FieldA = "InterfaceA_FieldA";
    }

    // 普通接口继承泛型接口
    interface InterfaceB extends InterfaceA<Integer> {
        String InterfaceA_FieldA = "InterfaceA_FieldA";
        String InterfaceB_FieldA = "InterfaceB_FieldA";
    }

    // 普通接口
    @InterfaceAnnotation2
    interface InterfaceC {
        String InterfaceC_FieldA = "InterfaceC_FieldA";
    }

    // 普通接口
    @InterfaceAnnotation
    @Deprecated
    interface InterfaceD extends InterfaceB {
        String InterfaceA_FieldA = "InterfaceA_FieldA";
        String InterfaceB_FieldA = "InterfaceB_FieldA";
        String InterfaceD_FieldA = "InterfaceD_FieldA";
    }

    // 泛型子接口
    interface InterfaceE<T> extends InterfaceA<T>, Comparable<T> {
        String InterfaceA_FieldA = "InterfaceA_FieldA";
        String InterfaceE_FieldA = "InterfaceE_FieldA";
    }

    //普通子接口
    interface InterfaceF extends InterfaceE<String> {
        String InterfaceA_FieldA = "InterfaceA_FieldA";
        String InterfaceE_FieldA = "InterfaceE_FieldA";
        String InterfaceF_FieldA = "InterfaceF_FieldA";
    }

    //普通子接口
    interface InterfaceG extends InterfaceA {
        String InterfaceA_FieldA = "InterfaceA_FieldA";
        String InterfaceG_FieldA = "InterfaceG_FieldA";
    }

    //普通子接口
    interface InterfaceH extends InterfaceB {
        String InterfaceA_FieldA = "InterfaceA_FieldA";
        String InterfaceB_FieldA = "InterfaceB_FieldA";
        String InterfaceH_FieldA = "InterfaceH_FieldA";
    }

    // 泛型类
    @ClassAnnotation
    static class ClassA<E> {
        private String Private_ClassA_FieldA = "Private_ClassA_FieldA";
        private static String Private_ClassA_Static_FieldA = "Private_ClassA_Static_FieldA";
        private final String Private_ClassA_Final_FieldA = "Private_ClassA_Final_FieldA";
        private static final String Private_ClassA_Static_Final_FieldA = "Private_ClassA_Static_Final_FieldA";

        String ClassA_FieldB = "ClassA_FieldB";
        static String ClassA_Static_FieldB = "ClassA_Static_FieldB";
        final String ClassA_Final_FieldB = "ClassA_Final_FieldB";
        static final String ClassA_Static_Final_FieldB = "ClassA_Static_Final_FieldB";

        protected String Protected_ClassA_FieldC = "Protected_ClassA_FieldC";
        protected static String Protected_ClassA_Static_FieldC = "Protected_ClassA_Static_FieldC";
        protected final String Protected_ClassA_Final_FieldC = "Protected_ClassA_Final_FieldC";
        protected static final String Protected_ClassA_Static_Final_FieldC = "Protected_ClassA_Static_Final_FieldC";

        public String Public_ClassA_FieldD = "Public_ClassA_FieldD";
        public static String Public_ClassA_Static_FieldD = "Public_ClassA_Static_FieldD";
        public final String Public_ClassA_Final_FieldD = "Public_ClassA_Final_FieldD";
        public static final String Public_ClassA_Static_Final_FieldD = "Public_ClassA_Static_Final_FieldD";

        private E Private_ClassA_E_Field;

        private ClassA() {
        }

        ClassA(String name) {
        }

        protected ClassA(int age, String name) {
        }

        public ClassA(char sex, int age, String name) {
        }
    }

    // 普通类继承泛型类
    static class ClassB extends ClassA<Integer> {
        private String Private_ClassA_FieldA = "Private_ClassA_FieldA";
        private static String Private_ClassA_Static_FieldA = "Private_ClassA_Static_FieldA";
        private final String Private_ClassA_Final_FieldA = "Private_ClassA_Final_FieldA";
        private static final String Private_ClassA_Static_Final_FieldA = "Private_ClassA_Static_Final_FieldA";
        private String Private_ClassB_FieldA = "Private_ClassB_FieldA";
        private static String Private_ClassB_Static_FieldA = "Private_ClassB_Static_FieldA";
        private final String Private_ClassB_Final_FieldA = "Private_ClassB_Final_FieldA";
        private static final String Private_ClassB_Static_Final_FieldA = "Private_ClassB_Static_Final_FieldA";

        String ClassA_FieldB = "ClassA_FieldB";
        static String ClassA_Static_FieldB = "ClassA_Static_FieldB";
        final String ClassA_Final_FieldB = "ClassA_Final_FieldB";
        static final String ClassA_Static_Final_FieldB = "ClassA_Static_Final_FieldB";
        String ClassB_FieldB = "ClassB_FieldB";
        static String ClassB_Static_FieldB = "ClassB_Static_FieldB";
        final String ClassB_Final_FieldB = "ClassB_Final_FieldB";
        static final String ClassB_Static_Final_FieldB = "ClassB_Static_Final_FieldB";

        protected String Protected_ClassA_FieldC = "Protected_ClassA_FieldC";
        protected static String Protected_ClassA_Static_FieldC = "Protected_ClassA_Static_FieldC";
        protected final String Protected_ClassA_Final_FieldC = "Protected_ClassA_Final_FieldC";
        protected static final String Protected_ClassA_Static_Final_FieldC = "Protected_ClassA_Static_Final_FieldC";
        protected String Protected_ClassB_FieldC = "Protected_ClassB_FieldC";
        protected static String Protected_ClassB_Static_FieldC = "Protected_ClassB_Static_FieldC";
        protected final String Protected_ClassB_Final_FieldC = "Protected_ClassB_Final_FieldC";
        protected static final String Protected_ClassB_Static_Final_FieldC = "Protected_ClassB_Static_Final_FieldC";

        public String Public_ClassA_FieldD = "Public_ClassA_FieldD";
        public static String Public_ClassA_Static_FieldD = "Public_ClassA_Static_FieldD";
        public final String Public_ClassA_Final_FieldD = "Public_ClassA_Final_FieldD";
        public static final String Public_ClassA_Static_Final_FieldD = "Public_ClassA_Static_Final_FieldD";
        public String Public_ClassB_FieldD = "Public_ClassB_FieldD";
        public static String Public_ClassB_Static_FieldD = "Public_ClassB_Static_FieldD";
        public final String Public_ClassB_Final_FieldD = "Public_ClassB_Final_FieldD";
        public static final String Public_ClassB_Static_Final_FieldD = "Public_ClassB_Static_Final_FieldD";

        private Integer Private_ClassA_E_Field;
        private Integer Private_ClassB_T_Field;

        private ClassB() {
        }

        ClassB(String name) {
        }

        protected ClassB(int age, String name) {
        }

        public ClassB(char sex, int age, String name) {
        }
    }

    // 普通类
    @ClassAnnotation2
    static class ClassC {
        private String Private_ClassC_FieldA = "Private_ClassC_FieldA";
        private static String Private_ClassC_Static_FieldA = "Private_ClassC_Static_FieldA";
        private final String Private_ClassC_Final_FieldA = "Private_ClassC_Final_FieldA";
        private static final String Private_ClassC_Static_Final_FieldA = "Private_ClassC_Static_Final_FieldA";

        String ClassC_FieldB = "ClassC_FieldB";
        static String ClassC_Static_FieldB = "ClassC_Static_FieldB";
        final String ClassC_Final_FieldB = "ClassC_Final_FieldB";
        static final String ClassC_Static_Final_FieldB = "ClassC_Static_Final_FieldB";

        protected String Protected_ClassC_FieldC = "Protected_ClassC_FieldC";
        protected static String Protected_ClassC_Static_FieldC = "Protected_ClassC_Static_FieldC";
        protected final String Protected_ClassC_Final_FieldC = "Protected_ClassC_Final_FieldC";
        protected static final String Protected_ClassC_Static_Final_FieldC = "Protected_ClassC_Static_Final_FieldC";

        public String Public_ClassC_FieldD = "Public_ClassC_FieldD";
        public static String Public_ClassC_Static_FieldD = "Public_ClassC_Static_FieldD";
        public final String Public_ClassC_Final_FieldD = "Public_ClassC_Final_FieldD";
        public static final String Public_ClassC_Static_Final_FieldD = "Public_ClassC_Static_Final_FieldD";

        private ClassC() {
        }

        ClassC(String name) {
        }

        protected ClassC(int age, String name) {
        }

        public ClassC(char sex, int age, String name) {
        }
    }

    // 普通类
    @ClassAnnotation
    @Deprecated
    static class ClassD extends ClassB {
        private String Private_ClassA_FieldA = "Private_ClassA_FieldA";
        private static String Private_ClassA_Static_FieldA = "Private_ClassA_Static_FieldA";
        private final String Private_ClassA_Final_FieldA = "Private_ClassA_Final_FieldA";
        private static final String Private_ClassA_Static_Final_FieldA = "Private_ClassA_Static_Final_FieldA";
        private String Private_ClassB_FieldA = "Private_ClassB_FieldA";
        private static String Private_ClassB_Static_FieldA = "Private_ClassB_Static_FieldA";
        private final String Private_ClassB_Final_FieldA = "Private_ClassB_Final_FieldA";
        private static final String Private_ClassB_Static_Final_FieldA = "Private_ClassB_Static_Final_FieldA";
        private String Private_ClassD_FieldA = "Private_ClassD_FieldA";
        private static String Private_ClassD_Static_FieldA = "Private_ClassD_Static_FieldA";
        private final String Private_ClassD_Final_FieldA = "Private_ClassD_Final_FieldA";
        private static final String Private_ClassD_Static_Final_FieldA = "Private_ClassD_Static_Final_FieldA";

        String ClassA_FieldB = "ClassA_FieldB";
        static String ClassA_Static_FieldB = "ClassA_Static_FieldB";
        final String ClassA_Final_FieldB = "ClassA_Final_FieldB";
        static final String ClassA_Static_Final_FieldB = "ClassA_Static_Final_FieldB";
        String ClassB_FieldB = "ClassB_FieldB";
        static String ClassB_Static_FieldB = "ClassB_Static_FieldB";
        final String ClassB_Final_FieldB = "ClassB_Final_FieldB";
        static final String ClassB_Static_Final_FieldB = "ClassB_Static_Final_FieldB";
        String ClassD_FieldB = "ClassD_FieldB";
        static String ClassD_Static_FieldB = "ClassD_Static_FieldB";
        final String ClassD_Final_FieldB = "ClassD_Final_FieldB";
        static final String ClassD_Static_Final_FieldB = "ClassD_Static_Final_FieldB";

        protected String Protected_ClassA_FieldC = "Protected_ClassA_FieldC";
        protected static String Protected_ClassA_Static_FieldC = "Protected_ClassA_Static_FieldC";
        protected final String Protected_ClassA_Final_FieldC = "Protected_ClassA_Final_FieldC";
        protected static final String Protected_ClassA_Static_Final_FieldC = "Protected_ClassA_Static_Final_FieldC";
        protected String Protected_ClassB_FieldC = "Protected_ClassB_FieldC";
        protected static String Protected_ClassB_Static_FieldC = "Protected_ClassB_Static_FieldC";
        protected final String Protected_ClassB_Final_FieldC = "Protected_ClassB_Final_FieldC";
        protected static final String Protected_ClassB_Static_Final_FieldC = "Protected_ClassB_Static_Final_FieldC";
        protected String Protected_ClassD_FieldC = "Protected_ClassD_FieldC";
        protected static String Protected_ClassD_Static_FieldC = "Protected_ClassD_Static_FieldC";
        protected final String Protected_ClassD_Final_FieldC = "Protected_ClassD_Final_FieldC";
        protected static final String Protected_ClassD_Static_Final_FieldC = "Protected_ClassD_Static_Final_FieldC";

        public String Public_ClassA_FieldD = "Public_ClassA_FieldD";
        public static String Public_ClassA_Static_FieldD = "Public_ClassA_Static_FieldD";
        public final String Public_ClassA_Final_FieldD = "Public_ClassA_Final_FieldD";
        public static final String Public_ClassA_Static_Final_FieldD = "Public_ClassA_Static_Final_FieldD";
        public String Public_ClassB_FieldD = "Public_ClassB_FieldD";
        public static String Public_ClassB_Static_FieldD = "Public_ClassB_Static_FieldD";
        public final String Public_ClassB_Final_FieldD = "Public_ClassB_Final_FieldD";
        public static final String Public_ClassB_Static_Final_FieldD = "Public_ClassB_Static_Final_FieldD";
        public String Public_ClassD_FieldD = "Public_ClassD_FieldD";
        public static String Public_ClassD_Static_FieldD = "Public_ClassD_Static_FieldD";
        public final String Public_ClassD_Final_FieldD = "Public_ClassD_Final_FieldD";
        public static final String Public_ClassD_Static_Final_FieldD = "Public_ClassD_Static_Final_FieldD";

        private Integer Private_ClassA_E_Field;
        private Integer Private_ClassB_E_Field;
        private Integer Private_ClassC_E_Field;

        private ClassD() {
        }

        ClassD(String name) {
        }

        protected ClassD(int age, String name) {
        }

        public ClassD(char sex, int age, String name) {
        }
    }

    // 泛型子类
    static class ClassE<T> extends ClassA<T> {
        private String Private_ClassA_FieldA = "Private_ClassA_FieldA";
        private static String Private_ClassA_Static_FieldA = "Private_ClassA_Static_FieldA";
        private final String Private_ClassA_Final_FieldA = "Private_ClassA_Final_FieldA";
        private static final String Private_ClassA_Static_Final_FieldA = "Private_ClassA_Static_Final_FieldA";
        private String Private_ClassE_FieldA = "Private_ClassE_FieldA";
        private static String Private_ClassE_Static_FieldA = "Private_ClassE_Static_FieldA";
        private final String Private_ClassE_Final_FieldA = "Private_ClassE_Final_FieldA";
        private static final String Private_ClassE_Static_Final_FieldA = "Private_ClassE_Static_Final_FieldA";

        String ClassA_FieldB = "ClassA_FieldB";
        static String ClassA_Static_FieldB = "ClassA_Static_FieldB";
        final String ClassA_Final_FieldB = "ClassA_Final_FieldB";
        static final String ClassA_Static_Final_FieldB = "ClassA_Static_Final_FieldB";
        String ClassE_FieldB = "ClassE_FieldB";
        static String ClassE_Static_FieldB = "ClassE_Static_FieldB";
        final String ClassE_Final_FieldB = "ClassE_Final_FieldB";
        static final String ClassE_Static_Final_FieldB = "ClassE_Static_Final_FieldB";

        protected String Protected_ClassA_FieldC = "Protected_ClassA_FieldC";
        protected static String Protected_ClassA_Static_FieldC = "Protected_ClassA_Static_FieldC";
        protected final String Protected_ClassA_Final_FieldC = "Protected_ClassA_Final_FieldC";
        protected static final String Protected_ClassA_Static_Final_FieldC = "Protected_ClassA_Static_Final_FieldC";
        protected String Protected_ClassE_FieldC = "Protected_ClassE_FieldC";
        protected static String Protected_ClassE_Static_FieldC = "Protected_ClassE_Static_FieldC";
        protected final String Protected_ClassE_Final_FieldC = "Protected_ClassE_Final_FieldC";
        protected static final String Protected_ClassE_Static_Final_FieldC = "Protected_ClassE_Static_Final_FieldC";

        public String Public_ClassA_FieldD = "Public_ClassA_FieldD";
        public static String Public_ClassA_Static_FieldD = "Public_ClassA_Static_FieldD";
        public final String Public_ClassA_Final_FieldD = "Public_ClassA_Final_FieldD";
        public static final String Public_ClassA_Static_Final_FieldD = "Public_ClassA_Static_Final_FieldD";
        public String Public_ClassE_FieldD = "Public_ClassE_FieldD";
        public static String Public_ClassE_Static_FieldD = "Public_ClassE_Static_FieldD";
        public final String Public_ClassE_Final_FieldD = "Public_ClassE_Final_FieldD";
        public static final String Public_ClassE_Static_Final_FieldD = "Public_ClassE_Static_Final_FieldD";

        private T Private_ClassA_E_Field;
        private T Private_ClassE_T_Field;

        private ClassE() {
        }

        ClassE(String name) {
        }

        protected ClassE(int age, String name) {
        }

        public ClassE(char sex, int age, String name) {
        }
    }

    //普通子类
    static class ClassF extends ClassE<String> {
        private String Private_ClassA_FieldA = "Private_ClassA_FieldA";
        private static String Private_ClassA_Static_FieldA = "Private_ClassA_Static_FieldA";
        private final String Private_ClassA_Final_FieldA = "Private_ClassA_Final_FieldA";
        private static final String Private_ClassA_Static_Final_FieldA = "Private_ClassA_Static_Final_FieldA";
        private String Private_ClassE_FieldA = "Private_ClassE_FieldA";
        private static String Private_ClassE_Static_FieldA = "Private_ClassE_Static_FieldA";
        private final String Private_ClassE_Final_FieldA = "Private_ClassE_Final_FieldA";
        private static final String Private_ClassE_Static_Final_FieldA = "Private_ClassE_Static_Final_FieldA";
        private String Private_ClassF_FieldA = "Private_ClassF_FieldA";
        private static String Private_ClassF_Static_FieldA = "Private_ClassF_Static_FieldA";
        private final String Private_ClassF_Final_FieldA = "Private_ClassF_Final_FieldA";
        private static final String Private_ClassF_Static_Final_FieldA = "Private_ClassF_Static_Final_FieldA";

        String ClassA_FieldB = "ClassA_FieldB";
        static String ClassA_Static_FieldB = "ClassA_Static_FieldB";
        final String ClassA_Final_FieldB = "ClassA_Final_FieldB";
        static final String ClassA_Static_Final_FieldB = "ClassA_Static_Final_FieldB";
        String ClassE_FieldB = "ClassE_FieldB";
        static String ClassE_Static_FieldB = "ClassE_Static_FieldB";
        final String ClassE_Final_FieldB = "ClassE_Final_FieldB";
        static final String ClassE_Static_Final_FieldB = "ClassE_Static_Final_FieldB";
        String ClassF_FieldB = "ClassF_FieldB";
        static String ClassF_Static_FieldB = "ClassF_Static_FieldB";
        final String ClassF_Final_FieldB = "ClassF_Final_FieldB";
        static final String ClassF_Static_Final_FieldB = "ClassF_Static_Final_FieldB";

        protected String Protected_ClassA_FieldC = "Protected_ClassA_FieldC";
        protected static String Protected_ClassA_Static_FieldC = "Protected_ClassA_Static_FieldC";
        protected final String Protected_ClassA_Final_FieldC = "Protected_ClassA_Final_FieldC";
        protected static final String Protected_ClassA_Static_Final_FieldC = "Protected_ClassA_Static_Final_FieldC";
        protected String Protected_ClassE_FieldC = "Protected_ClassE_FieldC";
        protected static String Protected_ClassE_Static_FieldC = "Protected_ClassE_Static_FieldC";
        protected final String Protected_ClassE_Final_FieldC = "Protected_ClassE_Final_FieldC";
        protected static final String Protected_ClassE_Static_Final_FieldC = "Protected_ClassE_Static_Final_FieldC";
        protected String Protected_ClassF_FieldC = "Protected_ClassF_FieldC";
        protected static String Protected_ClassF_Static_FieldC = "Protected_ClassF_Static_FieldC";
        protected final String Protected_ClassF_Final_FieldC = "Protected_ClassF_Final_FieldC";
        protected static final String Protected_ClassF_Static_Final_FieldC = "Protected_ClassF_Static_Final_FieldC";

        public String Public_ClassA_FieldD = "Public_ClassA_FieldD";
        public static String Public_ClassA_Static_FieldD = "Public_ClassA_Static_FieldD";
        public final String Public_ClassA_Final_FieldD = "Public_ClassA_Final_FieldD";
        public static final String Public_ClassA_Static_Final_FieldD = "Public_ClassA_Static_Final_FieldD";
        public String Public_ClassE_FieldD = "Public_ClassE_FieldD";
        public static String Public_ClassE_Static_FieldD = "Public_ClassE_Static_FieldD";
        public final String Public_ClassE_Final_FieldD = "Public_ClassE_Final_FieldD";
        public static final String Public_ClassE_Static_Final_FieldD = "Public_ClassE_Static_Final_FieldD";
        public String Public_ClassF_FieldD = "Public_ClassF_FieldD";
        public static String Public_ClassF_Static_FieldD = "Public_ClassF_Static_FieldD";
        public final String Public_ClassF_Final_FieldD = "Public_ClassF_Final_FieldD";
        public static final String Public_ClassF_Static_Final_FieldD = "Public_ClassF_Static_Final_FieldD";

        private String Private_ClassA_T_Field;
        private String Private_ClassE_T_Field;
        private String Private_ClassF_T_Field;

        private ClassF() {
        }

        ClassF(String name) {
        }

        protected ClassF(int age, String name) {
        }

        public ClassF(char sex, int age, String name) {
        }
    }

    //普通子类
    static class ClassG extends ClassA {
        private String Private_ClassA_FieldA = "Private_ClassA_FieldA";
        private static String Private_ClassA_Static_FieldA = "Private_ClassA_Static_FieldA";
        private final String Private_ClassA_Final_FieldA = "Private_ClassA_Final_FieldA";
        private static final String Private_ClassA_Static_Final_FieldA = "Private_ClassA_Static_Final_FieldA";
        private String Private_ClassG_FieldA = "Private_ClassG_FieldA";
        private static String Private_ClassG_Static_FieldA = "Private_ClassG_Static_FieldA";
        private final String Private_ClassG_Final_FieldA = "Private_ClassG_Final_FieldA";
        private static final String Private_ClassG_Static_Final_FieldA = "Private_ClassG_Static_Final_FieldA";

        String ClassA_FieldB = "ClassA_FieldB";
        static String ClassA_Static_FieldB = "ClassA_Static_FieldB";
        final String ClassA_Final_FieldB = "ClassA_Final_FieldB";
        static final String ClassA_Static_Final_FieldB = "ClassA_Static_Final_FieldB";
        String ClassG_FieldB = "ClassG_FieldB";
        static String ClassG_Static_FieldB = "ClassG_Static_FieldB";
        final String ClassG_Final_FieldB = "ClassG_Final_FieldB";
        static final String ClassG_Static_Final_FieldB = "ClassG_Static_Final_FieldB";

        protected String Protected_ClassA_FieldC = "Protected_ClassA_FieldC";
        protected static String Protected_ClassA_Static_FieldC = "Protected_ClassA_Static_FieldC";
        protected final String Protected_ClassA_Final_FieldC = "Protected_ClassA_Final_FieldC";
        protected static final String Protected_ClassA_Static_Final_FieldC = "Protected_ClassA_Static_Final_FieldC";
        protected String Protected_ClassG_FieldC = "Protected_ClassG_FieldC";
        protected static String Protected_ClassG_Static_FieldC = "Protected_ClassG_Static_FieldC";
        protected final String Protected_ClassG_Final_FieldC = "Protected_ClassG_Final_FieldC";
        protected static final String Protected_ClassG_Static_Final_FieldC = "Protected_ClassG_Static_Final_FieldC";

        public String Public_ClassA_FieldD = "Public_ClassA_FieldD";
        public static String Public_ClassA_Static_FieldD = "Public_ClassA_Static_FieldD";
        public final String Public_ClassA_Final_FieldD = "Public_ClassA_Final_FieldD";
        public static final String Public_ClassA_Static_Final_FieldD = "Public_ClassA_Static_Final_FieldD";
        public String Public_ClassG_FieldD = "Public_ClassG_FieldD";
        public static String Public_ClassG_Static_FieldD = "Public_ClassG_Static_FieldD";
        public final String Public_ClassG_Final_FieldD = "Public_ClassG_Final_FieldD";
        public static final String Public_ClassG_Static_Final_FieldD = "Public_ClassG_Static_Final_FieldD";

        private Object Private_ClassA_E_Field;

        private ClassG() {
        }

        ClassG(String name) {
        }

        protected ClassG(int age, String name) {
        }

        public ClassG(char sex, int age, String name) {
        }
    }

    //普通子类
    static class ClassH extends ClassB implements InterfaceA<String>, Comparable<String> {
        String InterfaceA_FieldA = "InterfaceA_FieldA";

        private String Private_ClassA_FieldA = "Private_ClassA_FieldA";
        private static String Private_ClassA_Static_FieldA = "Private_ClassA_Static_FieldA";
        private final String Private_ClassA_Final_FieldA = "Private_ClassA_Final_FieldA";
        private static final String Private_ClassA_Static_Final_FieldA = "Private_ClassA_Static_Final_FieldA";
        private String Private_ClassB_FieldA = "Private_ClassB_FieldA";
        private static String Private_ClassB_Static_FieldA = "Private_ClassB_Static_FieldA";
        private final String Private_ClassB_Final_FieldA = "Private_ClassB_Final_FieldA";
        private static final String Private_ClassB_Static_Final_FieldA = "Private_ClassB_Static_Final_FieldA";
        private String Private_ClassH_FieldA = "Private_ClassH_FieldA";
        private static String Private_ClassH_Static_FieldA = "Private_ClassH_Static_FieldA";
        private final String Private_ClassH_Final_FieldA = "Private_ClassH_Final_FieldA";
        private static final String Private_ClassH_Static_Final_FieldA = "Private_ClassH_Static_Final_FieldA";

        String ClassA_FieldB = "ClassA_FieldB";
        static String ClassA_Static_FieldB = "ClassA_Static_FieldB";
        final String ClassA_Final_FieldB = "ClassA_Final_FieldB";
        static final String ClassA_Static_Final_FieldB = "ClassA_Static_Final_FieldB";
        String ClassB_FieldB = "ClassB_FieldB";
        static String ClassB_Static_FieldB = "ClassB_Static_FieldB";
        final String ClassB_Final_FieldB = "ClassB_Final_FieldB";
        static final String ClassB_Static_Final_FieldB = "ClassB_Static_Final_FieldB";
        String ClassH_FieldB = "ClassH_FieldB";
        static String ClassH_Static_FieldB = "ClassH_Static_FieldB";
        final String ClassH_Final_FieldB = "ClassH_Final_FieldB";
        static final String ClassH_Static_Final_FieldB = "ClassH_Static_Final_FieldB";

        protected String Protected_ClassA_FieldC = "Protected_ClassA_FieldC";
        protected static String Protected_ClassA_Static_FieldC = "Protected_ClassA_Static_FieldC";
        protected final String Protected_ClassA_Final_FieldC = "Protected_ClassA_Final_FieldC";
        protected static final String Protected_ClassA_Static_Final_FieldC = "Protected_ClassA_Static_Final_FieldC";
        protected String Protected_ClassB_FieldC = "Protected_ClassB_FieldC";
        protected static String Protected_ClassB_Static_FieldC = "Protected_ClassB_Static_FieldC";
        protected final String Protected_ClassB_Final_FieldC = "Protected_ClassB_Final_FieldC";
        protected static final String Protected_ClassB_Static_Final_FieldC = "Protected_ClassB_Static_Final_FieldC";
        protected String Protected_ClassH_FieldC = "Protected_ClassH_FieldC";
        protected static String Protected_ClassH_Static_FieldC = "Protected_ClassH_Static_FieldC";
        protected final String Protected_ClassH_Final_FieldC = "Protected_ClassH_Final_FieldC";
        protected static final String Protected_ClassH_Static_Final_FieldC = "Protected_ClassH_Static_Final_FieldC";

        public String Public_ClassA_FieldD = "Public_ClassA_FieldD";
        public static String Public_ClassA_Static_FieldD = "Public_ClassA_Static_FieldD";
        public final String Public_ClassA_Final_FieldD = "Public_ClassA_Final_FieldD";
        public static final String Public_ClassA_Static_Final_FieldD = "Public_ClassA_Static_Final_FieldD";
        public String Public_ClassB_FieldD = "Public_ClassB_FieldD";
        public static String Public_ClassB_Static_FieldD = "Public_ClassB_Static_FieldD";
        public final String Public_ClassB_Final_FieldD = "Public_ClassB_Final_FieldD";
        public static final String Public_ClassB_Static_Final_FieldD = "Public_ClassB_Static_Final_FieldD";
        public String Public_ClassH_FieldD = "Public_ClassH_FieldD";
        public static String Public_ClassH_Static_FieldD = "Public_ClassH_Static_FieldD";
        public final String Public_ClassH_Final_FieldD = "Public_ClassH_Final_FieldD";
        public static final String Public_ClassH_Static_Final_FieldD = "Public_ClassH_Static_Final_FieldD";

        private Integer Private_ClassA_E_Field;
        private Integer Private_ClassB_T_Field;
        private Integer Private_ClassF_T_Field;

        private ClassH() {
        }

        ClassH(String name) {
        }

        protected ClassH(int age, String name) {
        }

        public ClassH(char sex, int age, String name) {
        }

        @Override
        public int compareTo(String o) {
            return 0;
        }
    }

    static class ClassJ<K> extends HashMap<K, ClassB> implements InterfaceA<String> {
        String InterfaceA_FieldA = "InterfaceA_FieldA";

        private String Private_ClassJ_FieldA = "Private_ClassJ_FieldA";
        private static String Private_ClassJ_Static_FieldA = "Private_ClassJ_Static_FieldA";
        private final String Private_ClassJ_Final_FieldA = "Private_ClassJ_Final_FieldA";
        private static final String Private_ClassJ_Static_Final_FieldA = "Private_ClassJ_Static_Final_FieldA";

        String ClassJ_FieldB = "ClassJ_FieldB";
        static String ClassJ_Static_FieldB = "ClassJ_Static_FieldB";
        final String ClassJ_Final_FieldB = "ClassJ_Final_FieldB";
        static final String ClassJ_Static_Final_FieldB = "ClassJ_Static_Final_FieldB";

        protected String Protected_ClassJ_FieldC = "Protected_ClassJ_FieldC";
        protected static String Protected_ClassJ_Static_FieldC = "Protected_ClassJ_Static_FieldC";
        protected final String Protected_ClassJ_Final_FieldC = "Protected_ClassJ_Final_FieldC";
        protected static final String Protected_ClassJ_Static_Final_FieldC = "Protected_ClassJ_Static_Final_FieldC";

        public String Public_ClassJ_FieldD = "Public_ClassJ_FieldD";
        public static String Public_ClassJ_Static_FieldD = "Public_ClassJ_Static_FieldD";
        public final String Public_ClassJ_Final_FieldD = "Public_ClassJ_Final_FieldD";
        public static final String Public_ClassJ_Static_Final_FieldD = "Public_ClassJ_Static_Final_FieldD";

        private K Private_ClassJ_K_Field;

        private ClassJ() {
        }

        ClassJ(String name) {
        }

        protected ClassJ(int age, String name) {
        }

        public ClassJ(char sex, int age, String name) {
        }
    }

    static class ClassO extends ClassJ<Integer> {
        String InterfaceA_FieldA = "InterfaceA_FieldA";

        private String Private_ClassJ_FieldA = "Private_ClassJ_FieldA";
        private static String Private_ClassJ_Static_FieldA = "Private_ClassJ_Static_FieldA";
        private final String Private_ClassJ_Final_FieldA = "Private_ClassJ_Final_FieldA";
        private static final String Private_ClassJ_Static_Final_FieldA = "Private_ClassJ_Static_Final_FieldA";
        private String Private_ClassO_FieldA = "Private_ClassO_FieldA";
        private static String Private_ClassO_Static_FieldA = "Private_ClassO_Static_FieldA";
        private final String Private_ClassO_Final_FieldA = "Private_ClassO_Final_FieldA";
        private static final String Private_ClassO_Static_Final_FieldA = "Private_ClassO_Static_Final_FieldA";

        String ClassJ_FieldB = "ClassJ_FieldB";
        static String ClassJ_Static_FieldB = "ClassJ_Static_FieldB";
        final String ClassJ_Final_FieldB = "ClassJ_Final_FieldB";
        static final String ClassJ_Static_Final_FieldB = "ClassJ_Static_Final_FieldB";
        String ClassO_FieldB = "ClassO_FieldB";
        static String ClassO_Static_FieldB = "ClassO_Static_FieldB";
        final String ClassO_Final_FieldB = "ClassO_Final_FieldB";
        static final String ClassO_Static_Final_FieldB = "ClassO_Static_Final_FieldB";

        protected String Protected_ClassJ_FieldC = "Protected_ClassJ_FieldC";
        protected static String Protected_ClassJ_Static_FieldC = "Protected_ClassJ_Static_FieldC";
        protected final String Protected_ClassJ_Final_FieldC = "Protected_ClassJ_Final_FieldC";
        protected static final String Protected_ClassJ_Static_Final_FieldC = "Protected_ClassJ_Static_Final_FieldC";
        protected String Protected_ClassO_FieldC = "Protected_ClassO_FieldC";
        protected static String Protected_ClassO_Static_FieldC = "Protected_ClassO_Static_FieldC";
        protected final String Protected_ClassO_Final_FieldC = "Protected_ClassO_Final_FieldC";
        protected static final String Protected_ClassO_Static_Final_FieldC = "Protected_ClassO_Static_Final_FieldC";

        public String Public_ClassJ_FieldD = "Public_ClassJ_FieldD";
        public static String Public_ClassJ_Static_FieldD = "Public_ClassJ_Static_FieldD";
        public final String Public_ClassJ_Final_FieldD = "Public_ClassJ_Final_FieldD";
        public static final String Public_ClassJ_Static_Final_FieldD = "Public_ClassJ_Static_Final_FieldD";
        public String Public_ClassO_FieldD = "Public_ClassO_FieldD";
        public static String Public_ClassO_Static_FieldD = "Public_ClassO_Static_FieldD";
        public final String Public_ClassO_Final_FieldD = "Public_ClassO_Final_FieldD";
        public static final String Public_ClassO_Static_Final_FieldD = "Public_ClassO_Static_Final_FieldD";

        private Integer Private_ClassJ_K_Field;
        private Integer Private_ClassO_K_Field;

        private ClassO() {
        }

        ClassO(String name) {
        }

        protected ClassO(int age, String name) {
        }

        public ClassO(char sex, int age, String name) {
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @interface InterfaceAnnotation {

    }

    @Retention(RetentionPolicy.SOURCE)
    @Inherited
    @interface InterfaceAnnotation2 {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @interface ClassAnnotation {

    }

    @Retention(RetentionPolicy.SOURCE)
    @Inherited
    @interface ClassAnnotation2 {

    }

    /***************/

}
