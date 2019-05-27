package com.cd.chapter06.thread;

public class Main {

	public static void main(String[] args) {
		new MyThread("Nice! ").start();//新启动的线程
		new MyThread("Good! ").start();
		
		/*for (int i = 0; i < 10000; i++) {
			System.out.print("Good! ");
		}*/
	}
}
