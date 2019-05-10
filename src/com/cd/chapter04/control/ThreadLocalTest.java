package com.cd.chapter04.control;

import java.util.Date;

/**
 * 
 * @author cd
 * @date 2019年4月3日 下午2:14:04
 * @desc ThreadLocal 是一种多线程间并发访问变量的解决方案，完全不提供锁，使用空间换时间的手段，
 *       为每个线程提供变量的独立副本，以保障线程安全，它不是一种数据共享的解决方案
 *       在高并发量或锁竞争激烈的场合，使用ThreadLocal可以在一定程度上减少锁竞争。
 * 
 *       void set(T value): 将此局部变量的当前线程副本中的值设置为指定值 T get(): 返回此线程局部变量的当前线程副本中的值
 *       void remove(): 移除此线程局部变量当前线程的值
 * 
 */
public class ThreadLocalTest implements Runnable {

	public static final ThreadLocal<Date> local = new ThreadLocal<Date>();

	private long time;

	public ThreadLocalTest(long time) {
		this.time = time;
	}

	@Override
	public void run() {
		Date d = new Date(time); // 必须在每个线程中新建
		for (int i = 0; i < 50000; i++) {
			local.set(d);// 设置每个线程的local值不会影响其它线程
			if (local.get().getTime() != time)
				System.out.println("id=" + time + ", local="
						+ local.get().getTime());
		}

	}

	public static void main(String[] args) {
		Thread t = new Thread(new ThreadLocalTest(1554272915637L));
		t.start();
	}
}
