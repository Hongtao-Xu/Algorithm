package com.cn.advance.Stage2.BSTree;

import java.util.Stack;

/*
 *二分搜索树
 * 非递归实现
 */
public class BST_NR<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST_NR() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 1.向二分搜索树中添加新的元素e，非递归实现
    public void add(E e){
        if (root == null) {
            size++;
            root = new Node(e);
            return;
        }
        Node p =root;
        while (p != null) {
            if (e.compareTo(p.e) < 0) {
                if (p.left == null) {
                    p.left = new Node(e);size++;
                    return;
                }
                p = p.left;//继续往左边节点寻找，直到找到空节点为止
            } else if (e.compareTo(p.e) > 0) {
                if (p.right==null) {
                    p.right=new Node(e);size++;
                    return;
                }
                p=p.right;
            }else {
                return;//add()函数啥也没做，相当于替换了相等的节点
            }
        }
    }

    // 2.二分搜索树的前序遍历，非递归实现,深度优先--Stack
    public void preOrderNR() {
        if(root==null)return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            //注意此处压栈的左右顺序不能返
            if (cur.right!=null)
                stack.push(cur.right);
            if (cur.left!=null)
                stack.push(cur.left);
        }
    }

}
