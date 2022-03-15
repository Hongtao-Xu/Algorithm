package com.cn.advance.Stage1.DataStruct.Queue;

/*
 * 链表实现队列
 */
public class LinkedListQueue<E> implements Queue<E> {
    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //入队：链表尾
    @Override
    public void enqueue(E e) {
        if (tail == null) {//空链表
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    //出队：链表头
    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        Node target = head;
        head = head.next;
        target.next = null;
        //GC回收
        target.next = null;
        if (head == null)//只有一个元素，出对后为0
            tail = null;
        size--;
        return (E) target.e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return (E) head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");
        Node target = head;
        while (target != null) {
            res.append(target + "->");
            target = target.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

}
