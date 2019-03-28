package com.cd.chapter02.agent;
/**
 * 
 * @author cd
 * @date 2019年3月25日 下午2:56:07
 * @desc 代理类 轻量级对象 创建很快 用于替代DBQuery的位置
 */
public class DBQueryProxy implements IDBQuery{
	private DBQuery real = null;

	public String request() {
		//真正需要的时候才创建真实对象，创建过程可能很慢
		if(real == null)
			real = new DBQuery();
		//在多线程环境下，这里返回一个虚假类， 类似于Future模式
		return real.request();
	}
	
	
	
}
