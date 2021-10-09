package com.cn.mk.utils;

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
     *
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
}
