package com.cn.LeetCode;

import java.util.ArrayList;
import java.util.List;

/*
 * 78.子集
 * https://leetcode-cn.com/problems/subsets/
 */
public class LC78 {

    //1.二进制
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<Integer> t = new ArrayList<Integer>();
            List<List<Integer>> ans = new ArrayList<>();
            int n = nums.length;
            //mask:[0,1<<n-1]
            for (int mask = 0; mask < (1 << n); mask++) {
                t.clear();
                for (int i = 0; i < n; i++) {
                    //mask & (1 << i) 只有所有位数都相同，才能==0
                    if ((mask & (1 << i)) != 0) {
                        t.add(nums[i]);
                    }
                }
                ans.add(new ArrayList<Integer>(t));
            }
            return ans;
        }
    }

    //2.循环枚举
    static class Solution1 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<Integer>());
            for (Integer n : nums) {
                int size = res.size();
                for (int i = 0; i < size; i++) {
                    List<Integer> newSub = new ArrayList<>(res.get(i));
                    newSub.add(n);
                    res.add(newSub);
                }
            }
            return res;
        }
    }

    //3.递归枚举
    static class Solution2 {

        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<Integer>());
            recursion(nums, 0, res);
            return res;
        }

        public void recursion(int[] nums, int i, List<List<Integer>> res) {
            if (i >= nums.length) return;
            int size = res.size();
            for (int j = 0; j < size; j++) {
                List<Integer> newSub = new ArrayList<>(res.get(j));
                newSub.add(nums[i]);
                res.add(newSub);
            }
            recursion(nums, i + 1, res);
        }
    }

    //4.中序遍历
    static class Solution3 {

        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<Integer>());
            inOrder(nums, 0, new ArrayList<Integer>(), res);
            return res;
        }

        public void inOrder(int[] nums, int i, List<Integer> subset, List<List<Integer>> res) {
            if (i >= nums.length) return;
            subset = new ArrayList<>(subset);
            inOrder(nums, i + 1, subset, res);
            subset.add(nums[i]);
            res.add(subset);
            inOrder(nums, i + 1, subset, res);
        }


    }

    //5.回溯
    static class Solution4 {

        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<Integer>());
            backtrack(nums, 0, new ArrayList<>(), res);
            return res;
        }

        public void backtrack(int[] nums, int i, List<Integer> sub, List<List<Integer>> res) {
            for (int j = i; j < nums.length; j++) {
                if (j > i && nums[j] == nums[j - 1]) continue;
                sub.add(nums[j]);
                res.add(new ArrayList<>(sub));
                backtrack(nums, j + 1, sub, res);
                sub.remove(sub.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        int[] nums = new int[]{1, 2, 3};
        solution3.subsets(nums);
    }

}
