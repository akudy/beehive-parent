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
    private static Map<String, RandomStringFactory> pool = new HashMap<>(POOL_MAX_SIZE);

    /**
     * 数字元符号
     */
    private static final String DIGIT_SYMBOL = "0123456789";

    /**
     * 字母元符号
     */
    private static final String ALPHABET_SYMBOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 字母数字符号
     */
    private static final String ALPHANUMERIC_SYMBOL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 可显示的符号，除空格符号
     */
    private static final String GRAPHEME_SYMBOL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@$$%^&*()_+-={}[]|\\;:'\"<>,.?/";

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
        if (pool.containsKey(metaSymbol)) {
            factory = pool.get(metaSymbol);
        } else {
            factory = new RandomStringFactory(metaSymbol);
            synchronized (pool) {
                if (pool.size() > POOL_MAX_SIZE) {
                    pool.clear();
                }
                pool.put(metaSymbol, factory);
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
     * 构建一个随机数字字符串工厂，元符号包含0-9的数字
     *
     * @return 随机数字字符串工厂
     * @see #newFactory(String)
     * @since 1.0
     */
    public static RandomStringFactory newDigitFactory() {
        return newFactory(DIGIT_SYMBOL);
    }

    /**
     * 构建一个随机字母字符串工厂，元符号包含A-Z和a-z的字母
     *
     * @return 随机字母字符串工厂
     * @see #newFactory(String)
     * @since 1.0
     */
    public static RandomStringFactory newAlphabetFactory() {
        return newFactory(ALPHABET_SYMBOL);
    }

    /**
     * 构建一个随机字母数字字符串工厂，元符号包含0-9、A-Z、a-z的数字和字母
     *
     * @return 随机字母数据字符串工厂
     * @see #newFactory(String)
     * @since 1.0
     */
    public static RandomStringFactory newAlphanumericFactory() {
        return newFactory(ALPHANUMERIC_SYMBOL);
    }

    /**
     * 构建一个可显示符号的字符串工厂，元素包含键盘上数组、字母、标点符号所有字符（不包含空格符）
     *
     * @return 随机字符串工厂
     * @see #newFactory(String)
     * @since 1.0
     */
    public static RandomStringFactory newGraphemeFactory() {
        return newFactory(GRAPHEME_SYMBOL);
    }
}
