package com.cn.advance.Stage3.HashMap;

import com.cn.advance.utils.FileOperation;

import java.util.ArrayList;

/*
 * 完全随机：普通二分搜索树性能很好，缺点：退化链表
 * AVL: 对于查询较多
 * RBTree: 牺牲了平衡性(2logn的高度)，综合性能更好，统计性能好
 */
public class Main {
    private static final String book = "src/main/java/com/cn/advance/utils/pride-and-prejudice.txt";

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile(book, words)) {
            //Test BST Tree
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

            //Test AVL Tree
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

            //Test HashTable
            startTime = System.nanoTime();
            HashTable<String, Integer> ht = new HashTable<>();
            for (String word : words) {
                if (ht.contains(word))
                    ht.set(word, ht.get(word) + 1);
                else
                    ht.add(word, 1);
            }
            for (String word : words)
                ht.contains(word);

            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("Hash Table: " + time + " s");

            //Test HashTable2
            startTime = System.nanoTime();
            HashTable2<String, Integer> ht2 = new HashTable2<>();
            for (String word : words) {
                if (ht2.contains(word))
                    ht2.set(word, ht2.get(word) + 1);
                else
                    ht2.add(word, 1);
            }
            for (String word : words)
                ht2.contains(word);

            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("Hash Table: " + time + " s");
        }
    }
}
