package com.cn.advance.Stage3.Trie.hw;


import java.util.TreeMap;

/*
 * 211. 添加与搜索单词 - 数据结构设计
 * https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/
 */
public class LC211 {
    class WordDictionary {

        public class Node {
            public boolean isWord;
            public TreeMap<Character, Node> next;

            public Node(boolean isWord) {
                this.isWord = isWord;
                this.next = new TreeMap<>();
            }

            public Node() {
                this(false);
            }
        }

        private Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next.get(c) == null)
                    cur.next.put(c, new Node());
                cur = cur.next.get(c);
            }
            cur.isWord = true;
        }

        public boolean search(String word) {
            return match(root, word, 0);
        }

        //递归写法
        private boolean match(Node node, String word, int index) {
            if (index == word.length()) return node.isWord;
            char c = word.charAt(index);

            if (c != '.') {//c不等于.
                if (node.next.get(c) == null)
                    return false;
                return match(node.next.get(c), word, index + 1);
            } else {//c等于.
                for (char nextChar : node.next.keySet())
                    if (match(node.next.get(nextChar), word, index + 1)) {
                        return true;
                    }
                return false;
            }
        }
    }
}
