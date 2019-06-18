package com.cd.chapter07.t_03.notifyAll;

public class ThreadB extends Thread{
	private Object lock;
	public ThreadB(Object lock){
		this.lock = lock;
	}

	@Override
	public void run() {
		Service s = new Service();
		s.testMethod(lock);
	}
}
