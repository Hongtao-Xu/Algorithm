package com.cn.advance.Stage1.LinkedList.hw.lc206.linkList;

/*
 * 非递归实现
 * 三指针法
 */
public class Solution {
    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next; //避免空指针异常
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode rev = reverseList(head.next);
        //宏观分析：
        head.next.next = head;//通过未翻转的头，拿到头的下一个，指向都
        head.next = null;//斩断原来的1->2
        return rev;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).reverseList(head);
        System.out.println(res);
    }
}
