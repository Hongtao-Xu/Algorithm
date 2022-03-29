package com.cn.advance.Stage2.BSearch.hw;

import java.util.Random;

/*
 * Select-k问题
 * 非递归实现，二分法思路
 *
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * 215. 数组中的第K个最大元素
 */
public class SelectKBS {
    public int findKthLargest(int[] nums, int k) {
        Random rnd = new Random();
        //第K小个元素
        return selectK(nums, nums.length - k, rnd);
    }

    //数组中的第K个最大元素
    private int selectK(int[] arr, int k, Random rnd) {
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int p = partition(arr, l, r, rnd);
            if (p == k) return arr[p];
            if (k < p) r = p - 1;
            else l = p + 1;
        }
        return -1;
    }

    //1.生成 [l, r] 之间的随机索引p
    //2.找到p在排好序后的数组中的位置
    //3.返回这个位置 j (第k大的数字)
    private int partition(int[] arr, int l, int r, Random rnd) {
        // 生成 [l, r] 之间的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);
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
