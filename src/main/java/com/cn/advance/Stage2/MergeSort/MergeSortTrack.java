package com.cn.advance.Stage2.MergeSort;

import com.cn.advance.utils.ArrayGenerator;
import com.cn.advance.utils.SortingHelper;

import java.util.Arrays;

/*
 * 归并排序,追踪打印
 */
public class MergeSortTrack {
    public MergeSortTrack() {
    }


    public static <E extends Comparable<E>> void sort(E[] arr) {
        innerSort(arr, 0, arr.length - 1,0);
    }

    //主排序(递归调用)
    private static <E extends Comparable<E>> void innerSort(E[] arr, int l, int r, int depth) {

        // 生成深度字符串
        String depthString = generateDepthString(depth);
        // 1.打印当前 innerSort 处理的数组区间信息
        System.out.print(depthString);
        System.out.println(String.format("mergesort arr[%d, %d]", l, r));

        if (l >= r) return;
        int mid = l + (r - l) / 2;
        innerSort(arr,l,mid,depth+1);
        innerSort(arr,mid + 1, r,depth+1);

        // 2.打印这次 merge 要处理的区间范围
        System.out.print(depthString);
        System.out.println(String.format("merge arr[%d, %d] and arr[%d, %d]", l, mid, mid + 1, r));

        merge(arr, l, mid, r);

        // 3.打印 merge 后的数组
        System.out.print(depthString);
        System.out.print(String.format("after mergesort arr[%d, %d] :", l, r));
        for(E e: arr)
            System.out.print(e + " ");
        System.out.println();
    }

    // 合并两个有序的区间 arr[l, mid] 和 arr[mid + 1, r]
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {

        //复制一份，[...)
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);

        int i = l, j = mid + 1;

        // 每轮循环为 arr[k] 赋值
        for (int k = l; k <= r; k++) {
            //i往右，越界
            if (i > mid) {
                arr[k] = temp[j - l];j++;
            }
            //j往右，越界
            else if (j > r) {
                arr[k] = temp[i - l];i++;
            }
            //非越界,正常比较
            else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i - l];i++;
            }
            else {
                arr[k] = temp[j - l];j++;
            }

        }
    }
    
    private static String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
    

    public static void main(String[] args) throws Exception {
        int n = 4;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(MergeSortTrack.class, arr);
    }
}
