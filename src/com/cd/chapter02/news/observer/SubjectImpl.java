package com.cd.chapter02.news.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题接口实现
 * @author cd
 * @date 2019年6月11日 上午11:53:03
 * @desc
 */
public class SubjectImpl implements Subject{
	
	private List<Observer> observers;
	private String msg;
	
	public void setMessage(String msg){
		this.msg = msg;
		notifyObserver();
	}
	
	public SubjectImpl(){
		observers = new ArrayList<Observer>();
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		int i = observers.indexOf(observer);
		if(i >= 0){
			observers.remove(i);
		}
	}

	@Override
	public void notifyObserver() {
		for (Observer o : observers) {
			o.update(msg);
		}
	}

}
