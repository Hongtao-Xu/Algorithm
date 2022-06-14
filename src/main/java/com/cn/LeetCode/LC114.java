package com.cn.LeetCode;

import javax.swing.tree.TreeNode;
import java.util.*;

/*
 *@114. 二叉树展开为链表
 *https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
 */
public class LC114 {

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

    //1.先前序，后展开
    class Solution {
        public void flatten(TreeNode root) {
            List<TreeNode> list = new ArrayList<>();
            preOrder(root, list);
            //展开
            int len = list.size();
            for (int i = 1; i < len; i++) {
                TreeNode pre = list.get(i - 1);
                TreeNode cur = list.get(i);
                pre.left = null;
                pre.right = cur;
            }
        }

        private void preOrder(TreeNode node, List<TreeNode> list) {
            if (node == null)
                return;
            list.add(node);
            preOrder(node.left, list);
            preOrder(node.right, list);
        }
    }

    //2.先前序，后展开,递归
    class Solution2 {
        public void flatten(TreeNode root) {
            List<TreeNode> list = new ArrayList<>();
            Deque<TreeNode> stack = new LinkedList<>();
            TreeNode node = root;
            while (node != null || !stack.isEmpty()) {//node不为null 或 栈不为空
                while (node != null) {
                    list.add(node);
                    stack.push(node);
                    node = node.left;
                }
                node = stack.pop();
                node = node.right;
            }

            int len = list.size();
            for (int i = 1; i < len; i++) {
                TreeNode pre = list.get(i - 1);
                TreeNode cur = list.get(i);
                pre.left = null;
                pre.right = cur;
            }
        }
    }

    //3.前序+展开
    class Solution3 {
        public void flatten(TreeNode root) {
            if (root == null)
                return;
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            TreeNode prev = null;
            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                if (prev != null) {//初始时，prev是null
                    prev.left = null;
                    prev.right = cur;
                }
                TreeNode left = cur.left, right = cur.right;
                if (right != null)
                    stack.push(right);
                if (left != null)
                    stack.push(left);
                prev = cur;
            }
        }
    }

    //前驱节点
    class Solution4 {
        public void flatten(TreeNode root) {
            TreeNode curr = root;
            while (curr != null) {
                if (curr.left != null) {//寻找前驱节点
                    TreeNode next = curr.left;
                    TreeNode predecessor = next;
                    while (predecessor.right != null) {
                        predecessor = predecessor.right;
                    }
                    //这几步操作
                    //1.移动curr的右子树到predecessor的右
                    predecessor.right = curr.right;
                    //2.断开curr的左子树
                    curr.left = null;
                    //移动到curr的右子树
                    curr.right = next;
                }
                curr = curr.right;
            }
        }
    }
}
