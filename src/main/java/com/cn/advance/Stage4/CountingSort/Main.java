package com.cn.advance.Stage4.CountingSort;

import java.util.Random;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/3/29  16:27
 */
public class Main {
    public static void main(String[] args) {
        int n = 26 * 26 * 26 * 26;
        Student[] students = new Student[n];
        int k = 0;
        Random rnd = new Random();
        for (char c1 = 'a'; c1 <= 'z'; c1++)
            for (char c2 = 'a'; c2 <= 'z'; c2++)
                for (char c3 = 'a'; c3 <= 'z'; c3++)
                    for (char c4 = 'a'; c4 <= 'z'; c4++) {
                        students[k] = new Student("" + c1 + c2 + c3 + c4, rnd.nextInt(101));
                        k++;
                    }
        //计数排序过程
        int R = 101;
        //O(n)
        int[] cnt = new int[R];
        for (Student student : students)
            cnt[student.getScore()]++;

        //O(R)
        int[] index = new int[R + 1];
        for (int i = 0; i < R; i++)
            index[i + 1] = index[i] + cnt[i];

        //辅助空间 O(n)
        Student[] temp = new Student[n];
        for (Student student : students) {
            temp[index[student.getScore()]] = student;
            index[student.getScore()]++;
        }
        //处理返回值 O(n)
        for (int i = 0; i < n; i++)
            students[i] = temp[i];

        //验证
        for (int i = 1; i < n; i++) {
            //有序性
            if (students[i - 1].getScore() > students[i].getScore())
                throw new RuntimeException("Sort Failed");
            //稳定性
            if (students[i - 1].getScore() == students[i].getScore()) {
                if (students[i - 1].getName().compareTo(students[i].getName()) >0)
                    throw new RuntimeException("No Stable");
            }
        }
    }
}
