package com.cd.chapter02.strategy;

/**
 * @description: 操作的环境
 * @author: Mr.Wang
 * @create: 2019-03-26 22:43
 **/
public class Context {
    //构造函数使用那个操作
    private IStrategy strategy;
    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

    //使用那个操作
    public void operate(){
        strategy.operate();;
    }
}
