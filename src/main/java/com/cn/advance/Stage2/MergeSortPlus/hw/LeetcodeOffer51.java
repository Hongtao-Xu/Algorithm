package com.cn.advance.Stage2.MergeSortPlus.hw;

import java.util.Arrays;

/*
 * 剑指 Offer 51. 数组中的逆序对
 */
public class LeetcodeOffer51 {
    class Solution {
        //全局变量
        private int res;

        public int reversePairs(int[] nums) {
            int[] temp = Arrays.copyOf(nums, nums.length);
            res=0;
            sort(nums, 0, nums.length-1, temp);
            return res;
        }

        private void sort(int[] arr, int l, int r, int[] temp) {
            if (l >= r) return;

            int mid = l + (r - l) / 2;
            sort(arr, l, mid, temp);
            sort(arr, mid + 1, r, temp);

            if (arr[mid] > arr[mid + 1])
                merge(arr, l, mid, r, temp);
        }

        private void merge(int[] arr, int l, int mid, int r, int[] temp) {
            //辅助的aux
            System.arraycopy(arr, l, temp, l, (r-l+1));

            int i = l,j = mid + 1;
            for (int k = l; k <=r; k++) {
                if (i>mid){
                    arr[k]=temp[j];j++;
                }else if(j> r){
                    arr[k]=temp[i];i++;
                }else if(temp[i]<=temp[j]){
                    arr[k]=temp[i];i++;
                }else {
                    arr[k]=temp[j];j++;
                    res+=(mid-i+1);
                }
            }
        }
    }

    //优化：不使用全局变量，没有状态
    class Solution2 {

        public int reversePairs(int[] nums) {
            int[] temp = Arrays.copyOf(nums, nums.length);
            return sort(nums, 0, nums.length-1, temp);
        }

        private int sort(int[] arr, int l, int r, int[] temp) {
            if (l >= r) return 0;

            int res=0;
            int mid = l + (r - l) / 2;
            res +=sort(arr, l, mid, temp);
            res +=sort(arr, mid + 1, r, temp);

            if (arr[mid] > arr[mid + 1])
                res +=merge(arr, l, mid, r, temp);
            return res;
        }

        private int merge(int[] arr, int l, int mid, int r, int[] temp) {
            //辅助的aux
            System.arraycopy(arr, l, temp, l, (r-l+1));

            int i = l,j = mid + 1,res=0;
            for (int k = l; k <=r; k++) {
                if (i>mid){
                    arr[k]=temp[j];j++;
                }else if(j> r){
                    arr[k]=temp[i];i++;
                }else if(temp[i]<=temp[j]){
                    arr[k]=temp[i];i++;
                }else {
                    arr[k]=temp[j];j++;
                    res+=(mid-i+1);
                }
            }
            return res;
        }
    }


}

//时间超出限制
/*public int reversePairs(int[] nums) {
    int res = 0;
    for (int i = 0; i < nums.length; i++)
        for (int j = i + 1; j < nums.length; j++)
            if (nums[i] > nums[j]) res++;
    return res;
}*/
