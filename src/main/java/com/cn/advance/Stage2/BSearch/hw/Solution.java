package com.cn.advance.Stage2.BSearch.hw;

/*
 *https://leetcode-cn.com/problems/binary-search/
 *704. 二分查找
 */
//递归实现
public class Solution {
    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    public int search(int[] data, int l, int r, int target) {
        if (l > r) return -1;

        int mid = l + (r - l) / 2;
        if (data[mid] == target)
            return mid;
        if (data[mid] < target)
            return search(data, mid + 1, r, target);
        return search(data, l, mid - 1, target);
    }
}
//非递归实现
class Solution2{
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        // 循环不变量：在 data[l, r] 的范围中查找 target
        while (l<=r) {
            int mid = l + (r - l) / 2;
            if (nums[mid]==target) return mid;
            if (nums[mid]<target)
                l=mid+1;
            else
                r=mid-1;
        }
        return -1;
    }
}
