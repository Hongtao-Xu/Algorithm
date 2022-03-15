package com.cn.advance.Stage1.DataStruct.Queue.hw.lc225;

import java.util.LinkedList;
import java.util.Queue;

/*
 * push 的过程只使用一个 queue
 *@author: xu-hongtao
 *@Time: 2021/10/22  22:19
 */
public class MyStack4 {
    private Queue<Integer> q;

    public MyStack4() {
        q = new LinkedList<>();
    }

    //”队首“的元素，始终是等效栈的”栈顶“
    public void push(int x) {
        // 首先，将 x 入队
        q.add(x);
        // 执行 n - 1 次出队再入队的操作
        for (int i=1;i<q.size();i++)
            q.add(q.remove());
    }

    /* 3 2 1 4*/
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
