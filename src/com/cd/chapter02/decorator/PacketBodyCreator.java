package com.cd.chapter02.decorator;

/**
 * 
 * @author cd
 * @date 2019年3月26日 上午9:51:12
 * @desc 具体的组件 系统核心类
 */
public class PacketBodyCreator implements IPacketCreator{

	public String handlerContent() {
		
		return "Content of Packet";//构造核心数据，但不包括格式
	}

}
