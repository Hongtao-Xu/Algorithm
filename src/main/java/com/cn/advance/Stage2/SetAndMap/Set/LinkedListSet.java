package com.cn.advance.Stage2.SetAndMap.Set;

/*
 * 自定义基于单链表的
 * Set集合
 */
public class LinkedListSet<E> implements Set<E>{

    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        //注意去重
        if (!list.contains(e))
            list.addFirst(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
