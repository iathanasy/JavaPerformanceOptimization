package com.cd.chapter07.t_01.t_02.interrupt;

public class MyThread extends Thread{

	@Override
	public void run() {
		try {
			for (int i = 0; i < 500000; i++) {
				if (this.isInterrupted()) {
					System.out.println("已经是停止状态，退出");
					throw new Exception();
					//break;
				}
				System.out.println("i=" + (i + 1));
			}
			System.out.println("我被输出，如果此代码是for有继续运行，线程并未停止。");
		} catch (Exception e) {
			System.out.println("进入MyThread.java类中run方法的catch了");
			e.printStackTrace();
		}
		
	}
}
