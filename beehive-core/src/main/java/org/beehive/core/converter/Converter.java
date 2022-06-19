/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.convert.Converter
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-14
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.converter;

/**
 * 类型转换接口，用于将一个输入的对象转换为目标类型对象。
 *
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.convert</code></li>
 * <li>Class Name: <code>Converter</code></li>
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
 * @param <S> 源对象类型
 * @param <T> 目标对象类型
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @since 1.0
 */
public interface Converter<S, T> {

    /**
     * 将源对象转换为目标对象
     *
     * @param source 源对象实例
     * @return 目标对象实例
     * @throws ConvertException
     * @since 1.0
     */
    T convert(S source) throws ConvertException;

    /**
     * 将源对象转换为目标对象，如果转换失败，则返回指定的默认值
     *
     * @param source       源对象实例
     * @param defaultValue 如果转换失败，则返回默认值
     * @return 目标对象实例
     * @since 1.0
     */
    default T convert(S source, T defaultValue) {
        try {
            return this.convert(source);
        } catch (ConvertException e) {
            return defaultValue;
        }
    }

}
