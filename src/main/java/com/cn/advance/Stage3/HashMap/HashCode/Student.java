package com.cn.advance.Stage3.HashMap.HashCode;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/3/28  17:03
 */
public class Student {
    int grade;
    int cls;
    String firstName;
    String lastName;

    public Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int B = 31;
        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.toLowerCase().hashCode();//不区分大小写
        hash = hash * B + lastName.toLowerCase().hashCode();

        return hash;
    }

    @Override
    //Object中的方法，如果两个对象的hashCode产生冲突时，就要比较这两个类的具体的值
    public boolean equals(Object obj) {
        if (this == obj) return true; //是否是同一个对象
        if (obj == null) return false;//是否是空对象
        if (getClass() != obj.getClass()) return false;//是否是同一类对象
        Student another = (Student) obj;
        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
    }
}

