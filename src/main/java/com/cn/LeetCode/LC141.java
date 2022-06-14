package com.cn.LeetCode;

import java.util.HashSet;

/*
 *141. 环形链表
 *https://leetcode.cn/problems/linked-list-cycle/
 */
public class LC141 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //1.HashSet
    public class Solution {
        public boolean hasCycle(ListNode head) {
            //不能有重复元素
            HashSet<ListNode> hSet = new HashSet<>();
            while (head != null) {
                //有重复元素
                if (!hSet.add(head)) {
                    return true;
                }
                head = head.next;
            }
            return false;
        }
    }

    //2.快慢指针
    public class Solution2 {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null)
                return false;
            ListNode slow = head;
            ListNode fast = head;
            do {
                //fast指针走到尾了
                if (fast == null || fast.next == null)
                    return false;
                slow = slow.next;
                fast = fast.next.next;
            } while (slow != fast);
            return true;
        }
    }
}
