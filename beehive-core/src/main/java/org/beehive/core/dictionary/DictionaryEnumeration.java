/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.dictionary.DictionaryEnumeration
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-24
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.dictionary;

/**
 * 可枚举的字典项定义。用户规范化枚举字典的定义。
 * <br>
 * 可枚举的字典包含，字典值代码、字典值展示标题、字典值描述基础信息。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.dictionary</code></li>
 * <li>Class Name: <code>DictionaryEnumeration</code></li>
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
 * <td align="center"><em>2020/9/24</em></td>
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
public interface DictionaryEnumeration<T> {

    /**
     * 字典代码
     *
     * @return 字典代码
     */
    T getCode();

    /**
     * 字典字符串描述，一般用于字典中文名称展示
     *
     * @return 字典字符串描述，一般用于字典中文名称展示
     */
    String getTitle();

    /**
     * 字典注释，默认等同字典字符串描述
     *
     * @return 字典注释
     */
    default String getDescription() {
        return this.getTitle();
    }

    /**
     * 比较输入的代码和当前枚举的代码是否相同。<br/>
     * <ul>
     * <li>用于比较输入的代码是否等同当前枚举的代码，如果相等则可以认定为当前枚举项。</li>
     * <li>如果是字符串，则忽略大小写</li>
     * </ul>
     *
     * @param code 输入的代码
     * @return 如果相等则返回true，否则返回false
     */
    default boolean sameCode(T code) {
        if (code instanceof String) {
            return ((String) this.getCode()).equalsIgnoreCase((String) code);
        }
        return this.getCode().equals(code);
    }

    /**
     * 字典枚举的JSON字符串形式。默认的JSON字符串仅仅包含name；这里将包含该枚举定义的基础元素code、title、description属性。<br/>
     * 通过该方法转换的JSON字符串在反序列化为对象时需要定制化处理。<br/>
     *
     * @return 字符串形式
     */
    default String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"code\": \"").append(this.getCode()).append("\", ");
        sb.append("\"title\": \"").append(this.getTitle()).append("\", ");
        sb.append("\"description\": ").append(this.getDescription()).append("\"");
        sb.append("}");
        return sb.toString();
    }

    /**
     * 获取指定枚举类型代码对应的枚举对象。<br/>
     * <ul>
     * <li>如果指定代码是当前字典枚举类型，则与该枚举类型的代码进行比较</li>
     * <li>如果指定代码不是当前字典枚举类型，则与其枚举名称进行比较</li>
     * </ul>
     *
     * @param enumType 枚举类
     * @param code     代码值
     * @param <E>      枚举类型
     * @param <T>      枚举代码类型
     * @return 匹配的枚举对象
     */
    static <T, E extends Enum<E>> E instanceOfCode(Class<E> enumType, T code) {
        if (!enumType.isEnum()) {
            throw new ClassCastException(enumType.getCanonicalName() + " is not an enumeration type");
        }
        E[] enums = enumType.getEnumConstants();
        if (enums == null || enums.length == 0) {
            throw new RuntimeException("Enumeration has no content defined. " + enumType.getCanonicalName());
        }
        for (E instance : enums) {
            if (instance instanceof DictionaryEnumeration) {
                if (((DictionaryEnumeration) instance).sameCode(code)) {
                    return instance;
                }
            } else {
                if (instance.name().equalsIgnoreCase(String.valueOf(code))) {
                    return instance;
                }
            }
        }
        throw new IllegalArgumentException("There is no matching enumeration definition. " + enumType.getCanonicalName() + "(" + code + ")");
    }

    /**
     * 获取指定枚举类型代码对应的枚举对象；如果没有找到则返回默认指定的枚举对象。<br/>
     *
     * @param enumType 枚举类
     * @param code     代码值
     * @param enumItem 默认的枚举对象
     * @param <E>      枚举类型
     * @param <T>      枚举代码类型
     * @return 匹配的枚举对象，或者指定的枚举对象
     * @see #instanceOfCode(Class, Object)
     */
    static <T, E extends Enum<E>> E instanceOfCodeOrDefault(Class<E> enumType, T code, E enumItem) {
        if (!enumType.isEnum()) {
            throw new ClassCastException(enumType.getCanonicalName() + " is not an enumeration type");
        }
        E[] enums = enumType.getEnumConstants();
        if (enums == null || enums.length == 0) {
            throw new RuntimeException("Enumeration has no content defined. " + enumType.getCanonicalName());
        }
        try {
            return instanceOfCode(enumType, code);
        } catch (Exception e) {
            return enumItem;
        }
    }

}
