package com.cd.chapter07.t_03.TwoThreadTransData;


public class ThreadB extends Thread{

	private MyList list;
	public ThreadB(MyList list){
		this.list = list;
	}
	
	@Override
	public void run() {
		try {
			while(true){
				if(list.getSize() == 5){
					System.out.println("==5，线程B退出了");
					throw new InterruptedException();
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
