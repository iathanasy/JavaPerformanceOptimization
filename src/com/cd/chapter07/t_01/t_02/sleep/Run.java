package com.cd.chapter07.t_01.t_02.sleep;

public class Run {

	public static void main(String[] args) {
		MyThread t = new MyThread();
		t.start();
		t.interrupt();
		
		System.out.println("end");
	}
}
