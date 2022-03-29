package com.cn.advance.Stage3.Sort.Bubble;

import com.cn.advance.utils.ArrayGenerator;
import com.cn.advance.utils.SortingHelper;

import java.util.Arrays;

/*
 * 冒泡排序
 * 优化：
 * - 对于已经有一定排好序的，直接跳过排好的
 */
public class BubbleSort3 {
    private BubbleSort3() {
    }

    public static <E extends Comparable<E>> void sort(E[] data) {
        for (int i = 0; i + 1 < data.length; ) {//i：arr[n-i,n)已经排好序
            //arr[n-i,n)已经排好序
            //通过冒泡，在arr[n-i-1]放上合适的元素
            int lastSwappedIndex = 0;
            for (int j = 0; j + 1 <= data.length - i - 1; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(data, j, j + 1);
                    lastSwappedIndex = j + 1;//最后交换的元素位置，有序数组的边际
                }
            }
            i = data.length - lastSwappedIndex;
            //j+1<=[data.length-i-1]=[lastSwappedIndex-1]
            //因此，for循环比较[0,lastSwappedIndex-1]
        }
        //arr[n-i-1,n)已经排好序
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) throws Exception {
        int n = 50000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);

        System.out.println("Random Array");
        SortingHelper.sortTest(BubbleSort.class, arr);
        SortingHelper.sortTest(BubbleSort2.class, arr2);
        SortingHelper.sortTest(BubbleSort3.class, arr3);
        System.out.println();


        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);

        System.out.println("Ordered Array");
        SortingHelper.sortTest(BubbleSort.class, arr);
        SortingHelper.sortTest(BubbleSort2.class, arr2);
        SortingHelper.sortTest(BubbleSort3.class, arr3);
    }
}
