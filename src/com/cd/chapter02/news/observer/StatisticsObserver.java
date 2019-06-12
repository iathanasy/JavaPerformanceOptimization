package com.cd.chapter02.news.observer;

public class StatisticsObserver implements Observer{
	
	public StatisticsObserver(Subject subject){
		subject.registerObserver(this);
	}

	@Override
	public void update(String msg) {
		System.out.println(this.getClass().getName()+ ":"+msg);
	}

}
