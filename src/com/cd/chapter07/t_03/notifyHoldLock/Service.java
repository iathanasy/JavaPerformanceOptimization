package com.cd.chapter07.t_03.notifyHoldLock;

public class Service {

	public void testMethod(Object lock){
		synchronized(lock){
			try {
				System.out.println("begin wait() ThreadName = "+ Thread.currentThread().getName());
				lock.wait();
				System.out.println("end wait() ThreadName = "+ Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("出现异常了，因为呈wait状态的线程被inerrupt()了");
			}
			
		}
	}
	
	public void synNotifyMethod(Object lock){
		try {
			synchronized(lock){
				System.out.println("begin notify() ThreadName="+ Thread.currentThread().getName() + ", time="+ System.currentTimeMillis());
				lock.notify();
				Thread.sleep(5000);
				System.out.println("end notify() ThreadName="+ Thread.currentThread().getName() + ", time="+ System.currentTimeMillis());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
