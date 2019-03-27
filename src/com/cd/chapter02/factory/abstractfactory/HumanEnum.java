package com.cd.chapter02.factory.abstractfactory;

/**
 * @description: ������ö��
 * @author: Mr.Wang
 * @create: 2019-03-27 22:32
 **/
public enum HumanEnum {
    YellowMaleHuman("com.cd.chapter02.factory.abstractfactory.YellowMaleHuman"),
    YellowFemaleHuman("com.cd.chapter02.factory.abstractfactory.YellowFemaleHuman"),

    WhiteMaleHuman("com.cd.chapter02.factory.abstractfactory.WhiteMaleHuman"),
    WhiteFemaleHuman("com.cd.chapter02.factory.abstractfactory.WhiteFemaleHuman"),

    BlackMaleHuman("com.cd.chapter02.factory.abstractfactory.BlackMaleHuman"),
    BlackFemaleHuman("com.cd.chapter02.factory.abstractfactory.BlackFemaleHuman");

    private String value="";
    //���幹�캯����Ŀ����Data(value)����ƥ��
    private HumanEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    /*
     * java enum���;�����ʹ�ã�������Ҫʹ�ö�̬���̳еȷ���
     * �Ͼ���Class��ȫ���Դ���enum
     */
}
