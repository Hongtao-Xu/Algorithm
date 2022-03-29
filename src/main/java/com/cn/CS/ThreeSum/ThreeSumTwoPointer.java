package com.cn.CS.ThreeSum;

import java.util.Arrays;

/*
更有效的方法是先将数组排序，然后使用双指针进行查找，
时间复杂度为 O(N2)
同样不适用与数组存在重复元素的情况
 */
public class ThreeSumTwoPointer implements ThreeSum{
    @Override
    public int count(int[] nums) {
        int N = nums.length;
        int cnt = 0;
        Arrays.sort(nums);

        for (int i = 0; i < N - 2; i++) {
            int l = i + 1, r = N - 1, target = -nums[i];
            while (l < r) {
             int sum = nums[l]+nums[r];
                if (sum == target) {
                    cnt++;l++;r--;
                } else if (sum<target) {
                    l++;
                }else {
                    r--;
                }
            }
        }
        return cnt;
    }
}
