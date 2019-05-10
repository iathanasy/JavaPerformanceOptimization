package com.cd.chapter02.factory.abstractfactory;

/**
 * @description:
 * @author: Mr.Wang
 * @create: 2019-03-27 22:51
 **/
public class Main {

	public static void main(String[] args) {
		// 制造男性
		HumanFactory maleHumanFactory = new MaleHumanFactory();
		// 制造女性
		HumanFactory femaleHumanFactory = new FemaleHumanFactory();

		// 开始制造 男黄人
		Human maleYellowHuman = maleHumanFactory.createYellowHuman();
		// 开始制造 女黄人
		Human femaleYellowHuman = femaleHumanFactory.createYellowHuman();

		maleYellowHuman.cry();
		maleYellowHuman.laugh();
		maleYellowHuman.talk();
		maleYellowHuman.sex();

		femaleYellowHuman.laugh();
		femaleYellowHuman.cry();
		femaleYellowHuman.talk();
		femaleYellowHuman.sex();

		// 下面可以制造其他颜色的人...
	}
}
