package com.cd.chapter07.t_02.service;

public class HasSelfPrivateNum {
	private int num = 0;
	synchronized public void addI(String name){
		
		try {
			//方法内的变量为线程安全
			//int num = 0;
			if(name.equals("a")){
				num = 100;
				System.out.println("a set over!");
				Thread.sleep(2000);
			}else{
				num = 200;
				System.out.println("b set over!");
			}
			
			System.out.println(String.format("name=[%s], num=[%s]", name, num));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
