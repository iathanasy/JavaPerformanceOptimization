package com.cd.chapter02.valueobject;
import java.rmi.Naming;


public class OrderManagerClient {

	public static void main(String[] args) {
		try {
			/*IOrderManager usermanager = (IOrderManager) Naming.lookup("OrderManager");
			long begin = System.currentTimeMillis();
			for (int i = 0; i < 1000; i++) {
				usermanager.getOrder(i);// Value Objectģʽ
			}
			//getOrder spend: 209
			System.out.println("getOrder spend: "+ (System.currentTimeMillis() - begin));
			begin = System.currentTimeMillis();
			
			for (int i = 0; i < 1000; i++) {
				usermanager.getClientName(i);//ͨ����ν�����ȡ����
				usermanager.getNumber(i);
				usermanager.getProdName(i);
			}
			
			//3 Method call spend: 327
			System.out.println("3 Method call spend: "+ (System.currentTimeMillis() - begin));
			System.out.println(usermanager.getOrder(0).getClientName());*/
			
			//�ɴ˿ɼ������ݴ��������Ч�ķ�װ �������Ե�����Զ�̷������õ�����
			
			//ҵ��������������ӻ��棬 �Ӷ�����Զ�̷������õĴ���
			/*BusinessDelegate bd = new BusinessDelegate();
			Order o = bd.getOrder(11);
			o.setNumber(11);
			bd.updateOrder(o);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
