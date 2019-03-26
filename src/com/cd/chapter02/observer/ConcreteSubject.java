package com.cd.chapter02.observer;

import java.util.Vector;

/**
 * 
 * @author cd
 * @date 2019��3��26�� ����11:07:18
 * @desc ���������ʵ��
 */
public class ConcreteSubject implements ISubject{
	
	Vector<IObserver> observers = new Vector<IObserver>();
	private User user;
	
	@Override
	public void attach(IObserver observer) {
		observers.addElement(observer);
	}

	@Override
	public void detach(IObserver observer) {
		observers.removeElement(observer);
	}

	@Override
	public void inform() {
		for (IObserver obs : observers) {
			obs.update(user); //ע�� ������֪ͨ�۲���
		}
	}
	
	public void setUser(User u){
		this.user = u;
		inform();
	}

}
