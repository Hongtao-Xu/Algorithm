package com.cn.LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * 169. 多数元素
 * https://leetcode.cn/problems/majority-element/
 */
public class LC169 {

    //1.哈希表
    class Solution {
        public int majorityElement(int[] nums) {
            Map<Integer, Integer> countMap = countNum(nums);
            Map.Entry<Integer, Integer> majorityEntry = null;
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {//>=1
                    majorityEntry = entry;
                }
            }
            return majorityEntry.getKey();
        }

        private Map<Integer, Integer> countNum(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int n : nums) {
                if (!map.containsKey(n)) {
                    map.put(n, 1);
                } else {
                    map.put(n, map.get(n) + 1);
                }
            }
            return map;
        }
    }

    //2.排序
    class Solution1 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }

    //3.随机抽取
    class Solution2 {
        public int majorityElement(int[] nums) {
            Random random = new Random();
            int majorityCount = nums.length / 2;
            while (true) {
                int candidate = nums[randRange(random, 0, nums.length)];
                if (countOccurences(nums, candidate) > majorityCount)
                    return candidate;
            }
        }


        private int randRange(Random rand, int min, int max) {
            return rand.nextInt(max - min) + min;
        }

        private int countOccurences(int[] nums, int num) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == num)
                    count++;
            }
            return count;
        }

    }

    //4.分治
    class Solution3 {
        public int majorityElement(int[] nums) {
            return majorityElementRec(nums, 0, nums.length - 1);
        }

        private int majorityElementRec(int[] nums, int lo, int hi) {
            if(lo==hi)
                return nums[lo];

            int mid = (hi - lo) / 2 + lo;
            int left = majorityElementRec(nums, lo, mid);
            int right = majorityElementRec(nums, mid + 1, hi);


            if (left==right)
                return left;

            int leftCount = countOccurences(nums, left);
            int rightCount = countOccurences(nums, right);
            return leftCount < rightCount ? right : left;
        }

        private int countOccurences(int[] nums, int num) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == num)
                    count++;
            }
            return count;
        }
    }

    //5.Boyer-Moore
    class Solution4 {
        public int majorityElement(int[] nums) {
            int count=0;
            Integer candidate = null;
            for (int n:nums){
                if (count==0)
                    candidate = n;
                count+=(n==candidate)?1:-1;
            }
            return candidate;
        }
    }

}
