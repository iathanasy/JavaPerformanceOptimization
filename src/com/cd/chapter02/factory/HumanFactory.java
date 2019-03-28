package com.cd.chapter02.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @description: 工厂方法模式
 * @author: Mr.Wang
 * @create: 2019-03-27 21:19
 **/
public class HumanFactory {
    //延迟化 ，初始化过的Human都在这里
    private static HashMap<String, Human> humans = new HashMap<String, Human>();

    //制造 需要传入类型
    public static Human createHuman(Class c){
        Human human = null; //定义类型
        try {
            //如果map有直接去，不用初始化
            if(humans.containsKey(c.getSimpleName())){
                human = humans.get(c.getSimpleName());
            }else {
                human = (Human) Class.forName(c.getName()).newInstance();//生成实列
                //放入map中
                humans.put(c.getSimpleName(), human);
            }
        } catch (IllegalAccessException e) {
            System.out.println("定义错误");
        } catch (InstantiationException e) {
            System.out.println("指定类型");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到该实列");
        }
        return human;
    }

    //直接制造 随机
    public static Human createHuman(){
        Human human = null;
        List<Class> list = ClassUtils.getAllClassByinterface(Human.class);//定义了多少人类
        Random random = new Random();
        int rand = random.nextInt(list.size());

        human = createHuman(list.get(rand));//随机制造
        return human;
    }
}
