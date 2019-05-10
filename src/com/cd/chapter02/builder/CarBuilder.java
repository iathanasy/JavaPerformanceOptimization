package com.cd.chapter02.builder;

import java.util.ArrayList;

/**
 * @description: 要什么顺序的车， 你说我给你建造出来
 * @author: Mr.Wang
 * @create: 2019-04-01 22:09
 **/
public abstract class CarBuilder {
	// 建造一个模型，你要给我一个顺序要，就是组装顺序
	public abstract void setSequence(ArrayList<String> sequence);

	// 设置完毕顺序后，就可以直接拿到这个车辆模型
	public abstract CarModel getCarModel();
}
