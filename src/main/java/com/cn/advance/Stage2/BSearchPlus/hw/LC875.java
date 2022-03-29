package com.cn.advance.Stage2.BSearchPlus.hw;

import java.util.Arrays;

/*
 * https://leetcode-cn.com/problems/koko-eating-bananas/
 * 875. 爱吃香蕉的珂珂
 */
public class LC875 {

    //在 H 小时内吃掉所有香蕉的最小速度
    public int minEatingSpeed(int[] piles, int h) {
        //香蕉个数[1,max]
        int l = 1, r = Arrays.stream(piles).max().getAsInt();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (eatingTime(piles, mid) <= h)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    private int eatingTime(int[] piles, int k) {
        int res = 0;
        for (int pile : piles) {
            res += pile / k + (pile % k > 0 ? 1 : 0);
        }
        return res;
    }
}
/* [30,11,23,4,20], H = 5
 * r=1,l=30
 * mid=15:2+1+2+1+2=8,不满足
 * r=16,l=30
 * mid=23:2+1+1+1+1=6,不满足
 * r=24,l=30
 * mid=27:2+1+1+1+1=6,不满足
 * r=28,l=30
 * mid=29:2+1+1+1+1=6,不满足
 * r=30,l=30
 * mid=30:1+1+1+1+1=5,满足
 */
