package com.cd.chapter02.observer;
/**
 * 
 * @author cd
 * @date 2019��3��26�� ����11:01:11
 * @desc ����ӿ�
 */
public interface ISubject {
	void attach(IObserver observer);//��ӹ۲���
	void detach(IObserver observer);//ɾ���۲���
	void inform();//֪ͨ���й۲���
}
