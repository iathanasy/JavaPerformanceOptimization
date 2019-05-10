package com.cd.chapter02.facade;

/**
 * @description: 开始写信了
 * @author: Mr.Wang
 * @create: 2019-04-01 20:54
 **/
public class Client {
	public static void main(String[] args) {
		String context = "Hello,It's me,do you know who I am? I'm your old lover. I'd like to....";
		String address = "Happy Road No. 666,God Province,Heaven";

		// 现代化的邮局，有这项服务，邮局名称叫Hell Road
		ModenPostOffice modenPostOffice = new ModenPostOffice();
		// 你只要把信的内容和收信人地址给他，他会帮你完成一系列的工作；
		modenPostOffice.sendLetter(context, address);

	}
}
