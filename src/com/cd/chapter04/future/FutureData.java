package com.cd.chapter04.future;

/**
 * 
 * @author cd
 * @date 2019年4月1日 下午3:24:57
 * @desc FutureData 是Future模式的关键，是真实数据RealData的代理，封装了获取RealData的等待过程
 */
public class FutureData implements Data{

	protected RealData realData = null;
	protected boolean isReady = false;
	
	public synchronized void setRealData(RealData realData){
		if(isReady) return;
		this.realData =realData;
		isReady = true;
		notifyAll();//RealData已经被注入，通知getResult();
	}
	
	@Override
	public synchronized String getResult() { //会等待 RealData构造完成
		while(!isReady)
			try {
				wait(); //一直等待 ，直到 RealData被注入
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return realData.result; //由RealData实现
	}

}
