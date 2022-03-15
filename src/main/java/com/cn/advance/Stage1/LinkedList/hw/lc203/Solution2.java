package com.cn.advance.Stage1.LinkedList.hw.lc203;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/12/5  17:23
 */
public class Solution2 {
    public ListNode removeElements(ListNode head, int val) {
        //虚拟头节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        //处理中间节点
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution2()).removeElements(head, 6);
        System.out.println(res);
    }

}
