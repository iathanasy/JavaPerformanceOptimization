package com.cd.chapter02.adapter;

import java.util.Map;

/**
 * @description: 外系统的人员信息
 * @author: Mr.Wang
 * @create: 2019-04-01 21:17
 **/
public interface IOuterUser {

	// 基本信息，比如名称，性别/手机号码等
	Map getUserBaseInfo();

	// 工作区域信息
	Map getUserOfficeInfo();

	// 用户家庭信息
	Map getUserHomeInfo();
}
