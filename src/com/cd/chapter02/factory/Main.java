package com.cd.chapter02.factory;

/**
 * @description:
 * @author: Mr.Wang
 * @create: 2019-03-27 21:24
 **/
public class Main {
    public static void main(String[] args) {
        //传入类型 制造 白人
        Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.cry();
        whiteHuman.laugh();
        whiteHuman.talk();

        //传入类型 制造 黑人
        Human blackHuman = HumanFactory.createHuman(BlackHuman.class);
        blackHuman.cry();
        blackHuman.laugh();
        blackHuman.talk();

        //传入类型 制造 黄人
        Human yellowHuman = HumanFactory.createHuman(YellowHuman.class);
        yellowHuman.talk();
        yellowHuman.laugh();
        yellowHuman.cry();


        /////////////////////////////////////////////////////////////////
        //随机制造
        for (int i = 0; i < 100000; i++){
            Human human = HumanFactory.createHuman();
            human.cry();
            human.laugh();
            human.talk();
        }
    }
}
