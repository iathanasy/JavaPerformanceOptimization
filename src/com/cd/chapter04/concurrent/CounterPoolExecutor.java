package com.cd.chapter04.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
 * @author cd
 * @date 2019年4月2日 下午3:20:17
 * @desc 扩展 ThreadPoolExecutor
 */
public class CounterPoolExecutor extends ThreadPoolExecutor{
	private AtomicInteger count = new AtomicInteger(0);//统计执行次数
	public long startTime = 0;
	public String funcname="";
	public static int TASK_COUNT = 4000;//任务总数
	
	public CounterPoolExecutor(int corePoolSize, 
			int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	//运行结束
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		int i = count.addAndGet(1);//每次执行完一个任务 加1
		if(i == TASK_COUNT){ //如果完成的任务数达到预期值
			System.out.println(funcname + "spend time: "+ (System.currentTimeMillis() - startTime));
		}
	}
}
