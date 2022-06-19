/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.string.test.RandomStringFactoryTest
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-10-17
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.string.test;

import org.beehive.core.string.RandomStringFactory;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Comments,使用一句话简述该类信息，句末请使用./。
 * <br>
 * Description,类的详细描述信息,可使用简单的HTML标签
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.string.test</code></li>
 * <li>Class Name: <code>RandomStringFactoryTest</code></li>
 * <li>Java Version Used: Java 8</li>
 * <li>Compile With Java Version: JDK 8</li>
 * </ul>
 * <p>
 * <b>Upgrade/Modify Description:</b>
 * <dl>
 * <dd>
 * <table border="1" cellspacing="0" cellpadding="0" summary=""Upgrade&shy;Modify History>
 * <tr>
 * <th>Version</th>
 * <th>Environment</th>
 * <th>ModifyTime</th>
 * <th>Modifier</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td align="center"><em>1.0</em></td>
 * <td align="center"><em>Java 8</em></td>
 * <td align="center"><em>2020/10/17</em></td>
 * <td align="center"><em>akudy</em></td>
 * <td><em>Define</em></td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 *
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @since 1.0
 */
public class RandomStringFactoryTest {

    private void testRandom(RandomStringFactory factory, int charCount) {
        for (int i = 0; i < 10; i++) {
            System.out.println(factory.random(charCount));
        }
    }

    @Test
    public void testRandomCustom() {
        RandomStringFactory factory = RandomStringFactory.newFactory("我是中国人，我爱我的祖国");
        testRandom(factory, 3);
    }

    @Test
    public void testRandomDigit() {
        RandomStringFactory factory = RandomStringFactory.newDigitFactory();
        testRandom(factory, 6);
    }

    @Test
    public void testRandomAlphabet() {
        RandomStringFactory factory = RandomStringFactory.newAlphabetFactory();
        testRandom(factory, 6);
    }

    @Test
    public void testRandomAlphanumeric() {
        RandomStringFactory factory = RandomStringFactory.newAlphanumericFactory();
        testRandom(factory, 6);
    }

    @Test
    public void testRandomGrapheme() {
        RandomStringFactory factory = RandomStringFactory.newGraphemeFactory();
        testRandom(factory, 6);
    }

    @Test
    public void testLocalChar(){
        RandomStringFactory factory = RandomStringFactory.newLocalCharacterFactory();
        testRandom(factory, 20);
        for (int i = 0; i < 10; i++) {
            factory = RandomStringFactory.newLocalCharacterFactory();
            System.out.println(factory+"->"+factory.random(20));
        }
    }

    @Test
    public void testRandomFactory() {
        for (int i = 0; i < 10; i++) {
            RandomStringFactory factory = RandomStringFactory.newFactory("我是中国人，我爱我的祖国");
            System.out.println(factory + "->" + factory.random(3));
        }
    }

    @Test
    public void concurrentTestRandomFactory() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Thread thread1 = new Thread("Thread1") {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    RandomStringFactory factory = RandomStringFactory.newFactory("我是中国人，我爱我的祖国");
                    System.out.println(this.getName() + ": " + factory + "->" + factory.random(3));
                }
            }
        };
        Thread thread2 = new Thread("Thread2") {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    RandomStringFactory factory = RandomStringFactory.newFactory("我是中国人，我爱我的祖国");
                    System.out.println(this.getName() + ": " + factory + "->" + factory.random(3));
                }
            }
        };
        Thread thread3 = new Thread("Thread3") {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    RandomStringFactory factory = RandomStringFactory.newDigitFactory();
                    System.out.println(this.getName() + ": " + factory + "->" + factory.random(3));
                }
            }
        };
        Thread thread4 = new Thread("Thread4") {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    RandomStringFactory factory = RandomStringFactory.newLocalCharacterFactory();
                    System.out.println(this.getName() + ": " + factory + "->" + factory.random(10));
                }
            }
        };
        executor.submit(thread1);
        executor.submit(thread2);
        executor.submit(thread3);
        executor.submit(thread4);

        executor.shutdown();
    }

}
