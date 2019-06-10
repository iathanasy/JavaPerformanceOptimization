package com.cd.chapter02.news.decorator;

/**
 * 抽象装饰类
 * @author cd
 * @date 2019年6月10日 下午2:51:05
 * @desc
 */
public class Decorator extends Man{
	
	private Man man;
	
	public Decorator(Man man){
		this.man = man;
	}

	@Override
	void aboutMe() {
		man.aboutMe();
	}

}
