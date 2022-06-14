package com.cn.LeetCode;

/*
 *695. 岛屿的最大面积
 *https://leetcode.cn/problems/max-area-of-island/
 */
public class LC695 {

    //1.DFS
    class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            int res = 0;
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    if (grid[r][c] == 1) {
                        int a = area(grid, r, c);
                        res = Math.max(res, a);
                    }
                }
            }
            return res;
        }

        int area(int[][] grid, int r, int c) {
            // 如果坐标 (r, c) 超出了网格范围，直接返回
            if (!inArea(grid, r, c)) {
                return 0;
            }
            // 如果非1，返回
            if (grid[r][c] != 1) {
                return 0;
            }
            grid[r][c] = 2; // 将格子标记为「已遍历过」

            // 访问上、下、左、右四个相邻结点
            return 1
                    + area(grid, r - 1, c)
                    + area(grid, r + 1, c)
                    + area(grid, r, c - 1)
                    + area(grid, r, c + 1);
        }

        boolean inArea(int[][] grid, int r, int c) {
            return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
        }
    }
}
