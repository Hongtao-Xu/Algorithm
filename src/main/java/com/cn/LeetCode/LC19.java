package com.cn.LeetCode;

import java.util.List;
import java.util.Stack;

/*
 * 19. 删除链表的倒数第 N 个结点
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 */
public class LC19 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //1.计算链表长度
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummyHead = new ListNode(0, head);
            int length = getLength(dummyHead.next);
            ListNode cur = dummyHead;
            for (int i =0;i<=length-n;i++)
                cur = cur.next;
            //删除节点
            cur.next = cur.next.next;
            return dummyHead.next;
        }

        private int getLength(ListNode node) {
            int length = 0;
            while (node.next != null) {
                length++;
                node = node.next;
            }
            return length;
        }
    }

    //2.栈
    class Solution2 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummyHead = new ListNode(0, head);
            Stack<ListNode> stack = new Stack<>();
            ListNode cur = dummyHead;
            while (cur != null) {
                stack.push(cur);
                cur = cur.next;
            }
            for(int i=0;i<n;i++)
                stack.pop();
            //当前栈顶的元素是待删除元素的前驱节点
            ListNode pre = stack.peek();
            pre.next = pre.next.next;
            return dummyHead.next;
        }
    }

    //3.快慢指针
    class Solution3 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummyHead = new ListNode(0,head);
            ListNode slow = dummyHead;
            ListNode fast = dummyHead.next;
            for (int i=0;i<n;i++)
                fast=fast.next;
            //此时fast和slow相差n
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
            //此时slow指向pre
            slow.next = slow.next.next;
            return dummyHead.next;
        }
    }
}
