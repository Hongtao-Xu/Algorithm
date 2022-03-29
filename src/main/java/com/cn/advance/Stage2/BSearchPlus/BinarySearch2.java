package com.cn.advance.Stage2.BSearchPlus;

/*
 * 二分查找，一个数存不存在
 * 在有序数组中判断一个target是否存在，存在则返回相应索引，不存在则返回-1
 */
public class BinarySearch2 {
    private BinarySearch2() {
    }

    // 使用求解 >= target 的最小值索引的思路，实现 search
    public static <E extends Comparable<E>> int search2(E[] data, E target){
        // 以下代码是求解 >= target 的最小值索引
        int l = 0, r = data.length;

        // 在 data[l, r] 中寻找解
        while(l < r){
            int mid = l + (r - l) / 2;
            if(data[mid].compareTo(target) < 0)
                l = mid + 1;
            else
                r = mid;
        }

        // l 是 >= target 的最小值索引
        // 如果 data[l] == target，则返回 l；否则返回 -1
        if(l < data.length && data[l].compareTo(target) == 0)
            return l;
        return -1;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        System.out.println("==========Test search2===========");
        for (int i = 0; i <= 6; i++)
            System.out.print(BinarySearch2.search2(arr, i) + " ");
        System.out.println();

    }

}
