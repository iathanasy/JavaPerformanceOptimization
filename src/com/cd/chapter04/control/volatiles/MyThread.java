package com.cd.chapter04.control.volatiles;

public class MyThread extends Thread{
	private volatile boolean stop = false;//确保stop变量在多线程中是可见的
	
	public void stopMe(){//其它线程调用停止本线程
		stop = true;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(!stop) //在其它线程中改变stop的值 
			i++;
		System.out.println("Stop Thread");
	}
	
}
