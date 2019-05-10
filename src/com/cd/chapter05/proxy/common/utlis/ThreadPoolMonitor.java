package com.cd.chapter05.proxy.common.utlis;

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

/**
 * 线程池工具类，监视ThreadPoolExecutor执行情况
 * 
 * @author cd
 * @date 2019年5月8日 上午11:49:02
 * @desc
 */
public class ThreadPoolMonitor implements Runnable {

	private static Logger logger = Logger.getLogger(ThreadPoolMonitor.class);
	private ThreadPoolExecutor executor;
	public static volatile boolean isStopMonitor = false;
	private String name = "";

	public ThreadPoolMonitor(ThreadPoolExecutor executor, String name) {
		this.executor = executor;
		this.name = name;
	}

	@Override
	public void run() {
		while (!isStopMonitor) {
			/*
			 * logger.debug(name + String.format(
			 * "[monitor] [%d/%d] Active: %d, Completed: %d, queueSize: %d, Task: %d, isShutdown: %s, isTerminated: %s"
			 * , this.executor.getPoolSize(), this.executor.getCorePoolSize(),
			 * this.executor.getActiveCount(),
			 * this.executor.getCompletedTaskCount(),
			 * this.executor.getQueue().size(), this.executor.getTaskCount(),
			 * this.executor.isShutdown(), this.executor.isTerminated()));
			 */
			logger.debug(name
					+ String.format(
							"[監聽] [%s/%s] 活跃线程数: %s, 完成线程数: %s, 任务队列大小: %s, 总任务数: %s, 是否關閉: %s, 是否終止: %s",
							this.executor.getPoolSize(),
							this.executor.getCorePoolSize(),
							this.executor.getActiveCount(),
							this.executor.getCompletedTaskCount(),
							this.executor.getQueue().size(),
							this.executor.getTaskCount(),
							this.executor.isShutdown(),
							this.executor.isTerminated()));

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.error("InterruptedException", e);
			}
		}
	}

}
