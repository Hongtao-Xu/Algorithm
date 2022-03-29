package com.cn.CS.ThreeSum;

import java.util.Random;

/*
 * 测试ThreeSum
 */
public class Test {
    public static void main(String[] args) {
        //test_1();
        //test_2();
        test_3();
    }

    private static void test_3() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSumTwoPointer threeSumTwoPointer = new ThreeSumTwoPointer();
        int count = threeSumTwoPointer.count(nums);
        System.out.println(count);
    }

    private static void test_2() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSumBinarySearch threeSumBinarySearch = new ThreeSumBinarySearch();
        int count = threeSumBinarySearch.count(nums);
        System.out.println(count);
    }

    private static void test_1() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSumSlow threeSumSlow = new ThreeSumSlow();
        int count = threeSumSlow.count(nums);
        System.out.println(count);
    }
}
