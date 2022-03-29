package com.cn.advance.Stage3.Trie;

/*
 * Set接口
 */
public interface Set<E> {

    void add(E e);
    boolean contains(E e);
    void remove(E e);
    int getSize();
    boolean isEmpty();
}
