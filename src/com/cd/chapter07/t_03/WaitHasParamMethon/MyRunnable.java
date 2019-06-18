package com.cd.chapter07.t_03.WaitHasParamMethon;

public class MyRunnable {

	static private Object lock = new Object();
	static private Runnable runnable1= new Runnable() {
		
		@Override
		public void run() {
			try {
				synchronized(lock){
					System.out.println("wait begin timer = "+ System.currentTimeMillis());
					lock.wait(5000);//5s自动唤醒
					System.out.println("wait end timer = "+ System.currentTimeMillis());
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	static private Runnable runnable2 = new Runnable() {
		
		@Override
		public void run() {
			synchronized(lock){
				System.out.println("notify begin timer = "+ System.currentTimeMillis());
				lock.notify();
				System.out.println("notify end timer = "+ System.currentTimeMillis());
			}
		}
	};
	
	public static void main(String[] args) {
		
		try {
			Thread t = new Thread(runnable1);
			t.start();
			
			Thread.sleep(3000);
			//也可以在5s内由其他线程进行唤醒
			Thread t2 = new Thread(runnable2);
			t2.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}
