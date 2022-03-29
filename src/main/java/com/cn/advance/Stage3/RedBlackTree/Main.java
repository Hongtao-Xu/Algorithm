package com.cn.advance.Stage3.RedBlackTree;

import com.cn.advance.utils.FileOperation;

import java.util.ArrayList;
import java.util.Collections;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/3/26  21:50
 */
public class Main {
    private static final String book = "src/main/java/com/cn/advance/utils/pride-and-prejudice.txt";

    public static void main(String[] args) {
        //test_1();
        test_2();
    }

    //性能对比 RBTree
    private static void test_2() {
        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile(book, words)) {
            //测试最坏情况
            //BST：退化为链表
            //AVL: 依然平衡
            Collections.sort(words);
            //======================Test BST Tree======================
            long startTime = System.nanoTime();
            BST<String, Integer> bst = new BST<>();
            for (String word : words) {
                if (bst.contains(word))
                    bst.set(word, bst.get(word) + 1);
                else
                    bst.add(word, 1);
            }
            for (String word : words)
                bst.contains(word);
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST Tree: " + time + " s");

            //======================Test AVL Tree======================
            startTime = System.nanoTime();
            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.set(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }
            for (String word : words)
                avl.contains(word);
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL Tree: " + time + " s");

            //======================Test RB Tree======================
            startTime = System.nanoTime();
            RBTree<String, Integer> rbt = new RBTree<>();
            for (String word : words) {
                if (rbt.contains(word))
                    rbt.set(word, avl.get(word) + 1);
                else
                    rbt.add(word, 1);
            }
            for (String word : words)
                rbt.contains(word);
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("RB Tree: " + time + " s");
        }
    }

    //测试AVL是否平衡与基本功能
    private static void test_1() {
        long startTime = System.nanoTime();
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(book, words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.set(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }
            System.out.println("Total different words: " + avl.getSize());
            System.out.println("Frequency of PRIDE: " + avl.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + avl.get("prejudice"));
            System.out.println("is BST: " + avl.isBST());
            System.out.println("is AVL: " + avl.isBalanced());

            for (String word : words)
                avl.remove(word);
            if (!avl.isBST() || !avl.isBalanced())
                throw new RuntimeException("Error");
        }
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST Tree: " + time + " s");
    }
}
