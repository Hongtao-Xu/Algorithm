package com.cn.base;

import org.junit.Test;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/6/16  23:43
 */
public class TestDemo04 extends Demo04{
    @Test
    public void test1 (){
        boolean result = isMatch("AAABCAAB", "A*B.A*B");
        System.out.println(result);
    }
}
