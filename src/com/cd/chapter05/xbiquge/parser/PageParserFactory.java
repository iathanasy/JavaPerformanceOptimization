package com.cd.chapter05.xbiquge.parser;

import java.util.HashMap;
import java.util.Map;
import com.cd.chapter05.proxy.common.parser.Parser;


/**
 * 规则解析工厂 根据解析类 获取解析实例
 * @author cd
 * @date 2019年5月9日 下午3:21:10
 * @desc 
 */
public class PageParserFactory<T>{
	private Map<String, Parser<T>> map  = new HashMap();
	
	private volatile static PageParserFactory instance;
	
	//单例
	public static PageParserFactory getInstance(){
		if(instance == null){
			synchronized (PageParserFactory.class) {
				if(instance == null){
					instance = new PageParserFactory();
				}
			}
		}
		return instance;
	}
	
	public Parser<T> getProxyListPageParser(Class clazz){
        String parserName = clazz.getSimpleName();
        Parser<T> proxyListPageParser = null;
        if (map.containsKey(parserName)){
            return map.get(parserName);
        }
        else {
            try {
            	Parser<T> parser = (Parser<T>) clazz.newInstance();
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
