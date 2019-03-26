package com.cd.chapter02.decorator;

/**
 * 
 * @author cd
 * @date 2019年3月26日 上午9:59:55
 * @desc 具体的装饰器 ： 完成核心发布内容进行Html格式化
 */
public class PacketHtmlHeaderCreator extends PacketDecorator{

	public PacketHtmlHeaderCreator(IPacketCreator c) {
		super(c);
	}
	
	/**
	 * 实现父类接口方法
	 * 将给定数据封装成HTML
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
