package com.cn.advance.Stage2.QuickSort;

import com.cn.advance.Stage1.SortBase.InsertionSort.InsertionSort;
import com.cn.advance.utils.ArrayGenerator;
import com.cn.advance.utils.SortingHelper;

import java.util.Arrays;

/*
 * 使用待处理数组区间的"中间值"作为标定点
 */
public class QuickSortSpecial {
    private QuickSortSpecial() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }


    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) return;

        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        // 永远使用中间值作为标定点
        swap(arr, l, (l + r) / 2);

        // arr[l+1...j] < v ; arr[j+1...i] >= v
        int j = l;
        for (int i = l + 1; i <= r; i++) {//arr[j+1...i] >= v时，直接i++，不用交换
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) throws Exception {

        int n = 5000;

        Integer[] arr = ArrayGenerator.generateSpecialArray(n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);

        System.out.println("Special Array");
        SortingHelper.sortTest(QuickSortSpecial.class, arr);
        SortingHelper.sortTest(QuickSort3.class, arr2);
        System.out.println();
    }
}
