package com.cn.LeetCode;

import java.util.HashSet;
import java.util.Set;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/6/5  21:59
 */
public class LC142 {

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
        public ListNode detectCycle(ListNode head) {
            ListNode pos = head;
            Set<ListNode> visited = new HashSet<>();
            while (pos != null) {
                if (visited.contains(pos)) {
                    return pos;
                } else {
                    visited.add(pos);
                }
                pos = pos.next;
            }
            return null;
        }
    }

    //2.快慢指针
    public class Solution1 {
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null)
                return null;
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null) {//fast未走到null
                slow = slow.next;
                if (fast.next != null) {
                    fast = fast.next.next;
                } else {//fast走完了，没有环
                    return null;
                }
                //a=c+(n-1)(b+c)
                if (fast == slow) {//有环，一定会遇上
                    ListNode ptr = head;
                    while (ptr != slow) {
                        ptr = ptr.next;
                        slow = slow.next;
                    }
                    return ptr;
                }
            }
            return null;
        }
    }
}
