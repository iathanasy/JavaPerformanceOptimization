package com.cd.chapter02.news.decorator;

/**
 * 装饰者模式
 * @author cd
 * @date 2019年6月10日 下午2:57:52
 * @desc
 * @see java.io.InputStream  抽象构件
 * @see java.io.FilterInputStream 抽象装饰类
 * @see java.io.BufferedInputStream 具体的装饰类
 * @see java.io.DataInputStream 具体的装饰类
 */
public class DecoratorPattern {

	public static void main(String[] args) {
		//核心构建
		Man potato = new Potato();
		potato.aboutMe();//基础功能
		System.out.println();
		
		//功能增加
		potato = new TwoDecorator(new OneDecorator(potato));
		potato.aboutMe();//增加后的功能
	}
	
	 /**
     * 总结：
     * 装饰者模式，可以动态地将责任附加到对象上，若要扩展功能，装饰者提供了比继承更加有
     * 弹性的替代方案，有效的避免了类爆炸现象的产生。虽然装饰者模式能够动态将责任附加到
     * 对象上，但是他会产生许多的细小对象，增加了系统的复杂度。java.io 包可谓是装饰者
     * 模式的最佳实践。
     */
}
