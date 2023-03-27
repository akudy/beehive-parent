/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.test.StringUtilsTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-10-15
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.util.test;

import org.beehive.core.string.PatternMessageBuilder;
import org.beehive.core.string.PatternStringBuilder;
import org.beehive.core.string.pattern.PatternMessage;
import org.beehive.core.string.pattern.PatternString;
import org.beehive.util.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.util.test</code></li>
 * <li>Class Name: <code>StringUtilsTest</code></li>
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
 * <td align="center"><em>2020/10/15</em></td>
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
public class StringUtilsTest {

    @Test
    public void testFormat() {
        System.out.println("format(字符串：{}, 213) => " + StringUtils.format("字符串：{}", "213"));
        System.out.println("logFormat(字符串：{}, 213) => " + StringUtils.logFormat("字符串：{}", "213"));
        System.out.println("msgFormat(字符串：{0}, 213) => " + StringUtils.msgFormat("字符串：{0}", "213"));
        System.out.println("strFormat(字符串：%s, 213) => " + StringUtils.strFormat("字符串：%s", "213"));

        PatternMessageBuilder pmb = new PatternMessageBuilder();
        pmb.append("字符串：");
        pmb.append(PatternMessage.stringBuilder(0).build());
        pmb.append("  数字：");
        pmb.append(PatternMessage.numberBuilder(1).digit().point().digit(2).build());
        System.out.println("msgFormat(" + pmb.toString() + ", 213, 213.12) => " + StringUtils.msgFormat(pmb.toString(), "213", 213.12));

        PatternStringBuilder psb = new PatternStringBuilder();
        psb.append("字符串：");
        psb.append(PatternString.builder(PatternString.Marker.STRING_VALUE_LOWERCASE).build());
        psb.append("  数字：");
        psb.append(PatternString.builder(PatternString.Marker.DECIMAL_FLOATING_NUMBER).decimalNumberPrecision(1).build());
        System.out.println("strFormat(" + psb.toString() + ", 213, 213.12) => " + StringUtils.strFormat(psb.toString(), "213", 213.12));
    }

    @Test
    public void testAppend() {
        String str = "这是一段关于爱国的文字";
        System.out.println("appendToLeft(" + str + ", ., 3) => " + StringUtils.appendToLeft(str, '.', 3));
        System.out.println("appendToRight(" + str + ", ., 3) => " + StringUtils.appendToRight(str, '.', 3));
        System.out.println("appendToLeft(" + str + ", ...) => " + StringUtils.appendToLeft(str, "..."));
        System.out.println("appendToRight(" + str + ", ...) => " + StringUtils.appendToRight(str, "..."));
        System.out.println("appendToLeft(" + str + ", 哈哈哈, 2) => " + StringUtils.appendToLeft(str, "哈哈哈,", 2));
        System.out.println("appendToRight(" + str + ", 哈哈哈, 2) => " + StringUtils.appendToRight(str, ",哈哈哈", 2));
    }

    @Test
    public void testNullOrBlank() {
        System.out.println("isNull(null) => " + StringUtils.isNull(null));
        System.out.println("isNull(\"null\") => " + StringUtils.isNull("null"));
        System.out.println("isNotNull(null) => " + StringUtils.isNotNull(null));
        System.out.println("isNotNull(\"null\") => " + StringUtils.isNotNull("null"));
        System.out.println("isBlank(null) => " + StringUtils.isBlank(null));
        System.out.println("isBlank( 大厦 ) => " + StringUtils.isBlank(" 大厦 "));
        System.out.println("isBlank(\"     \") => " + StringUtils.isBlank("     "));
        System.out.println("isNotBlank(null) => " + StringUtils.isNotBlank(null));
        System.out.println("isNotBlank( 大厦 ) => " + StringUtils.isNotBlank(" 大厦 "));
        System.out.println("isNotBlank(\"     \") => " + StringUtils.isNotBlank("     "));
    }

