package com.cd.chapter02.decorator;

/**
 * 
 * @author cd
 * @date 2019��3��26�� ����9:51:12
 * @desc �������� ϵͳ������
 */
public class PacketBodyCreator implements IPacketCreator{

	public String handlerContent() {
		
		return "Content of Packet";//����������ݣ�����������ʽ
	}

}
