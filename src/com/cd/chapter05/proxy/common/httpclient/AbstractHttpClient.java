package com.cd.chapter05.proxy.common.httpclient;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.cd.chapter05.proxy.common.entity.Page;
import com.cd.chapter05.proxy.common.utlis.HttpClientUtils;

/**
 * httpClient抽象类
 * 
 * @author cd
 * @date 2019年5月8日 上午10:00:39
 * @desc
 */
public abstract class AbstractHttpClient {

	private Logger logger = Logger.getLogger(AbstractHttpClient.class);

	public InputStream getWebPageInputStream(String url) {
		try {
			CloseableHttpResponse response = HttpClientUtils.getResponse(url);
			return response.getEntity().getContent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Page getWebPage(String url) throws IOException {
		return getWebPage(url, "UTF-8");
	}

	/**
	 * 返回实体
	 * 
	 * @param url
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public Page getWebPage(String url, String charset) throws IOException {
		Page page = new Page();
		CloseableHttpResponse response = null;
		response = HttpClientUtils.getResponse(url);

		page.setStatusCode(response.getStatusLine().getStatusCode());
		page.setUrl(url);

		try {
			if (page.getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				page.setHtml(EntityUtils.toString(entity, charset));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return page;
	}

	public Page getWebPage(HttpRequestBase request) throws IOException {
		return getWebPage(request, "utf-8");
	}

	public Page getWebPage(HttpRequestBase request, String charset)
			throws IOException {
		CloseableHttpResponse response = null;
		response = HttpClientUtils.getResponse(request);
		Page page = new Page();
		page.setStatusCode(response.getStatusLine().getStatusCode());
		page.setHtml(EntityUtils.toString(response.getEntity(), charset));
		page.setUrl(request.getURI().toString());
		return page;
	}

}
