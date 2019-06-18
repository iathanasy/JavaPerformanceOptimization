package com.cd.chapter07.t_03.waitOld;

public class Run {

	public static void main(String[] args) throws InterruptedException {
		String lock = new String("");
		Add add = new Add(lock);
		Subtract sub = new Subtract(lock);
		
		ThreadSubtract subThread1 = new ThreadSubtract(sub);
		subThread1.setName("subThread1");
		subThread1.start();
		
		ThreadSubtract subThread2 = new ThreadSubtract(sub);
		subThread2.setName("subThread2");
		subThread2.start();
		
		Thread.sleep(1000);
		
		ThreadAdd addThread = new ThreadAdd(add);
		addThread.setName("addThread");
		addThread.start();
	}
}
