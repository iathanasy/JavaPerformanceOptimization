package com.cd.chapter07.t_02.t5;

public class MyThread2 extends Thread{

	private Task task;
	public MyThread2(Task task){
		this.task = task;
	}

	@Override
	public void run() {
		CommonUtils.beginTime2 = System.currentTimeMillis();
		task.doLongTimeTask();
		CommonUtils.endTime2 = System.currentTimeMillis();
	}
}
