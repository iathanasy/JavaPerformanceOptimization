package com.cd.chapter02.factory;

/**
 * @description: ����
 * @author: Mr.Wang
 * @create: 2019-03-27 21:16
 **/
public class WhiteHuman implements Human{
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
