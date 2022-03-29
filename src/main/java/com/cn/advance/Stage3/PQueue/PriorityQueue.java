package com.cn.advance.Stage3.PQueue;

import com.cn.advance.Stage3.Heap.MaxHeap;

/*
 * 优先队列
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E>{
    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        this.maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
