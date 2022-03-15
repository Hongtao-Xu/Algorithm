package com.cn.advance.utils;

import java.util.Random;

/*
 *@program:数组生成器
 *@author: xu-hongtao
 *@Time: 2021/9/8  20:03
 */
public class ArrayGenerator {

    private ArrayGenerator() {
    }

    /**
     * @param n 生成数组的长度
     * @return 返回顺序数组
     */
    public static Integer[] generateOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    /**
     * @param n     生成数组的长度
     * @param bound 每个元素的范围:[0,bound)
     * @return 返回生成的数组
     */
    public static Integer[] generateRandomArray(int n, int bound) {
        Integer[] arr = new Integer[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++)
            arr[i] = rnd.nextInt(bound);
        return arr;
    }

    /**
     * 针对以中间点为标定点的快速排序，生成一个特殊的测试用例
     * 使得这样的快速排序产生退化
     *
     * @param n
     * @return 特殊的数组
     */
    public static Integer[] generateSpecialArray(int n) {
        Integer[] arr = new Integer[n];
        generateSpecialArray(arr, 0, arr.length - 1, 0);
        return arr;
    }

    //[8 2 6 4 0 1 3 5 7 9]
    //0 [2 6 4 8 1 3 5 7 9]
    //0 1 [6 4 8 2 3 5 7 9]
    //0 1 2 [4 8 6 3 5 7 9]
    //0 1 2 3 [8 6 4 5 7 9]
    //0 1 2 3 4 [6 8 5 7 9]
    //0 1 2 3 4 5 [8 6 7 9]
    //0 1 2 3 4 5 6 [8 7 9]
    //0 1 2 3 4 5 6 7 [8 9]
    //0 1 2 3 4 5 6 7 8 [9]
    private static void generateSpecialArray(Integer[] arr, int l, int r, int value) {
        if (l > r) return;
        int mid = l + (r - l) / 2;
        //1.把最小值放到中间；
                                                                                                                                 arr[mid] = value;
        //2.模拟partition过程，把中间元素和最左边元素交换位置；
        swap(arr, l, mid);
        //3.处理除了最左边的元素之外，剩下的n-1个元素
        generateSpecialArray(arr, l + 1, r, value + 1);
        //4.还要把中间的元素和最左边的元素交换回来。
        swap(arr, l, mid);
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static <E> void print(E[] arr){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i] + " ");
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args) {
        Integer[] arr = ArrayGenerator.generateSpecialArray(5);
    }
}


