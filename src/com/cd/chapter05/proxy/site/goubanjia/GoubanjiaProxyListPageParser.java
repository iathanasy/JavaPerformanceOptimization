package com.cd.chapter05.proxy.site.goubanjia;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cd.chapter05.proxy.entity.Proxy;
import com.cd.chapter05.proxy.site.AbstractProxyPortDecode;

/**
 * www.goubanjia.com 网站代理解析
 * 
 * @author cd
 * @date 2019年5月8日 下午4:12:59
 * @desc
 */
public class GoubanjiaProxyListPageParser extends AbstractProxyPortDecode {

	@Override
	public List<Proxy> parse(String content) {

		List<Proxy> proxyList = new ArrayList<Proxy>();
		if (content == null || content.equals("")) {
			return proxyList;
		}

		Document doc = Jsoup.parse(content);
		setPort(doc, ".port");// 解码端口
		Elements elements = doc.select("table.table tr");
		for (Element element : elements) {
			// 获取td节点
			Element td = element.select("td").first();
			if (null == td)
				continue;

			String anonymous = element.select("td:eq(1)").first().text();
			/**
			 * 查找所有style属性包含none字符串的标签（页面上未显示的标签），并移除 包括以下两种 style=display:
			 * none; style=display:none;
			 */
			Elements ipTd = td.select("[style*=none;]");
			for (Element none : ipTd) {
				none.remove();
			}
			// 移除空格
			String ipPort = td.text().replaceAll(" ", "");
			Proxy proxy = new Proxy();
			if (anonymousFlag) {
				if(anonymous.contains("匿")){
					String ip = ipPort.split(":")[0];
					String port = ipPort.split(":")[1];
					String type = element.select("td:eq(2)").first().text();
					String location = element.select("td:eq(3)").first().text();
					proxy.setIp(ip);
					proxy.setPort(Integer.valueOf(port));
					proxy.setLocation(location.replaceAll(" ", ""));
					proxy.setAnonymous(anonymous);
					proxy.setType(type);

				}	
			}else{
				String ip = ipPort.split(":")[0];
				String port = ipPort.split(":")[1];
				String type = element.select("td:eq(2)").first().text();
				String location = element.select("td:eq(3)").first().text();
				proxy.setIp(ip);
				proxy.setPort(Integer.valueOf(port));
				proxy.setLocation(location.replaceAll(" ", ""));
				proxy.setAnonymous(anonymous);
				proxy.setType(type);
			}
			proxyList.add(proxy);

		}

		return proxyList;
	}

}
