package com.cd.chapter02.factory;

/**
 * @description: 黑人
 * @author: Mr.Wang
 * @create: 2019-03-27 21:18
 **/
public class BlackHuman implements Human {
	@Override
	public void laugh() {
		System.out.println("黑人会笑");
	}

	@Override
	public void cry() {
		System.out.println("黑人会哭");
	}

	@Override
	public void talk() {
		System.out.println("黑人会说话");
	}

}
