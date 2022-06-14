package com.cn.LeetCode;

import java.util.HashMap;

/*
 *105. 从前序与中序遍历序列构造二叉树
 *https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class LC105 {

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

    //[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
    //[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
    class Solution {
        HashMap<Integer, Integer> map = new HashMap<>();

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int n = preorder.length;
            //构造HashMap，存储中序遍历的 值：序号
            for (int i = 0; i < n; i++) {
                map.put(inorder[i], i);
            }
            return BuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
        }

        private TreeNode BuildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
            //终止条件
            if (preLeft > preRight)
                return null;
            //中序根节点就是，prLeft
            int rootPreIndex = preLeft;
            //取出root值
            Integer rootInorderIndex = map.get(preorder[rootPreIndex]);
            TreeNode root = new TreeNode(preorder[rootPreIndex]);
            //计算左右子树长度
            int leftSize = rootInorderIndex - inLeft;
            int rightSize = inRight - rootInorderIndex;
            //连接左右子树
            root.left = BuildTree(preorder, inorder, rootPreIndex + 1, rootPreIndex + leftSize, inLeft, rootInorderIndex - 1);
            root.right = BuildTree(preorder, inorder, rootPreIndex + leftSize + 1, preRight, rootInorderIndex + 1, inRight);
            return root;
        }
    }
}
