package com.cd.chapter02.decorator;

/**
 * 
 * @author cd
 * @date 2019��3��26�� ����10:08:47
 * @desc ����װ������ ���Httpͷ������
 */
public class PacketHttpHeaderCreator extends PacketDecorator{

	public PacketHttpHeaderCreator(IPacketCreator c) {
		super(c);
	}

	@Override
	public String handlerContent() {
		//�Ը������ݼ���httpͷ��Ϣ
		StringBuffer sb = new StringBuffer();
		sb.append("Cache-Control:no-cache\n");
		sb.append("Date:Tue, 26 Mar 2019 02:13:08 GMT\n");
		sb.append(component.handlerContent());
		return sb.toString();
	}

}
