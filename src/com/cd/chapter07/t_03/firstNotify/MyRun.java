package com.cd.chapter07.t_03.firstNotify;

public class MyRun {

	private String lock = new String("");
	private boolean  isFirstRunB = false;
	private Runnable runnableA = new Runnable() {
		
		@Override
		public void run() {
			
				try {
					synchronized(lock){
						while(!isFirstRunB){//isFirstRunB = false
							System.out.println("begin wait");
							lock.wait();
							System.out.println("end wait");
						}
					}
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
	};
	
	private Runnable runnableB = new Runnable() {
		
		@Override
		public void run() {
			
			synchronized(lock){
				System.out.println("begin notify");
				lock.notify();
				System.out.println("end notify");
				isFirstRunB = true;
			}	
		}
	};
	
	
	public static void main(String[] args) throws InterruptedException {
		MyRun run = new MyRun();
		//正常执行
		Thread a = new Thread(run.runnableA);
		a.start();
		
		Thread b = new Thread(run.runnableB);
		b.start();
		
		//通知过早,notify先通知了，wait方法也没必要执行了，否则会停在这。可以改造下加个字段标识 isFirstRunB = false;
		/*Thread b = new Thread(run.runnableB);
		b.start();
		Thread.sleep(100);
		Thread a = new Thread(run.runnableA);
		a.start();*/
		
		
		
		
	}
}
