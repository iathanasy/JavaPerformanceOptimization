package com.cd.chapter04.thread;

/**
 * 
 * @author cd
 * @date 2019年4月2日 上午9:54:50
 * @desc 线程
 */
public class PThread extends Thread {

	// 线程池
	private ThreadPool pool;
	// 任务
	private Runnable target;
	private boolean isShutDown = false; // 是否关闭
	private boolean isIdle = false;// 是否空闲

	public PThread(ThreadPool pool, Runnable target, String name) {
		super(name);// 线程名称
		this.pool = pool;
		this.target = target;
	}

	public Runnable getTarget() {
		return target;
	}

	public boolean isIdle() {
		return isIdle;
	}

	@Override
	public void run() {
		// 只要没有关闭，则一直不结束该线程
		while (!isShutDown) {
			isIdle = false;
			if (target != null) {
				// 运行任务
				target.run();
			}
			// 任务结束 置为空闲状态
			isIdle = true;
			try {
				// 该任务结束后，不关闭线程，而是放入线程池空闲队列
				pool.repool(this);
				synchronized (this) {
					// 线程空闲，等待新的任务到来
					wait();
				}
			} catch (InterruptedException e) {

			}
			isIdle = false;
		}
	}

	public synchronized void setTarget(Runnable newTarget) {
		this.target = newTarget;
		// 设置任务之后，通知run方法，开始执行这个任务
		notifyAll();
	}

	// 关闭线程池
	public synchronized void shutDown() {
		isShutDown = true;
		notifyAll();
	}

}
