package com.cn.advance.Stage2.BSTree;

/*
 *SBT_NR 非递归实现的测试
 */
public class BST_NR_Test {
    public static void main(String[] args) {
        BST_NR<Integer> bst = new BST_NR<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.add(num);
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        bst.preOrderNR();
        System.out.println();
        //5 3 2 4 6 8
    }
}
