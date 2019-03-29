package com.cd.chapter03.nio;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * @author cd
 * @date 2019年3月29日 下午3:07:21
 * @desc 直接内存访问
 * ByteBuffer: 在JVM堆上分配空间，其最大内存，受到最大堆的限制
 * DirectBuffer: 直接分配在物理内存中，并不占用堆空间
 * 
 * DirectBuffer 占用的内存并不在堆中，因此对堆空间的操作相对较少(DirectBuffer 对象本身还是在堆上分配的)
 * ByteBuffer 在堆上分配空间，其GC操作相对频繁
 * 
 * 在需要频繁创建Buffer的场合，由于DirectBuffer创建和销毁代价比较高，因此不适用的，
 * 但是如果能叫DirectBuffer进行复用，在读写频繁情况下，是可以大幅度改善系统性能。
 */
public class DirectBufferTest {
	/*
	 *  DirectBuffer: 19
		ByteBuffer: 23
	 * 可见 仅内存访问速度而言  DirectBuffer 比 ByteBuffer快
		DirectBuffer for: 17
		ByteBuffer for: 9

	 * DirectBuffer 虽然有访问速度上的优势，但是创建和销毁确远比  ByteBuffer 高。
	 */

	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		//DirectBuffer
		ByteBuffer b = ByteBuffer.allocateDirect(500);//分配DirectBuffer 直接在内存中分配
		for (int i = 0; i < 100000; i++) {
			for (int j = 0; j < 99; j++) {
				b.putInt(j);//向DirectBuffer中写入数据
			}
			b.flip();
			for (int j = 0; j < 99; j++) {
				b.getInt(); //读取数据
			}
			b.clear();
		}
		
		System.out.println("DirectBuffer: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		//ByteBuffer
		ByteBuffer b1 = ByteBuffer.allocate(500);//分配heap Buffer 在堆上分配
		for (int i = 0; i < 100000; i++) {
			for (int j = 0; j < 99; j++) 
				b1.putInt(j);//向heap Buffer中写入数据
			
			b1.flip();
			for (int j = 0; j < 99; j++) 
				b1.getInt(); //读取数据
			
			b1.clear();
		}
		
		System.out.println("ByteBuffer: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		//直接在内存中分配Buffer
		for (int i = 0; i < 20000; i++) {
			ByteBuffer bb = ByteBuffer.allocateDirect(1000);
		}
		System.out.println("DirectBuffer for: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		//在堆上分配Buffer
		for (int i = 0; i < 20000; i++) {
			ByteBuffer bb = ByteBuffer.allocate(1000);
		}
		System.out.println("ByteBuffer for: "+ (System.currentTimeMillis() - begin));
		
		try {
			monDirectBuffer();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 监控DirectBuffer的使用情况
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static void monDirectBuffer() throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Class c = Class.forName("java.nio.Bits");//通过反射取
		
		Field maxMemory = c.getDeclaredField("maxMemory");
		maxMemory.setAccessible(true);//得私有数据
		Field reservedMemory = c.getDeclaredField("reservedMemory");
		reservedMemory.setAccessible(true);
		synchronized(c){
			Long maxMemoryValue = (Long) maxMemory.get(null);//总大小
			AtomicLong reservedMemoryValue = (AtomicLong) reservedMemory.get(null);//剩余大小
			
			System.out.println("maxMemoryValue: "+ maxMemoryValue);
			System.out.println("reservedMemoryValue: "+ reservedMemoryValue);
		}
	}
}
