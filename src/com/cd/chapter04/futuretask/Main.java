package com.cd.chapter04.futuretask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * 
 * @author cd
 * @date 2019年4月1日 下午3:51:22
 * @desc 使用JDk内置的Future模式实现上面的功能
 */
public class Main {

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		// 构造FutureTask
		FutureTask<String> future = new FutureTask<String>(new RealData("name"));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		// 执行FutureTask,相当于上例中的client.request("name") 发送请求
		// 在这里开启线程线程进行RealData的call()执行
		executor.submit(future);
		System.out.println("请求完毕");

		try {
			// 处理其它业务逻辑
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 相当于上例中的data.getResult(),取得call()方法的返回值
		// 如果此时call方法没有执行完成，则依然会等待
		System.out.println(future.get());
	}
}
