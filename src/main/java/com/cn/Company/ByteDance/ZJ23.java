package com.cn.Company.ByteDance;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
   ZJ23 找零
 */
public class ZJ23 {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();
        int n = Integer.parseInt(string);
        int money = (1024 - n);
        int cnt = 0;

        cnt += money / 64;
        money = money % 64;
        cnt += money / 16;
        money = money % 16;
        cnt += money / 4;
        money = money % 4;
        cnt += money / 1;
        System.out.println(cnt);
    }

}
