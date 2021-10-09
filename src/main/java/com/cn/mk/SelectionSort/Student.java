package com.cn.mk.SelectionSort;

/*
 *@program:自己定义的对象
 *@author: xu-hongtao
 *@Time: 2021/9/8  11:59
 */
public class Student implements Comparable<Student> {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public boolean equals(Object student) {
        //提前判断
        if (this == student)
            return true;
        if (student == null)
            return false;
        if (this.getClass() != student.getClass())
            return false;

        Student another = (Student) student;
        return this.name.equals(((Student) student).name);//比较学生的name属性
//        return this.name.toLowerCase().equals(((Student) student).name.toLowerCase());//比较学生的name属性,忽略大小写
    }


    @Override
    public int compareTo(Student another) {
//        if(this.score< another.score)
//            return -1;
//        else if(this.score==another.score)
//            return 0;
//        return 1;
        return this.score - another.score;
    }

    @Override
    public String toString() {
        return String.format("Student(name:%s,score:%d)", name, score);
    }
}
