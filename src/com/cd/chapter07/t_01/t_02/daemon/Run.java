package com.cd.chapter07.t_01.t_02.daemon;
/**
 * 守护线程
 * @author cd
 * @date 2019年6月13日 下午4:47:39
 * @desc
 */
public class Run {

	public static void main(String[] args) {
		
		try {
			MyThread t = new MyThread();
			t.setDaemon(true);
			t.start();
			Thread.sleep(5000);
			System.out.println("离开thread对象也不在打印了， 也就是停止了");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
