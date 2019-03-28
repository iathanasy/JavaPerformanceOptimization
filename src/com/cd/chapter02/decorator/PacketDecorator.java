package com.cd.chapter02.decorator;

/**
 * 
 * @author cd
 * @date 2019年3月26日 上午9:53:18
 * @desc 装饰者  维护核心组件 component 对象
 */
public abstract class PacketDecorator implements IPacketCreator{

	IPacketCreator component;
	
	public PacketDecorator(IPacketCreator c){
		component = c;
	}
}
