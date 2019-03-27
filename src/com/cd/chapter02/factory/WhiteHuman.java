package com.cd.chapter02.factory;

/**
 * @description: 白人
 * @author: Mr.Wang
 * @create: 2019-03-27 21:16
 **/
public class WhiteHuman implements Human{
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
