package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 男性白种人
 * @author: Mr.Wang
 * @create: 2019-03-27 22:30
 **/
public class WhiteMaleHuman extends AbstractWhiteHuman{
    @Override
    public void sex() {
        System.out.println("男性白种人");
    }
}
