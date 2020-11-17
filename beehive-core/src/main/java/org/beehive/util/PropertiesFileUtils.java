/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.PropertiesFileUtils
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-11-17
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties属性文件/配置文件工具类。提供属性文件的读写接口定义。
 * <br>
 * 包含属性文件的读取、写入等操作。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.util</code></li>
 * <li>Class Name: <code>PropertiesFileUtils</code></li>
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
 * <td align="center"><em>2020/11/17</em></td>
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
public class PropertiesFileUtils {

    /**
     * 属性文件后缀
     */
    private static final String SUFFIX = ".properties";

    /**
     * 加载指定的属性文件列表，并返回一个属性对象。重复的key将会被替换，与指定的加载文件顺序有关。
     *
     * @param paths 文件路径列表，可以不包含后缀名
     * @return 属性对象
     * @throws IOException 读取文件异常
     */
    public static Properties load(String... paths) throws IOException {
        Properties properties = new Properties();
        if (ArrayUtils.isEmpty(paths)) {
            return properties;
        }
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        InputStream is = null;
        for (String path : paths) {
            if (!hasSuffix(path)) {
                path += SUFFIX;
            }
            is = classLoader.getResourceAsStream(path);
            properties.load(is);
        }
        return properties;
    }

    /**
     * 判断属性文件是否包含后缀
     *
     * @param path 文件路径
     * @return 如果包含后缀则返回true，否则返回false
     */
    private static boolean hasSuffix(String path) {
        return path.endsWith(SUFFIX);
    }


    public static Properties loadAll(String directory) {

        return null;
    }

    public static Properties loadFromClassPath(Class<?> clazz, String... paths) {
        return null;
    }

    public static String getValue(String key, String... paths) {
        return null;
    }


}
