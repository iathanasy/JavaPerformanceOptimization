package com.cd.chapter02.factory;

/**
 * @description: 黄人
 * @author: Mr.Wang
 * @create: 2019-03-27 21:15
 **/
public class YellowHuman implements Human {
	@Override
	public void laugh() {
		System.out.println("黄人会笑");
	}

	@Override
	public void cry() {
		System.out.println("黄人会哭");
	}

	@Override
	public void talk() {
		System.out.println("黄人会说话");
	}
}
