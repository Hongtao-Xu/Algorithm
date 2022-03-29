package com.cn.advance.Stage2.SetAndMap.Set;

import java.util.ArrayList;

/*
 * BSTSet 的测试类
 */
public class BSTSet_Test {
    public static void main(String[] args) {
        test_1();
    }

    //测试读取文本，放入集合，去重
    private static void test_1() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words1 = new ArrayList<>();
        //此处需要Content Root
        if (FileOperation.readFile("src/main/java/com/cn/advance/Stage2/SetMap/pride-and-prejudice.txt", words1)) {
            System.out.println("Total words:  " + words1.size());
            BSTSet<String> set1 = new BSTSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }
        System.out.println();

        System.out.println("A Tale of Two Cities");
        ArrayList<String> words2 = new ArrayList<>();
        if(FileOperation.readFile("src/main/java/com/cn/advance/Stage2/SetMap/a-tale-of-two-cities.txt", words2)){
            System.out.println("Total words: " + words2.size());

            BSTSet<String> set2 = new BSTSet<>();
            for(String word: words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
    }
}
