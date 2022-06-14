package com.cn.LeetCode;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/4/24  20:08
 */
public class LC62 {
    //1.排序
    class Solution {
        public int uniquePaths(int m, int n) {
            long ans = 1;
            //n<----m
            for (int x = n, y = 1; y < m; x++, y++) {
                ans = ans * x / y;
            }
            return (int) ans;
        }
    }

    //2.动态规划
    class Solution2 {
        public int uniquePaths(int m, int n) {
            int[][] f = new int[m][n];
            //初始化两条边
            for (int i = 0; i < m; i++)
                f[i][0] = 1;
            for (int j = 0; j < n; j++)
                f[0][j] = 1;
            //遍历所有格子
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
            }
            return f[m - 1][n - 1];
        }
    }
}
