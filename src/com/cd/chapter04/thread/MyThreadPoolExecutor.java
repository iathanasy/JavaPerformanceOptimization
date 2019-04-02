package com.cd.chapter04.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author cd
 * @date 2019年4月2日 下午2:33:42
 * @desc 扩展ThreadPoolExecutor 线程池
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor{
	/**
	 * public ThreadPoolExecutor(int corePoolSize,
             int maximumPoolSize,
             long keepAliveTime,
             TimeUnit unit,
             BlockingQueue<Runnable> workQueue,
             ThreadFactory threadFactory,
             RejectedExecutionHandler handler)        
	*
	*corePoolSize: 线程池中的线程数量
	*maximumPoolSize： 线程池中的最大线程数量
	*keepAliveTime： 当线程池数量超过 corePoolSize时，多余的空闲线程存活时间
	*unit： keepAliveTime的单位
	*workQueue： 任务队列，被提交但尚未被执行的任务
	*threadFactory： 线程工厂，用于创建线程， 一般使用默认的
	*handler: 拒绝策略，当任务太多来不及处理，如何拒绝任务
	*
	*队列：
	*SynchronousQueue： 直接提交队列，没有容量，每一个插入都要等待一个相应的删除操作，反之每一个删除都要等待一个插入操作。不保存任务，总是将任务提交给线程执行，如果没有空闲进程，则尝试创建新的进程，如果进程数量已达到最大值，则执行拒绝策略。使用这个队列，需要设置很大的maximumPoolSize值，否则很容易执行异常策略
	*ArrayBlockingQueue: 有界任务队列，必须带一个容量参数，表示该队列的最大容量。若有新的任务要执行，如果线程池的实际线程数量小于corePoolSize,则会创建新的线程，大于则会添加到等待队列。若队列已满，则总线程数不大于maximumPoolSize的前提下，创建新的进程执行任务，大于则执行拒绝策略
	*LinkedBlockingQueue :无界任务队列，除非系统资源耗尽，否则无界队列不存在任务入队失败的情况
	*PriorityBlockingQueue: 优先队列，根据自身的优先级顺序先后执行，在确保系统性能的同时，也能有很好的质量保证(总是确保高优先级任务先执行).
	*
	*内置拒绝策略：
	*AbortPolicy :会直接抛出异常，阻止系统正常工作
	*CallerRunsPolicy: 只要线程池未关闭，直接在调用者线程中，运行当前被丢弃的任务
	*DiscardOledestPolicy: 丢弃最老的一个请求，就是即将被执行的一个任务，并尝试再次提交当前任务
	*DiscardPolicy: 默默丢弃无法处理的任务，不予任何处理
	*也可自己扩展策略
	*
	*估算线程池大小的计算公式
	*Ncpu = CPU的数量
	*Ucpu = 目标CPU的使用率，0 <= Ucpu <= 1
	*W/C = 等待时间与计算时间的比率
	*
	*Nthreads = Ncpu * Ucpu * (1 + W/C)
	*
	*取得可用CPU数量
	*Runtime.getRuntime().availableProcessors();
	*/
	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	/**
	 * 运行前
	 */
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.println("beforeExecute: "+ ((MyThread)r).getName() +" TID: "+ t.getId());
	}
	
	/**
	 * 运行结束后
	 */
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		System.out.println("afterExecute TID: "+ Thread.currentThread().getId() +" PoolSize: "+ this.getPoolSize());
	}
}
