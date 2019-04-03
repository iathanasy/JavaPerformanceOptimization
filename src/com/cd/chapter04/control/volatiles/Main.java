package com.cd.chapter04.control.volatiles;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		MyThread t = new MyThread();
		t.start();
		Thread.sleep(1000);
		t.stopMe(); //改变值
		Thread.sleep(1000);
		/**
		 * 如果线程因为stop变量改变而退出，则会打印，
		 * volatile 的意义在于如果在主线程中停止了MyThread线程，则MyThread会立即发现stop状态的改变，从而停止。
		 * 如果不使用 volatile ,则MyThread可能不会立即发现这个改变。
		 * 当前实例中如果不使用volatile ，则MyThread永远不会退出
		 */
	}
}
