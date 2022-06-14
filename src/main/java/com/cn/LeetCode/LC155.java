package com.cn.LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/*
 * 155. 最小栈
 * https://leetcode.cn/problems/min-stack/
 */
public class LC155 {

    //1.辅助栈
    class MinStack {
        Deque<Integer> xStack;
        Deque<Integer> minStack;//辅助栈

        public MinStack() {
            xStack = new LinkedList<Integer>();
            minStack = new LinkedList<Integer>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            xStack.push(val);
            //栈顶，val
            minStack.push(Math.min(minStack.peek(), val));
        }

        public void pop() {
            xStack.pop();
            minStack.pop();
        }

        public int top() {
            return xStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    //2.差值法(值溢出问题)
    class MinStack1 {
        Deque<Long> stack;
        long min;  //用来存放当前的最小值

        public MinStack1() {
            stack = new LinkedList<Long>();
        }

        public void push(int x) {
            if (stack.isEmpty()) {
                min = x;
                stack.push(x - min);
            } else {
                stack.push(x - min);
                if (x < min) {
                    //更新min
                    min = x;
                }
            }
        }

        public void pop() {
            if (stack.isEmpty())
                return;
            long pop = stack.pop();
            if (pop < 0) {//出现负值，要更新min
                min = min - pop;
            }
        }

        public int top() {
            long top = stack.peek();
            //负数的话，出栈的值保存在 min 中
            if (top < 0) {
                return (int) min;
            }
            return (int) (top + min);
        }

        public int getMin() {
            return (int) min;  //直接返回当前最小值
        }
    }

    //3.最小值入栈法
    static class MinStack2 {
        Deque<Integer> stack;
        int min = Integer.MAX_VALUE;  //用来存放当前的最小值

        public MinStack2() {
            stack = new LinkedList<Integer>();
        }

        public void push(int x) {
            if (x <= min) {//当前值更小
                stack.push(min);  //将之前的最小值保存
                min = x; //更新最小值
            }
            stack.push(x);
        }

        public void pop() {
            //如果弹出的值是最小值，那么将下一个元素更新为最小值
            if (stack.pop() == min) {
                min = stack.pop();//连弹出2个元素，第二个元素，其实就是入栈的时候存放的最小值
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;  //直接返回当前最小值
        }
    }

    //4.使用链表，修改底层数据结构
    class MinStack4 {
        class Node {
            int val;
            int min;//到当前链表，最小值是哪一个
            Node next;

            public Node() {
            }

            public Node(int val, int min) {
                this.val = val;
                this.min = min;
                this.next = null;
            }
        }


        Node head;//指向栈顶

        public MinStack4() {
        }

        public void push(int x) {
            if (head == null) {
                head = new Node(x, x);//栈底元素的下一个元素是null
            } else {
                Node cur = new Node(x, Math.min(head.min, x));
                cur.next = head;
                head = cur;
            }
        }

        public void pop() {
            if (head != null)
                head = head.next;
        }

        public int top() {
            if (head != null)
                return head.val;
            return -1;
        }

        public int getMin() {
            if (head != null)
                return head.min;
            return -1;
        }
    }


    public static void main(String[] args) {
        MinStack2 minStack2 = new MinStack2();
        minStack2.push(2);
        minStack2.push(1);
        minStack2.push(3);
        minStack2.push(1);
        minStack2.pop();
        minStack2.pop();
        minStack2.pop();
        minStack2.pop();
    }
}
