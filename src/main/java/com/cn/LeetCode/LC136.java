package com.cn.LeetCode;

import java.util.HashMap;
import java.util.Map;

/*
 *136. 只出现一次的数字
 *https://leetcode.cn/problems/single-number/
 */
public class LC136 {

    //1.异或
    class Solution {
        public int singleNumber(int[] nums) {
            int signal = 0;
            for (int n : nums) {
                signal = signal ^ n;
            }
            return signal;
        }
    }

    //2.Hash表
    class Solution1 {
        public int singleNumber(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Integer n : nums) {
                Integer count = map.get(n);
                count = count == null ? 1 : ++count;//count如果为空，就=1，否则就+1
                map.put(n, count);
            }
            for (Integer n : map.keySet()) {
                Integer count = map.get(n);
                if (count==1)
                    return n;
            }
            return -1;
        }
    }

}
