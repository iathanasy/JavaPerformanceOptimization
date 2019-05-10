package com.cd.chapter02.strategy;

/**
 * @description: 具体策略操作
 * @author: Mr.Wang
 * @create: 2019-03-26 22:40
 **/
public class BackDoor implements IStrategy {

	@Override
	public void operate() {
		System.out.println("操作一...");
	}
}
