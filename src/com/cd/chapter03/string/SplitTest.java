package com.cd.chapter03.string;

import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * ���ַ���ִ��ʱ��
 * spilt: 284
 * StringTokenizer: 279
 * indexOf: 7283
 * @author cd
 * @date 2019��3��27�� ����3:33:08
 * @desc
 */
public class SplitTest {

	public static void main(String[] args) {
		//1.split�ָ��ַ���,����ǿ�� ������ȷ�������� ,������ϵͳ��Ƶ��ʹ��������� �ǲ���ȡ��
		String str = "a;b,c:d";
		System.out.println(Arrays.asList(str.split("[;|,|:]")));
		
		String orgStr = null; 
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 1000; i++) {
			sb.append(i);
			sb.append(";");
		}
		orgStr = sb.toString();
		
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			orgStr.split(";");
		}
		System.out.println("spilt: "+ (System.currentTimeMillis() - begin));
		
		
		//2.ʹ�ø���Ч��StringTokenizer��ָ��ַ���
		begin = System.currentTimeMillis();
		StringTokenizer st = new StringTokenizer(orgStr,";");
		for (int i = 0; i < 10000; i++) {
			while(st.hasMoreTokens())
				st.nextToken();
			
			st = new StringTokenizer(orgStr,";");
		}
		
		System.out.println("StringTokenizer: "+ (System.currentTimeMillis() - begin));
		
		//3.���Ż����ַ����ָʽ  ������Ч�ʼ������
		begin = System.currentTimeMillis();
		String tmp = orgStr;
		for (int i = 0; i < 10000; i++) {
			while(true){
				String splitStr = null;
				int j = tmp.indexOf(";");//�ҷָ�����λ��
				if(j < 0) break; //û���ҵ��ָ���
				splitStr = tmp.substring(0,j);//�ҵ��ָ�����ȡ���ַ���
				tmp = tmp.substring(j+1);//ʣ����Ҫ������ַ���
			}
			tmp = orgStr;
		}
		
		System.out.println("indexOf: "+ (System.currentTimeMillis() - begin));
		

	}
}
