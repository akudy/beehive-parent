/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.ObjectUtils
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-08-19
 * Comments: Java源文件
 */

package org.beehive.util;

/**
 * 对象操作相关工具类定义。
 * <br>
 * 提供对象的常用操作工具方法。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.util</code></li>
 * <li>Class Name: <code>ObjectUtils</code></li>
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
 * <td align="center"><em>2020/8/19</em></td>
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
public class ObjectUtils {

    /**
     * 判断给定的实例对象是否是指定类型的实例
     *
     * @param object 对象实例
     * @param clazz  类型
     * @return 如果是指定类型的实例则返回true，否则返回false
     * @since 1.0
     */
    public static boolean instanceOf(Object object, Class<?> clazz) {
        return clazz.isInstance(object);
    }

    public static <T> String toString(T instance) {
        return instance.toString();
    }

}
