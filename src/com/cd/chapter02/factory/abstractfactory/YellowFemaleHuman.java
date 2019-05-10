package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 女性黄种人
 * @author: Mr.Wang
 * @create: 2019-03-27 22:27
 **/
public class YellowFemaleHuman extends AbstractYellowHuman {

	@Override
	public void sex() {
		System.out.println("女性黄种人");
	}
}
