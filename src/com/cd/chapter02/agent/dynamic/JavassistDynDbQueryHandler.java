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
 * @date 2019��3��25�� ����3:31:47
 * @desc Javassist ����
 */
public class JavassistDynDbQueryHandler implements MethodHandler{
	IDBQuery real = null;

	public Object invoke(Object arg0, Method arg1, Method arg2, Object[] arg3)
			throws Throwable {
		if(real == null)
			real = new DBQuery();
		return real.request();
	}
	
	/**
	 * 1.������ģʽ
	 * @return
	 * @throws Exception
	 */
	public static IDBQuery createJavassistDynProxy() throws Exception{
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setInterfaces(new Class[]{IDBQuery.class});//ָ���ӿ�
		Class proxyClass = proxyFactory.createClass();
		IDBQuery javassistProxy = (IDBQuery) proxyClass.newInstance();//
		((ProxyObject)javassistProxy).setHandler(new JavassistDynDbQueryHandler()); //ָ��handler������
		return javassistProxy;
	}
	
	/**
	 * �ֽ��뷽ʽ
	 * @return
	 * @throws NotFoundException 
	 */
	public static IDBQuery createJavassistBytecodeDynamicProxy() throws Exception{
		ClassPool mPool = new ClassPool(true);
		//��������
		CtClass mCtc = mPool.makeClass(IDBQuery.class.getName()+ "Javassist-BytecodeProxy");
		//��Ҫʵ�ֵĽӿ�
		mCtc.addInterface(mPool.get(IDBQuery.class.getName()));
		//��ӹ��캯��
		mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
		//�������ֶ���Ϣ ʹ�ö�̬Java����
		mCtc.addField(CtField.make("public " + IDBQuery.class.getName() +" real;", mCtc));
		String dbqueryname = DBQuery.class.getName();
		//��ӷ���������ʹ�ö�̬Java����ָ���ڲ��߼�
		mCtc.addMethod(CtMethod.make("public String request() { if(real == null) real = new "+dbqueryname +"(); return real.request();}", mCtc));
		//����������Ϣ���ɶ�̬��
		Class pc = mCtc.toClass();
		//���ɶ�̬���ʵ��
		IDBQuery bytecodeProxy = (IDBQuery) pc.newInstance();
		return bytecodeProxy;
	}

}
