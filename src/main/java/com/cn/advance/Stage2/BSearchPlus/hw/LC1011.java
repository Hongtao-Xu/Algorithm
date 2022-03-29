package com.cn.advance.Stage2.BSearchPlus.hw;

import java.util.Arrays;

/*
 *https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
 *1011. 在 D 天内送达包裹的能力
 * 注意：
 * 在 days 天内将传送带上的所有包裹送达的船的最低运载能力
 * 我们装载的重量不会超过船的最大运载重量
 */
public class LC1011 {
    //<=target的最小值
    public static int shipWithinDays(int[] weights, int days) {

        //int l = 1;//有bug,比如，有8吨或，运力只有7，就会有余数
        int l = Arrays.stream(weights).max().getAsInt();
        int r = Arrays.stream(weights).sum();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (days(weights, mid) <= days)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    private static int days(int[] weights, int k) {
        int cur = 0, res = 0;
        for (int weight : weights) {
            if (cur + weight <= k) {//未超过载重量
                cur += weight;
            } else {//超过载重量，船再开始新的一趟装货
                res++;
                cur = weight;
            }
        }
        res++;
        return res;
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 1, 1};
        int days=4;
        int res = LC1011.shipWithinDays(weights, days);
        System.out.println(res);
    }
}
/*
 * 3,2,2,4,1,4   days = 3  输出：6
 * l=1,r=16
 * mid=8; days=3,满足
 * l=1,r=8
 * mid=4; days=5,不满足
 * l=5,r=8
 * mid=6; days=3,满足
 * l=5,r=6;
 * mid=5; days=4,不满足
 * l=6,r=6
 * mid=6
 */
