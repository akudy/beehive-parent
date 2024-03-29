/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.charset1.test;

import org.beehive.util.ClassUtils;
import org.beehive.util.EnumUtils;
import org.beehive.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Unicode 统一编码集。
 * <br>
 * 描述Unicode字符集的组成。
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class UnicodeCharset {

    /**
     * 字符集列表
     */
    private static Map<String, CharacterGroup> charGroupMap;

    /**
     * 初始化字符集李诶报
     */
    private UnicodeCharset() {
        charGroupMap = new HashMap<>();
    }

    /**
     * 获取所有的字符组名称
     *
     * @return 字符组名称列表
     */
    public Set<String> getGroupNames() {
        return Collections.unmodifiableSet(UnicodeCharset.charGroupMap.keySet());
    }

    /**
     * 获取所有的字符组对象列表
     *
     * @return 字符组对象列表
     */
    public Collection<CharacterGroup> getGroups() {
        return Collections.unmodifiableCollection(UnicodeCharset.charGroupMap.values());
    }

    /**
     * 根据字符组名称获取一个字符组对象
     *
     * @param groupName 字符组名称
     * @return 字符组对象
     */
    public CharacterGroup getGroup(String groupName) {
        return UnicodeCharset.charGroupMap.getOrDefault(groupName, null);
    }

    /**
     * 获取Unicode 字符集对象，内部会进行字符集的加载
     *
     * @return 字符集对象
     */
    public static UnicodeCharset getUnicodeCharset() {
        Holder.charset.loadCharset();
        return Holder.charset;
    }

    private static final String CHAR_SET_FILE_NAME = "charset/charset-chargroup.tb";
    private static final String PREFIX_COMMENT = "#";
    private static final String PREFIX_CHAR_GROUP = "";
    private static final String PREFIX_CHAR_TABLE = "   ";
    private static final String COLUMNS_SEPARATOR = "  ";

    /**
     * 加载字符集文件
     */
    private void loadCharset() {
        try {
            URL fileUrl = new URL(ClassUtils.getClassLoadURL(UnicodeCharset.class).toString() + CHAR_SET_FILE_NAME);
            InputStream in = fileUrl.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = null;

            CharacterGroup group = null;
            CharacterTable table = null;

            while ((line = reader.readLine()) != null) {
                try {
                    if (line.trim().length() == 0) {
                        continue;
                    }
                    if (line.startsWith(PREFIX_COMMENT)) {
                        continue;
                    }
                    if (line.startsWith(PREFIX_CHAR_TABLE)) {
                        line = line.trim();
                        if (line.startsWith(PREFIX_COMMENT)) {
                            continue;
                        }
                        if (group == null) {
                            throw new IOException("make sure that the character-group takes precedence. message = " + line);
                        }
                        String[] tableColumns = line.split(COLUMNS_SEPARATOR);
                        group.addTable(intOfStr(tableColumns[0]), intOfStr(tableColumns[1]), enumOfStr(tableColumns[2]));
                    } else {
                        line = line.trim();
                        String[] groupColumns = line.split(COLUMNS_SEPARATOR);
                        group = new CharacterGroup(groupColumns[0], groupColumns[1]);
                        UnicodeCharset.charGroupMap.put(groupColumns[0], group);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println(line);
                    e.printStackTrace();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int intOfStr(String hexStr) {
        hexStr = hexStr.substring(2);
        return Integer.valueOf(hexStr, 16);
    }

    private static EnumSet<CharacterTag> enumOfStr(String tagStr) {
        EnumSet<CharacterTag> enumSet = EnumSet.noneOf(CharacterTag.class);
        if (StringUtils.isBlank(tagStr)) {
            return enumSet;
        }
        String[] tags = tagStr.split(",");
        for (String tag : tags) {
            CharacterTag tagEnum = EnumUtils.getInstance(CharacterTag.class, tag.trim().toUpperCase());
            if (tagEnum != null) {
                enumSet.add(tagEnum);
            }
        }
        return enumSet;
    }

    /**
     * 安全的单例类初始化
     */
    private static class Holder {

        private static final UnicodeCharset charset = new UnicodeCharset();

    }

    public static void main(String[] args) {
        Collection<CharacterGroup> sets = UnicodeCharset.getUnicodeCharset().getGroups();
        for (CharacterGroup group : sets) {
            group.list(System.out);
        }
    }

}
