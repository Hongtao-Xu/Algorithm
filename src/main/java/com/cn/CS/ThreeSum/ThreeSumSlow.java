package com.cn.CS.ThreeSum;

/*
 * 数量级为 O(N3)
 */
public class ThreeSumSlow implements ThreeSum {

    @Override
    public int count(int[] nums) {
        int N = nums.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

}
