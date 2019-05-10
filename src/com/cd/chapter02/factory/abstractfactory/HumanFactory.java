package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 抽象工厂模式
 * @author: Mr.Wang
 * @create: 2019-03-27 22:39
 **/
public interface HumanFactory {
	// 创建黄人
	Human createYellowHuman();

	// 创建白人
	Human createWhiteHuman();

	// 创建黑人
	Human createBlackHuman();
}
