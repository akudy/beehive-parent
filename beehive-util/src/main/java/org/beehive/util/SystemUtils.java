/*
 * Copyright (c) 2019-2019 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-util
 * File Name: org.beehive.util.SystemUtils
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2019-06-26
 * Comments: Java源文件
 */

package org.beehive.util;

import static org.beehive.helper.SystemInfo.*;

/**
 * 系统工具类。
 * <br>
 * 主要用于获取或定义一些系统参数，所有的参数定义只与当前运行实例有关；不影响其他运行实例。
 *  TODO 这个类需要考虑清楚到底应该做什么
 *  TODO 1. 获取所有系统参数，并尝试进行修改？
 *  TODO 2. 获取当前被依赖应用（项目）的属性？
 *  TODO 3. 扫描当前被依赖应用（项目）所有jar包的的常量定义？
 *  TODO 4. 扫描beehive项目的所有配置定义，并提供修改？
 *  TODO 5. 扫描当前被依赖应用（项目）所有jar包的的配置信息，并提供修改？
 *  TODO 6. 统一被依赖应用（项目）的所有配置到该类中，并尝试修改所有常量的值；让应用保证应用（项目）的所有类同的配置统一
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
 * <table border="1"  cellspacing="0" cellpadding="0" summary="Upgrade/Modify History">
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
public final class SystemUtils {

    /**
     * 私有化构造函数，禁止实例化
     */
    private SystemUtils() {
    }

    /**
     * 获取所在国家短名称，例如：CN
     *
     * @return 国家短名称
     */
    public static String getCountryShortName() {
        return USER.COUNTRY;
    }

    /**
     * 获取所在地区的时区，例如：GMT+8:00
     *
     * @return 时区标识符，默认：GMT+8:00
     * @see USER#TIMEZONE
     */
    public static String getTimezone() {
        if (USER.TIMEZONE == null || USER.TIMEZONE.trim().length() == 0) {
            return "GMT+8:00";
        }
        return USER.TIMEZONE;
    }

    /**
     * 获取当前语言环境，例如：zh
     *
     * @return 语言环境，默认：zh
     * @see USER#LANGUAGE
     */
    public static String getLanguage() {
        if (USER.LANGUAGE == null || USER.LANGUAGE.trim().length() == 0) {
            return "zh";
        }
        return USER.LANGUAGE;
    }

    /**
     * 获取Java版本号，例如：1.8.0_172
     *
     * @return Java版本号
     * @see JAVA#VERSION
     */
    public static String getJavaVersion() {
        return JAVA.VERSION;
    }

    /**
     * 获取文件编码格式
     *
     * @return 文件编码格式，默认：UTF-8
     * @see JAVA#FILE_ENCODING
     */
    public static String getFileEncoding() {
        return JAVA.FILE_ENCODING;
    }

    /**
     * 获取系统换行符
     *
     * @return 系统换行符，默认：\r\n
     * @see OS#LINE_SEPARATOR
     */
    public static String getLineSeparator() {
        return OS.LINE_SEPARATOR;
    }

    /**
     * 获取文件分隔符
     *
     * @return 文件分隔符，默认：\
     * @see OS#FILE_SEPARATOR
     */
    public static String getFileSeparator() {
        return OS.FILE_SEPARATOR;
    }

    /**
     * 获取路径分隔符
     *
     * @return 路径分隔符，默认：\
     * @see OS#PATH_SEPARATOR
     */
    public static String getPathSeparator() {
        return OS.PATH_SEPARATOR;
    }

}
