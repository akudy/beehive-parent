/*
 * Copyright (c) 2019-2020 by akudy All Rights Reserved.
 * Create Environment: Windows10(64bit)/Jetbrains IDEA 2018/Java 8
 * Project Name: beehive-parent
 * Module Name: beehive-core
 * File Name: org.beehive.core.collection.AbstractListPage
 * Encoding: UTF-8
 * Creator: akudy(akudys@163.com)
 * Create Date: 2020-09-21
 * Comments: <简述该文件的内容和作用>
 */

package org.beehive.core.collection;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * 抽象的分页列表对象定义；内部使用{@link List}来作为列表的数据源进行分页处理。
 * <br>
 * 为了给方便的遍历，该列表支持自我迭代。分页后的列表不允许修改，也就是说输入到该对象的源列表或返回的列表都不允许在添加或删除元素，避免破坏分页对象。
 * <p>
 * <b>Type Informations:</b>
 * <ul>
 * <li>Package Name: <code>org.beehive.core.collection</code></li>
 * <li>Class Name: <code>AbstractListPage</code></li>
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
 * <td align="center"><em>2020/9/21</em></td>
 * <td align="center"><em>akudy</em></td>
 * <td><em>Define</em></td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 *
 * @param <E> 被分页的列表元素类型
 * @author akudy(akudys @ 163.com)
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractListPage<E> implements Pageable, Iterable<Page<E>> {

    /**
     * 每页数量大小，一个正整数值
     */
    private int pageSize;

    /**
     * 待分页的源数据
     */
    private List<E> sourceData;

    /**
     * 待分页的源数据总条数
     */
    private int totalCount;

    /**
     * 构建一个待分页的数据列表
     *
     * @param sourceData 源数据列表
     */
    public AbstractListPage(List<E> sourceData) {
        this(sourceData, DEFAULT_PAGE_SIZE);
    }

    /**
     * 构建一个指定页面大小的待分页的数据列表
     *
     * @param sourceData 源数据列表
     * @param pageSize   每页展示大小
     * @see Collections#unmodifiableList(List)
     */
    public AbstractListPage(List<E> sourceData, int pageSize) {
        this.sourceData = Collections.unmodifiableList(sourceData);
        this.pageSize = pageSize;
        this.totalCount = this.sourceData.size();
    }

    /**
     * 获取每页数量大小，一个正整数值
     *
     * @return 每页数量大小
     */
    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 获取待分页的数据总量，一个正整数值
     *
     * @return 需要分页的数据总量
     */
    @Override
    public int getTotalCount() {
        return this.totalCount;
    }

    /**
     * 获取页面数据列表<br/>
     * 如果页码或者页面大小为负数，则会获取源数据列表
     *
     * @return 当前页的数据列表
     */
    public Page<E> getPage(int pageNo) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        int startIndex = 0;
        int endIndex = this.getTotalCount();
        if (pageNo > 0 && this.pageSize > 0) {
            startIndex = this.pageSize * (pageNo - 1);
            endIndex = this.pageSize * pageNo;
            startIndex = startIndex > this.totalCount ? this.totalCount : startIndex;
            endIndex = endIndex > this.totalCount ? this.totalCount : endIndex;
        }
        List<E> subList = this.sourceData.subList(startIndex, endIndex);
        return new Page<>(pageNo, this.pageSize, this.totalCount, getPageCount(), new UnmodifiableSubList<>(subList));
    }

    /**
     * 获取首页数据列表
     *
     * @return 数据列表
     */
    public Page<E> getFirstPage() {
        return this.getPage(0);
    }

    /**
     * 获取最后一页数据列表
     *
     * @return 数据列表
     */
    public Page<E> getLastPage() {
        return this.getPage(this.getPageCount());
    }

    @Override
    public Iterator<Page<E>> iterator() {
        return new PageIterator();
    }

    /**
     * 这是一个迭代器实现
     */
    private class PageIterator implements Iterator<Page<E>> {

        int cursor = 1;

        @Override
        public boolean hasNext() {
            return cursor <= getPageCount();
        }

        @Override
        public Page<E> next() {
            try {
                int i = cursor;
                Page<E> nextPage = getPage(i);
                cursor = i + 1;
                return nextPage;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
    }

    /**
     * 不可编辑的子列表对象
     *
     * @param <E> 元素类型
     */
    private class UnmodifiableSubList<E> extends AbstractList<E> {

        final List<? extends E> list;

        UnmodifiableSubList(List<? extends E> list) {
            super();
            this.list = list;
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public boolean equals(Object o) {
            return o == this || list.equals(o);
        }

        @Override
        public int hashCode() {
            return list.hashCode();
        }

        @Override
        public E get(int index) {
            return list.get(index);
        }

        @Override
        public E set(int index, E element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(int index, E element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public E remove(int index) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int indexOf(Object o) {
            return list.indexOf(o);
        }

        @Override
        public int lastIndexOf(Object o) {
            return list.lastIndexOf(o);
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void replaceAll(UnaryOperator<E> operator) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void sort(Comparator<? super E> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ListIterator<E> listIterator() {
            return listIterator(0);
        }

        @Override
        public ListIterator<E> listIterator(final int index) {
            return new ListIterator<E>() {
                private final ListIterator<? extends E> i = list.listIterator(index);

                @Override
                public boolean hasNext() {
                    return i.hasNext();
                }

                @Override
                public E next() {
                    return i.next();
                }

                @Override
                public boolean hasPrevious() {
                    return i.hasPrevious();
                }

                @Override
                public E previous() {
                    return i.previous();
                }

                @Override
                public int nextIndex() {
                    return i.nextIndex();
                }

                @Override
                public int previousIndex() {
                    return i.previousIndex();
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }

                @Override
                public void set(E e) {
                    throw new UnsupportedOperationException();
                }

                @Override
                public void add(E e) {
                    throw new UnsupportedOperationException();
                }

                @Override
                public void forEachRemaining(Consumer<? super E> action) {
                    i.forEachRemaining(action);
                }
            };
        }

        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            return new UnmodifiableSubList<>(list.subList(fromIndex, toIndex));
        }

        private Object readResolve() {
            return this;
        }
    }

}
