package com.cd.chapter07.t_01.t_02;

/**
 * 数据共享
 * @author cd
 * @date 2019年6月13日 上午11:02:56
 * @desc
 */
public class MyThread1 extends Thread{
	private int count = 3;
	
	@Override
	public synchronized void run() {
		count--;
		System.out.println(String.format("name= [%s], count= [%d]",this.currentThread().getName(),count));
	}
	
	static class Main{
		public static void main(String[] args) {
			//非线程安全  需要加synchronized
			MyThread1 t = new MyThread1();
			Thread t1 = new Thread(t, "A");
			Thread t2 = new Thread(t, "B");
			Thread t3 = new Thread(t, "C");
			t1.start();
			t2.start();
			t3.start();
			/**
			 *  name= [A], count= [1]
				name= [B], count= [0]
				name= [C], count= [0]
			 */
		}
	}
}
