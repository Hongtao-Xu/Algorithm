package com.cn.LeetCode;

/*
 * 33. 搜索旋转排序数组
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class LC33 {
    //1.二分搜索
    class Solution {
        public int search(int[] nums, int target) {
            int len = nums.length;
            if (len == 0) return -1;
            int left = 0, right = len - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) return mid;
                //右边有序
                if (nums[mid] < nums[right]) {
                    //目标值在右边
                    if (target > nums[mid] && target <= nums[right]) {
                        left = mid + 1;
                    } else {//目标值在左边
                        right = mid - 1;
                    }
                } else { //左边有序
                    //目标值在左边
                    if (target < nums[mid] && target >= nums[left]) {
                        right = mid - 1;
                    } else { //目标值在右边
                        left = mid + 1;
                    }
                }
            }
            return -1;
        }
    }
}
