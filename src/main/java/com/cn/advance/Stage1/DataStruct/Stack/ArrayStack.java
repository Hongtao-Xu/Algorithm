package com.cn.advance.Stage1.DataStruct.Stack;

/*
 *数组实现栈
 */
public class ArrayStack<E> implements Stack<E> {

    MyArray<E> array;

    public ArrayStack(int capacity) {
        array = new MyArray<>(capacity);
    }

    public ArrayStack() {
        array = new MyArray<>();
    }

    @Override

    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack：");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {//最后一个元素后，不用逗号
                res.append(',');
            }
        }
        res.append("] top");
        return res.toString();
    }
}
