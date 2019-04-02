package com.cd.chapter04.concurrent.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class AccessMapThread implements Runnable{

	protected String name;
	Random r = new Random();
	
	Map map = Collections.synchronizedMap(new HashMap()); //testsynchronizedMapspend time: 446
	//Map map = new ConcurrentHashMap(); //testConcurrentHashMapspend time: 328
	
	public AccessMapThread() {
	}
	
	public AccessMapThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 500; i++) {
				handleMap(r.nextInt(1000));//每个线程进行500次get()保证高并发
			}
			Thread.sleep(r.nextInt(100));
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	public Object handleMap(int index){
		map.put(r.nextInt(2000), "zzz");
		return map.get(index);
	}
	
}
