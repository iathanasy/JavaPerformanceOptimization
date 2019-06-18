package com.cd.chapter07.t_02.service;

public class ThreadA extends Thread{
	private HasSelfPrivateNum numRef;
	
	public ThreadA(HasSelfPrivateNum numRef){
		this.numRef = numRef;
	}
	
	@Override
	public void run() {
		numRef.addI("a");
	}
}
