/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.util;

import org.beehive.core.dictionary.DictionaryEnumeration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 枚举类型工具类。<br/>
 * <br>
 * 提供枚举类型操作的方法定义，例如：通过枚举名称获取枚举对象等。
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class EnumUtils {

    /**
     * 枚举默认名称字段名
     */
    private static final String ENUM_DEFAULT_FIELD_NAME = "name";

    /**
     * 空字符对象的字符串格式
     */
    private static final String NULL_STRING = "null";

    /**
     * 比较枚举唯一值时是否忽略大小写
     */
    private static boolean IGNORE_CASE = true;

    /**
     * 枚举唯一标识的字段名称
     */
    private static String FIELD_NAME = ENUM_DEFAULT_FIELD_NAME;

    /**
     * 这是比较枚举唯一值是是否忽略大小写
     *
     * @param ignoreCase true-忽略；false-不忽略
     */
    public void setIgnoreCase(boolean ignoreCase) {
        EnumUtils.IGNORE_CASE = ignoreCase;
    }

    /**
     * 设置枚举枚举唯一标识字段名称
     *
     * @param fieldName 字段名称
     */
    public void setFieldName(String fieldName) {
        EnumUtils.FIELD_NAME = fieldName;
    }

    /**
     * 获取枚举项列表。<br/>
     * 等同枚举的<code>values()</code>方法。
     *
     * @param enumClass 枚举类类型
     * @param <E>       枚举类类型
     * @return 枚举项列表
     */
    public static <E extends Enum<E>> List<E> getInstances(Class<E> enumClass) {
        E[] enumItemArray = enumClass.getEnumConstants();
        return Arrays.asList(enumItemArray);
    }

    /**
     * 根据指定的任意字段的值标识符来查找对应的枚举项。如果该字段的值标识符支持重复，则可能返回多个枚举项实例。<br/>
     * 会根据全局配置的是否忽略大小写[{@value IGNORE_CASE}]来进行值的比较。
     *
     * @param enumClass  枚举类类型
     * @param identifier 值标识符
     * @param fieldName  待查找匹配的字段名称
     * @param <E>        枚举类类型
     * @return 匹配的枚举项列表
     */
    public static <E extends Enum<E>> List<E> getInstances(Class<E> enumClass, String identifier, String fieldName) {
        List<E> enumList = new ArrayList<E>();
        E[] enumItemArray = enumClass.getEnumConstants();
        if (enumItemArray.length == 0) {
            return enumList;
        }
        try {
            for (E enumItem : enumItemArray) {
                Field field = null;
                if (ENUM_DEFAULT_FIELD_NAME.equals(fieldName)) {
                    field = Enum.class.getDeclaredField(fieldName);
                } else {
                    field = enumItem.getClass().getDeclaredField(fieldName);
                }
                if (field == null) {
                    continue;
                }
                /*if (field.isEnumConstant()) {
                    break;
                }*/
                field.setAccessible(true);
                String value = String.valueOf(field.get(enumItem));
                boolean isEquals = (IGNORE_CASE && value.equalsIgnoreCase(identifier)) || value.equals(identifier);
                if (isEquals) {
                    enumList.add(enumItem);
                    continue;
                }
            }
        } catch (Exception e) {
            return enumList;
        }
        return enumList;
    }

    /**
     * 根据指定的全局配置字段[{@value FIELD_NAME}]的值表示来查找对应的枚举项。如果该字段的值标识符支持重复，则可能返回多个枚举项实例。<br/>
     * 会根据全局配置的是否忽略大小写[{@value IGNORE_CASE}]来进行值的比较。
     *
     * @param enumClass  枚举类类型
     * @param identifier 值标识符
     * @param <E>        枚举类类型
     * @return 匹配的枚举项列表
     * @see #getInstances(Class, String, String)
     */
    public static <E extends Enum<E>> List<E> getInstances(Class<E> enumClass, String identifier) {
        return getInstances(enumClass, identifier, FIELD_NAME);
    }

    /**
     * 根据指定的任意字段的值标识符来查找对应的枚举项。并返回首个匹配的枚举项，如果没有匹配则返回null。<br/>
     * 会根据全局配置的是否忽略大小写[{@value IGNORE_CASE}]来进行值的比较。
     *
     * @param enumClass  枚举类类型
     * @param identifier 值标识符
     * @param fieldName  待查找匹配的字段名称
     * @param <E>        枚举类类型
     * @return 首个匹配的枚举项实例或null
     * @see #getInstances(Class, String, String)
     */
    public static <E extends Enum<E>> E getInstance(Class<E> enumClass, String identifier, String fieldName) {
        List<E> enumItemList = getInstances(enumClass, identifier, fieldName);
        return enumItemList.size() > 0 ? enumItemList.get(0) : null;
    }

    /**
     * 根据指定的全局配置字段[{@value FIELD_NAME}]的值表示来查找对应的枚举项，并返回第一个匹配的枚举项，如果没有匹配则返回null。<br/>
     * 会根据全局配置的是否忽略大小写[{@value IGNORE_CASE}]来进行值的比较。
     *
     * @param enumClass  枚举类类型
     * @param identifier 值标识符
     * @param <E>        枚举类类型
     * @return 首个匹配的枚举项实例或null
     * @see #getInstance(Class, String)
     */
    public static <E extends Enum<E>> E getInstance(Class<E> enumClass, String identifier) {
        return getInstance(enumClass, identifier, FIELD_NAME);
    }

    /**
     * 根据指定的任意字段的值标识符来查找对应的枚举项。并返回首个匹配的枚举项，如果没有匹配则返回指定的默认枚举对象。<br/>
     * 会根据全局配置的是否忽略大小写[{@value IGNORE_CASE}]来进行值的比较。
     *
     * @param enumClass    枚举类类型
     * @param identifier   值标识符
     * @param fieldName    待查找匹配的字段名称
     * @param enumInstance 默认返回枚举对象
     * @param <E>          枚举类类型
     * @return 首个匹配的枚举项实例或默认返回枚举对象
     * @see #getInstances(Class, String, String)
     */
    public static <E extends Enum<E>> E getInstanceOrDefault(Class<E> enumClass, String identifier, String fieldName, E enumInstance) {
        List<E> enumItemList = getInstances(enumClass, identifier, fieldName);
        return enumItemList.size() > 0 ? enumItemList.get(0) : enumInstance;
    }

    /**
     * 根据指定的全局配置字段[{@value FIELD_NAME}]的值表示来查找对应的枚举项，并返回第一个匹配的枚举项，如果没有匹配则指定的默认枚举对象。<br/>
     * 会根据全局配置的是否忽略大小写[{@value IGNORE_CASE}]来进行值的比较。
     *
     * @param enumClass    枚举类类型
     * @param identifier   值标识符
     * @param enumInstance 默认返回枚举对象
     * @param <E>          枚举类类型
     * @return 首个匹配的枚举项实例或默认返回枚举对象
     * @see #getInstanceOrDefault(Class, String, String, Enum)
     */
    public static <E extends Enum<E>> E getInstanceOrDefault(Class<E> enumClass, String identifier, E enumInstance) {
        return getInstanceOrDefault(enumClass, identifier, FIELD_NAME, enumInstance);
    }

    /**
     * 判断指定的值标识符是否是一个枚举实例；根据指定的字段进行比较匹配。同时如果给定的值标识为空，指定是否允许忽略。<br/>
     * 会根据全局配置的是否忽略大小写[{@value IGNORE_CASE}]来进行值的比较。
     *
     * @param enumClass  枚举类类型
     * @param identifier 值标识符
     * @param fieldName  待查找匹配的字段名称
     * @param ignoreNull 是否忽略空标识符
     * @param <E>        枚举类类型
     * @return 如果忽略空，则传入是空则返回true；如果不忽略空，如果是枚举项则返回true，否则或异常则返回false
     */
    public static <E extends Enum<E>> boolean isEnumInstance(Class<E> enumClass, String identifier, String fieldName, boolean ignoreNull) {
        if (ignoreNull && NULL_STRING.equals(String.valueOf(identifier))) {
            return true;
        }
        try {
            E[] enumItemArray = enumClass.getEnumConstants();
            for (E enumItem : enumItemArray) {
                Field field = null;
                if (ENUM_DEFAULT_FIELD_NAME.equals(fieldName)) {
                    field = Enum.class.getDeclaredField(fieldName);
                } else {
                    field = enumItem.getClass().getDeclaredField(fieldName);
                }
                if (field == null) {
                    return false;
                }
                field.setAccessible(true);
                String value = String.valueOf(field.get(enumItem));
                boolean isEnum = (IGNORE_CASE && value.equalsIgnoreCase(identifier)) || value.equals(identifier);
                if (isEnum) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * 判断指定的值标识符是否是一个枚举实例；根据默认配置字段{@value FIELD_NAME}进行比较匹配。同时如果给定的值标识为空，指定是否允许忽略。<br/>
     * 会根据全局配置的是否忽略大小写[{@value IGNORE_CASE}]来进行值的比较。
     *
     * @param enumClass  枚举类类型
     * @param identifier 值标识符
     * @param ignoreNull 是否忽略空标识符
     * @param <E>        枚举类类型
     * @return 如果忽略空，则传入是空则返回true；如果不忽略空，如果是枚举项则返回true，否则或异常则返回false
     */
    public static <E extends Enum<E>> boolean isEnumInstance(Class<E> enumClass, String identifier, boolean ignoreNull) {
        return isEnumInstance(enumClass, identifier, FIELD_NAME, ignoreNull);
    }

    /**
     * 判断指定的值标识符是否是一个枚举实例；根据指定的字段进行比较匹配,如果输入的值标识符为空则返回true。<br/>
     * 会根据全局配置的是否忽略大小写[{@value IGNORE_CASE}]来进行值的比较。
     *
     * @param enumClass  枚举类类型
     * @param identifier 值标识符
     * @param fieldName  待查找匹配的字段名称
     * @param <E>        枚举类类型
     * @return 如果传入是空则返回true；如果不为空，如果是枚举项则返回true，否则或异常则返回false
     */
    public static <E extends Enum<E>> boolean isEnumInstance(Class<E> enumClass, String identifier, String fieldName) {
        return isEnumInstance(enumClass, identifier, fieldName, true);
    }

    /**
     * 判断指定的值标识符是否是一个枚举实例；根据默认配置的字段{@value FIELD_NAME}进行比较匹配,如果输入的值标识符为空则返回true。<br/>
     * 会根据全局配置的是否忽略大小写[{@value IGNORE_CASE}]来进行值的比较。
     *
     * @param enumClass  枚举类类型
     * @param identifier 值标识符
     * @param <E>        枚举类类型
     * @return 如果传入是空则返回true；如果不为空，如果是枚举项则返回true，否则或异常则返回false
     */
    public static <E extends Enum<E>> boolean isEnumInstance(Class<E> enumClass, String identifier) {
        return isEnumInstance(enumClass, identifier, FIELD_NAME, true);
    }

    /**
     * 将枚举对象转换为字符串格式。分为原始枚举和字典项枚举定义的格式。
     *
     * @param instance 枚举项对象
     * @param <E>      枚举类类型
     * @return 格式化后的字符串
     * @see Enum#name()
     * @see DictionaryEnumeration#toJSONString()
     */
    public static <E extends Enum<E>> String toString(E instance) {
        if (instance == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(instance.name()).append(" = ");
        if (instance instanceof DictionaryEnumeration) {
            sb.append(((DictionaryEnumeration) instance).toJSONString()).append(" @DictionaryEnumeration");
            return sb.toString();
        } else {
            sb.append(instance.name()).append(" @Enum");
            return sb.toString();
        }
    }

}
