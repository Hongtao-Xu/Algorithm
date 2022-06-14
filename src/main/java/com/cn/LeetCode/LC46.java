package com.cn.LeetCode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 *46. 全排列
 *https://leetcode-cn.com/problems/permutations/
 */
public class LC46 {

    //1.深度优先+回溯
    public class Solution {
        public List<List<Integer>> permute(int[] nums) {
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            Deque<Integer> path = new LinkedList<>();
            boolean[] used = new boolean[len];
            if (len == 0)
                return res;
            dsf(nums, 0, len, path, res, used);
            return res;
        }

        private void dsf(int[] nums,
                         int depth,
                         int len,
                         Deque<Integer> path,
                         List<List<Integer>> res,
                         boolean[] used) {
            //终止条件
            if (depth == len) {
                res.add(new ArrayList<>(path));//这里很关键
                return;//不能遗漏
            }
            for (int i = 0; i < len; i++) {
                if (!used[i]) {
                    path.addLast(nums[i]);
                    used[i] = true;

                    dsf(nums, depth + 1, len, path, res, used);

                    used[i] = false;
                    path.removeLast();
                }
            }
        }
    }

    //2.深度优先+不回溯
    public class Solution1 {
        public List<List<Integer>> permute(int[] nums) {
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            boolean[] used = new boolean[len];
            if (len == 0)
                return res;
            dsf(nums, 0, len, path, res, used);
            return res;
        }

        private void dsf(int[] nums,
                         int depth,
                         int len,
                         List<Integer> path,
                         List<List<Integer>> res,
                         boolean[] used) {
            //终止条件
            if (depth == len) {
                res.add(path);//用拷贝，因为每一层传递下来的 path 变量都是新建的
                return;//不能遗漏
            }
            for (int i = 0; i < len; i++) {
                if (!used[i]) {
                    //每一次尝试都创建新的变量表示当前的"状态"
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(nums[i]);

                    boolean[] newUsed = new boolean[len];
                    System.arraycopy(used, 0, newUsed, 0, len);
                    newUsed[i] = true;

                    dsf(nums, depth + 1, len, newPath, res, newUsed);
                    //无需回溯
                }
            }
        }
    }
}
