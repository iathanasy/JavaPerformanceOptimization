package com.cd.chapter05.proxy.common.entity;

import com.cd.chapter05.proxy.entity.Proxy;

/**
 * 页面实体类
 * 
 * @author cd
 * @date 2019年5月8日 上午10:57:44
 * @desc
 */
public class Page {
	private String url;
	private int statusCode;// 响应状态码
	private String html;// response content
	private Proxy proxy; // 是否使用代理

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}
	
	

	@Override
	public String toString() {
		return "Page [url=" + url + ", statusCode=" + statusCode + ", html="
				+ html + ", proxy=" + proxy + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Page page = (Page) o;
		return url.equals(page.url);
	}

	@Override
	public int hashCode() {

		return url.hashCode();
	}
}
