package com.cd.chapter07.t_02.synchronizedMethodLockObject;

public class ThreadB extends Thread{

	private MyObject myObject;
	public ThreadB(MyObject myObject){
		this.myObject = myObject;
	}
	
	@Override
	public void run() {
		myObject.methodB();
	}
}
