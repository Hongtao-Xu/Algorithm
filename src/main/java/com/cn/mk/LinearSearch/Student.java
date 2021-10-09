package com.cn.mk.LinearSearch;

/*
 *@program:自己定义的对象
 *@author: xu-hongtao
 *@Time: 2021/9/8  11:59
 */
public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object student){
        //提前判断
        if(this==student)
            return true;
        if(student==null)
            return false;
        if (this.getClass()!=student.getClass())
            return false;

        Student another = (Student) student;
//        return this.name.equals(((Student) student).name);//比较学生的name属性
        return this.name.toLowerCase().equals(((Student) student).name.toLowerCase());//比较学生的name属性,忽略大小写
    }
}
