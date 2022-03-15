package com.cn.advance.Stage2.MergeSortPlus;

import com.cn.advance.Stage2.MergeSort.MergeSort;
import com.cn.advance.utils.ArrayGenerator;
import com.cn.advance.utils.SortingHelper;

import java.util.Arrays;

/*
 * 归并排序-优化1
 * 如果arr[mid]小于arr[mid+1]，就不必merge了
 */
public class MergeSort2 {
    private MergeSort2() {
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
        //优化
        if(arr[mid].compareTo(arr[mid+1])>0)//如果 arr[mid]小于arr[mid+1]，就不必merge了
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
        int n = 1000000;

        System.out.println("Random Array:");
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest(MergeSort.class, arr);
        SortingHelper.sortTest(MergeSort2.class, arr2);

        System.out.println("Ordered Array:");
        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest(MergeSort.class, arr);
        SortingHelper.sortTest(MergeSort2.class, arr2);

    }
}