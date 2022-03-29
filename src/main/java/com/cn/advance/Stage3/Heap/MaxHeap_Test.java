package com.cn.advance.Stage3.Heap;

import java.util.Arrays;
import java.util.Random;

/*
 * 测试最大堆
 */
public class MaxHeap_Test {
    public static void main(String[] args) {
        //test_1();
        test_2();
    }

    //测试heapify的性能
    private static void test_2() {
        int n = 1000000;
        Random random = new Random();

        Integer[] testData1 = new Integer[n];
        for (int i = 0; i < n; i++)
            testData1[i] = random.nextInt(Integer.MAX_VALUE);
        Integer[] testData2 = Arrays.copyOf(testData1, n);
        double time1 = testHeap(testData1, false);
        System.out.println("Without heapify: " + time1 + " s");

        double time2 = testHeap(testData2, true);
        System.out.println("With heapify: " + time2 + " s");
    }

    //测试最大堆基本功能
    private static void test_1() {
        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = maxHeap.extractMax();
        for (int i = 1; i < n; i++)
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MaxHeap completed");
    }

    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        //构造最大堆：两种方法
        if (isHeapify)
            maxHeap = new MaxHeap<>(testData);
        else {
            maxHeap = new MaxHeap<>(testData.length);
            for (int num : testData)
                maxHeap.add(num);
        }
        //从最大堆中取出最大值，形成有序数组
        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++)
            arr[i] = maxHeap.extractMax();
        //对数组是否有序验证
        for (int i = 1; i < testData.length; i++)
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
