package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 抽象黑人
 * @author: Mr.Wang
 * @create: 2019-03-27 22:26
 **/
public abstract class AbstractBlackHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黑人会笑");
    }

    @Override
    public void cry() {
        System.out.println("黑人会哭");
    }

    @Override
    public void talk() {
        System.out.println("黑人会说话");
    }
}
