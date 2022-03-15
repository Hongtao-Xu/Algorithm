package com.cn.advance.Stage1.DataStruct.Queue.hw.lc225;

import java.util.LinkedList;
import java.util.Queue;

/*
 *@program: 小优化，使用一个变量记录栈顶元素
 *@author: xu-hongtao
 *@Time: 2021/10/22  21:39
 */
public class MyStack2 {

    private Queue<Integer> q;
    private int top;

    public MyStack2() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        q.add(x);
        top=x;
    }

    public int pop() {
        // 创建另外一个队列 q2
        Queue<Integer> q2 = new LinkedList<>();
        // 除了最后一个元素，将 q 中的所有元素放入 q2
        while (q.size() > 1){
            top = q.peek();//只有最后依次的赋值，记录的是top值
            q2.add(q.remove());
        }
        // q 中剩下的最后一个元素就是“栈顶”元素
        int ret = q.remove();
        // 此时 q2 是整个数据结构存储的所有其他数据，赋值给 q
        q = q2;
        // 返回“栈顶元素”
        return ret;
    }

    public int top() {
       return top;
    }

    public boolean empty() {
        return q.isEmpty();
    }
}

