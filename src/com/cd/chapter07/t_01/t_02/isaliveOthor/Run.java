package com.cd.chapter07.t_01.t_02.isaliveOthor;

public class Run {

	public static void main(String[] args) {
		CountOperate c = new CountOperate();
		Thread t = new Thread(c);
		System.out.println("main begin isAlive:"+t.isAlive());
		t.setName("A");
		t.start();
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("main end isAlive:"+t.isAlive());
	}
}
