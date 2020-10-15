/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.StringDictCompartor
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-10-15 23:59:08
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string;

import org.beehive.util.StringUtils;

import java.util.Comparator;

/**
 * 字符串字典比较器，扩展至{@link Comparator}接口。
 * <br>
 * 这里字符串比较会按照字典的排序顺序来进行比较。
 * <p/>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.string</code></li>
 * <li>Class Name: <code>StringDictCompartor</code></li>
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
 * <td align="center"><em>2020-10-15</em></td>
 * <td align="center"><em>akudy</em></td>
 * <td align="center"><em>Define</em></td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @since Java 8
 */
public class StringDictComparator implements Comparator<String> {

    /**
     * 这里的比较算法，会将字符串进行<code>GBK</code>编码的转换。
     *
     * @param str1 被比较字符串文本
     * @param str1 比较字符串文本
     * @return 如果被比较字符串大于比较字符串则返回大于0；如果被比较字符串小于比较字符串则返回小于0；否则返回0
     * @see String#compareToIgnoreCase(String)
     * @see StringUtils#compareWithDict(String, String)
     */
    @Override
    public int compare(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return 0;
        }
        if (str1 == null && str2 != null) {
            return -1;
        }
        if (str1 != null && str2 == null) {
            return 1;
        }
        String newText1 = StringUtils.encode(str1, "GBK");
        String newText2 = StringUtils.encode(str2, "GBK");
        return newText1.compareToIgnoreCase(newText2);
    }

}
