package com.cd.chapter05.xbiquge.parser;

import com.cd.chapter05.proxy.common.parser.Parser;

/**
 * 解析内容页面
 * @author cd
 * @date 2019年5月9日 下午3:04:56
 * @desc
 */
public interface PageTParser<T> extends Parser{
	T parse(T t);
}
