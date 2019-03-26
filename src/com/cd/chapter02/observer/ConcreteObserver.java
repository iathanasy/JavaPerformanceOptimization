package com.cd.chapter02.observer;
/**
 * 
 * @author cd
 * @date 2019年3月26日 上午11:55:12
 * @desc 具体的观察者
 */
public class ConcreteObserver implements IObserver{

	@Override
	public void update(User u) {
		System.out.println("observer receives information :"+ u.toString());
	}

}
