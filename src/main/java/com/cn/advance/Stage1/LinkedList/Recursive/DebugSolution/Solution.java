package com.cn.advance.Stage1.LinkedList.Recursive.DebugSolution;

import com.cn.advance.Stage1.LinkedList.hw.lc203.ListNode;


/*
 * Debug此问题,打印输出法
 */
public class Solution {
    public ListNode removeElements(ListNode head, int val, int depth) {
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);//调用了ListNode的toString方法

        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return head;
        }

        ListNode res = removeElements(head.next, val, depth + 1);//分解到基本问题
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);
        ListNode ret;
        if (head.val == val) {//从基本问题往上推
            ret = res;//满足条件，就直接返回res，不添加这个头节点head(相当于删除了符合条件的节点)
        } else {
            head.next = res;//不满足条件，添加这个头节点head
            ret = head;//返回添加头结点后的链表，继续往上推
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret);
        return ret;
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElements(head, 2, 0);
        System.out.println(res);
    }
}
