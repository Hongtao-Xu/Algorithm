package com.cn.advance.Stage1.LinkedList.hw.lc206.linkList;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/12/8  23:04
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    // 链表节点的构造函数
    // 使用arr为参数，创建一个链表，当前的ListNode为链表头结点
    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("arr can not be empty");
        this.val = arr[0];
        ListNode cur = this;//当前节点
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
