package com.cn.LeetCode;

import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 23. 合并K个升序链表
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 *
 */
public class LC23 {

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

    //1.K 指针，O(NK)
    class Solution1 {
        public ListNode mergeKLists(ListNode[] lists) {
            int k = lists.length;
            ListNode dummyHead = new ListNode(0);
            ListNode tail = dummyHead;
            while (true) {
                ListNode minNode = null;
                int minNodePtr = -1;//循环终止条件：minNodePtr的值未被修改，就是所有链表都为空了
                for (int i = 0; i < k; i++) {
                    if (lists[i] == null) {
                        continue;
                    }
                    if (minNode == null || minNode.val > lists[i].val) {
                        minNode = lists[i];
                        minNodePtr = i;
                    }
                }
                if (minNodePtr == -1) break;
                tail.next = minNode;
                tail = tail.next;
                //把上一个循环中取出最小值的链表往后移
                lists[minNodePtr] = lists[minNodePtr].next;
            }
            return dummyHead.next;
        }
    }

    //2.小根堆，O(NlogK)，使用优先队列来优化选取当前K个节点的最小值
    class Solution2 {
        public ListNode mergeKLists(ListNode[] lists) {
            Queue<ListNode> pq = new PriorityQueue<>((v1, v2) ->
                    v1.val - v2.val
            );
            for (ListNode node : lists) {
                if (node != null)
                    pq.offer(node);
            }
            ListNode dummyHead = new ListNode(0);
            ListNode tail = dummyHead;
            while (!pq.isEmpty()) {
                ListNode minNode = pq.poll();
                tail.next = minNode;
                tail = tail.next;
                if (minNode.next != null) {
                    pq.offer(minNode.next);
                }
            }
            return dummyHead.next;
        }
    }

    //3.逐一合并两条链表,O(NK)
    class Solution3 {
        public ListNode mergeKLists(ListNode[] lists) {
            ListNode res = null;
            for (ListNode list : lists) {
                res = merge2Lists(res, list);
            }
            return res;
        }

        private ListNode merge2Lists(ListNode l1, ListNode l2) {
            if (l1 == null)
                return l2;
            if (l2 == null)
                return l1;
            if (l1.val < l2.val) {
                l1.next = merge2Lists(l1.next, l2);
                return l1;
            } else {
                l2.next = merge2Lists(l2.next, l1);
                return l2;
            }
        }
    }

    //4.两两合并对 1 进行优化，时间复杂度：O(NlogK)O(NlogK)
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            }
            return merge(lists, 0, lists.length - 1);
        }

        private ListNode merge(ListNode[] lists, int lo, int hi) {
            if (lo == hi)
                return lists[lo];
            int mid = lo + (hi - lo) / 2;
            ListNode l1 = merge(lists, lo, mid);
            ListNode l2 = merge(lists, mid + 1, hi);
            return merge2Lists(l1, l2);
        }

        private ListNode merge2Lists(ListNode l1, ListNode l2) {
            if (l1 == null)
                return l2;
            if (l2 == null)
                return l1;
            if (l1.val < l2.val) {
                l1.next = merge2Lists(l1.next, l2);
                return l1;
            } else {
                l2.next = merge2Lists(l2.next, l1);
                return l2;
            }
        }
    }
}
