package com.cd.chapter03.nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 * @author cd
 * @date 2019年3月29日 下午3:07:03
 * @desc MappedByteBuffer 性能测试
 */
public class MappedByteBufferTest {

	/**
	 * 测试数据
	 * 
	 *  DataOutputStream: 191
		DataInputStream: 257
		ByteBuffer Out: 77
		ByteBuffer In: 10
		映射到内存 Out: 13
		映射到内存 In: 6


	 *Stream 边读边处理数据
	 *ByteBuffer 一次性读入内存 在做后续处理
	 */
	static final int numOfInts = 4000000; 
	static final String path = "D://tem_stream.tmp";
	public static void main(String[] args) throws IOException {
		long begin = System.currentTimeMillis();
		//传统基于流的方式 
		//if(!f.exists()) f.createNewFile(); //不存在就创建
		//写入数据
		DataOutputStream dos = new DataOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(new File(path))));
		
		for (int i = 0; i < numOfInts; i++) {
			dos.writeInt(i); //向文件中写入400W整数
		}
		if(dos != null) dos.close();
		
		System.out.println("DataOutputStream: "+ (System.currentTimeMillis() - begin));
		
		
		begin = System.currentTimeMillis();
		//读取数据
		DataInputStream dis = new DataInputStream(
				new BufferedInputStream(
						new FileInputStream(new File(path))));
		for (int i = 0; i < numOfInts; i++) {
			dis.readInt();
		}
		if(dis != null) dis.close();
		System.out.println("DataInputStream: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		//使用ByteBuffer实现写入数据
		FileOutputStream fout = new FileOutputStream(new File(path));
		FileChannel fc = fout.getChannel();//得到通道
		ByteBuffer byteBuf = ByteBuffer.allocate(numOfInts *4);
		for (int i = 0; i <numOfInts; i++) {
			byteBuf.put(int2byte(i));//将整数转换为数组
		}
		byteBuf.flip(); //准备写
		fc.write(byteBuf);
		System.out.println("ByteBuffer Out: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		//读取数据
		FileInputStream fin = new FileInputStream(new File(path));
		FileChannel fic = fin.getChannel();
		fic.read(byteBuf);//读取文件数据
		fic.close();
		byteBuf.flip();//准备读取数据
		while(byteBuf.hasRemaining()){
			byte2int(byteBuf.get(),byteBuf.get(),byteBuf.get(),byteBuf.get());//将byte转换为整数
		}
		System.out.println("ByteBuffer In: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		//直接映射到内存方式 写入数据
		FileChannel fcc = new RandomAccessFile(path, "rw").getChannel();
		IntBuffer ib = fcc.map(FileChannel.MapMode.READ_WRITE, 0, numOfInts * 4).asIntBuffer();//文件映射到内存
		for (int i = 0; i < numOfInts; i++) {
			ib.put(i);//写入文件
		}
		if(fcc != null) fcc.close();
		System.out.println("映射到内存 Out: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		//读取数据
		FileChannel frc = new FileInputStream(path).getChannel();
		 IntBuffer rib = frc.map(FileChannel.MapMode.READ_ONLY, 0, frc.size()).asIntBuffer(); //文件映射到内存
		 while(rib.hasRemaining())
			 rib.get(); //获取数据
		 if(frc != null) frc.close();
		 
		 System.out.println("映射到内存 In: "+ (System.currentTimeMillis() - begin));
	}
	
	
	
	public static byte[] int2byte(int res){
		byte[] targets = new byte[4];
		targets[3] = (byte) (res & 0xff); //最低位
		targets[2] = (byte) ((res >> 8) & 0xff); //次低位
		targets[1] = (byte) ((res >> 16) & 0xff); //次高位
		targets[0] = (byte) (res >>> 24); //最高位 ,无符号右移
		return targets;
	}
	
	/**
	 * 将每4个byte转为int值
	 * @param res
	 * @return
	 */
	public static int byte2int(byte b1, byte b2, byte b3, byte b4){
		return ((b1 & 0xff) << 24) | ((b2 & 0xff) << 16) | ((b3 & 0xff) << 8) | (b4 & 0xff);
	}
}
