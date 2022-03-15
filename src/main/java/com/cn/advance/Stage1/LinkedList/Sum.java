package com.cn.advance.Stage1.LinkedList;

/*
 * 递归
 */
public class Sum {
    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    // 计算arr[l...n)这个区间内所有数字的和
    private static int sum(int[] arr, int l) {
        if (arr.length == l)
            return 0;
        return arr[l] + sum(arr, l + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(sum(arr));
    }


}
