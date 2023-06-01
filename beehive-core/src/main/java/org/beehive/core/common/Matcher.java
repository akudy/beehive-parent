/*
 * Copyright (c) 2019-2022 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.common.Matcher
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2022-06-27
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.common;

/**
 * 匹配器功能接口定义。
 * <br>
 * 用于检测源对象（值）和目标对象（值）是否匹配。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.common</code></li>
 *   <li>Class Name: <code>Matcher</code></li>
 *   <li>Java Version Used: Java 8</li>
 *   <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 *   <dd>
 *     <table border="1" cellspacing="0" cellpadding="0" summary=""Upgrade&shy;Modify History>
 *       <tr>
 *         <th>Version</th>
 *         <th>Environment</th>
 *         <th>ModifyTime</th>
 *         <th>Modifier</th>
 *         <th>Description</th>
 *       </tr>
 *       <tr>
 *         <td align="center"><em>1.0</em></td>
 *         <td align="center"><em>Java 8</em></td>
 *         <td align="center"><em>2022-06-27</em></td>
 *         <td align="center"><em>akudy</em></td>
 *         <td><em>Define</em></td>
 *       </tr>
 *     </table>
 *   </dd>
 * </dl>
 *
 * @param <T> 源对象（值）类型定义
 * @author <a href="mailto:akudys@163.com">akudy</a>
 * @version 1.0
 * @since 1.0
 */
public interface Matcher<T> {

    /**
     * 匹配器接受检查的对象，如果检查的对象符合匹配器的要求和规则，则返回true，否则返回false。
     * 匹配规则：规则具体由实现类提供。
     *
     * @param obj 受检查的对象
     * @return 如果完全匹配则返回true，否则返回false
     */
    boolean accept(T obj);

}
