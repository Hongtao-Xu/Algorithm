package com.cn.advance.Stage2.MergeSortPlus;


import com.cn.advance.utils.ArrayGenerator;
import com.cn.advance.utils.SortingHelper;

import java.util.Arrays;
/*
 * 归并排序-自底向上
 * -注意
 * 由于jvm，编程语言等的原因，并不一定比自顶向下的归并排序快
 */
public class MergeSortBU {
    private MergeSortBU() {
    }

    /**
     * 提供给用户接口
     * @param arr 用户传入要排序的数组
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        int n = arr.length;
        E[] temp = Arrays.copyOf(arr, n);
        // sz:合并的区间长度
        for (int sz = 1; sz < n; sz+=sz) {
            // 合并的两个区间的起始位置:
            // 合并 [i, i + sz - 1] 和 [i + sz, Math.min(i + sz + sz - 1, n - 1)]
            for (int i = 0; i+sz<n; i+=sz+sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz])>0)
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1), temp);
            }
        }
    }

    // 合并两个有序的区间 arr[l, mid] 和 arr[mid + 1, r]
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {

        //由于完成merge所需要的temp辅助空间的大小和arr一样,
        // 所以我们也不需要处理temp索引的偏移量
        System.arraycopy(arr, l, temp, l, (r - l + 1));

        int i = l, j = mid + 1;

        // 每轮循环为 arr[k] 赋值
        for (int k = l; k <= r; k++) {
            //i往右，越界
            if (i > mid) {
                arr[k] = temp[j];
                j++;
            }
            //j往右，越界
            else if (j > r) {
                arr[k] = temp[i];
                i++;
            }
            //非越界,正常比较
            else if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int n = 5000000;

        System.out.println("Random Array:");
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest(MergeSort4.class, arr);
        SortingHelper.sortTest(MergeSortBU.class, arr2);
    }
}
