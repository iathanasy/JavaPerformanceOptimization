package com.cd.chapter02.agent;

/**
 * 
 * @author cd
 * @date 2019年3月25日 下午2:54:27
 * @desc 真实主题 重量级对象 构造可能会比较慢
 */
public class DBQuery implements IDBQuery {

	public DBQuery() {
		// 可能包含数据库连接等耗时操作
		/*
		 * try { Thread.sleep(1000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

	public String request() {

		return "request string";
	}

}
