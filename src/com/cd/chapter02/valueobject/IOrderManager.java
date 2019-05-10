package com.cd.chapter02.valueobject;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author cd
 * @date 2019年3月26日 下午4:11:35
 * @desc
 */
public interface IOrderManager extends Remote {

	public Order getOrder(int id) throws RemoteException; // Value Object 模式

	public String getClientName(int id) throws RemoteException;

	public String getProdName(int id) throws RemoteException;

	public int getNumber(int id) throws RemoteException;

	public boolean checkUser(int uid) throws RemoteException;

	public void updateOrder(Order o) throws RemoteException;
}
