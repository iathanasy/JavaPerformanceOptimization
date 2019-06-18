package com.cd.chapter07.t_03.waitReleaseLock;

public class Test {
	
	public static void main(String[] args) {
		Object lock = new Object();
		//当方法wait被执行之后锁被自动释放，但notify执行之后锁却不自动释放。
		ThreadA a = new ThreadA(lock);
		a.start();
		
		ThreadB b = new ThreadB(lock);
		b.start();
	}
}
