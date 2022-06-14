package com.cn.LeetCode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 32. 最长有效括号
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 *
 */
public class LC32 {

    /**
     * if s[i] == '(' :
     * dp[i] = 0
     * if s[i] == ')' :
     * if s[i - 1] == '(' :
     * dp[i] = dp[i - 2] + 2 #要保证i - 2 >= 0
     * <p>
     * if s[i - 1] == ')' and s[i - dp[i - 1] - 1] == '(' :
     * dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2 #要保证i - dp[i - 1] - 2 >= 0
     */
    //1.动态规划
    //时间：O(n)
    //空间：O(n)
    class Solution1 {
        public int longestValidParentheses(String s) {
            int n = s.length();
            int[] dp = new int[n];//dp是以i处括号结尾的有效括号长度
            int max_len = 0;
            //i从1开始，一个是单括号无效，另一个是防i - 1索引越界
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) == ')') {//遇见右括号才开始判断
                    if (s.charAt(i - 1) == '(') {
                        if (i < 2) dp[i] = 2;
                        else dp[i] = dp[i - 2] + 2;
                    } else {//s.charAt(i-1)==')'
                        if (dp[i - 1] > 0) {//前一个括号是有效括号
                            int pre_left = i - dp[i - 1] - 1;//注意边界条件
                            if (pre_left >= 0 && s.charAt(pre_left) == '(') {
                                dp[i] = dp[i - 1] + 2;
                                //**左括号前还可能存在有效括号
                                if (pre_left - 1 > 0) {
                                    dp[i] = dp[i - 1] + dp[pre_left - 1] + 2;
                                }
                            }
                        }
                    }
                }
                max_len = Math.max(max_len, dp[i]);
            }
            return max_len;
        }
    }

    //2.栈
    //时间：O(n)
    //空间：O(n)
    class Solution2 {
        public int longestValidParentheses(String s) {
            int maxans = 0;
            Deque<Integer> stack = new LinkedList<>();
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        maxans = Math.max(maxans, i - stack.peek());
                    }
                }
            }
            return maxans;
        }
    }

    //3.不需要额外空间
    //时间：O(n)
    //空间：O(1)
    class Solution {
        public int longestValidParentheses(String s) {
            int left = 0, right = 0, maxLength = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    maxLength = Math.max(maxLength, 2 * left);
                } else if (right > left) {//右括号比左括号还多
                    left = right = 0;//重置
                }
            }
            left = right = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == ')') {
                    right++;
                } else {
                    left++;
                }
                if (left == right) {
                    maxLength = Math.max(maxLength, 2 * left);
                } else if (left > right) {//左括号比右括号还多
                    left = right = 0;//重置
                }
            }
            return maxLength;
        }
    }

}
