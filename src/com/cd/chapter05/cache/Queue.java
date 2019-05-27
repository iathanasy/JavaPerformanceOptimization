package com.cd.chapter05.cache;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.font.EAttribute;

/**
 * 缓存队列
 * @author cd
 * @date 2019年5月24日 下午2:28:17
 * @desc
 */
public class Queue {
	
	private final static Logger logger = LoggerFactory.getLogger(Queue.class);
	
	/**
	 * 已访问Url存储到磁盘
	 * Ehcache
	 */
	
	/**
     * 等待爬取的URL
     * 
     */
    public static BlockingQueue<String> unVisitedUrls = new LinkedBlockingQueue<String>();

    /**
     * 添加url到爬虫队列，假如队列中存在，就不添加
     * @param url
     */
    public static void addUnVisitedUrl(String url){
    	if(StringUtil.isBlank(url))
    		return;
    	if(!unVisitedUrls.contains(url)){
    		unVisitedUrls.add(url);
    		logger.info("[" + url + "] 添加到爬虫队列");
    	}
    }
    
}
