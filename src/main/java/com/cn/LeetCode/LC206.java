package com.cn.LeetCode;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/6/14  23:15
 */
public class LC206 {


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //1.迭代
    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;

            while (curr != null) {
                //存储下一个节点，方便待会移动prev和curr指针
                ListNode next = curr.next;
                //翻转
                curr.next = prev;
                //移动prev和curr指针
                prev = curr;
                curr = next;
            }
            return prev;
        }
    }

    //2.递归
    class Solution1 {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null)
                return head;
            ListNode newNode = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return newNode;
        }
    }
}
