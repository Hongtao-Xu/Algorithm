package com.cn.LeetCode;

/*
 * 124. 二叉树中的最大路径和
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 */
public class LC124 {

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

    //1.递归法
    class Solution {
        public int maxSum = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            maxGain(root);
            return maxSum;
        }

        private int maxGain(TreeNode node) {
            if (node == null)
                return 0;
            //1.递归寻找左右节点的最大贡献值(大于0)
            int leftMaxGain = Math.max(maxGain(node.left), 0);
            int rightMaxGain = Math.max(maxGain(node.right), 0);

            //2.最大路径和
            int priceNewPath = node.val + leftMaxGain + rightMaxGain;

            //3.更新maxSum
            maxSum = Math.max(priceNewPath, maxSum);

            //4.回溯
            return node.val + Math.max(leftMaxGain, rightMaxGain);
        }
    }
}
