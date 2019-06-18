package com.cd.chapter07.t_02.synLockIn;

public class MyThread extends Thread{
	@Override
	public void run() {
		Sub s = new Sub();
		s.operateISubMethod();
	}
	
}
