package com.cd.chapter07.t_02.synchronizedMethodLockObject;

public class MyObject {

	//不加synchronized两个线程是同时进来的，加了就是排队进入
	synchronized public void methodA(){
		try {
			System.out.println("begin methodA threadName = "+ Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end endTime= "+System.currentTimeMillis());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	synchronized public void methodB(){
		try {
			System.out.println("begin methodB threadName = "+ Thread.currentThread().getName()
					+"begin time = "+ System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
