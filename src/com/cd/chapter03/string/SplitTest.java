package com.cd.chapter03.string;

import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 三种方法执行时间
 * spilt: 284
 * StringTokenizer: 279
 * indexOf: 7283
 * @author cd
 * @date 2019年3月27日 下午3:33:08
 * @desc
 */
public class SplitTest {

	public static void main(String[] args) {
		//1.split分割字符简单,功能强大 但性能确不尽人意 ,在敏感系统中频繁使用这个方法 是不可取的
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
		
		
		//2.使用更高效的StringTokenizer类分割字符串
		begin = System.currentTimeMillis();
		StringTokenizer st = new StringTokenizer(orgStr,";");
		for (int i = 0; i < 10000; i++) {
			while(st.hasMoreTokens())
				st.nextToken();
			
			st = new StringTokenizer(orgStr,";");
		}
		
		System.out.println("StringTokenizer: "+ (System.currentTimeMillis() - begin));
		
		//3.更优化的字符串分割方式  经测试效率及其底下
		begin = System.currentTimeMillis();
		String tmp = orgStr;
		for (int i = 0; i < 10000; i++) {
			while(true){
				String splitStr = null;
				int j = tmp.indexOf(";");//找分隔符的位置
				if(j < 0) break; //没有找到分隔符
				splitStr = tmp.substring(0,j);//找到分隔符截取子字符串
				tmp = tmp.substring(j+1);//剩下需要处理的字符串
			}
			tmp = orgStr;
		}
		
		System.out.println("indexOf: "+ (System.currentTimeMillis() - begin));
		

	}
}
