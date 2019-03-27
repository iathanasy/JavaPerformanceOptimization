package com.cd.chapter02.factory.abstractfactory;

/**
 * @description:
 * @author: Mr.Wang
 * @create: 2019-03-27 22:51
 **/
public class Main {

    public static void main(String[] args) {
        //��������
        HumanFactory maleHumanFactory = new MaleHumanFactory();
        //����Ů��
        HumanFactory femaleHumanFactory = new FemaleHumanFactory();

        //��ʼ���� �л���
        Human maleYellowHuman = maleHumanFactory.createYellowHuman();
        //��ʼ���� Ů����
        Human femaleYellowHuman = femaleHumanFactory.createYellowHuman();

        maleYellowHuman.cry();
        maleYellowHuman.laugh();
        maleYellowHuman.talk();
        maleYellowHuman.sex();

        femaleYellowHuman.laugh();
        femaleYellowHuman.cry();
        femaleYellowHuman.talk();
        femaleYellowHuman.sex();

        //�����������������ɫ����...
    }
}
