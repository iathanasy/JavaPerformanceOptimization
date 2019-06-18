package com.cd.chapter07.t_03.waitOld;

public class ThreadAdd extends Thread{

	private Add p;
	
	public ThreadAdd(Add p){
		this.p = p;
	}
	
	@Override
	public void run() {
		p.add();
	}
}
