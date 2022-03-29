package com.cn.advance.Stage2.SetAndMap.Set.hw;

import java.util.TreeSet;

/*
 * Leetcode 804.唯一摩尔斯密码词
 * https://leetcode.com/problems/unique-morse-code-words/description/
 */
public class Solution {
    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        //基于树的Set实现
        TreeSet<String> set = new TreeSet<>();
        for(String word: words){
            StringBuilder res = new StringBuilder();
            for(int i = 0 ; i < word.length() ; i ++)
                res.append(codes[word.charAt(i) - 'a']);

            set.add(res.toString());
        }
        return set.size();
    }
}
