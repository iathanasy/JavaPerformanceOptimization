package com.cd.chapter07.t_02.synchronizedMethodLockObject;

public class ThreadA extends Thread{

	private MyObject myObject;
	public ThreadA(MyObject myObject){
		this.myObject = myObject;
	}
	
	@Override
	public void run() {
		myObject.methodA();
	}
}
