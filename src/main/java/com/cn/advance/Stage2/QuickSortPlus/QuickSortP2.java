package com.cn.advance.Stage2.QuickSortPlus;

import com.cn.advance.Stage1.SortBase.InsertionSort.InsertionSort;
import com.cn.advance.Stage2.MergeSort.MergeSort;
import com.cn.advance.Stage2.QuickSort.QuickSort3;
import com.cn.advance.utils.ArrayGenerator;
import com.cn.advance.utils.SortingHelper;

import java.util.Arrays;
import java.util.Random;

/*
 * 快速排序优化-三路排序
 */
public class QuickSortP2 {

    private QuickSortP2() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        Random rnd = new Random();
        sort(arr, 0, arr.length - 1, rnd);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, Random rnd) {
        // 使用 Insertion Sort 优化

        if (r - l <= 7) {
            InsertionSort.sort(arr, l, r);
            return;
        }
        /** 三路快速排序的 partition 过程 **/
        // 随机化：生成 [l, r] 之间的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);

        //arr[l+1,lt]<v; arr[lt+1,i-1]==v; arr[gt,r]>v;
        int lt = l, i = l + 1, gt = r + 1;
        while (i < gt) {
            if (arr[i].compareTo(arr[l]) < 0) {//加入左边区域
                lt++;
                swap(arr, i, lt);//lt之前维护的元素都经过比较了
                i++;
            } else if (arr[i].compareTo(arr[l]) > 0) {
                gt--;
                swap(arr, i, gt);//gt之前维护的元素没有经过比较
            } else {// arr[i] == v
                i++;
            }
        }
        //arr[l]与arr[lt]交换位置
        //arr[l,lt-1]<v; arr[lt,i-1]==v; arr[gt,r]>v;
        swap(arr, l, lt);
        /** 三路快速排序的 partition 过程结束 **/
        // 递归调用
        sort(arr, l, lt - 1, rnd);
        sort(arr, gt, r, rnd);
    }


    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) throws Exception {

        int n = 10000;

        System.out.println("Random Array:");
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest(QuickSort3.class, arr);
        SortingHelper.sortTest(QuickSortP1.class, arr2);
        SortingHelper.sortTest(QuickSortP2.class, arr3);

        System.out.println("Ordered Array:");
        arr = ArrayGenerator.generateRandomArray(n, n);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest(QuickSort3.class, arr);
        SortingHelper.sortTest(QuickSortP1.class, arr2);
        SortingHelper.sortTest(QuickSortP2.class, arr3);

        System.out.println("Same Array:");
        arr = ArrayGenerator.generateRandomArray(n, n);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest(QuickSort3.class, arr);
        SortingHelper.sortTest(QuickSortP1.class, arr2);
        SortingHelper.sortTest(QuickSortP2.class, arr3);
    }
}
