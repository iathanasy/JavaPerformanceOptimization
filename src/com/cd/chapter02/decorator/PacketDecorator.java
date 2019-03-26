package com.cd.chapter02.decorator;

/**
 * 
 * @author cd
 * @date 2019��3��26�� ����9:53:18
 * @desc װ����  ά��������� component ����
 */
public abstract class PacketDecorator implements IPacketCreator{

	IPacketCreator component;
	
	public PacketDecorator(IPacketCreator c){
		component = c;
	}
}
