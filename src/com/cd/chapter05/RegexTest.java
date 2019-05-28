package com.cd.chapter05;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cd.chapter05.proxy.common.utlis.HttpClientUtils;

public class RegexTest {

	public static void main(String[] args) throws IOException {
		getLinkUrl();
		
	}
	
	/**
	 * 获取网站的所有连接
	 * @throws IOException
	 */
	public static void getLinkUrl() throws IOException{
		//String regex = "[https|http]+://[\\w]+.meishij.net(/[\\w]+)+[/|(.\\w)]+";//不带参数
		//String regex = "[https|http]+://[\\w]+.meishij.net(/[\\w]+)+[/|(.\\w/?%&=*)?]+";//带参数
		String regex = "[https|http]+://[\\w]+.meishij.net(/|([\\w]|[\u4E00-\u9FA5\\w()])+)+[/|(.\\w/?%&=*)+]*";//支持中文
		String input = HttpClientUtils.getWebPage("https://www.meishij.net");
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		System.out.println(input);
		System.out.println(m.find());
		while(m.find()){
			System.out.println(m.group());
		}
	}
}