    @Test
    public void testEquals() {
        String str = "this is string utils class test..";
        System.out.println("equalsAll(\"" + str + "\", \"this is string utils class test..\", \"this is string utils class test..\") => " + StringUtils.equalsAll(str, "this is string utils class test..", "this is string utils class test.."));
        System.out.println("equalsAll(\"" + str + "\", \"this is string utils class test\", \"this is string utils class test..\") => " + StringUtils.equalsAll(str, "this is string utils class test", "this is string utils class test.."));
        System.out.println("equalsAny(\"" + str + "\", \"this is string utils class test..\", \"this is string utils class test..\") => " + StringUtils.equalsAny(str, "this is string utils class test..", "this is string utils class test.."));
        System.out.println("equalsAny(\"" + str + "\",\"this is string utils class test\", \"this is string utils class test..\") => " + StringUtils.equalsAny(str, "this is string utils class test", "this is string utils class test.."));
        System.out.println("equalsAllIgnoreCase(\"" + str + "\", \"THIS is string utils Class test..\", \"this Is string UTILS class test..\") => " + StringUtils.equalsAllIgnoreCase(str, "THIS is string utils Class test..", "this Is string UTILS class test.."));
        System.out.println("equalsAllIgnoreCase(\"" + str + "\", \"THIS is string utils Class test\", \"this Is string UTILS class test..\") => " + StringUtils.equalsAllIgnoreCase(str, "THIS is string utils Class test", "this Is string UTILS class test.."));
        System.out.println("equalsAnyIgnoreCase(\"" + str + "\", \"THIS is string utils Class test..\", \"this Is string UTILS class test..\") => " + StringUtils.equalsAnyIgnoreCase(str, "THIS is string utils Class test..", "this Is string UTILS class test.."));
        System.out.println("equalsAnyIgnoreCase(\"" + str + "\", \"THIS is string utils Class test\", \"this Is string UTILS class test..\") => " + StringUtils.equalsAnyIgnoreCase(str, "THIS is string utils Class test", "this Is string UTILS class test.."));
    }

    @Test
    public void testContains() {
        String str = "this is string utils class test..";
        System.out.println("containsAny(\"" + str + "\", 's','d', 'f') => " + StringUtils.containsAny(str, 's', 'd', 'f'));
        System.out.println("containsAny(\"" + str + "\", 'S','D', 'F') => " + StringUtils.containsAny(str, 'S', 'D', 'F'));
        System.out.println("containsAnyIgnoreCase(\"" + str + "\", 'S','D', 'F') => " + StringUtils.containsAnyIgnoreCase(str, 'S', 'D', 'F'));
        System.out.println("containsAny(\"" + str + "\", \"is\", \"test\", \"find\") => " + StringUtils.containsAny(str, "is", "test", "find"));
        System.out.println("containsAny(\"" + str + "\", \"IS\", \"TEst\", \"Find\") => " + StringUtils.containsAny(str, "IS", "TEst", "Find"));
        System.out.println("containsAnyIgnoreCase(\"" + str + "\", \"IS\", \"TEst\", \"Find\") => " + StringUtils.containsAnyIgnoreCase(str, "IS", "TEst", "Find"));
    }

    @Test
    public void testCompare() {
        System.out.println("compareWithDict(\"zhangsan\", \"lisi\") => " + (StringUtils.compareWithDict("zhangsan", "lisi") > 0 ? "lisi" : "zhangsan"));
        System.out.println("compareWithDict(\"zhangsan\", \"Lisi\") => " + (StringUtils.compareWithDict("zhangsan", "Lisi") > 0 ? "Lisi" : "zhangsan"));
        System.out.println("compareWithDict(\"张三\", \"李四\") => " + (StringUtils.compareWithDict("张三", "李四") > 0 ? "李四" : "张三"));

        // 按字符串长度比较
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
        System.out.println("compareWithDict(\"zhangsan\", \"lisi\") => " + (StringUtils.compare("zhangsan", "lisi", comparator) > 0 ? "lisi" : "zhangsan"));
        System.out.println("compareWithDict(\"张三\", \"李里四\") => " + (StringUtils.compare("张三", "李里四", comparator) > 0 ? "李里四" : "张三"));
    }

