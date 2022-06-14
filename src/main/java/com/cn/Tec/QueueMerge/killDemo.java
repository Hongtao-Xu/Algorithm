package com.cn.Tec.QueueMerge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
 * 队列合并
 * 场景:秒杀高并发，用队列合并请求，拉高QPS
 */
public class killDemo {

    /**
     * 启动10个线程
     * 库存6个
     * 生成一个合并队列
     * 每个用户能拿到自己的请求响应
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        killDemo killDemo = new killDemo();
        //启动定时任务，异步线程
        killDemo.mergeJob();
        Thread.sleep(2000);

        List<Future<Result>> futureList = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10);//模拟并发
        //模拟10个线程
        for (int i = 0; i < 10; i++) {
            final Long orderId = i + 100L;
            final Long userId = Long.valueOf(i);
            Future<Result> future = executorService.submit(() -> {
                countDownLatch.countDown();
                return killDemo.operate(new UserRequest(orderId, userId, 1));
            });
            //把结果存起来
            futureList.add(future);
        }
        //获取每个响应的结果
        futureList.forEach(future -> {
            try {
                //每个用户最多等300ms
                Result result = future.get(300, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + ":客户端请求响应:" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 模拟数据库的库存 行数
    private Integer stock = 6;
    //阻塞队列
    private BlockingQueue<RequestPromise> queue = new LinkedBlockingDeque<>(10);

    /*
     * 用户库存扣减
     */
    public Result operate(UserRequest userRequest) throws InterruptedException {
        // TODO 阈值判断
        // TODO 队列的创建
        RequestPromise requestPromise = new RequestPromise(userRequest);
        boolean enqueueSuccess = queue.offer(requestPromise, 100, TimeUnit.MILLISECONDS);
        //进队失败
        if (!enqueueSuccess) {
            return new Result(false, "系统繁忙");
        }
        //进队成功
        synchronized (requestPromise) {//获取锁 ，方便后面wait()释放锁
            try {
                requestPromise.wait(200);
                if (requestPromise.getResult() == null) {
                    //通知上游，等待超时
                    return new Result(false, "等待超时");
                }
            } catch (InterruptedException e) {
                //通知上游，等待被中断
                return new Result(false, "被中断");
            }
        }
        //进队完成
        return requestPromise.getResult();
    }

    //定时任务，异步轮询，统一一次进行扣减
    public void mergeJob() {
        new Thread(() -> {
            List<RequestPromise> list = new ArrayList<>();
            while (true) {
                //不断轮询
                if (queue.isEmpty()) {
                    try {
                        //当队列为空时，等待10ms，不然CPU空转
                        Thread.sleep(10);
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //队列不为空，poll出一个加入list
                while (queue.peek() != null) {
                    list.add(queue.poll());
                }
                System.out.println(Thread.currentThread().getName() + ":合并扣减库存:" + list);
                //累计list到底要扣多少库存
                int sum = list.stream().mapToInt(e -> e.getUserRequest().getCount()).sum();
                // 库存足够
                if (sum <= stock) {
                    stock -= sum;
                    // notify 所有用户
                    list.forEach(requestPromise -> {
                        requestPromise.setResult(new Result(true, "ok"));
                        synchronized (requestPromise) {
                            requestPromise.notify();
                        }
                    });
                    continue;
                }
                //库存不够 sum>stock 退化为循环，一个一个处理
                for (RequestPromise requestPromise : list) {
                    int count = requestPromise.getUserRequest().getCount();
                    //当前用户扣减的库存足够
                    if (count <= stock) {
                        stock -= count;
                        requestPromise.setResult(new Result(true, "ok"));
                    } else {//当前用户扣减的库存不够
                        requestPromise.setResult(new Result(false, "库存不足"));
                    }
                    //notify 当前用户
                    synchronized (requestPromise) {
                        requestPromise.notify();
                    }
                }
                list.clear();
            }
        }, "mergeThread").start();
    }
}
