package com.cn.LeetCode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * 146. LRU 缓存
 *https://leetcode.cn/problems/lru-cache/
 */
public class LC146 {

    //1.使用封装好的双向链表
    class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache(int capacity) {
            //true:按照读取的顺序进行排序
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            //什么时候移除
            return size() > capacity;
        }
    }

    //2.手动实现
    class LRUCache1 {

        class ListNode {
            private int key;
            private int value;
            private ListNode prev;
            private ListNode next;

            public ListNode() {
            }

            public ListNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private int size;
        private int capacity;
        Map<Integer, ListNode> cache = new HashMap<>();
        ListNode dummyHead, dummyTail;

        public LRUCache1(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            dummyHead = new ListNode();
            dummyTail = new ListNode();
            //连接头尾节点
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        public int get(int key) {
            ListNode c = cache.get(key);
            if (c == null) {//cache中没有
                return -1;
            }
            moveToHead(c);//get访问后，直接移动到头部
            return c.value;
        }

        public void put(int key, int value) {
            ListNode c = cache.get(key);
            if (c == null) {//没有节点，直接创建
                ListNode node = new ListNode(key, value);
                cache.put(key, node);
                addToHead(node);//新加入的节点，也是在头部
                size++;//维护size
                if (size > capacity) {
                    ListNode tail = removeTail();
                    cache.remove(tail.key);//放不下了，优先删除尾部节点
                    size--;
                }
            } else {//有节点，直接更新
                c.value = value;
                moveToHead(c);//更新了值，也移动到头部
            }
        }


        //对外接口：将节点移动到头结点
        private void moveToHead(ListNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void removeNode(ListNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToHead(ListNode node) {
            node.next = dummyHead.next;
            node.prev = dummyHead;
            dummyHead.next.prev = node;
            dummyHead.next = node;
        }

        private ListNode removeTail() {
            ListNode res = dummyTail.prev;
            removeNode(res);
            return res;
        }
    }

}
