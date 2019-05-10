package com.cd.chapter05.proxy.parser;

import java.util.List;
import com.cd.chapter05.proxy.common.parser.Parser;
import com.cd.chapter05.proxy.entity.Proxy;

/**
 * 代理列表页面解析
 * 
 * @author cd
 * @date 2019年5月8日 下午4:10:23
 * @desc
 */
public interface ProxyListPageParser extends Parser {

	/**
	 * 是否只要匿名代理
	 */
	static final boolean anonymousFlag = false;

	List<Proxy> parse(String content);

}
