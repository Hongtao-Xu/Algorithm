package com.cn.LeetCode;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 239. 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class LC239 {

    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] pair1, int[] pair2) {
                    return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
                }
            });
            //处理第一个滑动窗口
            for (int i = 0; i < k; i++) {
                pq.offer(new int[]{nums[i], i});
            }
            //一共有n-k+1个滑动窗口
            int[] res = new int[n - k + 1];
            res[0] = pq.peek()[0];
            for (int i = k; i < n; i++) {
                pq.offer(new int[]{nums[i], i});
                /*
                   如果最大值在窗口中，pq.peek()[1] <= i - k 不满足
                 */
                while (pq.peek()[1] <= i - k) {
                    pq.poll();
                }
                res[i - k + 1] = pq.peek()[0];
            }
            return res;
        }
    }
}
