package com.cn.LeetCode;

/*
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class LC34 {
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int leftIndex = binarySearch(nums, target, true);//不等于
            int rightIndex = binarySearch(nums, target, false) - 1;//等于
            if (leftIndex <= rightIndex &&
                    rightIndex < nums.length &&
                    nums[leftIndex] == target &&
                    nums[rightIndex] == target) {
                return new int[]{leftIndex, rightIndex};
            }
            return new int[]{-1, -1};
        }

        private int binarySearch(int[] nums, int target, boolean lower) {
            int left = 0, right = nums.length - 1, ans = nums.length;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                /**
                 * lower: false时，nums[mid] > target
                 * lower: true时，nums[mid] >= target
                 */
                if (nums[mid] > target || (lower && nums[mid] >= target)) {
                    right = mid - 1;
                    ans=mid;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }

    }
}
