package com.cn.LeetCode;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/5/1  21:00
 */
public class LC415 {

    //1.两数相加
    class Solution {
        public String addStrings(String num1, String num2) {
            //i:num1的指针，j:num2的指针，add:进位值
            int i=num1.length()-1,j=num2.length()-1,add=0;
            StringBuffer sb = new StringBuffer();
            while (i>=0||j>=0||add!=0) {
                int x = i >= 0 ? num1.charAt(i) - '0' : 0;
                int y = j >= 0 ? num2.charAt(j) - '0' : 0;
                int result = x+y+add;
                sb.append(result % 10);
                add=result/10;
                i--;j--;
            }
            //计算结果要翻转
            sb.reverse();
            return sb.toString();
        }
    }
}
