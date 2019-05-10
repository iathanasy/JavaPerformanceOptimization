package com.cd.chapter05.httpclient;

import java.io.IOException;
import java.util.HashSet;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 利用jsoup解析数据，还原js数据混淆
 * 
 * @author cd
 * @date 2019年5月6日 上午10:47:18
 * @desc
 */
public class IpDemo {
	private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36";

	public static final HashSet<String> setIp = new HashSet<String>();;

	public static void main(String[] args) {
		crawlingGoubanjiaIp();
		crawlingData5uIp();
		System.out.println("------------" + setIp.size()
				+ "-------------------");
		System.out.println(setIp.toString());

	}

	public static void init() {
		crawlingGoubanjiaIp();
		crawlingData5uIp();
	}

	/**
	 * www.goubanjia.com 爬取IP
	 * 
	 * @return
	 */
	public static HashSet<String> crawlingGoubanjiaIp() {

		try {
			String url = "http://www.goubanjia.com/";
			CloseableHttpClient httpClient = HttpClients.custom()
					.setUserAgent(userAgent).build();
			HttpGet request = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(request);
			String responseStr = EntityUtils.toString(response.getEntity(),
					"UTF-8");
			// System.out.println(responseStr);
			// 解析数据
			Document document = Jsoup.parse(responseStr);
			setPort(document);
			// 获取class="table" 的所有子节点tr <table class="table table-hover">
			Elements elements = document.select("table.table tr");
			for (Element element : elements) {
				// 获取td节点
				Element td = element.select("td").first();
				if (null == td)
					continue;

				/**
				 * 查找所有style属性包含none字符串的标签（页面上未显示的标签），并移除 包括以下两种 style=display:
				 * none; style=display:none;
				 */
				Elements ipTd = td.select("[style*=none;]");
				for (Element none : ipTd) {
					none.remove();
				}
				// 移除空格
				String ipPort = td.text().replaceAll(" ", "");
				// 打印
				System.out.println("goubanjia---> " + ipPort);
				String ip = ipPort.split(":")[0];
				Integer port = Integer.parseInt(ipPort.split(":")[1]);
				try {
					CloseableHttpResponse body = getResponse(url, ip, port);
					if (body != null)
						setIp.add(ipPort);
				} catch (IOException e) {
					System.err.println("代理异常：" + e.getMessage());
					continue;
				}
				if (response != null)
					setIp.add(ipPort);

				/**
				 * 第一次运行结果： 124.152.32.140:9060 219.223.222.6:8827
				 * 183.129.244.21:8090 58.50.179.100:8277 113.200.56.13:8579
				 * 202.112.237.102:8809
				 * 
				 * 第二次运行结果 124.152.32.140:9028 219.223.222.6:8308
				 * 183.129.244.21:8399 58.50.179.100:8932 113.200.56.13:8415
				 * 202.112.237.102:8549
				 * 
				 * ip地址能够准确的拿到了，却发现port被做了混淆，而且每次返回的port还在动态改变。
				 */
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return setIp;
	}

	/**
	 * http://www.data5u.com/ 爬取 IP
	 * 
	 * @return
	 */
	public static HashSet<String> crawlingData5uIp() {
		String url = "http://www.data5u.com/";
		String responseStr = getBody(url);
		Document document = Jsoup.parse(responseStr);
		setPort(document);
		Elements ul = document.select("ul.l2");

		// System.out.println(ul);
		for (Element element : ul) {
			String ip = element.select("span li").first().text();
			Integer port = Integer.parseInt(element.select("span li.port")
					.text());
			String ipPort = ip + ":" + port;
			System.out.println("data5u---> " + ipPort);
			try {
				CloseableHttpResponse body = getResponse(url, ip, port);
				if (body != null)
					setIp.add(ipPort);
			} catch (IOException e) {
				System.err.println("代理异常：" + e.getMessage());
				continue;
			}
		}
		return setIp;
	}

	/**
	 * 返回网页内容
	 * 
	 * @return
	 */
	public static String getBody(String uri) {
		CloseableHttpClient httpClient = HttpClients.custom()
				.setUserAgent(userAgent).build();
		HttpGet request = new HttpGet(uri);
		String body = null;
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			body = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (ClientProtocolException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();

		}
		return body;
	}

	/**
	 * 测试/设置 代理IP
	 * 
	 * @return
	 * @throws IOException
	 */
	public static CloseableHttpResponse getResponse(String uri, String ip,
			Integer port) throws IOException {
		CloseableHttpClient httpClient = HttpClients.custom()
				.setUserAgent(userAgent).build();
		HttpGet request = new HttpGet(uri);

		// 设置代理IP，设置连接超时时间 、 设置 请求读取数据的超时时间 、 设置从connect
		// Manager获取Connection超时时间、
		HttpHost proxy = new HttpHost(ip, port);
		RequestConfig config = RequestConfig.custom().setProxy(proxy)
				.setConnectTimeout(3000).setSocketTimeout(3000)
				.setConnectionRequestTimeout(3000).build();
		request.setConfig(config);

		// 请求
		CloseableHttpResponse response = httpClient.execute(request);
		return response;
	}

	/**
	 * js代码port还原
	 * 
	 * @param doc
	 */
	private static void setPort(Document doc) {
		for (Element e : doc.select(".port")) {// $('.port').each(function() {
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
