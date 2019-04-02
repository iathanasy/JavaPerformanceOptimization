package com.cd.chapter04.producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		//建立缓冲区
		BlockingQueue<PCData> queue = new LinkedBlockingQueue<PCData>();
		Producer p1 = new Producer(queue);//生产者
		Producer p2 = new Producer(queue);
		Producer p3 = new Producer(queue);
		Consumer c1 = new Consumer(queue);//消费者
		Consumer c2 = new Consumer(queue);
		Consumer c3 = new Consumer(queue);
		
		//建立线程池
		ExecutorService s = Executors.newCachedThreadPool();
		s.execute(p1);//运行生产者
		s.execute(p2);
		s.execute(p3);
		s.execute(c1);//运行消费者
		s.execute(c2);
		s.execute(c3);
		
		Thread.sleep(10 * 1000); 
		//停止生产者
		p1.stop();
		p2.stop();
		p3.stop();
		
		Thread.sleep(3000);
		s.shutdown();
	}
}
