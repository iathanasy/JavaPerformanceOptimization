package com.cd.chapter02.builder;

import java.util.ArrayList;

/**
 * @description: 给定一个顺序，返回一个宝马车
 * @author: Mr.Wang
 * @create: 2019-04-01 22:15
 **/
public class BMWBuilder extends CarBuilder {
	private BMWModel bmw = new BMWModel();

	@Override
	public void setSequence(ArrayList<String> sequence) {
		bmw.setSequence(sequence);
	}

	@Override
	public CarModel getCarModel() {
		return this.bmw;
	}
}
