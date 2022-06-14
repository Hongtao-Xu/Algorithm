package com.cn.LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
 *85. 最大矩形
 *https://leetcode-cn.com/problems/maximal-rectangle/
 */
public class LC85 {
    //1.暴力解法
    class Solution {
        public int maximalRectangle(char[][] matrix) {
            int row = matrix.length;
            if (row == 0) return 0;
            int col = matrix[0].length;
            int[][] length = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == '1')
                        length[i][j] = (j == 0 ? 0 : length[i][j - 1]) + 1;
                }
            }
            int ret = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (length[i][j] == 0) continue;

                    int width = length[i][j];
                    int area = width * 1;//这里很容易忽略
                    for (int k = i - 1; k >= 0; k--) {
                        width = Math.min(width, length[k][j]);
                        area = Math.max(area, width * (i - k + 1));
                    }
                    ret = Math.max(area, ret);
                }
            }
            return ret;
        }
    }

    //2.单调栈
    class Solution1 {
        public int maximalRectangle(char[][] matrix) {
            int row = matrix.length;
            if (row == 0) return 0;
            int col = matrix[0].length;
            int[] heights = new int[col + 1];//哨兵
            int maxArea=0;
            for (int i = 0; i < row; i++) {
                Deque<Integer> stack = new ArrayDeque<>();
                heights[col]=0;//哨兵高度为0，不肯在他的右边有比他还矮的数，所以遍历完后，栈一定为空
                //每求一个高度就进行栈的操作
                for (int j = 0; j <= col; j++) {//列数：[0,col]
                    if(j<col){// 多申请了 1 个元素，所以要判断,防止越界
                        if(matrix[i][j]=='1')
                            heights[j]+=1;
                        else
                            heights[j]=0;
                    }
                    if(stack.isEmpty()||heights[j]>=heights[stack.peek()]) {
                        stack.push(j);
                    }else {
                        //每次要判断新的栈顶是否高于当前元素
                        while (!stack.isEmpty() && heights[j] < heights[stack.peek()]) {
                            int height = heights[stack.pop()];
                            int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
                            int rightLessMin = j;
                            int area = (rightLessMin - leftLessMin - 1) * height;
                            maxArea = Math.max(area, maxArea);
                        }
                        stack.push(j);
                    }
                }
            }
            return maxArea;
        }
    }



}
