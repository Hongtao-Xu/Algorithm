package com.cn.LeetCode;

/*
 * 31. 下一个排列
 * https://leetcode-cn.com/problems/next-permutation/
 *
 */
public class LC31 {
    class Solution {
        public void nextPermutation(int[] nums) {
            int len = nums.length-1;//len指向最后一个元素
            int i = len - 1;
            int j = len;
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }
            if (i >= 0) {
                while (j >= 0 && nums[i] >= nums[j]) {
                    j--;
                }
                swap(nums, i, j);
            }
            reverse(nums, i + 1, len);
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        private void reverse(int[] nums, int l, int r) {
            while (l < r) {
                swap(nums, l, r);
                l++;
                r--;
            }
        }
    }

}
