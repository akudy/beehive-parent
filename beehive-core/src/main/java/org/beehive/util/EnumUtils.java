/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.EnumUtils
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-08-19
 * Comments: Java源文件
 */

package org.beehive.util;

import java.util.Arrays;
import java.util.List;

/**
 * 枚举类型工具类。<br/>
 * <br>
 * 提供枚举类型操作的方法定义，例如：通过枚举名称获取枚举对象等。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.enumeration</code></li>
 * <li>Class Name: <code>EnumUtils</code></li>
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
 * @since 1.0
 */
public class EnumUtils {

    /**
     * 比较枚举唯一值时是否忽略大小写
     */
    private static boolean ignoreCase = true;

    /**
     * 枚举唯一标识的字段名称
     */
    private static String fieldName = "name";

    /**
     * 这是比较枚举唯一值是是否忽略大小写
     *
     * @param ignoreCase true-忽略；false-不忽略
     */
    public void setIgnoreCase(boolean ignoreCase) {
        EnumUtils.ignoreCase = ignoreCase;
    }

    /**
     * 设置枚举枚举唯一标识字段名称
     *
     * @param fieldName 字段名称
     */
    public void setFieldName(String fieldName) {
        EnumUtils.fieldName = fieldName;
    }

    /**
     * 获取枚举项列表。<br/>
     * 等同枚举的<code>values()</code>方法。
     *
     * @param enumClass 枚举类类型
     * @param <E>       枚举类类型
     * @return 枚举项列表
     */
    public static <E extends Enum<E>> List<E> getInstances(Class<E> enumClass) {
        E[] enumItemArray = enumClass.getEnumConstants();
        return Arrays.asList(enumItemArray);
    }

    public static <E extends Enum<E>> String toString(E instance){

        return instance.toString();
    }


}
