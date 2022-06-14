package com.cn.Company.ByteDance;

import java.util.HashMap;
import java.util.Map;

/*
 * 两个数之和
 */
public class JZ2 {

    //1.暴力枚举
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
            return new int[0];
        }
    }


    //2.hashMap
    class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> hm = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (hm.containsKey(target - nums[i])) {
                    return new int[]{i, hm.get(target - nums[i])};
                }
                hm.put(nums[i], i);
            }
            return new int[0];
        }
    }

}
