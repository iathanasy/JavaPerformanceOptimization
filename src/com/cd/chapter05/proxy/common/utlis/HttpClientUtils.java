package com.cd.chapter05.proxy.common.utlis;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;

import org.apache.http.Consts;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * HttpClient工具类
 * 
 * @author cd
 * @date 2019年5月7日 下午5:54:35
 * @desc
 */
public class HttpClientUtils {

	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	private static CookieStore cookieStore = new BasicCookieStore(); // cookie存储
	private static CloseableHttpClient httpClient;
	private final static String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36";
	private static HttpHost proxy;
	private static RequestConfig requestConfig;

	static {
		init();
	}

	/**
	 * 初始化配置
	 */
	private static void init() {
		try {
			// ssl证书相关
			SSLContext sslContext = SSLContexts
					.custom()
					.loadTrustMaterial(
							KeyStore.getInstance(KeyStore.getDefaultType()),
							new TrustStrategy() {

								@Override
								public boolean isTrusted(
										X509Certificate[] chain, String authType)
										throws CertificateException {

									return true;
								}
							}).build();

			SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(
					sslContext);
			// 注册登记证书
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
					.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", sslFactory).build();

			// 创建HTTP的连接池管理对象
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(
					socketFactoryRegistry);

			// socket设置
			SocketConfig socketConfig = SocketConfig.custom()
					.setSoTimeout(Constants.TIMEOUT).setTcpNoDelay(true)
					.build();

			connManager.setDefaultSocketConfig(socketConfig);

			// 连接设置
			ConnectionConfig connectionConfig = ConnectionConfig.custom()
					.setMalformedInputAction(CodingErrorAction.IGNORE)
					.setUnmappableInputAction(CodingErrorAction.IGNORE)
					.setCharset(Consts.UTF_8).build();

			connManager.setDefaultConnectionConfig(connectionConfig);
			connManager.setMaxTotal(500);// 最大连接数
			connManager.setDefaultMaxPerRoute(300);// 路由的默认连接数

			/**
			 * 请求重试处理
			 */
			HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {

				@Override
				public boolean retryRequest(IOException exception,
						int executionCount, HttpContext context) {
					if (executionCount > 2) {
						// 大于2次就放弃
						return false;
					}
					if (exception instanceof InterruptedIOException) {
						// 超时
						return true;
					}
					if (exception instanceof UnknownHostException) {
						// 目标服务器不可达
						return true;
					}
					if (exception instanceof ConnectTimeoutException) {
						// 连接被拒绝
						return true;
					}
					if (exception instanceof SSLException) {
						// ssl握手异常
						return true;
					}
					HttpRequest request = HttpClientContext.adapt(context)
							.getRequest();
					if (request instanceof HttpEntityEnclosingRequest) {
						return true;
					}

					return false;
				}
			};

			HttpClientBuilder httpClientBuilder = HttpClients.custom()
					.setConnectionManager(connManager)
					.setRetryHandler(retryHandler)
					.setRedirectStrategy(new LaxRedirectStrategy())
					.setDefaultCookieStore(new BasicCookieStore())
					.setUserAgent(userAgent);

			if (proxy != null) {
				httpClientBuilder.setRoutePlanner(
						new DefaultProxyRoutePlanner(proxy)).build();
			}

			httpClient = httpClientBuilder.build();

			requestConfig = RequestConfig
					.custom()
					.setSocketTimeout(Constants.TIMEOUT)
					// 超时设置
					.setConnectTimeout(Constants.TIMEOUT)
					.setConnectionRequestTimeout(Constants.TIMEOUT)
					.setCookieSpec(CookieSpecs.STANDARD).build();

		} catch (Exception e) {
			logger.error("Exception:", e);
		}
	}

	/**
	 * 获取网页内容
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getWebPage(String url) throws IOException {
		HttpGet request = new HttpGet(url);
		return getWebPage(request, "utf-8");
	}

	/**
	 * post请求
	 * 
	 * @param postUrl
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String postWebPage(String postUrl, Map<String, String> params)
			throws IOException {
		HttpPost post = new HttpPost(postUrl);
		setHttpPostParams(post, params);// 设置参数
		return getWebPage(post, "utf-8");
	}

	public static String getWebPage(HttpRequestBase request) throws IOException {
		return getWebPage(request, "utf-8");
	}

	/**
	 * 获取页面内容
	 * 
	 * @param request
	 *            请求
	 * @param encoding
	 *            字符编码
	 * @return 网页内容
	 * @throws IOException
	 */
	public static String getWebPage(HttpRequestBase request, String encoding)
			throws IOException {
		CloseableHttpResponse response = null;
		response = getResponse(request);
		logger.info("status---" + response.getStatusLine().getStatusCode());
		String context = EntityUtils.toString(response.getEntity(), encoding);
		request.releaseConnection();// 释放连接
		return context;
	}

	/**
	 * 返回响应请求
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static CloseableHttpResponse getResponse(HttpRequestBase request)
			throws IOException {
		if (request.getConfig() == null) {
			request.setConfig(requestConfig);
		}
		// 随机取一个
		request.setHeader("User-Agent", Constants.userAgentArray[new Random()
				.nextInt(Constants.userAgentArray.length)]);
		HttpClientContext httpClientContext = HttpClientContext.create();
		httpClientContext.setCookieStore(cookieStore);// 存储cookie
		CloseableHttpResponse response = httpClient.execute(request,
				httpClientContext);
		return response;
	}

	/**
	 * 返回响应请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static CloseableHttpResponse getResponse(String url)
			throws IOException {
		HttpGet request = new HttpGet(url);
		return getResponse(request);
	}

	/**
	 * 设置request请求参数
	 * 
	 * @param request
	 * @param params
	 */
	public static void setHttpPostParams(HttpPost request,
			Map<String, String> params) {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		for (String key : params.keySet()) {
			formParams.add(new BasicNameValuePair(key, params.get(key)));
		}
		UrlEncodedFormEntity entity = null;

		try {
			entity = new UrlEncodedFormEntity(formParams, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		request.setEntity(entity);
	}

	public static CookieStore getCookieStore() {
		return cookieStore;
	}

	public static void setCookieStore(CookieStore cookieStore) {
		HttpClientUtils.cookieStore = cookieStore;
	}

	/**
	 * 设置请求配置
	 * 
	 * @return
	 */
	public static RequestConfig.Builder getRequestConfigBuilder() {
		return RequestConfig.custom().setSocketTimeout(Constants.TIMEOUT)
				.setConnectTimeout(Constants.TIMEOUT)
				.setConnectionRequestTimeout(Constants.TIMEOUT)
				.setCookieSpec(CookieSpecs.STANDARD);
	}
}
