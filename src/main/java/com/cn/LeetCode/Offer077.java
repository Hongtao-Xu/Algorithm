package com.cn.LeetCode;

import java.util.PriorityQueue;

/*
 * 从单链表中找出第 K 大的节点
 *https://leetcode-cn.com/problems/7WHec2/
 */
public class Offer077 {
    /**
     * 时间复杂度nlogn的算法：
     * 归并排序：
     * 自顶向下
     * 自底向上
     * 堆排序
     * 快速排序（最差时间复杂度为n^2）
     */


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

    //1.自顶向下归并排序
    //时间复杂度：O(nlogn)，其中 n 是链表的长度。
    //空间复杂度：O(logn)，其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间。
    class Solution {
        public ListNode sortList(ListNode head) {
            return sort(head, null);
        }

        private ListNode sort(ListNode head, ListNode tail) {
            if (head == null) return null;
            //快慢指针
            if (head.next == tail) {
                head.next = null;//截断
                return head;
            }
            ListNode slow = head, fast = head;
            while (fast != tail) {
                slow = slow.next;
                fast = fast.next;
                if (fast != tail) {
                    fast = fast.next;
                }
            }
            ListNode mid = slow;
            ListNode list1 = sort(head, mid);
            ListNode list2 = sort(mid, tail);
            ListNode res = merge(list1, list2);
            return res;
        }

        private ListNode merge(ListNode node1, ListNode node2) {
            ListNode dummyHead = new ListNode(0), cur, temp1, temp2;
            cur = dummyHead;
            temp1 = node1;
            temp2 = node2;//为了防止某一个链表用完时
            while (temp1 != null && temp2 != null) {//两个链表长度相等
                if (temp1.val < temp2.val) {
                    cur.next = temp1;
                    temp1 = temp1.next;
                } else {
                    cur.next = temp2;
                    temp2 = temp2.next;
                }
                cur = cur.next;
            }
            if (temp1 != null) {
                cur.next = temp1;
            } else if (temp2 != null) {
                cur.next = temp2;
            }
            return dummyHead.next;
        }
    }

    //2.自底向上归并排序
    class Solution2 {
        public ListNode sortList(ListNode head) {
            if (head == null) return null;
            int length = 0;
            ListNode node = head;
            while (node != null) {
                length++;
                node = node.next;
            }
            ListNode dummyHead = new ListNode(0, head);

            for (int subLength = 1; subLength < length; subLength <<= 1) {
                ListNode prev = dummyHead, curr = dummyHead.next;
                while (curr != null) {
                    ListNode head1 = curr;
                    for (int i = 1; i <subLength&&curr.next!=null; i++) {
                        curr = curr.next;
                    }
                    ListNode head2 = curr.next;
                    curr.next=null;
                    curr=head2;

                    for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                        curr = curr.next;
                    }
                    ListNode next = null;
                    if (curr != null) {
                        next = curr.next;
                        curr.next = null;
                    }
                    ListNode merged = merge(head1, head2);
                    prev.next = merged;
                    while (prev.next != null) {
                        prev = prev.next;
                    }
                    curr = next;
                }
            }
            return dummyHead.next;
        }
        public ListNode merge(ListNode head1, ListNode head2) {
            ListNode dummyHead = new ListNode(0);
            ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
            while (temp1 != null && temp2 != null) {
                if (temp1.val <= temp2.val) {
                    temp.next = temp1;
                    temp1 = temp1.next;
                } else {
                    temp.next = temp2;
                    temp2 = temp2.next;
                }
                temp = temp.next;
            }
            if (temp1 != null) {
                temp.next = temp1;
            } else if (temp2 != null) {
                temp.next = temp2;
            }
            return dummyHead.next;
        }
    }

    //3.堆
    //时间复杂度O(nlogn)，空间复杂度O(n)
    class Solution3 {
        public ListNode sortList(ListNode head) {
            //小顶堆
            PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
            while (head != null) {
                heap.offer(head);
                head = head.next;
            }
            ListNode dummy = new ListNode(), cur;
            cur = dummy;
            while (heap.size() > 0) {
                cur.next = heap.peek();
                heap.poll();
                cur = cur.next;
            }
            cur.next = null;
            return dummy.next;
        }
    }

}
