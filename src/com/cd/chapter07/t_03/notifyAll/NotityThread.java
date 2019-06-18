package com.cd.chapter07.t_03.notifyAll;

public class NotityThread extends Thread{

	private Object lock;
	public NotityThread(Object lock){
		this.lock = lock;
	}
	
	@Override
	public void run() {
		synchronized(lock){
			/*lock.notify();//只能唤醒一个线程，写多个可以唤醒多个线程
			lock.notify();
			lock.notify();*/
			
			lock.notifyAll();//唤醒全部线程
			
		}
	}
}
