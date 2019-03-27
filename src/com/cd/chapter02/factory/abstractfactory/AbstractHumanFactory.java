package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: ���󹤳�
 * @author: Mr.Wang
 * @create: 2019-03-27 22:41
 **/
public abstract class AbstractHumanFactory implements HumanFactory{

    protected Human createHuman(HumanEnum humanEnum){
        Human human = null;
        if(!humanEnum.getValue().equals("")) {//����������Ĳ���һ��Enum�е�Element������
            try {
                human = (Human) Class.forName(humanEnum.getValue()).newInstance();
            } catch (Exception e) {
                //��Ϊʹ�õ�enum,�����쳣������ᷢ�� ������enum������
                e.printStackTrace();
            }
        }
        return human;
    }
}
