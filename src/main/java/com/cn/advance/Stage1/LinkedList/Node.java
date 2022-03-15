package com.cn.advance.Stage1.LinkedList;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/12/4  22:33
 */
public class Node<E> {
    public E e;
    public Node next;

    public Node(E e) {
        this.e = e;
    }

    public Node(E e, Node next) {
        this.e = e;
        this.next = next;
    }

    //打印Node节点内容，就会打印e的值
    @Override
    public String toString() {
        return e.toString();
    }
}
