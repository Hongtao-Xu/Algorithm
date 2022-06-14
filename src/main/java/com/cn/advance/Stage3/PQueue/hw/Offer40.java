package com.cn.advance.Stage3.PQueue.hw;

/*
 * 剑指 Offer 40. 最小的k个数
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * - Top K问题
 */
public class Offer40 {

    public class Array<E> {

        private E[] data;
        private int size;

        //构造函数，传入数组的容量capacity构造Array
        public Array(int capacity) {
            data = (E[]) new Object[capacity];
            size = 0;
        }

        //无参构造函数，默认capacity=10
        public Array() {
            this(10);
        }
        //将传入的E[] arr转为动态数组
        public Array(E[] arr) {
            data = (E[]) new Object[arr.length];
            for (int i = 0;i<arr.length;i++)
                data[i] = arr[i];
            size= arr.length;
        }
        //获取数组的元素个数
        public int getSize() {
            return size;
        }

        //获取数组的容量
        public int getCapacity() {
            return data.length;
        }

        //判断数组是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        //往数组的最后添加一个元素
        public void addLast(E e) {
            add(size, e);
        }

        //往数组的头部添加一个元素
        public void addFirst(E e) {
            add(0, e);
        }

        //在index位置插入新元素e
        public void add(int index, E e) {

            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Add failed,Require index >=0 and index<=size");
            }
            if (size == data.length)
                resize(2 * data.length);

            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }
            data[index] = e;
            size++;//总是指向最后一个元素
        }

        //获取index索引位置的元素
        public E get(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Get failed,Index is illegal");
            }
            return data[index];
        }

        //修改index索引位置的元素
        public void set(int index, E e) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Get failed,Index is illegal");
            }
            data[index] = e;
        }

        //查找数组中是否有元素e
        public boolean contains(E e) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(e))
                    return true;
            }
            return false;
        }

        //查找数组元素中e所在的索引，不存在返回-1
        public int find(E e) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(e))
                    return i;
            }
            return -1;
        }

        //删除index位置的元素，返回删除元素
        public E remove(int index) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Remove failed,Index is illegal");
            }
            E ret = data[index];
            for (int i = index + 1; i < size; i++)
                data[i - 1] = data[i];
            size--;
            data[size] = null;//loitering objects!=memory leak
            if (size == (data.length / 4) && (data.length / 2) != 0)
                resize(data.length / 2);
            return ret;
        }

        //删除数组第一个元素，返回删除元素
        public E removeFirst() {
            return remove(0);
        }

        //删除数组最后一个元素，返回删除元素
        public E removeLast() {
            return remove(size - 1);
        }

        //从数组中删除元素e
        public void removeElement(E e) {
            int index = find(e);
            if (index != -1)
                remove(index);
        }

        public void swap(int i,int j) {
            if(i < 0 || i >= size || j < 0 || j >= size)
                throw new IllegalArgumentException("Index is illegal.");
            E t = data[i];
            data[i]=data[j];
            data[j]=t;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append(String.format("Array:size=%d,capacity=%d\n", size, data.length));
            res.append('[');
            for (int i = 0; i < size; i++) {
                res.append(data[i]);
                if (i != size - 1) {//最后一个元素后，不用逗号
                    res.append(',');
                }
            }
            res.append(']');
            return res.toString();
        }

        //动态数组，扩容，将数组空间的容量变成newCapacity大小
        private void resize(int newCapacity) {
            E[] newData = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++)
                newData[i] = data[i];
            data = newData;
        }
    }

    public class MaxHeap<E extends Comparable<E>> {
        private Array<E> data;

        public MaxHeap(int capacity) {
            this.data = new Array<>(capacity);
        }

        public MaxHeap() {
            this.data = new Array<>();
        }

        // 将任意数组调整为最大堆 heapify
        public MaxHeap(E[] arr) {
            this.data = new Array<>(arr);
            //对所有非叶子节点siftDown()
            if (arr.length != 1) {//1个元素的数组，本身就是一个最大堆
                for (int i = parent(arr.length - 1); i >= 0; i--)
                    siftDown(i);
            }
        }

        // 返回堆中的元素个数
        public int size() {
            return data.getSize();
        }

        // 返回一个布尔值, 表示堆中是否为空
        public boolean isEmpty() {
            return data.isEmpty();
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
        private int parent(int index) {
            if (index == 0)
                throw new IllegalArgumentException("index-0 doesn't have parent.");
            return (index - 1) / 2;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
        private int leftChild(int index) {
            return index * 2 + 1;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
        private int rightChild(int index) {
            return index * 2 + 2;
        }

        // 向堆中添加元素,把元素上浮到它应该在的位置
        public void add(E e) {
            data.addLast(e);
            siftUp(data.getSize() - 1);
        }

        //上浮
        private void siftUp(int k) {
            while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
                data.swap(k, parent(k));
                k = parent(k);
            }
        }

        // 看堆中的最大元素
        public E findMax() {
            if (data.getSize() == 0)
                throw new IllegalArgumentException("Can not findMax when heap is empty.");
            return data.get(0);
        }

        // 取出堆中最大元素
        public E extractMax() {
            E ret = findMax();
            data.swap(0, data.getSize() - 1);
            data.removeLast();
            siftDown(0);
            return ret;
        }

        //下沉
        private void siftDown(int k) {
            while (leftChild(k) < data.getSize()) {
                int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
                //j:左孩子；j+1右孩子
                if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0)
                    j++;//j变成了指向大的元素
                // data[j] 是 leftChild 和 rightChild 中的最大值
                if (data.get(k).compareTo(data.get(j)) >= 0)
                    break;//此时k已经比左右孩子都大，不用操作了
                //k，比大孩子小
                data.swap(k, j);
                k = j;//k指向新的节点
            }
        }

        // 取出堆中的最大元素，并且替换成元素e
        public E replace(E e) {
            E ret = findMax();
            data.set(0, e);
            siftDown(0);
            return ret;
        }

    }

    public interface Queue <E>{
        int getSize();
        boolean isEmpty();
        void enqueue(E e);
        E dequeue();
        E getFront();
    }

    public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
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

    public int[] getLeastNumbers(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0;i<k;i++)
            pq.enqueue(arr[i]);
        for(int i=k;i<arr.length;i++)
            if (!pq.isEmpty()&&arr[i]<pq.getFront()){
                pq.dequeue();
                pq.enqueue(arr[i]);
            }
        int[] res = new int[k];
        for(int i=0;i<k;i++)
            res[i]=pq.dequeue();
        return res;
    }
}
