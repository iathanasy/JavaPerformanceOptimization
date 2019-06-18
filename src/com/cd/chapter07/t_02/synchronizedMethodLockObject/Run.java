package com.cd.chapter07.t_02.synchronizedMethodLockObject;

public class Run {

	public static void main(String[] args) {
		MyObject object = new MyObject();
		ThreadA a = new ThreadA(object);
		ThreadB b = new ThreadB(object);
		a.setName("a");
		b.setName("b");
		a.start();
		b.start();
		
		//加上synchronized一定是排队运行的。
	}
}
