package com.cn.advance.Stage1.DataStruct.Queue.hw.lc225;

import java.util.LinkedList;
import java.util.Queue;

/*
 *@program: push 的过程使用两个 queue
 *@author: xu-hongtao
 *@Time: 2021/10/22  22:11
 */
public class MyStack3 {
    private Queue<Integer> q;

    public MyStack3() {
        q = new LinkedList<>();
    }

    public void push(int x) {

        Queue<Integer> q2 = new LinkedList<>();
        q2.add(x);
        while (!q.isEmpty())
            q2.add(q.remove());
        q=q2;
    }

    public int pop() {
        return q.remove();
    }

    public int top() {
       return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
