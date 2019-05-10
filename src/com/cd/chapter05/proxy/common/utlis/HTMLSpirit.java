package com.cd.chapter05.proxy.common.utlis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HTML工具
 * 
 * @author cd
 * @date 2019年5月7日 下午3:05:42
 * @desc
 */
public class HTMLSpirit {

	/**
	 * 去掉所有HTML标签
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}

	/**
	 * 特殊字符处理替换
	 * 
	 * @param str
	 * @return
	 */
	public static String htmlReplace(String str) {
		str = str.replace("&ldquo;", "“");
		str = str.replace("&rdquo;", "”");
		str = str.replace("&nbsp;", " ");
		// str = str.replace("&", "&amp;");
		str = str.replace("&#39;", "'");
		str = str.replace("&rsquo;", "’");
		str = str.replace("&mdash;", "—");
		str = str.replace("&ndash;", "–");
		str = str.replace("&lt;", "");
		str = str.replace("&gt;", "");
		return str;
	}
}
