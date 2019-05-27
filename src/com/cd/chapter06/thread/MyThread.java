package com.cd.chapter06.thread;

public class MyThread extends Thread{
	private String message;

	public MyThread(String message){
		this.message = message;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			System.out.print(message);
		}
	}
}
