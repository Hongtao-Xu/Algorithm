package com.cn.advance.Stage3.HashMap.hw;

/*
 * 387. 字符串中的第一个唯一字符
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 *
 */
public class LC387 {

    class Solution {
        public int firstUniqChar(String s) {
            int[] freq = new int[26];
            for (int i = 0; i < s.length(); i++)
                freq[s.charAt(i) - 'a']++;

            for (int j = 0; j <s.length() ;j++)
                if (freq[s.charAt(j)-'a']==1)
                    return j;
            return -1;
        }
    }
}
