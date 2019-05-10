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
import com.cd.chapter05.xbiquge.parser.PageTParser;

public class XbiqugeContextTask implements Runnable{
	
	private static Logger logger = Logger.getLogger(XbiqugeContextTask.class);
	protected String url;
	private Chapter chapter;
	private boolean proxyFlag;// 是否通过代理下载
	private String pageCharset = "utf-8";
	protected static XbiqugeCrawl xbiqugeCrawl = XbiqugeCrawl.getInstance();

	private XbiqugeContextTask() {

	}
	
	public XbiqugeContextTask(String url, boolean proxyFlag, Chapter chapter) {
		super();
		this.url = url;
		this.proxyFlag = proxyFlag;
		this.chapter = chapter;
	}

	public XbiqugeContextTask(String url, boolean proxyFlag,
			String pageCharset , Chapter chapter) {
		super();
		this.url = url;
		this.proxyFlag = proxyFlag;
		this.pageCharset = pageCharset;
		this.chapter = chapter;
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
		xbiqugeCrawl.getContextThreadExecutor().get(url).execute(
				new XbiqugeContextTask(url , true, chapter));
	}

	/**
	 * 解析数据处理
	 * 
	 * @param page
	 */
	public void handle(Page page) {
		//获取解析数据
		PageTParser<Chapter> parser = (PageTParser<Chapter>) PageParserFactory.getInstance()
				.getProxyListPageParser(xbiqugeCrawl.chapterMap.get(url));
		chapter.setContext(page.getHtml()); //网页内容赋值
		Chapter chapters = parser.parse(chapter);
		
		//启动下载任务线程池
		xbiqugeCrawl.getDownLoadThreadExecutor().execute(new XbiqugeDownLoadTask(chapter));
		
		//logger.info(chapters.toString());
	}

	
	private String getProxyStr(HttpHost proxy) {
		if (proxy == null) {
			return "";
		}
		return proxy.getAddress() + ":" + proxy.getPort();
	}
}
