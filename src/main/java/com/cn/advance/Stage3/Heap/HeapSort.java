package com.cn.advance.Stage3.Heap;

import com.cn.advance.Stage2.MergeSortPlus.MergeSort4;
import com.cn.advance.Stage2.QuickSortPlus.QuickSortP1;
import com.cn.advance.Stage2.QuickSortPlus.QuickSortP2;
import com.cn.advance.utils.ArrayGenerator;
import com.cn.advance.utils.SortingHelper;

import java.util.Arrays;

/*
 * 堆排序
 */
public class HeapSort {
    private HeapSort() {
    }

    //原地排序
    public static <E extends Comparable<E>> void sort(E[] data) {
        if (data.length <= 1) return;
        //parent:((data.length-1) - 1) / 2
        for (int i = (data.length - 2) / 2; i >= 0; i--)//heapify(),转为最大堆
            siftDown(data, i, data.length);
        //把最大的数字max,从数组头，换到尾，然后siftDown
        for (int i = data.length - 1; i >= 0; i--) {
            swap(data, 0, i);
            siftDown(data, 0, i);//把数组头元素下沉
        }
    }

    //对 data[0, n) 所形成的最大堆中，索引 k 的元素，执行 siftDown
    private static <E extends Comparable<E>> void siftDown(E[] data, int k, int n) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1; // 在此轮循环中,data[k]和data[j]交换位置
            //j:左孩子；j+1右孩子
            if (j + 1 < n && data[j + 1].compareTo(data[j]) > 0)
                j++;//j变成了指向大的元素
            // data[j] 是 leftChild 和 rightChild 中的最大值
            if (data[k].compareTo(data[j]) >= 0)
                break;//此时k已经比左右孩子都大，不用操作了
            //k，比大孩子小
            swap(data, k, j);
            k = j;//k指向新的节点
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    //非原地排序
    public static <E extends Comparable<E>> void sort2(E[] data) {
        MaxHeap<E> maxHeap = new MaxHeap<>();
        for (E e : data)
            maxHeap.add(e);
        for (int i = data.length - 1; i >= 0; i--)
            data[i] = maxHeap.extractMax();
    }


    public static void main(String[] args) throws Exception {
        int n = 1000000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);


        SortingHelper.sortTest(MergeSort4.class, arr);
        SortingHelper.sortTest(QuickSortP1.class, arr2);
        SortingHelper.sortTest(QuickSortP2.class, arr3);
        SortingHelper.sortTest(HeapSort.class, arr4);
    }
}
