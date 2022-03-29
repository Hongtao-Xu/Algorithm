package com.cn.advance.Stage3.Trie.hw;

import com.cn.advance.Stage3.Trie.Trie;

import java.util.TreeMap;

/*
 *208. 实现 Trie (前缀树)
 *https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 *
 */
public class LC208 {

    class Trie {

        private class Node {
            public boolean isWord;
            public TreeMap<Character, Node> next;

            public Node(boolean isWord) {
                this.isWord = isWord;
                next = new TreeMap<>();
            }

            public Node() {
                this(false);
            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        // 向Trie中添加一个新的单词word
        public void insert(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next.get(c) == null)
                    cur.next.put(c, new Node());
                cur = cur.next.get(c);
            }
            if (!cur.isWord) {
                cur.isWord = true;
            }
        }

        // 查询单词word是否在Trie中
        public boolean search(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next.get(c) == null)
                    return false;
                cur = cur.next.get(c);
            }
            return cur.isWord;
        }

        public boolean startsWith(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (cur.next.get(c) == null)
                    return false;
                cur = cur.next.get(c);
            }
            return true;//一直没有return false，说明所有字母都匹配完了，一定是前缀或本身
        }
    }
}
