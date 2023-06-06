/*
 * Copyright(c) 2021 By akudy All Rights Reserved.
 */

package org.beehive.util;

import org.beehive.core.algorithm.IndexRangeAlgorithm;
import org.beehive.core.collection.ListPage;

import java.util.*;

/**
 * 集合工具类。提供常规的集合判断、构建、转换等常用功能。
 * <br>
 * 该工具类用于辅助{@link java.util.Collections}工具类的实现数组其他功能的扩展。
 *
 * @author akudy
 * @version 1.0
 * @since 1.0
 */
public class CollectionUtils {

    /*----------------------------- null & notNull & empty && notEmpty start ----------------------------------------*/

    /**
     * 判断集合是空，即等于null。
     *
     * @param collection 集合对象
     * @param <E>        集合元素类型
     * @return 如果等于null则返回true，否则返回false
     * @since 1.0
     */
    public static <E> boolean isNull(Collection<E> collection) {
        return null == collection;
    }

    /**
     * 判断集合不是空，即不等于null。
     *
     * @param collection 集合对象
     * @param <E>        集合元素类型
     * @return 如果不等于null则返回true，否则返回false
     * @since 1.0
     */
    public static <E> boolean isNotNull(Collection<E> collection) {
        return !isNull(collection);
    }

    /**
     * 判断集合是空，即是null或者元素个数为0。
     *
     * @param collection 集合对象
     * @param <E>        集合元素类型
     * @return 如果不等于null或者元素个数为0则返回true，否则返回false
     * @since 1.0
     */
    public static <E> boolean isEmpty(Collection<E> collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * 判断集合不是空，即非null并且元素个数大于0。
     *
     * @param collection 集合对象
     * @param <E>        集合元素类型
     * @return 如果不等于null并且元素个数大于0则返回true，否则返回false
     * @since 1.0
     */
    public static <E> boolean isNotEmpty(Collection<E> collection) {
        return !isEmpty(collection);
    }

    /*----------------------------- null & notNull & empty && notEmpty end ----------------------------------------*/


    /*----------------------------- cast start ----------------------------------------*/

    /**
     * 判断一个对象是否是Collection对象
     *
     * @param object 任何对象
     * @return 如果是Collection对象则返回true，否则false
     * @since 1.0
     */
    public static boolean isCollection(Object object) {
        if (object instanceof Collection) {
            return true;
        }
        return false;
    }

    /**
     * 获取一个对象集合的长度
     *
     * @param object 任何对象
     * @return 如果是Collection对象则返回其size，否则返回-1
     * @since 1.0
     */
    public static int size(Object object) {
        if (isCollection(object)) {
            return ((Collection) object).size();
        } else {
            throw new ClassCastException("object [" + object + "] is not collection.");
        }
    }

    /**
     * 通过指定的元素列表实例化一个{@link ArrayList}列表对象
     *
     * @param e   元素列表
     * @param <E> 元素类型
     * @return {@link ArrayList}列表对象
     * @since 1.0
     */
    public static <E> ArrayList<E> newArrayList(E... e) {
        ArrayList<E> arrayList = new ArrayList<E>();
        if (ArrayUtils.isEmpty(e)) {
            return arrayList;
        }
        Collections.addAll(arrayList, e);
        return arrayList;
    }

    /**
     * 通过指定的元素列表实例化一个{@link HashSet}列表对象。
     * <br/>
     * 给定的元素对象必须实现{@link Comparable}接口规范，即重写{@link Object#equals(Object)}和{@link Object#hashCode()}方法
     *
     * @param e   元素列表
     * @param <E> 元素类型
     * @return {@link HashSet}列表对象
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> HashSet<E> newHashSet(E... e) {
        HashSet<E> hashSet = new HashSet<E>();
        if (ArrayUtils.isEmpty(e)) {
            return hashSet;
        }
        Collections.addAll(hashSet, e);
        return hashSet;
    }

    /**
     * 将一个{@link List}集合对象转换为一个{@link HashSet}列表对象。
     * <br/>
     * 给定的元素对象必须实现{@link Comparable}接口规范，即重写{@link Object#equals(Object)}和{@link Object#hashCode()}方法
     *
     * @param list {@link List}集合对象
     * @param <E>  集合元素类型
     * @return {@link HashSet}列表对象
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> HashSet<E> asHashSet(List<E> list) {
        HashSet<E> hashSet = new HashSet<>();
        hashSet.addAll(list);
        return hashSet;
    }

    /**
     * 将一个{@link Set}集合对象转换为一个{@link ArrayList}列表对象。
     * <br/>
     * 给定的元素对象必须实现{@link Comparable}接口规范，即重写{@link Object#equals(Object)}和{@link Object#hashCode()}方法
     *
     * @param set {@link Set}集合对象
     * @param <E> 集合元素类型
     * @return {@link ArrayList}列表对象
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> ArrayList<E> asArrayList(Set<E> set) {
        ArrayList<E> arrayList = new ArrayList<>();
        arrayList.addAll(set);
        return arrayList;
    }

    /*----------------------------- cast end ----------------------------------------*/

    /*----------------------------- operate start ----------------------------------------*/

    /**
     * 向集合中添加元素列表
     *
     * @param collection 集合对象
     * @param e          元素列表
     * @param <E>        元素类型
     * @see Collection#add(Object)
     * @since 1.0
     */
    public static <E> void add(Collection<E> collection, E... e) {
        if (!ArrayUtils.isEmpty(e)) {
            for (E element : e) {
                collection.add(element);
            }
        }
    }

    /**
     * 从集合中移除元素列表
     *
     * @param collection 集合对象
     * @param e          元素列表
     * @param <E>        元素类型
     * @see Collection#remove(Object)
     * @since 1.0
     */
    public static <E> void remove(Collection<E> collection, E... e) {
        if (!ArrayUtils.isEmpty(e)) {
            for (E element : e) {
                collection.remove(element);
            }
        }
    }

    /*----------------------------- operate end ----------------------------------------*/


    /*----------------------------- find start ----------------------------------------*/

    /**
     * 判断集合中是否包含指定的所有元素。
     *
     * @param collection 源集合
     * @param e          元素列表
     * @param <E>        元素类型
     * @return 如果所有元素都在集合中，则返回true；如果有一个不再集合中，则返回false
     * @see Collection#contains(Object)
     * @since 1.0
     */
    public static <E> boolean containsAll(Collection<E> collection, E... e) {
        if (ArrayUtils.isEmpty(e)) {
            return false;
        }
        for (E element : e) {
            if (!collection.contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断集合中是否包含指定的任意一个元素
     *
     * @param collection 源集合
     * @param e          元素列表
     * @param <E>        元素类型
     * @return 如果任意一个元素在集合中，则返回true；否则返回false
     * @see Collection#contains(Object)
     * @since 1.0
     */
    public static <E> boolean containsAny(Collection<E> collection, E... e) {
        if (ArrayUtils.isEmpty(e)) {
            return false;
        }
        for (E element : e) {
            if (collection.contains(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找指定元素在集合中出现的次数。元素类型需要实现{@link Comparable}接口的规范，重写{@link Object#equals(Object)}和{@link Object#hashCode()}方法。
     *
     * @param collection 源集合
     * @param e          待查找元素
     * @param <E>        元素类型
     * @return 如果不存在则返回0；否则返回该元素出现的次数
     * @see Comparable#compareTo(Object)
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> int count(Collection<E> collection, E e) {
        int count = 0;
        for (E element : collection) {
            if (element.compareTo(e) == 0) {
                count++;
            }
        }
        return count;
    }

    /*----------------------------- find end ----------------------------------------*/


    /*----------------------------- unite calc start ----------------------------------------*/

    /**
     * 将两个集合连接起来
     * <br/>
     * 给定的元素对象必须实现{@link Comparable}接口规范，即重写{@link Object#equals(Object)}和{@link Object#hashCode()}方法
     *
     * @param collectionA 集合A
     * @param collectionB 集合B
     * @param <E>         集合元素类型
     * @return 两个集合连接后的新集合
     * @see Collection#addAll(Collection)
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> ArrayList<E> join(Collection<E> collectionA, Collection<E> collectionB) {
        ArrayList<E> newList = new ArrayList<E>();
        newList.addAll(collectionA);
        newList.addAll(collectionB);
        return newList;
    }

    /**
     * 保证唯一性的向列表中添加元素
     */
    private static <E extends Comparable<? super E>> void add0(ArrayList<E> arrayList, Collection<E> collection) {
        if (isNotEmpty(collection)) {
            for (E e : collection) {
                if (arrayList.contains(e)) {
                    continue;
                }
                arrayList.add(e);
            }
        }
    }

    /**
     * 将两个集合进行合并，得出两个集合的合集，并去重。等同于并集（A∪B）
     * <br/>
     * 给定的元素对象必须实现{@link Comparable}接口规范，即重写{@link Object#equals(Object)}和{@link Object#hashCode()}方法
     *
     * @param collectionA 集合A
     * @param collectionB 集合B
     * @param <E>         集合元素类型
     * @return 并集之后的新集合
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> ArrayList<E> merge(Collection<E> collectionA, Collection<E> collectionB) {
        ArrayList<E> newList = new ArrayList<E>();
        add0(newList, collectionA);
        add0(newList, collectionB);
        return newList;
    }

    /**
     * 取两个集合共同的元素，得出两个集合的交集。等同于交集（A∩B）
     * <br/>
     * 给定的元素对象必须实现{@link Comparable}接口规范，即重写{@link Object#equals(Object)}和{@link Object#hashCode()}方法
     *
     * @param collectionA 集合A
     * @param collectionB 集合B
     * @param <E>         集合元素类型
     * @return 两个集合共同元素组成的新集合
     * @see Collection#addAll(Collection)
     * @see Collection#retainAll(Collection)
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> ArrayList<E> cross(Collection<E> collectionA, Collection<E> collectionB) {
        ArrayList<E> newList = new ArrayList<E>();
        newList.addAll(collectionA);
        newList.retainAll(collectionB);
        return newList;
    }

    /**
     * 在一个集合中移除包含在另一集合中的元素，并得出两个集合的差集。等同于差集（A－B）
     *
     * @param collectionA 集合A
     * @param collectionB 集合B
     * @param <E>         集合元素类型
     * @return 集合A排除在集合B中的元素后的新集合
     * @see Collection#addAll(Collection)
     * @see Collection#removeAll(Collection)
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> ArrayList<E> differ(Collection<E> collectionA, Collection<E> collectionB) {
        ArrayList<E> newList = new ArrayList<E>();
        newList.addAll(collectionA);
        newList.removeAll(collectionB);
        return newList;
    }

    /**
     * 排除两个数组共同的元素，并得出两个数组的对称差集。等同于对称差集（(A－B)∪(B－A)）
     * <br/>
     * 给定的元素对象必须实现{@link Comparable}接口规范，即重写{@link Object#equals(Object)}和{@link Object#hashCode()}方法
     *
     * @param collectionA 集合A
     * @param collectionB 集合B
     * @param <E>         集合元素类型
     * @return 两个集合共同元素以外的元素组成的新集合
     * @since 1.0
     */
    public static <E extends Comparable<? super E>> ArrayList<E> unCross(Collection<E> collectionA, Collection<E> collectionB) {
        ArrayList<E> list1 = differ(collectionA, collectionB);
        ArrayList<E> list2 = differ(collectionB, collectionA);
        ArrayList<E> newList = join(list1, list2);
        return newList;
    }

    /*----------------------------- unite calc end ----------------------------------------*/


    /*----------------------------- list extract start ----------------------------------------*/

    /**
     * 针对一个{@link List}集合列表进行截取，并返回一个新的子列表对象
     *
     * @param list  原{@link List}集合列表
     * @param index 截取的开始位置和元素个数（正数表示正向计算并截取，负数表示逆向计算并截取)
     * @param <E>   元素类型
     * @return 子集合列表
     * @see IndexRangeAlgorithm
     * @since 1.0
     */
    public static <E> List<E> subList(List<E> list, int index) {
        if (list == null) {
            return null;
        }
        int[] range = IndexRangeAlgorithm.indexRange(list.size(), index);
        return list.subList(range[0], range[1]);
    }

    /**
     * 针对一个{@link List}集合列表进行截取，并返回一个新的子列表对象
     *
     * @param list  原{@link List}集合列表
     * @param index 截取的开始位置和元素个数（正数表示正向计算，负数表示逆向计算)
     * @param count 截取的方向和元素个数（整数表示正向截取，负数表示逆向截取）
     * @param <E>   元素类型
     * @return 子集合列表
     * @see IndexRangeAlgorithm
     * @since 1.0
     */
    public static <E> List<E> subList(List<E> list, int index, int count) {
        if (list == null) {
            return null;
        }
        int[] range = IndexRangeAlgorithm.indexRange(list.size(), index, count);
        return list.subList(range[0], range[1]);
    }

    /**
     * 针对一个{@link List}集合列表进行截取，并返回一个新的子列表对象；将所有索引进行重新排序并去重，然后提取指定位置的元素组成新列表
     *
     * @param list  原{@link List}集合列表
     * @param index 索引位置列表，超出范围或负值被剔除
     * @param <E>   元素类型
     * @return 子集合列表
     * @see IndexRangeAlgorithm
     * @since 1.0
     */
    public static <E> List<E> pickList(List<E> list, int... index) {
        if (list == null) {
            return null;
        }
        if (ArrayUtils.isEmpty(index)) {
            return list;
        }
        int size = list.size();
        List<E> subList = new ArrayList<>();
        int[] range = IndexRangeAlgorithm.indexRange(size, index);
        for (int i : range) {
            subList.add(list.get(i));
        }
        return subList;
    }

    /**
     * 针对一个{@link List}集合列表进行元素提取，并组成一个新的子列表对象
     *
     * @param list  原{@link List}集合列表
     * @param index 提取的索引位置，超过范围或负整数会被忽略
     * @param <E>   元素类型
     * @return 子集合列表
     * @since 1.0
     */
    public static <E> List<E> ofList(List<E> list, int... index) {
        if (list == null) {
            return null;
        }
        if (ArrayUtils.isEmpty(index)) {
            return list;
        }
        List<E> arrayList = new ArrayList<>();
        for (int i : index) {
            if (i >= 0 && i < list.size()) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    /*----------------------------- list extract end ----------------------------------------*/

    /*----------------------------- list page start ----------------------------------------*/

    /**
     * 将一个{@link List}列表对象转换为可分页的列表页面对象
     *
     * @param list     源集合列表对象
     * @param pageSize 每页大小
     * @param <E>      元素对象列表
     * @return 可分页的列表页面对象
     * @see ListPage
     */
    public static <E> ListPage<E> toListPage(List<E> list, int pageSize) {
        return new ListPage<>(list, pageSize);
    }

    /*----------------------------- list page end ----------------------------------------*/

}
