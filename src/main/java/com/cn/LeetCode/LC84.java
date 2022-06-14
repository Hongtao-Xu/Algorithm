package com.cn.LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/5/6  20:54
 */
public class LC84 {

    //1.暴力解法
    static class Solution {
        public int largestRectangleArea(int[] heights) {
            int len = heights.length;
            int height;
            int max = 0;
            for (int i = 0; i < len; i++) {
                height = heights[i];
                int right = 0, left = 0;
                for (int j = i; j < len && heights[j] >= height; j++) {
                    right++;
                }
                for (int k = i - 1; k >= 0 && heights[k] >= height; k--) {
                    left++;
                }
                int area = (right + left) * height;
                if (area > max) max = area;
            }
            return max;
        }
    }

    //2.栈顶
    static class Solution1 {
        public int largestRectangleArea(int[] heights) {
            int len = heights.length;
            if (len == 0) return 0;
            if (len == 1) return heights[0];
            int res = 0;
            Deque<Integer> stack = new ArrayDeque<>(len);
            for (int i = 0; i < len; i++) {
                // 这个 while 很关键，因为有可能不止一个柱形的最大宽度可以被计算出来
                while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {//右边的柱小于当前栈顶的高度
                    int curHeight = heights[stack.pollLast()];
                    //特殊情况2: 如果有相等的柱，直接弹出
                    while (!stack.isEmpty() && heights[stack.peek()] == curHeight) {
                        stack.pollLast();
                    }
                    int curWidth;
                    if (stack.isEmpty()) {
                        curWidth = i;//特殊情况1，栈为空，一定可以扩散到最左边
                    } else {
                        curWidth = i - stack.peekLast() - 1;
                    }
                    res = Math.max(res, curHeight * curWidth);
                }
                stack.addLast(i);
            }
            //特殊情况3: 所有元素弹出
            while (!stack.isEmpty()) {
                int curHeight = heights[stack.pollLast()];
                while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                    stack.pollLast();
                }
                int curWidth;
                if (stack.isEmpty()) {
                    curWidth = len;
                } else {
                    curWidth = len - stack.peekLast() - 1;
                }
                res = Math.max(res, curHeight * curWidth);
            }
            return res;
        }
    }

    //3.栈+哨兵
    static class Solution2 {
        public int largestRectangleArea(int[] heights) {
            int len = heights.length;
            if (len == 0) return 0;
            if (len == 1) return heights[0];
            int res = 0;
            int[] newHeight = new int[len + 2];
            newHeight[0] = 0;//左哨兵
            System.arraycopy(heights, 0, newHeight, 1, len);
            newHeight[len + 1] = 0;//右哨兵
            len += 2;
            heights = newHeight;
            Deque<Integer> stack = new ArrayDeque<>(len);
            // 先放入哨兵，在循环里就不用做非空判断
            stack.addLast(0);
            for (int i = 0; i < len; i++) {
                while (heights[i] < heights[stack.peekLast()]) {
                    int curHeight = heights[stack.pollLast()];
                    int curWidth = i - stack.peekLast() - 1;
                    res = Math.max(res, curHeight * curWidth);
                }
                stack.addLast(i);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        int res = solution.largestRectangleArea(heights);
        System.out.println(res);
    }
}
