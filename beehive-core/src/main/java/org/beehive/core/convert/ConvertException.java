/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.convert.ConvertException
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-14
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.convert;

import org.beehive.util.ObjectUtils;

/**
 * 类型转换异常定义。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.convert</code></li>
 * <li>Class Name: <code>ConvertException</code></li>
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
public class ConvertException extends RuntimeException {

    /**
     * 使用异常消息内容实例化一个转换异常对象
     * @param message 异常消息内容
     */
    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(Class<?> originClass, Class<?> targetClass) {
        this("the original type[" + originClass.getName() + "] cannot be converted to the target type[" + targetClass.getName() + "].");
    }

    public ConvertException(Object origin, Class<?> targetClass) {
        this("the original object[" + ObjectUtils.toString(origin) + "] cannot be converted to the target type[" + targetClass.getName() + "].");
    }

}
