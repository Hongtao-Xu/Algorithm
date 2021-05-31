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

    /*1.0
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {//是否含有结果值的key
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    //1.1
    public  int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                if(j!=i&&(nums[i]+nums[j]==target)){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0};
    }

    /*2.0
    * 输入：n = 16
    * 输出：true
    * 解释：24 = 16
    * */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

     //定义单链表
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

      //3.0
    public ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode head=null,tail=null;
        int carry = 0;
        while (l1!=null||l2!=null){
            int n1 = l1 != null ? l1.val : 0;//l1不为空，n1=l1.val;l1为空，n1=0
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {//头结点为：空
                head = tail = new ListNode(sum % 10);
            }else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if(l1!=null){
                l1 = l1.next;
            }
            if(l2!=null){
                l2 = l2.next;
            }
        }
        if (carry>0){
            tail.next = new ListNode(carry);
        }
        return head;
    }
}
