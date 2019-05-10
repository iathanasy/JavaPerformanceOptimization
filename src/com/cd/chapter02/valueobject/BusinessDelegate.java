package com.cd.chapter02.valueobject;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 
 * @author cd
 * @date 2019年3月26日 下午4:56:37
 * @desc 业务代理模式
 */
public class BusinessDelegate {

	IOrderManager usermanager = null;// 封装远程方法调用的流程

	public BusinessDelegate() {
		try {
			usermanager = (IOrderManager) Naming.lookup("OrderManager");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkUserFromCache(int uid) {
		return true;
	}

	public boolean checkUser(int uid) throws RemoteException {
		// 当前对象被多个客户端共享
		// 可以在本地缓存中校验用户
		if (!checkUserFromCache(uid))
			return usermanager.checkUser(1);
		return true;
	}

	public Order getOrderFromCache(int oid) {
		return null;
	}

	public Order getOrder(int oid) throws RemoteException {
		// 可以在本地缓存中获取订单
		// 减少远程方法调用次数
		Order order = getOrderFromCache(oid);
		if (order == null)
			return usermanager.getOrder(oid);
		return order;
	}

	public boolean updateOrder(Order order) throws RemoteException {
		// 暴露给展示层方法
		// 封装了业务流程
		// 可能在远程中执行
		if (checkUser(1)) {
			Order o = getOrder(1);
			o.setNumber(10);
			usermanager.updateOrder(o);
		}
		return true;
	}
}
