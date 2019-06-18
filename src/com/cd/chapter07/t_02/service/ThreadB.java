package com.cd.chapter07.t_02.service;

public class ThreadB extends Thread{
	private HasSelfPrivateNum numRef;
	
	public ThreadB(HasSelfPrivateNum numRef){
		this.numRef = numRef;
	}
	
	@Override
	public void run() {
		numRef.addI("b");
	}
}
