package com.cd.chapter04.producer;

/**
 * 
 * @author cd
 * @date 2019年4月2日 上午9:15:41
 * @desc 生产者和消费者之间的共享数据模型
 */
public final class PCData {// 任务相关数据 是不可继承，创建之后不可更改
	private final int intData;

	public PCData(int intData) {
		this.intData = intData;
	}

	public PCData(String intData) {
		this.intData = Integer.valueOf(intData);
	}

	public int getIntData() {
		return intData;
	}

	@Override
	public String toString() {
		return "PCData [intData=" + intData + "]";
	}

}
