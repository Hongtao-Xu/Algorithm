package com.cn.base;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/6/5  22:59
 */
public class Demo02 {

    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;

        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    //中位数：解法1
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums;
        int m = nums1.length;
        int n = nums2.length;
        nums = new int[m + n];
        if (m == 0) {//nums1为空
            if(n%2==0){//nums2为偶数
                return (nums2[n/2-1]+nums2[n/2]) / 2.0;
            }
        }
        if (n == 0) {//nums2为空
            if (m % 2 == 0) {//nums1为偶数
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            } else {
                return nums1[m / 2];
            }
        }
        int count = 0;
        int i =0;
        int j = 0;
        while (count!=(m+n)) {//num1+num2总长度
            if (i==m) {
                while (j!=n) {//i指针到末尾时，j指针还指向数字，此循环把nums2剩下所有的数放入nums
                    nums[count++] = nums2[j++];
                }
                break;//直接跳出大循环
            }
            if (j == n) {
                while (i != m) {
                    nums[count++] = nums1[i++];
                }
                break;
            }
            if(nums1[i]<nums2[j]){
                nums[count++]=nums1[i++];
            }else {
                nums[count++]=nums2[j++];
            }
        }
        //合并之后的数组求中位数
        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        } else {
            return nums[count / 2];
        }
    }
    //中位数：解法2
    public double findMedianSortedArrays1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        //起始的值
        int left = -1, right = -1;
        //起始指针位置
        int aStart = 0, bStart = 0;

        for (int i = 0; i <= len / 2; i++) {
            left = right;
            //if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
            if((aStart< m && bStart >= n)||(A[aStart]<B[bStart])){
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }
}
