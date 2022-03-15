package com.cn.advance.Stage2.QuickSortPlus.hw.SelectK;

import java.util.Arrays;
import java.util.Random;

/*
 * 剑指 Offer 40——最小的k个数
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * 思路：双路排序
 * 把所有的元素排好序，结果就是 Arrays.copyOf(arr, k)
 */
public class Solution2 {
    public int[] getLeastNumbers(int[] arr, int k) {
        //此处判断是因为：Random.nextInt的参数必须为正数
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        Random rnd = new Random();
        //注意：直接通过快排切分排好第 K 小的数（下标为 K-1）
        selectK(arr, 0, arr.length - 1, k -1, rnd);
        return Arrays.copyOf(arr, k);
    }

    private void selectK(int[] arr, int l, int r, int k, Random rnd) {
        int p = partition(arr, l, r, rnd);

        if (k == p) return;
        if (k < p) {
            selectK(arr, l, p - 1, k, rnd);
        } else {
            selectK(arr, p + 1, r, k, rnd);
        }
    }

    //完成一次partition之后，找到p完全排序好后应该在的位置
    //返回值：p前一共有多少个元素
    private int partition(int[] arr, int l, int r, Random rnd) {
        // 生成 [l, r] 之间的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);

        // arr[l+1...i-1] <= v; arr[j+1...r] >= v
        int i = l + 1, j = r;
        while (true) {
            while (i <= j && arr[i] < arr[l])
                i++;
            while (j >= i && arr[j] > arr[l])
                j--;
            if (i >= j) break;
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
