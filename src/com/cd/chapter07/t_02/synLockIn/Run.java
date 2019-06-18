package com.cd.chapter07.t_02.synLockIn;

public class Run {

	public static void main(String[] args) {
		//当存在父子类继承关系时，子类完全可以通过可重入锁	调用父类的同步方法
		MyThread t = new MyThread();
		t.start();
	}
}
