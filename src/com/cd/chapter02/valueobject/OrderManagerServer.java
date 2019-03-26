package com.cd.chapter02.valueobject;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class OrderManagerServer {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);//ע��RMI�˿�
			IOrderManager usermanager = new OrderManager();//RMIԶ�̶���
			Naming.rebind("OrderManager", usermanager);//��RMI����
			System.out.println("OrderManager is ready.");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
}
