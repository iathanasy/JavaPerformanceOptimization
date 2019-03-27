package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: 抽象工厂
 * @author: Mr.Wang
 * @create: 2019-03-27 22:41
 **/
public abstract class AbstractHumanFactory implements HumanFactory{

    protected Human createHuman(HumanEnum humanEnum){
        Human human = null;
        if(!humanEnum.getValue().equals("")) {//如果传进来的不是一个Enum中的Element不处理
            try {
                human = (Human) Class.forName(humanEnum.getValue()).newInstance();
            } catch (Exception e) {
                //因为使用的enum,这种异常情况不会发送 ，除非enum有问题
                e.printStackTrace();
            }
        }
        return human;
    }
}
