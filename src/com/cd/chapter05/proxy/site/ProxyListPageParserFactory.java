package com.cd.chapter05.proxy.site;

import java.util.HashMap;
import java.util.Map;

import com.cd.chapter05.proxy.parser.ProxyListPageParser;

/**
 * 通过url获取
 * 
 * @author cd
 * @date 2019年5月8日 下午5:56:15
 * @desc
 */
public class ProxyListPageParserFactory {

	private static Map<String, ProxyListPageParser> map = new HashMap();

	public static ProxyListPageParser getProxyListPageParser(Class clazz) {
		String parserName = clazz.getSimpleName();
		ProxyListPageParser proxyListPageParser = null;
		if (map.containsKey(parserName)) {
			return map.get(parserName);
		} else {
			try {
				ProxyListPageParser parser = (ProxyListPageParser) clazz
						.newInstance();
				parserName = clazz.getSimpleName();
				map.put(parserName, parser);
				return parser;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
