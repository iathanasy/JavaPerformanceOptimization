package com.cd.chapter02.strategy;

/**
 * @description: �����Ļ���
 * @author: Mr.Wang
 * @create: 2019-03-26 22:43
 **/
public class Context {
    //���캯��ʹ���Ǹ�����
    private IStrategy strategy;
    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

    //ʹ���Ǹ�����
    public void operate(){
        strategy.operate();;
    }
}
