package com.cn.LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 */
public class LC22 {
    //1.1 做减法
    class Solution {
        //n:括号的对数
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            //特判
            if (n == 0) return res;
            //深度优先遍历
            dfs("", n, n, res);
            return res;
        }

        private void dfs(String curStr, int left, int right, List<String> res) {
            if (left == 0 && right == 0) {
                res.add(curStr);
                return;
            }
            if (left > right) return;//左括号剩余数量大于右括号
            if (left > 0)
                dfs(curStr + "(", left - 1, right, res);
            if (right > 0)
                dfs(curStr + ")", left, right - 1, res);
        }
    }

    //1.2 做加法
    class Solution2 {
        //n:括号的对数
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            //特判
            if (n == 0) return res;
            //深度优先遍历
            dfs("", 0, 0, n, res);
            return res;
        }

        private void dfs(String curStr, int left, int right, int n, List<String> res) {
            if (left == n && right == n) {
                res.add(curStr);
                return;
            }
            if (left < right) return;//左括号剩余数量大于右括号
            if (left < n)
                dfs(curStr + "(", left + 1, right, n, res);
            if (right < n)
                dfs(curStr + ")", left, right + 1, n, res);
        }
    }

    //2.广度优先
    class Solution3 {
        private class Node {
            private String res;
            //剩余左括号
            private int left;
            private int right;

            public Node(String res, int left, int right) {
                this.res = res;
                this.left = left;
                this.right = right;
            }
        }

        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            if (n == 0) return res;
            //队列
            Queue<Node> queue = new LinkedList<>();
            queue.offer(new Node("", n, n));
            while (!queue.isEmpty()) {
                Node curNode = queue.poll();
                if (curNode.left == 0 && curNode.right == 0) {
                    res.add(curNode.res);
                }
                if (curNode.left > 0) {
                    queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
                }
                if (curNode.right > 0) {
                    queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
                }
            }
            return res;
        }
    }

    //动态规划
    class Solution4 {
        //n:括号的对数
        public List<String> generateParenthesis(int n) {
            LinkedList<LinkedList<String>> result = new LinkedList<>();
            if(n==0)return result.get(0);

            LinkedList<String> list0 = new LinkedList<>();
            list0.add("");
            LinkedList<String> list1 = new LinkedList<>();
            list1.add("()");
            result.add(list1);
            for(int i=2; i<=n; i++){
                LinkedList<String> temp = new LinkedList<>();
                for(int j=0;j<i;j++){
                    LinkedList<String> str1 = result.get(j);
                    LinkedList<String> str2 = result.get(i-1-j);
                    for(String s1:str1){
                        for(String s2:str2){
                            String el = "("+s1+")"+s2;
                            temp.add(el);
                        }
                    }
                }
                result.add(temp);
            }
            return result.get(n);
        }
    }
}










