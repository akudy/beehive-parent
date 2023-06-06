/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.util.test;

import org.beehive.util.PropertiesFileUtils;
import org.junit.Test;

import java.util.Properties;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class PropertiesFileUtilsTest {

    @Test
    public void loadTest() {
        try {
            Properties properties = PropertiesFileUtils.load("string/factory/common-characters");
            System.out.println(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
