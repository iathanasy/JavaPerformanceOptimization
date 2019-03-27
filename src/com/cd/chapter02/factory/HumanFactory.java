package com.cd.chapter02.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @description: ��������ģʽ
 * @author: Mr.Wang
 * @create: 2019-03-27 21:19
 **/
public class HumanFactory {
    //�ӳٻ� ����ʼ������Human��������
    private static HashMap<String, Human> humans = new HashMap<String, Human>();

    //���� ��Ҫ��������
    public static Human createHuman(Class c){
        Human human = null; //��������
        try {
            //���map��ֱ��ȥ�����ó�ʼ��
            if(humans.containsKey(c.getSimpleName())){
                human = humans.get(c.getSimpleName());
            }else {
                human = (Human) Class.forName(c.getName()).newInstance();//����ʵ��
                //����map��
                humans.put(c.getSimpleName(), human);
            }
        } catch (IllegalAccessException e) {
            System.out.println("�������");
        } catch (InstantiationException e) {
            System.out.println("ָ������");
        } catch (ClassNotFoundException e) {
            System.out.println("�Ҳ�����ʵ��");
        }
        return human;
    }

    //ֱ������ ���
    public static Human createHuman(){
        Human human = null;
        List<Class> list = ClassUtils.getAllClassByinterface(Human.class);//�����˶�������
        Random random = new Random();
        int rand = random.nextInt(list.size());

        human = createHuman(list.get(rand));//�������
        return human;
    }
}
