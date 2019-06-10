package com.cd.chapter02.news.decorator;

/**
 * 具体构建 (ConcreteComponent)
 * @author cd
 * @date 2019年6月10日 下午2:49:31
 * @desc
 */
public class Potato extends Man{

	@Override
	void aboutMe() {
		System.out.print("Hi,我是Java");
	}

}
