package com.cn.CS.ThreeSum;

import java.util.Arrays;

/*
将数组进行排序，对两个元素求和，
并用二分查找方法查找是否存在该和的相反数，如果存在，就说明存在和为 0 的三元组。
只有数组不含有相同元素才能使用这种解法，否则二分查找的结果会出错。
-  O(N2logN)
 */
public class ThreeSumBinarySearch implements ThreeSum {

    @Override
    public int count(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int target = -nums[i]-nums[j];
                int index = BinarySearch(nums, target);
                // 应该注意这里的下标必须大于 j，否则会重复统计。
                if (index>j) cnt++;
            }
        }
        return cnt;
    }

    //二分查找
    private static int BinarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (target > nums[mid])
                l = mid + 1;
            else if (target < nums[mid])
                r = mid - 1;
            else
                return mid;
        }
        return -1;
    }
}
