package com.cd.chapter05.xbiquge.task;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cd.chapter05.HTMLSpirit;
import com.cd.chapter05.xbiquge.entity.Chapter;
import com.cd.chapter05.xbiquge.parser.PageParser;
import com.cd.chapter05.xbiquge.parser.PageTParser;
/**
 * 笔趣阁内容解析规则
 * @author cd
 * @date 2019年5月9日 上午11:38:25
 * @desc
 */
public class XbiqugeContextParser implements PageTParser<Chapter>{

	@Override
	public Chapter parse(Chapter t) {
		Document docContext = Jsoup.parse(t.getContext());
		Elements els = docContext.select("#content");
		String context = HTMLSpirit.htmlReplace(HTMLSpirit.delHTMLTag(els
				.html()));
		t.setContext(context);
		return t;
	}

}
