package com.cn.LeetCode;

/*
 *70. 爬楼梯
 *https://leetcode-cn.com/problems/climbing-stairs/
 */
public class LC70 {
    //1.动态规划
    class Solution {
        public int climbStairs(int n) {
            int[] dp = new int[n + 1];
            dp[0]=1;//第0级，1种走法
            dp[1]=1;//第1级，1种走法
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }
    //2.动态规划，滚动数组
    class Solution1 {
        public int climbStairs(int n) {
           int p=0,q=0,r=1;
            for (int i = 1; i <= n; i++) {
                p=q;
                q=r;
                r=q+p;
            }
            return r;
        }
    }
    //3.数学通式
    class Solution2 {
        public int climbStairs(int n) {
            double sqrt5 = Math.sqrt(5);
            //Math.pow(a,b) a^b
            double finb = Math.pow((1+sqrt5)/2,n+1)-Math.pow((1-sqrt5)/2,n+1);
            //Math.round: 返回最接近参数的整数
            return (int) Math.round(finb / sqrt5);
        }
    }


}
