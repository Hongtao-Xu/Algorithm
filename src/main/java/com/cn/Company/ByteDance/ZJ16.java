package com.cn.Company.ByteDance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 数列的和
 */
public class ZJ16 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        while ((s = br.readLine()) != null) {
            String[] temp = s.split(" ");
            int n = Integer.parseInt(temp[0]);
            int m = Integer.parseInt(temp[1]);
            double res = solution(n, m);
            System.out.printf("%.2f\n", res);
        }
    }

    static double solution(int n, int m) {
        double sum = n;
        double t = n;
        for (int i = 1; i < m; i++) {
            t = Math.sqrt(t);
            sum = sum + t;
        }
        return sum;
    }
}
