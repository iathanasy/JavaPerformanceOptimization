package com.cd.chapter05.proxy.common.utlis;

import java.util.concurrent.*;

/**
 * 自定义线程池
 * 
 * @author cd
 * @date 2019年5月8日 上午11:41:15
 * @desc
 * 
 *       corePoolSize: 线程池中的线程数量 maximumPoolSize： 线程池中的最大线程数量 keepAliveTime：
 *       当线程池数量超过 corePoolSize时，多余的空闲线程存活时间 unit： keepAliveTime的单位 workQueue：
 *       任务队列，被提交但尚未被执行的任务 threadFactory： 线程工厂，用于创建线程， 一般使用默认的 handler:
 *       拒绝策略，当任务太多来不及处理，如何拒绝任务
 */
public class SimpleThreadPoolExecutor extends ThreadPoolExecutor {

	private String threadPoolName;

	public SimpleThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, String threadPoolName) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		this.threadPoolName = threadPoolName;
	}

	public SimpleThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
			String threadPoolName) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory);
		this.threadPoolName = threadPoolName;
	}

	public SimpleThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue,
			RejectedExecutionHandler handler, String threadPoolName) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				handler);
		this.threadPoolName = threadPoolName;
	}

	public SimpleThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
			RejectedExecutionHandler handler, String threadPoolName) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
		this.threadPoolName = threadPoolName;
	}

	/**
	 * 運行前 修改thread name
	 * 
	 * @param t
	 * @param r
	 */
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		if (t.getName().startsWith("pool-")) {
			t.setName(t.getName().replaceAll("pool-\\d", this.threadPoolName));
		}
	}

	/**
	 * 运行结束后
	 */
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		// System.out.println("afterExecute TID: "+
		// Thread.currentThread().getId() +" PoolSize: "+ this.getPoolSize());
	}
}
