/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.file;

/**
 * 路径匹配器。用于完成文件路径的匹配工作。
 * <br>
 * 对文件路径进行匹配的接口定义。
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public interface PathMatcher{

    /**
     * 判断一个路径是否是一个模式方式。如果不是则完全不用使用模式进行匹配，可以直接进行相等比较。减少时间损耗。
     *
     * @param path 路径
     * @return 如果是模式字符串，则返回true；否则返回false。
     */
    boolean isPattern(String path);

    /**
     * 判断一个路径是否和模式匹配
     *
     * @param pattern 模式字符串
     * @param path    路径
     * @return 如果匹配则返回true；否则返回false。
     */
    boolean match(String pattern, String path);

}
