package com.cd.chapter02.observer;
/**
 * 
 * @author cd
 * @date 2019��3��26�� ����11:55:12
 * @desc ����Ĺ۲���
 */
public class ConcreteObserver implements IObserver{

	@Override
	public void update(User u) {
		System.out.println("observer receives information :"+ u.toString());
	}

}
