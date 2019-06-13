package com.cd.chapter07.t_01.t_02.priority;

import java.util.Random;

public class MyThread1 extends Thread{

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		long addResult = 0;
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 50000; i++) {
				Random r = new Random();
				r.nextInt();
				addResult = addResult + i;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("★ ★ ★ ★ ★ thread 1 use time ="+ (end - start));
	}
}
