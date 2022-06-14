package com.cn.LeetCode;

/*
 *48. 旋转图像
 *https://leetcode-cn.com/problems/rotate-image/
 */
public class LC48 {

    //1.使用辅助矩阵
    class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            int[][] newMatrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newMatrix[j][n - i - 1] = matrix[i][j];
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = newMatrix[i][j];
                }
            }
        }
    }

    //2.原地旋转
    //4个元素相关，4个元素组成一个环
    class Solution1 {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < (n + 1) / 2; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[n - j - 1][i];
                    matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                    matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                    matrix[j][n - i - 1] = temp;
                }
            }
        }
    }

    //3.二次翻转
    //水平+对角
    class Solution2 {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            //水平
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[n - i - 1][j];
                    matrix[n - i - 1][j] = temp;
                }
            }
            //对角线
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
    }

}
