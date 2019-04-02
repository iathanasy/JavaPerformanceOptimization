package com.cd.chapter04.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThread implements Runnable, Comparable<MyThread>{
	protected String name;

	public MyThread() {
	}

	public MyThread(String name) {
		this.name = name;
	}

	

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		try {
			//使用sleep方法代替一个具体功能的执行
			Thread.sleep(100);
			System.out.println(name + "");
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		//未使用线程池
		long begin = System.currentTimeMillis();
		/*for (int i = 0; i < 1000; i++) {
			new Thread(new MyThread("testNoThreadPool "+ Integer.toString(i))).start();
		}
		System.out.println("testNoThreadPool: "+ (System.currentTimeMillis() - begin));
		
		//线程池可以减少线程频繁调度的开销，下面是使用自己编写的简易线程池
		begin = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			ThreadPool.getInstance().start(new MyThread("testThreadPool "+Integer.toString(i)));
		}
		System.out.println("testThreadPool: "+ (System.currentTimeMillis() - begin));
		
		//使用Jdk自带的线程池  得到一个可复用线程的线程池
		begin = System.currentTimeMillis();
		ExecutorService exe = Executors.newCachedThreadPool();
		for (int i = 0; i < 1000; i++) {
			//在线程池中执行一个任务
			exe.execute(new MyThread("testJDKThreadPool "+ Integer.toString(i)));
		}
		System.out.println("testJDKThreadPool: "+ (System.currentTimeMillis() - begin));*/
		
		/***
		 * Executors 线程池工厂方法说明
		 * newFixedThreadPool(): 固定线程池数量的线程池。当有新的任务提交，线程池中若有空闲线程，则立即执行，否则保存到队列中。待有空闲线程时，便处理队列中的任务
		 * newSingleThreadExecutor(): 只有一个线程的线程池，若多余的一个任务被提交到该线程池，则会保存到任务队列中，待有空闲线程时，便处理队列中的任务，先入先出顺序处理
		 * newCachedThreadPool(): 可根据实际情况调整线程数量的线程池。线程池数量不确定，若有空闲线程可以复用，则会优先使用可复用线程。若所有任务都在运行，又有新的任务提交，则会创建新的线程处理任务。所有线程在当前任务完成后，将返回线程池进行复用。
		 * newSingleThreadScheduledExecutor(): 返回一个ScheduledExecutorService对象，线程池大小为1，可定时执行某个任务或周期性执行。
		 * newScheduledThreadPool(): 返回一个ScheduledExecutorService对象，可以指定任务数量，可定时执行某个任务或周期性执行。
		 * 
		 * 以上方法内部实现均都使用了ThreadPoolExecutor ,可以看出都是ThreadPoolExecutor的封装
		 * 
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
		
		int cpu = Runtime.getRuntime().availableProcessors();
		System.out.println(cpu);
		//自定义线程池
		begin = System.currentTimeMillis();
		ExecutorService ex = new MyThreadPoolExecutor(100, 200, 0L, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
		for (int i = 0; i < 10; i++) {
			//在线程池中执行一个任务
			ex.execute(new MyThread("testThreadPoolExecutor3_"+ Integer.toString(9-i)));
		}
		System.out.println("testThreadPoolExecutor3: "+ (System.currentTimeMillis() - begin));
		ex.shutdown();
	}

	@Override
	public int compareTo(MyThread o) {
		//比较任务的优先级
		int me = Integer.parseInt(this.name.split("_")[1]);
		int other = Integer.parseInt(this.name.split("_")[1]);//线程名称中标注任务优先级
		if(me > other) return 1;
		else if(me < other) return -1;
		else 
		return 0;
	}

}
