package com.cd.chapter06.runnable;

public class Main {
	public static void main(String[] args) {
		new Thread(new MyRunnable("Nice!")).start();
		new Thread(new MyRunnable("Good!")).start();
	}
}
