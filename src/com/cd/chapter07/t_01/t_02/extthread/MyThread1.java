package com.cd.chapter07.t_01.t_02.extthread;

public class MyThread1 extends Thread{

	@Override
	public void run() {
		System.out.println("MyThread1 run priority: "+ this.getPriority());
		MyThread2 t2 = new MyThread2();
		t2.start();
	}
}
