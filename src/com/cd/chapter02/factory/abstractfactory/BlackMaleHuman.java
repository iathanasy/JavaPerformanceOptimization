package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 男性黑种人
 * @author: Mr.Wang
 * @create: 2019-03-27 22:32
 **/
public class BlackMaleHuman extends AbstractBlackHuman{
    @Override
    public void sex() {
        System.out.println("男性黑种人");
    }
}
