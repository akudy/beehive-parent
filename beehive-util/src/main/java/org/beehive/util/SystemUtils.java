/*
 * Copyright (c) 2019-2019 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-util
 * File Name: org.beehive.util.SystemUtils
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2019-06-26
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.util;

/**
 * Java运行系统工具类。
 * <br>
 * 主要用于获取或定义一些系统参数，所有的参数定义只与当前运行实例有关；不影响其他运行实例。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.util</code></li>
 * <li>Class Name: <code>SystemUtils</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1">
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
 * <td align="center"><em>2019/6/26</em></td>
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
public class SystemUtils {

    /**
     * 私有化构造函数，禁止实例化
     */
    private SystemUtils() {
    }

    /**
     * 当前所在国家地区代码，默认为：CN
     */
    public static final String USER_COUNTRY = System.getProperty("user.country", "CN");

    /**
     * 行分隔符，默认为：\r\n
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator", "\r\n");

    /**
     * Java Class头版本号
     */
    public static final String JAVA_CLASS_VERSION = System.getProperty("java.class.version");

    /**
     * 当前所在时区，默认为：东八区（GMT+8:00）
     */
    public static final String USER_TIMEZONE = System.getProperty("user.timezone", "GMT+8:00");

    /**
     * 当前文件编码格式，默认为：UTF-8
     */
    public static final String FILE_ENCODING = System.getProperty("file.encoding", "UTF-8");

    /**
     * Java规范版本号，例如:1.8
     *
     * @see #JAVA_VERSION
     */
    public static final String JAVA_SPECIFICATION_VERSION = System.getProperty("java.specification.version");

    /**
     * Java虚拟机规范版本号，例如:1.8
     *
     * @see #JAVA_SPECIFICATION_VERSION
     */
    public static final String JAVA_VM_SPECIFICATION_VERSION = System.getProperty("java.vm.specification.version");

    /**
     * 当前语言代码
     */
    public static final String USER_LANGUAGE = System.getProperty("user.language", "zh");

    /**
     * Java版本号，例如：1.8.0_172
     *
     * @see #JAVA_SPECIFICATION_VERSION
     * @see #JAVA_RUNTIME_VERSION
     */
    public static final String JAVA_VERSION = System.getProperty("java.version");

    /**
     * Java运行环境版本号，例如：1.8.0_172-b11
     *
     * @see #JAVA_VERSION
     */
    public static final String JAVA_RUNTIME_VERSION = System.getProperty("java.runtime.version");

    /**
     * 文件分隔符，默认为：\
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator", "\\");

    /**
     * 多路径分隔符，默认为：;
     */
    public static final String PATH_SEPARATOR = System.getProperty("path.separator", ";");

}
