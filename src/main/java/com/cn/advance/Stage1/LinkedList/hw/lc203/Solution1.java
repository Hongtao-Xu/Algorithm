package com.cn.advance.Stage1.LinkedList.hw.lc203;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/12/5  17:09
 */
public class Solution1 {
    public ListNode removeElements(ListNode head, int val) {
        //处理头节点
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }
        //如果此时所有节点都删除完了
        if (head == null)
            return head;
        //处理中间节点
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution1()).removeElements(head, 6);
        System.out.println(res);
    }
}
