package com.cd.chapter07.t_03.notifyAll;

public class ThreadC extends Thread{
	private Object lock;
	public ThreadC(Object lock){
		this.lock = lock;
	}

	@Override
	public void run() {
		Service s = new Service();
		s.testMethod(lock);
	}
}
