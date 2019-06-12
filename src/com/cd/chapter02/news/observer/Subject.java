package com.cd.chapter02.news.observer;

/**
 * 主题接口
 * @author cd
 * @date 2019年6月11日 上午11:43:01
 * @desc
 */
public interface Subject {

	//注册观察者
	void registerObserver(Observer observer);
	
	//移除
	void removeObserver(Observer observer);
	
	//通知观察者
	void notifyObserver();
}
