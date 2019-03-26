package com.cd.chapter02.observer;
/**
 * 
 * @author cd
 * @date 2019年3月26日 上午11:01:11
 * @desc 主题接口
 */
public interface ISubject {
	void attach(IObserver observer);//添加观察者
	void detach(IObserver observer);//删除观察者
	void inform();//通知所有观察者
}
