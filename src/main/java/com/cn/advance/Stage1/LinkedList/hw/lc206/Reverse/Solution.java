package com.cn.advance.Stage1.LinkedList.hw.lc206.Reverse;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/12/8  23:46
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode rev = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return rev;
    }
}
