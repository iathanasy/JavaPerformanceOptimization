package com.cd.chapter04.guarded;

public class ServerThread extends Thread{

	private RequestQueue requestQueue;

	public ServerThread(RequestQueue requestQueue, String name) {
		super(name);
		this.requestQueue = requestQueue;
	}
	
	@Override
	public void run() {
		while(true){
			final Request request = requestQueue.getRequest();//得到请求
			final FutureData future = (FutureData) request.getResponse(); //1
			/*try {
				Thread.sleep(100);//模拟请求处理耗时
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}*/
			//RealData的创建比较耗时
			RealData realData = new RealData(request.getName()); //2
			//处理完成后，通知客户端进程
			future.setRealData(realData); //3
			
			System.out.println(Thread.currentThread().getName()+ " handles "+ request);
		}
	}
}
