package com.cn.LeetCode;

/*
 * 21. 合并两个有序链表
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 */
public class LC21 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //1.递归
    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if(list1==null)
                return list2;
            else if(list2==null)
                return list1;
            //哪一个链表的头节点的值更小
            else if(list1.val< list2.val){
                //更小头结点的剩下的节点，与另一个链表融合
                list1.next = mergeTwoLists(list1.next,list2);
                return list1;
            }else {
                list2.next = mergeTwoLists(list2.next,list1);
                return list2;
            }
        }
    }
    //2.双指针
    class Solution2 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            //虚拟头结点
            ListNode preHead = new ListNode(-1);

            ListNode pre = preHead;
            while(l1!=null&&l2!=null){
                if(l1.val<l2.val){
                    pre.next = l1;
                    l1 = l1.next;
                }else{
                    pre.next = l2;
                    l2 = l2.next;
                }
                pre = pre.next;
            }
            if(l1==null)
                pre.next = l2;
            else
                pre.next = l1;
            return preHead.next;
        }
    }
}
