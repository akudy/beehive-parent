/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 构造函数匹配器。
 * <br>
 * 基于构造函数的指定条件进行匹配，这里定义了构建函数匹配器的条件内容和匹配规则。
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class ConstructorMatcher extends ClassMemberMatcher<Constructor> {

    /**
     * 构造函数参数列表
     */
    private Type[] parameterTypes;

    private ConstructorMatcher(Builder builder) {
        this.setModifiers(builder.modifiers);
        this.parameterTypes = builder.parameterTypeList.toArray(new Type[]{});
        this.setAnnotations(builder.annotations);
        this.setAnnotationMatchStrategy(builder.annotationMatchStrategy);
    }

    /**
     * 获取构造函数匹配器定义的参数类型列表
     *
     * @return 构造函数匹配器定义的参数类型列表
     */
    public Type[] getDeclaredParameterTypes() {
        return parameterTypes;
    }

    @Override
    public boolean accept(Constructor constructor) {
        // 声明类匹配
        if (!this.declaringClassMatch(constructor)) {
            return false;
        }
        // 修饰符匹配
        if (!this.modifierMatch(constructor)) {
            return false;
        }
        //注解匹配
        if (!this.annotationMatch(constructor)) {
            return false;
        }
        //参数列表匹配
        Class<?>[] tempParamTypes = constructor.getParameterTypes();
        // 所有构造函数的第一个参数为所属类型定义，在比较匹配时需要剔除
        int tempParamLength = tempParamTypes.length - 1;
        // 默认构造函数
        boolean nonParameterFlag = this.parameterTypes == null || this.parameterTypes.length == 0;
        if (nonParameterFlag) {
            //匹配器没有明确参数
            if (tempParamLength > 0) {
                return false;
            }
        } else {
            int parameterLength = this.parameterTypes.length;
            //匹配器有参数指定
            if (parameterLength != tempParamLength) {
                return false;
            } else {
                // 有参数，剔除构造函数的第一个参数后，如果参数长度相等，则逐个类型进行比较
                for (int i = 0, size = parameterLength; i < size; i++) {
                    // 如果某一个参数类型不匹配，则直接跳出比较
                    if (this.parameterTypes[i] != tempParamTypes[i + 1]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 构造函数匹配器构造器
     */
    public static class Builder extends ClassMemberMatcher.Builder<ConstructorMatcher, Builder> {

        private List<Type> parameterTypeList;

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
         * 添加一个参数类型列表，按顺序进行添加
         *
         * @param parameterType 参数类型列表
         * @return 当前构造器
         */
        public Builder addParameterType(Type... parameterType) {
            if (this.parameterTypeList == null) {
                this.parameterTypeList = new ArrayList<>();
            }
            this.addParameterType(this.parameterTypeList, parameterType);
            return this;
        }

        @Override
        public Builder _self() {
            return this;
        }

        @Override
        public ConstructorMatcher build() {
            return new ConstructorMatcher(this);
        }

    }
}
