package com.cn.LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * 剑指 Offer II 058. 日程表
 * https://leetcode-cn.com/problems/fi9suh/
 */
public class Offer58 {
    //[start,end)

    //1.暴力List
    class MyCalendar1 {

        private List<int[]> list;

        public MyCalendar1() {
            this.list = new ArrayList<int[]>();
        }

        public boolean book(int start, int end) {
            for (int[] date : list) {
                if (start < date[1] && end > date[0]) return false;
            }
            list.add(new int[]{start, end});
            return true;
        }
    }

    //2.TreeMap的底层是红黑树
    class MyCalendar2 {
        //             <start  , end>
        private TreeMap<Integer, Integer> map;

        public MyCalendar2() {
            this.map = new TreeMap<Integer, Integer>();
        }

        public boolean book(int start, int end) {
            //比当前start小的当中最大的那个区间
            Map.Entry<Integer, Integer> entry1 = map.floorEntry(start);
            //比当前start大的当中最小的那个区间
            Map.Entry<Integer, Integer> entry2 = map.ceilingEntry(start);
            if (entry1 != null && entry1.getValue() > start) {
                return false;
            }
            if (entry2 != null && entry2.getKey() < end) {
                return false;
            }
            map.put(start, end);
            return true;
        }
    }

    //3.自定义BST
    class MyCalendar3 {
        public class TreeNode {
            TreeNode left;
            TreeNode right;
            int start;
            int end;

            public TreeNode(int start, int end) {
                this.start = start;
                this.end = end;
            }

            public TreeNode() {
            }
        }

        TreeNode root;

        //不维护size变量
        public MyCalendar3() {
        }

        public boolean book(int start, int end) {
            if (root == null) {
                this.root = new TreeNode(start, end);
                return true;
            }
            //类似二分搜索BST
            TreeNode cur = root;
            while (cur != null) {
                if (end <= cur.start) {
                    if (cur.left == null) {
                        cur.left = new TreeNode(start, end);
                        return true;
                    }
                    cur = cur.left;
                } else if (start >= cur.end) {
                    if (cur.right == null) {
                        cur.right = new TreeNode(start, end);
                        return true;
                    }
                    cur = cur.right;
                } else {
                    return false;
                }
            }
            return false;
        }
    }
}
