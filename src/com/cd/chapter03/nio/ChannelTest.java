package com.cd.chapter03.nio;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author cd
 * @date 2019年3月28日 下午5:42:42
 * @desc 通道测试
 */
public class ChannelTest {

	public static void main(String[] args) {
		try {
			FileInputStream fin = new FileInputStream(new File("SerSingleton.txt"));
			FileChannel channel = fin.getChannel(); //获取读通道
			ByteBuffer buf = ByteBuffer.allocate(1024); //定义容量
			while(true){
				int len = channel.read(buf); //读入数据 到ByteBuffer
				if(len == -1) break;
				System.out.println(new String(buf.array()));
			}
			fin.close();//关闭通道
			
			nioCopyFile("SerSingleton.txt","D://1.txt");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			
		}
	}
	
	/**
	 * copy文件
	 * @param resource 源路径
	 * @param destination 
	 * @throws IOException 
	 */
	public static void nioCopyFile(String resource, String destination) throws IOException{
		FileInputStream in = new FileInputStream(new File(resource));
		FileOutputStream out = new FileOutputStream(new File(destination));
		
		FileChannel read = in.getChannel(); //读通道
		FileChannel write = out.getChannel();//写通道
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while(true){
			buffer.clear();//不清空每次数据都会在里面
			int len = read.read(buffer);
			if(len == -1) break;
			buffer.flip();
			write.write(buffer);
		}
		
		in.close();
		out.close();
	}
}
