package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: �����ɫ��
 * @author: Mr.Wang
 * @create: 2019-03-27 22:23
 **/
public abstract class AbstractYellowHuman implements Human{

    public void laugh() {
        System.out.println("���˻�Ц");
    }


    public void cry() {
        System.out.println("���˻��");
    }


    public void talk() {
        System.out.println("���˻�˵��");
    }
}
