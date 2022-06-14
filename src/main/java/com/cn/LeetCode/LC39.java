package com.cn.LeetCode;

import java.util.*;

/*
 * 39. 组合总和
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class LC39 {

    //1.回溯
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            int len = candidates.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len == 0)
                return res;
            Deque<Integer> path = new ArrayDeque<>();
            dfs(candidates, 0, len, target, path, res);
            return res;
        }

        /**
         * @param candidates 候选数组
         * @param begin      搜索起点
         * @param len
         * @param target     每减去一个元素，目标值变小
         * @param path       从根结点到叶子结点的路径，是一个栈
         * @param res        结果集列表
         */
        private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
            if (target < 0) return;
            if (target == 0) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = begin; i < len; i++) {
                path.addLast(candidates[i]);
                dfs(candidates, i, len, target - candidates[i], path, res);
                path.removeLast();
            }
        }
    }

    //2.回溯+剪枝
    class Solution1 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            int len = candidates.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len == 0)
                return res;
            Deque<Integer> path = new ArrayDeque<>();

            //排序是剪枝的前提
            Arrays.sort(candidates);
            dfs(candidates, 0, len, target, path, res);
            return res;
        }

        /**
         * @param candidates 候选数组
         * @param begin      搜索起点
         * @param len
         * @param target     每减去一个元素，目标值变小
         * @param path       从根结点到叶子结点的路径，是一个栈
         * @param res        结果集列表
         */
        private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
            // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
            if (target == 0) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = begin; i < len; i++) {
                //前提是已经有序
                if (target - candidates[i] < 0) {
                    break;//剪枝
                }
                path.addLast(candidates[i]);
                dfs(candidates, i, len, target - candidates[i], path, res);
                path.removeLast();
            }
        }
    }

}
