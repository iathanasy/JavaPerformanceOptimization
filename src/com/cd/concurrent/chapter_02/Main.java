package com.cd.concurrent.chapter_02;

/**
 *
 * // Thread 类中的实例方法，持有线程实例引用即可检测线程中断状态
 * public boolean isInterrupted() {}
 *
 * // Thread 中的静态方法，检测调用这个方法的线程是否已经中断
 * // 注意：这个方法返回中断状态的同时，会将此线程的中断状态重置为 false
 * // 所以，如果我们连续调用两次这个方法的话，第二次的返回值肯定就是 false 了
 * public static boolean interrupted() {}
 *
 * // Thread 类中的实例方法，用于设置一个线程的中断状态为 true
 * public void interrupt() {}
 *
 * 如果线程处于以下三种情况，那么当线程被中断的时候，能自动感知到：
 *  来自 Object 类的 wait()、wait(long)、wait(long, int)，
 *  来自 Thread 类的 join()、join(long)、join(long, int)、sleep(long)、sleep(long, int)
 *
 * 这几个方法的相同之处是，方法上都有: throws InterruptedException
 * 如果线程阻塞在这些方法上（我们知道，这些方法会让当前线程阻塞），这个时候如果其他线程对这个线程进行了中断，
 * 那么这个线程会从这些方法中立即返回，抛出 InterruptedException 异常，同时重置中断状态为 false。
 *
 *
 * 线程中断
 * 参考：https://www.javadoop.com/post/AbstractQueuedSynchronizer-2
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->{
            // 检测线程中断状态
//            while (!Thread.currentThread().isInterrupted()){
            while (!Thread.interrupted()){
                try {
                    Thread.sleep(1000);
                    System.out.println("sleep 1000");
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    /**
                     * 捕捉到异常时，中断状态在这里为false，所以你要不设置中断位的话，即使主线程中尝试中断了，但在这里还是跳不出while循环
                     * 注释和不注释 // 注释就一直输出 `sleep 1000`,
                     * 不注释过1秒后线程就都停止了，也不输出 `sleep 1000`
                     */
                     Thread.currentThread().interrupt();//作用：设置为true
                    // 注意此处会重置状态为false
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        });
        t1.start();
        //等t1线程运行起来
        Thread.sleep(1500);
        t1.interrupt();//中断t1线程
    }
}
