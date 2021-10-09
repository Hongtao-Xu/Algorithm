package com.cn.mk.LinearSearch;

import com.cn.mk.utils.ArrayGenerator;

/*
 *@program:线形查找
 *@author: xu-hongtao
 *@Time: 2021/9/8  11:32
 */
public class LinearSearch {
    //私有化构造方法
    private LinearSearch() {
    }

//    public static int search(int[] data, int target) {
//        for (int i = 0; i < data.length; i++) {
//            if (data[i] == target) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    public static void main(String[] args) {
//        int[] data = {35, 4, 23, 456, 38, 45, 6, 8};
//        int res1 = LinearSearch.search(data, 45);
//        System.out.println(res1);
//        int res2 = LinearSearch.search(data, 4);
//        System.out.println(res2);
//    }


    //泛型版本：
    public static <E> int search(E[] data, E target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target)) {//类对象的比较要用euals()
                return i;
            }
        }
        return -1;
    }

    public static void main1(String[] args) {
        //测试包装类Integer
        int n = 100000;
        Integer[] data = ArrayGenerator.generateOrderedArray(n);//泛型要使用包装类
        int res1 = LinearSearch.search(data, n);
        System.out.println(res1);
        int res2 = LinearSearch.search(data, 7);
        System.out.println(res2);
        //测试自定义类
        Student[] studnets = {new Student("Alice"),
                new Student("Bob"),
                new Student("Alen")};
        Student me = new Student("bob");
        int res3 = LinearSearch.search(studnets, me);
        System.out.println(res3);
    }

    public static void main(String[] args) {
        int[] dataSize = {1000000, 10000000};
        for (int n : dataSize) {
            Integer[] data = ArrayGenerator.generateOrderedArray(n);//泛型要使用包装类
            long startTime = System.nanoTime();
            for (int k = 0; k < 100; k++)
                LinearSearch.search(data, n);
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("n=" + n + ",100 runs:" + time + "s");
        }
    }
}
