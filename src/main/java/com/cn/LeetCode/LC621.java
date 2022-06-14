package com.cn.LeetCode;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/4/17  23:08
 */
public class LC621 {
    class Solution {
        public int leastInterval(char[] tasks, int n) {
            int[] arr = new int[26];
            for (char c : tasks) {
                arr[c - 'A']++;
            }
            int max=0;
            for (int i = 0; i < 26; i++) {
                max = Math.max(max, arr[i]);
            }
            int ret = (max - 1) * (n + 1);
            //第max行剩余的任务
            for (int i = 0; i < 26; i++) {
                if (arr[i] == max) {
                    ret++;
                }
            }
            return Math.max(ret, tasks.length);
        }
    }
}
