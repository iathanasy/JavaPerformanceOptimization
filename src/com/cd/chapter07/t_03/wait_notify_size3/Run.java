package com.cd.chapter07.t_03.wait_notify_size3;

public class Run {

	public static void main(String[] args) {
		try {
			Object lock = new Object();
			ThreadA a = new ThreadA(lock);
			a.start();
			
			Thread.sleep(50);
			
			ThreadB b = new ThreadB(lock);
			b.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
