package com.cd.chapter05.cnblog;

import java.io.IOException;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpStatus;


import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.cd.chapter05.proxy.common.entity.Page;
import com.cd.chapter05.proxy.common.httpclient.AbstractHttpClient;
import com.cd.chapter05.proxy.common.utlis.ThreadPoolUtil;

/**
 * 
 * @author cd
 * @date 2019年5月17日 上午10:29:53
 * @desc
 */
public class CnblogCrawl extends AbstractHttpClient{
	private final static String url="https://www.cnblogs.com";
	private static Queue<String> unVisitedUrl = new LinkedBlockingQueue<String>();
	private static Set<String> visitedUrl = new HashSet<String>();
	private static ThreadPoolExecutor executor = ThreadPoolUtil.createThreadPool("pool", 10, 100);
	
	private volatile static CnblogCrawl instance;
	
	public static CnblogCrawl getInstance(){
		if(instance == null){
			synchronized(CnblogCrawl.class){
				if(instance == null){
					instance = new CnblogCrawl();
				}
			}
		}
		return instance;
	}
	
	public static void main(String[] args){
		try {
			CnblogCrawl.getInstance().getXpath();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getXpath() throws IOException, XPathExpressionException, ParserConfigurationException{
		Page page = getWebPage(url);
		if(HttpStatus.SC_OK == page.getStatusCode()){

			HtmlCleaner hcCleaner = new HtmlCleaner();
			TagNode tagNode = hcCleaner.clean(page.getHtml());
			Document dom = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList result = (NodeList) xPath.evaluate("//div[@class='post_item_body']/h3", dom, XPathConstants.NODESET);
			
			for (int i = 0; i < result.getLength(); i++) {
				Node node = result.item(i);
				System.out.println(node.getTextContent());
			}
			
		}else{
			System.err.println(url + "-返回状态非200");
		}
	} 
}
