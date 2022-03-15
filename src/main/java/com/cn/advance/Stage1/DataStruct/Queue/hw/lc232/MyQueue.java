package com.cn.advance.Stage1.DataStruct.Queue.hw.lc232;

import java.util.Stack;

/*
 *@program:用栈列实现队列
 *@author: xu-hongtao
 *@Time: 2021/10/22  21:22
 */
public class MyQueue {
    private Stack<Integer> stack;

    public MyQueue() {
        stack = new Stack<>();
    }

    public void push(int x) {
        //移动到stack2
        Stack<Integer> stack2 = new Stack<>();
        while (!stack.empty())
            stack2.push(stack.pop());

        stack.push(x);
        //移动到stack
        while (!stack2.empty())
            stack.push(stack2.pop());
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
