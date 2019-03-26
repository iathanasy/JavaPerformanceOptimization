package com.cd.chapter02.valueobject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @author cd
 * @date 2019��3��26�� ����4:14:31
 * @desc
 */
public class OrderManager extends UnicastRemoteObject implements IOrderManager{

	protected OrderManager() throws RemoteException {
		super();
	}

	@Override
	public String getClientName(int id) throws RemoteException{
		//���ؿͻ��Ķ�����
		return "billy";
	}

	@Override
	public String getProdName(int id) throws RemoteException{
		//������Ʒ����
		return "desk";
	}

	@Override
	public int getNumber(int id) throws RemoteException{
		//��������
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
		System.out.println("�޸��˶���"+ o);
	}
 
}
