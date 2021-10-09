package com.cn.base;

import org.junit.Test;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/6/5  23:01
 */
public class TestDemo02 extends Demo02{

    @Test
    public void TestlengthOfLongestSubstring() {
        String s = "abcabc";
        int result = lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    @Test
    public void TestfindMedianSortedArrays() {
        int[] nums1= new int[]{1,3, 5,8,9};

        int[] nums2= new int[]{2,3,7};
        double result = findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }
    @Test
    public void TestfindMedianSortedArrays1() {
        int[] nums1= new int[]{1,3, 5,8,9};
        int[] nums2= new int[]{2,3,7};
        double result = findMedianSortedArrays1(nums1, nums2);
        System.out.println(result);
    }


}
