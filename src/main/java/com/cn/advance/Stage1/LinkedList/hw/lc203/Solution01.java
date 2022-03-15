package com.cn.advance.Stage1.LinkedList.hw.lc203;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/12/5  17:20
 */
public class Solution01 {
    public ListNode removeElements(ListNode head, int val) {
        //处理头节点
        while (head != null && head.val == val) {
            head = head.next;
        }
        //如果此时所有节点都删除完了
        if (head == null)
            return head;
        //处理中间节点
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }
}
