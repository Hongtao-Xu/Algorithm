package com.cn.advance.Stage2.QuickSortPlus.hw.SelectK;

import java.util.Random;

/*
 * LeetCode-215
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * -思路：
 * 双路排序
 */
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        Random rnd = new Random();
        return selectK(nums, 0, nums.length - 1, nums.length - k, rnd);
    }

    private int selectK(int[] arr, int l, int r, int k, Random rnd) {
        int p = partition(arr, l, r, rnd);
        //此处不必全部排序
        if (k == p) return arr[p];

        if (k < p){
            return selectK(arr, l, p - 1, k, rnd);
        }else {
            return selectK(arr, p + 1, r, k, rnd);
        }
    }

    //完成一次partition之后，找到p完全排序好后应该在的位置
    private int partition(int[] arr, int l, int r, Random rnd) {
        // 生成 [l, r] 之间的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);

        // arr[l+1...i-1] <= v; arr[j+1...r] >= v
        int i = l + 1, j = r;
        while(true){
            while(i <= j && arr[i] < arr[l])
                i ++;
            while(j >= i && arr[j] > arr[l])
                j --;
            if(i >= j) break;
            swap(arr, i, j);
            i ++;
            j --;
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
