package com.cd.chapter07.t_01.t_02.fast;
/**
 * 看谁运行的快
 * @author cd
 * @date 2019年6月13日 下午4:42:41
 * @desc
 */
public class Run {

	public static void main(String[] args) {
		try {
			ThreadA a = new ThreadA();
			a.setPriority(Thread.NORM_PRIORITY - 3);
			a.start();
			
			ThreadB b = new ThreadB();
			b.setPriority(Thread.NORM_PRIORITY + 3);
			b.start();
			
			Thread.sleep(2000);
			
			a.stop();
			b.stop();
			
			System.out.println("a = "+ a.getCount());
			System.out.println("b = "+ b.getCount());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
