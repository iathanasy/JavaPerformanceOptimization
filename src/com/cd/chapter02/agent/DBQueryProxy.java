package com.cd.chapter02.agent;
/**
 * 
 * @author cd
 * @date 2019��3��25�� ����2:56:07
 * @desc ������ ���������� �����ܿ� �������DBQuery��λ��
 */
public class DBQueryProxy implements IDBQuery{
	private DBQuery real = null;

	public String request() {
		//������Ҫ��ʱ��Ŵ�����ʵ���󣬴������̿��ܺ���
		if(real == null)
			real = new DBQuery();
		//�ڶ��̻߳����£����ﷵ��һ������࣬ ������Futureģʽ
		return real.request();
	}
	
	
	
}
