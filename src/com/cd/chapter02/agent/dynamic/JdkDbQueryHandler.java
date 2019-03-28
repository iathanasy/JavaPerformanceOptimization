package com.cd.chapter02.agent.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.cd.chapter02.agent.DBQuery;
import com.cd.chapter02.agent.IDBQuery;

/**
 *  动态代理使用字节码动态生成加载技术，在运行时生成并加载类
 * @author cd
 * @date 2019年3月25日 下午3:12:31
 * @desc jdk自带 推荐使用 CGLIB Javassist
 */
public class JdkDbQueryHandler implements InvocationHandler{
	IDBQuery real = null; //主题接口

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(real == null)
			real  = new DBQuery();//如果第一次调用，则生成真实对象
		return real.request(); //使用真实主题完成实际操作
	}
	
	public static IDBQuery createJdkProxy(){
		IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[]{IDBQuery.class}, 
				new JdkDbQueryHandler());//指定Handler
		return jdkProxy;
	}
	
}
