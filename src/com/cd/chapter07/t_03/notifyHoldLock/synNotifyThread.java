package com.cd.chapter07.t_03.notifyHoldLock;

public class synNotifyThread extends Thread{
	private Object lock;
	public synNotifyThread(Object lock){
		this.lock = lock;
	}
	
	@Override
	public void run() {
		Service s = new Service();
		s.synNotifyMethod(lock);
	}

}
