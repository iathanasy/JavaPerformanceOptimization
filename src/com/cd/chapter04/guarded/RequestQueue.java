package com.cd.chapter04.guarded;

import java.util.LinkedList;

import com.sun.swing.internal.plaf.synth.resources.synth;

/**
 * 请求队列
 * @author cd
 * @date 2019年4月1日 下午5:12:41
 * @desc
 */
public class RequestQueue {

	private LinkedList queue = new LinkedList();
	
	public synchronized Request getRequest(){
		while(queue.size() == 0)
			try {
				wait(); //等待 直到有新的Request加入
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return (Request) queue.remove();//返回Request队列中的第一个请求
	}
	
	public synchronized void addRequest(Request request){
		queue.add(request);
		notifyAll();//通知getRequest()方法
	}
}
