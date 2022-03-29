package com.cn.advance.Stage3.RedBlackTree;

import java.util.ArrayList;

/*
 * 完全随机：普通二分搜索树性能很好，缺点：退化链表
 * AVL: 对于查询较多
 * RBTree: 牺牲了平衡性(2logn的高度)，综合性能更好，统计性能好
 */
public class Main3 {
    public static void main(String[] args) {
        int n = 20000000;

        ArrayList<Integer> testData = new ArrayList<>();
        for (int i =0 ;i<n;i++)
            testData.add(i);

        //================Test AVL================
        long startTime = System.nanoTime();
        AVLTree<Integer,Integer> avl = new AVLTree<>();
        for (Integer x:testData)
            avl.add(x,null);
        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL: "+time+"s");

        //================Test RBTree================
        startTime = System.nanoTime();
        RBTree<Integer,Integer> rbt = new RBTree<>();
        for (Integer x:testData)
            rbt.add(x,null);
        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBTree: "+time+"s");

    }
}
