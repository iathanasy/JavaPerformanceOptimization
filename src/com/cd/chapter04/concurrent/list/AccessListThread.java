package com.cd.chapter04.concurrent.list;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发List
 * 
 * @author cd
 * @date 2019年4月2日 下午3:03:25
 * @desc Vector 或者 CopyOnWriteArrayList是两个线程安全的List实现的，ArrayList不是线程安全的。
 *       应该避免在多线程环境中使用ArrayList ,如果因为某些原因必须要使用 则使用
 *       Conllections.synchronizedList(List list) 进行包装
 * 
 *       CopyOnWriteArrayList：
 *       当对象进行写操作时，复制该对象，对副本进行修改，然后将副本写回。若进行读操作，则直接返回结果，操作过程中不进行同步。 读快，写慢
 *       适合读多写少的 Vector: 使用了同步关键字synchronized 适合写多读少的场合
 * 
 *       并发Set 和List一样的 也有CopyOnWriteArrayList、 Conllections.synchronizedSet(Set
 *       set)
 */
public class AccessListThread implements Runnable {
	protected String name;
	Random r = new Random();

	Vector<Object> list = new Vector<Object>(); // testVectorspend time: 105

	// CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>();
	// //testCopyOnWriteArrayListspend time: 117

	public AccessListThread() {
	}

	public AccessListThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 500; i++) {
				getList(r.nextInt(1000));// 每个线程进行500次get()保证高并发
			}

			Thread.sleep(r.nextInt(100));
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public Object getList(int index) {
		return list.get(index);
	}
}
