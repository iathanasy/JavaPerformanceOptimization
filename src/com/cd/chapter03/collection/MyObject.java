package com.cd.chapter03.collection;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * 引用类型  ：强引用、软引用、弱引用、虚引用
 * 强引用：1.可以直接访问目标对象  
 *      2.指向的对象在任何时候都不会被系统回收，JVM宁愿抛出OOM异常，也不回收引用所指向的对象 
 *      3.可能导致内存泄漏
 * 软引用：除了强引用外，最强的引用类型。可以使用一个引用队列，当对象被回收时，就会加入到这个队列中。
 * 
 * 		软引用、弱引用都非常适合保存那些可有可无的缓存数据。当系统内存不足时，这些缓存数据会被回收，不会导致内存溢出。
 * 		而当内存充足时，缓存可以存在相当长的时间，从而起到加速系统的作用。
 * 
 * @author cd
 * @date 2019年3月29日 下午5:44:31
 * @desc
 */
public class MyObject {

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("MyObject 被回收了...");//被回收时输出
	}
	
	@Override
	public String toString() {
		return "I am MyObject";
	}
	//配置vm   -Xmx5M
	static MyObject obj = new MyObject();//强引用
	static ReferenceQueue<MyObject> softQueue = new ReferenceQueue<MyObject>();//创建引用队列
	static SoftReference<MyObject> softRef;
	
	public static void main(String[] args) {
		softRef = new SoftReference<MyObject>(obj, softQueue);//创建软引用
		new Thread(new CheckRefQueue()).start(); //检测引用队列，监控对象回收情况
		obj = null;// 删除强引用
		System.gc();
		System.out.println("分配大块内存");
		//java.lang.OutOfMemoryError: Java heap space
		byte[] b = new byte[4*1024*925];//分配一块较大内存区，强迫gc
		System.out.println("softRef: "+ softRef.get());
	}
	
	
	//监控对象回收情况
	static class CheckRefQueue implements Runnable{
		Reference<MyObject> obj = null;

		@Override
		public void run() {
			//如果对象被回收则进入引用队列
			try {
				obj = (Reference<MyObject>) softQueue.remove();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(obj != null) System.out.println("obj: "+ obj.get());
				
		}
		
	}

}
