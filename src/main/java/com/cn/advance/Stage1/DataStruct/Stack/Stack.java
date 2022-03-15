package com.cn.advance.Stage1.DataStruct.Stack;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/10/11  20:02
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
