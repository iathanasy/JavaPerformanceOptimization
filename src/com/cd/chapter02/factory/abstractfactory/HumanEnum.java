package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 人类型枚举
 * @author: Mr.Wang
 * @create: 2019-03-27 22:32
 **/
public enum HumanEnum {
	YellowMaleHuman("com.cd.chapter02.factory.abstractfactory.YellowMaleHuman"), YellowFemaleHuman(
			"com.cd.chapter02.factory.abstractfactory.YellowFemaleHuman"),

	WhiteMaleHuman("com.cd.chapter02.factory.abstractfactory.WhiteMaleHuman"), WhiteFemaleHuman(
			"com.cd.chapter02.factory.abstractfactory.WhiteFemaleHuman"),

	BlackMaleHuman("com.cd.chapter02.factory.abstractfactory.BlackMaleHuman"), BlackFemaleHuman(
			"com.cd.chapter02.factory.abstractfactory.BlackFemaleHuman");

	private String value = "";

	// 定义构造函数，目的是Data(value)类型匹配
	private HumanEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	/*
	 * java enum类型尽量简单使用，尽量不要使用多态、继承等方法 毕竟用Class完全可以代替enum
	 */
}
