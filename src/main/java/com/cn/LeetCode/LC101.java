package com.cn.LeetCode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/5/14  17:23
 */
public class LC101 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //1.递归
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            return check(root, root);
        }

        private boolean check(TreeNode p, TreeNode q) {
            //1.都为null
            if (p == null && q == null)
                return true;
            //2.其中一个为null
            if (p == null || q == null)
                return false;
            return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
        }
    }

    //2.迭代+栈
    class Solution2 {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) return true;
            return check(root, root);
        }

        private boolean check(TreeNode u, TreeNode v) {
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(u);
            stack.push(v);
            while (!stack.isEmpty()) {
                u = stack.pop();
                v = stack.pop();
                if (u == null && v == null)
                    continue;
                //判错：
                //1.其中一个为null
                //2.或节点值不相等
                if ((u == null || v == null) || (u.val != v.val))
                    return false;
                stack.push(u.left);
                stack.push(v.right);

                stack.push(u.right);
                stack.push(v.left);
            }
            return true;
        }
    }


    //3.迭代+队列
    class Solution3 {
        public boolean isSymmetric(TreeNode root) {
            return check(root, root);
        }

        public boolean check(TreeNode u, TreeNode v) {
            Queue<TreeNode> q = new LinkedList<TreeNode>();
            q.offer(u);
            q.offer(v);
            while (!q.isEmpty()) {
                u = q.poll();
                v = q.poll();
                if (u == null && v == null) {
                    continue;
                }
                if ((u == null || v == null) || (u.val != v.val)) {
                    return false;
                }

                q.offer(u.left);
                q.offer(v.right);

                q.offer(u.right);
                q.offer(v.left);
            }
            return true;
        }
    }

}
