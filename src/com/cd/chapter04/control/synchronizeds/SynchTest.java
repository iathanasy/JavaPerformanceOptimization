package com.cd.chapter04.control.synchronizeds;

import java.util.ArrayList;
import java.util.List;

/**
 * 为了有效的控制线程间的协作，需要配合使用synchronized 以及wait()和notify()等方法
 * @author cd
 * @date 2019年4月3日 上午10:38:08
 * @desc 同步测试
 */
public class SynchTest {
	private List list = new ArrayList();
	
	public synchronized Object pop() throws InterruptedException{
		while(list.size() == 0){ //队列为空 ，等待
			this.wait();
		}
		if(list.size() > 0){
			return list.remove(0);//如果队列不为空 则返回第一个对象
		}else{
			return null;
		}
	}
	
	public synchronized void put(Object o){
		list.add(o); //增加对象到队列
		this.notify();//通知pop()方法，可以取得数据
	}
	
	public static void main(String[] args) throws InterruptedException {
		final SynchTest s = new SynchTest();
		Thread t = new Thread(){
			@Override
			public void run() {
				try {
					System.out.println(s.pop());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
		System.out.println("zz");
		s.put("123");
	}
}
