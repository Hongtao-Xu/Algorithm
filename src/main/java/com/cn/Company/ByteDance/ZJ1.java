package com.cn.Company.ByteDance;

/*
  36进制由0-9，a-z，共36个字符表示。
  要求按照加法规则计算出任意两个36进制正整数的和，如1b + 2x = 48 （解释：47+105=152）
  要求：不允许使用先将36进制数字整体转为10进制，相加后再转回为36进制的做法
 */
public class ZJ1 {
    class Solution {
        public String add36Strings(String num1, String num2) {
            int i = num1.length() - 1, j = num2.length() - 1, add = 0;
            StringBuilder sb = new StringBuilder();
            while (i >= 0 || j >= 0 || add != 0) {
                int x = i >= 0 ? getInt(num1.charAt(i)) : 0;
                int y = j >= 0 ? getInt(num2.charAt(j)) : 0;
                int res = x + y + add;
                sb.append(getChar(res % 36));
                add = res / 36;
                i--;
                j--;
            }
            return sb.reverse().toString();
        }

        //将十进制转为36进制字符
        public char getChar(int n) {
            if (n <= 9) {
                return (char) (n + '0');
            } else {
                return (char) (n - 10 + 'a');
            }
        }

        //将36进制字符转为十进制数字
        public int getInt(char c) {
            if (c <= '9') {
                return c - '0';
            } else {
                return 10 + (c - 'a');
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new ZJ1().new Solution();
//        String a = "1b", b = "2x", c;
        String a = "abbbb", b = "1", c;
        c = solution.add36Strings(a, b);
        System.out.println(c);
    }

}
