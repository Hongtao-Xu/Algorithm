package com.cn.advance.Stage1.DataStruct.Queue;

/*
 *@program:不浪费一个空间的循环队列
 *@author: xu-hongtao
 *@Time: 2021/10/14  17:28
 */

public class LoopQueue2<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue2(int capacity) {
        data = (E[]) new Object[capacity];//不浪费一个空间
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue2() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        //  return front == tail;
        return size == 0;//注意，我们不再使用front和tail之间的关系来判断队列是否为空，而直接使用size
    }

    @Override
    public void enqueue(E e) {
        //  if ((tail + 1) % data.length == front)
        // 注意，我们不再使用front和tail之间的关系来判断队列是否为满，而直接使用size
        if (size == getCapacity())
            //扩容
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        E ret = data[front];
        front = (front + 1) % data.length;
        size--;
        //缩容
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];//环形 循环
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size=%d,capacity=%d\n", size, getCapacity()));
        res.append("front [");
        /*  for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {//不是最后一个元素后
                res.append(',');
            }
        }*/
        // 注意，我们的循环遍历打印队列的逻辑也有相应的更改
        for (int i = 0; i < size; i++) {
            res.append(data[(front + i) % data.length]);
            if ((i + front + 1) % data.length != tail) {//不是最后一个元素后
                res.append(',');
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){

        LoopQueue2<Integer> queue = new LoopQueue2<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}