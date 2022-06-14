package com.cn.LeetCode;

import java.util.ArrayList;
import java.util.List;

/*
 * 72. 编辑距离
 * https://leetcode-cn.com/problems/edit-distance/
 */
public class LC72 {
    //1.自顶向下的递归
    class Solution {
        public int minDistance(String word1, String word2) {
            int len1 = word1.length(), len2 = word2.length();
            //终止条件
            if (len1 == 0 || len2 == 0)
                return Math.max(len1, len2);
            //最后一个字符相同，直接continue,继续向前比较
            if (word1.charAt(len1 - 1) == word2.charAt(len2 - 1))
                //注意substring[a,b)
                return minDistance(word1.substring(0, len1 - 1), word2.substring(0, len2 - 1));
            //真正的递归
            return 1 + minIn3item(
                    minDistance(word1, word2.substring(0, len2 - 1)),
                    minDistance(word1.substring(0, len1 - 1), word2),
                    minDistance(word1.substring(0, len1 - 1), word2.substring(0, len2 - 1))
            );
        }

        //3者求最小
        public int minIn3item(int l1, int l2, int l3) {
            int temp = Math.min(l1, l2);
            return Math.min(temp, l3);
        }
    }

    //2.自底向上的动态规划
    class Solution1 {
        public int minDistance(String word1, String word2) {
            int len1 = word1.length(), len2 = word2.length();

            List<List<Integer>> op = new ArrayList<>();

            //注意i的取值范围：0~len
            for (int i = 0; i < len1 + 1; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j < len2 + 1; j++) {
                    if (i == 0)
                        row.add(j);
                    else if (j == 0)
                        row.add(i);
                    else
                        row.add(0);
                }
                op.add(row);
            }
            //动态规划
            //注意i的取值范围：0~len
            for (int i = 0; i < len1; i++) {
                for (int j = 0; j < len2; j++) {
                    int temp;
                    if (word1.charAt(i) == word2.charAt(j)) {
                        temp = op.get(i).get(j);
                    } else {
                        temp = minIn3item(op.get(i + 1).get(j), op.get(i).get(j + 1), op.get(i).get(j)) + 1;
                    }
                    op.get(i + 1).set(j + 1, temp);
                }
            }
            return op.get(len1).get(len2);
        }

        //3者求最小
        public int minIn3item(int l1, int l2, int l3) {
            int temp = Math.min(l1, l2);
            return Math.min(temp, l3);
        }
    }

}
