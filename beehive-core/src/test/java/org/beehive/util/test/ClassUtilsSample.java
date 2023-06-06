/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.util.test;

import org.beehive.core.reflection.FieldMatcher;
import org.beehive.util.ClassUtils;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class ClassUtilsSample {

    // 泛型接口
    @InterfaceAnnotation
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

    // 泛型类
    class ClassA<E> {

        private int a;
        int b;
        protected int c;
        public int d;

        private int aa;

        private E e;

        public static final int ddd = 123;

        ClassA() {
        }

        private ClassA(int a) {
            this.a = a;
        }

        ClassA(int a, int b) {
            this.a = a;
            this.b = b;
        }

        protected ClassA(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public ClassA(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        private int getA() {
            return 'a';
        }

        int getB() {
            return 'b';
        }

        protected int getC() {
            return 'c';
        }

        public int getD() {
            return 'd';
        }

    }

    // 普通类继承泛型类
    class ClassB extends ClassA<Integer> {

        private String a;
        String b;
        protected String c;
        public String d;

        private int aa;

        ClassB() {
        }

        private ClassB(String a) {
            this.a = a;
        }

        ClassB(String a, String b) {
            this.a = a;
            this.b = b;
        }

        protected ClassB(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public ClassB(String a, String b, String c, String d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        private String getA() {
            return "a";
        }

        String getBB() {
            return "b";
        }

        protected String getCC() {
            return "C";
        }

        public String getDD() {
            return "D";
        }

    }

    //普通类
    @ClassAnnotation
    class ClassC {
        class ClassC1 {

        }

    }

    // 普通类实现泛型接口
    class ClassD implements InterfaceA<Integer> {

    }

    class StringArrayList extends ArrayList<String> {


    }

    @Deprecated
    class ClassE extends ClassC implements InterfaceA {

    }


    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @interface InterfaceAnnotation {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @interface ClassAnnotation {

    }


    public static void main(String[] args) {
//        List<Field> field1 = ClassUtils.getFieldList(ClassA.class, true);
//        for (Field field : field1) {
//            System.out.println(field.getName() + "\t" + field.getDeclaringClass().getName() + "\t" + field);
//        }
//        List<Field> field2 = ClassUtils.getFieldList(ClassB.class, true);
//        System.out.println();
//        for (Field field : field2) {
//            System.out.println(field.getName() + "\t" + field.getDeclaringClass().getName() + "\t" + field + "\t => \t" + (field.getType() == String.class));
//        }

//
//        System.out.println("\n\n-------------------------------------------------------------------------");
//
//        List<Constructor<?>> constructor1 = ClassUtils.getConstructorList(ClassA.class);
//        for (Constructor field : constructor1) {
//            System.out.println(field.getName() + "\t" + field.getDeclaringClass().getName() + "\t" + field);
//        }
//        System.out.println();
//        List<Constructor<?>> constructor2 = ClassUtils.getConstructorList(ClassB.class);
//        for (Constructor field : constructor2) {
//            System.out.println(field.getName() + "\t" + field.getDeclaringClass().getName() + "\t" + field);
//        }
//
//        System.out.println("\n\n-------------------------------------------------------------------------");
//
//        List<Method> method1 = ClassUtils.getMethodList(ClassA.class, true);
//        for (Method field : method1) {
//            System.out.println(field.getName() + "\t" + field.getDeclaringClass().getName() + "\t" + field);
//        }
//        System.out.println();
//        List<Method> method2 = ClassUtils.getMethodList(ClassB.class, true);
//        for (Method field : method2) {
//            System.out.println(field.getName() + "\t" + field.getDeclaringClass().getName() + "\t" + field);
//        }

        FieldMatcher fieldMatcher = new FieldMatcher.Builder("ddd").typeOf(int.class).build();
        Field[] fields = ClassUtils.getContainFields(ClassB.class, fieldMatcher);
        System.out.println(Arrays.toString(fields));
        System.out.println(fields[0] + "\t" + fields[0].getDeclaringClass());
        Field[] field = ClassUtils.getDeclaredFields(ClassB.class, fieldMatcher);
        System.out.println(field);

        Field[] field1 = ClassUtils.getContainFields(ClassA.class, fieldMatcher);
        System.out.println(field1[0].getModifiers());
        System.out.println((Modifier.STATIC | Modifier.FINAL | Modifier.PUBLIC));
        System.out.println(Modifier.PUBLIC + "\t" + (Modifier.fieldModifiers() & Modifier.PUBLIC));

        FieldMatcher fieldMatcher2 = new FieldMatcher.Builder("a").typeOf(int.class).build();
        Field[] field2 = ClassUtils.getDeclaredFields(ClassA.class, fieldMatcher2);
        System.out.println(field2[0].getModifiers());

        System.out.println("\n\n-------------------------------------------------------------------------");

        Constructor constructor = ClassUtils.getDeclaredConstructor(ClassB.class);
        System.out.println(constructor);
        Constructor constructor1 = ClassUtils.getDeclaredConstructor(ClassB.class, String.class, String.class);
        System.out.println(constructor1);
        Constructor constructor2 = ClassUtils.getDeclaredConstructor(ClassB.class, String.class, int.class);
        System.out.println(constructor2);

    }


}
