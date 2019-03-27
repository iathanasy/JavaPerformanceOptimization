package com.cd.chapter03.string;

import java.util.*;

/**
 * @author cd
 * @date 2019年3月27日 下午2:59:43
 * @desc 
 */
public class SubStringTest {

	public static void main(String[] args) {
		List<String> handler = new ArrayList<String>();
		
		/**
		 * HugeStr 不到1000次就内存溢出
		 * ImprovedHugeStr 不会
		 */
		for (int i = 0; i < 100000; i++) {
			HugeStr h = new HugeStr();
			//ImprovedHugeStr h = new ImprovedHugeStr();
			handler.add(h.getSubString(1, 5));
		}
	}
	
	static class HugeStr{
		private String str = new String(new char[100000]);//一个很长的String
		public String getSubString(int begin, int end){//截取字符串 有溢出
			return str.substring(begin, end);
		}
	}
	
	static class ImprovedHugeStr{
		private String str = new String(new char[100000]);
		public String getSubString(int begin, int end){//截取子字符串，并重新生成
			//主要使用的以空间换时间的策略
			return new String(str.substring(begin, end));//新的字符串，没有溢出
		}
	}
}

