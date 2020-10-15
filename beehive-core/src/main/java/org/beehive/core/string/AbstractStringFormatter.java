/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.AbstractStringFormatter
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-10-13
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string;

import java.util.Set;

/**
 * 提供抽象的字符串格式类实现，该类主要提取格式化处理过程中共用的方法。
 * <br>
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.string</code></li>
 * <li>Class Name: <code>AbstractStringFormatter</code></li>
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
 * <td align="center"><em>2020/10/13</em></td>
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
public abstract class AbstractStringFormatter implements StringFormatter {

    /**
     * 将一个对象参数追加到字符串构建器中
     *
     * @param strBuilder 字符串构建器
     * @param object     对象参数
     * @param seenSet    对象数组散列表，用户进行缓存对象信息，防止重复解析追加；如果一个对象出现多次，则可以保证追加一次
     */
    protected void deeplyAppendParameter(StringBuilder strBuilder, Object object, Set<Object[]> seenSet) {
        if (object == null) {
            strBuilder.append("null");
            return;
        }
        if (!object.getClass().isArray()) {
            safeObjectAppend(strBuilder, object);
        } else {
            if (object instanceof boolean[]) {
                booleanArrayAppend(strBuilder, (boolean[]) object);
            } else if (object instanceof byte[]) {
                byteArrayAppend(strBuilder, (byte[]) object);
            } else if (object instanceof char[]) {
                charArrayAppend(strBuilder, (char[]) object);
            } else if (object instanceof short[]) {
                shortArrayAppend(strBuilder, (short[]) object);
            } else if (object instanceof int[]) {
                intArrayAppend(strBuilder, (int[]) object);
            } else if (object instanceof long[]) {
                longArrayAppend(strBuilder, (long[]) object);
            } else if (object instanceof float[]) {
                floatArrayAppend(strBuilder, (float[]) object);
            } else if (object instanceof double[]) {
                doubleArrayAppend(strBuilder, (double[]) object);
            } else {
                objectArrayAppend(strBuilder, (Object[]) object, seenSet);
            }
        }
    }

    /**
     * 安全的对象级字符串追加方法，如果出现异常，则保证不会终止程序
     *
     * @param strBuilder
     * @param object
     */
    protected void safeObjectAppend(StringBuilder strBuilder, Object object) {
        try {
            strBuilder.append(object.toString());
        } catch (Throwable t) {
            strBuilder.append("[FAILED toString()]");
        }
    }

    /**
     * 将对象数组元素追加到字符串构造器中
     *
     * @param strBuilder 字符串构造器
     * @param array      数组元素
     */
    protected void objectArrayAppend(StringBuilder strBuilder, Object[] array, Set<Object[]> seenSet) {
        strBuilder.append('[');
        if (!seenSet.contains(array)) {
            seenSet.add(array);
            final int len = array.length;
            for (int i = 0; i < len; i++) {
                deeplyAppendParameter(strBuilder, array[i], seenSet);
                if (i != len - 1) {
                    strBuilder.append(", ");
                }
            }
            seenSet.remove(array);
        } else {
            strBuilder.append("...");
        }
        strBuilder.append(']');
    }

    /**
     * 将数组元素追加到字符串构造器中
     *
     * @param strBuilder 字符串构造器
     * @param array      数组元素
     */
    protected void booleanArrayAppend(StringBuilder strBuilder, boolean[] array) {
        strBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            strBuilder.append(array[i]);
            if (i != len - 1) {
                strBuilder.append(", ");
            }
        }
        strBuilder.append(']');
    }

    /**
     * 将数组元素追加到字符串构造器中
     *
     * @param strBuilder 字符串构造器
     * @param array      数组元素
     */
    protected void byteArrayAppend(StringBuilder strBuilder, byte[] array) {
        strBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            strBuilder.append(array[i]);
            if (i != len - 1) {
                strBuilder.append(", ");
            }
        }
        strBuilder.append(']');
    }

    /**
     * 将数组元素追加到字符串构造器中
     *
     * @param strBuilder 字符串构造器
     * @param array      数组元素
     */
    protected void charArrayAppend(StringBuilder strBuilder, char[] array) {
        strBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            strBuilder.append(array[i]);
            if (i != len - 1) {
                strBuilder.append(", ");
            }
        }
        strBuilder.append(']');
    }

    /**
     * 将数组元素追加到字符串构造器中
     *
     * @param strBuilder 字符串构造器
     * @param array      数组元素
     */
    protected void shortArrayAppend(StringBuilder strBuilder, short[] array) {
        strBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            strBuilder.append(array[i]);
            if (i != len - 1) {
                strBuilder.append(", ");
            }
        }
        strBuilder.append(']');
    }

    /**
     * 将数组元素追加到字符串构造器中
     *
     * @param strBuilder 字符串构造器
     * @param array      数组元素
     */
    protected void intArrayAppend(StringBuilder strBuilder, int[] array) {
        strBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            strBuilder.append(array[i]);
            if (i != len - 1) {
                strBuilder.append(", ");
            }
        }
        strBuilder.append(']');
    }

    /**
     * 将数组元素追加到字符串构造器中
     *
     * @param strBuilder 字符串构造器
     * @param array      数组元素
     */
    protected void longArrayAppend(StringBuilder strBuilder, long[] array) {
        strBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            strBuilder.append(array[i]);
            if (i != len - 1) {
                strBuilder.append(", ");
            }
        }
        strBuilder.append(']');
    }

    /**
     * 将数组元素追加到字符串构造器中
     *
     * @param strBuilder 字符串构造器
     * @param array      数组元素
     */
    protected void floatArrayAppend(StringBuilder strBuilder, float[] array) {
        strBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            strBuilder.append(array[i]);
            if (i != len - 1) {
                strBuilder.append(", ");
            }
        }
        strBuilder.append(']');
    }

    /**
     * 将数组元素追加到字符串构造器中
     *
     * @param strBuilder 字符串构造器
     * @param array      数组元素
     */
    protected void doubleArrayAppend(StringBuilder strBuilder, double[] array) {
        strBuilder.append('[');
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            strBuilder.append(array[i]);
            if (i != len - 1) {
                strBuilder.append(", ");
            }
        }
        strBuilder.append(']');
    }

}
