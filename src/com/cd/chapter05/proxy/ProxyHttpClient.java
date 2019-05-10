package com.cd.chapter05.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import com.cd.chapter05.proxy.common.httpclient.AbstractHttpClient;
import com.cd.chapter05.proxy.common.utlis.Constants;
import com.cd.chapter05.proxy.common.utlis.SimpleThreadPoolExecutor;
import com.cd.chapter05.proxy.common.utlis.ThreadPoolUtil;
import com.cd.chapter05.proxy.task.ProxyPageTask;

/**
 * 代理客户端
 * 
 * @author cd
 * @date 2019年5月8日 下午4:45:46
 * @desc
 */
public class ProxyHttpClient extends AbstractHttpClient {

	private static final Logger logger = Logger
			.getLogger(ProxyHttpClient.class);
	private volatile static ProxyHttpClient instance;

	// 单例
	public static ProxyHttpClient getInstance() {
		if (instance == null) {
			synchronized (ProxyHttpClient.class) {
				if (instance == null) {
					instance = new ProxyHttpClient();
				}
			}
		}
		return instance;
	}

	/**
	 * 代理测试线程池
	 */
	private ThreadPoolExecutor proxyTestThreadExecutor;

	private Map<String, SimpleThreadPoolExecutor> threadPoolExecutorMap = new HashMap();

	private ProxyHttpClient() {
		initThreadPool();
	}

	/**
	 * 初始化线程池
	 */
	private void initThreadPool() {
		// 测试线程池
		proxyTestThreadExecutor = ThreadPoolUtil.createThreadPool(
				"proxyTestThreadExecutor", Constants.proxyTestCorePoolSize,
				Constants.proxyTestQueueSize);
		// 代理线程池
		Set<String> keys = ProxyPool.proxyMap.keySet();
		for (String key : keys) {
			threadPoolExecutorMap.put(key,
					(SimpleThreadPoolExecutor) ThreadPoolUtil.createThreadPool(
							key + "-ProxyDownloadThreadExecutor",
							Constants.proxyTestCorePoolSize,
							Constants.proxyTestQueueSize));
		}

	}

	/**
	 * 抓取代理
	 */
	public void startCrawl() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Set<String> keys = ProxyPool.proxyMap.keySet();
					for (String url : keys) {
						threadPoolExecutorMap.get(url).execute(
								new ProxyPageTask(url, url, false, "UTF-8"));
					}

					try {
						int proxyPageDownloadInterval = Constants.proxyPageDownloadInterval;
						Thread.sleep(1000 * 60 * proxyPageDownloadInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public ThreadPoolExecutor getProxyTestThreadExecutor() {
		return proxyTestThreadExecutor;
	}

	public Map<String, SimpleThreadPoolExecutor> getThreadPoolExecutorMap() {
		return threadPoolExecutorMap;
	}

}
