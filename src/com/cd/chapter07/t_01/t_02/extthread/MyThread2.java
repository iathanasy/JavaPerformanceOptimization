package com.cd.chapter07.t_01.t_02.extthread;

public class MyThread2 extends Thread{

	@Override
	public void run() {
		System.out.println("MyThread2 run priority: "+ this.getPriority());
	}
}
