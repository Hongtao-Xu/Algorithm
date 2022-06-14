package com.cn.LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 200. 岛屿数量
 * https://leetcode.cn/problems/number-of-islands/
 */
public class LC200 {


    //1.深度优先
    class Solution {
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0)
                return 0;

            int nr = grid.length;
            int nc = grid[0].length;
            int num_islands = 0;

            for (int r = 0; r < nr; r++) {
                for (int c = 0; c < nc; c++) {
                    if (grid[r][c] == '1') {
                        num_islands++;
                        dfs(grid, r, c);
                    }
                }
            }
            return num_islands;
        }

        void dfs(char[][] grid, int r, int c) {
            // 判断 base case
            // 如果坐标 (r, c) 超出了网格范围，直接返回
            if (!inArea(grid, r, c)) {
                return;
            }
            if (grid[r][c] == '0' || grid[r][c] == '2')
                return;

            grid[r][c] = '2'; // 将格子标记为「已遍历过」
            // 访问上、下、左、右四个相邻结点
            dfs(grid, r - 1, c);
            dfs(grid, r + 1, c);
            dfs(grid, r, c - 1);
            dfs(grid, r, c + 1);

        }

        boolean inArea(char[][] grid, int r, int c) {
            return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
        }
    }

    //2.广度优先
    class Solution1 {
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0)
                return 0;

            int nr = grid.length;    //行数
            int nc = grid[0].length; //列数
            int num_islands = 0;

            for (int r = 0; r < nr; r++) {
                for (int c = 0; c < nc; c++) {
                    if (grid[r][c] == '1') {//陆地
                        num_islands++;
                        grid[r][c] = '0';
                        Queue<Integer> neighbors = new LinkedList<>();
                        //将陆地坐标加入队列
                        neighbors.add(r * nc + c);//行*列数+列  顺着数第几个
                        while (!neighbors.isEmpty()) {
                            int id = neighbors.remove(); //队列头
                            int row = id / nc;
                            int col = id % nc;

                            //四个方向判断
                            if (row - 1 >= 0 && grid[row - 1][col] == '1') {//不越界且是陆地
                                neighbors.add((row - 1) * nc + col);
                                grid[row - 1][col] = '0';
                            }
                            if (row + 1 < nr && grid[row + 1][col] == '1') {
                                neighbors.add((row + 1) * nc + col);
                                grid[row + 1][col] = '0';
                            }
                            if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                                neighbors.add(row * nc + col - 1);
                                grid[row][col - 1] = '0';
                            }
                            if (col + 1 < nc && grid[row][col + 1] == '1') {
                                neighbors.add(row * nc + col + 1);
                                grid[row][col + 1] = '0';
                            }
                        }
                    }
                }
            }
            return num_islands;
        }
    }

    //3.并查集
    class Solution2 {
        //并查集实现,rank优化
        class UnionFind {
            int count;
            int[] parent;
            int[] rank;


            public UnionFind(char[][] grid) {
                count = 0;
                int m = grid.length;
                int n = grid[0].length;
                parent = new int[m * n];
                rank = new int[m * n];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (grid[i][j] == '1') {
                            parent[i * n + j] = i * n + j;
                            count++;//所有的1
                        }
                        rank[i * n + j] = 0;
                        //当grid[i][j] == '0'时，parent[i * n + j] = 0;rank[i * n + j] = 0;
                    }
                }
            }

            public int find(int i) {
                //不断去查询自己的父亲节点, 直到到达根节点
                while (i != parent[i])
                    i = parent[i];
                return i;
            }

            public void union(int x, int y) {
                int rootx = find(x);
                int rooty = find(y);

                if (rootx == rooty)
                    return;

                // 根据两个元素所在树的rank不同判断合并方向
                // 将rank低的集合合并到rank高的集合上
                if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;//维护rank
                }
                count--;//维护count，连接一次，count-1
            }

            public int getCount() {
                return count;
            }

        }

        //调用
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0)
                return 0;

            int nr = grid.length;    //行数
            int nc = grid[0].length; //列数
            UnionFind uf = new UnionFind(grid);
            for (int row = 0; row < nr; row++) {
                for (int col = 0; col < nc; col++) {
                    if (grid[row][col] == '1') {
                        grid[row][col] = '0';
                        //四个方向判断,是陆地，就连接
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {//不越界且是陆地
                            uf.union(row * nc + col, (row - 1) * nc + col);
                        }
                        if (row + 1 < nr && grid[row + 1][col] == '1') {
                            uf.union(row * nc + col, (row + 1) * nc + col);
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            uf.union(row * nc + col, row * nc + (col - 1));
                        }
                        if (col + 1 < nc && grid[row][col + 1] == '1') {
                            uf.union(row * nc + col, row * nc + (col + 1));
                        }
                    }
                }
            }
            return uf.getCount();
        }

    }

}

//    void dfs(int[][] grid, int r, int c) {
//        // 判断 base case
//        // 如果坐标 (r, c) 超出了网格范围，直接返回
//        if (!inArea(grid, r, c)) {
//            return;
//        }
//        grid[r][c] = 2; // 将格子标记为「已遍历过」
//
//
//        // 访问上、下、左、右四个相邻结点
//        dfs(grid, r - 1, c);
//        dfs(grid, r + 1, c);
//        dfs(grid, r, c - 1);
//        dfs(grid, r, c + 1);
//
//    }
//
//    boolean inArea(int[][] grid, int r, int c) {
//        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
//    }