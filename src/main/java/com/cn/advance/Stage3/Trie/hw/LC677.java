package com.cn.advance.Stage3.Trie.hw;

import java.util.TreeMap;

/*
 * 677. 键值映射
 * https://leetcode-cn.com/problems/map-sum-pairs/
 */
public class LC677 {
    class MapSum {

        public class Node {
            public int value;
            public TreeMap<Character, Node> next;

            public Node(int value) {
                this.value = value;
                next = new TreeMap<>();
            }

            public Node() {
                this(0);
            }
        }

        private Node root;

        public MapSum() {
            root = new Node();
        }

        public void insert(String key, int val) {
            Node cur = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if (cur.next.get(c) == null)
                    cur.next.put(c, new Node());
                cur = cur.next.get(c);
            }
            cur.value = val;
        }

        public int sum(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (cur.next.get(c) == null)
                    return 0;
                cur = cur.next.get(c);
            }
            return sum(cur);//到最后一个字母
        }

        private int sum(Node node) {
            int res = node.value;
            for (char c:node.next.keySet())
                res += sum(node.next.get(c));
            return res;
        }
    }
}
