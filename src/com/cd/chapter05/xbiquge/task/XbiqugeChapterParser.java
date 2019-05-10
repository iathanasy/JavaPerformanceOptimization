package com.cd.chapter05.xbiquge.task;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cd.chapter05.HTMLSpirit;
import com.cd.chapter05.xbiquge.XbiqugeCrawl;
import com.cd.chapter05.xbiquge.entity.Chapter;
import com.cd.chapter05.xbiquge.parser.PageParser;
/**
 * 笔趣阁章节解析规则
 * @author cd
 * @date 2019年5月9日 上午11:38:25
 * @desc
 */
public class XbiqugeChapterParser implements PageParser<Chapter>{

	@Override
	public List<Chapter> parse(String html) {
		Document doc = Jsoup.parse(html);
		Elements chapterList = doc.select("#list dd");// 章节列表
		List<Chapter> list = new ArrayList<Chapter>();
		for (Element el : chapterList) {
			String title = el.text();
			String url = el.select("a[href]").attr("href"); // url
			//System.out.println(title + ":" + url);
			Chapter chapter = new Chapter(title, XbiqugeCrawl.baseUrl+ url);
			list.add(chapter);
		}
		
		/*Document docContext = Jsoup.parse(html);
		Elements els = docContext.select("#content");
		String context = HTMLSpirit.htmlReplace(HTMLSpirit.delHTMLTag(els
				.html()));// 内容
*/		
		return list;
	}

}
