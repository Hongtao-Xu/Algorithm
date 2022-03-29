package com.cn.advance.Stage2.SetAndMap.Map;

/*
 * 自定义Map接口
 */
public interface Map<K,V> {

    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();
}
