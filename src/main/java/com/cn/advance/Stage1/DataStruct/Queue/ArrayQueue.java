package com.cn.advance.Stage1.DataStruct.Queue;

/*
 * 数组实现队列
 */
public class ArrayQueue<E> implements Queue<E> {
    public MyArray<E> array;

    public ArrayQueue(int capacity) {
        array = new MyArray<>(capacity);
    }
    public ArrayQueue() {
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

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }


    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue:");
        res.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {//最后一个元素后，不用逗号
                res.append(',');
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
