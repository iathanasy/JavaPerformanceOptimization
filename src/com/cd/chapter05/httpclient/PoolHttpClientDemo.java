package com.cd.chapter05.httpclient;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * 连接池实例
 * 
 * @author cd
 * @date 2019年5月7日 下午3:38:50
 * @desc
 */
public class PoolHttpClientDemo {

	public static void main(String[] args) {
		// 创建HTTP的连接池管理对象
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// 将最大连接数增加到200
		cm.setMaxTotal(200);
		// 每个路由的默认连接数增加到20
		cm.setDefaultMaxPerRoute(20);
		// 可设置某个访问的最大连接数
		/*
		 * HttpHost httpHost = new HttpHost("https://www.baidu.com/");
		 * cm.setMaxPerRoute(new HttpRoute(httpHost), 50);
		 */

		// 发起3次get请求
		String url = "http://www.xbiquge.la";
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			doGet(cm, url);
		}
		long end = System.currentTimeMillis();
		System.out.println("consume -> " + (end - start));

		// 清理无效连接
		new IdleConnectionEvictor(cm).start();
	}

	/**
	 * 请求重试处理
	 * 
	 * @param retryCount
	 *            重试次数
	 * @return
	 */
	public static HttpRequestRetryHandler retryHandler(final int retryCount) {
		HttpRequestRetryHandler httRequestRetryHandler = new HttpRequestRetryHandler() {

			@Override
			public boolean retryRequest(IOException exception,
					int executionCount, HttpContext context) {
				if (executionCount >= retryCount) {
					// 如果已经重试了5次，就放弃
					return false;
				}
				if (exception instanceof InterruptedIOException) {
					// 超时
					return false;
				}
				if (exception instanceof UnknownHostException) {
					// 目标服务器不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {
					// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {
					// ssl握手异常
					return false;
				}
				HttpClientContext clientContext = HttpClientContext
						.adapt(context);
				HttpRequest request = clientContext.getRequest();
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// 如果请求是幂等的，就再次尝试
					return true;
				}
				return false;
			}
		};
		return httRequestRetryHandler;
	}

	/**
	 * get
	 * 
	 * @param connectionManager
	 *            连接管理
	 * @param url
	 */
	public static void doGet(HttpClientConnectionManager connectionManager,
			String url) {
		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(connectionManager)
				.setRetryHandler(retryHandler(5)).build();

		// 创建Http Get 请求
		HttpGet request = new HttpGet(url);
		// 构建请求配置信息
		RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)// 创建连接的最长时间
				.setConnectionRequestTimeout(500)// 从连接池中获取到连接最长时间
				.setSocketTimeout(1000 * 10)// 数据传输的最长时间 10s
				// .setStaleConnectionCheckEnabled(true)//提交请求前测试连接是否可用
				.build();
		// 设置请求配置信息
		request.setConfig(config);

		CloseableHttpResponse response = null;

		try {
			// 执行请求
			response = httpClient.execute(request);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				String context = EntityUtils.toString(response.getEntity(),
						"UTF-8");
				System.out.println("内容长度：" + context);
			}
		} catch (IOException e) {
			System.err.println("请求失败 -->" + e.getMessage());
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					System.err.println("关闭response 失败 --> " + e.getMessage());
				}
			}
			// 此处不能关闭httpClient，如果关闭httpClient，连接池也会销毁
			// httpClient.close();
		}
	}

	/**
	 * 监听连接池中空闲连接，清理无效连接
	 * 
	 * @author cd
	 * @date 2019年5月7日 下午4:33:29
	 * @desc
	 */
	public static class IdleConnectionEvictor extends Thread {
		private final HttpClientConnectionManager connectionManager;
		private volatile boolean shutdown;

		public IdleConnectionEvictor(
				HttpClientConnectionManager connectionManager) {
			this.connectionManager = connectionManager;
		}

		@Override
		public void run() {
			try {
				while (!shutdown) {
					synchronized (this) {
						wait(3000);// 3s检测一次
						// 关闭失效的连接
						connectionManager.closeExpiredConnections();
					}
				}
			} catch (InterruptedException e) {
				// 结束
				e.printStackTrace();
			}
		}

		public void shutdown() {
			shutdown = true;
			synchronized (this) {
				notifyAll();
			}
		}
	}
}
