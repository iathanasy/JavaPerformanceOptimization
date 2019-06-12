package com.cd.chapter02.news.observer;

/**
 * 观察者实现类
 * @author cd
 * @date 2019年6月11日 下午2:16:24
 * @desc
 */
public class CurrentObserver implements Observer{
	
	public CurrentObserver(Subject subject){
		//每个观察者通过构造方法传入它所订阅的主题，并在主题中注册该观察者
		subject.registerObserver(this);
	}

	@Override
	public void update(String msg) {
		System.out.println(this.getClass().getName() +"："+ msg);
	}

}
