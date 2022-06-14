package com.cn.LeetCode;

import java.util.*;

/*
 * 40. 组合总和 II
 * https://leetcode-cn.com/problems/combination-sum-ii/
 */
public class LC40 {
    //回溯+减枝
    class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            int len = candidates.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len == 0) return res;
            Arrays.sort(candidates);
            Deque<Integer> path = new ArrayDeque<>();
            dfs(candidates, len, 0, target, path, res);
            return res;
        }

        private void dfs(int[] candidates,
                         int len,
                         int begin,
                         int target,
                         Deque<Integer> path,
                         List<List<Integer>> res) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = begin; i < len; i++) {
                // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
                if (target - candidates[i] < 0) break;
                // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
                if (i > begin && candidates[i] == candidates[i - 1]) continue;
                path.addLast(candidates[i]);
                // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
                dfs(candidates, len, i+1, target - candidates[i], path, res);
                path.removeLast();
            }
        }
    }
}
