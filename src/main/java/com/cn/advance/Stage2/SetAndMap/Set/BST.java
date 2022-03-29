package com.cn.advance.Stage2.SetAndMap.Set;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 二分搜索树
 * - 递归实现
 * - 优化递归终止条件
 */
public class BST<E extends Comparable<E>> {
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

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 1.向二分搜索树中添加新的元素e
    public void add(E e) {
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);
        return node;
    }

    //2.查看二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    // 查看以node为根的二分搜索树中是否包含元素e, 递归算法
    private boolean contains(Node node, E e) {
        if (node == null)
            return false;
        if (node.e.compareTo(e) == 0)
            return true;
        else if (node.e.compareTo(e) > 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    // 3.二分搜索树的"前序遍历"
    public void preOrder() {
        preOrder(root);
    }

    // 前序遍历以node为根的二分搜索树, 递归算法
    private void preOrder(Node node) {
        if (node == null) return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 4.二分搜索树的"中序遍历"
    public void inOrder() {
        inOrder(root);
    }

    // 中序遍历以node为根的二分搜索树, 递归算法
    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 5.二分搜索树的"后序遍历"
    public void postOrder() {
        postOrder(root);
    }

    // 后序遍历以node为根的二分搜索树, 递归算法
    private void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 6.二分搜索树的"层序遍历"
    public void levelOrder() {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);
            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }

    //================ 删除二叉树相关 ===================
    // 寻找二分搜索树的最小元素
    public E minimum(){
        if(size==0)throw new IllegalArgumentException("BST is empty");
        return  minimum(root).e;
    }
    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if (node.left==null) return node;
        return minimum(node.left);
    }
    // 寻找二分搜索树的最大元素
    public E maximum(){
        if(size == 0)throw new IllegalArgumentException("BST is empty");
        return maximum(root).e;
    }
    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node){
        if( node.right == null )return node;
        return maximum(node.right);
    }

    // 1.从二分搜索树中删除最小值所在节点, 返回最小值
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }
    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){
        if (node.left == null) {
            Node rightNode = node.right;
            node.right=null;size--;
            return rightNode;
        }
        node.left=removeMin(node.left);
        return node;
    }

    // 2. 从二分搜索树中删除最大值所在节点, 返回最大值
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }
    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node){
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 3.从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }
    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e){
        if (node == null) {
            return null;
        } else if (node.e.compareTo(e)<0) {
            node.right = remove(node.right,e);
            return node;
        } else if (node.e.compareTo(e)>0) {
            node.left = remove(node.left, e);
            return node;
        }else {// e.compareTo(node.e) == 0
            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right=null;size--;
                return rightNode;
            }
            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;size --;
                return leftNode;
            }
            // 待删除节点左右子树均不为空的情况
            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点(successor后继节点；predecessor前驱节点)
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);//删掉的这个节点就是successor的前身,这里做了size--
            successor.left = node.left;
            node.left=node.right=null;
            return successor;
        }
    }

    //================ 打印二叉树 ===================
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    // 生成深度字符："---.."
    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }
}
