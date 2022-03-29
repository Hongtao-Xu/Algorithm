package com.cn.advance.Stage3.Trie;

import com.cn.advance.Stage2.SetAndMap.Set.BSTSet;
import com.cn.advance.utils.FileOperation;

import java.util.ArrayList;

/*
 * 测试
 */
public class Trie_Test {
    public static void main(String[] args) {
        test_bstSet();
        test_trieSet();
    }

    private static void test_bstSet() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/main/java/com/cn/advance/Stage3/Trie/pride-and-prejudice.txt", words)) {

            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words)
                set.add(word);

            for (String word : words)
                set.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");
        }
    }

    private static void test_trieSet() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/main/java/com/cn/advance/Stage3/Trie/pride-and-prejudice.txt", words)) {

            long startTime = System.nanoTime();

            TrieSet<String> set = new TrieSet<>();
            for (String word : words)
                set.add(word);

            for (String word : words)
                set.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("TrieSet: " + time + " s");
        }
        System.out.println();
    }
}
