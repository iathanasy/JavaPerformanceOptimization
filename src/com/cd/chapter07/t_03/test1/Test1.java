package com.cd.chapter07.t_03.test1;

public class Test1 {

	public static void main(String[] args) {
		try{
			String lock = new String();
			System.out.println("syn上面");
			synchronized(lock){
				System.out.println("syn第一行");
				lock.wait();//必须在同步方法或者方法块中调用
				System.out.println("wait下面一行");
			}
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
