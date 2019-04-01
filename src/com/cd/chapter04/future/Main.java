package com.cd.chapter04.future;
/**
 * 
 * @author cd
 * @date 2019年4月1日 下午3:17:14
 * @desc Future 模式
 */
public class Main {
	public static void main(String[] args) {
		Client c = new Client();
		//这里会立即返回，因为得到的是FutureData,而不是RealData
		Data data = c.request("name");
		System.out.println("请求完毕");
		
		try {
			//这里使用sleep 模拟其它业务逻辑的处理，
			//在处理这些业务逻辑的过程中，RealData被创建，充分利用了等待时间
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("数据="+data.getResult());
	}
}
