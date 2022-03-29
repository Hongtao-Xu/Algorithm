package com.cn.advance.Stage2.BSearchPlus;

/*
 * 二分查找变种：
 * Lower
 */
public class Lower {
    private Lower() {
    }

    // < target 的最大值索引
    public static <E extends Comparable<E>> int lower(E[] data, E target) {
        int l = -1, r = data.length - 1;
        // 在 data[l, r] 中寻找解
        while (l < r) {
            //int mid = l+(r-l)/2;//此方法在遇到取下界的时候会有bug
            int mid = l + (r - l + 1) / 2;
            if (data[mid].compareTo(target) < 0)//mid>=target
                l = mid;//此处值应该改变，但由于int的取整，未改变
            else r = mid - 1;
        }
        return l;
    }

    // 不存在，返回<target里的最大值索引(lower函数)
    // == target，返回相等值里的，最"小"的索引
    public static <E extends Comparable<E>> int lower_floor(E[] data, E target) {
        int l = lower(data, target);
        if (l + 1 < data.length && data[l + 1] == target)
            return l + 1;
        return l;
    }

    // 不存在，返回<target里的最大值索引(upper函数)
    //== target，返回相等值里的，最"大"的索引
    public static <E extends Comparable<E>> int upper_floor(E[] data, E target) {
        int l = -1, r = data.length - 1;
        // 在 data[l, r] 中寻找解
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (data[mid].compareTo(target) <= 0)
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        System.out.println("==========Test Lower===========");
        for (int i = 0; i <= 6; i++)
            System.out.print(Lower.lower(arr, i) + " ");
        System.out.println();

        System.out.println("==========Test Lower_Floor===========");
        for (int i = 0; i <= 6; i++)
            System.out.print(Lower.lower_floor(arr, i) + " ");
        System.out.println();

        System.out.println("==========Test Upper_Floor===========");
        for (int i = 0; i <= 6; i++)
            System.out.print(Lower.upper_floor(arr, i) + " ");
        System.out.println();
    }

}
