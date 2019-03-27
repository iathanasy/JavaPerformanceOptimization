package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 抽象白人
 * @author: Mr.Wang
 * @create: 2019-03-27 22:25
 **/
public abstract class AbstractWhiteHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("白人会笑");
    }

    @Override
    public void cry() {
        System.out.println("白人会哭");
    }

    @Override
    public void talk() {
        System.out.println("白人会说话");
    }
}
