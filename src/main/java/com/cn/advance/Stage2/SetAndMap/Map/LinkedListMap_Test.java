package com.cn.advance.Stage2.SetAndMap.Map;

import java.util.ArrayList;

/*
 * 测试：LinkedListMap
 */
public class LinkedListMap_Test {
    public static void main(String[] args) {
        test_1();
    }
    private static final String book1 = "src/main/java/com/cn/advance/Stage2/SetMap/pride-and-prejudice.txt";
    private static final String book2 = "src/main/java/com/cn/advance/Stage2/SetMap/a-tale-of-two-cities.txt";

    private static void test_1() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(book1, words)) {
            System.out.println("Total words: " + words.size());

            LinkedListMap<String, Integer> map = new LinkedListMap<>();
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

}
