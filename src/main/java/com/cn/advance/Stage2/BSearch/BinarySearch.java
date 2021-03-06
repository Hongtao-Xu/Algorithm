package com.cn.advance.Stage2.BSearch;

/*
 * 有序数组的：
 * 二分查找,递归实现
 */
public class BinarySearch {
    private BinarySearch(){}

    public static <E extends Comparable<E>> int search(E[] data, E target) {
        return search(data, 0, data.length-1,  target);
    }

    public static <E extends Comparable<E>> int search(E[] data, int l,int r,E target) {
        if (l>r)return -1;

        int mid = l + (r - l) / 2;
        if (data[mid].compareTo(target)==0)
            return mid;
        if (data[mid].compareTo(target)<0)
            return search(data, mid + 1, r, target);
        return search(data, l, mid - 1, target);
    }
}
