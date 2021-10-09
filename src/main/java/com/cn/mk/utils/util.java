package com.cn.mk.utils;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/9/9  20:06
 */

public class util {

    private util() {
    }

    /**
     *
     * @param arr 传入数组
     * @param i 交换位置i
     * @param j 交换位置j
     * @param <E> 泛型
     */
    public static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
