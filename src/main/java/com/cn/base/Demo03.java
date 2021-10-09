package com.cn.base;

/*
 *@program:最长回文子串
 *@author: xu-hongtao
 *@Time: 2021/6/15  11:39
 */
public class Demo03 {


    /*中心扩散*/
    public String huiwen(String s) {
        //边界条件判断
        if (s.length() < 2)
            return s;
        //start表示  最长回文串开始的位置
        //maxLen表示 最长回文串的长度
        int begin = 0, maxLen = 1;
        int len = s.length();
        char[] charArray = s.toCharArray();
        //中心扩散
        for (int i = 0; i < len-1; i++) {
            int oddlen = expandAroundCenter(charArray, i, i);
            int evenlen = expandAroundCenter(charArray, i, i + 1);
            int curManLen = Math.max(oddlen, evenlen);
            if(curManLen>maxLen){
                maxLen = curManLen;
                //此步要画图求解
                begin = i - (maxLen - 1) / 2;
            }
        }
        //截取回文子串
        return s.substring(begin, begin + maxLen);
    }
    public int expandAroundCenter(char[] charArray, int left, int right) {
        //当left=right时，回文串是一个奇数
        //当right=left+1,回文串是一个偶数
        int len = charArray.length;
        int i = left;int j = right;
        while (i>=0&&j<len) {
            if(charArray[i]==charArray[j]){
                i--;
                j++;
            }else {
                break;
            }
        }
        //回文串的长度：j-i+1-2
        return j - i - 1;
    }



    /*暴力解法*/
    public String huiwen2(String s){
        int len = s.length();
        if (len<2) {
            return s;
        }
        int maxLen = 1;
        int begin =0;
        //string 转为chararray
        char[] charArray = s.toCharArray();
        //枚举所有长度严格大于1的子串 charArry[i..j]
        for (int i= 0;i<len-1;i++){
            for (int j=i+1; j < len; j++) {
                if (j-i+1>maxLen&&validPalindromic(charArray,i,j)){
                    maxLen=j-i+1;
                    begin=i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
    /*
    * 验证子串 s[left..right]是否为回文串
    * */
    private boolean validPalindromic(char[] charArray,int left ,int right){
        while (left<right){
            if (charArray[left]!=charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /*动态规划*/
    public String huiwen3(String s) {
        //边界条件判断
        if (s.length() < 2)
            return s;
        //begin  最长回文串开始的位置
        //maxLen最长回文串的长度
        int begin = 0, maxLen = 1;
        int len = s.length();
        char[] charArray = s.toCharArray();

        boolean[][] dp = new boolean[len][len];//默认全是false
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;//对角线一定为true
        }
        //填表
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i]!=charArray[j]) {
                    dp[i][j] = false;
                }else {
                    if (j-i<3) {//字符串字符：个数为1，或2
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                //每次记录maLen的值
                if(dp[i][j]&&j-i+1>maxLen){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        //截取回文子串
        return s.substring(begin, begin + maxLen);
    }
}
