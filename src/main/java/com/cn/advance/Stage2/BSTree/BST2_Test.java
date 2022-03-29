package com.cn.advance.Stage2.BSTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 测试SBT各个方法
 */
public class BST2_Test {
    public static void main(String[] args) {
        //test_1();
        //test_2();
        test_3();
    }

    //测试二叉搜索树功能
    private static void test_1() {
        BST2<Integer> bst = new BST2<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num:nums)
            bst.add(num);
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
//        bst.preOrder();
//        System.out.println();
        //5 3 2 4 6 8

//        bst.inOrder();
//        System.out.println();
        //2 3 4 5 6 8

//        bst.postOrder();
//        System.out.println();
        //2 4 3 8 6 5

        bst.levelOrder();
        System.out.println();
        //5 3 6 2 4 8
    }

    //测试删除最大最小值
    private static void test_2(){
        BST2<Integer> bst = new BST2<>();
        Random random = new Random();
        int n=1000;
        //test removeMin
        for (int i = 0; i < n; i++)
            bst.add(random.nextInt(10000));
        List<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty())
            nums.add(bst.removeMin());
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMin test completed.");

        // test removeMax
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));
        nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMax());
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) < nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMax test completed.");
    }

    //测试删除任意值
    private static void test_3(){
        BST2<Integer> bst = new BST2<>();
        Random random = new Random();
        int n=20;
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(n));// 注意, 由于随机生成的数据有重复, 所以bst中的数据数量大概率是小于n的

        // order数组中存放[0...n)的所有元素
        Integer[] order = new Integer[n];
        for (int i=0;i<n;i++)order[i]=i;
        // 打乱order数组的顺序
        shuffle(order);

        // 按order[i]"乱序"删除[0...n)范围里的所有元素
        for( int i = 0 ; i < n ; i ++ ){
            if(bst.contains(order[i])){
                bst.remove(order[i]);
                System.out.println("After remove " + order[i] + ", size = " + bst.size() );
            }
        }
        // 最终整个二分搜索树应该为空
        System.out.println("Last Size: "+bst.size());

    }
    // 打乱数组顺序
    private static void shuffle(Object[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            //交换arr[]中i处和ops处的元素
            int ops = (int) (Math.random() * (i + 1));
            Object t = arr[ops];
            arr[ops]=arr[i];
            arr[i] = t;
        }
    }
}
