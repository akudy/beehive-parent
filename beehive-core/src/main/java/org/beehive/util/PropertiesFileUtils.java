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
     * 加载指定的属性文件，并将属性文件内容填充到指定的属性对象中。
     *
     * @param properties   属性对象
     * @param relativePath 文件相对类加载目录的路径，可以不包含后缀名
     * @throws IOException 读取文件异常
     */
    public static void load(Properties properties, String relativePath) throws IOException {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        InputStream is = null;
        if (!hasSuffix(relativePath)) {
            relativePath += SUFFIX;
        }
        is = classLoader.getResourceAsStream(relativePath);
        properties.load(is);
    }

    /**
     * 加载指定的属性文件，并返回属性对象。
     *
     * @param relativePath 属性文件相对类加载目录的路径，可以不包含后缀名
     * @return 属性对象
     * @throws IOException 读取文件异常
     */
    public static Properties load(String relativePath) throws IOException {
        Properties properties = new Properties();
        load(properties, relativePath);
        return properties;
    }

    /**
     * 加载多个指定的属性文件，并将属性文件内容填充到指定的属性对象中。<br/>
     * 多个文件中重复的key将会被替换，与指定的加载文件顺序有关。
     *
     * @param properties    属性对象
     * @param relativePaths 属性文件相对类加载目录的路径列表，可以不包含后缀名
     * @throws IOException 读取文件异常
     */
    public static void loads(Properties properties, String... relativePaths) throws IOException {
        if (ArrayUtils.isNotEmpty(relativePaths)) {
            ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            InputStream is = null;
            for (String path : relativePaths) {
                if (!hasSuffix(path)) {
                    path += SUFFIX;
                }
                is = classLoader.getResourceAsStream(path);
                properties.load(is);
            }
        }
    }

    /**
     * 加载指定的属性文件列表，并返回一个属性对象。<br/>
     * 多个文件中重复的key将会被替换，与指定的加载文件顺序有关。
     *
     * @param relativePaths 属性文件相对类加载目录的路径列表，可以不包含后缀名
     * @return 属性对象
     * @throws IOException 读取文件异常
     */
    public static Properties loads(String... relativePaths) throws IOException {
        Properties properties = new Properties();
        loads(properties, relativePaths);
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

    /**
     * 加载指定目录下的所有属性文件，并返回一个属性对象。<br/>
     * 多个文件中重复的key将会被替换，与指定的加载文件顺序有关。
     *
     * @param relativeDirectoryPath 相对类加载目录的目录路径
     * @return 属性对象
     * @throws IOException 读取异常
     */
    public static Properties loadAll(String relativeDirectoryPath) throws IOException {
//        Properties properties = new Properties();
//        URL classPathUrl = ClassUtils.getDefaultClassLoadPath();
//        if (classPathUrl == null) {
//            return properties;
//        }
//        File file = new File(classPathUrl.getPath() + relativeDirectoryPath);
//        if (!file.isDirectory()) {
//            throw new IllegalArgumentException("[" + relativeDirectoryPath + "] is not directory.");
//        }

        return null;
    }

    /**
     * 从指定的类目录加载指定的属性文件，并将顺序内容填充到指定的属性对象中。
     *
     * @param properties 属性对象
     * @param clazz      类定义
     * @param fileName   文件名称
     * @return 顺序对象
     */
    public static Properties loadFromClassPath(Properties properties, Class<?> clazz, String fileName) {
        return null;
    }

    /**
     * 从指定的类目录加载指定的属性文件，并返回属性对象。
     *
     * @param clazz    类定义
     * @param fileName 文件名称
     * @return 顺序对象
     */
    public static Properties loadFromClassPath(Class<?> clazz, String fileName) {
        return null;
    }

}
