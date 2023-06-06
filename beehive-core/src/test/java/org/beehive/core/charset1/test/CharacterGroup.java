/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.core.charset1.test;

import org.beehive.util.CollectionUtils;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * 字符组对象定义。
 * <br>
 * 描述一个字符组的组成。一个字符组由多个字符表组成。
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class CharacterGroup {

    /**
     * 字符集英文名称
     */
    private String enName;

    /**
     * 字符集中文名称
     */
    private String zhName;

    /**
     * 字符集包含的字符组列表
     */
    private Set<CharacterTable> tables;

    CharacterGroup(String enName, String zhName) {
        this.enName = enName;
        this.zhName = zhName;
        this.tables = new HashSet<>();
    }

    /**
     * 字符数量总数
     *
     * @return 字符组的总数
     */
    public int count() {
        int count = 0;
        Iterator<CharacterTable> iterator = this.tables.iterator();
        while (iterator.hasNext()) {
            count += iterator.next().count();
        }
        return count;
    }

    /**
     * 根据指定的标签查找包含该标签的所有字符表
     *
     * @param tag 标签值
     * @return 匹配的字符表
     */
    public List<CharacterTable> matchTables(CharacterTag tag) {
        return this.matchTablesOfAll(tag);
    }

    /**
     * 根据指定的标签查找包含所有标签的所有字符表
     *
     * @param tags 标签值列表
     * @return 匹配的字符表
     */
    public List<CharacterTable> matchTablesOfAll(CharacterTag... tags) {
        List<CharacterTable> mathTables = new ArrayList<>();
        Iterator<CharacterTable> iterator = this.tables.iterator();
        while (iterator.hasNext()) {
            CharacterTable table = iterator.next();
            if (CollectionUtils.containsAll(table.getTags(), tags)) {
                mathTables.add(table);
            }
        }
        return mathTables;
    }

    /**
     * 根据指定的标签查找包含任意标签的所有字符表
     *
     * @param tags 标签值列表
     * @return 匹配的字符表
     */
    public List<CharacterTable> matchTablesOfAny(CharacterTag... tags) {
        List<CharacterTable> mathTables = new ArrayList<>();
        Iterator<CharacterTable> iterator = this.tables.iterator();
        while (iterator.hasNext()) {
            CharacterTable table = iterator.next();
            if (CollectionUtils.containsAny(table.getTags(), tags)) {
                mathTables.add(table);
            }
        }
        return mathTables;
    }

    /**
     * 判断某个字符是否属于该字符组
     *
     * @param ch 某个字符
     * @return 如果属于该字符组，则返回true；否则返回false
     */
    public boolean contains(char ch) {
        Iterator<CharacterTable> iterator = this.tables.iterator();
        while (iterator.hasNext()) {
            CharacterTable table = iterator.next();
            if (table.contains(ch)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取所有的字符
     *
     * @return 该字符组下的所有字符
     */
    public char[] chars() {
        int charCount = this.count();
        int i = 0;
        char[] chars = new char[charCount];
        Iterator<CharacterTable> iterator = this.tables.iterator();
        while (iterator.hasNext()) {
            CharacterTable table = iterator.next();
            for (int code = table.getStart(); code <= table.getEnd(); code++) {
                chars[i++] = (char) code;
            }
        }
        return chars;
    }


    /**
     * 添加一个字符组到该字符集
     *
     * @param start 开始编号整数值
     * @param end   结束编码整数值
     * @param tags  标签列表
     */
    void addTable(int start, int end, EnumSet<CharacterTag> tags) {
        this.tables.add(new CharacterTable(start, end, tags));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CharacterGroup that = (CharacterGroup) o;
        return Objects.equals(enName, that.enName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enName);
    }


    public void chars(PrintStream out) {
        out.println("---enName=" + this.enName + ", zhName=" + this.zhName + ", count=" + this.count() + "---");
        if (this.tables != null) {
            Iterator<CharacterTable> iterator = this.tables.iterator();
            while (iterator.hasNext()) {
                CharacterTable table = iterator.next();
                for (int i = table.getStart(), size = table.getEnd(); i <= size; i++) {
                    out.print((char) i);
                }
            }
            out.println();
        }
        out.flush();
    }

    public void list(PrintStream out) {
        out.println("---enName=" + this.enName + ", zhName=" + this.zhName + ", count=" + this.count() + "---");
        if (this.tables != null) {
            Iterator<CharacterTable> iterator = this.tables.iterator();
            while (iterator.hasNext()) {
                CharacterTable table = iterator.next();
                out.println("\tstart=" + table.getStart() + "[" + toHexString(table.getStart()) + "], end=" + table.getEnd() + "[" + toHexString(table.getEnd()) + "], tags=" + table.getTags() + ", count=" + table.count());
            }
        }
        out.flush();
    }

    public void chars(PrintWriter out, boolean aColumn) {
        out.println("---enName=" + this.enName + ", zhName=" + this.zhName + ", count=" + this.count() + "---");
        if (this.tables != null) {
            Iterator<CharacterTable> iterator = this.tables.iterator();
            while (iterator.hasNext()) {
                CharacterTable table = iterator.next();
                for (int i = table.getStart(), size = table.getEnd(); i <= size; i++) {
                    if (aColumn) {
                        out.println((char) i);
                    } else {
                        out.print((char) i);
                    }
                }
            }
            if (!aColumn) {
                out.println();
            }
        }
        out.flush();
    }

    public void chars(PrintWriter out) {
        this.chars(out, false);
    }

    public void list(PrintWriter out) {
        out.println("---enName=" + this.enName + ", zhName=" + this.zhName + ", count=" + this.count() + "---");
        if (this.tables != null) {
            Iterator<CharacterTable> iterator = this.tables.iterator();
            while (iterator.hasNext()) {
                CharacterTable table = iterator.next();
                out.println("\tstart=" + table.getStart() + "[" + toHexString(table.getStart()) + "], end=" + table.getEnd() + "[" + toHexString(table.getEnd()) + "], tags=" + table.getTags() + ", count=" + table.count());
            }
        }
        out.flush();
    }

    private static String toHexString(int number) {
        return String.format("0x%04X", number);
    }
}
