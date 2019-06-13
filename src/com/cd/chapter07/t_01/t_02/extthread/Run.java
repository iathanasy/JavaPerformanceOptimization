package com.cd.chapter07.t_01.t_02.extthread;

public class Run {

	public static void main(String[] args) {
		System.out.println("main begin priority:"+ Thread.currentThread().getPriority());
		Thread.currentThread().setPriority(6);
		System.out.println("main end priority:"+ Thread.currentThread().getPriority());
		MyThread1 t = new MyThread1();//线程1中启动线程2 优先级设置是可以被继承的。
		t.start();
	}
}
