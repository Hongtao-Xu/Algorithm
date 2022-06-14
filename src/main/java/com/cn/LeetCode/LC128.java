package com.cn.LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 *128. 最长连续序列
 *https://leetcode.cn/problems/longest-consecutive-sequence/
 */
public class LC128 {
    //1.HashSet
    class Solution {
        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<Integer>();
            for (int n : nums) {
                set.add(n);
            }
            int longsetStreak = 0;
            for (int temp : set) {
                //直接跳过含有下一个的大数
                if (!set.contains(temp - 1)) {
                    int currentNum = temp;
                    int currentStreak = 1;

                    while (set.contains(currentNum + 1)) {
                        currentNum += 1;
                        currentStreak += 1;
                    }
                    longsetStreak = Math.max(longsetStreak, currentStreak);
                }
            }
            return longsetStreak;
        }
    }
}
