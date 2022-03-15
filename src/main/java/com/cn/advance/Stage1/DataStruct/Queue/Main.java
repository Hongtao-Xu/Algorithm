package com.cn.advance.Stage1.DataStruct.Queue;

import java.util.Random;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/10/12  21:01
 */
public class Main {
    //ArrayQueue的测试用例：
    public static void main1(String[] args) {

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 0) {
                queue.dequeue();
            }
        }
    }

    //LoopQueue的测试用例：
    public static void main2(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    //时间复杂度对比：
    public static void main(String[] args) {
        int opCount = 100000;
        //数组队列
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue:"+time1 + "s");
        //循环队列
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue:"+time2 + "s");
        //循环队列
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue:"+time3 + "s");
    }

    private static double testQueue(Queue<Integer> q,int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        //入队
        for (int i = 0; i <opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        //出队
        for (int i = 0; i <opCount; i++)
            q.dequeue();
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

}
