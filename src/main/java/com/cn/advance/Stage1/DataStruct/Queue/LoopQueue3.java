package com.cn.advance.Stage1.DataStruct.Queue;

/*
 *@program:不用size,浪费一个空间，的循环队列
 *@author: xu-hongtao
 *@Time: 2021/10/14  17:28
 */
public class LoopQueue3<E> implements Queue<E> {
    private E[] data;
    private int front, tail;


    public LoopQueue3(int capacity) {
        data = (E[]) new Object[capacity + 1];//故意浪费一个空间
        front = 0;
        tail = 0;
    }

    public LoopQueue3() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        // 如果tail >= front，非常简单，队列中的元素个数就是tail - front
        // 如果tail < front，说明我们的循环队列"循环"起来了，此时，队列中的元素个数为：

        // 也可以理解成，此时，data中没有元素的数目为front - tail,
        // 整体元素个数就是 data.length - (front - tail) = data.length + tail - front

        return tail>=front?tail-front:data.length + tail - front;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }


    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front)
            //扩容
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;

    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequue from an empty queue");
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        //缩容
        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0)
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
        E[] newData = (E[]) new Object[newCapacity + 1];
        int size = getSize();
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
        res.append(String.format("Queue: size=%d,capacity=%d\n", getSize(), getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {//不是最后一个元素后
                res.append(',');
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){

        LoopQueue3<Integer> queue = new LoopQueue3<>();
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
