package com.cn.advance.Stage2.MergeSortPlus;

import com.cn.advance.Stage2.MergeSort.MergeSort;
import com.cn.advance.utils.ArrayGenerator;
import com.cn.advance.utils.SortingHelper;

import java.util.Arrays;

/*
 * 归并排序-优化3
 * E[] temp = Arrays.copyOfRange(arr, l, r + 1);
 * 每一次开辟temp空间,都会造成内存消耗，处理数据较大后，对性能是比较大的消耗
 * - 解决：
 * 开辟一块较大的公共空间
 */
public class MergeSort4 {
    private MergeSort4() {
    }

    /**
     * 提供给用户接口
     *
     * @param arr 用户传入要排序的数组
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        innerSort(arr, 0, arr.length - 1, temp);
    }

    //主排序(递归调用)
    private static <E extends Comparable<E>> void innerSort(E[] arr, int l, int r, E[] temp) {
        //最终条件:数组中只有0或1个元素，不用排序，直接返回
        if (l >= r) return;

        //int mid= (l+r)/2;
        //进阶版：不会出现数字越界，l+r超过int所能表达的最大数字
        int mid = l + (r - l) / 2;

        innerSort(arr, l, mid, temp);
        innerSort(arr, mid + 1, r, temp);
        //优化1
        if (arr[mid].compareTo(arr[mid + 1]) > 0)//如果 arr[mid]小于arr[mid+1]，就不必merge了
            merge(arr, l, mid, r, temp);
    }

    // 合并两个有序的区间 arr[l, mid] 和 arr[mid + 1, r]
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {

        //由于完成merge所需要的temp辅助空间的大小和arr一样,
        // 所以我们也不需要处理temp索引的偏移量
        System.arraycopy(arr, l, temp, l, (r - l + 1));

        int i = l, j = mid + 1;

        // 每轮循环为 arr[k] 赋值
        for (int k = l; k <= r; k++) {
            //i往右，越界
            if (i > mid) {
                arr[k] = temp[j];j++;
            }
            //j往右，越界
            else if (j > r) {
                arr[k] = temp[i];i++;
            }
            //非越界,正常比较
            else if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];i++;
            } else {
                arr[k] = temp[j];j++;
            }

        }
    }

    public static void main(String[] args) throws Exception {
        int n = 5000000;

        System.out.println("Random Array:");
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest(MergeSort.class, arr);
        SortingHelper.sortTest(MergeSort4.class, arr2);
    }
}
