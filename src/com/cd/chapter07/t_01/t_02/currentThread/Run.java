package com.cd.chapter07.t_01.t_02.currentThread;

public class Run {

	public static void main(String[] args) {
		CountOperate c = new CountOperate();
		Thread t = new Thread(c);
		t.setName("A");
		t.start();
	}
}
