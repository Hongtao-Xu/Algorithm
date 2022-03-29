package com.cn.advance.Stage4.CountingSort;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/3/29  16:25
 */
public class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("Student(name: %s,score: %d)", name, score);
    }
}
