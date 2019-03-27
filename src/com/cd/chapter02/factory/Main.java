package com.cd.chapter02.factory;

/**
 * @description:
 * @author: Mr.Wang
 * @create: 2019-03-27 21:24
 **/
public class Main {
    public static void main(String[] args) {
        //�������� ���� ����
        Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.cry();
        whiteHuman.laugh();
        whiteHuman.talk();

        //�������� ���� ����
        Human blackHuman = HumanFactory.createHuman(BlackHuman.class);
        blackHuman.cry();
        blackHuman.laugh();
        blackHuman.talk();

        //�������� ���� ����
        Human yellowHuman = HumanFactory.createHuman(YellowHuman.class);
        yellowHuman.talk();
        yellowHuman.laugh();
        yellowHuman.cry();


        /////////////////////////////////////////////////////////////////
        //�������
        for (int i = 0; i < 100000; i++){
            Human human = HumanFactory.createHuman();
            human.cry();
            human.laugh();
            human.talk();
        }
    }
}
