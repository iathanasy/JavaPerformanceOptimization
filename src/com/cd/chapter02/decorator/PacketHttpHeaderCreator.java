package com.cd.chapter02.decorator;

/**
 * 
 * @author cd
 * @date 2019年3月26日 上午10:08:47
 * @desc 具体装饰器： 完成Http头部处理
 */
public class PacketHttpHeaderCreator extends PacketDecorator{

	public PacketHttpHeaderCreator(IPacketCreator c) {
		super(c);
	}

	@Override
	public String handlerContent() {
		//对给定数据加上http头信息
		StringBuffer sb = new StringBuffer();
		sb.append("Cache-Control:no-cache\n");
		sb.append("Date:Tue, 26 Mar 2019 02:13:08 GMT\n");
		sb.append(component.handlerContent());
		return sb.toString();
	}

}
