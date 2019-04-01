package com.cd.chapter03.skill;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Demo02 {

	public static void main(String[] args) throws IOException {
		/*
		 * FileWriter FileReader 要优于 OutputStream InputStream 
		 * 测试的循环次数 count * 10
		 */
		String path = "file.txt";
		int count = 10000;
		//OutputStream InputStream
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(path));
		long start = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			dos.writeBytes(String.valueOf(i) + "\r\n"); //写入数据
		}
		dos.close();
		System.out.println("OutputSream: "+ (System.currentTimeMillis()- start));
		
		DataInputStream dis = new DataInputStream(new FileInputStream(path));
		start = System.currentTimeMillis();
		int line = 0;
		while((line = dis.read()) != -1) dis.readLine();//读取数据
		dis.close();
		System.out.println("InputSream: "+ (System.currentTimeMillis()- start));
		
		/////////////////////////////////////////////////
		
		//使用缓冲 Buffered
		
		//OutputStream InputStream
		dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
		start = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			dos.writeBytes(String.valueOf(i) + "\r\n"); //写入数据
		}
		dos.close();
		System.out.println("Buffered OutputSream: "+ (System.currentTimeMillis()- start));
		
		dis = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
		start = System.currentTimeMillis();
		
		while(dis.readLine() != null) ;//读取数据
		dis.close();
		System.out.println("Buffered InputSream: "+ (System.currentTimeMillis()- start));
		
		///////////////////////////////////////////////////
		//Writer Reader
		FileWriter fw = new FileWriter(path);
		start = System.currentTimeMillis();
		for (int i = 0; i < count * 10; i++) { //count * 10
			fw.write(String.valueOf(i) + "\r\n"); //写入数据
		}
		fw.close();
		System.out.println("FileWriter: "+ (System.currentTimeMillis()- start));
		
		FileReader fr = new FileReader(path);
		start = System.currentTimeMillis();
		while(fr.read() != -1);
		fr.close();
		System.out.println("FileReader: "+ (System.currentTimeMillis()- start));
		
		////////////////////////////////////////////////
		//使用Buffered
		BufferedWriter bfw = new BufferedWriter(new FileWriter(path));
		start = System.currentTimeMillis();
		for (int i = 0; i < count * 10; i++) { //count * 10
			bfw.write(String.valueOf(i) + "\r\n"); //写入数据
		}
		bfw.close();
		System.out.println("BufferedFileWriter: "+ (System.currentTimeMillis()- start));
		
		BufferedReader bfr = new BufferedReader(new FileReader(path));
		start = System.currentTimeMillis();
		while(bfr.read() != -1);
		bfr.close();
		System.out.println("BufferedFileReader: "+ (System.currentTimeMillis()- start));
	}
}
