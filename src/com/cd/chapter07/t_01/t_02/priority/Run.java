package com.cd.chapter07.t_01.t_02.priority;

public class Run {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			MyThread1 t1 = new MyThread1();
			t1.setPriority(5);
			t1.start();
			MyThread2 t2 = new MyThread2();
			t2.setPriority(6);
			t2.start();
		}
	}
}
