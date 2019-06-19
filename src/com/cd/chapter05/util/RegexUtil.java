package com.cd.chapter05.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * regex tool
 *
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
	 * url格式校验
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isUrl(String str) {
		if (str==null || str.trim().length()==0) {
			return false;
		}
		return matches(URL_REGEX, str);
	}
	
	/**
	 * 提取时间格式
	 * @param pInput
	 * @return
	 */
	public static String extractTime(String pInput) {
        if (pInput == null) {
            return null;
        }
        String regEx = "\\d{4}-\\d{2}-\\d{2}";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(pInput);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }
	
	public static void main(String[] args) {
		System.out.println(extractTime("该的信息2019-05-30　/　1006人看过"));
	}

}