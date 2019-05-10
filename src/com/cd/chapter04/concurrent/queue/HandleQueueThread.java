package com.cd.chapter04.concurrent.queue;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue 核心方法 适用于多线程间的数据共享 offer(object): 添加，有足够的空间返回true
 * 否则返回false。(不阻塞当前执行方法的线程) offer(E o,long timeout, TimeUnit unit):
 * 设定等待时间，在指定的时间内，还不能往队列中加入则返回失败。 put(object):
 * 添加，没有足够的空间则调用该方法的线程被阻塞直到里面有空闲时间在继续 poll(time):
 * 取走排在首位的对象。若不能立即取出，则可以等time规定的时间，取不到时返回null. poll(long timeout, TimeUnit
 * unit): 取出一个队首对象，如果在指定的时间内，队列一旦有数据可取，则立即返回数据，超时后依然没有，则返回失败 take():
 * 取走排在首位的对象，若为空，阻断进入等待状态，直到有新的数据被加入。 drainTo():
 * 一次性获取所有可用对象(可指定获取个数)，可以提升获取数据的效率，不需要多次分批加锁或释放锁
 * 
 * 主要提供了两个实现类 ArrayBlockingQueue: 基于数组阻塞队列实现，维护了一个定长数组，用于缓存队列中的数据对象
 * LinkedBlockingQueue: 基于链表阻塞队列，内部维持着一个数据缓冲队列(一个链表构成)
 * 
 * 
 * @author cd
 * @date 2019年4月2日 下午4:05:06
 * @desc
 */
public class HandleQueueThread implements Runnable {

	protected String name;
	Random r = new Random();
	Queue q;

	public HandleQueueThread(String name) {
		this.name = name;
	}

	public HandleQueueThread() {
		// initLinkedBlockingQueue();
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 500; i++) {
				handleQueue(r.nextInt(1000));// 每个线程进行500次get()保证高并发
			}
			Thread.sleep(r.nextInt(100));
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public Object handleQueue(int index) {
		q.add(index);// 添加
		q.poll();// 消费
		return null;
	}

	public void initConcurrentLinkedQueue() {
		q = new ConcurrentLinkedQueue();// 初始化 高性能队列
										// testConcurrentLinkedQueuespend time:
										// 280
		for (int i = 0; i < 300; i++)
			q.add(i);
	}

	public void initLinkedBlockingQueue() {
		q = new LinkedBlockingQueue();// 初始化 多线程间的数据共享
										// testLinkedBlockingQueuespend time:
										// 300
		for (int i = 0; i < 300; i++)
			q.add(i);
	}
}
