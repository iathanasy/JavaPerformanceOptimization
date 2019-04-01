package com.cd.chapter04.master;

public class PlusWorker extends Worker{

	@Override
	public Object handle(Object input) {
		//Worker 求立方和
		Integer i = (Integer) input;
		return i * i * i;
	}
}
