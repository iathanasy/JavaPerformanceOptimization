package com.cd.chapter07.t_03.notifyHoldLock;

public class Test {

	public static void main(String[] args) {
		Object lock = new Object();
		ThreadA a = new ThreadA(lock);
		a.start();
		
		//a.interrupt();
		
		NotifyThread n = new NotifyThread(lock);
		n.start();
		//必须执行完notify方法所在的同步synchronized代码块才释放锁
		synNotifyThread s = new synNotifyThread(lock);
		s.start();
		
		
	}
}
