package com.cd.chapter02.decorator;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		IPacketCreator pc = new PacketHttpHeaderCreator(
							new PacketHtmlHeaderCreator(
							new PacketBodyCreator()));//����������ȹ���
		System.out.println(pc.handlerContent());
		//OutputStream ʹ�õ�װ����ģʽ
		//OutputStream ά��������� component ����
		//FileOutputStream //������� ϵͳ������
		//FilterOutputStream //װ����
		//BufferedOutputStream //�����װ����  ���ӻ��� ���Ż�IO����
		//DataOutputStream //����װ����   ���ӶԶ����������͵�д����֧��
		
		//����һ��������Ķ�����
		//DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("a.txt"))); //spend: 7
		//û�л��幦�ܵ�������
		DataOutputStream dout = new DataOutputStream(new FileOutputStream("a.txt")); //spend: 229
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			dout.writeLong(i);
		}
		System.out.println("spend: "+ (System.currentTimeMillis() - begin));
		
		//װ����ģʽ io����
		/**
		 * Main -> DataOutputStream -> BufferedOutputStream -> FileOutputStream
		 * 	 writeLong     ->       write            ->      write      
		 * 														BufferedOutputStreamͨ��flushBuffer()����write()�ṩ���� 
		 *  return         <-         return        <-       return
		 *  
		 *  
		 *  ��FileOutputStream.write() ֮ǰ����� �����ȵ��� BufferedOutputStream.write()   116 line
		 *  
		 *  ���Կ��� ������ÿ�� BufferedOutputStream.write() ���ö���ȥ����д�����ݣ����ǽ�����д�뻺���У�
		 *  ��������ʱ�Ż�ȥ���� FileOutputStream.write()���� ʵ��д�����ݣ��Դ�ʵ����������빦�������������
		 */
	}
}
