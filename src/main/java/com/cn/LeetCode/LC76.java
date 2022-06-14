package com.cn.LeetCode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * 76. 最小覆盖子串
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class LC76 {

    //滑动窗口
    class Solution {

        Map<Character, Integer> ori = new HashMap<Character, Integer>();//存储目标字符串
        Map<Character, Integer> cnt = new HashMap<Character, Integer>();

        public String minWindow(String s, String t) {
            int tLen = t.length();
            int sLen = s.length();
            //1.初始化ori
            for (int i = 0; i < tLen; i++) {
                char c = t.charAt(i);
                ori.put(c, ori.getOrDefault(c, 0) + 1);
            }
            //初始[l,r]为空区间, 区间长度len=r-l+1
            //结构区间：[ansL,ansR)
            int l = 0, r = -1, ansL = -1, ansR = -1;
            int len = Integer.MAX_VALUE;//初始化为不可能达到的长度
            //2.1 右边先向右扩展
            while (r < sLen) {
                //先右扩
                ++r;
                //如果r对应的字符是目标字符，cnt中添加
                if (r < sLen && ori.containsKey(s.charAt(r))) {
                    cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
                }
                //2.2 左边向右缩小
                while (check() && l <= r) {

                    //维护滑动窗口
                    if (r - l + 1 < len) {
                        len = r - l + 1;
                        ansL = l;
                        ansR = l + len;

                    }
                    //如果l对应的字符是目标字符，cnt中移除
                    if (ori.containsKey(s.charAt(l))) {
                        cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                    }
                    //后左缩
                    ++l;
                }
            }
            //如果未更改，就返回""
            return ansL == -1 ? "" : s.substring(ansL, ansR);
        }

        public boolean check() {
            Iterator iter = ori.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Character key = (Character) entry.getKey();
                Integer val = (Integer) entry.getValue();

                if (cnt.getOrDefault(key, 0) < val) {
                    //目标字符串中的字符个数，大于截取出来的字符串中字符的个数，一定不满足
                    return false;
                }
            }
            return true;
        }
    }

}
