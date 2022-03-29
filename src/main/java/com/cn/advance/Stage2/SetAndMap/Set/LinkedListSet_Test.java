package com.cn.advance.Stage2.SetAndMap.Set;

import java.util.ArrayList;

/*
 * 测试LinkedListSet
 */
public class LinkedListSet_Test {
    public static void main(String[] args) {
        //test_1();
        test_2();
    }

    private static final String book1 = "src/main/java/com/cn/advance/Stage2/SetMap/pride-and-prejudice.txt";
    private static final String book2 = "src/main/java/com/cn/advance/Stage2/SetMap/a-tale-of-two-cities.txt";

    //测试LinkedListSet基本功能
    private static void test_1() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words1 = new ArrayList<>();
        //此处需要Content Root
        if (FileOperation.readFile(book1, words1)) {
            System.out.println("Total words:  " + words1.size());
            LinkedListSet<String> set1 = new LinkedListSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }
        System.out.println();

        System.out.println("A Tale of Two Cities");
        ArrayList<String> words2 = new ArrayList<>();
        if(FileOperation.readFile(book2, words2)){
            System.out.println("Total words: " + words2.size());
            LinkedListSet<String> set2 = new LinkedListSet<>();
            for(String word: words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
    }

    //测试自定义BSTSet与LinkedListSet的时间复杂度
    private static void test_2(){
        BSTSet<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet, book2);
        System.out.println("BST Set: " + time1 + " s");

        System.out.println();
        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, book2);
        System.out.println("Linked List Set: " + time2 + " s");

        System.out.println();
        AVLSet<String> avlSet = new AVLSet<>();
        double time3 = testSet(avlSet, book2);
        System.out.println("AVL Set: " + time3 + " s");
    }

    private static double testSet(Set<String> set, String filename){
        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                set.add(word);
            System.out.println("Total different words: " + set.getSize());
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
}
