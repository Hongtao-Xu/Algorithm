package com.cn.advance.Stage1.LinkedList.Recursive.StanfordProblems;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/12/9  18:10
 */
public class Node {
    public int val;
    public Node next;

    public Node(int x) {
        val = x;
    }

    // 链表节点的构造函数
    // 使用arr为参数，创建一个链表，当前的ListNode为链表头结点
    public Node(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("arr can not be empty");

        this.val = arr[0];
        Node cur = this;//当前节点
        for (int i = 1; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
            cur = cur.next;
        }
    }

    // 以当前节点为头结点的链表信息字符串
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = this;
        while (cur != null) {
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
