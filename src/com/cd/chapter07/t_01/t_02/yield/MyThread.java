package com.cd.chapter07.t_01.t_02.yield;

public class MyThread extends Thread{

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		int count = 0;
		for (int i = 0; i < 50000000; i++) {
			//Thread.yield();
			count = count + (i + 1);
		}
		long end = System.currentTimeMillis();
		System.out.println("count: "+ count);
		System.out.println("用时：" + (end - start) + "毫秒。");
	}
}
