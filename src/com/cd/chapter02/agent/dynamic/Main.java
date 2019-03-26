package com.cd.chapter02.agent.dynamic;

import com.cd.chapter02.agent.IDBQuery;

/**
 * @author cd
 * @date 2019年3月25日 下午3:53:09
 * @desc 动态代理测试
 */
public class Main {

	public static final int CIRCLE = 30000000;
	public static void main(String[] args) throws Exception {
		IDBQuery d = null;
		long begin = System.currentTimeMillis();
		d = JdkDbQueryHandler.createJdkProxy(); //测试JDK动态代理
		System.out.println("createJdkProxy: "+ (System.currentTimeMillis() - begin));
		System.out.println("JdkProxy class: "+ d.getClass().getName());
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			d.request();
		}
		System.out.println("callJdkProxy: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		d = CglibDbQueryInterceptor.createCglibProxy(); //测试CGLIB动态代理
		System.out.println("createCglibProxy: "+ (System.currentTimeMillis() - begin));
		System.out.println("CglibProxy class: "+ d.getClass().getName());
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			d.request();
		}
		System.out.println("callCglibProxy: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		d = JavassistDynDbQueryHandler.createJavassistDynProxy(); //测试Javassist动态代理
		System.out.println("createJavassistDynProxy: "+ (System.currentTimeMillis() - begin));
		System.out.println("JavassistDynProxy class: "+ d.getClass().getName());
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			d.request();
		}
		System.out.println("callJavassistDynProxy: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		d = JavassistDynDbQueryHandler.createJavassistBytecodeDynamicProxy(); //测试 Javassist动态代理 字节码方式
		System.out.println("createJavassistBytecodeDynamicProxy: "+ (System.currentTimeMillis() - begin));
		System.out.println("JavassistBytecodeDynamicProxy class: "+ d.getClass().getName());
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			d.request();
		}
		System.out.println("callJavassistBytecodeDynamicProxy: "+ (System.currentTimeMillis() - begin));
	}
}
