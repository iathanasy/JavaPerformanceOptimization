package com.cd.chapter05.taobao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cd.chapter05.proxy.common.utlis.HttpClientUtils;

public class TbkApi {

	static String appkey = "25412826";
	static String appsecret = "21ba45b2c5b5cdbc85777742a52fc18b";
	static String REST_URL = "http://gw.api.taobao.com/router/rest";
	
	public static void main(String[] args) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		String body = HttpClientUtils.postWebPage("http://gw.api.taobao.com/router/rest", params);
		System.out.println(body);
	}
}
