/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.string;

import org.beehive.util.StringUtils;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * 字符串字典比较器，扩展至{@link Comparator}接口。
 * <br>
 * 这里字符串比较会按照字典的排序顺序来进行比较。
 *
 * @author akudy
 * @version 1.0
 * @since Java 8
 */
public class StringDictComparator implements Comparator<String> {

    /**
     * 这里的比较算法，会将字符串进行中文编码的转换。
     *
     * @param str1 被比较字符串文本
     * @param str1 比较字符串文本
     * @return 如果被比较字符串大于比较字符串则返回大于0；如果被比较字符串小于比较字符串则返回小于0；否则返回0
     * @see Collator#getInstance(Locale)
     * @see StringUtils#compareWithDict(String, String)
     */
    @Override
    public int compare(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return 0;
        }
        if (str1 == null && str2 != null) {
            return -1;
        }
        if (str1 != null && str2 == null) {
            return 1;
        }
        Comparator comparator = Collator.getInstance(Locale.CHINESE);
        return comparator.compare(str1, str2);
    }

}
