package com.cn.LeetCode;

/*
 *198. 打家劫舍
 *https://leetcode.cn/problems/house-robber/
 */
public class LC198 {

    //1.动态规划
    class Solution {
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;
            int len = nums.length;
            if (len == 1)
                return nums[0];
            int[] dp = new int[len];
            //边界条件
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < len; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[len - 1];
        }
    }

    //2.滚动数组
    class Solution1 {
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;
            int len = nums.length;
            if (len == 1)
                return nums[0];
            //边界条件
            int first = nums[0];
            int second = Math.max(nums[0], nums[1]);
            //滚动数组
            for (int i = 2; i < len; i++) {
                int temp = second;
                second = Math.max(first + nums[i], second);
                first = temp;
            }
            return second;
        }
    }


}
