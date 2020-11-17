/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.util.ResourceUtils
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-08-19
 * Comments: Java源文件
 */

package org.beehive.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 资源工具类。提供资源文件的读写接口定义。
 * <br>
 * 包含资源文件的读取、写入等操作。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.util</code></li>
 * <li>Class Name: <code>ResourceUtils</code></li>
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
public class ResourceUtils {

    /**
     * 加载多个资源文件
     * @param resourcePaths
     * @return
     * @throws IOException
     */
    public static Properties loadResources(String... resourcePaths) throws IOException {
        Properties properties = new Properties();
        if (ArrayUtils.isEmpty(resourcePaths)) {
            return properties;
        }
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        if (classLoader == null) {
            return properties;
        }
        for (String resourcePath : resourcePaths) {
            InputStream inputStream = classLoader.getResourceAsStream(resourcePath);
            properties.load(inputStream);
        }
        return properties;
    }

    public static Properties loadResources(Class<?> clazz, String... resourcePaths) {
        return null;
    }

    public static Properties loadResourcesFromClassPath(Class<?> clazz, String... resourcePaths) {
        return null;
    }



//    public static File loadFile(String resourcePath) {
//        return null;
//    }
//
//    public static File loadFile(String resourcePath, Class<?> clazz) {
//        return null;
//    }
//
//    public static File loadFileFromClassPath(String resourcePath, Class<?> clazz) {
//        return null;
//    }
//
//    public static InputStream loadFileAsStream(String resourcePath) {
//        return null;
//    }
//
//    public static InputStream loadFileAsStream(String resourcePath, Class<?> clazz) {
//        return null;
//    }
//
//    public static InputStream loadFileAsStreamFromClassPath(String resourcePath, Class<?> clazz) {
//        return null;
//    }

}
