/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.file.test;

import org.beehive.core.file.AntPathMatcher;
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
public class AntPathMatcherTest {

    @Test
    public void matchTest() {
        AntPathMatcher matcher = new AntPathMatcher();
        String path = "/a/b/c/d1/e1/f1/g1/h1/i1/j/k.txt";
        String pattern = "**";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "*";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "?";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/*";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/?";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/*/*";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/*";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/*.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/?.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/*/*.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/*/?.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/a/b/c/d1/e1/f1/g1/h1/i1/j/k.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/**/**/**/**/**/**/**/**/*/**";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/**/**/**/**/";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/*/*.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/*/**/*.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/*/?.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/**/*/**/?.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
        pattern = "/a/b/c/**/e/**/f/g/**/?.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/**";
        path = "/abc/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/**";
        path = "/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/**/*";
        path = "/abc/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/**/*";
        path = "/abc/a/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/ab*/*";
        path = "/abc/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/ab*/*";
        path = "/abc/a/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/abc/*";
        path = "/abc/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));

        pattern = "/abc/*";
        path = "/abc/a/a.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));


    }

    @Test
    public void test(){
        AntPathMatcher matcher = new AntPathMatcher();
        String path = "/a/b/c/d1/e1/f1/g1/h1/i1/j/k.txt";
        String pattern = "/a/b/c/**/e/**/f/g/**/?.txt";
        System.out.println(String.format("match(\"%s\", \"%s\") = %s", pattern, path, matcher.match(pattern, path)));
    }

}
