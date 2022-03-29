package com.cn.advance.Stage3.HashMap;

import java.util.TreeMap;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2022/3/28  18:32
 */
public class HashTable<K, V> {
    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;

    private TreeMap<K, V>[] hashtable;
    private int M;
    private int size;

    public HashTable(int M) {
        this.M = M;
        this.size = 0;
        this.hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++)
            hashtable[i] = new TreeMap<>();
    }

    public HashTable() {
        this(initCapacity);
    }


    private int hash(K key) {
        // 取正
        // 注意：M的值与计算出的hash值有关
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key))
            map.put(key, value);//更新
        else {
            map.put(key, value);
            size++;
        }
        //判断是否需要扩容
        //  size/M>=upperTol
        if (size >= upperTol * M)
            resize(2 * M);
    }

    public V remove(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;
            if (size < lowerTol * M && M / 2 >= initCapacity)
                resize(M / 2);
        }
        return ret;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key))
            throw new IllegalArgumentException(key + "doesn't exist");
        map.put(key, value);
    }

    public boolean contains(K key) {
       return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++)
            newHashTable[i] = new TreeMap<>();
        //注意此处M的处理
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet())
                newHashTable[hash(key)].put(key, map.get(key));
        }
        this.hashtable = newHashTable;
    }
}