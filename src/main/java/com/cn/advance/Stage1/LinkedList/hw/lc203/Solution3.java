package com.cn.advance.Stage1.LinkedList.hw.lc203;

/*
 * 递归解题v1
 */
public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        if (head==null)
            return head;
        ListNode res = removeElements(head.next, val);//分解到基本问题

        if (head.val == val) {//从基本问题往上推
            return res;//满足条件，就直接返回res，不添加这个头节点head(相当于删除了符合条件的节点)
        } else {
            head.next = res;//不满足条件，添加这个头节点head
            return head;//返回添加头结点后的链表，继续往上推
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution3()).removeElements(head, 1);
        System.out.println(res);
    }
}
