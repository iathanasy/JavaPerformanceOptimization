package com.cd.chapter02.agent.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.cd.chapter02.agent.DBQuery;
import com.cd.chapter02.agent.IDBQuery;

/**
 *  ��̬����ʹ���ֽ��붯̬���ɼ��ؼ�����������ʱ���ɲ�������
 * @author cd
 * @date 2019��3��25�� ����3:12:31
 * @desc jdk�Դ� �Ƽ�ʹ�� CGLIB Javassist
 */
public class JdkDbQueryHandler implements InvocationHandler{
	IDBQuery real = null; //����ӿ�

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(real == null)
			real  = new DBQuery();//�����һ�ε��ã���������ʵ����
		return real.request(); //ʹ����ʵ�������ʵ�ʲ���
	}
	
	public static IDBQuery createJdkProxy(){
		IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[]{IDBQuery.class}, 
				new JdkDbQueryHandler());//ָ��Handler
		return jdkProxy;
	}
	
}
