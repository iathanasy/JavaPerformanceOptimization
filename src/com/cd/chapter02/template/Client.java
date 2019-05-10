package com.cd.chapter02.template;

/**
 * @description: 客户开始使用这个模型
 * @author: Mr.Wang
 * @create: 2019-04-01 21:32
 **/
public class Client {
	public static void main(String[] args) {
		// 客户开着H1型号，出去遛弯了
		HummerH1Model h1 = new HummerH1Model();
		h1.setAlarm(false);
		h1.run(); // 汽车跑起来了；

		// 客户开H2型号，出去玩耍了
		HummerModel h2 = new HummerH2Model();
		h2.run();
	}
}
