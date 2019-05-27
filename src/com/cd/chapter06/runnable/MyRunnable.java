package com.cd.chapter06.runnable;

public class MyRunnable implements Runnable{
	private String message;
	public MyRunnable(String message){
		this.message = message;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			System.out.print(message);
		}
	}

}
