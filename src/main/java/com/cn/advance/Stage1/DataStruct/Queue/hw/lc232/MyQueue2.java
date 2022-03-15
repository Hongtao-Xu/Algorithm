package com.cn.advance.Stage1.DataStruct.Queue.hw.lc232;

import java.util.Stack;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/10/22  23:22
 */
public class MyQueue2 {

    private Stack<Integer> stack;
    int front;

    public MyQueue2() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if(empty()) front = x;
        stack.push(x);
    }
    public int pop() {

        Stack<Integer> stack2 = new Stack<>();

        while(stack.size() > 1) {
            front = stack.peek();
            stack2.push(stack.pop());
        }

        int ret = stack.pop();

        while(!stack2.isEmpty())
            stack.push(stack2.pop());

        return ret;
    }
    public int peek() {
        return front;
    }
    public boolean empty() {
        return stack.isEmpty();
    }

}
