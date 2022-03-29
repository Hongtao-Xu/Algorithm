package com.cn.advance.Stage2.BSearch;

/*
 * 有序数组的：
 * 二分查找，非递归实现
 */
public class BinarySearchNR {
    private BinarySearchNR() {
    }

    // 非递归实现二分查找法
    public static <E extends Comparable<E>> int search(E[] arr, E target) {
        int l = 0, r = arr.length-1;
        // 循环不变量：在 data[l, r] 的范围中查找 target
        while (l<=r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target)==0) return mid;
            if (arr[mid].compareTo(target)<0)
                l=mid+1;
            else
                r=mid-1;
        }
        return -1;
    }

}
