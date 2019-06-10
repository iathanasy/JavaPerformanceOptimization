package com.cd.chapter02.news.decorator;

/**
 * 具体装饰类(TwoDecorator)
 * @author cd
 * @date 2019年6月10日 下午2:55:00
 * @desc
 */
public class TwoDecorator extends Decorator{

	public TwoDecorator(Man man) {
		super(man);
	}
	
	@Override
	void aboutMe() {
		super.aboutMe();
		System.out.print(",我是装饰类Two");
	}

}
