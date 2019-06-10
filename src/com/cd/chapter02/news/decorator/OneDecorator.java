package com.cd.chapter02.news.decorator;

/**
 * 具体装饰类（OneDecorator）
 * @author cd
 * @date 2019年6月10日 下午2:52:54
 * @desc
 */
public class OneDecorator extends Decorator{

	public OneDecorator(Man man) {
		super(man);
	}

	@Override
	void aboutMe() {
		super.aboutMe();
		System.out.print(",我是装饰类One");
	}
}
