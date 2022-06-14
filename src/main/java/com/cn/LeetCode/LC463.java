package com.cn.LeetCode;

/*
 * 463. 岛屿的周长
 * https://leetcode.cn/problems/island-perimeter/
 */
public class LC463 {

    class Solution {
        public int islandPerimeter(int[][] grid) {
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    if (grid[r][c] == 1) //只有一个岛屿
                        return dfs(grid, r, c);
                }
            }
            return 0;
        }

        int dfs(int[][] grid, int r, int c) {
            // 判断 base case
            // 如果坐标 (r, c) 超出了网格范围，直接返回
            if (!inArea(grid, r, c))
                return 1;
            if (grid[r][c] == 0)
                return 1;
            if (grid[r][c] != 1)
                return 0;

            grid[r][c] = 2; // 将格子标记为「已遍历过」
            // 访问上、下、左、右四个相邻结点
            return dfs(grid, r - 1, c)
                    + dfs(grid, r + 1, c)
                    + dfs(grid, r, c - 1)
                    + dfs(grid, r, c + 1);

        }

        boolean inArea(int[][] grid, int r, int c) {
            return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
        }
    }

}
