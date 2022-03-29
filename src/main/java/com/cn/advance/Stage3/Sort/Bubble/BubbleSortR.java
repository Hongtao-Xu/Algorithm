package com.cn.advance.Stage3.Sort.Bubble;

import com.cn.advance.utils.ArrayGenerator;
import com.cn.advance.utils.SortingHelper;

import java.util.Arrays;

/*
 * 冒泡排序
 * 另一种实现思路：
 * - 从尾到头比较
 * - 最小元素放到数组头部
 */
public class BubbleSortR {
    private BubbleSortR() {
    }

    public static <E extends Comparable<E>> void sort3(E[] data) {
        for (int i = 0; i + 1 < data.length; i++) {
            // arr[0, i) 已排好序
            // 通过冒泡在 arr[i] 位置放上合适的元素
            for (int j = data.length - 1; j > i; j--)//j-1可以指向i
                if (data[j - 1].compareTo(data[j]) > 0)
                    swap(data, j - 1, j);
        }
    }
    public static <E extends Comparable<E>> void sort2(E[] data){
        for (int i = 0; i + 1 < data.length; i++) {
            // arr[0, i) 已排好序
            // 通过冒泡在 arr[i] 位置放上合适的元素
            boolean isSwapped = false;//如果一次都没交换过，那么数组本来就是有序的
            for(int j = data.length - 1; j > i; j --)
                if(data[j - 1].compareTo(data[j]) > 0){
                    swap(data, j - 1, j);
                    isSwapped = true;
                }

            if(!isSwapped) break;
        }
    }
    public static <E extends Comparable<E>> void sort(E[] data){
        for(int i = 0; i + 1 < data.length; ){
            // arr[0, i) 已排好序
            // 通过冒泡在 arr[i] 位置放上合适的元素
            int lastSwappedIndex = data.length - 1;//与j的初始位置相同
            for(int j = data.length - 1; j > i; j --)
                if(data[j - 1].compareTo(data[j]) > 0){
                    swap(data, j - 1, j);
                    lastSwappedIndex = j - 1;//最后交换的元素位置，有序数组的边际
                }

            i = lastSwappedIndex + 1;//[0,lastSwappedIndex)都已排好序
        }
    }


    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) throws Exception {
        int n = 50000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        System.out.println("Random Array");
        SortingHelper.sortTest(BubbleSortR.class, arr);
        System.out.println();

        arr = ArrayGenerator.generateOrderedArray(n);
        System.out.println("Ordered Array");
        SortingHelper.sortTest(BubbleSortR.class, arr);
    }
}
