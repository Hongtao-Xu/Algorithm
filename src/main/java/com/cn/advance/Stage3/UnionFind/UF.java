package com.cn.advance.Stage3.UnionFind;

/*
 *UF接口
 */
public interface UF {
    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
