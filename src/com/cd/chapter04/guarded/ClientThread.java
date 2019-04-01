package com.cd.chapter04.guarded;

import java.util.ArrayList;
import java.util.List;

public class ClientThread extends Thread{

	private RequestQueue requestQueue; //请求队列
	private List<Request> list = new ArrayList<Request>();//1

	public ClientThread(RequestQueue requestQueue, String name) {
		super(name);
		this.requestQueue = requestQueue;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			//构造请求
			Request request = new Request("RequestID: "+ i + ", ThreadName: "+ Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getName()+ " handles "+ request);
			//设置一个FutureData值
			request.setResponse(new FutureData());//1
			requestQueue.addRequest(request); //提交请求
			
			//发送请求
			list.add(request);
			try {
				//客户端请求速度 快于服务端处理速度
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			//取得服务端返回的值
			for (Request r: list) {
				System.out.println("ClientThread Name is : "+ Thread.currentThread().getName()
						+ "Response is "+ r.getResponse().getResult());
			}
			
			
		}
		System.out.println(Thread.currentThread().getName()+ "request end");
	}
	
}
