package com.cd.chapter05.util;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {

	 /**
     * 获取页面上所有超链接地址 （<a>标签的href值）
     *
     * @param html  页面文档
     * @return Set<String>
     */
    public static Set<String> findLinks(Document html) {

        if (html == null) {
            return null;
        }

        // element
        /**
         *
         * Elements resultSelect = html.select(tagName);	// 选择器方式
         * Element resultId = html.getElementById(tagName);	// 元素ID方式
         * Elements resultClass = html.getElementsByClass(tagName);	// ClassName方式
         * Elements resultTag = html.getElementsByTag(tagName);	// html标签方式 "body"
         *
         */
        Elements hrefElements = html.select("a[href]");

        // 抽取数据
        Set<String> links = new HashSet<String>();
        if (hrefElements!=null && hrefElements.size() > 0) {
            for (Element item : hrefElements) {
                String href = item.attr("abs:href");    // href、abs:href
                if (UrlUtil.isUrl(href)) {
                    links.add(href);
                }
            }
        }
        return links;
    }

    /**
     * 获取页面上所有图片地址 （<a>标签的href值）
     *
     * @param html
     * @return Set<String>
     */
    public static Set<String> findImages(Document html) {

        Elements imgs = html.getElementsByTag("img");

        Set<String> images = new HashSet<String>();
        if (imgs!=null && imgs.size() > 0) {
            for (Element element: imgs) {
                String imgSrc = element.attr("abs:src");
                images.add(imgSrc);
            }
        }

        return images;
    }
}
