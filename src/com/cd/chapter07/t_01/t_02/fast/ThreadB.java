package com.cd.chapter07.t_01.t_02.fast;

public class ThreadB extends Thread{

	private int count = 0;

	public int getCount() {
		return count;
	}
	
	@Override
	public void run() {
		while(true){
			count++;
		}
	}
}
