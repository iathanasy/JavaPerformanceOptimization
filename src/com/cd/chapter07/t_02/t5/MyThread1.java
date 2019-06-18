package com.cd.chapter07.t_02.t5;

public class MyThread1 extends Thread{

	private Task task;
	public MyThread1(Task task){
		this.task = task;
	}

	@Override
	public void run() {
		CommonUtils.beginTime1 = System.currentTimeMillis();
		task.doLongTimeTask();
		CommonUtils.endTime1 = System.currentTimeMillis();
	}
}
