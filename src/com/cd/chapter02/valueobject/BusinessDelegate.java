package com.cd.chapter02.valueobject;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 
 * @author cd
 * @date 2019��3��26�� ����4:56:37
 * @desc ҵ�����ģʽ
 */
public class BusinessDelegate {

	IOrderManager usermanager = null;//��װԶ�̷������õ�����
	
	public BusinessDelegate(){
		try {
			usermanager = (IOrderManager) Naming.lookup("OrderManager");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkUserFromCache(int uid){
		return true;
	}
	
	public boolean checkUser(int uid) throws RemoteException{
		//��ǰ���󱻶���ͻ��˹���
		//�����ڱ��ػ�����У���û�
		if(!checkUserFromCache(uid))
			return usermanager.checkUser(1);
		return true;
	}
	
	public Order getOrderFromCache(int oid){
		return null;
	}
	
	public Order getOrder(int oid) throws RemoteException{
		//�����ڱ��ػ����л�ȡ����
		//����Զ�̷������ô���
		Order order = getOrderFromCache(oid);
		if(order == null)
			return usermanager.getOrder(oid);
		return order;
	}
	
	public boolean updateOrder(Order order) throws RemoteException{
		//��¶��չʾ�㷽��
		//��װ��ҵ������
		//������Զ����ִ��
		if(checkUser(1)){
			Order o = getOrder(1);
			o.setNumber(10);
			usermanager.updateOrder(o);
		}
		return true;
	}
}
