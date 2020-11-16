/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.RandomStringFactory
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-10-17
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * 随机字符串工厂，用于产生字符串。包含共用方法和接口定义。
 * <br>
 * 包括产生随机字符串等。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.string</code></li>
 * <li>Class Name: <code>RandomStringFactory</code></li>
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
 * <td align="center"><em>2020/10/16</em></td>
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
public class RandomStringFactory {

    /**
     * 对象池的最大的容量，默认16
     */
    private static final int POOL_MAX_SIZE = 16;

    /**
     * 随机字符串工厂池
     */
    private static Map<Integer, RandomStringFactory> pool = new HashMap<>(POOL_MAX_SIZE);

    /**
     * 元符号字符串
     */
    protected String metaSymbol;

    private RandomStringFactory(String metaSymbol) {
        this.metaSymbol = metaSymbol;
    }

    /**
     * 构建工厂是必须指定元符号组成的字符串
     *
     * @param metaSymbol 元符号字符串
     * @since 1.0
     */
    public static RandomStringFactory newFactory(String metaSymbol) {
        RandomStringFactory factory = null;
        Integer key = metaSymbol == null ? null : metaSymbol.hashCode();
        if (pool.containsKey(key)) {
            factory = pool.get(key);
        } else {
            factory = new RandomStringFactory(metaSymbol);
            synchronized (pool) {
                if (pool.size() > POOL_MAX_SIZE) {
                    pool.clear();
                }
                pool.put(key, factory);
            }
        }
        return factory;
    }

    /**
     * 构建工厂时必须指定元符号数组
     *
     * @param metaSymbol 元符号数组
     * @see #newFactory(String)
     * @since 1.0
     */
    public static RandomStringFactory newFactory(char[] metaSymbol) {
        return newFactory(new String(metaSymbol));
    }

    /**
     * 根据指定的字符个数产生一个随机的字符串
     *
     * @param charCount 字符个数
     * @return 随机字符串
     * @since 1.0
     */
    public String random(int charCount) {
        if (this.metaSymbol == null) {
            return null;
        }
        int size = this.metaSymbol.length();
        Random randomizer = new Random();
        StringBuilder buffer = new StringBuilder(charCount);
        for (int i = 0; i < charCount; i++) {
            int index = randomizer.nextInt(size);
            char c = this.metaSymbol.charAt(index);
            buffer.append(c);
        }
        return buffer.toString();
    }







    /**
     * 资源文件地址
     */
    private static ResourceBundle charResource;

    /**
     * 根据指定的资源key来构建随机字符串工厂
     *
     * @param charKey 资源key，位于"string/factory/common_characters"文件中
     * @return 随机字符串工厂对象
     */
    private synchronized static RandomStringFactory newFactoryFromResource(String charKey) {
        ClassLoader classLoader = RandomStringFactory.class.getClassLoader();
        System.out.println(classLoader.getResource(".").getPath());

        System.err.println("========================");
        if (RandomStringFactory.charResource == null) {
            String loader = ClassLoader.getSystemClassLoader().getResource(".").getPath();
            System.err.println(loader);
//            InputStream is = new FileInputStream(new File(loader + "/string/factory/common-characters.properties"));
//            System.err.println(is);
            System.err.println("is null");
            RandomStringFactory.charResource = ResourceBundle.getBundle("string/factory/common-characters");
        }
        System.err.println("suc");
        String metaSymbol = RandomStringFactory.charResource.getString(charKey);
        System.err.println(metaSymbol);
        if (metaSymbol != null) {
            return new RandomStringFactory(metaSymbol);
        }
        return null;
    }

    /**
     * 构建一个随机数字字符串工厂，元符号包含0-9的数字
     *
     * @return 随机数字字符串工厂
     * @see #newFactory(String)
     * @since 1.0
     */
    public static RandomStringFactory newDigitFactory() {
        return newFactoryFromResource("digit.chars");
    }

    /**
     * 构建一个随机字母字符串工厂，元符号包含A-Z和a-z的字母
     *
     * @return 随机字母字符串工厂
     * @see #newFactory(String)
     * @since 1.0
     */
    public static RandomStringFactory newAlphabetFactory() {
        return newFactoryFromResource("alphabet.chars");
    }

    /**
     * 构建一个随机字母数字字符串工厂，元符号包含0-9、A-Z、a-z的数字和字母
     *
     * @return 随机字母数据字符串工厂
     * @see #newFactory(String)
     * @since 1.0
     */
    public static RandomStringFactory newAlphanumericFactory() {
        return newFactoryFromResource("alphanumeric.chars");
    }

    /**
     * 构建一个可显示符号的字符串工厂，元素包含键盘上数组、字母、标点符号所有字符（不包含空格符）
     *
     * @return 随机字符串工厂
     * @see #newFactory(String)
     * @since 1.0
     */
    public static RandomStringFactory newGraphemeFactory() {
        return newFactoryFromResource("inputable.chars");
    }

    /**
     * 构建一个本地语言的字符串工厂，不包含常规标点符号；比如在中文平台下为常规汉字字符
     *
     * @return 随机字符串工厂
     * @see #newFactory(String)
     * @since 1.0
     */
    public static RandomStringFactory newLocalLanguageFactory() {
        return newFactoryFromResource("common.local.language.chars");
    }

}
