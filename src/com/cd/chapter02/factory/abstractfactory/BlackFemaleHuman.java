package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 女性黑种人
 * @author: Mr.Wang
 * @create: 2019-03-27 22:31
 **/
public class BlackFemaleHuman extends AbstractBlackHuman{
    @Override
    public void sex() {
        System.out.println("女性黑种人");
    }
}
