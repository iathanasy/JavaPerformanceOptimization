package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 男性黄种人
 * @author: Mr.Wang
 * @create: 2019-03-27 22:29
 **/
public class YellowMaleHuman extends AbstractYellowHuman {
	@Override
	public void sex() {
		System.out.println("男性黄种人");
	}
}
