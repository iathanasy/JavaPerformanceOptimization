package com.cd.chapter02.agent;
/**
 * 
 * @author cd
 * @date 2019��3��25�� ����2:54:27
 * @desc ��ʵ����  ���������� ������ܻ�Ƚ���
 */
public class DBQuery implements IDBQuery{
	
	public DBQuery(){
		//���ܰ������ݿ����ӵȺ�ʱ����
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public String request() {
		
		return "request string";
	}

}
