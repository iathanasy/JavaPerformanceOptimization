package com.cd.chapter02.valueobject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @author cd
 * @date 2019年3月26日 下午4:14:31
 * @desc
 */
public class OrderManager extends UnicastRemoteObject implements IOrderManager{

	protected OrderManager() throws RemoteException {
		super();
	}

	@Override
	public String getClientName(int id) throws RemoteException{
		//返回客户的订单名
		return "billy";
	}

	@Override
	public String getProdName(int id) throws RemoteException{
		//返回商品名字
		return "desk";
	}

	@Override
	public int getNumber(int id) throws RemoteException{
		//返回数量
		return 20;
	}

	@Override
	public Order getOrder(int id) throws RemoteException {
		Order o = new Order();
		o.setClientName("billy");
		o.setNumber(20);
		o.setProdunctName("desk");
		return o;
	}

	@Override
	public boolean checkUser(int uid) throws RemoteException {
		
		return true;
	}

	@Override
	public void updateOrder(Order o) throws RemoteException{
		System.out.println("修改了对象："+ o);
	}
 
}
