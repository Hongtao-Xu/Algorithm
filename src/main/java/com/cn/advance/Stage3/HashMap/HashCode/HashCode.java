package com.cn.advance.Stage3.HashMap.HashCode;

import java.util.HashMap;
import java.util.HashSet;

/*
 * Java中的hashCode方法
 *
 */
public class HashCode {
    public static void main(String[] args) {
        int a = 42;
        System.out.println(((Integer) a).hashCode());//42
        int b = -42;
        System.out.println(((Integer) b).hashCode());//-42
        double c = 3.1415926;
        System.out.println(((Double) c).hashCode());//219937201
        String d = "xht";
        System.out.println(d.hashCode());//118660

        Student student = new Student(3, 2, "xht1", "liu");
        System.out.println(student.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);

        //默认的：java.lang.Object.hashCode是根据两个对象的地址生成hash值
        Student student2 = new Student(3, 2, "xht1", "liu");
        System.out.println(student2.hashCode());
    }
}
