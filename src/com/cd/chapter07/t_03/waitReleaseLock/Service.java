package com.cd.chapter07.t_03.waitReleaseLock;

public class Service {

	public void testMethod(Object lock){
		synchronized(lock){
			try {
				System.out.println("star wait()");
				lock.wait(); //当方法wait被执行之后锁被自动释放，但notify执行之后锁却不自动释放。
				//Thread.sleep(4000);
				System.out.println("end wait()");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
