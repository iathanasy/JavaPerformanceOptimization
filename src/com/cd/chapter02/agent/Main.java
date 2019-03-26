package com.cd.chapter02.agent;
/**
 * 代理：主题接口、真实主题、代理类、Main
 * @author cd
 * @date 2019年3月25日 下午3:00:58
 * @desc 主函数 引用IDBQuery接口，并使用代理类工作
 */
public class Main {

	public static void main(String[] args) {
		IDBQuery q = new DBQueryProxy();//使用代理
		String s = q.request();//在真正使用的时候才创建真实对象
		System.out.println(s);
		/**
		 * 注意：将代理模式用于实现延迟加载、可以有效的提升系统的启动速度
		 */
	}
}
