package com.cd.chapter02.decorator;

/**
 * 
 * @author cd
 * @date 2019��3��26�� ����9:59:55
 * @desc �����װ���� �� ��ɺ��ķ������ݽ���Html��ʽ��
 */
public class PacketHtmlHeaderCreator extends PacketDecorator{

	public PacketHtmlHeaderCreator(IPacketCreator c) {
		super(c);
	}
	
	/**
	 * ʵ�ָ���ӿڷ���
	 * ���������ݷ�װ��HTML
	 */
	@Override
	public String handlerContent() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<body>");
		sb.append(component.handlerContent());
		sb.append("</body>");
		sb.append("</html>\n");
		return sb.toString();
	}

}
