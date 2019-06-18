package com.cd.chapter07.t_02.dirtyRead;

public class Run {

	public static void main(String[] args) {
		
		try {
			PublicVar p = new PublicVar();
			ThreadA t = new ThreadA(p);
			t.start();
			Thread.sleep(200);//打印结果受此值大小影响。
			p.getValue();//出现脏读 可以用synchronized解决
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
