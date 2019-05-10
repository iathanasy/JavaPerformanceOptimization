package com.cd.chapter02.strategy;

/**
 * @description:
 * @author: Mr.Wang
 * @create: 2019-03-26 22:47
 **/
public class Main {
	public static void main(String[] args) {
		Context context;
		context = new Context(new BackDoor());
		// ��һ������
		context.operate();

		context = new Context(new BlockEnemy());
		context.operate();
	}
}
