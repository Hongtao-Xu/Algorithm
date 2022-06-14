package com.cn.advance.Stage3.SegmentTree.hw;

/*
 *303. 区域和检索 - 数组不可变
 * https://leetcode-cn.com/problems/range-sum-query-immutable/
 */
public class LC303 {
    static class NumArray {

        public interface Merger<E> {
            E merge(E a, E b);
        }

        public class SegmentTree<E> {

            private E[] tree;
            private E[] data;
            private Merger<E> merger;

            public SegmentTree(E[] arr, Merger<E> merger) {
                this.merger = merger;
                this.data = (E[]) new Object[arr.length];
                for (int i = 0; i < arr.length; i++)
                    data[i] = arr[i];
                this.tree = (E[]) new Object[4 * arr.length];
                buildSegmentTree(0, 0, arr.length - 1);
            }

            // 在treeIndex的位置创建表示区间[l...r]的线段树
            private void buildSegmentTree(int treeIndex, int l, int r) {
                if (l == r) {
                    tree[treeIndex] = data[l];
                    return;
                }
                int leftTreeIndex = leftChild(treeIndex);
                int rightTreeIndex = rightChild(treeIndex);

                int mid = l + (r - l) / 2;
                buildSegmentTree(leftTreeIndex, l, mid);
                buildSegmentTree(rightTreeIndex, mid + 1, r);

                tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
            }

            // 返回区间[queryL, queryR]的值
            public E query(int queryL, int queryR) {
                if (queryL < 0 || queryL >= data.length ||
                        queryR < 0 || queryR >= data.length || queryL > queryR)
                    throw new IllegalArgumentException("Index is illegal.");

                return query(0, 0, data.length - 1, queryL, queryR);
            }

            // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
            private E query(int treeIndex, int l, int r, int queryL, int queryR) {
                if (l == queryL && r == queryR)
                    return tree[treeIndex];
                int mid = l + (r - l) / 2;
                int leftTreeIndex = leftChild(treeIndex);
                int rightTreeIndex = rightChild(treeIndex);

                if (queryR <= mid)
                    return query(leftTreeIndex, l, mid, queryL, queryR);
                else if (queryL >= mid + 1)
                    return query(rightTreeIndex, mid + 1, r, queryL, queryR);

                E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
                E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
                return merger.merge(leftResult, rightResult);
            }


            // 将index位置的值，更新为e
            public void set(int index, E e) {
                if (index < 0 || index >= data.length)
                    throw new IllegalArgumentException("Index is illegal");
                data[index] = e;
                set(0, 0, data.length - 1, index, e);
            }

            // 在以treeIndex为根的线段树中更新index的值为e
            private void set(int treeIndex, int l, int r, int index, E e) {
                if (l == r) {
                    tree[treeIndex] = e;
                    return;
                }
                int mid = l + (r - l) / 2;

                // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
                int leftTreeIndex = leftChild(treeIndex);
                int rightTreeIndex = rightChild(treeIndex);

                if (index <= mid)
                    set(leftTreeIndex, l, mid, index, e);
                else
                    set(rightTreeIndex, l, mid + 1, index, e);

                tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
            }

            public int getSize() {
                return data.length;
            }

            public E get(int index) {
                if (index < 0 || index >= data.length)
                    throw new IllegalArgumentException("Index is illegal.");
                return data[index];
            }

            // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
            private int leftChild(int index) {
                return 2 * index + 1;
            }

            // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
            private int rightChild(int index) {
                return 2 * index + 2;
            }

            @Override
            public String toString() {
                StringBuilder res = new StringBuilder();
                res.append("[");
                for (int i = 0; i < tree.length; i++) {
                    if (tree[i] != null)
                        res.append(tree[i]);
                    else
                        res.append("null");
                    if (i != tree.length - 1)
                        res.append(", ");
                }
                res.append("]");
                return res.toString();
            }
        }

        private SegmentTree<Integer> segmentTree;

        public NumArray(int[] nums) {
            if (nums.length > 0) {
                Integer[] data = new Integer[nums.length];
                for (int i=0;i<nums.length;i++)
                    data[i] = nums[i];
                segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
            }
        }

        public int sumRange(int i, int j) {
            if(segmentTree == null)
                throw new IllegalArgumentException("Segment Tree is null");

            return segmentTree.query(i, j);
        }
    }

    //解法二：预处理
    class NumArray2{
        private int[] sum;
        // sum[i]存储前i个元素和, sum[0] = 0
        // 即sum[i]存储nums[0...i-1]的和
        // sum(i, j) = sum[j + 1] - sum[i]

        public NumArray2(int[] nums) {
            sum = new int[nums.length + 1];
            sum[0] = 0;
            for(int i = 1 ; i < sum.length ; i ++)
                sum[i] = sum[i - 1] + nums[i - 1];
                //sum[1] = sum[0]+nums[0]
        }
        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }
}
