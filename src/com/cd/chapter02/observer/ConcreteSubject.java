package com.cd.chapter02.observer;

import java.util.Vector;

/**
 * 
 * @author cd
 * @date 2019年3月26日 上午11:07:18
 * @desc 具体的主题实现
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
			obs.update(user); //注意 在这里通知观察者
		}
	}
	
	public void setUser(User u){
		this.user = u;
		inform();
	}

}
