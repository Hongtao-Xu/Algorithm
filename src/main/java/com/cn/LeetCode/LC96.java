package com.cn.LeetCode;

/*
 *96. 不同的二叉搜索树
 *https://leetcode.cn/problems/unique-binary-search-trees/
 */
public class LC96 {

    //1.动态规划，笛卡尔乘积
    class Solution {
        public int numTrees(int n) {
            int[] res = new int[n + 1];
            res[0] = 1;
            res[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    res[i] = res[j - 1] * res[i - j];
                }
            }
            return res[n];
        }
    }

    //2.为卡塔兰数
    class Solution1 {
        public int numTrees(int n) {
            long res = 1;
            for (int i = 0; i < n; i++) {
                res = res * 2 * (2 * i + 1) / (i + 2);
            }
            return (int) res;
        }
    }

}
