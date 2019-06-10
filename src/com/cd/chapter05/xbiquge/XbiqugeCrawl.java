package com.cd.chapter05.xbiquge;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cd.chapter05.proxy.ProxyHttpClient;
import com.cd.chapter05.proxy.ProxyPool;
import com.cd.chapter05.proxy.common.entity.Page;
import com.cd.chapter05.proxy.common.httpclient.AbstractHttpClient;
import com.cd.chapter05.proxy.common.utlis.Constants;
import com.cd.chapter05.proxy.common.utlis.SimpleThreadPoolExecutor;
import com.cd.chapter05.proxy.common.utlis.ThreadPoolUtil;
import com.cd.chapter05.xbiquge.entity.Chapter;
import com.cd.chapter05.xbiquge.task.XbiqugeChapterParser;
import com.cd.chapter05.xbiquge.task.XbiqugeChapterTask;
import com.sun.org.apache.bcel.internal.generic.SIPUSH;

/**
 * 新笔趣阁抓取
 * @author cd
 * @date 2019年5月9日 上午10:45:51
 * @desc
 */
public class XbiqugeCrawl extends AbstractHttpClient{
	private Logger logger = Logger.getLogger(XbiqugeCrawl.class);
	private static final String url = "http://www.xbiquge.la/xiaoshuodaquan/";
	public static final String baseUrl = "http://www.xbiquge.la";
	public final static int count = 1; //小说爬取数量
	public final static Map<String, Class> chapterMap = new ConcurrentHashMap<String, Class>(); //小说章节地址
	public final static Map<String, String> map = new ConcurrentHashMap<String, String>();
	public final static Queue<Chapter> waitSet = new LinkedBlockingQueue();//待爬取的地址
	public final static Set<String> okSet = new LinkedHashSet<String>();//已经爬取过的地址

	private volatile static XbiqugeCrawl instance;
	//单例
	public static XbiqugeCrawl getInstance(){
		if(instance == null){
			synchronized (XbiqugeCrawl.class) {
				if(instance == null){
					instance = new XbiqugeCrawl();
				}
			}
		}
		return instance;
	}
	
	private ThreadPoolExecutor downLoadThreadExecutor;
	
	/**
	 * 章节内容线程池
	 */
	private Map<String, SimpleThreadPoolExecutor> contextThreadExecutor = new HashMap<String, SimpleThreadPoolExecutor>();

	/**
	 * 章节线程池
	 */
	private Map<String, SimpleThreadPoolExecutor> chapterThreadExecutor = new HashMap();
	
	
	private XbiqugeCrawl(){

		/*new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					initAll();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();*/
		
		try {
			//等待2s
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initOne();
		initChapterThreadPool();
		//启动代理
		ProxyHttpClient.getInstance().startCrawl();
			
		
		
	}
	
	/**
	 * 初始化章节线程池
	 */
	private void initChapterThreadPool() {
		// 章节线程池
		Set<String> keys = chapterMap.keySet();
		for (String key : keys) {
			chapterThreadExecutor.put(key,
					(SimpleThreadPoolExecutor) ThreadPoolUtil.createThreadPool(
							key + "-ChapterThreadExecutor",
							Constants.proxyTestCorePoolSize,
							Constants.proxyTestQueueSize));
		}
		
		//下载任务线程池
		downLoadThreadExecutor = ThreadPoolUtil.createThreadPool("DownLoadThreadExecutor", Constants.proxyTestCorePoolSize,
				Constants.proxyTestQueueSize);

	}
	
	public void initOne(){
		String url = "http://www.xbiquge.la/26/26508/";
		map.put(url, "上门女婿");
		chapterMap.put(url, XbiqugeChapterParser.class);//
	}
	
	/**
	 * 抓取全部小说列表
	 * @throws IOException 
	 */
	public void initAll() throws IOException{
		Page page = getWebPage(url);
		Document doc = Jsoup.parse(page.getHtml());
		Elements ul = doc.select(".novellist ul li");
		for (Element li : ul) {
			String name = li.text();// 名称
			String url = li.select("a[href]").attr("href"); // url
			//System.out.println(name+ ":"+ url);
			chapterMap.put(url, XbiqugeChapterParser.class);//
			map.put(url, name);
		}
	}
	
	public void start(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Set<String> keys = chapterMap.keySet();
					for (String url : keys) {
						chapterThreadExecutor.get(url).execute(
								new XbiqugeChapterTask(map.get(url), url , true));
					}

					/*try {
						int proxyPageDownloadInterval = 60;
						Thread.sleep(1000 * 60 * proxyPageDownloadInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}*/
					
				}
			}
		}).start();
	}

	public Map<String, SimpleThreadPoolExecutor> getContextThreadExecutor() {
		return contextThreadExecutor;
	}

	public Map<String, SimpleThreadPoolExecutor> getChapterThreadExecutor() {
		return chapterThreadExecutor;
	}

	public ThreadPoolExecutor getDownLoadThreadExecutor() {
		return downLoadThreadExecutor;
	}
	
   
}
