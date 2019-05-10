package com.cd.chapter05.proxy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.cd.chapter05.proxy.entity.Proxy;
import com.cd.chapter05.proxy.site.goubanjia.GoubanjiaProxyListPageParser;

/**
 * 代理池
 * 
 * @author cd
 * @date 2019年5月8日 下午4:56:55
 * @desc
 */
public class ProxyPool {
	/**
	 * proxySet读写锁
	 */
	public final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public final static Set<Proxy> proxySet = new HashSet<Proxy>();

	/**
	 * 代理池延迟队列
	 */
	public final static DelayQueue<Proxy> proxyQueue = new DelayQueue();

	public final static Map<String, Class> proxyMap = new HashMap();

	static {
		proxyMap.put("http://www.goubanjia.com/",
				GoubanjiaProxyListPageParser.class);
	}
}
