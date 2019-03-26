package com.cd.chapter02.valueobject;
import java.rmi.Naming;


public class OrderManagerClient {

	public static void main(String[] args) {
		try {
			/*IOrderManager usermanager = (IOrderManager) Naming.lookup("OrderManager");
			long begin = System.currentTimeMillis();
			for (int i = 0; i < 1000; i++) {
				usermanager.getOrder(i);// Value Object模式
			}
			//getOrder spend: 209
			System.out.println("getOrder spend: "+ (System.currentTimeMillis() - begin));
			begin = System.currentTimeMillis();
			
			for (int i = 0; i < 1000; i++) {
				usermanager.getClientName(i);//通过多次交互获取数据
				usermanager.getNumber(i);
				usermanager.getProdName(i);
			}
			
			//3 Method call spend: 327
			System.out.println("3 Method call spend: "+ (System.currentTimeMillis() - begin));
			System.out.println(usermanager.getOrder(0).getClientName());*/
			
			//由此可见对数据传输进行有效的封装 可以明显的提升远程方法调用的性能
			
			//业务代理对象可以增加缓存， 从而减少远程方法调用的次数
			/*BusinessDelegate bd = new BusinessDelegate();
			Order o = bd.getOrder(11);
			o.setNumber(11);
			bd.updateOrder(o);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
