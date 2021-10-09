package com.cn.base;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/6/17  16:59
 */
public class Demo05 {

    public boolean isValid(String s) {
        int n = s.length();
        //字符为奇数个，一定不满足条件
        if (n % 2 == 1) {
            return false;
        }

        //Character 类用于对单个字符进行操作
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        //栈
        Deque<Character> stack = new LinkedList<Character>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {//判断字符是否为  )]}
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {//栈为空，或栈顶与ch匹配不相等
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();//目标为栈空
    }

    public int MaxArea(int[] height){
        int l =0;//左指针
        int r = height.length-1;//右指针
        int ans = 0;
        while (l<r){
            int area = Math.min(height[r],height[l])*(r - l);
            ans = Math.max(ans, area);
            if(height[l]<=height[r]){
                ++l;
            }else {
                --r;
            }
        }
        return ans;
    }
}
