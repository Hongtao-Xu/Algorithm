package com.cn.advance.Stage1.DataStruct.Array;


//import org.omg.CORBA.Object;

public class MyArray<E> {

    private E[] data;
    private int size;

    //构造函数，传入数组的容量capacity构造Array
    public MyArray(int capacity) {
//        data = new E[capacity];
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //无参构造函数，默认capacity=10
    public MyArray() {
        this(10);
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
            //throw new IllegalArgumentException("Add failed,Array is full");
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
        if (size == data.length / 4 && data.length / 2 != 0)
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

    //动态数组，扩容
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }
}
