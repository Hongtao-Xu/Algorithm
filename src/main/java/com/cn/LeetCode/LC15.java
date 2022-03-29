package com.cn.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 15. 三数之和
 * https://leetcode-cn.com/problems/3sum/
 */
public class LC15 {

    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> lists = new ArrayList<>();
            //排序
            Arrays.sort(nums);
            //特判
            if (nums.length < 3) return lists;
            //双指针
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) return lists;
                //重复元素
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int cur = nums[i];
                int L = i + 1, R = nums.length - 1;
                while (L < R) {
                    int temp = cur + nums[L] + nums[R];
                    if (temp==0) {
                        lists.add(Arrays.asList(cur, nums[L], nums[R]));
                        /**
                         * 注意这里的逻辑是：
                         * x+y+z=0;
                         * x是cur,如果y与下一个y相同，那么，z也一定与上一个z相同
                         */
                        while (L<R&&nums[L+1]==nums[L])L++;
                        while (L<R&&nums[R-1]==nums[R])R--;
                        L++;R--;
                    } else if (temp<0) {
                        L++;
                    }else {
                        R--;
                    }
                }
            }
            return lists;
        }
    }
}
