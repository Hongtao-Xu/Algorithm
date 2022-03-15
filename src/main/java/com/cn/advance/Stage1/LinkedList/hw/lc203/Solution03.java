package com.cn.advance.Stage1.LinkedList.hw.lc203;

/*
 * 递归解题v2
 */
public class Solution03 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return head;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution3()).removeElements(head, 2);
        System.out.println(res);
    }

}
