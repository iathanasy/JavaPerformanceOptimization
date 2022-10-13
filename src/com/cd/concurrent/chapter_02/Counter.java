package com.cd.concurrent.chapter_02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一个基于CAS线程安全的计数器 方法safeCount和一个非线程安全的计数器count
 */
public class Counter {
    private AtomicInteger atomicCount = new AtomicInteger(0);
    private int count = 0;

    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++){
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(thread);
        }

        // 启动
        for (Thread t: ts){
            t.start();
        }

        // 等待所有线程执行完
        for (Thread t: ts){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.count);
        System.out.println(cas.atomicCount.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount(){
        for (;;){
            int count = atomicCount.get();
            boolean suc = atomicCount.compareAndSet(count, ++count);
            if(suc) break;
        }
    }

    /**
     * 非线程安全计数
     */
    private void count(){
        count++;
    }
}
