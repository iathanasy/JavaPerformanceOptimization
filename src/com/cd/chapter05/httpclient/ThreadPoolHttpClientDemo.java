package com.cd.chapter05.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * 多线程连接池实例
 * 
 * @author cd
 * @date 2019年5月7日 下午5:08:00
 * @desc
 */
public class ThreadPoolHttpClientDemo {

	public static void main(String[] args) {
		// 连接池对象
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		// 将最大连接数增加到200
		connectionManager.setMaxTotal(200);
		// 每个路由的默认连接数增加到20
		connectionManager.setDefaultMaxPerRoute(20);

		// HttpClient对象
		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(connectionManager).build();
		// URIs to DoGet
		String[] urisToGet = { "https://www.baidu.com/s?word=java",
				"https://www.baidu.com/s?word=java",
				"https://www.baidu.com/s?word=java",
				"https://www.baidu.com/s?word=java" };

		// 为每个URl创建一个线程
		GetThread[] threads = new GetThread[urisToGet.length];
		for (int i = 0; i < threads.length; i++) {
			HttpGet request = new HttpGet(urisToGet[i]);
			threads[i] = new GetThread(httpClient, request);
		}

		// 启动线程
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

		// join线程
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 执行Get请求线程
	 * 
	 * @author cd
	 * @date 2019年5月7日 下午5:11:53
	 * @desc
	 */
	public static class GetThread extends Thread {
		private final CloseableHttpClient httpClient;
		private final HttpContext context;
		private final HttpGet request;

		public GetThread(CloseableHttpClient httpClient, HttpGet request) {
			this.httpClient = httpClient;
			this.context = HttpClientContext.create();
			this.request = request;
		}

		@Override
		public void run() {
			try {
				CloseableHttpResponse response = httpClient.execute(request);
				try {
					HttpEntity entity = response.getEntity();
					String context = EntityUtils.toString(entity);
					System.out.println(context);

				} finally {
					EntityUtils.consume(response.getEntity());
					if (response != null)
						response.close();
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
