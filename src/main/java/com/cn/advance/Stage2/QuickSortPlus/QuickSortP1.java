package com.cn.advance.Stage2.QuickSortPlus;

import com.cn.advance.Stage1.SortBase.InsertionSort.InsertionSort;
import com.cn.advance.Stage2.MergeSort.MergeSort;
import com.cn.advance.Stage2.QuickSort.QuickSort3;
import com.cn.advance.utils.ArrayGenerator;
import com.cn.advance.utils.SortingHelper;

import java.util.Arrays;
import java.util.Random;

/*
 * 快速排序优化-双路排序
 */
public class QuickSortP1 {

    private QuickSortP1() {
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
        int p = partition(arr, l, r, rnd);
        sort(arr, l, p - 1, rnd);
        sort(arr, p + 1, r, rnd);
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r, Random rnd) {
        //生成在[l,r]的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);
        // 循环不变量
        // arr[l+1...i-1] <= v ; arr[j+1...r] >= v
        int i = l+1,j=r;
        while (true) {
            while (i<=j&&arr[i].compareTo(arr[l])<=0)
                i++;
            while (j>=i&&arr[j].compareTo(arr[l])>=0)
                j--;
            if (i>=j) break;

            swap(arr, i, j);
            i++;j--;
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

        int n = 10000;

        System.out.println("Random Array:");
        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr11 = Arrays.copyOf(arr1, arr1.length);
        SortingHelper.sortTest(QuickSort3.class, arr1);
        SortingHelper.sortTest(QuickSortP1.class, arr11);

        System.out.println("Ordered Array:");
        Integer[] arr2 = ArrayGenerator.generateOrderedArray(n);
        Integer[] arr22 = Arrays.copyOf(arr2, arr2.length);
        SortingHelper.sortTest(QuickSort3.class, arr2);
        SortingHelper.sortTest(QuickSortP1.class, arr22);

        System.out.println("Same Array:");
        Integer[] arr4 = ArrayGenerator.generateRandomArray(n, 1);
        //arr全都是0
        Integer[] arr44 = Arrays.copyOf(arr4, arr4.length);
        //退化了为：O(n^2)
        SortingHelper.sortTest(QuickSort3.class, arr4);
        SortingHelper.sortTest(MergeSort.class, arr44);

    }
}
