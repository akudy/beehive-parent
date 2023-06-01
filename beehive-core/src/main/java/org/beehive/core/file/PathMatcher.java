/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.file.PathMatcher
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-12-21
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.file;

/**
 * 路径匹配器。用于完成文件路径的匹配工作。
 * <br>
 * 对文件路径进行匹配的接口定义。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.file</code></li>
 * <li>Class Name: <code>PathMatcher</code></li>
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
 * <td align="center"><em>2020/12/21</em></td>
 * <td align="center"><em>akudy</em></td>
 * <td><em>Define</em></td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 *
 * @author <a href="mailto:akudys@163.com">akudy</a>
 * @version 1.0
 * @since 1.0
 */
public interface PathMatcher{

    /**
     * 判断一个路径是否是一个模式方式。如果不是则完全不用使用模式进行匹配，可以直接进行相等比较。减少时间损耗。
     *
     * @param path 路径
     * @return 如果是模式字符串，则返回true；否则返回false。
     */
    boolean isPattern(String path);

    /**
     * 判断一个路径是否和模式匹配
     *
     * @param pattern 模式字符串
     * @param path    路径
     * @return 如果匹配则返回true；否则返回false。
     */
    boolean match(String pattern, String path);

}
