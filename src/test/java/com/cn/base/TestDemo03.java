package com.cn.base;

import org.junit.Test;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/6/15  12:02
 */
public class TestDemo03 extends Demo03{

    @Test
    public void Tessthuiwen2(){
        String s = "babad";
        String result = huiwen2(s);
        System.out.println(result);
    }

    @Test
    public void Tessthuiwen(){
        String s = "cbbd";
        String result = huiwen(s);
        System.out.println(result);
    }

    @Test
    public void Tessthuiwen3(){
        String s = "cb";
        String result = huiwen3(s);
        System.out.println(result);
    }



}
