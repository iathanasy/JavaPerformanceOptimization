package com.cd.chapter05.proxy.task;

import static com.cd.chapter05.proxy.ProxyPool.proxyQueue;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;

import com.cd.chapter05.proxy.ProxyPool;
import com.cd.chapter05.proxy.common.entity.Page;
import com.cd.chapter05.proxy.common.httpclient.AbstractHttpClient;
import com.cd.chapter05.proxy.common.utlis.Constants;
import com.cd.chapter05.proxy.common.utlis.HttpClientUtils;
import com.cd.chapter05.proxy.entity.Proxy;

/**
 * 代理检测task 将可用代理添加到DelayQueue延时队列中
 * 
 * @author cd
 * @date 2019年5月8日 下午6:07:47
 * @desc
 */
public class ProxyTestTask implements Runnable {

	private static Logger logger = Logger.getLogger(ProxyTestTask.class);

	private Proxy proxy;
	private AbstractHttpClient httpClient;
	private String url;

	public ProxyTestTask(Proxy proxy, AbstractHttpClient httpClient, String url) {
		this.proxy = proxy;
		this.httpClient = httpClient;
		this.url = url;
	}

	@Override
	public void run() {
		int size = proxyQueue.size();
		int count = Constants.proxyNumberThreshold;
		if (size >= count) {
			logger.debug(String.format("当前可用代理 %s 个,暂时不测试代理", size));
			return;
		}
		long startTime = System.currentTimeMillis();

		/*if (!proxy.getAnonymous().contains("匿")) {
			// 丢弃透明代理
			return;
		}*/
		HttpGet request = null;
		try {
			Page page = null;
			if (StringUtil.isBlank(proxy.getIp())) {
				page = httpClient.getWebPage(url);
			} else {
				// 代理测试
				request = new HttpGet(url);
				HttpHost httpHost = new HttpHost(proxy.getIp(), proxy.getPort());
				request.setConfig(HttpClientUtils.getRequestConfigBuilder()
						.setProxy(httpHost).build());
				page = httpClient.getWebPage(request);
			}

			long endTime = System.currentTimeMillis();
			String logStr = Thread.currentThread().getName() + " "
					+ proxy.getProxyStr() + "  executing request "
					+ page.getUrl() + " response statusCode:"
					+ page.getStatusCode() + "  request cost time:"
					+ (endTime - startTime) + "ms";

			if (page == null || page.getStatusCode() != 200) {
				logger.warn(logStr);
				return;
			}
			request.releaseConnection();// 释放连接
			proxy.setResponseTime(endTime - startTime);
			logger.debug(proxy.toString() + "---------" + page.toString());
			if (!(ProxyPool.proxySet.contains(proxy)
					|| !proxy.getAnonymous().contains("匿")// 匿名
			|| proxy.getResponseTime() >= Constants.httpTimeout)// 超过5s丢弃
			) {
				logger.info(proxy.toString() + "----------代理可用--------请求耗时:"
						+ (endTime - startTime) + "ms");
				ProxyPool.lock.writeLock().lock();
				try {
					proxy.setLastSuccessfulTime(System.currentTimeMillis());
					ProxyPool.proxySet.add(proxy);
				} finally {
					ProxyPool.lock.writeLock().unlock();
				}
				ProxyPool.proxyQueue.add(proxy);
			}
		} catch (IOException e) {
			logger.debug("IOException:", e);
		} finally {
			if (request != null) {
				request.releaseConnection();
			}
		}

	}

}
