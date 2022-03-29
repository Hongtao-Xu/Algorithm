package com.cn.advance.Stage2.BSearch;

/*
 * BinarySearch();
 * 修改循环不变量[l,r)
 * 测试：
 * https://leetcode-cn.com/problems/binary-search/
 */
public class BinarySearch2 {
    private BinarySearch2() {
    }

    public static <E extends Comparable<E>> int search(E[] data, E target) {
        return search(data, 0, data.length, target);
    }

    // 在 data[l, r) 的范围中查找 target
    public static <E extends Comparable<E>> int search(E[] data, int l, int r, E target) {
        if (l >= r) return -1;

        int mid = l + (r - l) / 2;
        if (data[mid].compareTo(target) == 0)
            return mid;
        if (data[mid].compareTo(target) < 0)
            return search(data, mid + 1, r, target);// 继续在 data[mid + 1, r) 范围里寻找解
        return search(data, l, mid, target);// 继续在 data[l, mid) 范围里寻找解
    }
}
