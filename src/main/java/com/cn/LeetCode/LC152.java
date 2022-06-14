package com.cn.LeetCode;

/*
 * 152. 乘积最大子数组
 * https://leetcode.cn/problems/maximum-product-subarray/
 */
public class LC152 {
    //动态规划
    class Solution {
        public int maxProduct(int[] nums) {
            int max = Integer.MIN_VALUE, imin = 1, imax = 1;
            for (int i = 0; i < nums.length; i++) {
                //如果出现负数，交换imin和imax
                if (nums[i] < 0) {
                    int temp = imin;
                    imin = imax;
                    imax = temp;
                }
                imax = Math.max(imax * nums[i], nums[i]);
                imin = Math.min(imin * nums[i], nums[i]);
                max = Math.max(max, imax);
            }
            return max;
        }
    }
}
