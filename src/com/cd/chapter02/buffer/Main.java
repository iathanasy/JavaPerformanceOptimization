package com.cd.chapter02.buffer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {

	private static final int circle = 100000;
	
	public static void main(String[] args) throws IOException {
		Writer write = new FileWriter(new File("file.txt"));
		long begin = System.currentTimeMillis();
		for (int i = 0; i < circle; i++) {
			write.write(i); //写入文件
		}
		write.close();
		//FileWrite spend: 64
		System.out.println("FileWrite spend: "+ (System.currentTimeMillis() - begin));
		
		
		Writer wr = new BufferedWriter(new FileWriter(new File("file.txt")));//增加缓冲
		begin = System.currentTimeMillis();
		for (int i = 0; i < circle; i++) {
			wr.write(i); //写入文件
		}
		wr.close();
		//BufferedWriter spend: 8
		System.out.println("BufferedWriter spend: "+ (System.currentTimeMillis() - begin));
	}
}
