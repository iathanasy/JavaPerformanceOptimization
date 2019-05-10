package com.cd.chapter04.futuretask;

import java.util.concurrent.Callable;

public class RealData implements Callable<String> {
	private String para;

	public RealData(String para) {
		this.para = para;
	}

	@Override
	public String call() throws Exception {
		// RealData 构造可能很慢，需要用户等待很久， 使用sleep模拟
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(para + ",");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.substring(0, sb.length() - 1).toString();
	}

}