    @Test
    public void testSubstr() {
        String str = "hello world!";
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        StringBuilder c = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            a.append(str.charAt(i) + "\t\t");
            b.append((i) + "\t\t");
            c.append(-(str.length() - i) + "\t\t");
        }
        System.out.println("----------------------------------------------");
        System.out.println("str.length = " + str.length());
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());
        System.out.println("-----------------------------------------------");
        System.out.println("subStr(\"" + str + "\", -3) => " + StringUtils.subStr(str, -3));
        System.out.println("subStr(\"" + str + "\", -3, -1) => " + StringUtils.subStr(str, -3, -1));
        System.out.println("subStr(\"" + str + "\", -3, 1) => " + StringUtils.subStr(str, -3, 1));
        System.out.println("subStr(\"" + str + "\", 3) => " + StringUtils.subStr(str, 3));
        System.out.println("subStr(\"" + str + "\", 3, 7) => " + StringUtils.subStr(str, 3, 7));
    }

    @Test
    public void testSlice() {
        String str = "hello world!";
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        StringBuilder c = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            a.append(str.charAt(i) + "\t\t");
            b.append((i) + "\t\t");
            c.append(-(str.length() - i) + "\t\t");
        }
        System.out.println("----------------------------------------------");
        System.out.println("str.length = " + str.length());
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());
        System.out.println("-----------------------------------------------");
        System.out.println("slice(\"" + str + "\", -3) => " + StringUtils.slice(str, -3));
        System.out.println("slice(\"" + str + "\", -3, -1) => " + StringUtils.slice(str, -3, -1));
        System.out.println("slice(\"" + str + "\", -3, 1) => " + StringUtils.slice(str, -3, 1));
        System.out.println("slice(\"" + str + "\", 3) => " + StringUtils.slice(str, 3));
        System.out.println("slice(\"" + str + "\", 3, 7) => " + StringUtils.slice(str, 3, 7));
    }

    @Test
    public void testPickStr() {
        String str = "this is string utils class, you can used it. it is used ...";
        printIndex(str);
        int[] indexs = new int[]{2, 3, 2, 4, 5, 20, 29};
        String subStr = StringUtils.pickStr(str, indexs);
        System.out.println(indexs.length + "\t" + subStr.length());
        System.out.println("pickStr(\"" + str + "\", " + Arrays.toString(indexs) + ") => " + subStr);
    }

    @Test
    public void testOfStr() {
        String str = "this is string utils class, you can used it. it is used ...";
        printIndex(str);
        int[] indexs = new int[]{2, 3, 2, 4, 5, 20, 29};
        String subStr = StringUtils.ofStr(str, indexs);
        System.out.println(indexs.length + "\t" + subStr.length());
        System.out.println("ofStr(\"" + str + "\", " + Arrays.toString(indexs) + ") => " + subStr);
    }

    @Test
    public void testSplit() {
        String str = "this is string utils class, you can used it. it is used ...";
        String[] strArray1 = StringUtils.split(str, 's');
        String[] strArray2 = StringUtils.split(str, 's', 'c');
        String[] strArray3 = StringUtils.split(str, "is");
        System.out.println("split(\"" + str + "\", 's') => " + Arrays.toString(strArray1));
        System.out.println("split(\"" + str + "\", 's', 'c') => " + Arrays.toString(strArray2));
        System.out.println("split(\"" + str + "\", \"is\") => " + Arrays.toString(strArray3));
    }

    @Test
    public void testRandomStr() {
        System.out.println("randomDigit(6) => " + StringUtils.randomDigit(6));
        System.out.println("randomEnLetter(6) => " + StringUtils.randomEnLetter(6));
        System.out.println("randomDigitAndEnLetter(6) => " + StringUtils.randomDigitAndEnLetter(6));
        System.out.println("randomLocalChar(6) => " + StringUtils.randomLocalChar(6));
        String str = "this is string utils class, you can used it. it is used ...";
        System.out.println("randomLocalChar(\"" + str + "\",6) => " + StringUtils.random(str, 6));
    }

    @Test
    public void testCase() {
        String str = "username";
        System.out.println("upperCase(\"" + str + "\") => " + StringUtils.upperCase(str));
        System.out.println("upperCase(\"" + str + "\", 0, 1) => " + StringUtils.upperCase(str, 0, 1));
        System.out.println("upperCaseFirst(\"" + str + "\") => " + StringUtils.upperCaseFirst(str));
        str = "USerName";
        System.out.println("lowerCase(\"" + str + "\") => " + StringUtils.lowerCase(str));
        System.out.println("lowerCase(\"" + str + "\", 0, 1) => " + StringUtils.lowerCase(str, 0, 1));
        System.out.println("lowerCaseFirst(\"" + str + "\") => " + StringUtils.lowerCaseFirst(str));
    }

    @Test
    public void testTranscode() throws Exception {
        String str = "我是akudy";
        String afterStr = StringUtils.transcode(str, "UTF-8", "GBK");
        String beforeStr = StringUtils.transcode(afterStr, "GBK", "UTF-8");
        System.out.println("transcode(\"" + str + "\", \"UTF-8\", \"GBK\") => " + afterStr);
        System.out.println("transcode(\"" + afterStr + "\", \"GBK\", \"UTF-8\") => " + beforeStr);

        afterStr = StringUtils.encode(str, "GBK");
        System.out.println("encode(\"" + str + "\") => " + afterStr);
        beforeStr = StringUtils.decode(afterStr, "GBK");
        System.out.println("decode(\"" + afterStr + "\") => " + beforeStr);
    }

    @Test
    public void testType() {
        System.out.println("isNumber(1234a) => " + StringUtils.isNumber("1234a"));
        System.out.println("isNumber(1234.01) => " + StringUtils.isNumber("1234.01"));
        System.out.println("isNumber(1234.) => " + StringUtils.isNumber("1234."));
        System.out.println("isNumber(0.1234.01) => " + StringUtils.isNumber("0.1234.01"));
        System.out.println("isNumber(.1234) => " + StringUtils.isNumber(".1234"));
        System.out.println("isNumber(1234) => " + StringUtils.isNumber("1234"));

        System.out.println("isIntNumber(1234a) => " + StringUtils.isIntNumber("1234a"));
        System.out.println("isIntNumber(1234.01) => " + StringUtils.isIntNumber("1234.01"));
        System.out.println("isIntNumber(1234.) => " + StringUtils.isIntNumber("1234."));
        System.out.println("isIntNumber(0.1234.01) => " + StringUtils.isIntNumber("0.1234.01"));
        System.out.println("isIntNumber(.1234) => " + StringUtils.isIntNumber(".1234"));
        System.out.println("isIntNumber(1234) => " + StringUtils.isIntNumber("1234"));
    }

    @Test
    public void testIndexOf() {
        String str = "this is string utils class, you can used it. it is used ...";
        printIndex(str);
        int[] chIndexes = StringUtils.indexOf(str, 's');
        System.out.println("indexOf(\"" + str + "\", 's') => " + Arrays.toString(chIndexes));
        int[] strIndexes = StringUtils.indexOf(str, "is");
        System.out.println("indexOf(\"" + str + "\", \"is\") => " + Arrays.toString(strIndexes));
    }

    private void printIndex(String str) {
        StringBuilder chs = new StringBuilder();
        StringBuilder index = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            chs.append(str.charAt(i)).append("\t\t");
            index.append(i).append("\t\t");
        }
        System.out.println(chs.toString());
        System.out.println(index.toString());
        System.out.println("-----------------------------------------------------------");
    }


}
