package com.cd.chapter04.control.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author cd
 * @date 2019年4月3日 上午11:14:17
 * @desc 重入锁 提供了公平锁和非公平锁
 * 公平锁： 保证在锁等待的队列中各个线程是公平的，不会存在插队的情况，对锁的获取总是先进先出。
 * 非公平锁：不做这个保证，申请锁的线程可能插队，后申请锁的线程有可能先拿到锁。
 * 
 * 公平锁的实现代价比非公平锁的大，从性能上讲，非公平锁性能要好的多，因此，若无特殊要求，因优先选择非公平锁 
 * public ReentrantLock(boolean fair) 指定锁是否公平
 * 
 * lock(): 获得锁，如果锁已经被占用，则等待
 * lockInterruptibly(): 获得锁，但优先响应中断
 * tryLock(): 尝试获得锁，成功true 失败false,该方法不等待，立即返回
 * tryLock(long time, TimeUnit unit): 在给定的时间内尝试获得锁
 * unlock(): 释放锁
 */
public class ReentrantLockTest {
	static ReentrantLock lock = new ReentrantLock();
	private static Runnable createTask(){
		return new Runnable(){

			@Override
			public void run() {
				while(true){
					try {
						//if(lock.tryLock(500, TimeUnit.MILLISECONDS))
						//if(lock.tryLock())
						//lock.lock();
						lock.lockInterruptibly();
						{
							try{
								System.out.println("locked: "+ Thread.currentThread().getName());
								Thread.sleep(1000);
							}finally{
								lock.unlock();
								System.out.println("unlock: "+ Thread.currentThread().getName());
								break;
							}
						}
						
						/*else{//这段代码在lock()和lockInterruptibly()时删除
							System.out.println("unable to lock "+ Thread.currentThread().getName());
						}*/
					} catch (Exception e) {
						System.out.println("InterruptedException is "+ Thread.currentThread().getName());
					}
				}
			}
			
		};
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(createTask(), "T1");
		Thread t2 = new Thread(createTask(), "T2");
		t1.start();
		t2.start();
		Thread.sleep(600);
		t2.interrupt();//中断
	}
}
