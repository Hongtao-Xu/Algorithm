package com.cn.advance.Stage1.LinkedList;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/12/4  22:35
 */
public class LinkedList<E> {
    private Node dummyHead;
    private int size;

    public LinkedList() {
        this.dummyHead = new Node(null, null);
        this.size = 0;
    }

    // 获取链表中的元素个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表的index位置添加新的元素e
    public void add(int index, E e) {
        //对index做判断
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        //关键：找到前一个节点
        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;
        //添加
        Node node = new Node(e, prev.next);
        prev.next = node;
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

    // 获得链表的第index个位置的元素
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node target = dummyHead.next;
        for (int i = 0; i < index; i++)
            target = target.next;
        return (E) target.e;
    }

    // 获得链表的第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    // 修改链表的第index个位置的元素为e
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node target = dummyHead.next;
        for (int i = 0; i < index; i++)
            target = target.next;
        target.e = e;
    }

    // 查找链表中是否有元素e
    public boolean contains(E e) {
        Node target = dummyHead.next;
        while (target != null) {
            if (target.e.equals(e))
                return true;
            target = target.next;
        }
        return false;
    }

    // 从链表中删除index位置的元素, 返回删除的元素
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node prev = dummyHead;

        for (int i = 0; i < index; i++)
            prev = prev.next;

        Node target = prev.next;
        prev.next = target.next;
        //gc回收
        target.next = null;
        size--;
        return (E) target.e;
    }

    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从链表中删除元素e
    public void removeElement(E e) {

        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e))//找到要删除元素，退出while循环
                break;
            prev = prev.next;
        }
        Node target = prev.next;
        prev.next = target.next;
        target.next = null;
        size--;
    }

    //遍历节点
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node target = dummyHead.next;
        while (target != null) {
            res.append(target + "->");
            target = target.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
