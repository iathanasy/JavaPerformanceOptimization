package com.cd.chapter05.proxy.common.utlis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 * @author cd
 * @date 2019年5月15日 下午5:05:14
 * @desc
 */
public class RegexUtil {

	/**
	 * 正则匹配
	 * @param regex	: 正则表达式
	 * @param str	: 待匹配字符串
	 * @return boolean
	 */
	public static boolean matches(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	private static final String URL_REGEX = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

	/**
	 * url完整格式校验
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isAllUrl(String str) {
		if (str==null || str.trim().length()==0) {
			return false;
		}
		return matches(URL_REGEX, str);
	}
	
	/**
     * url格式校验
     */
    public static boolean isUrl(String url) {
        if (url!=null && url.trim().length()>0 && url.startsWith("http")) {
            return true;
        }
        return false;
    }
}
