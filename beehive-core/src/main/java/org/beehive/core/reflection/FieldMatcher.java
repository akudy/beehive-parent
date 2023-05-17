/*
 * Copyright (c) 2019-2022 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.reflection.FieldMatcher
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2022-07-01
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * 字段匹配器。
 * <br>
 * 基于字段的指定条件进行匹配，这里定义了字段匹配器的条件内容和匹配规则。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.reflection.matcher</code></li>
 *   <li>Class Name: <code>FieldMatcher</code></li>
 *   <li>Java Version Used: Java 8</li>
 *   <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 *   <dd>
 *     <table border="1" cellspacing="0" cellpadding="0" summary=""Upgrade&shy;Modify History>
 *       <tr>
 *         <th>Version</th>
 *         <th>Environment</th>
 *         <th>ModifyTime</th>
 *         <th>Modifier</th>
 *         <th>Description</th>
 *       </tr>
 *       <tr>
 *         <td align="center"><em>1.0</em></td>
 *         <td align="center"><em>Java 8</em></td>
 *         <td align="center"><em>2022-06-29</em></td>
 *         <td align="center"><em>akudy</em></td>
 *         <td><em>Define</em></td>
 *       </tr>
 *     </table>
 *   </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @since 1.0
 */
public class FieldMatcher extends ClassMemberMatcher<Field> {

    /**
     * 属性/字段类型
     */
    private Type type;

    /**
     * 属性/字段名称
     */
    private String name;

    /**
     * 获取属性/字段匹配的类型定义
     *
     * @return 属性/字段类型定义
     */
    public Type getDeclaredType() {
        return this.type;
    }

    /**
     * 获取属性/字段匹配的名称定义
     *
     * @return 属性/字段名称定义
     */
    public String getName() {
        return this.name;
    }

    /**
     * 复杂的字段匹配器
     *
     * @param builder 字段匹配器构造器
     */
    private FieldMatcher(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.setModifiers(builder.modifiers);
        this.setAnnotations(builder.annotations);
        this.setAnnotationMatchStrategy(builder.annotationMatchStrategy);
    }


    @Override
    public boolean accept(Field field) {
        // 声明类匹配
        if (!this.declaringClassMatch(field)) {
            return false;
        }
        // 修饰符匹配
        if (!this.modifierMatch(field)) {
            return false;
        }
        //注解匹配
        if (!this.annotationMatch(field)) {
            return false;
        }
        if (this.getDeclaredType() != null && this.getDeclaredType() != field.getType()) {
            return false;
        }
        if (this.getName() != null && !(this.getName().equals(field.getName()))) {
            return false;
        }
        return true;
    }

    /**
     * 字段匹配器构造器
     */
    public static class Builder extends ClassMemberMatcher.Builder<FieldMatcher, Builder> {

        private Type type;
        private String name;

        public Builder(String name) {
            this.name = name;
        }

        /**
         * 设置声明类型
         *
         * @param declaredType 字段声明类型
         * @return 构造器
         */
        public Builder typeOf(Type declaredType) {
            this.type = declaredType;
            return this;
        }

        @Override
        public Builder _self() {
            return this;
        }

        /**
         * 创建字段匹配器对象
         *
         * @return 字段匹配器对象
         */
        @Override
        public FieldMatcher build() {
            return new FieldMatcher(this);
        }

    }

}
