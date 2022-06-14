package com.cn.LeetCode;

import org.junit.Test;

/*
 * 53. 最大子数组和
 * https://leetcode-cn.com/problems/maximum-subarray/
 *
 */
public class LC53 {

    //1.贪心
    class Solution {
        public int maxSubArray(int[] nums) {
            int pre = 0, maxAns = nums[0];
            for (int x : nums) {
                //找原数组的最大值
                pre = Math.max(pre + x, x);
                maxAns = Math.max(maxAns, pre);
            }
            return maxAns;
        }
    }

    //动态规划
    static class Solution2 {

        public int maxSubArray(int[] nums) {
            int n = nums.length;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i - 1] > 0) nums[i] += nums[i - 1];
            }
            int max = nums[0];
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > max) max = nums[i];
            }
            return max;
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,4,-1,7,8};
        Solution3 solution3 = new Solution3();
        solution3.maxSubArray(nums);
    }


    //2.线段树
    static class Solution3 {
        public class Status {
            public int lSum, rSum, mSum, iSum;

            public Status(int lSum, int rSum, int mSum, int iSum) {
                this.lSum = lSum;
                this.rSum = rSum;
                this.mSum = mSum;
                this.iSum = iSum;
            }
        }

        public int maxSubArray(int[] nums) {
            return getInfo(nums, 0, nums.length - 1).mSum;
        }

        public Status getInfo(int[] a, int l, int r) {
            if (l == r) return new Status(a[l], a[r], a[l], a[r]);

            int mid = l + (r - l) / 2;
            Status lSub = getInfo(a, l, mid);
            Status rSub = getInfo(a, mid + 1, r);
            return pushUp(lSub, rSub);
        }

        //相当于merge
        public Status pushUp(Status l, Status r) {
            int iSum = l.iSum + r.iSum;
            int lSum = Math.max(l.lSum, r.lSum + l.iSum);
            int rSum = Math.max(r.rSum, l.rSum + r.iSum);
            int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
            return new Status(lSum, rSum, mSum, iSum);
        }
    }

    //贪心
    class Solution4 {

        public int maxSubArray(int[] nums) {
            int cur_sum, max_sum;
            cur_sum = nums[0];
            max_sum = nums[0];
            for (int i = 1; i < nums.length; i++) {
                //当前和=max(当前值，当前值+之前和)
                cur_sum = Math.max(nums[i], cur_sum + nums[i]);
                //最大和 = max(当前和，最大和)
                max_sum = Math.max(cur_sum, max_sum);
            }
            return max_sum;
        }
    }
}
