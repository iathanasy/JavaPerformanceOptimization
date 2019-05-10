package com.cd.chapter04.control.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 
 * @author cd
 * @date 2019年4月3日 上午11:42:48
 * @desc
 * 
 *       在读多写少的场合，使用读写锁可以分离读操作和写操作，使所有读操作间真正并行，
 */
public class ReadWriteLockTest {

	private static Lock lock = new ReentrantLock();
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private static Lock readLock = readWriteLock.readLock();
	private static Lock writeLock = readWriteLock.writeLock();
	Object value = null;

	// 使用重入锁同步读写操作
	public Object handleRead() throws InterruptedException {
		try {
			lock.lock();// 模拟读操作
			Thread.sleep(1); // 读操作的耗时越多，读写锁的优势越明显

			return value;// 因为重入锁总是要排队等待锁，而读锁可以绝对并行
		} finally {
			lock.unlock();
		}
	}

	public void handleWrite(int index) throws InterruptedException {
		try {
			lock.lock();// 模拟写操作
			Thread.sleep(1);
			value = index;
		} finally {
			lock.unlock();
		}
	}

	// 使用读写锁同步读写操作
	public Object handleRead2() throws InterruptedException {
		try {
			readLock.lock();// 读锁
			Thread.sleep(1);
			return value;
		} finally {
			readLock.unlock();
		}
	}

	public void handleWrite2(int index) throws InterruptedException {
		try {
			writeLock.lock();// 写锁
			Thread.sleep(1);
			value = index;
		} finally {
			writeLock.unlock();
		}
	}

	public static void main(String[] args) {
		final ReadWriteLockTest r = new ReadWriteLockTest();

		Thread t = new Thread() {
			@Override
			public void run() {
				long begin = System.currentTimeMillis();
				for (int i = 0; i < 2000; i++) {
					try {
						/*
						 * r.handleWrite(i); r.handleRead();
						 */
						r.handleWrite2(i);
						r.handleRead2();
						// System.out.println(r.handleRead());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("seep: "
						+ (System.currentTimeMillis() - begin));

			}
		};
		t.start();

	}
}
