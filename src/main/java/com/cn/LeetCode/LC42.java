package com.cn.LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/*
 *https://leetcode-cn.com/problems/trapping-rain-water/
 *42. 接雨水
 */
public class LC42 {
    //1.按照行来求
    class Solution {
        public int trap(int[] height) {
            int max = getMax(height);
            int sum = 0;
            for (int i = 1; i <= max; i++) {
                int sum_temp = 0;
                boolean isStart = false;
                for (int j = 0; j < height.length; j++) {
                    if (isStart && height[j] < i)
                        sum_temp++;
                    if (height[j] >= i) {
                        sum += sum_temp;
                        sum_temp = 0;
                        isStart = true;
                    }
                }
            }
            return sum;
        }

        private int getMax(int[] height) {
            int max = 0;
            for (int i = 0; i < height.length; i++) {
                if (height[i] > max)
                    max = height[i];
            }
            return max;
        }
    }

    //2.按照列来求
    class Solution2 {
        public int trap(int[] height) {
            int sum = 0;
            for (int i = 1; i < height.length - 1; i++) {
                int max_left = 0;
                //找出左边最高
                for (int j = i - 1; j >= 0; j--) {
                    if (height[j] > max_left)
                        max_left = height[j];
                }
                //找出右边最高
                int max_right = 0;
                for (int j = i + 1; j < height.length; j++) {
                    if (height[j] > max_right)
                        max_right = height[j];
                }
                //找出两边最高中较矮的
                int min = Math.min(max_left, max_right);
                if (min > height[i]) {
                    sum += (min - height[i]);
                }
            }
            return sum;
        }

    }

    //3.按照列来求+动态规划
    class Solution3 {
        public int trap(int[] height) {
            int sum = 0;
            int[] max_left = new int[height.length];
            int[] max_right = new int[height.length];
            for (int i = 1; i < height.length - 1; i++) {
                max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
            }
            for (int i = height.length - 2; i >= 0; i--) {
                max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
            }
            for (int i = 1; i < height.length - 1; i++) {
                int min = Math.min(max_left[i], max_right[i]);
                if (min > height[i]) {
                    sum += (min - height[i]);
                }
            }
            return sum;
        }
    }

    //4.按照列来求+双指针
    class Solution4 {
        public int trap(int[] height) {
            int sum = 0;
            int max_left = 0;
            int max_right = 0;
            int left = 1;
            int right = height.length - 2;
            for (int i = 1; i < height.length - 1; i++) {
                //从右往左更新
                if (height[left - 1] < height[right + 1]) {
                    max_left = Math.max(max_left, height[left - 1]);
                    int min = max_left;
                    if (min > height[left]) {
                        sum += (min - height[left]);
                    }
                    left++;
                } else {
                    max_right = Math.max(max_right, height[right + 1]);
                    int min = max_right;
                    if (min > height[right]) {
                        sum += (min - height[right]);
                    }
                    right--;
                }
            }
            return sum;
        }
    }

    //5.栈，按层求，每次求两个柱之间的雨水
    class Solution5 {
        public int trap(int[] height) {
            int sum = 0;
            Deque<Integer> stack = new LinkedList<>();
            int current = 0;
            while (current < height.length) {
                //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
                while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                    int h = height[stack.peek()]; //取出要出栈的元素
                    stack.pop();
                    if (stack.isEmpty()) break;
                    int distance = current - stack.peek() - 1;//两堵墙之前的距离。
                    int min = Math.min(height[stack.peek()], height[current]);
                    //注意：
                    //h=min时，是存不住水的，也就是为0
                    //min-h>0,最矮的墙比水底高，才能存水，存水的部分是差值
                    sum += distance * (min-h);
                }
                stack.push(current);//当前指向的墙入栈
                current++;
            }
            return sum;
        }
    }
}
