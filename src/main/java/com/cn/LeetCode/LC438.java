package com.cn.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 438. 找到字符串中所有字母异位词
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 */
public class LC438 {

    //1.滑动窗口
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            int sLen = s.length(), pLen = p.length();
            if (pLen > sLen)
                return new ArrayList<Integer>();

            //返回结果
            List<Integer> res = new ArrayList<>();
            int[] sCount = new int[26];
            int[] pCount = new int[26];
            //s[0:pLen]
            for (int i = 0; i < pLen; i++) {
                sCount[s.charAt(i) - 'a']++;
                pCount[p.charAt(i) - 'a']++;
            }
            if (Arrays.equals(sCount, pCount)) {
                res.add(0);
            }
            for (int i = 0; i < sLen - pLen; i++) {
                sCount[s.charAt(i) - 'a']--;
                sCount[s.charAt(i + pLen) - 'a']++;
                if (Arrays.equals(sCount, pCount)) {
                    res.add(i + 1);
                }
            }
            return res;
        }
    }


    //2.滑动窗口改进，差值
    class Solution1 {
        public List<Integer> findAnagrams(String s, String p) {
            int sLen = s.length(), pLen = p.length();
            if (pLen > sLen)
                return new ArrayList<Integer>();

            //返回结果
            List<Integer> res = new ArrayList<>();
            int[] count = new int[26];
            //前pLen个字符
            for (int i = 0; i < pLen; i++) {
                count[s.charAt(i) - 'a']++;
                count[p.charAt(i) - 'a']--;
            }
            int differ = 0;
            for (int i = 0; i < 26; i++) {
                if (count[i] !=0) {
                    differ++;
                }
            }
            if (differ == 0)
                res.add(0);
            for (int i = 0; i < sLen - pLen; i++) {
                int left = count[s.charAt(i) - 'a'];
                if (left == 1) {
                    differ--;
                } else if (left == 0) {
                    differ++;
                }
                count[s.charAt(i) - 'a']--;

                int right = count[s.charAt(i + pLen) - 'a'];
                if (right == -1) {
                    differ--;
                } else if (right == 0) {
                    differ++;
                }
                count[s.charAt(i + pLen) - 'a']++;
                if (differ == 0)
                    res.add(i + 1);
            }
            return res;
        }
    }
}
