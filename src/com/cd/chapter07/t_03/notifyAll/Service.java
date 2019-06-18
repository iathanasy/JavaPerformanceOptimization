package com.cd.chapter07.t_03.notifyAll;

public class Service {

	public void testMethod(Object lock){
		synchronized(lock){
			try {
				System.out.println("begin wait() ThreadName = "+ Thread.currentThread().getName());
				lock.wait();
				System.out.println("end wait() ThreadName = "+ Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
