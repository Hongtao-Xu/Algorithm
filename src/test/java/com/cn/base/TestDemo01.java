package com.cn.base;

import org.junit.Test;
import com.cn.base.Demo01;

import java.util.Arrays;

/*
 *@program:基础算法题01测试类
 *@author: xu-hongtao
 *@Time: 2021/5/30  22:16
 */
public class TestDemo01 extends Demo01{

    @Test
    public void towSum2(){
        int[] input = new int[]{2,7,11,15};
        int[] result = twoSum(input, 9);
        System.out.println("["+result[0]+","+result[1]+"]");
    }

    @Test
    public void twoSum() {
        int[] input = new int[]{2,7,11,15};
        int[] result = twoSum(input, 9);
        System.out.println("["+result[0]+","+result[1]+"]");
    }

}
