/*
 * Copyright (c) 2019-2022 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.reflection.ClassMemberMatcher
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2022-07-01
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.reflection;

import org.beehive.core.common.Matcher;
import org.beehive.util.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 类成员匹配器。
 * <br>
 * 用于匹配类成员是否符合指定的规则或要求。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 *   <li>Package Name: <code>org.beehive.core.reflection.matcher</code></li>
 *   <li>Class Name: <code>ClassMemberMatcher</code></li>
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
public abstract class ClassMemberMatcher<T extends Member> implements Matcher<T> {

    /**
     * 定义在什么类中，即当前成员所属的直接类型
     */
    private Class<?> declaringClass;

    /**
     * 修饰符
     */
    private int modifiers;

    /**
     * 注解类型列表
     */
    private Class<? extends Annotation>[] annotations;

    /**
     * 多注解的匹配策略；默认为任意匹配
     */
    private MatchStrategy annotationMatchStrategy = MatchStrategy.ANY;

    /**
     * 设置声明类定义
     *
     * @param declaringClass 声明类
     */
    protected void setDeclaringClass(Class<?> declaringClass) {
        this.declaringClass = declaringClass;
    }

    /**
     * 设置需要匹配的修饰符标记位值。
     *
     * @param modifiers 修饰标记位值
     */
    protected void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    /**
     * 设置需要匹配的注解列表
     *
     * @param annotations 注解列表
     */
    protected void setAnnotations(Class<? extends Annotation>[] annotations) {
        this.annotations = annotations;
    }

    /**
     * 设置多注解的匹配策略
     *
     * @param annotationMatchStrategy 匹配策略
     */
    protected void setAnnotationMatchStrategy(MatchStrategy annotationMatchStrategy) {
        this.annotationMatchStrategy = annotationMatchStrategy;
    }

    /**
     * 获取声明类定义
     *
     * @return 声明类定义
     */
    public Class<?> getDeclaringClass() {
        return this.declaringClass;
    }

    /**
     * 获取需要匹配的修饰符标记位值。<br/>
     * 例如：<code>
     * {@link java.lang.reflect.Modifier#PUBLIC} | {@link java.lang.reflect.Modifier#STATIC} | {@link java.lang.reflect.Modifier#FINAL}
     * </code>
     *
     * @return 字段修饰符的标记位值
     * @see java.lang.reflect.Modifier
     */
    public int getModifiers() {
        return this.modifiers;
    }

    /**
     * 获取需要匹配的注解列表
     *
     * @return 注解列表
     */
    public Class<? extends Annotation>[] getDeclaredAnnotations() {
        return this.annotations;
    }

    /**
     * 获取多注解的匹配策略
     *
     * @return 多注解的匹配策略
     */
    public MatchStrategy getAnnotationMatchStrategy() {
        return this.annotationMatchStrategy;
    }

    /**
     * 声明类型匹配检查
     *
     * @param obj 成员对象，必须是{@link Member}类型或其子类型
     * @return 如果声明类未定义，则默认为匹配；否则通过类型进行对比匹配
     * @see Member
     */
    protected boolean declaringClassMatch(T obj) {
        if (this.getDeclaringClass() != null) {
            return obj.getDeclaringClass() == this.getDeclaringClass();
        }
        return true;
    }

    /**
     * 修饰符匹配检查
     *
     * @param obj 成员对象，必须是{@link Member}类型或其子类型
     * @return 如果匹配符未设置，则默认为匹配；否则通过匹配符进行检查
     * @see Member
     */
    protected boolean modifierMatch(T obj) {
        if (this.getModifiers() != 0) {
            return ((obj.getModifiers() & this.getModifiers()) == this.getModifiers());
        }
        return true;
    }

    private <E extends AccessibleObject> Class<? extends Annotation>[] getAnnotationClasses(E obj) {
        Annotation[] annotations = obj.getAnnotations();
        int length = annotations.length;
        Class<? extends Annotation>[] annotationClasses = new Class[length];
        if (obj.getAnnotations().length > 0) {
            for (int i = 0; i < length; i++) {
                annotationClasses[i] = annotations[i].annotationType();
            }
        }
        return annotationClasses;
    }

    /**
     * 注解匹配检查
     *
     * @param obj 成员对象，必须是{@link AccessibleObject}类型或其子类型
     * @param <E> 参数类型限定符
     * @return 如果注解不存在，则默认为匹配；否则通过注解进行检查，并基于匹配策略
     */
    protected <E extends AccessibleObject> boolean annotationMatch(E obj) {
        if (this.getDeclaredAnnotations() != null) {
            if (this.getAnnotationMatchStrategy() == MatchStrategy.ALL) {
                return ArrayUtils.containsAll(this.getAnnotationClasses(obj), ClassMemberMatcher.annotationClassComparator, this.getDeclaredAnnotations());
            } else {
                return ArrayUtils.containsAny(this.getAnnotationClasses(obj), ClassMemberMatcher.annotationClassComparator, this.getDeclaredAnnotations());
            }
        }
        return true;
    }

    /**
     * 这是一个具有层次化的构造器模式的实现，作为父类（抽象类）的构造器约束定义
     *
     * @param <O> 实际的实例化对象类型，扩展至该父类（抽象类）
     * @param <B> 真实的构造器类型
     */
    protected abstract static class Builder<O extends ClassMemberMatcher, B extends Builder<O, B>> {

        protected Class<?> declaringClass;
        protected int modifiers;
        protected Class<? extends Annotation>[] annotations;
        protected MatchStrategy annotationMatchStrategy = MatchStrategy.ANY;

        /**
         * 设置什么的类容器
         *
         * @param declaringClass 声明类
         * @return 构造器
         */
        public B classOf(Class<?> declaringClass) {
            this.declaringClass = declaringClass;
            return _self();
        }

        /**
         * 设置修饰符标识位值
         *
         * @param modifiers 修饰符标识位值
         * @return 构造器
         * @see Modifier
         */
        public B modifierOf(int... modifiers) {
            if (modifiers != null || modifiers.length > 0) {
                for (int i = 0, size = modifiers.length; i < size; i++) {
                    this.modifiers = this.modifiers | modifiers[i];
                }
                if ((Modifier.fieldModifiers() & this.modifiers) != this.modifiers) {
                    throw new IllegalArgumentException("field modifiers is error.");
                }
            }
            return _self();
        }

        /**
         * 设置修饰注解
         *
         * @param annotations 修饰注解
         * @return 构造器
         */
        public B annotationOf(Class<? extends Annotation>... annotations) {
            if (annotations != null && annotations.length > 0) {
                this.annotations = annotations;
            }
            return _self();
        }

        /**
         * 设置多注解的匹配策略
         *
         * @param annotationMatchStrategy
         * @return 构造器
         */
        public B annotationMatchStrategy(MatchStrategy annotationMatchStrategy) {
            this.annotationMatchStrategy = annotationMatchStrategy;
            return _self();
        }

        /**
         * 添加多参数类型到一个列表中，该方法属于子类重用代码的封装
         *
         * @param parameterTypeList 参数类型列表
         * @param parameterType     多个参数类型
         */
        protected void addParameterType(List<Type> parameterTypeList, Type... parameterType) {
            if (parameterTypeList == null) {
                parameterTypeList = new ArrayList<>();
            }
            if (parameterType != null && parameterType.length > 0) {
                for (Type type : parameterType) {
                    parameterTypeList.add(type);
                }
            }
        }


        /**
         * 构造器方法，返回该构造器构造的具体对象类型
         *
         * @return 具体对象
         */
        public abstract O build();

        /**
         * 返回构造器类型
         *
         * @return 真实构造器类型
         */
        public abstract B _self();

    }

    /**
     * 成员多属性参数的匹配策略，适用于注解、参数列表等匹配策略定义
     */
    public enum MatchStrategy {
        /**
         * 全部匹配，只有全部都匹配才返回true，否则返回false
         */
        ALL,
        /**
         * 任意匹配，只要任意一个匹配就返回true，如果都不匹配则返回false
         */
        ANY;
    }

    /**
     * 注解比较器
     */
    protected static final Comparator<Class<? extends Annotation>> annotationClassComparator = new Comparator<Class<? extends Annotation>>() {
        @Override
        public int compare(Class<? extends Annotation> o1, Class<? extends Annotation> o2) {
            return o1 == o2 ? 0 : 1;
        }
    };

}