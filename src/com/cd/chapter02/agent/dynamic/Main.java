package com.cd.chapter02.agent.dynamic;

import com.cd.chapter02.agent.IDBQuery;

/**
 * @author cd
 * @date 2019��3��25�� ����3:53:09
 * @desc ��̬�������
 */
public class Main {

	public static final int CIRCLE = 30000000;
	public static void main(String[] args) throws Exception {
		IDBQuery d = null;
		long begin = System.currentTimeMillis();
		d = JdkDbQueryHandler.createJdkProxy(); //����JDK��̬����
		System.out.println("createJdkProxy: "+ (System.currentTimeMillis() - begin));
		System.out.println("JdkProxy class: "+ d.getClass().getName());
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			d.request();
		}
		System.out.println("callJdkProxy: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		d = CglibDbQueryInterceptor.createCglibProxy(); //����CGLIB��̬����
		System.out.println("createCglibProxy: "+ (System.currentTimeMillis() - begin));
		System.out.println("CglibProxy class: "+ d.getClass().getName());
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			d.request();
		}
		System.out.println("callCglibProxy: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		d = JavassistDynDbQueryHandler.createJavassistDynProxy(); //����Javassist��̬����
		System.out.println("createJavassistDynProxy: "+ (System.currentTimeMillis() - begin));
		System.out.println("JavassistDynProxy class: "+ d.getClass().getName());
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			d.request();
		}
		System.out.println("callJavassistDynProxy: "+ (System.currentTimeMillis() - begin));
		
		begin = System.currentTimeMillis();
		d = JavassistDynDbQueryHandler.createJavassistBytecodeDynamicProxy(); //���� Javassist��̬���� �ֽ��뷽ʽ
		System.out.println("createJavassistBytecodeDynamicProxy: "+ (System.currentTimeMillis() - begin));
		System.out.println("JavassistBytecodeDynamicProxy class: "+ d.getClass().getName());
		begin = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			d.request();
		}
		System.out.println("callJavassistBytecodeDynamicProxy: "+ (System.currentTimeMillis() - begin));
	}
}
