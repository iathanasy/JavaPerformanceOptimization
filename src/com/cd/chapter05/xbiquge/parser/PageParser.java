package com.cd.chapter05.xbiquge.parser;

import java.util.List;

import com.cd.chapter05.proxy.common.parser.Parser;

/**
 * 解析页面数据， 返回一个列表
 * @author cd
 * @date 2019年5月9日 上午11:37:30
 * @desc
 */
public interface PageParser<T> extends Parser{

	List<T> parse(String html);
}
