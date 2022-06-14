package com.cn.LeetCode;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/5/6  15:44
 */
public class LC79 {

    class Solution {
        //先上下左后的顺序无关紧要
        private static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        private int rows;//行数
        private int cols;//列数
        private int len;//word字符长度
        private boolean[][] visited;//此位置的值是否用过
        private char[] charArray;//word转字母数组
        private char[][] board;//源字母矩阵

        public boolean exist(char[][] board, String word) {
            this.rows = board.length;
            this.cols = board[0].length;
            if (rows == 0) return false;
            this.visited = new boolean[rows][cols];
            this.len = word.length();
            this.charArray = word.toCharArray();
            this.board = board;
            //遍历所有字母矩阵
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (dfs(i, j, 0)) return true;
                }
            }
            return false;
        }

        public boolean dfs(int x, int y, int begin) {
            //判断找到了对应字母路径,递归终止条件
            if (begin == len - 1) return board[x][y] == charArray[begin];
            //从头开始匹配
            if (board[x][y] == charArray[begin]) {
                visited[x][y] = true;
                //上下左右的移动
                for (int[] dir : DIRECTIONS) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    //1.是否越界 2.是否用过
                    if (inArea(newX, newY) && !visited[newX][newY]) {
                        if (dfs(newX, newY, begin + 1)) return true;
                    }
                }
                visited[x][y] = false;//注意此处的对称性
            }
            return false;
        }

        //判断当前位置是否越界
        public boolean inArea(int x, int y) {
            return x >= 0 && x < rows && y >= 0 && y < cols;
        }

    }

}
