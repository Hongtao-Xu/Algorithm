package com.cn.mk.SelectionSort;

import com.cn.mk.utils.ArrayGenerator;
import com.cn.mk.utils.SortingHelper;
import com.cn.mk.utils.util;

/*
 *@program:选择排序
 *@author: xu-hongtao
 *@Time: 2021/9/8  20:31
 */
public class SelectionSort {
    private SelectionSort() {
    }

    /*
     * arr[0...i)已排序，arr[i..n)未排序
     * */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) //循环不变量 arr[i..n)
                    minIndex = j;
            }
            util.swap(arr, i, minIndex);
        }
    }

    /*
     * arr[i..n)已排序，arr[0...i)未排序
     * */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int maxIndex = i;
            for (int j = i; j >= 0; j--) {
                if (arr[j].compareTo(arr[maxIndex]) > 0) //循环不变量 arr[i..n)
                    maxIndex = j;
            }
            util.swap(arr, i, maxIndex);
        }
    }


    public static void main1(String[] args) {
        Integer[] arr = {1, 4, 2, 3, 5, 6};
        sort(arr);
        for (int e : arr)
            System.out.print(e + " ");
        System.out.println("\n");
        Student[] students = {
                new Student("Alice", 34),
                new Student("Bobb", 56),
                new Student("Sky", 98),
        };
        sort(students);
        for (Student stu : students)
            System.out.println(stu + " ");
    }

    //时间复杂度分析
    public static void main(String[] args) throws Exception {
        int[] dataSize = {10000, 100000};
        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest(SelectionSort.class, arr);
        }
    }

}
