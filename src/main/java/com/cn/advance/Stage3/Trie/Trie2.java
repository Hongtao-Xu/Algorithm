package com.cn.advance.Stage3.Trie;

import java.util.HashMap;

/*
 * 字典树
 * 使用HashMap的Trie
 */
public class Trie2 {
    private class Node{

        public boolean isWord;
        public HashMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new HashMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie2(){
        root = new Node();
        size = 0;
    }

    // 向Trie中添加一个新的单词word
    public void add(String word){

        Node cur = root;
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        if(!cur.isWord){
            cur.isWord = true;
            size ++;
        }
    }

    // 查询单词word是否在Trie中
    public boolean contains(String word){

        Node cur = root;
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
    // 获得Trie中存储的单词数量
    public int getSize() {
        return size;
    }
}