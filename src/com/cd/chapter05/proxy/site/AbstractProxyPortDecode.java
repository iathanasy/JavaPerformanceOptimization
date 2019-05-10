package com.cd.chapter05.proxy.site;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.cd.chapter05.proxy.parser.ProxyListPageParser;

/**
 * 抽象 代理端口编解码
 * 
 * @author cd
 * @date 2019年5月8日 下午4:20:22
 * @desc 需要实现端口解码的 可以继承
 */
public abstract class AbstractProxyPortDecode implements ProxyListPageParser {

	/**
	 * js代码port还原
	 * 
	 * @param doc
	 */
	public void setPort(Document doc, String cssQuery) {
		// select ".port"
		for (Element e : doc.select(cssQuery)) {// $('.port').each(function() {
			String a = e.text();// var a = $(this).html();
			if (a.indexOf("*") != -0x1) {// if (a.indexOf('*') != -0x1) {
				return;
			}
			String b = e.attr("class");// var b = $(this).attr('class');
			b = b.split(" ")[0x1];// b = (b.split(" "))[0x1];
			String[] c = b.split("");// var c = b.split("");
			int d = b.length();// var d = c.length;
			StringBuilder f = new StringBuilder();// var f = [];
			for (int g = 0x0; g < d; g++) {// for (var g = 0x0; g < d; g++) {
				f.append("ABCDEFGHIZ".indexOf(c[g]));// f.push('ABCDEFGHIZ'.indexOf(c[g]))
			}
			e.text(String.valueOf(Integer.valueOf(f.toString()) >> 0x3));// $(this).html(window.parseInt(f.join(''))
																			// >>
																			// 0x3)
		}
	}

	/**
	 * 原js地址：http://www.goubanjia.com/theme/goubanjia/javascript/pde.js?v=1.0
	 * port js解码后 解密网站： https://tool.lu/js
	 * 
	 * var _$ = ['.port', "each", "html", "indexOf", '*', "attr", 'class',
	 * "split", " ", "", "length", "push", 'ABCDEFGHIZ', "parseInt", "join",
	 * '']; $(function() { $(_$[0])[_$[1]](function() { var a =
	 * $(this)[_$[2]](); if (a[_$[3]](_$[4]) != -0x1) { return }; var b =
	 * $(this)[_$[5]](_$[6]); try { b = (b[_$[7]](_$[8]))[0x1]; var c =
	 * b[_$[7]](_$[9]); var d = c[_$[10]]; var f = []; for (var g = 0x0; g < d;
	 * g++) { f[_$[11]](_$[12][_$[3]](c[g])) };
	 * $(this)[_$[2]](window[_$[13]](f[_$[14]](_$[15])) >> 0x3) } catch (e) {}
	 * }) })
	 */
}
