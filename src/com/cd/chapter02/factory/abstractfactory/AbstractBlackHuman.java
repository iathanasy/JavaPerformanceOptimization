package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: �������
 * @author: Mr.Wang
 * @create: 2019-03-27 22:26
 **/
public abstract class AbstractBlackHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("���˻�Ц");
    }

    @Override
    public void cry() {
        System.out.println("���˻��");
    }

    @Override
    public void talk() {
        System.out.println("���˻�˵��");
    }
}
