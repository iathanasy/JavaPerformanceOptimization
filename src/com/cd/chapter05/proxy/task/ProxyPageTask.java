package com.cd.chapter05.proxy.task;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;

import com.cd.chapter05.proxy.ProxyHttpClient;
import com.cd.chapter05.proxy.common.entity.Page;
import com.cd.chapter05.proxy.common.utlis.Constants;
import com.cd.chapter05.proxy.common.utlis.HttpClientUtils;
import com.cd.chapter05.proxy.entity.Proxy;
import com.cd.chapter05.proxy.parser.ProxyListPageParser;
import com.cd.chapter05.proxy.site.ProxyListPageParserFactory;

import static com.cd.chapter05.proxy.ProxyPool.proxyQueue;
import static com.cd.chapter05.proxy.ProxyPool.proxyMap;
import static com.cd.chapter05.proxy.ProxyPool.proxySet;

/**
 * 下载代理网页并解析 若下载失败，通过代理去下载代理网页
 * 
 * @author cd
 * @date 2019年5月8日 下午4:54:20
 * @desc
 */
public class ProxyPageTask implements Runnable {

	private static Logger logger = Logger.getLogger(ProxyPageTask.class);
	protected String url;
	private boolean proxyFlag;// 是否通过代理下载
	private Proxy currentProxy;// 当前线程使用的代理
	private String pageCharset = "utf-8";
	private String domain;
	protected static ProxyHttpClient proxyHttpClient = ProxyHttpClient
			.getInstance();

	private ProxyPageTask() {

	}

	public ProxyPageTask(String domain, String url, boolean proxyFlag) {
		this.domain = domain;
		this.url = url;
		this.proxyFlag = proxyFlag;
	}

	public ProxyPageTask(String domain, String url, boolean proxyFlag,
			String pageCharset) {
		this.domain = domain;
		this.url = url;
		this.proxyFlag = proxyFlag;
		this.pageCharset = pageCharset;
	}

	@Override
	public void run() {
		if (null != url) {
			int size = proxyQueue.size();
			int count = Constants.proxyNumberThreshold;
			if (size >= count) {
				logger.debug(String.format("当前可用代理 %s 个,暂时不下载代理页面", size));
				return;
			}
			long requestStartTime = System.currentTimeMillis();
			Page page = null;
			HttpGet tempRequest = null;

			try {
				if (proxyFlag && size > 0) {// 是否使用代理
					tempRequest = new HttpGet(url);
					currentProxy = proxyQueue.take();
					// 使用代理
					HttpHost proxy = new HttpHost(currentProxy.getIp(),
							currentProxy.getPort());
					tempRequest.setConfig(HttpClientUtils
							.getRequestConfigBuilder().setProxy(proxy).build());
					page = proxyHttpClient.getWebPage(tempRequest, pageCharset);
				} else {// 首次使用本机ip爬取
					page = proxyHttpClient.getWebPage(url, pageCharset);
				}

				int status = page.getStatusCode();
				long requestEndTime = System.currentTimeMillis();
				String logStr = Thread.currentThread().getName() + " "
						+ getProxyStr(currentProxy) + "  executing request "
						+ page.getUrl() + " response statusCode:" + status
						+ "  request cost time:"
						+ (requestEndTime - requestStartTime) + "ms";

				if (status == HttpStatus.SC_OK) {
					logger.debug(logStr);
					handle(page);
				} else {
					logger.debug(logStr);
					Thread.sleep(100);
					retry();
				}

			} catch (InterruptedException e) {
				logger.error("InterruptedException", e);
			} catch (IOException e) {
				retry();
			} finally {
				if (currentProxy != null) {
					currentProxy.setTimeInterval(Constants.TIMEOUT);
					proxyQueue.add(currentProxy);
				}
				if (tempRequest != null) {
					tempRequest.releaseConnection();
				}
			}
		}
	}

	/**
	 * retry
	 */
	public void retry() {
		proxyHttpClient.getThreadPoolExecutorMap().get(domain)
				.execute(new ProxyPageTask(domain, url, true, pageCharset));
	}

	/**
	 * 启动测试任务
	 * 
	 * @param page
	 */
	public void handle(Page page) {
		ProxyListPageParser parser = ProxyListPageParserFactory
				.getProxyListPageParser(proxyMap.get(url));
		List<Proxy> proxyList = parser.parse(page.getHtml());
		for (Proxy p : proxyList) {
			if (!proxySet.contains(p.getProxyStr())) {
				proxyHttpClient.getProxyTestThreadExecutor().execute(
						new ProxyTestTask(p, proxyHttpClient,
								Constants.proxyTestHttpUrl));
			}
		}
	}

	private String getProxyStr(Proxy proxy) {
		if (proxy == null) {
			return "";
		}
		return proxy.getIp() + ":" + proxy.getPort();
	}

}
