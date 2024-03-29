/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.util.test;

import org.beehive.core.dictionary.DictionaryEnumeration;
import org.beehive.util.EnumUtils;
import org.junit.Test;

import java.util.List;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class EnumUtilsTest {

    enum ClassType {
        NUMBER("number", "数字"),
        STRING("str", "字符串");

        private String code;

        private String title;

        ClassType(String code, String title) {
            this.code = code;
            this.title = title;
        }

        public String getCode() {
            return this.code;
        }

        public String getTitle() {
            return this.title;
        }
    }

    enum ClassItem implements DictionaryEnumeration<String> {

        NUMBER("number", "数字"),
        STRING("str", "字符串");

        private String code;

        private String title;

        ClassItem(String code, String title) {
            this.code = code;
            this.title = title;
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public String getTitle() {
            return this.title;
        }
    }

    @Test
    public void testItems() {
        System.out.println(String.format("getInstances(%s) = ", ClassType.class));
        List<ClassType> classTypeList = EnumUtils.getInstances(ClassType.class);
        for (ClassType classType : classTypeList) {
            System.out.println("\t" + classType);
        }

        System.out.println(String.format("getInstances(%s) = ", ClassItem.class));
        List<ClassItem> classItemList = EnumUtils.getInstances(ClassItem.class);
        for (ClassItem classItem : classItemList) {
            System.out.println("\t" + classItem);
        }
    }

    @Test
    public void testToString() {
        List<ClassType> classTypeList = EnumUtils.getInstances(ClassType.class);
        for (ClassType classType : classTypeList) {
            System.out.println(String.format("toString(%s) = %s", classType, EnumUtils.toString(classType)));
        }

        List<ClassItem> classItemList = EnumUtils.getInstances(ClassItem.class);
        for (ClassItem classItem : classItemList) {
            System.out.println(String.format("toString(%s) = %s", classItem, EnumUtils.toString(classItem)));
        }
    }


    @Test
    public void testInstanceOf() {
        System.out.println(String.format("getInstances(%s, %s, %s) = toString() -->", ClassType.class, "str", "code"));
        List<ClassType> classTypeList = EnumUtils.getInstances(ClassType.class, "str", "code");
        for (ClassType classType : classTypeList) {
            System.out.println("\t" + EnumUtils.toString(classType));
        }
        System.out.println(String.format("getInstances(%s, %s, %s) = toString() -->", ClassItem.class, "STR", "code"));
        List<ClassItem> classItemList = EnumUtils.getInstances(ClassItem.class, "STR", "code");
        for (ClassItem classItem : classItemList) {
            System.out.println("\t" + EnumUtils.toString(classItem));
        }

        System.out.println(String.format("getInstances(%s, %s) = toString() -->", ClassType.class, "number"));
        classTypeList = EnumUtils.getInstances(ClassType.class, "number");
        for (ClassType classType : classTypeList) {
            System.out.println("\t" + EnumUtils.toString(classType));
        }
        System.out.println(String.format("getInstances(%s, %s) = toString() -->", ClassItem.class, "NUMBER"));
        classItemList = EnumUtils.getInstances(ClassItem.class, "NUMBER");
        for (ClassItem classItem : classItemList) {
            System.out.println("\t" + EnumUtils.toString(classItem));
        }

        ClassType classType = EnumUtils.getInstanceOrDefault(ClassType.class, "1", ClassType.NUMBER);
        System.out.println(String.format("getInstanceOrDefault(%s, %s, %s) = toString() --> %s", ClassType.class, "1", ClassType.NUMBER, EnumUtils.toString(classType)));
        ClassItem classItem = EnumUtils.getInstanceOrDefault(ClassItem.class, "1", ClassItem.NUMBER);
        System.out.println(String.format("getInstanceOrDefault(%s, %s, %s) = toString() --> %s", ClassItem.class, "1", ClassItem.NUMBER, EnumUtils.toString(classItem)));

        classType = EnumUtils.getInstance(ClassType.class, "1");
        System.out.println(String.format("getInstanceOrDefault(%s, %s) = toString() --> %s", ClassType.class, "1", EnumUtils.toString(classType)));
        classItem = EnumUtils.getInstance(ClassItem.class, "1");
        System.out.println(String.format("getInstanceOrDefault(%s, %s) = toString() --> %s", ClassItem.class, "1", EnumUtils.toString(classItem)));
    }

    @Test
    public void testIsInstance() {
        System.out.println(String.format("isEnumInstance(%s, %s, %s ,%s) --> %s", ClassType.class, null, "code", false, EnumUtils.isEnumInstance(ClassType.class, null, "code", false)));
        System.out.println(String.format("isEnumInstance(%s, %s, %s) --> %s", ClassItem.class, "STR", "code", EnumUtils.isEnumInstance(ClassItem.class, "STR", "code")));
        System.out.println(String.format("isEnumInstance(%s, %s) --> %s", ClassType.class, "STRING", EnumUtils.isEnumInstance(ClassType.class, "STRING")));
        System.out.println(String.format("isEnumInstance(%s, %s, %s) --> %s", ClassItem.class, null, false, EnumUtils.isEnumInstance(ClassItem.class, null, false)));
    }

}
