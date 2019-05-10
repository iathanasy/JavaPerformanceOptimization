package com.cd.chapter02.agent.dynamic;

import java.lang.reflect.Method;

import com.cd.chapter02.agent.DBQuery;
import com.cd.chapter02.agent.IDBQuery;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.NotFoundException;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

/**
 * 
 * @author cd
 * @date 2019年3月25日 下午3:31:47
 * @desc Javassist 代理
 */
public class JavassistDynDbQueryHandler implements MethodHandler {
	IDBQuery real = null;

	public Object invoke(Object arg0, Method arg1, Method arg2, Object[] arg3)
			throws Throwable {
		if (real == null)
			real = new DBQuery();
		return real.request();
	}

	/**
	 * 1.代理工厂模式
	 * 
	 * @return
	 * @throws Exception
	 */
	public static IDBQuery createJavassistDynProxy() throws Exception {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setInterfaces(new Class[] { IDBQuery.class });// 指定接口
		Class proxyClass = proxyFactory.createClass();
		IDBQuery javassistProxy = (IDBQuery) proxyClass.newInstance();//
		((ProxyObject) javassistProxy)
				.setHandler(new JavassistDynDbQueryHandler()); // 指定handler处理器
		return javassistProxy;
	}

	/**
	 * 字节码方式
	 * 
	 * @return
	 * @throws NotFoundException
	 */
	public static IDBQuery createJavassistBytecodeDynamicProxy()
			throws Exception {
		ClassPool mPool = new ClassPool(true);
		// 定义类名
		CtClass mCtc = mPool.makeClass(IDBQuery.class.getName()
				+ "Javassist-BytecodeProxy");
		// 需要实现的接口
		mCtc.addInterface(mPool.get(IDBQuery.class.getName()));
		// 添加构造函数
		mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
		// 添加类的字段信息 使用动态Java代码
		mCtc.addField(CtField.make("public " + IDBQuery.class.getName()
				+ " real;", mCtc));
		String dbqueryname = DBQuery.class.getName();
		// 添加方法，这里使用动态Java代码指定内部逻辑
		mCtc.addMethod(CtMethod.make(
				"public String request() { if(real == null) real = new "
						+ dbqueryname + "(); return real.request();}", mCtc));
		// 基于以上信息生成动态类
		Class pc = mCtc.toClass();
		// 生成动态类的实例
		IDBQuery bytecodeProxy = (IDBQuery) pc.newInstance();
		return bytecodeProxy;
	}

}
