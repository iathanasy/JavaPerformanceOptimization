package com.cd.chapter05.xbiquge.task;

import static com.cd.chapter05.proxy.ProxyPool.proxyMap;
import static com.cd.chapter05.proxy.ProxyPool.proxyQueue;
import static com.cd.chapter05.proxy.ProxyPool.proxySet;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;

import com.cd.chapter05.proxy.common.entity.Page;
import com.cd.chapter05.proxy.common.utlis.Constants;
import com.cd.chapter05.proxy.common.utlis.HttpClientUtils;
import com.cd.chapter05.proxy.common.utlis.SimpleThreadPoolExecutor;
import com.cd.chapter05.proxy.common.utlis.ThreadPoolUtil;
import com.cd.chapter05.proxy.entity.Proxy;
import com.cd.chapter05.xbiquge.XbiqugeCrawl;
import com.cd.chapter05.xbiquge.entity.Chapter;
import com.cd.chapter05.xbiquge.parser.PageParser;
import com.cd.chapter05.xbiquge.parser.PageParserFactory;

public class XbiqugeChapterTask implements Runnable{
	
	private static Logger logger = Logger.getLogger(XbiqugeChapterTask.class);
	private String name;
	protected String url;
	private boolean proxyFlag;// 是否通过代理下载
	private String pageCharset = "utf-8";
	protected static XbiqugeCrawl xbiqugeCrawl = XbiqugeCrawl.getInstance();

	private XbiqugeChapterTask() {

	}
	
	public XbiqugeChapterTask(String name,String url, boolean proxyFlag) {
		super();
		this.name = name;
		this.url = url;
		this.proxyFlag = proxyFlag;
	}

	public XbiqugeChapterTask(String url, boolean proxyFlag,
			String pageCharset) {
		super();
		this.url = url;
		this.proxyFlag = proxyFlag;
		this.pageCharset = pageCharset;
	}


	@Override
	public void run() {
		int size = proxyQueue.size();
		Page page = null;
		HttpGet request = null;
		long requestStartTime = System.currentTimeMillis();
		
		if(proxyFlag && size > 0){//使用代理
			HttpHost proxy = new HttpHost(proxyQueue.poll().getIp(), proxyQueue.poll().getPort());
			request = new HttpGet(url);
			request.setConfig(HttpClientUtils.getRequestConfigBuilder().setProxy(proxy).build());
		}else{
			request = new HttpGet(url);
		}
		
		try {
			page = xbiqugeCrawl.getWebPage(request);
			int status = page.getStatusCode();
			long requestEndTime = System.currentTimeMillis();
			String logStr = Thread.currentThread().getName() + " "
					+ getProxyStr(request.getConfig().getProxy()) + "  executing request "
					+ page.getUrl() + " response statusCode:" + status
					+ "  request cost time:"
					+ (requestEndTime - requestStartTime) + "ms";
			
			
			if (status == HttpStatus.SC_OK) {
				logger.debug(logStr);
				handle(page);
				xbiqugeCrawl.okSet.add(url);
			} else {
				logger.debug(logStr);
				Thread.sleep(100);
				retry();
			}
		}catch (InterruptedException e) {
			logger.error("InterruptedException", e);	
		} catch (IOException e) {
			retry();
			logger.debug("IOException:", e);
		} finally {
			if (request != null) {
				request.releaseConnection();
			}
		}
	}

	
	/**
	 * 重试 retry
	 */
	public void retry() {
		xbiqugeCrawl.getChapterThreadExecutor().get(url).execute(
				new XbiqugeChapterTask(name, url , true));
	}

	/**
	 * 解析数据处理
	 * 
	 * @param page
	 */
	public void handle(Page page) {
		//获取解析数据
		PageParser<Chapter> parser = (PageParser<Chapter>) PageParserFactory.getInstance()
				.getProxyListPageParser(xbiqugeCrawl.chapterMap.get(url));
		List<Chapter> chapterList = parser.parse(page.getHtml());
		logger.info(name + "正在下载...");
		//初始化内容线程池
		initContextThreadPool(chapterList);
		for (Chapter chapter : chapterList) {
			if(!xbiqugeCrawl.okSet.equals(chapter.getUrl())){//未爬取的
				xbiqugeCrawl.getContextThreadExecutor().get(chapter.getUrl())
				.execute(new XbiqugeContextTask(chapter.getUrl(), true, chapter));//启动内容线程池
			}
		}
	}
	
	/**
	 * 初始化内容线程池
	 */
	private void initContextThreadPool(List<Chapter> list){
		
		for (Chapter chapter : list) {
			String key = chapter.getUrl();
			xbiqugeCrawl.chapterMap.put(key, XbiqugeContextParser.class);//初始化解析器
			chapter.setName(name);
			xbiqugeCrawl.getContextThreadExecutor().put(key ,
					(SimpleThreadPoolExecutor) ThreadPoolUtil.createThreadPool(
							key + "-ContextThreadExecutor",
							Constants.proxyTestCorePoolSize,
							Constants.proxyTestQueueSize));
		}
		
	}
	
	private String getProxyStr(HttpHost proxy) {
		if (proxy == null) {
			return "";
		}
		return proxy.getAddress() + ":" + proxy.getPort();
	}
}
