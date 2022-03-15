package com.cn.advance.Stage2.MergeSort;

import com.cn.advance.Stage1.SortBase.InsertionSort.InsertionSort;
import com.cn.advance.Stage1.SortBase.SelectionSort.SelectionSort;
import com.cn.advance.utils.ArrayGenerator;
import com.cn.advance.utils.SortingHelper;

import java.util.Arrays;

/*
 * 归并排序
 */
public class MergeSort {
    private MergeSort() {
    }

    /**
     * 提供给用户接口
     *
     * @param arr 用户传入要排序的数组
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        innerSort(arr, 0, arr.length - 1);
    }

    //主排序(递归调用)
    private static <E extends Comparable<E>> void innerSort(E[] arr, int l, int r) {
        //最终条件:数组中只有0或1个元素，不用排序，直接返回
        if (l >= r) return;

        //int mid= (l+r)/2;
        //进阶版：不会出现数字越界，l+r超过int所能表达的最大数字
        int mid = l + (r - l) / 2;

        innerSort(arr,l,mid);
        innerSort(arr,mid + 1, r);
        merge(arr, l, mid, r);
    }

    // 合并两个有序的区间 arr[l, mid] 和 arr[mid + 1, r]
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {

        //复制一份，[...)
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);

        int i = l, j = mid + 1;

        // 每轮循环为 arr[k] 赋值
        for (int k = l; k <= r; k++) {
            //i往右，越界
            if (i > mid) {
                arr[k] = temp[j - l];j++;
            }
            //j往右，越界
            else if (j > r) {
                arr[k] = temp[i - l];i++;
            }
            //非越界,正常比较
            else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i - l];i++;
            }
            else {
                arr[k] = temp[j - l];j++;
            }

        }
    }

    public static void main(String[] args) throws Exception {
        int n = 100000;
        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest(MergeSort.class, arr1);
        SortingHelper.sortTest(SelectionSort.class, arr2);
        SortingHelper.sortTest(InsertionSort.class, arr3);
    }

}
