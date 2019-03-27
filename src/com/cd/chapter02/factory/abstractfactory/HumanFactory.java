package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: ���󹤳�ģʽ
 * @author: Mr.Wang
 * @create: 2019-03-27 22:39
 **/
public interface HumanFactory {
    //��������
    Human createYellowHuman();
    //��������
    Human createWhiteHuman();
    //��������
    Human createBlackHuman();
}
