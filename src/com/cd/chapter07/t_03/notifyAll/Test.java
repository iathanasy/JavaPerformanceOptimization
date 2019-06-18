package com.cd.chapter07.t_03.notifyAll;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		Object lock = new Object();
		ThreadA a = new ThreadA(lock);
		a.start();
		
		ThreadB b = new ThreadB(lock);
		b.start();
		
		ThreadC c = new ThreadC(lock);
		c.start();
		
		Thread.sleep(1000);
		
		NotityThread n = new NotityThread(lock);
		n.start();
		
	}
}
