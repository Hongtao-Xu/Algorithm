package com.cn.advance.Stage3.Sort.Shell;

import com.cn.advance.utils.ArrayGenerator;
import com.cn.advance.utils.SortingHelper;

import java.util.Arrays;

/*
 * 希尔排序
 * 优化：
 * - 3重循环变2重循环
 * - H-Sequence,步长优化
 */
public class ShellSort3 {

    private ShellSort3() {
    }

    public static <E extends Comparable<E>> void sort(E[] data) {
        int h = 1;
        while (h < data.length) {
            h = h * 3 + 1;
            // 1, 4, 13, 40 ...
        }
        while (h >= 1) {//循环终止条件
            //对[h,n)进行插入排序
            for (int i = h; i < data.length; i++) {//i的起始位置为：第一个子数组的第二个元素
                E t = data[i];
                int j;
                for (j = i; j - h >= 0 && t.compareTo(data[j - h]) < 0; j -= h)
                    data[j] = data[j - 1];//不断挪动
                data[j] = t;//原来的数组被覆盖的只有j此时的位置
            }
            h = h / 3;//int自动去除小数位
        }
    }
    public static void main(String[] args) throws Exception {
        int n = 50000000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest(ShellSort2.class, arr);
        SortingHelper.sortTest(ShellSort3.class, arr2);
    }

}
