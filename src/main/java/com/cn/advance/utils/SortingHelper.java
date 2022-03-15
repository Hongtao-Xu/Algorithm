package com.cn.advance.utils;

import java.lang.reflect.Method;

/*
 *@program:排序算法辅助器
 *@author: xu-hongtao
 *@Time: 2021/9/9  9:40
 */
public class SortingHelper {
    private SortingHelper() {
    }

    /**
     * @param <E> 泛型
     * @param arr 排序好的数组
     * @return 判断数组是否是升序
     */
    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i - 1].compareTo(arr[i]) > 0)
                return false;
        return true;
    }

    public static <E extends Comparable<E>> void sortTest(Class<?> Algorithm, E[] arr) throws Exception {
        long startTime = System.nanoTime();
// ====================很重要 =================
//        Method[] m = Algorithm.getDeclaredMethods();
//        for (Method temp : m){
//            System.out.println(temp.toString());
//        }
        String name = Algorithm.getSimpleName();
        Method sort = Algorithm.getMethod("sort", Comparable[].class);
        sort.invoke(null,(Object) arr);
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        if (!SortingHelper.isSorted(arr)) {
            throw new RuntimeException("algorithm Sort failed");
        }
        System.out.println(String.format("%s , n=%d : %f s", name, arr.length, time));
    }

}

