package com.cd.chapter02.decorator;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		IPacketCreator pc = new PacketHttpHeaderCreator(
							new PacketHtmlHeaderCreator(
							new PacketBodyCreator()));//核心组件最先构造
		System.out.println(pc.handlerContent());
		//OutputStream //使用的装饰者模式
		//OutputStream //维护核心组件 component 对象
		//FileOutputStream //具体组件 系统核心类
		//FilterOutputStream //装饰者
		//BufferedOutputStream //具体的装饰器  增加缓冲 ，优化IO性能
		//DataOutputStream //具体装饰器   增加对多种数据类型的写操作支持
		
		//生成一个带缓冲的对象流
		//DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("a.txt"))); //spend: 7
		//没有缓冲功能的流对象
		DataOutputStream dout = new DataOutputStream(new FileOutputStream("a.txt")); //spend: 229
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			dout.writeLong(i);
		}
		System.out.println("spend: "+ (System.currentTimeMillis() - begin));
		
		//装饰者模式 io流程
		/**
		 * Main -> DataOutputStream -> BufferedOutputStream -> FileOutputStream
		 * 	 writeLong     ->       write            ->      write      
		 * 														BufferedOutputStream通过flushBuffer()调用write()提供性能 
		 *  return         <-         return        <-       return
		 *  
		 *  
		 *  在FileOutputStream.write() 之前会调用 会首先调用 BufferedOutputStream.write()   116 line
		 *  
		 *  可以看到 并不是每次 BufferedOutputStream.write() 调用都会去磁盘写入数据，而是将数据写入缓存中，
		 *  当缓存满时才会去调用 FileOutputStream.write()方法 实际写入数据，以此实现性能组件与功能组件完美分离
		 */
	}
}
