/*
 * Copyright (c) 2019-2022 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.reflection.MethodMatcher
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2022-07-01
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法匹配器。
 * <br>
 * 基于方法的指定条件进行匹配，这里定义了方法匹配器的条件内容和匹配规则。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.reflection.matcher</code></li>
 *   <li>Class Name: <code>MethodMatcher</code></li>
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
 *         <td align="center"><em>2022-07-01</em></td>
 *         <td align="center"><em>akudy</em></td>
 *         <td><em>Define</em></td>
 *       </tr>
 *     </table>
 *   </dd>
 * </dl>
 *
 * @author <a href="mailto:akudys@163.com">akudy</a>
 * @version 1.0
 * @since 1.0
 */
public class MethodMatcher extends ClassMemberMatcher<Method> {

    /**
     * 方法返回类型
     */
    private Type returnType;

    /**
     * 方法名称
     */
    private String name;

    /**
     * 方法参数列表
     */
    private Type[] parameterTypes;

    /**
     * 获取方法返回类型定义
     *
     * @return 返回类型
     */
    public Type getReturnType() {
        return this.returnType;
    }

    /**
     * 获取方法名称定义
     *
     * @return 方法名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 获取方法参数类型列表定义
     *
     * @return 方法参数类型列表
     */
    public Type[] getParameterTypes() {
        return this.parameterTypes;
    }

    private MethodMatcher(Builder builder) {
        this.name = builder.name;
        this.returnType = builder.returnType;
        this.parameterTypes = builder.parameterTypeList.toArray(new Type[]{});
    }

    @Override
    public boolean accept(Method method) {
        // 声明类匹配
        if (!this.declaringClassMatch(method)) {
            return false;
        }
        // 修饰符匹配
        if (!this.modifierMatch(method)) {
            return false;
        }
        //注解匹配
        if (!this.annotationMatch(method)) {
            return false;
        }
        if (this.getName() != null && !(this.getName().equals(method.getName()))) {
            return false;
        }
        if (this.getReturnType() != null && !(this.getReturnType() == method.getReturnType())) {
            return false;
        }
        //参数列表匹配
        Class<?>[] tempParamTypes = method.getParameterTypes();
        // 无参匹配
        boolean nonParameterFlag = this.parameterTypes == null || this.parameterTypes.length == 0;
        if (nonParameterFlag) {
            //匹配器没有明确参数
            return tempParamTypes.length > 0 ? false : true;
        } else {
            int parameterLength = this.parameterTypes.length;
            //匹配器有参数指定
            if (parameterLength != tempParamTypes.length) {
                return false;
            } else {
                // 有参数，剔除构造函数的第一个参数后，如果参数长度相等，则逐个类型进行比较
                for (int i = 0, size = parameterLength; i < size; i++) {
                    // 如果某一个参数类型不匹配，则直接跳出比较
                    if (this.parameterTypes[i] != tempParamTypes[i]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 方法匹配器构造器
     */
    public static class Builder extends ClassMemberMatcher.Builder<MethodMatcher, Builder> {

        private String name;
        private Type returnType;
        private List<Type> parameterTypeList = new ArrayList<>();

        /**
         * 设置方法名称
         *
         * @param name 方法名称
         * @return 当前构造器
         */
        public Builder nameOf(String name) {
            this.name = name;
            return this;
        }

        /**
         * 设置方法返回类型
         *
         * @param returnType 方法返回类型
         * @return 当前构造器
         */
        public Builder returnTypeOf(Type returnType) {
            this.returnType = returnType;
            return this;
        }

        /**
         * 插入参数类型到列表中，同时指定插入位置
         *
         * @param index         参数类型未知
         * @param parameterType 参数类型
         * @return 当前构造器
         */
        public Builder parameterTypeOf(int index, Type parameterType) {
            if (this.parameterTypeList == null) {
                this.parameterTypeList = new ArrayList<>();
            }
            this.parameterTypeList.set(index, parameterType);
            return this;
        }

        /**
         * 添加一个参数类型，按顺序进行添加
         *
         * @param parameterType 参数类型
         * @return 当前构造器
         */
        public Builder addParameterType(Type... parameterType) {
            this.addParameterType(this.parameterTypeList, parameterType);
            return this;
        }

        @Override
        public Builder _self() {
            return this;
        }

        @Override
        public MethodMatcher build() {
            return new MethodMatcher(this);
        }
    }

}
