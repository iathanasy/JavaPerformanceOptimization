package com.cd.chapter04.future;

public class Client {

	public Data request(final String queryStr) {
		final FutureData future = new FutureData();
		new Thread() {
			public void run() {// RealData构建很慢
				// 所以在单独的线程中进行
				RealData realData = new RealData(queryStr);
				future.setRealData(realData);
			};
		}.start();
		return future; // Future会被立即返回
	}
}
