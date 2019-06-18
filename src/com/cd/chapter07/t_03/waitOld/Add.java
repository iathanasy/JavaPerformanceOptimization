package com.cd.chapter07.t_03.waitOld;

public class Add {

	private String lock;
	public Add(String lock){
		this.lock = lock;
	}
	
	public void add(){
		synchronized(lock){
			ValueObject.list.add("zz");
			lock.notifyAll();
		}
	}
}
