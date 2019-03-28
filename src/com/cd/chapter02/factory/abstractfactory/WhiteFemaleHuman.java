package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 女性白种人
 * @author: Mr.Wang
 * @create: 2019-03-27 22:30
 **/
public class WhiteFemaleHuman extends AbstractWhiteHuman{
    @Override
    public void sex() {
        System.out.println("女性白种人");
    }
}
