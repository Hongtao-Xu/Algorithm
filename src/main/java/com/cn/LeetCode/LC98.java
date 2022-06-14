package com.cn.LeetCode;

import com.sun.source.tree.Tree;

import java.util.Deque;
import java.util.LinkedList;

/*
 *98. 验证二叉搜索树
 *https://leetcode.cn/problems/validate-binary-search-tree/
 */
public class LC98 {

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
        public boolean isValidBST(TreeNode root) {
            return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean isBST(TreeNode node, long lower, long upper) {
            if (node == null)
                return true;
            if (node.val <= lower || node.val >= upper)
                return false;
            return isBST(node.left, lower, node.val) && isBST(node.right, node.val, upper);
        }
    }

    //2.中序遍历
    class Solution1 {
        long pre = Long.MIN_VALUE;

        public boolean isValidBST(TreeNode root) {
            return inOrder(root);
        }

        private boolean inOrder(TreeNode node) {
            if (node == null)
                return true;

            //访问左子树
            if (inOrder(node.left)) {
                return true;
            }
            //访问中间节点
            if (node.val <= pre)
                return false;
            pre = node.val;
            //访问右子树
            if (inOrder(node.right)) {
                return true;
            }
            return true;
        }
    }

    //3.中序遍历,非递归
    class Solution2 {
        long pre = Long.MIN_VALUE;

        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            Deque<TreeNode> stack = new LinkedList<>();

            //向左走到底
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (root.val <= pre) return false;
                pre = root.val;
                root = root.right;
            }
            return true;
        }
    }

}
