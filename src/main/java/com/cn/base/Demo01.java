package com.cn.base;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
 *@program:基础算法题01
 *@author: xu-hongtao
 *@Time: 2021/5/30  22:15
 */
public class Demo01 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    public  int[] twoSum2(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                if(j!=i&&(nums[i]+nums[j]==target)){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0};
    }
}
