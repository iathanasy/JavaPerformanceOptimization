package com.cd.chapter07.t_02.synLockIn;

public class Sub extends Main{

	synchronized public void operateISubMethod(){
		try {
			while(i > 0){
				i--;
				System.out.println("sub print i: "+ i);
				Thread.sleep(100);
				this.operateIMainMethod();//可重入锁
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
