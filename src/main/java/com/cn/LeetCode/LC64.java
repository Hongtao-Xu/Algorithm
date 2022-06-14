package com.cn.LeetCode;

/*
 *64. 最小路径和
 *https://leetcode-cn.com/problems/minimum-path-sum/
 */
public class LC64 {

    //1.不使用额外空间
    class Solution {
        public int minPathSum(int[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (i == 0 && j == 0) continue;
                    else if (i == 0) grid[i][j] = grid[i][j - 1] + grid[i][j];
                    else if (j == 0) grid[i][j] = grid[i - 1][j] + grid[i][j];
                    else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
            return grid[grid.length - 1][grid[0].length - 1];
        }
    }

    //2.使用额外空间
    class Solution1 {
        public int minPathSum(int[][] grid) {
            int rows = grid.length, col = grid[0].length;
            if (grid == null || rows == 0 || col == 0) return 0;
            int[][] dp = new int[rows][col];
            //初始化
            dp[0][0] = grid[0][0];
            for (int i = 1; i < rows; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
            for (int j = 1; j < col; j++) {
                dp[0][j] = dp[0][j - 1] + grid[0][j];
            }
            for (int i = 1; i < rows; i++) {//注意起点
                for (int j = 1; j < col; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
            return dp[rows - 1][col - 1];
        }
    }

}
