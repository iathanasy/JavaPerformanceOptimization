package com.cd.chapter02.template;

/**
 * @description: H1和H2有什么差别，还真不知道，真没接触过悍马
 * @author: Mr.Wang
 * @create: 2019-04-01 21:30
 **/
public class HummerH2Model extends HummerModel{
    @Override
    public void start() {
        System.out.println("悍马H2发动...");
    }

    @Override
    public void stop() {
        System.out.println("悍马H1停车...");
    }

    @Override
    public void alarm() {
        System.out.println("悍马H2鸣笛...");
    }

    @Override
    public void engineBoom() {
        System.out.println("悍马H2引擎声音是这样在...");
    }

    //默认没有喇叭的
    @Override
    protected boolean isAlarm() {
        return false;
    }
}
