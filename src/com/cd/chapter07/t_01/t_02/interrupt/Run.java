package com.cd.chapter07.t_01.t_02.interrupt;

public class Run {

	public static void main(String[] args) {
		try {
			MyThread t = new MyThread();
			t.start();
			Thread.sleep(2000);
			t.interrupt();//这个当前线程是Main 所以它并未中断
			//Thread.currentThread().interrupt();
			System.out.println("是否停止1？="+ t.isInterrupted());
			System.out.println("是否停止2？="+ t.interrupted());
		} catch (InterruptedException e) {
			System.out.println("main catch");
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}
