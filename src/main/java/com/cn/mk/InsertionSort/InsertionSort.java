package com.cn.mk.InsertionSort;

import com.cn.mk.SelectionSort.SelectionSort;
import com.cn.mk.utils.ArrayGenerator;
import com.cn.mk.utils.SortingHelper;
import com.cn.mk.utils.util;

import java.util.Arrays;

/*
 *@program:插入排序
 *@author: xu-hongtao
 *@Time: 2021/9/9  20:00
 */
public class InsertionSort {
    private InsertionSort() {
    }

    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
//            for (int j = i; j - 1 >= 0; j--) {
//                if (arr[j].compareTo(arr[j - 1]) < 0)
//                    util.swap(arr, j, j - 1);
//                else break;
            for (int j = i; j - 1 >= 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                util.swap(arr, j, j - 1);
            }
        }
    }

    //优化：
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        for (int i = 0; i < arr.length; i++) {

            E t = arr[i];
            int j;//j是为了寻找到t应该在的位置
            for (j = i; j - 1 >= 0 && t.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = t;
        }
    }

    //另一种方式的实现：
    /*
     * arr[i..n)已排序，arr[0...i)未排序
     * */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            E t = arr[i];
            int j;//j是为了寻找到t应该在的位置
            for (j = i; j + 1 < arr.length && t.compareTo(arr[j + 1]) > 0; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = t;
        }
    }

    public static void main(String[] args) throws Exception {
        int[] dataSize = {10000, 100000};
        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest(InsertionSort.class, arr);
        }
    }

    public static void main2(String[] args) throws Exception {
        //==========测试选择排序与插入排序==========
        int[] dataSize = {10000, 100000};
        for (int n : dataSize) {
            System.out.println("Random Array:");
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arr2 = Arrays.copyOf(arr, arr.length);
            SortingHelper.sortTest(InsertionSort.class, arr);
            SortingHelper.sortTest(SelectionSort.class, arr2);
            System.out.println();

            System.out.println("Ordered Array:");
            Integer[] arr3 = ArrayGenerator.generateOrderedArray(n);
            Integer[] arr4 = Arrays.copyOf(arr3, arr.length);
            SortingHelper.sortTest(InsertionSort.class, arr3);
            SortingHelper.sortTest(SelectionSort.class, arr4);
            System.out.println();
        }
    }
}
