package com.cn.advance.Stage2.BSearchPlus;

/*
 * 二分查找变种：
 * Upper
 *
 * 技巧：
 * 对于不存在的情况，总是返回靠近target的哪个数字
 * ceil基于upper,所以返回右边的值
 * floor基于lower,所以返回左边的值
 */
public class Upper {
    private Upper() {}
    // > target 的最小值索引
    public static <E extends Comparable<E>> int upper(E[] data, E target) {
        int l = 0, r = data.length;
        // 在 data[l, r] 中寻找解
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) <= 0)
                l = mid + 1;//这一步，值一定改变
            else
                r = mid;
        }
        return l;
    }

    // 不存在，返回>target里的最小值索引(upper函数)
    //== target，返回相等值里的，最"大"的索引
    //ceil(5.0)=5;ceil(5.5)=6;向上取整
    public static <E extends Comparable<E>> int upper_ceil(E[] data, E target) {
        int u = upper(data, target);
        if (u - 1 >= 0 && data[u - 1].compareTo(target) == 0)
            return u - 1;
        return u;
    }

    // 不存在，返回>target里的最小值索引(upper函数)
    // == target，返回相等值里的，最小的索引
    public static <E extends Comparable<E>> int lower_ceil(E[] data, E target) {
        int l = 0, r = data.length;
        // 在 data[l, r] 中寻找解
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) < 0)
                l = mid + 1;
            else r = mid;
        }
        return l;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        System.out.println("==========Test Upper===========");
        for (int i = 0; i <= 6; i++)
            System.out.print(Upper.upper(arr, i) + " ");
        System.out.println();

        System.out.println("==========Test Upper_Ceil===========");
        for (int i = 0; i <= 6; i++)
            System.out.print(Upper.upper_ceil(arr, i) + " ");
        System.out.println();

        System.out.println("==========Test Lower_Ceil===========");
        for (int i = 0; i <= 6; i++)
            System.out.print(Upper.lower_ceil(arr, i) + " ");
        System.out.println();
    }
}
