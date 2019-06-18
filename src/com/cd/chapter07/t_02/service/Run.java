package com.cd.chapter07.t_02.service;

public class Run {

	public static void main(String[] args) {
		//方法内的变量为线程安全,可以尝试吧变量写到外面试试 在加上synchronized
		HasSelfPrivateNum numRef = new HasSelfPrivateNum();
		//HasSelfPrivateNum numRef1 = new HasSelfPrivateNum();
		ThreadA a = new ThreadA(numRef);
		a.start();
		ThreadB b = new ThreadB(numRef);
		b.start();
		/**
		 *  name=[b], num=[200]
			name=[a], num=[100]
		 */
	}
}
