package com.cn.advance.Stage3.PQueue;

/*
  队列接口
 */
public interface Queue <E>{
    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
