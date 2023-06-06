/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.string.test;

import org.beehive.core.string.Slf4jStringFormatter;
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
public class Slf4jStringFormatterTest {

    Slf4jStringFormatter formatter = new Slf4jStringFormatter();

    @Test
    public void test() {
        String message = "this is str template: name = {}, jobs = {}, \'sex\' = \\{{}\\}, description = {my name is xx}, {hello!{}}";
        System.out.println(message + " format of = ");
        System.out.println("\t" + formatter.format(message, "akudy"));
        System.out.println("\t" + formatter.format(message, "akudy", "java"));
        System.out.println("\t" + formatter.format(message, "akudy", "java", 'm', 'o'));
        System.out.println("\t" + formatter.format(message, "akudy", new String[]{"java", "python"}));
    }

}
