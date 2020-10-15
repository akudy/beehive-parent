/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.StringFormatter
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-10-13
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string;

/**
 * 字符串格式化，提供自定义的字符串格式化工具实现。
 * <br>
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.string</code></li>
 * <li>Class Name: <code>StringFormatter</code></li>
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
 * <td align="center"><em>2020/10/13</em></td>
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
public interface StringFormatter {

    /**
     * 提供将格式字符串格式化为目标字符串的方法
     *
     * @param strTemplate 格式字符串/模板字符串
     * @param parameters  参数列表
     * @return 格式化后的字符串
     */
    String format(String strTemplate, Object... parameters);

}
