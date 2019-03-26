package com.cd.chapter02.agent.dynamic;

import java.lang.reflect.Method;

import com.cd.chapter02.agent.DBQuery;
import com.cd.chapter02.agent.IDBQuery;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @author cd
 * @date 2019��3��25�� ����3:20:56
 * @desc CGLIB���ɶ�̬����
 */
public class CglibDbQueryInterceptor implements MethodInterceptor{
	IDBQuery real = null;

	/**
	 * ��ʵ����
	 */
	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		if(real == null)
			real = new DBQuery();
		return real.request();
	}
	
	/**
	 * ����
	 * @return
	 */
	public static IDBQuery createCglibProxy(){
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(new CglibDbQueryInterceptor());//ָ��������������������߼�
		enhancer.setInterfaces(new Class[]{IDBQuery.class});//ָ��ʵ�ֵĽӿ�
		IDBQuery cglibProxy = (IDBQuery) enhancer.create();//���ɴ������ʵ��
		return cglibProxy;
	}

}
