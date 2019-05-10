package com.cd.chapter05.httpclient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/*
 * 获取 evanyou(尤雨溪) 关注的所有用户信息
 */
public class ZhiHuDemo {

	public static void main(String[] args) throws IOException {
		String url = "https://www.zhihu.com/api/v4/members/evanyou/followees?"
				+ "include=data%5B*%5D.answer_count%2Carticles_count%2Cgender%2C"
				+ "follower_count%2Cis_followed%2Cis_following%2Cbadge%5B%3F(type%3Dbest_answerer)%5D.topics&"
				+ "offset=20&limit=20";
		// 创建http客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建http request (GET)
		HttpGet request = new HttpGet(url);
		// 设置http header
		// request.addHeader("authorization",
		// "oauth c3cef7c66a1843f8b3a9e6a1e3160e20");
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(request);
		// 打印
		String responseStr = EntityUtils
				.toString(response.getEntity(), "UTF-8");
		System.out.println(responseStr);

		// 解析数据
		String nextPageUrl = getNextPageUrl(responseStr);
		boolean isEnd = getIsEnd(responseStr);

		while (!isEnd && nextPageUrl != null) {
			// 创建http request(GET)
			request = new HttpGet(nextPageUrl);

			// 设置http header
			// request.addHeader("authorization",
			// "oauth c3cef7c66a1843f8b3a9e6a1e3160e20");
			response = httpClient.execute(request);
			// 打印
			responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println(responseStr);
			nextPageUrl = getNextPageUrl(responseStr);
			isEnd = getIsEnd(responseStr);
		}

	}

	/**
	 * 获取next url
	 * 
	 * @param responseStr
	 * @return url
	 */
	private static String getNextPageUrl(String responseStr) {
		JSONObject jsonObject = (JSONObject) JSON.parse(responseStr);
		jsonObject = (JSONObject) jsonObject.get("paging");
		return jsonObject.getString("next");
	}

	/**
	 * 获取is_end
	 * 
	 * @param responseStr
	 * @return
	 */
	private static boolean getIsEnd(String responseStr) {
		JSONObject jsonObject = (JSONObject) JSON.parse(responseStr);
		jsonObject = (JSONObject) jsonObject.get("paging");
		return jsonObject.getBoolean("is_end");
	}
}
