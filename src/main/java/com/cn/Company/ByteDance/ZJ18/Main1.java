package com.cn.Company.ByteDance.ZJ18;

import java.util.Scanner;

/*
 * 万万没想到之聪明的编辑
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < num; i++) {
            String s = sc.nextLine();
            String result = mend(s);
            System.out.println(result);
        }
    }

    private static String mend(String target) {
        char[] chars = target.toCharArray();
        StringBuilder builder = new StringBuilder();
        int k = 0;
        for (int i = 0; i < chars.length; i++) {
            chars[k] = chars[i];
            builder.append(chars[k]);
            k++;
            // 先满足规则1
            if (k >= 3 && chars[k - 3] == chars[k - 2] && chars[k - 2] == chars[k - 1]) {
                builder.deleteCharAt(k - 1);
                k--;
            }
            // 再满足规则2
            if (k >= 4 && chars[k - 4] == chars[k - 3] && chars[k - 2] == chars[k - 1]) {
                builder.deleteCharAt(k - 1);
                k--;
            }
        }
        return String.valueOf(builder);
    }
}
