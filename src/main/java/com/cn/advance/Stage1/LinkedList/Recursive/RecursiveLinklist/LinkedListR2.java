package com.cn.advance.Stage1.LinkedList.Recursive.RecursiveLinklist;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/12/7  21:51
 */
public class LinkedListR2<E>  {

    private class Node{
        E data;		//数据域
        Node next;	//指针域
        public Node(){
            this(null,null);
        }
        public Node(E data,Node next){
            this.data=data;
            this.next=next;
        }
        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node head;	//指向虚拟头结点的头指针
    private Node rear;	//指向尾结点的尾指针
    private int size;	//记录元素的个数

    public LinkedListR2() {
        head=new Node();
        rear=head;
        size=0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return
                size==0&&head.next==null;
    }
    // 在以head为头结点的链表的index位置插入元素e，递归算法,返回链表的头结点
    private Node add(Node head, int index, E e) {
        if (index == 0)
            return new Node(e, head);

        head.next = add(head.next, index - 1, e);
        return head;
    }
    // 在链表的index(0-based)位置添加新的元素e
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        head = add(head, index, e);
        size++;
    }
    // 在链表头添加新的元素e
    public void addFirst(E e) {
        add(0, e);
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e) {
        add(size, e);
    }


}
