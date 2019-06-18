package com.cd.chapter07.t_03.notifyHoldLock;

public class NotifyThread extends Thread{
	private Object lock;
	public NotifyThread(Object lock){
		this.lock = lock;
	}
	
	@Override
	public void run() {
		Service s = new Service();
		s.synNotifyMethod(lock);
	}

}
