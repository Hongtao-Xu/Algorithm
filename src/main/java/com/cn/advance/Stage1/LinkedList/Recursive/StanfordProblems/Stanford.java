package com.cn.advance.Stage1.LinkedList.Recursive.StanfordProblems;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/12/9  18:15
 */
public class Stanford {
    private Node head;
    private int size;

    public Stanford(Node head) {
        this.head = head;
    }

    public void CountTest(int searchFor) {
        if (head == null)
            throw new IllegalArgumentException("List can not be empty");
        System.out.println(Count(head, searchFor));
    }

    /**
     * 查找 head为头结点的链表中，searchFor的个数
     */
    private int Count(Node head, int searchFor) {
        Node cur = head;
        int count = 0;
        while (cur != null) {
            if (cur.val == searchFor)
                count++;
            cur = cur.next;
        }
        return count;
    }

    public void GetNthTest(int index) {
        if (head == null)
            throw new IllegalArgumentException("List can not be empty");
        int res = GetNth(head, index);
        if (res == -1)
            System.out.println("index out of range");
        else
            System.out.println(res);

    }

    /**
     * 查找 head为头结点的链表中，index处的值
     */
    private int GetNth(Node head, int index) {
        Node cur = head;
        int count = 0;
        while (cur != null) {
            if (count == index)
                return cur.val;
            count++;
            cur = cur.next;
        }
        return -1;
    }

    public void DeleteListTest() {
        //head=null;//gc未立即回收
        head = DeleteList(head);
    }

    /**
     *   删除整个链表
     */
    private Node DeleteList(Node head) {
        if (head == null)
            return null;
        Node cur = head;
        while (cur != null) {
            Node target = cur;
            cur = cur.next;
            target.next = null;//gc回收
        }
        return cur;
    }
//    public void  PopTest{
//        Pop(head);
//    }

//    int Pop(struct node** headRef) {
//    // your code...


    /**
     * 打印整个链表
     */
    public void print() {
        System.out.println(head);
    }



    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2};
        Node head = new Node(nums);
        Stanford sf = new Stanford(head);
        sf.print();
//        sf.CountTest(2);
//        sf.GetNthTest(2);
        sf.DeleteListTest();
        sf.print();
    }
}
