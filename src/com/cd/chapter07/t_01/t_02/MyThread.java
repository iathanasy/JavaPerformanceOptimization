package com.cd.chapter07.t_01.t_02;

/**
 * 线程数据不共享
 * @author cd
 * @date 2019年6月13日 上午10:53:30
 * @desc
 */
public class MyThread extends Thread{

	private int count = 5;
	public MyThread(String name){
		super.setName(name);
	}
	
	@Override
	public void run() {
		while(count > 0){
			count--;
			System.out.println(String.format("name= [%s], count= [%d]",this.currentThread().getName(),count));
		}
	}
	
	static class Main{
		public static void main(String[] args) {
			MyThread t1 = new MyThread("A");
			MyThread t2 = new MyThread("B");
			MyThread t3 = new MyThread("C");
			t1.start();
			t2.start();
			t3.start();
			
		}
	}
}


