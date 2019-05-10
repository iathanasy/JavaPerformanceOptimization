package com.cd.chapter04.thread;

import java.util.List;
import java.util.Vector;

/**
 * 
 * @author cd
 * @date 2019年4月2日 上午9:53:26
 * @desc 线程池
 */
public class ThreadPool {

	private static ThreadPool instance = null;

	// 空闲的线程队列
	private List<PThread> idleThreads;
	// 已有线程总数
	private int threadCounter;
	private boolean isShutDown = false;

	private ThreadPool() {
		this.idleThreads = new Vector<PThread>(5);
		threadCounter = 0;
	}

	public int getCreatedThreadsCount() {
		return threadCounter;
	}

	// 获取线程池实例
	public synchronized static ThreadPool getInstance() {
		if (instance == null)
			instance = new ThreadPool();
		return instance;
	}

	// 将线程放入池中
	protected synchronized void repool(PThread thread) {
		if (!isShutDown) {// 是否关闭 false
			idleThreads.add(thread);
		} else {
			thread.shutDown();
		}
	}

	// 停止线程池中所有线程
	public synchronized void shutdown() {
		isShutDown = true;
		int size = idleThreads.size();
		for (int i = 0; i < size; i++) {
			PThread idleThread = idleThreads.get(i);
			idleThread.shutDown();
		}
	}

	// 执行任务
	public synchronized void start(Runnable target) {
		PThread thread = null;
		// 如果有空闲 线程 则直接使用
		if (idleThreads.size() > 0) {
			int lastIndex = idleThreads.size() - 1;
			thread = idleThreads.get(lastIndex);
			idleThreads.remove(lastIndex);
			// 立即执行这个任务
			thread.setTarget(target);
		} else { // 没有空闲线程
			threadCounter++;
			// 创建新线程
			thread = new PThread(this, target, "PThread#" + threadCounter);
			// 启动这个线程
			thread.start();
		}
	}
}
