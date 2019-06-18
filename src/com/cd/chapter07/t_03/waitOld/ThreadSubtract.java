package com.cd.chapter07.t_03.waitOld;

public class ThreadSubtract extends Thread{

	private Subtract sub ;
	public ThreadSubtract(Subtract sub){
		this.sub = sub;
	}
	
	@Override
	public void run() {
		sub.subtract();
	}
}
