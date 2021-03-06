package com.cn.advance.Stage3.HashMap;

import java.util.ArrayList;

/*
 * AVL平衡树
 * 核心：
 * - 左旋转，右旋转
 * - 平衡因子
 * - remove操作时，要回溯维护平衡性
 */
public class AVLTree<K extends Comparable<K>, V> {
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    //获取节点node的高度
    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    //获取节点node的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    //判断二叉树是否是一棵二分搜索树
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++)
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    //判断二叉树是否是一棵平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    //判断以Node为根的二叉树是否是一棵平衡二叉树，递归算法
    private boolean isBalanced(Node node) {
        if (node == null) return true;

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //中序遍历
    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;//相当于更新操作

        //1.更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        //2.计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //Math.abs() - 绝对值
//        if (Math.abs(balanceFactor) > 1)
//            System.out.println("unbalanced: " + balanceFactor);

        //3.平衡维护，原因：左侧的左侧添加了一个节点
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);
        //平衡维护，原因：右侧的右侧添加了一个节点
        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);
        //平衡维护，原因：左侧的右侧添加了一个节点
        //LR
        if (balanceFactor >1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            //转换成了：LL
            return rightRotate(node);
        }
        //平衡维护，原因：右侧的左侧添加了一个节点
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            //转换成了：RR
            return leftRotate(node);
        }
        return node;
    }

    //对节点进行右旋转，返回旋转后新的根节点x
    //         y                        x
    //        / \                      / \
    //       x   T4   向右旋转(y)       z   y
    //      / \      ------------>   / \  / \
    //     z  T3                    T1 T2 T3 T4
    //    / \
    //   T1 T2
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3  = x.right;
        //向右旋转过程
        x.right = y;
        y.left=T3;
        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    //对节点进行左旋转，返回旋转后新的根节点x
    //         y                         x
    //        / \                       / \
    //       T1  x      向左旋转(y)      y   z
    //           /\   ------------>   / \  / \
    //         T2  z                 T1 T2 T3 T4
    //             /\
    //            T3 T4
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;
        //左旋转
        x.left = y;
        y.right = T2;
        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {

        if (node == null)
            return null;

        if (key.equals(node.key))
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    // 从二分搜索树中删除键为key的节点，返回key节点的值

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }


    public boolean contains(K key) {
        return getNode(root, key) != null;
    }


    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }


    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }


    public int getSize() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    private Node remove(Node node, K key) {

        if (node == null)return null;

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode =  node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;size--;
                retNode = rightNode;
            }
            // 待删除节点右子树为空的情况
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;size--;
                retNode = leftNode;
            }else {
                // 待删除节点左右子树均不为空的情况
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                //successor.right = removeMin(node.right); 此处可能会破坏平衡性
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }
        //retNode为空节点时，避免访问出现空指针
        if(retNode==null)return null;
        //==================处理retNode==================
        //1.更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        //2.计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        //3.平衡维护，原因：左侧的左侧添加了一个节点
        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);
        //平衡维护，原因：右侧的右侧添加了一个节点
        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);
        //平衡维护，原因：左侧的右侧添加了一个节点
        //LR
        if (balanceFactor >1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotate(retNode.left);
            //转换成了：LL
            return rightRotate(retNode);
        }
        //平衡维护，原因：右侧的左侧添加了一个节点
        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            retNode.right = rightRotate(retNode.right);
            //转换成了：RR
            return leftRotate(retNode);
        }
        return retNode;
    }
}
