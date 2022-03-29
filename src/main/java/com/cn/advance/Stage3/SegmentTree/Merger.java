package com.cn.advance.Stage3.SegmentTree;

/*
 * 指定merge的形式
 */
public interface Merger<E> {
    E merge(E a, E b);
}
