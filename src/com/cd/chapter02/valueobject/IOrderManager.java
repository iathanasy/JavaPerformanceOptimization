package com.cd.chapter02.valueobject;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 
 * @author cd
 * @date 2019��3��26�� ����4:11:35
 * @desc 
 */
public interface IOrderManager extends Remote{
	
	public Order getOrder(int id) throws RemoteException; //Value Object ģʽ

	public String getClientName(int id) throws RemoteException;
	public String getProdName(int id) throws RemoteException;
	public int getNumber(int id) throws RemoteException;
	
	public boolean checkUser(int uid) throws RemoteException;
	public void updateOrder(Order o) throws RemoteException;
}
