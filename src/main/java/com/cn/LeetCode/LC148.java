package com.cn.LeetCode;

/*
 * 148. 排序链表
 * https://leetcode.cn/problems/sort-list/
 */
public class LC148 {
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

    //1.递归，自顶向下
    class Solution {
        public ListNode sortList(ListNode head) {
            return sortList(head, null);
        }

        private ListNode sortList(ListNode head, ListNode tail) {
            if (head == null)
                return head;
            if (head.next == tail) {
                head.next = null;
                return head;
            }
            ListNode fast = head, slow = head;
            while (fast != tail) {
                slow = slow.next;
                fast = fast.next;
                if (fast != tail)
                    fast = fast.next;
            }
            ListNode mid = slow;
            ListNode list1 = sortList(head, mid);
            ListNode list2 = sortList(mid, tail);
            ListNode sorted = merge(list1, list2);
            return sorted;
        }

        //merge也使用双指针
        public ListNode merge(ListNode head1, ListNode head2) {
            //虚拟头结点
            ListNode preHead = new ListNode(-1);
            ListNode pre = preHead, l1 = head1, l2 = head2;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    pre.next = l1;
                    l1 = l1.next;
                } else {
                    pre.next = l2;
                    l2 = l2.next;
                }
                pre = pre.next;
            }
            if (l1 == null)
                pre.next = l2;
            else
                pre.next = l1;
            return preHead.next;
        }
    }

    //2.自底向上
    class Solution1 {
        public ListNode sortList(ListNode head) {
            //1.特判
            if (head == null)
                return head;
            //2.计算链表长度 length
            int length = 0;
            ListNode node = head;
            while (node != null) {
                length++;
                node = node.next;
            }
            //3.虚拟头结点
            ListNode dummyHead = new ListNode(0, head);
            for (int subLength = 1; subLength < length; subLength <<= 1) {//进行递归
                ListNode prev = dummyHead, curr = dummyHead.next;
                while (curr != null) {
                    ListNode head1 = curr;//head1
                    for (int i = 1; i < subLength && curr.next != null; i++) {
                        curr = curr.next;
                    }
                    ListNode head2 = curr.next;//head2
                    curr.next = null;
                    curr = head2;
                    for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                        curr = curr.next;
                    }
                    ListNode next = null;//next节点
                    if (curr != null) {
                        next = curr.next;
                        curr.next = null;
                    }
                    ListNode merged = merge(head1, head2);
                    prev.next = merged;//prev连接merge之后的节点
                    while (prev.next != null) {//移动prev
                        prev = prev.next;
                    }
                    curr = next;//更新curr节点
                }
            }
            return dummyHead.next;
        }

        //merge也使用双指针
        public ListNode merge(ListNode head1, ListNode head2) {
            //虚拟头结点
            ListNode preHead = new ListNode(-1);
            ListNode pre = preHead, l1 = head1, l2 = head2;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    pre.next = l1;
                    l1 = l1.next;
                } else {
                    pre.next = l2;
                    l2 = l2.next;
                }
                pre = pre.next;
            }
            if (l1 == null)
                pre.next = l2;
            else
                pre.next = l1;
            return preHead.next;
        }
    }
}
