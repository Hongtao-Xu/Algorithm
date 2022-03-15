package com.cn.advance.Stage1.DataStruct.Queue;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/10/12  20:52
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
