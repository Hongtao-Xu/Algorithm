package com.cn.LeetCode;

import java.util.ArrayList;
import java.util.List;

/*
 * 17. 电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class LC17 {
    //1.法一：回溯
    class Solution {
        //最终输出结果的list
        List<String> res = new ArrayList<>();
        //一个映射表，第二个位置是"abc“,第三个位置是"def"。。。
        //这里也可以用map，用数组可以更节省点内存
        String[] letter_map = {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public  List<String> letterCombinations(String digits) {
            //注意边界条件
            if (digits == null || digits.length() == 0) return res;
            iterStr(digits, new StringBuilder(), 0);
            return res;
        }

        //递归函数
        private void iterStr(String str, StringBuilder letter, int index) {
            if (index == str.length()) {//2==2
                res.add(letter.toString());
                return;
            }
            char c = str.charAt(index);
            int pos = c - '0';
            //1.map_string:abc
            //2.map_string:def
            String map_string = letter_map[pos];
            for (int i = 0; i < map_string.length(); i++) {
                letter.append(map_string.charAt(i));
                iterStr(str, letter, index + 1);
                letter.deleteCharAt(letter.length() - 1);
            }
        }
    }
    //2.法二：队列
    static class Solution2 {
        public  List<String> letterCombinations(String digits) {
            List<String> res = new ArrayList<>();
            if (digits==null||digits.length()==0)return res;
            String[] letter_map = {
                    " ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
            };
            //先往队列中加入一个空字符
            res.add("");
            for (int i = 0; i < digits.length(); i++) {
                String letter = letter_map[digits.charAt(i) - '0'];//abc,def
                int size = res.size();
                //计算出队列长度后，将队列中的每个元素挨个拿出来
                for (int j = 0; j < size; j++) {
                    String temp = res.remove(0);//temp进行组合
                    //然后跟"def"这样的字符串拼接，并再次放到队列中
                    for (int k = 0; k < letter.length(); k++) {
                        res.add(temp + letter.charAt(k));
                    }
                }
            }
            return res;
        }

    }
}

class Test{
    public static void main(String[] args) {
        LC17.Solution2 solution2 = new LC17.Solution2();
        List<String> strings = solution2.letterCombinations("23");
        System.out.println(strings);
    }
}
