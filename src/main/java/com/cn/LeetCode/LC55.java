package com.cn.LeetCode;

/*
 *55. 跳跃游戏
 *https://leetcode-cn.com/problems/jump-game/
 */
public class LC55 {
    //贪心
    class Solution {
        public boolean canJump(int[] nums) {
            int n = nums.length;
            int rightFurthest=0;
            for(int i=0;i<n;i++){
                if(i<=rightFurthest){
                    rightFurthest = Math.max(rightFurthest,i+nums[i]);
                    if(rightFurthest>=n-1){
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
