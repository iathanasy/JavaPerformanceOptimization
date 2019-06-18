package com.cd.chapter07.t_02.t5;

public class Task {

	private String getData1;
	private String getData2;
	
	/*synchronized public void doLongTimeTask(){
		try {
			System.out.println("begin task");
			Thread.sleep(3000);
			getData1 = "长时间处理任务返回的值 1 threadName: "+ Thread.currentThread().getName();
			getData2 = "长时间处理任务返回的值 2 threadName: "+ Thread.currentThread().getName();
			
			System.out.println(getData1);
			System.out.println(getData2);
			System.out.println("end task");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	//利用同步代码块解决同步方法的弊端
	public void doLongTimeTask(){
		try {
			System.out.println("begin task");
			Thread.sleep(3000);
			String privateGetData1 = "长时间处理任务返回的值 1 threadName: "+ Thread.currentThread().getName();
			String privateGetData2 = "长时间处理任务返回的值 2 threadName: "+ Thread.currentThread().getName();
			synchronized(this){
				getData1 = privateGetData1;
				getData2 = privateGetData2;
			}
			
			System.out.println(getData1);
			System.out.println(getData2);
			System.out.println("end task");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
