package com.cd.chapter02.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 外系统的用户信息的实现类
 * @author: Mr.Wang
 * @create: 2019-04-01 21:20
 **/
public class OuterUser implements IOuterUser {
	@Override
	public Map getUserBaseInfo() {
		HashMap baseInfoMap = new HashMap();
		baseInfoMap.put("userName", "这个员工叫混世魔王....");
		baseInfoMap.put("mobileNumber", "这个员工电话是....");
		return baseInfoMap;
	}

	@Override
	public Map getUserOfficeInfo() {
		HashMap homeInfo = new HashMap();

		homeInfo.put("homeTelNumbner", "员工的家庭电话是....");
		homeInfo.put("homeAddress", "员工的家庭地址是....");
		return homeInfo;
	}

	@Override
	public Map getUserHomeInfo() {
		HashMap officeInfo = new HashMap();
		officeInfo.put("jobPosition", "这个人的职位是BOSS...");
		officeInfo.put("officeTelNumber", "员工的办公电话是....");
		return officeInfo;
	}
}
