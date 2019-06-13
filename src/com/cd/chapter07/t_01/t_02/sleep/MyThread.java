package com.cd.chapter07.t_01.t_02.sleep;

public class MyThread extends Thread{

	@Override
	public void run() {
		try {
			System.out.println("run begin");
			Thread.sleep(200000);
			System.out.println("run end");
		} catch (InterruptedException e) {
			
			//在睡眠中被停止，会进入catch ,并清除停止状态值，使之变成false
			System.out.println("在睡眠中被停止，进入catch!"+this.isInterrupted());
			e.printStackTrace();
		}
	}
}
