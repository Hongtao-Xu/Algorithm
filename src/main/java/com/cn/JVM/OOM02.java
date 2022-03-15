package com.cn.JVM;

import java.util.ArrayList;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/10/22  20:21
 */
public class OOM02 {
    byte[] array = new byte[1 * 1024 * 1024];//1m

    public static void main(String[] args) {
        ArrayList<OOM02> list = new ArrayList<>();
        int count = 0;

        try {
            while (true) {
                list.add(new OOM02());//问题所在
                count = count + 1;
            }
        } catch (Error e) {
            System.out.println("count" + count);
            e.printStackTrace();
        }

    }
}
