package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 抽象黄色人
 * @author: Mr.Wang
 * @create: 2019-03-27 22:23
 **/
public abstract class AbstractYellowHuman implements Human{

    public void laugh() {
        System.out.println("黄人会笑");
    }


    public void cry() {
        System.out.println("黄人会哭");
    }


    public void talk() {
        System.out.println("黄人会说话");
    }
}
