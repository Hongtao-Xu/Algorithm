package com.cn.advance.Stage1.DataStruct.Array;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/10/10  0:18
 */

public class Main {
    public static void main(String[] args) {

        MyArray arr = new MyArray();
//        for (int i = 0; i < 10; i++)
        for (int i = 0; i < 10; i++)//使用封装类。自动装箱，拆箱
            arr.addLast(i);
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        arr.removeLast();
        System.out.println(arr);
    }
}
