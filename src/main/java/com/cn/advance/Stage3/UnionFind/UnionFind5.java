package com.cn.advance.Stage3.UnionFind;

/*
 * 我们的第五版Union-Find
 * - 通过循环降低树的高度
 * - 主要改动：find()函数：
 * parent[p] = parent[parent[p]];
 */
public class UnionFind5 implements UF{

    private int[] parent; // parent[i]表示第一个元素所指向的父节点
    private int[] rank;//// rank[i]表示以i为根的集合所表示的树的层数

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];
        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[p] == p
        while (p != parent[p]){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到rank高的集合上
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;// 此时, 我维护rank的值
        }
    }
}
