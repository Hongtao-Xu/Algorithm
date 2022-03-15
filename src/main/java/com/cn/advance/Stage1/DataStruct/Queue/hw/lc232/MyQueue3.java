package com.cn.advance.Stage1.DataStruct.Queue.hw.lc232;

import java.util.Stack;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/10/22  23:38
 */
public class MyQueue3 {

    private Stack<Integer> in;
    private Stack<Integer> out;


    public MyQueue3() {
        in = new Stack<>();
        out = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        in.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (out.isEmpty())
            in2out();
        return out.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (out.isEmpty())
            in2out();
        return out.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }

    private void in2out() {
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
    }
}
