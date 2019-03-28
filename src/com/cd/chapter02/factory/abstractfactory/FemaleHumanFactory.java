package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 女性工厂
 * @author: Mr.Wang
 * @create: 2019-03-27 22:50
 **/
public class FemaleHumanFactory extends AbstractHumanFactory{
    @Override
    public Human createYellowHuman() {
        return super.createHuman(HumanEnum.YellowFemaleHuman);
    }

    @Override
    public Human createWhiteHuman() {
        return super.createHuman(HumanEnum.WhiteFemaleHuman);
    }

    @Override
    public Human createBlackHuman() {
        return super.createHuman(HumanEnum.WhiteFemaleHuman);
    }
}
