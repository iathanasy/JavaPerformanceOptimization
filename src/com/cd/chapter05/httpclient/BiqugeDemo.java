package com.cd.chapter05.httpclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.cd.chapter05.HTMLSpirit;

/**
 * 爬取笔趣阁小说名
 * @author cd
 * @date 2019年5月6日 下午4:23:36
 * @desc
 */
public class BiqugeDemo {
	private static final HashSet<Entity> set = new HashSet<Entity>();//待爬取的url
	private static final HashSet<String> okUrl = new LinkedHashSet<String>();//已爬取过的url
	public static final HashSet<String> setIp = new HashSet<String>();//代理Ip
	private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36";
	private static final String uri = "http://www.xbiquge.la/xiaoshuodaquan/";
	private static final String baseUri = "http://www.xbiquge.la";
	private static String next = uri; //下次爬取的url
	private static final int connectTimeout = 10000;
	private static final int socketTimeout = 10000;
	private static final int connectionRequestTimeout = 15000;
	private static final String basePath = "D://novel/";
	/*private static final CloseableHttpClient httpClient = HttpClients.custom()
			.setUserAgent(userAgent).build();*/
	
	
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				crawlingGoubanjiaIp();
				System.out.println("代理");
			}
		}, 1000, 1000 * 40);//1s后执行 后面4分钟一次
		
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				init("凡人修仙传", "http://www.xbiquge.la/5/5395/");
				//analysis();
				//System.out.println(getContext("/1/1695/1272155.html"));
				
			}
		}, 15000);

	}
	
	/**
	 * 获取代理IP
	 */
	private static void crawlingGoubanjiaIp(){
		String url = "http://www.goubanjia.com/";
		CloseableHttpClient httpClient = HttpClients.custom()
				.setUserAgent(userAgent).build();
		HttpGet request = new HttpGet(url);
		CloseableHttpResponse response;
		try {
			response = httpClient.execute(request);
			String responseStr = EntityUtils.toString(response.getEntity(),
					"UTF-8");
			//解析数据
			Document document = Jsoup.parse(responseStr);
			setPort(document);
			//获取class="table" 的所有子节点tr  <table class="table table-hover"> 
			Elements elements = document.select("table.table tr");
			setIp.isEmpty();
			for (Element element : elements) {
				//获取td节点
				Element td = element.select("td").first();
				if (null == td)
					continue;

				/**
				 * 查找所有style属性包含none字符串的标签（页面上未显示的标签），并移除
				 * 包括以下两种
				 * style=display: none;
				 * style=display:none;
				 */
				Elements ipTd = td.select("[style*=none;]");
				for (Element none : ipTd) {
					none.remove();
				}
				//移除空格
				String ipPort = td.text().replaceAll(" ", "");
				//打印
				System.out.println("goubanjia---> "+ ipPort);
				String ip = ipPort.split(":")[0];
				Integer port = Integer.parseInt(ipPort.split(":")[1]);
				try {
					CloseableHttpResponse body = getResponse(url, ip, port);
					if(body != null) setIp.add(ipPort);
				} catch (IOException e) {
					System.err.println("代理异常："+ e.getMessage());
					continue;
				}
				if (response != null)
					setIp.add(ipPort);

			}
			
		} catch (ClientProtocolException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		
	}
	
	/**
	 * 测试/设置 代理IP
	 * @return
	 * @throws IOException 
	 */
	public static CloseableHttpResponse getResponse(String uri, String ip, Integer port) throws IOException{
		CloseableHttpClient httpClient = HttpClients.custom().setUserAgent(userAgent).build();
		HttpGet request = new HttpGet(uri);
		
		//设置代理IP，设置连接超时时间 、 设置 请求读取数据的超时时间 、 设置从connect Manager获取Connection超时时间、
		HttpHost proxy = new HttpHost(ip, port);
		RequestConfig config = RequestConfig.custom()
				.setProxy(proxy)
				.setConnectTimeout(connectTimeout)
				.setSocketTimeout(socketTimeout)
				.setConnectionRequestTimeout(connectionRequestTimeout)
				.build();
		request.setConfig(config);
		
		//请求
		CloseableHttpResponse response = httpClient.execute(request);
		return response;
	}
	
	/**
	 * 获取网站内容
	 * @return
	 */
	private static String getBody(String uri){
		/*if(okUrl.contains(uri)) 
			return null;*/
		CloseableHttpClient httpClient = HttpClients.custom()
				.setUserAgent(userAgent).build();
		HttpGet request = new HttpGet(uri);
		String body = null;
		try {
			Iterator<String> iter = setIp.iterator();
			List l = new ArrayList<String>();
			while(iter.hasNext()){
				 l.add(iter.next());
			}
			Random r = new Random();
			int i = r.nextInt(l.size());
			String ipPort = (String) l.get(i);
			String ip = ipPort.split(":")[0];
			Integer port = Integer.parseInt(ipPort.split(":")[1]);
			//设置代理IP，设置连接超时时间 、 设置 请求读取数据的超时时间 、 设置从connect Manager获取Connection超时时间、
			HttpHost proxy = new HttpHost(ip, port);
			RequestConfig config = RequestConfig.custom()
					.setProxy(proxy)
					.setConnectTimeout(connectTimeout)
					.setSocketTimeout(socketTimeout)
					.setConnectionRequestTimeout(connectionRequestTimeout)
					.build();
			request.setConfig(config);
			CloseableHttpResponse response = httpClient.execute(request);
			body = EntityUtils.toString(response.getEntity(), Charset.defaultCharset());
			
		
		} catch (Exception e) {
			System.err.println(e.getMessage());
			next = uri;
		}
		return body;
	}
	
	/**
	 * 解析数据 只解析小说名
	 */
	private static void analysis(){
		String responseStr = getBody(uri);
		Document doc = Jsoup.parse(responseStr);
		Elements ul = doc.select(".novellist ul li");
		okUrl.add(uri);
		for (Element li : ul) {
			 String name = li.text() ;//名称
			 String url = li.select("a[href]").attr("href"); //url
			 Entity entity = new Entity(name, url, false);
			 
			 System.out.println(name+ ":" + url);
			 //存储到磁盘
			 outFile(basePath+"/全部小说", name+ ":" + url+ "\n");//创建文件夹
			 /*List<Body> list = chapterList(entity);
			 okUrl.add(url);
			 entity.setMark(true);
			 entity.setBody(list);
			 set.add(entity);*/
		}
	}
	
	/**
	 * 根据小说名称获取
	 * @param name 小说名
	 * @param url url
	 */
	private static void init(String name, String url){
	 Entity entity = new Entity(name, url, false);
	 System.out.println(name+ ":" + url);
	 List<Body> list = chapterList(entity);
	 okUrl.add(url);
	 entity.setMark(true);
	 entity.setBody(list);
	 set.add(entity);
		
	}
	
	/**
	 * 只解析章节
	 * @param entity
	 */
	public static List<Body> chapterList(Entity entity){
		String homeBody = getBody(entity.getUrl());
		 List<Body> list = new ArrayList<Body>();
		 if(StringUtil.isBlank(homeBody)) return list;
		 Document chapters = Jsoup.parse(homeBody);
		 Elements chapterList = chapters.select("#list dd");//章节列表
		 String path = mkdirFile(entity.getName());//创建文件夹
		 for (Element chapter : chapterList) {
			 String cha = chapter.text();
			 String url1 = chapter.select("a[href]").attr("href"); //url
			 System.out.println(cha+ ":" + url1);
			 Body body = new Body(cha, url1, false);
			 String context = getContext(url1);
			 cha = path + "/"+ cha;
			 outFile(cha, context);//创建文件
			 body.setMark(true);
			 body.setContext(context);
			 okUrl.add(url1);
			 list.add(body);
		}
		 return list;
	}
	
	/**
	 * 只获取内容
	 * @param url
	 * @return
	 */
	public static String getContext(String url){
		 String contextHtml = getBody(baseUri+url); //内容
		 if(StringUtil.isBlank(contextHtml)) return url;
		 Document docContext = Jsoup.parse(contextHtml);
		 Elements els = docContext.select("#content");
		 String context = HTMLSpirit.htmlReplace(HTMLSpirit.delHTMLTag(els.html()));//内容
		 return context;
	}
	
	/**
	 *  创建.txt文件
	 * @param fileName 文件名
	 */
	public static String mkdirFile(String fileDir){
		//创建文件夹
		File file = new File(basePath + fileDir);
		if(!file.exists()){
			file.mkdirs();//创建文件夹
		}
		return file.getPath();
	}
	/**
	 * 存储到磁盘文件
	 * @param file 文件名
	 * @param context 内容
	 */
	public static void outFile(String file, String context){
		//使用ByteBuffer实现写入数据
		try {
			FileOutputStream fout = new FileOutputStream(new File(file + ".txt"), true);
			fout.write(context.getBytes());
			fout.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
     * js代码port还原
     * @param doc
     */
    private static void setPort(Document doc){
        for (Element e : doc.select(".port")){//$('.port').each(function() {
            String a = e.text();//var a = $(this).html();
            if(a.indexOf("*") != -0x1){//if (a.indexOf('*') != -0x1) {
                return;
            }
            String b = e.attr("class");//var b = $(this).attr('class');
            b = b.split(" ")[0x1];//b = (b.split(" "))[0x1];
            String[] c = b.split("");//var c = b.split("");
            int d = b.length();//var d = c.length;
            StringBuilder f = new StringBuilder();//var f = [];
            for(int g = 0x0; g < d; g++){//for (var g = 0x0; g < d; g++) {
                f.append("ABCDEFGHIZ".indexOf(c[g]));//f.push('ABCDEFGHIZ'.indexOf(c[g]))
            }
            e.text(String.valueOf(Integer.valueOf(f.toString()) >> 0x3));//$(this).html(window.parseInt(f.join('')) >> 0x3)
        }
    }
    
    /**
     * 原js地址：http://www.goubanjia.com/theme/goubanjia/javascript/pde.js?v=1.0
     * port js解码后
     * 解密网站： https://tool.lu/js
     * 
     * var _$ = ['.port', "each", "html", "indexOf", '*', "attr", 'class', "split", " ", "", "length", "push", 'ABCDEFGHIZ', "parseInt", "join", ''];
		$(function() {
			$(_$[0])[_$[1]](function() {
				var a = $(this)[_$[2]]();
				if (a[_$[3]](_$[4]) != -0x1) {
					return
				};
				var b = $(this)[_$[5]](_$[6]);
				try {
					b = (b[_$[7]](_$[8]))[0x1];
					var c = b[_$[7]](_$[9]);
					var d = c[_$[10]];
					var f = [];
					for (var g = 0x0; g < d; g++) {
						f[_$[11]](_$[12][_$[3]](c[g]))
					};
					$(this)[_$[2]](window[_$[13]](f[_$[14]](_$[15])) >> 0x3)
				} catch (e) {}
			})
		})
     */
}

/**
 * 实体类
 * @author cd
 * @date 2019年5月6日 下午4:52:34
 * @desc
 */
class Entity{
	String name;
	String url;
	boolean mark;//是否已爬取
	List<Body> body;
	public Entity(){}
	
	public Entity(String name, String url, boolean mark) {
		super();
		this.name = name;
		this.url = url;
		this.mark = mark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isMark() {
		return mark;
	}
	public void setMark(boolean mark) {
		this.mark = mark;
	}

	public List<Body> getBody() {
		return body;
	}

	public void setBody(List<Body> body) {
		this.body = body;
	}
	
	
}

class Body{
	String chapter;
	String url;
	String context;
	boolean mark;
	
	
	public Body() {
		super();
	}
	public Body(String chapter, String url, boolean mark) {
		super();
		this.chapter = chapter;
		this.url = url;
		this.mark = mark;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public boolean isMark() {
		return mark;
	}
	public void setMark(boolean mark) {
		this.mark = mark;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}

class Proxy{
	String ip;
	Integer port;
	
	public Proxy() {
		super();
	}
	public Proxy(String ip, Integer port) {
		super();
		this.ip = ip;
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	
}