package com.cn.advance.Stage2.SetAndMap.Map;

import java.util.ArrayList;

/*
 * 测试：BSTMap
 */
public class BSTMap_Test {
    private static final String book1 = "src/main/java/com/cn/advance/Stage2/SetMap/pride-and-prejudice.txt";
    private static final String book2 = "src/main/java/com/cn/advance/Stage2/SetMap/a-tale-of-two-cities.txt";

    public static void main(String[] args) {
        //test_1();
        test_2();
    }

    //测试BSTMap基本功能
    private static void test_1() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(book1, words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> map = new BSTMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }
        System.out.println();
    }

    //测试自定义 [BSTMap,LinkedListMap,AVLMap] 的时间复杂度
    private static void test_2(){
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        double time1 = testMap(bstMap, book2);
        System.out.println("BST Map: " + time1 + " s");

        System.out.println();
        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        double time2 = testMap(linkedListMap, book2);
        System.out.println("Linked List Map: " + time2 + " s");

        System.out.println();
        AVLMap<String, Integer> avlMap = new AVLMap<>();
        double time3 = testMap(avlMap, book2);
        System.out.println("AVLMap: " + time3 + " s");
    }

    private static double testMap(Map<String, Integer> map, String filename){
        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words){
                if(map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
