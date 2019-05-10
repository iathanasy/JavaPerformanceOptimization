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
 * @date 2019年3月25日 下午3:20:56
 * @desc CGLIB生成动态代理
 */
public class CglibDbQueryInterceptor implements MethodInterceptor {
	IDBQuery real = null;

	/**
	 * 真实主题
	 */
	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		if (real == null)
			real = new DBQuery();
		return real.request();
	}

	/**
	 * 代理
	 * 
	 * @return
	 */
	public static IDBQuery createCglibProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(new CglibDbQueryInterceptor());// 指定切入器，定义代理类逻辑
		enhancer.setInterfaces(new Class[] { IDBQuery.class });// 指定实现的接口
		IDBQuery cglibProxy = (IDBQuery) enhancer.create();// 生成代理类的实例
		return cglibProxy;
	}

}
