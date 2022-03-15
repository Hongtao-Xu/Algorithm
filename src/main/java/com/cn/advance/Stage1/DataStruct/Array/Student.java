package com.cn.advance.Stage1.DataStruct.Array;



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

//      Student another = (Student) student;
        return this.name.equals(((Student) student).name);//比较学生的name属性
//      return this.name.toLowerCase().equals(((Student) student).name.toLowerCase());//比较学生的name属性,忽略大小写
    }


    @Override
    public int compareTo(Student another) {
        return this.score - another.score;
    }

    @Override
    public String toString() {
        return String.format("Student(name:%s,score:%d)", name, score);
    }

    public static void main(String[] args) {
        MyArray<Student> arr = new MyArray<Student>(20);
        arr.addLast(new Student("Alice",100));
        arr.addLast(new Student("Bob",56));
        arr.addLast(new Student("Jack",89));
        System.out.println(arr);
    }
}
