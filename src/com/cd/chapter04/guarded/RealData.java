package com.cd.chapter04.guarded;

public class RealData implements Data{
	protected final String result;
	
	public RealData(String para){
		//RealData 构造可能很慢，需要用户等待很久， 使用sleep模拟
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(para+",");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		result = sb.substring(0, sb.length()-1).toString();
	}
	
	@Override
	public String getResult() {
	
		return result;
	}

}
