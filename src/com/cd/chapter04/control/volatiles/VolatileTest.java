package com.cd.chapter04.control.volatiles;

public class VolatileTest {

	volatile boolean isExit;

	public void tryExit() {
		if (isExit) { //
			System.out.println("zz");
			System.exit(0);
		}
	}

	public void swapValue() {
		isExit = !isExit; // true
	}

	public static void main(String[] args) throws InterruptedException {
		final VolatileTest v = new VolatileTest();
		Thread t = new Thread() {
			@Override
			public void run() {
				System.out.println("t start");
				while (true)
					v.tryExit();// 不停尝试是否可以退出
			}
		};
		t.start();

		Thread s = new Thread() {
			@Override
			public void run() {
				System.out.println("s start");
				while (true)
					v.swapValue();// 不停修改isExit的值
			}
		};
		s.start();
		Thread.sleep(10000);
	}

}
