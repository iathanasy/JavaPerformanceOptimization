package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 男性工厂类
 * @author: Mr.Wang
 * @create: 2019-03-27 22:47
 **/
public class MaleHumanFactory extends AbstractHumanFactory {

	@Override
	public Human createYellowHuman() {
		return super.createHuman(HumanEnum.YellowMaleHuman);
	}

	@Override
	public Human createWhiteHuman() {
		return super.createHuman(HumanEnum.WhiteMaleHuman);
	}

	@Override
	public Human createBlackHuman() {
		return super.createHuman(HumanEnum.BlackMaleHuman);
	}
}
