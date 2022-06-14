package com.cn.Company.ByteDance.ZJ18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 万万没想到之聪明的编辑
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i++) {
            System.out.println(mend(br.readLine()));
        }
    }

    private static String mend(String target) {
        char[] chars = target.toCharArray();
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            // AAA型的情况
            if (i > 1 && chars[i] == chars[index - 1] && chars[index - 1] == chars[index - 2]) {
                chars[i] = '\0';
            }
            // AABB型的情况
            else if (i > 2 && chars[i] == chars[index - 1] && chars[index - 2] == chars[index - 3]) {
                chars[i] = '\0';
            } else {
                chars[index] = chars[i];
                index++;
            }
        }
        return String.valueOf(chars).substring(0, index);
    }
}
