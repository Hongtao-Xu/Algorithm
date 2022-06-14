package com.cn.LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 *102. 二叉树的层序遍历
 *https://leetcode.cn/problems/binary-tree-level-order-traversal/
 *@Time: 2022/5/17  23:24
 */
public class LC102 {

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

    //1. 广度优先，队列
    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();

            if (root == null) return res;
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                List<Integer> level = new ArrayList<>();
                int currentLevelSize = q.size();
                //队列的大小就是这一层节点的数量
                for (int i = 0; i < currentLevelSize; i++) {
                    TreeNode cur = q.remove();
                    level.add(cur.val);
                    if (cur.left != null)
                        q.add(cur.left);
                    if (cur.right != null)
                        q.add(cur.right);
                }
                //一层遍历完
                res.add(level);
            }
            return res;
        }
    }

}
