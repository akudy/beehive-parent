/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.charset.test;

import org.beehive.util.StringUtils;
import org.junit.Test;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class UnicodeCharsetTest {

    @Test
    public void test(){
        String str = "我是";
        String str1 = StringUtils.decode(str,"ASCII");
        System.out.println(str1);

    }

}
