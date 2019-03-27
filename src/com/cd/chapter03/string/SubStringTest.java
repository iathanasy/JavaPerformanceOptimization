package com.cd.chapter03.string;

import java.util.*;

/**
 * @author cd
 * @date 2019��3��27�� ����2:59:43
 * @desc 
 */
public class SubStringTest {

	public static void main(String[] args) {
		List<String> handler = new ArrayList<String>();
		
		/**
		 * HugeStr ����1000�ξ��ڴ����
		 * ImprovedHugeStr ����
		 */
		for (int i = 0; i < 100000; i++) {
			HugeStr h = new HugeStr();
			//ImprovedHugeStr h = new ImprovedHugeStr();
			handler.add(h.getSubString(1, 5));
		}
	}
	
	static class HugeStr{
		private String str = new String(new char[100000]);//һ���ܳ���String
		public String getSubString(int begin, int end){//��ȡ�ַ��� �����
			return str.substring(begin, end);
		}
	}
	
	static class ImprovedHugeStr{
		private String str = new String(new char[100000]);
		public String getSubString(int begin, int end){//��ȡ���ַ���������������
			//��Ҫʹ�õ��Կռ任ʱ��Ĳ���
			return new String(str.substring(begin, end));//�µ��ַ�����û�����
		}
	}
}

