package com.cn.LeetCode;

/*
 * 110. 平衡二叉树
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 */
public class LC110 {
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

    //1.自顶向下递归
    class Solution {
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            } else {
                boolean cur = Math.abs(height(root.left) - height(root.right)) <= 1;
                boolean left = isBalanced(root.left);
                boolean right = isBalanced(root.right);
                //当前节点，左子树，右子树都为AVL
                return cur && left && right;
            }
        }

        public int height(TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                //+1是因为要加上本层的高度
                return Math.max(height(root.left), height(root.right)) + 1;
            }
        }
    }

    //2.自底向上递归
    /**
     * 如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），
     * 否则返回 −1。
     * 如果存在一棵子树不平衡，则整个二叉树一定不平衡。
     */
    class Solution1 {
        public boolean isBalanced(TreeNode root) {
            return height(root)>=0;
        }
        public int height(TreeNode root) {
            if (root==null)return 0;
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
                return -1;
            }else {
                return Math.max(leftHeight, rightHeight) + 1;
            }
        }
    }
}
