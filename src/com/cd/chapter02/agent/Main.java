package com.cd.chapter02.agent;
/**
 * ��������ӿڡ���ʵ���⡢�����ࡢMain
 * @author cd
 * @date 2019��3��25�� ����3:00:58
 * @desc ������ ����IDBQuery�ӿڣ���ʹ�ô����๤��
 */
public class Main {

	public static void main(String[] args) {
		IDBQuery q = new DBQueryProxy();//ʹ�ô���
		String s = q.request();//������ʹ�õ�ʱ��Ŵ�����ʵ����
		System.out.println(s);
		/**
		 * ע�⣺������ģʽ����ʵ���ӳټ��ء�������Ч������ϵͳ�������ٶ�
		 */
	}
}
