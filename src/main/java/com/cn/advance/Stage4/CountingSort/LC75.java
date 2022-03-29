package com.cn.advance.Stage4.CountingSort;

/*
 * 75. 颜色分类
 * https://leetcode-cn.com/problems/sort-colors/
 */
public class LC75 {
    //1.计数法
    class Solution {
        public void sortColors(int[] nums) {
            //不需要排序
            int[] cnt = new int[3];
            for (int num : nums)
                cnt[num]++;

            for (int i = 0; i < cnt[0]; i++)
                nums[i] = 0;
            for (int i = cnt[0]; i < cnt[0] + cnt[1]; i++)
                nums[i] = 1;
            for (int i = cnt[0] + cnt[1]; i < cnt[0] + cnt[1] + cnt[2]; i++)
                nums[i] = 2;
        }
    }

    //2.
    class Solution2 {
        public void sortColors(int[] nums) {
            //处理元素：[0,R)的计数排序
            int R = 3;

            int[] cnt = new int[R];
            for (int num : nums)
                cnt[num]++;

            //[index[i],index[i+1]]的值为i
            int[] index = new int[R + 1];
            for (int i = 0; i < R; i++)
                index[i + 1] = index[i] + cnt[i];
            //index:0,0+cnt[0],..
            //index:0 ,2 ,5 ,7,9

            //处理返回值
            for (int i = 0; i + 1 < index.length; i++)
                for (int j = index[i]; j < index[i + 1]; j++)
                    nums[j] = i;
        }
    }
}
