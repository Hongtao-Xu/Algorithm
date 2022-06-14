package com.cn.LeetCode;

import java.util.HashSet;
import java.util.Set;

/*
 * 160. 相交链表
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/
 */
public class LC160 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //1.双指针
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
            ListNode pA = headA, pB = headB;
            while (pA != pB) {
                pA = pA == null ? headB : pA.next;
                pB = pB == null ? headA : pB.next;
            }
            return pA;
        }
    }

    //2.HashSet
    public class Solution1 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            Set<ListNode> set = new HashSet<>();
            ListNode hA = headA, hB = headB;
            while (hA != null) {
                set.add(hA);
                hA = hA.next;
            }
            while (hB != null) {
                if (set.contains(hB)) {
                    return hB;
                }
                set.add(hB);
                hB = hB.next;
            }
            return null;
        }
    }

}
