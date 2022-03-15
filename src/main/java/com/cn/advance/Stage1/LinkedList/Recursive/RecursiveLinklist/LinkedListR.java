package com.cn.advance.Stage1.LinkedList.Recursive.RecursiveLinklist;


/// 递归实现的LinkedList
/// 类名称中LinkedListR里的R，是Recursion的意思，表示递归实现：
public class LinkedListR<E> {
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 在链表的递归实现中，我们不使用虚拟头结点，也能无差异的处理位置0的问题：
    private Node head;
    private int size;

    public LinkedListR() {
        head = null;
        size = 0;
    }

    // 获取链表中的元素个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
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

    // 在以head为头结点的链表中，找到第index个元素，递归算法
    private E get(Node head, int index) {
        if (index == 0)
            return head.e;
        E e = get(head.next, index - 1);
        return e;
    }

    // 获得链表的第index(0-based)个位置的元素
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");
        return get(head, index);
    }

    // 获得链表的第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    // 修改以head为头结点的链表中，第index(0-based)个位置的元素为e，递归算法
    private void set(Node head, int index, E e) {
        if (index == 0) {
            head.e = e;
            return;//不能省略，结束递归
        }
        set(head.next, index - 1, e);
    }

    // 在以head为头结点的链表中，查找是否存在元素e，递归算法
    private boolean contains(Node head, E e) {
        if (head == null)
            return false;
        if (head.e.equals(e))//只要有一个是满足条件的，就结束递归
            return true;
        return contains(head.next, e);
    }

    // 查找链表中是否有元素e
    public boolean contains(E e) {
        return contains(head, e);
    }

    int count = 0;
    // 从以head为头结点的链表中，删除第index位置的元素，递归算法
    private Node remove(Node head, int index) {
        if (head == null){//删除的是空链表
            return head;
        }
        count++;
        Node res = remove(head.next, index);

        if (count-index==1) {//满足
            size--;
            count--;
            return res;
        } else {//不满足
            head.next =res;
            count--;
            return head;

        }
    }

    // 从链表中删除index(0-based)位置的元素, 返回删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
//        E removed = get(head, index);
//        if (index==0) {
//            head = removeHead(head);
//        }else {
//            remove(this.head, index);
//        }
//        return removed;
        E removed = get(head, index);
        head = remove(head, index);//java不是C++,此处操作的不是值，而是引用，必须要加上
        return removed;
    }

    private Node removeHead(Node head) {
        if (head != null) {
            Node delNode = head;
            head = head.next;
            delNode.next = null;
        }
        return head;
    }
    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从以head为头结点的链表中，删除所有元素e，递归算法
    private Node removeElement(Node head, E e) {
        if (head == null)//一个符合条件的元素都没有
            return head;

        Node res = removeElement(head.next, e);//分解到基本问题
        if (head.e.equals(e)) {//满足条件
            size--;
            return res;
        } else {
            head.next = res;//不满足条件，添加这个头节点head
            return head;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }


}
